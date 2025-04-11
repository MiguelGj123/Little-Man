package frameMenuPrincipal;

public class MenuPrincipalPersonaje {

	public static MenuPrincipalPersonaje myMenuPrincipalPersonaje;
	private MenuPrincipalPersonajeState personajeTipo;
	
	private MenuPrincipalPersonaje() {
		personajeTipo = new MenuPrincipalPersonajeBlanco();
	}
	
	public static MenuPrincipalPersonaje getMyMenuPrincipalPersonaje() {
		if (myMenuPrincipalPersonaje == null) 
		{
			myMenuPrincipalPersonaje = new MenuPrincipalPersonaje();
		}
		
		return myMenuPrincipalPersonaje;
	}
	
	public void changeState(MenuPrincipalPersonajeState personajeTipo) 
	{
		this.personajeTipo = personajeTipo;
	}
	
	public void rotarDerecha() 
	{
		
		personajeTipo.rotarDerecha(this);
	}
	
	public void rotarIzquierda() 
	{
		
		personajeTipo.rotarIzquierda(this);
	}
	
	public String getTipo() {
		return personajeTipo.getTipo();
	}
	
}
