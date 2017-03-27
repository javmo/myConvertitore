package AnalizadorEstructural;



public class EjemploDeLectura {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Estructura lee = new	Estructura();
		 try{
		 lee.setRutaDelArchivo("D:\\Users\\jmorixe\\Documents\\Trabajos\\ASOL\\ID6548-Ciclo de vida\\copyprueba.txt");
		 lee.generarListaDeCampos();
 		 lee.BuscarPadre();
		 lee.cargarSuper(lee.getListaDeCampos().get(0));
		 
		 }catch(Exception e){
			 e.printStackTrace();
			 System.out.println("Error de lectura");
			 
		 }		    	  
		
			
	}

}
