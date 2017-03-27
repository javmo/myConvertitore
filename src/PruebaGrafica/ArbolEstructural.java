package PruebaGrafica;



import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import AnalizadorEstructural.Estructura;
import analizadorLexico.Campo;

public class ArbolEstructural extends JTree {

	List<Campo> listaCampos;
	Estructura estructura;
	List<NodoCampo> listaDeNodos = new ArrayList<NodoCampo>();
	JTree arbol;
	Campo campoRoot;
	DefaultTreeModel modelo;
	int index = 0;
//-----------------------------------------------------------------//	
//                    metodos	
//-----------------------------------------------------------------//	
	
	
	public ArbolEstructural(Estructura estructura1) {
		estructura = estructura1;
		listaCampos = estructura.getListaDeCampos();
		

	}

	public JTree generarArbol(Campo campo) {
		NodoCampo padre = new NodoCampo(campo.getNombre(), true);
		modelo = new DefaultTreeModel(padre);
		arbol = new JTree(modelo);
//		cargarSuper(this.listaCampos.get(0), padre);
		crearMesias(campo,padre);
		return arbol;

	}
//-----------------------------------------------------//
	public void crearMesias(Campo campo, NodoCampo padre) {

		for (Campo campitoDepne : campo.getListaDeDependencias()) {
			NodoCampo hijo = new NodoCampo(campitoDepne.getNombre());
			padre.add(hijo);
			this.recurrenciaSanta(campitoDepne, hijo);

		}

	}

	public void recurrenciaSanta(Campo aSantificar, NodoCampo hijoPosiblePadre) {
		if (aSantificar.getEsSupernivel()) {
			this.crearMesias(aSantificar, hijoPosiblePadre);
		}
		if (aSantificar.getEsOccurs()) {
			this.crearApostoles(aSantificar, hijoPosiblePadre);

		}
	}

	public void crearApostoles(Campo campo, NodoCampo padre) {
		for (Campo apostol : campo.getOccursDeCampos()) {
			NodoCampo apostolSanto = new NodoCampo(apostol.getNombre());
			padre.add(apostolSanto);
			this.recurrenciaSanta(apostol, apostolSanto);

		}

	}
	//-----------------------------------------------------//
	public void cargarSuper(Campo cSuper, NodoCampo padre) {
		int miNivel = cSuper.getNivel();
		int posi = this.listaCampos.indexOf(cSuper) + 1;
		Campo cDepend = this.listaCampos.get(posi);
		int nivelUltimo = cDepend.getNivel();

		while (posi < this.listaCampos.size() && miNivel < this.listaCampos.get(posi).getNivel()) {

			cDepend = this.listaCampos.get(posi);

			if (cDepend.getNivel() <= nivelUltimo) {

				NodoCampo hijo = new NodoCampo(cDepend.getNombre());
				this.listaDeNodos.add(hijo);
				padre.add(hijo);
				// cargarHijos(padre,hijo,index);
				cSuper.agregarDependencia(cDepend);
				nivelUltimo = this.listaCampos.get(posi).getNivel();
			}
			posi++;
			index++;

		}

		BuscarSuper(cSuper);
	}



	public void BuscarSuper(Campo cSuper) {
		for (Campo campo : cSuper.getListaDeDependencias()) {
			if (campo.getEsSupernivel()) {
				index = 0;
				NodoCampo padre = this.buscarNodo(campo.getNombre());
				cargarSuper(campo, padre);

			}
		}

	}

	private NodoCampo buscarNodo(String nombre) {
		NodoCampo nodoADevolver = null;
		for (NodoCampo n : listaDeNodos) {
			if (n.getNombre() == nombre) {
				nodoADevolver = n;
			}
		}
		return nodoADevolver;
	}

	public void cargarHijos(NodoCampo padre, NodoCampo hijo, int secuencia) {
		modelo.insertNodeInto(hijo, padre, secuencia);

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

	public JTree getArbol() {
		return arbol;
	}

	public void setArbol(JTree arbol) {
		this.arbol = arbol;
	}

}
