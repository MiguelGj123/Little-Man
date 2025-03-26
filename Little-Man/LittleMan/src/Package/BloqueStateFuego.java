package Package;

public class BloqueStateFuego implements BloqueState {
	
	private final int codigoBloque = 13;		// El codigo del bloque fuego es el codigo 13
	
	public int getCodigoBloque() { return codigoBloque;	}
	
	public boolean romperBloque(Bloque pBloque)
	{
		pBloque.cambiarTipo(new BloqueStateFuego());
		return true;
	}
	
	public boolean getJugadorChocaContraCelda() { return false; }
	
	public boolean getPuedeSerExplotado() { return true; }
	
	
	
}
