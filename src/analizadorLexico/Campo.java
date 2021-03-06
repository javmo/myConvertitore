package analizadorLexico;

public class Campo {
	
	String nombre;
	int nivel;
	int longitud;
	String tipoDeDato;
	String informacion;
//---------Booleanos seteados en el segundo barrido------------//	
	Boolean esFiller;
//---------Booleanos seteados en el tercer barrido------------//	
	Boolean esSupernivel;
	Boolean esOccurs;
	Boolean esRedefine;
	Boolean esVarClasica;
//-----------------------------------------------------------//	
	public void inicializarCampo(){
		this.setEsFiller(false);
		this.setEsOccurs(false);
		this.setEsRedefine(false);
		this.setEsSupernivel(false);
		this.setEsVarClasica(false);
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
		
	}
	
	public int getNivel() {
		return nivel;
	}


	public void setNivel(int nivel) {
		this.nivel = nivel;
	}


	public int getLongitud() {
		return longitud;
	}


	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}


	public String getTipoDeDato() {
		return tipoDeDato;
	}

// analizar tipo de dato
	
	public void evaluarTipoDato (String charDato){
		if (charDato == "X"){
			this.setTipoDeDato("Caracter");
		}
		this.setTipoDeDato("Numerico");
	}
	
	
	
	public void setTipoDeDato(String tipoDeDato) {
		this.tipoDeDato = tipoDeDato;
	}


	public String getInformacion() {
		return informacion;
	}


	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}


	public String getNombre() {
		return nombre;
	}

	public Boolean getEsSupernivel() {
		return esSupernivel;
	}

	public void setEsSupernivel(Boolean esSupernivel) {
		this.esSupernivel = esSupernivel;
	}

	public Boolean getEsRedefine() {
		return esRedefine;
	}

	public void setEsRedefine(Boolean esRedefine) {
		this.esRedefine = esRedefine;
	}

	public Boolean getEsOccurs() {
		return esOccurs;
	}

	public void setEsOccurs(Boolean esOccurs) {
		this.esOccurs = esOccurs;
	}

	public Boolean getEsVarClasica() {
		return esVarClasica;
	}

	public void setEsVarClasica(Boolean esVarClasica) {
		this.esVarClasica = esVarClasica;
	}

	public Boolean getEsFiller() {
		return esFiller;
	}

	public void setEsFiller(Boolean esFiller) {
		this.esFiller = esFiller;
	}
	

}
