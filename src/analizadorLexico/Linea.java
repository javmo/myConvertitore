package analizadorLexico;

import java.util.List;

import java.util.Arrays;

public class Linea {
	
	String lineaAnalizar;
	String[] arrayTokens;
	List<String> listaTokens;
	Campo miCampo;
	Boolean deboSeguir;
	int index;
	
//-------------------------------------------------------------------//
//                   Meteodos
//-------------------------------------------------------------------//	

	public void obtenerTokens(){
		this.setArrayTokens((this.getLineaAnalizar()).split("\\s+"));
		this.setListaTokens(Arrays.asList(this.getArrayTokens()));
					
	}
// analiza los tokens y los transforma en un campo	
	public void generarCampo(){
		miCampo = new Campo();
		miCampo.inicializarCampo();
		
		this.primerBarrido();
		if(deboSeguir){
			++index;
			this.segundoBarrido();
			if(deboSeguir){
				++index;
				this.tercerBarrido();
				if(deboSeguir){
					++index;
					this.cuartoBarrido();
					if(deboSeguir){
						++index;
						this.quintoBarrido();
						if(deboSeguir){
							++index;
							this.sextoBarrido();
						}
					}
				}
			}
		}
				
	}
// se setea el value que vino en la sentencia de la copy	
public void sextoBarrido() {
	if(listaTokens.get(index).indexOf(".") != -1){
		this.setDeboSeguir(false);
	}
	 String[] inforamcionPura = this.listaTokens.get(index).split("\\'"); 
	
	this.miCampo.setInformacion(inforamcionPura[1]);
	
	
}
// Valida que el token anterior no tenga un punto porque sino seria fin de sentencia
public void quintoBarrido() {
	if(listaTokens.get(index).indexOf(".") != -1){
		this.tratarErorr();
	}
	
	String value = "VALUE";
	if((this.miCampo.esFiller) || (this.miCampo.esVarClasica)){ 
		
		if(!(listaTokens.get(index).equals(value))){
			this.tratarErorr();
		}		
	}else{
		this.tratarErorr();	
	}
	
}
public void cuartoBarrido() {
	if(listaTokens.get(index).indexOf(".") != -1){
		this.setDeboSeguir(false);
	}
	String[] numeroDeOccurs = (this.listaTokens.get(index).split("\\."));
//------------------------------	
	if(this.miCampo.esOccurs){
		if(this.isNotNumeric(numeroDeOccurs[0])){
			this.tratarErorr();
		}
		this.miCampo.setCantidadDeOccurs(Integer.parseInt(numeroDeOccurs[0]));
//------------------------------		
	}else if(this.miCampo.esRedefine){
		if(this.isNotString(numeroDeOccurs[0])){
			this.tratarErorr();
		}
		this.miCampo.setRedefineA(numeroDeOccurs[0]);
//------------------------------		
	}else if(this.miCampo.esSupernivel){
		if(this.listaTokens.get(index) != "\\."){
			this.tratarErorr();
		}
//------------------------------		
	}else if((this.miCampo.esFiller) || (this.miCampo.esVarClasica)){
		if ((listaTokens.get(index).indexOf("(")) == -1){
			this.obtengoLongSinParentesis();
		}
		else{
			this.obtengoLongConParentesis();
		}
			
	
	}else 
	{
		this.tratarErorr();	
	}
	
}
// obtengo longitud por medio del caso PIC X(02) o PIC 9(04)
public void obtengoLongConParentesis() {
	if((listaTokens.get(index)).indexOf("X") != -1 ){
		this.miCampo.setEsCaracter(true);
	}else if((listaTokens.get(index)).indexOf("9") != -1){
		this.miCampo.setEsNumerico(true);
		
		}
		else{
			this.tratarErorr();
		}

	String[] enteroConUnParentesis = this.listaTokens.get(index).split("\\(");
	String[] enteroPuro = enteroConUnParentesis[1].split("\\)");
	this.miCampo.setLongitud(Integer.parseInt(enteroPuro[0]));
	
}
// Se obtiene la longitud y el tipo de dato del campo por medio de la nomenclatura sin parentesis
// ejemplo PIC XX o PIC 9999
public void obtengoLongSinParentesis() {
	if((listaTokens.get(index)).indexOf("X") != -1 ){
		this.miCampo.setEsCaracter(true);;
		this.miCampo.setLongitud(listaTokens.get(index).length());
	}else if((listaTokens.get(index)).indexOf("9") != -1){
		this.miCampo.setEsNumerico(true);
		this.miCampo.setLongitud(listaTokens.get(index).length());
	}
	this.tratarErorr();
}
//Tercer barrido se contempla que sea una variable normal, una varaible con occurs
//, el valor si es un booleano, si hay una variable que redefine a otra o si es un super nivel
public void tercerBarrido() {
	if(listaTokens.get(index).indexOf(".") != -1){
		this.setDeboSeguir(false);
	}
	
	switch (this.listaTokens.get(index)) {
		case "PIC":
			if(!(this.miCampo.esFiller)){
				this.miCampo.setEsVarClasica(true);
			}
			break;
		case "OCCURS":
			this.miCampo.setEsOccurs(true);
			break;
		case "VALUE":
			if(this.miCampo.getNivel() != 88){
				tratarErorr();
			}
			break;
		case "REDEFINE":
			if(this.miCampo.getNivel() == 88){
				tratarErorr();
			}
			this.miCampo.setEsRedefine(true);
			break;
		case "\\.":
			this.miCampo.setEsSupernivel(true);
			break;
		default:
			tratarErorr();
			break;
	
	}
				
}
// segundo barrido, analiza que el nombre del campo no sea numerico	
public void segundoBarrido() {
	if(this.isNotString(listaTokens.get(index))) {
		this.tratarErorr();
	}
	if(listaTokens.get(index).indexOf(".") != -1){
		this.miCampo.setEsSupernivel(true);
	}
	String filler= "FILLER";
	if (listaTokens.get(index).equals(filler) ){
		this.miCampo.setEsFiller(true);
	}
	this.miCampo.setNombre(listaTokens.get(index));
}
// primer barrido se analiza el nivel del campo debe ser un entero
	public void primerBarrido() {
	if (this.isNotNumeric(listaTokens.get(index))){
		this.tratarErorr();
	}
	this.miCampo.setNivel(Integer.parseInt(listaTokens.get(index)));
		
	
}
// Trata el error dependiendo el barrido
	public void tratarErorr() {
		this.setDeboSeguir(false);
		System.out.println("---------------------------");
		System.out.print("ERROR en el barrido Numero ");
		System.out.print(index+" ");
		switch (index) {
			case 0:
				System.out.println("Se esperaba el nivel de la variable (Debe ser nuemrico)");
				break;
			case 1:	
				System.out.println("Se esperaba el que el nombre del campo sea un string(No debe ser numerico)");
				break;
			case 2:
				System.out.println("Se registro un token value y no es un nivel booleano o se registro un token redefine y es booleano");
			case 3:
				System.out.println("Se registro un tipo de dato invalido, el numero de occurs no es nuemrico, el campo a redefinir no esta informado correctamente, es super nivel y no se informa el fin de sentencia");	
			case 4:
				System.out.println("Se registro un token que no es VALUE o no se especifico el fin de sentencia con el '.' ");	
			
		}
	}
	
	public Campo getMiCampo() {
		return miCampo;
	}

	public void setMiCampo(Campo miCampo) {
		this.miCampo = miCampo;
	}

	public String getLineaAnalizar() {
		return lineaAnalizar;
	}

	public void setLineaAnalizar(String lineaAnalizar) {
		this.lineaAnalizar = lineaAnalizar;
	}

	public String[] getArrayTokens() {
		return arrayTokens;
	}

	public void setArrayTokens(String[] arrayTokens) {
		this.arrayTokens = arrayTokens;

	}

	public boolean isNotNumeric(String numeroEnString){
		try {
			Integer.parseInt(numeroEnString);
			return false;
		} catch (NumberFormatException nfe){
			return true;
		}
	}
	
	public boolean isNotString(String stringConNumero) {
        if (stringConNumero.equals(stringConNumero.toString())) {
            return false;
        } else {
            return true;
        }
 
    }
	
	public List<String> getListaTokens() {
		return listaTokens;
	}
	public void setListaTokens(List<String> listaTokens) {
		this.listaTokens = listaTokens;
	}
	public Boolean getDeboSeguir() {
		return deboSeguir;
	}
	public void setDeboSeguir(Boolean deboSeguir) {
		this.deboSeguir = deboSeguir;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void setUpLinea() {
		this.setDeboSeguir(true);
		
	}
}
