package frameMenuPrincipal;

public class MenuPrincipalPersonajeAzul implements MenuPrincipalPersonajeState {

	private final String tipo = "AZUL";

	@Override
	public void rotarDerecha(MenuPrincipalPersonaje personaje) {
		personaje.changeState(new MenuPrincipalPersonajeRojo());
		
	}

	@Override
	public void rotarIzquierda(MenuPrincipalPersonaje personaje) {
		personaje.changeState(new MenuPrincipalPersonajeNegro());
	}
	
	public String getTipo() 
	{
		
		return tipo;
	}

}
