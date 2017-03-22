package AnalizadorEstructural;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import analizadorLexico.Linea;


public class ListaCampos {
	Linea lineaLeida = new Linea();
	 String linea=new String();
	 List<analizadorLexico.Campo> campos= new ArrayList<analizadorLexico.Campo>();
	 
public List<analizadorLexico.Campo> ListarCampos() throws Exception{
	 try(BufferedReader br = new BufferedReader(new FileReader("D:\\Users\\Serial\\Desktop\\CTest.txt"))) {	
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
				campos.add(lineaLeida.generarCampo());
		    	linea = br.readLine();
		    }
		    }	   
		     
		    
		    
	 }catch  (Exception e)  {
		 
    throw new Exception();
		
	}	
	 return campos;
	 
}
}
