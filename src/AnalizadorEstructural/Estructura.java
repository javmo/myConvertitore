package AnalizadorEstructural;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import analizadorLexico.Campo;
import analizadorLexico.Linea;

public class Estructura {

	Linea lineaLeida;
	String lineaPura=new String();
	List<Campo> listaDeCampos;
	String rutaDelArchivo;
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

			char primerCaracter;
			int a;
			Campo nuevoCampo ;
			primerCaracter= (char) br.read();

			//TODO: investigar funcion trim() en strings
			while (lineaPura!=null){

				lineaPura = br.readLine();
				lineaPura=lineaPura.trim();
				if (lineaPura != null){

					lineaLeida.setUpLinea();// esto es clave
					lineaLeida.setLineaAnalizar(lineaPura);
					lineaLeida.obtenerTokens();
					nuevoCampo=lineaLeida.generarCampo();
					if (nuevoCampo.getNivel() != 88){
						listaDeCampos.add(nuevoCampo);
					}

				}
			}	   
			br.close();


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

		while (this.listaDeCampos.get(indice).getNivel()> nivel && indice < this.listaDeCampos.size()){
			aniadirOccurse(campoOccurse,this.listaDeCampos.get(indice));
			indice++;
		}


	}
	public void ClonarHijitos(int indicePadre){
		Campo padre = this.listaDeCampos.get(indicePadre);
		int tamanio = padre.getOccursDeCampos().size();
		this.listaDeCampos.get(indicePadre);
		int contador = 0;
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
