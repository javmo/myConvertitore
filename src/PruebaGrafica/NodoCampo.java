package PruebaGrafica;

import javax.swing.tree.DefaultMutableTreeNode;

public class NodoCampo extends DefaultMutableTreeNode {
	String nombre;
	String informacion;
	
	
	public NodoCampo (String nomb){
		super(nomb);
		nombre = nomb;
	}
	public NodoCampo (String nomb,Boolean valor){
		super(nomb,valor);
		nombre = nomb;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getInformacion() {
		return informacion;
	}


	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

}
