package Package;

public class BloqueStateVacio implements BloqueState {
	
	private final int codigoBloque = 12;		// El codigo del bloque vacio es el codigo 12
	
	public int getCodigoBloque() { return codigoBloque;	}
	
	public boolean romperBloque(Bloque pBloque)
	{
		pBloque.cambiarTipo(new BloqueStateFuego());
		return true;
	}
	
	public boolean getJugadorChocaContraCelda() { return false; }
	
	public boolean getPuedeSerExplotado() { return true; }
	
	
	
}
