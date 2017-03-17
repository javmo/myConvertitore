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
					this.cuartoBarrido();
					if(deboSeguir){
//						this.quintoBarrido();
						if(deboSeguir){
//							this.sextoBarrido();
						}
					}
				}
			}
		}
				
	}
public void cuartoBarrido() {
//------------------------------	
	if(this.miCampo.esOccurs){
//------------------------------		
	}else if(this.miCampo.esRedefine){
//------------------------------		
	}else if(this.miCampo.esSupernivel){
//------------------------------		
	}else if(this.miCampo.esFiller){
//------------------------------		
	}else if(this.miCampo.esVarClasica){
//------------------------------		
	}
	this.tratarErorr();
}
//Tercer barrido se contempla que sea una variable normal, una varaible con occurs
//, el valor si es un booleano, si hay una variable que redefine a otra o si es un super nivel
public void tercerBarrido() {
	
	switch (this.listaTokens.get(index)) {
		case "PIC":
			this.miCampo.setEsVarClasica(true);
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
	if(this.isNotString()) {
		this.tratarErorr();
	}
	if (listaTokens.get(index) == "FILLER"){
		this.miCampo.setEsFiller(true);
	}
	this.miCampo.setNombre(listaTokens.get(index));
}
// primer barrido se analiza el nivel del campo debe ser un entero
	public void primerBarrido() {
	if (this.listaTokens.get(index) == "\\." || this.isNotNumeric()){
		this.tratarErorr();
	}
	this.miCampo.setNivel(Integer.parseInt(listaTokens.get(index)));
		
	
}
// Trata el error dependiendo el barrido
	public void tratarErorr() {
		this.setDeboSeguir(false);
		System.out.println("---------------------------");
		System.out.print("ERROR en el barrido N� ");
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

	public boolean isNotNumeric(){
		try {
			Integer.parseInt(listaTokens.get(index));
			return false;
		} catch (NumberFormatException nfe){
			return true;
		}
	}
	
	public boolean isNotString() {
        if (listaTokens.get(index).equals(listaTokens.get(index).toString())) {
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
