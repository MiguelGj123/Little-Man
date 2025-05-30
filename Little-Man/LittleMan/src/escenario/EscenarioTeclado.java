package escenario;
import java.util.ArrayList;

//Clase Singleton que gestiona la entrada del teclado del jugador.
//Controla las direcciones activas y si se ha presionado la bomba.
public class EscenarioTeclado {
	
	private static EscenarioTeclado miEscenarioTeclado;
	private ArrayList<EscenarioTecladoDireccion> direcciones;
	private EscenarioTecladoDireccionFactory dirFactory;
	private int tick;
	
	private boolean bomb;
	
	private EscenarioTeclado() {
		direcciones = new ArrayList<EscenarioTecladoDireccion>();
		dirFactory = EscenarioTecladoDireccionFactory.getDirectionFactory();
	}
	
	public static EscenarioTeclado getEscenarioTeclado() {
		if (miEscenarioTeclado == null) {
			miEscenarioTeclado = new EscenarioTeclado();
		}
		return miEscenarioTeclado;
	}
	
	public boolean containsKey(String direccionCheck) {
		return direcciones.stream()
			    .anyMatch(d -> d.getDireccion().equals(direccionCheck));
	}
	
	public void pressDirection(EscenarioTecladoDireccion direccion) {
		direcciones.addFirst(direccion);
	}
	
	public void releaseDirection(EscenarioTecladoDireccion direccion) {
		direcciones.remove(direccion);
	}
	
	public void pressedLeft()   { dirFactory.generate("L").pressed (this); }
	public void pressedRight()  { dirFactory.generate("R").pressed (this); }
	public void pressedUp()     { dirFactory.generate("U").pressed (this); }
	public void pressedDown()   { dirFactory.generate("D").pressed (this); }

	
	public void releasedLeft()  { dirFactory.generate("L").released(this); }
	public void releasedRight() { dirFactory.generate("R").released(this); }
	public void releasedUp()    { dirFactory.generate("U").released(this); }
	public void releasedDown()  { dirFactory.generate("D").released(this); }
	
	// Devuelve las direcciones activas cada 4 ticks, actualizando su estado. Si no toca enviar input, devuelve null.
	public String[] tick() {
		String[] direccionReturn = null;
		if (tick > 0) tick--;
		
		if (tick == 0 && !direcciones.isEmpty()) {
			direccionReturn = new String[direcciones.size()];
			tick = 4;
			
			for (int i = 0; i < direcciones.size(); i++) {
				direccionReturn[i] = direcciones.get(i).getDireccion();
				direcciones.get(i).tick(miEscenarioTeclado);
			}
		}
		
		return direccionReturn;
	}

	public void pressedBomb() { bomb = true; }
	public void releasedBomb() { bomb = false;}
	public boolean isBomb() { return bomb; }
	
	
	
	
}
