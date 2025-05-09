package escenario;

public abstract class EscenarioTecladoDireccion {
	
	private final String direccion;

	
	public EscenarioTecladoDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void pressed(EscenarioTeclado teclado) {
		if (!teclado.containsKey(direccion)) teclado.pressDirection(this);

	}

	public void released(EscenarioTeclado teclado) {
		teclado.releaseDirection(this);

	}
	
	public String tick(EscenarioTeclado teclado) {

		return direccion;
	}
}
