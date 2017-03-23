package PruebaGrafica;



import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import AnalizadorEstructural.Estructura;
import analizadorLexico.Campo;

public class ArbolEstructural extends JTree {

	List<Campo> listaCampos;
	Estructura estructura;
	List<DefaultMutableTreeNode > listaDeNodos;
	JTree arbol;
	
//-----------------------------------------------------------------//	
//                    metodos	
//-----------------------------------------------------------------//	
	
	
	public ArbolEstructural(){
		estructura = new Estructura();
	}
	
	
	
	
	public void definirRoot(){
/**Definimos cual ser� el directorio principal o la raiz de nuestro arbol*/
		 DefaultMutableTreeNode campoRoot = new DefaultMutableTreeNode(estructura);
/**Definimos el modelo donde se agregaran los nodos*/
		  DefaultTreeModel modelo = new DefaultTreeModel(campoRoot);
		  arbol = new JTree(modelo);
		  
		 
	}
	
	public void cargarListaDeNodos(){
		
		int index = 0;
		Campo campo = estructura.getListaDeCampos().get(index);
		DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(campo.getNombre());
		listaDeNodos.add(nodo);
		
		
	}
	
	
	public void insertarCampo(Campo nodoAInsertar, Campo nodoAlQuePertenece){
		DefaultMutableTreeNode nodoAInsertar2 = new DefaultMutableTreeNode(nodoAInsertar.getNombre());
		
	
		
		
	}




	public List<Campo> getListaCampos() {
		return listaCampos;
	}




	public void setListaCampos(List<Campo> listaCampos) {
		this.listaCampos = listaCampos;
	}




	public Estructura getEstructura() {
		return estructura;
	}




	public void setEstructura(Estructura estructura) {
		this.estructura = estructura;
	}




	public List<DefaultMutableTreeNode> getListaDeNodos() {
		return listaDeNodos;
	}




	public void setListaDeNodos(List<DefaultMutableTreeNode> listaDeNodos) {
		this.listaDeNodos = listaDeNodos;
	}




	public JTree getArbol() {
		return arbol;
	}




	public void setArbol(JTree arbol) {
		this.arbol = arbol;
	}
	
}
