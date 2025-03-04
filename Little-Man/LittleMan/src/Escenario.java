import java.util.*;

public class Escenario extends Observable{
	private static final int FILAS =13;
	private static final int COLUMNAS =17;
	private static Escenario miEscenario;
	private Entidad[][] tablero;
	
	private Escenario() {
		tablero =new Entidad[FILAS][COLUMNAS];
		inicializarTablero();
	}
	
	public static Escenario getEscenario() {
		if (miEscenario == null) {
			miEscenario = new Escenario();
		}
		return miEscenario;
	}
	
	private void inicializarTablero() {
		//TODO Lógica de inicialización
	}
	
	public void actualizarEscenario() {
		//TODO Lógica de actualización
		setChanged();
		notifyObservers();
	}
	
	public Entidad getEntidad(int fila, int colu) {
		Entidad miEntidad = new Entidad();
		if(fila>=0 && fila<FILAS && colu>=0 && colu < COLUMNAS) {
			miEntidad= tablero[fila][colu];
		}
		return miEntidad;
	}
	
	public void setEntidad(int fila, int colu, Entidad miEntidad) {
		if(fila>=0 && fila<FILAS && colu>=0 && colu < COLUMNAS) {
			tablero[fila][colu]=miEntidad;
			setChanged();
			notifyObservers();
		}
		
		
		
	}
}
