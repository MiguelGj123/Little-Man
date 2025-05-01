package frameMenuPrincipal;

public class MenuPrincipalPersonajeBlanco implements MenuPrincipalPersonajeState{

	private final String tipo = "BLANCO";

	@Override
	public void rotarDerecha(MenuPrincipalPersonaje personaje) {
		personaje.changeState(new MenuPrincipalPersonajeNegro());
	}

	@Override
	public void rotarIzquierda(MenuPrincipalPersonaje personaje) {
		personaje.changeState(new MenuPrincipalPersonajeRojo());
	}
	
	public String getTipo() 
	{
		
		return tipo;
	}
	
}
