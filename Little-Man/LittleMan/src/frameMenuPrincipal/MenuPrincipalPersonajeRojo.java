package frameMenuPrincipal;

public class MenuPrincipalPersonajeRojo implements MenuPrincipalPersonajeState {

	private final String tipo = "ROJO";

	@Override
	public void rotarDerecha(MenuPrincipalPersonaje personaje) {
		personaje.changeState(new MenuPrincipalPersonajeBlanco());
	}

	@Override
	public void rotarIzquierda(MenuPrincipalPersonaje personaje) {
		personaje.changeState(new MenuPrincipalPersonajeAzul());
	}
	
	public String getTipo() 
	{
		
		return tipo;
	}

}
