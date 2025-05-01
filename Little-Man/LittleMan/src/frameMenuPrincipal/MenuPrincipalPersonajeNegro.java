package frameMenuPrincipal;

public class MenuPrincipalPersonajeNegro implements MenuPrincipalPersonajeState {
	
	private final String tipo = "NEGRO";

	@Override
	public void rotarDerecha(MenuPrincipalPersonaje personaje) {
		personaje.changeState(new MenuPrincipalPersonajeAzul());
	}

	@Override
	public void rotarIzquierda(MenuPrincipalPersonaje personaje) {
		personaje.changeState(new MenuPrincipalPersonajeBlanco());
	}
	
	public String getTipo() 
	{
		
		return tipo;
	}
}
