package analizadorLexico;

public class SplitExample {

	 public static void main(String[] args) {
		 
		 int j = 0;
		 
		 Linea lineaLeida = new Linea();
		 lineaLeida.setUpLinea();
		 lineaLeida.setLineaAnalizar("02 WVDT0025-NRO-MENSAJE              PIC X(04) VALUE '0025'.");
		 lineaLeida.obtenerTokens();
		 lineaLeida.generarCampo();
		 
		 while(j<lineaLeida.arrayTokens.length){
			 System.out.print("Token nº");
			 System.out.print(j);
			 System.out.print(": ");
			 System.out.println(lineaLeida.arrayTokens[j]);
			 j++;
			 }
		 
		 
		 
	 }
	
	
	
	
}
