package AnalizadorEstructural;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import analizadorLexico.Campo;
import analizadorLexico.Linea;

public class Estructura {

	Linea lineaLeida;
	String linea=new String();
	List<Campo> listaDeCampos;
	String rutaDelArchivo;
// constructor de Clase Estructura	
	public Estructura(){
		this.lineaLeida = new Linea();
		this.linea= new String();
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
			    char primerCaracter;
			    int a;
			    primerCaracter= (char) br.read();
			    StringBuilder primerLinea = new StringBuilder();		  
			    
			    do {
			    a=	br.read();	
			    primerCaracter=(char) a;
			    } while (a==32);
			    
			    
			    linea = br.readLine();
			    if (linea != null){
			    primerLinea.append(primerCaracter);
			    primerLinea.append(linea);
			    lineaLeida.setUpLinea();
		    	lineaLeida.setLineaAnalizar(linea);
		    	lineaLeida.obtenerTokens();
		    	linea = br.readLine();
			    		    
			    while (linea != null){
			    	lineaLeida.setUpLinea();
			    	lineaLeida.setLineaAnalizar(linea);
			    	lineaLeida.obtenerTokens();
			    	listaDeCampos.add(lineaLeida.generarCampo());
			    	linea = br.readLine();
			    }
			    }	   
			     
			    
			    
		 }catch  (Exception e)  {
			 
	    throw new Exception();
			
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
		
	    while (this.listaDeCampos.get(indice).getNivel()< nivel && indice <= this.listaDeCampos.size()){
	    	añadirOccurse(campoOccurse,this.listaDeCampos.get(indice));
			indice++;
		}
			
		
	}
	public void ClonarHijitos(int indicePadre){
		int tamaño =this.listaDeCampos.get(indicePadre).getOccursDeCampos().size();
		int contador=0;
		while (contador < this.listaDeCampos.get(indicePadre).getOccursDeCampos().get(indicePadre).getCantidadDeOccurs()){
		for (int J=0;J<tamaño;J++) {
			this.listaDeCampos.get(indicePadre).getOccursDeCampos().add(this.listaDeCampos.get(indicePadre).getOccursDeCampos().get(J));
		}
		contador++;
		}
		
		
		
	}
	public void añadirOccurse (Campo c1 , Campo c2){
		c1.agregarCampoDeOccurs(c2);
		
	}
	


	public Linea getLineaLeida() {
		return lineaLeida;
	}


	public void setLineaLeida(Linea lineaLeida) {
		this.lineaLeida = lineaLeida;
	}


	public String getLinea() {
		return linea;
	}


	public void setLinea(String linea) {
		this.linea = linea;
	}


	public String getRutaDelArchivo() {
		return rutaDelArchivo;
	}


	public void setRutaDelArchivo(String rutaDelArchivo) {
		this.rutaDelArchivo = rutaDelArchivo;
	}
}
