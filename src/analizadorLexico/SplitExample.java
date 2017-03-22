package analizadorLexico;

public class SplitExample {

	 public static void main(String[] args) throws Exception {
		 
	//	 int j = 0;
		 	Linea lineaLeida = new Linea();
		 	lineaLeida.setUpLinea();
	    	lineaLeida.setLineaAnalizar("03 WVDT0030-NRO-MENSAJE               PIC X(04) VALUE '0030'.");
	    	lineaLeida.obtenerTokens();
	    	Campo nuevoCampo = lineaLeida.generarCampo();
		 
	//	 while(j<lineaLeida.arrayTokens.length){
	//		 System.out.print("Token numero");
	//		 System.out.print(j);
	//		 System.out.print(": ");
	//		 System.out.println(lineaLeida.arrayTokens[j]);
	//		 j++;
	//		 }
		 
		 
		 
	 }
	
	
	
	
}
