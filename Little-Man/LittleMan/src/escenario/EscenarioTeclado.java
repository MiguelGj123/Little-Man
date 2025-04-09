package escenario;
import java.util.ArrayList;

public class EscenarioTeclado {
	
	private static EscenarioTeclado miEscenarioTeclado;
	private ArrayList<EscenarioTecladoDireccion> direcciones;
	private EscenarioTecladoDireccionFactory dirFactory;
	private int tick;
	
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
		for (EscenarioTecladoDireccion direccion : direcciones) if (direccion.getDireccion().equals(direccionCheck)) return true;
		return false;
	}
	
	public void pressDirection(EscenarioTecladoDireccion direccion) {
		direcciones.addFirst(direccion);
	}
	
	public void releaseDirection(EscenarioTecladoDireccion direccion) {
		direcciones.remove(direccion);
	}
	
	public void pressedLeft()   { dirFactory.generate("L").pressed (this); }//System.out.println("LLLLLLLLLLLLLLLLLL AAAAAAAAAAAAAAAAAAAAAAAAAAAA");}//
	public void pressedRight()  { dirFactory.generate("R").pressed (this); }//System.out.println("RRRRRRRRRRRRRRRRRR AAAAAAAAAAAAAAAAAAAAAAAAAAAA");}
	public void pressedUp()     { dirFactory.generate("U").pressed (this); }//System.out.println("UUUUUUUUUUUUUUUUUU AAAAAAAAAAAAAAAAAAAAAAAAAAAA");}
	public void pressedDown()   { dirFactory.generate("D").pressed (this); }//System.out.println("DDDDDDDDDDDDDDDDDD AAAAAAAAAAAAAAAAAAAAAAAAAAAA");}

	
	public void releasedLeft()  { dirFactory.generate("L").released(this); }//System.out.println("LLLLLLLLLLLLLLLLLL BBBBBBBBBBBBBBBBBBBBBBBBBBBB");}
	public void releasedRight() { dirFactory.generate("R").released(this); }//System.out.println("RRRRRRRRRRRRRRRRRR BBBBBBBBBBBBBBBBBBBBBBBBBBBB");}
	public void releasedUp()    { dirFactory.generate("U").released(this); }//System.out.println("UUUUUUUUUUUUUUUUUU BBBBBBBBBBBBBBBBBBBBBBBBBBBB");}
	public void releasedDown()  { dirFactory.generate("D").released(this); }//System.out.println("DDDDDDDDDDDDDDDDDD BBBBBBBBBBBBBBBBBBBBBBBBBBBB");}
	
	public String[] tick() {
		String[] direccionReturn = null;
		if (tick > 0) tick--;
		
		if (tick == 0 && !direcciones.isEmpty()) {
			direccionReturn = new String[direcciones.size()];
			tick = 2;
			
			for (int i = 0; i < direcciones.size(); i++) {
				direccionReturn[i] = direcciones.get(i).getDireccion();
				direcciones.get(i).tick(miEscenarioTeclado);
			}
		}
		
		return direccionReturn;
	}
	
	
	
	
}
