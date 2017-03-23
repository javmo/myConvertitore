package AnalizadorEstructural;



public class EjemploDeLectura {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Estructura lee = new	Estructura();
		 try{
		 lee.setRutaDelArchivo("D:\\Users\\Serial\\Desktop\\CTest.txt");
		 lee.generarListaDeCampos();
		 lee.BuscarPadre();
		 
		 
		 }catch(Exception e){
			 e.printStackTrace();
			 System.out.println("Error de lectura");
			 
		 }		    	  
		
			
	}

}
