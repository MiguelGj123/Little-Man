import java.util.Observable;

public class MenuPrincipal extends Observable {
	private static MenuPrincipal miMenuPrincipal;
	private String personajeSeleccionado;
	private int nivelSeleccionado;
	private boolean listo;
	
	private MenuPrincipal() {
		
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
		if(personajeSeleccionado!=null) {
			listo=true;
			setChanged();
			notifyObservers(personajeSeleccionado);
		}
	}
	
	public boolean isReady() {
		return listo;
	}
	
	
}
