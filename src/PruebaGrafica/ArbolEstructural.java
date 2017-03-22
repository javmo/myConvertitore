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
	JTree arbol;
	
//-----------------------------------------------------------------//	
//                    metodos	
//-----------------------------------------------------------------//	
	
	
	public ArbolEstructural(){
		estructura = new Object();
	}
	
	
	
	
	public void definirRoot(){
/**Definimos cual será el directorio principal o la raiz de nuestro arbol*/
		 DefaultMutableTreeNode campoRoot = new DefaultMutableTreeNode(estructura);
/**Definimos el modelo donde se agregaran los nodos*/
		  DefaultTreeModel modelo = new DefaultTreeModel(campoRoot);
		  arbol = new JTree(modelo);
		 
	}
	
	public void insertarCampo(Campo nodoAInsertar, Campo nodoAlQuePertenece){
		DefaultMutableTreeNode nodoAInsertar = new DefaultMutableTreeNode((nodoAInsertar.getNombre());
		
	
		
		
	}
	
}
