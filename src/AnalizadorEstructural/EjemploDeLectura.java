package AnalizadorEstructural;



public class EjemploDeLectura {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Estructura lee = new	Estructura();
		 try{
			 
		 lee.generarListaDeCampos();
		 lee.BuscarPadre();
		 
		 
		 }catch(Exception e){
			 System.out.println("Error de lectura");;
			 
		 }		    	  
		
			
	}

}
