package AnalizadorEstructural;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.parsers.IntegratedParserConfiguration;

import analizadorLexico.Campo;
import analizadorLexico.Linea;

public class Estructura {

	Linea lineaLeida;
	String lineaPura=new String();
	List<Campo> listaDeCampos;
	String rutaDelArchivo;
	String trama;
	// constructor de Clase Estructura	
	public Estructura(){
		this.lineaLeida = new Linea();
		this.lineaPura= new String();
		this.rutaDelArchivo = new String();
		this.listaDeCampos= new ArrayList<>();
	}


	public List<Campo> getListaDeCampos() {
		return listaDeCampos;
	}

	public void setListaDeCampos(List<Campo> listaDeCampos) {
		this.listaDeCampos = listaDeCampos;
	}

	// 	Genera la lsita de campos, leyendo linea por linea enviadnoals al analizador lexico y obteniendo el campo
	public void generarListaDeCampos() throws Exception{
		try(BufferedReader br = new BufferedReader(new FileReader(rutaDelArchivo))) {	

			Campo nuevoCampo ;
			lineaPura = br.readLine();
			lineaPura = lineaPura.trim();
			//TODO: investigar funcion trim() en strings
			while (lineaPura!=null && ! lineaPura.equals("")){
			
					lineaLeida.setUpLinea();// esto es clave
					lineaLeida.setLineaAnalizar(lineaPura);
					lineaLeida.obtenerTokens();
					nuevoCampo=lineaLeida.generarCampo();
					
					if (nuevoCampo.getNivel() != 88){
						listaDeCampos.add(nuevoCampo);
					}
					lineaPura = br.readLine();
					if (lineaPura!=null){
						lineaPura = lineaPura.trim();	
					}
					
					
					
			}	   
			br.close();


		}catch  (Exception e)  {

			throw new Exception("Error de lectura");

		}

	}
	public void BuscarPadre(){
		for (int i=0;i<this.listaDeCampos.size();i++ ){
			if (this.listaDeCampos.get(i).getEsOccurs()){
				BuscarHijitos(i);
				ClonarHijitos(i);
			}
		}
	}
	public void BuscarHijitos(int indice){

		Campo campoOccurse = this.listaDeCampos.get(indice);
		int nivel = campoOccurse.getNivel();
		indice=indice+1;

		while (indice < this.listaDeCampos.size() && this.listaDeCampos.get(indice).getNivel()> nivel){
			aniadirOccurse(campoOccurse,this.listaDeCampos.get(indice));
			indice++;
		}


	}
	public void ClonarHijitos(int indicePadre){
		Campo padre = this.listaDeCampos.get(indicePadre);
		int tamanio = padre.getOccursDeCampos().size();
		this.listaDeCampos.get(indicePadre);
		int contador = 1;
		// crear nueva lista vacia
		// for de 0 a cantidad de occurs
		// copiar los elementos -> generar una lista a partir de otra y un constructor que reciba la misma clase new Campo(campo)
		
		while (contador < padre.getCantidadDeOccurs()){
			for (int J=0;J<tamanio;J++) {
				padre.getOccursDeCampos().add(padre.getOccursDeCampos().get(J));
			}
			contador++;
		}



	}
	public void cargarSuper(Campo cSuper){
		int miNivel= cSuper.getNivel();
		int posi =this.listaDeCampos.indexOf(cSuper)+1;
		Campo cDepend=this.listaDeCampos.get(posi);
		int nivelUltimo =cDepend.getNivel();
		
		while (posi < this.listaDeCampos.size() && miNivel < this.listaDeCampos.get(posi).getNivel() ){
			
			cDepend =this.listaDeCampos.get(posi);
			if(cDepend.getNivel() <= nivelUltimo){
				cSuper.agregarDependencia(cDepend);
				nivelUltimo =this.listaDeCampos.get(posi).getNivel();
			}
			posi++;
			
			
		}
		
		BuscarSuper(cSuper);
	}
	public void BuscarSuper(Campo cSuper){
		for (Campo campo : cSuper.getListaDeDependencias()) {
			if (campo.getEsSupernivel()){
				cargarSuper(campo);
				
			}
		}
		
	}
	public void CargarTrama(String tramita) throws Exception{
		Campo campoPadre =this.listaDeCampos.get(0);
		int comienza=0;
		String tramaAux;
		this.trama = tramita;
		if (tramita.length()==campoPadre.getLongitud()){
		for (Campo campoARellenar : campoPadre.getListaDeDependencias()){
			if (campoARellenar.getEsSupernivel()){
				
					comienza = cargarTramaDepend(tramita,campoARellenar,comienza);				
			}else{	
			tramaAux =  this.trama.substring(comienza,comienza+campoARellenar.getLongitud());		
	
				if (campoARellenar.getEsNumerico()){	
					try {
						Long.parseLong(tramaAux);
					} catch (Exception e) {
						// TODO: handle exception
						Exception e1 = new Exception("Error de tipo, variable: " + campoARellenar.getNombre());
						throw e1;
						
					}
				}
							 				
			campoARellenar.setInformacion(tramaAux);
			comienza=comienza+campoARellenar.getLongitud();
			}
		}
		}else{
			System.out.println("La trama no coincide con el largo de la copy: ");
			System.out.println("Copy: " + campoPadre.getLongitud() + " Trama: " + tramita.length());
		}
		
		
	}
	public int cargarTramaDepend(String tram,Campo campito,int comienza) throws Exception{
		String tramaAux=tram;

		for (Campo c :campito.getListaDeDependencias()){
			if (c.getEsSupernivel()){
				comienza= cargarTramaDepend(tram, c,comienza);
			}else{
				tramaAux =  this.trama.substring(comienza,comienza+c.getLongitud());
				
				if (c.getEsNumerico()){	
					try {
						Long.parseLong(tramaAux);
					} catch (Exception e) {
						// TODO: handle exception
						Exception e1 = new Exception("Error de tipo, variable: " + c.getNombre());
						throw e1;
						
					}
				}
				
				c.setInformacion(tramaAux);
				comienza=comienza+c.getLongitud();
			}
		}
		
		return comienza;
		
	}
	public void calcularLongitud(Campo padre){
		if (padre.getEsOccurs()){
			for (Campo hijo : padre.getOccursDeCampos()){
				recursividadjaviana(hijo);
				padre.agregarLongitud(hijo.getLongitud());
			
			}
		}	
		if (padre.getEsSupernivel()){
			for (Campo hijo : padre.getListaDeDependencias()){
				recursividadjaviana(hijo);
				padre.agregarLongitud(hijo.getLongitud());
			}
		}
        
	}
	public String convertirCoordi(String tramaCoor) throws Exception{
		char[] array=tramaCoor.toCharArray();
		int j;
		int inicio=0;
		char caracterSeparacion;
		String res="";
		String aux;
		String[] camposCoor;
		for (int i=0;i<tramaCoor.length();i++){
			j=i+1;
			if (array[i]=='D' && array[j]=='H'){
			inicio = j+1; 	
			}
		}
		if (inicio==0){
			throw new Exception ("Error de trama, no existe DH dentro de la trama del coordinador");		
			
		}else {
			String tramaCoorAux;
			inicio=inicio+5;
			tramaCoorAux = tramaCoor.substring(inicio);
			caracterSeparacion = tramaCoor.charAt(tramaCoor.length() - 2);
			aux= ""+ caracterSeparacion;
			camposCoor = tramaCoorAux.split(aux);
			for (int i=0;i<camposCoor.length - 1;i++){
				res=res+camposCoor[i];	
			}
			
		 return res;
			
		}
		
	}
	public String[] parsearCoordi(String tramaCoor) throws Exception{
		char[] array=tramaCoor.toCharArray();
		int j;
		int inicio=0;
		char caracterSeparacion;
		String aux;
		String[] camposCoor;
		for (int i=0;i<tramaCoor.length();i++){
			j=i+1;
			if (array[i]=='D' && array[j]=='H'){
			inicio = j+1; 	
			}
		}
		if (inicio==0){
			throw new Exception ("Error de trama, no existe DH dentro de la trama del coordinador");		
			
		}else {
			String tramaCoorAux;
			inicio=inicio+5;
			tramaCoorAux = tramaCoor.substring(inicio);
			caracterSeparacion = tramaCoor.charAt(tramaCoor.length() - 2);
			aux= ""+ caracterSeparacion;
			camposCoor = tramaCoorAux.split(aux);
		
		    return camposCoor;
			
		}
		
	}
	public void CargarTramaSinLimite(String[] valores) throws Exception{
		Campo campoPadre =this.listaDeCampos.get(0);
		int comienza=0;
		for (Campo campoARellenar : campoPadre.getListaDeDependencias()){
			if (campoARellenar.getEsSupernivel()){
				
					comienza = cargarTramaDependSinLimite(valores,campoARellenar,comienza);				
			}else{			
	         
				if (campoARellenar.getEsNumerico()){	
					try {
						Long.parseLong(valores[comienza]);
					} catch (Exception e) {
						// TODO: handle exception
						Exception e1 = new Exception("Error de tipo, variable: " + campoARellenar.getNombre());
						throw e1;
						
					}
				}
							 				
			campoARellenar.setInformacion(valores[comienza]);
			comienza++;
			}
		}
		
		
		
	}
	public int cargarTramaDependSinLimite(String[] valores,Campo campito,int comienza) throws Exception{

		for (Campo c :campito.getListaDeDependencias()){
			if (c.getEsSupernivel()){
				comienza= cargarTramaDependSinLimite(valores, c,comienza);
			}else{
				
				if (c.getEsNumerico()){	
					try {
						Long.parseLong(valores[comienza]);
					} catch (Exception e) {
						// TODO: handle exception
						Exception e1 = new Exception("Error de tipo, variable: " + c.getNombre());
						throw e1;
						
					}
				}
				
				c.setInformacion(valores[comienza]);
				comienza++;
			}
		}
		
		return comienza;
		
	}
	
	public void recursividadjaviana(Campo hijo){
		
		if (hijo.getEsOccurs() || hijo.getEsSupernivel()){
		calcularLongitud(hijo);
		}
	}
	public void aniadirOccurse (Campo c1 , Campo c2){
		c1.agregarCampoDeOccurs(c2);

	}



	public Linea getLineaLeida() {
		return lineaLeida;
	}


	public void setLineaLeida(Linea lineaLeida) {
		this.lineaLeida = lineaLeida;
	}


	public String getLinea() {
		return lineaPura;
	}


	public void setLinea(String linea) {
		this.lineaPura = linea;
	}


	public String getRutaDelArchivo() {
		return rutaDelArchivo;
	}


	public void setRutaDelArchivo(String rutaDelArchivo) {
		this.rutaDelArchivo = rutaDelArchivo;
	}
}
