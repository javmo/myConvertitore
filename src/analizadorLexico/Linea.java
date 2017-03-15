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
			this.segundoBarrido();
			if(deboSeguir){
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
// primer barrido se analiza el nivel del campo debe ser un entero
	public void primerBarrido() {
	if (this.listaTokens.get(index) == "\\." || this.isNotNumeric()){
			
		
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
	public List<String> getListaTokens() {
		return listaTokens;
	}
	public void setListaTokens(List<String> listaTokens) {
		this.listaTokens = listaTokens;
	}


	
}
