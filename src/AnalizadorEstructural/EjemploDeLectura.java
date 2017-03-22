package AnalizadorEstructural;



public class EjemploDeLectura {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Estructura lee = new	Estructura();
		 try{
		 lee.setRutaDelArchivo("D:\\Users\\jmorixe\\Documents\\Trabajos\\ASOL\\ID6548-Ciclo de vida\\copy prueba.txt");
		 lee.generarListaDeCampos();
		 lee.BuscarPadre();
		 
		 
		 }catch(Exception e){
			 System.out.println("Error de lectura");;
			 
		 }		    	  
		
			
	}

}
