package escenario;

public abstract class EscenarioTecladoDireccion {
	
	private final String direccion;
//	private boolean presionado;
	
	public EscenarioTecladoDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void pressed(EscenarioTeclado teclado) {
		if (!teclado.containsKey(direccion)) teclado.pressDirection(this);
//		presionado = true;
	}

	public void released(EscenarioTeclado teclado) {
		teclado.releaseDirection(this);
//		presionado = false;
	}
	
	public String tick(EscenarioTeclado teclado) {
//		if (!presionado) teclado.releaseDirection(this);
		return direccion;
	}
}
