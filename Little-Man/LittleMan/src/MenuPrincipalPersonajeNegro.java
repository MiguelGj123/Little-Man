
public class MenuPrincipalPersonajeNegro implements MenuPrincipalPersonajeState {
	private final String tipo = "NEGRO";

	@Override
	public void rotarDerecha(MenuPrincipalPersonaje personaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotarIzquierda(MenuPrincipalPersonaje personaje) {
		// TODO Auto-generated method stub
		personaje.changeState(new MenuPrincipalPersonajeBlanco());
	}
	
	public String getTipo() 
	{
		
		return tipo;
	}
}
