package AnalizadorEstructural;

import analizadorLexico.Linea;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;

public class Lector {
	 Linea lineaLeida = new Linea();
	 String linea= new String();
	 List<analizadorLexico.Campo> campos= new ArrayList<analizadorLexico.Campo>();	 
	 
     public void Leer () {
	 try(BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {	
		    
		    
		    linea = br.readLine();
		    
		    while (linea != null){
		    	lineaLeida.setUpLinea();
		    	lineaLeida.setLineaAnalizar(linea);
		    	lineaLeida.obtenerTokens();
				campos.add(lineaLeida.generarCampo());
		    	linea = br.readLine();
		    }
	 }catch  (Exception e)  {	
	throw new Exception();
		
	}
		    
		    
		  
	
		}
	 
	 

}
