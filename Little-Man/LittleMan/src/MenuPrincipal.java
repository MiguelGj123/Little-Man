import java.util.Observable;

public class MenuPrincipal extends Observable {
	private static MenuPrincipal miMenuPrincipal;
	private String personajeSeleccionado;
	private int nivelSeleccionado;
	private boolean listo;
	private MenuPrincipalPersonaje personaje;
	
	private MenuPrincipal() 
	{
		personaje = MenuPrincipalPersonaje.getMyMenuPrincipalPersonaje();
	}
	
	public static MenuPrincipal getMenuPrincipal() {
		if(miMenuPrincipal==null) {
			miMenuPrincipal=new MenuPrincipal();
		}
		return miMenuPrincipal;
	}
	
	//Metodo que establece el jugador dependiendo delo recibido por el controlador
	public void seleccionPersonaje(String pPersonaje) {
		this.personajeSeleccionado=pPersonaje;
		setChanged();
		notifyObservers(pPersonaje); //Notifica que el personaje ha cambiado
	}
	
	public void iniciarJuego() {
		listo=true;
//		Escenario.getEscenario().inicializarTablero(personaje.getTipo());
		setChanged();
		notifyObservers(personajeSeleccionado);
	}
	
	public boolean isReady() {
		return listo;
	}
	
	public void pressA() 
	{
		personaje.rotarIzquierda();
		personajeSeleccionado = personaje.getTipo();
		setChanged();
		notifyObservers(personajeSeleccionado);
	}
	public void pressD() 
	{
		personaje.rotarDerecha();
		personajeSeleccionado = personaje.getTipo();
		setChanged();
		notifyObservers(personajeSeleccionado);
	}
	
	public void seleccionarPersonaje(String playerTipo) {
		personaje.changeState(MenuPrincipalPersonajeFactory.getPersonajeFactory().generate(playerTipo));
	}
	
	
	
	public void pressEnter() {
		iniciarJuego();
	}
	
	
	
}
