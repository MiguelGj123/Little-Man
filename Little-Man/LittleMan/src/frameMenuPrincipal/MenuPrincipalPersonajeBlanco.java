package frameMenuPrincipal;

public class MenuPrincipalPersonajeBlanco implements MenuPrincipalPersonajeState{

	private final String tipo = "BLANCO";

	@Override
	public void rotarDerecha(MenuPrincipalPersonaje personaje) {
		// TODO Auto-generated method stub
		personaje.changeState(new MenuPrincipalPersonajeNegro());
	}

	@Override
	public void rotarIzquierda(MenuPrincipalPersonaje personaje) {
		// TODO Auto-generated method stub
		
	}
	
	public String getTipo() 
	{
		
		return tipo;
	}
	
}
