package AnalizadorEstructural;



public class EjemploDeLectura {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Estructura lee = new	Estructura();
		 try{
		 lee.setRutaDelArchivo("D:\\Users\\Serial\\Desktop\\CTest.txt");
		 lee.generarListaDeCampos();
 		 lee.BuscarPadre();
		 lee.cargarSuper(lee.getListaDeCampos().get(0));
		 lee.calcularLongitud(lee.getListaDeCampos().get(0));
		 lee.CargarTramaSinLimite(lee.parsearCoordi("[ahsfDH12345SASA�1234567895678�]"));
		 lee.CargarTrama(lee.convertirCoordi("[asjfhasfjashdfjashdklashdjkashfsdjfahsfDH12345S�1234567895�]"));
		 }catch(Exception e){
			 e.printStackTrace();
			 System.out.println(e.getMessage());
			 
		 }		    	  
			
	}

}
