package analizadorLexico;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
						this.quintoBarrido();
						if(deboSeguir){
							this.sextoBarrido();
						}
					}
				}
			}
		}
				
	}
// segundo barrido, analiza que el nombre del campo no sea numerico	
private void segundoBarrido() {
	if(this.isNotString()) {
		this.tratarErorr();
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
	private void tratarErorr() {
		this.setDeboSeguir(false);
		System.out.println("---------------------------");
		System.out.print("ERROR en el barrido Nº ");
		System.out.print(index);
		switch (index) {
			case 0:
				System.out.println("Se esperaba el nivel de la variable (Debe ser nuemrico)");
				break;
			case 1:	
				System.out.println("Se esperaba el que el nombre del campo sea un string(No debe ser numerico)");
				break;
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


	
}
