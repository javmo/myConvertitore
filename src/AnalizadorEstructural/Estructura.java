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
