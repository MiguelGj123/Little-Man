package Package;

public class BloqueStateBlando implements BloqueState {

	private final int codigoBloque = 11;		// El codigo del bloque blando es el codigo 11
	
	public int getCodigoBloque() { return codigoBloque;	}
	
	public boolean romperBloque(Bloque pBloque)
	{
		pBloque.cambiarTipo(new BloqueStateFuego());
		return true;
	}
	
	public boolean getJugadorChocaContraCelda() { return true; }
	
	public boolean getPuedeSerExplotado() { return true; }
	
	
	
}
