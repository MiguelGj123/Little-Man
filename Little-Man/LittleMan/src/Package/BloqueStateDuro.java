package Package;

public class BloqueStateDuro implements BloqueState {
	
	private final int codigoBloque = 10;		// El codigo del bloque duro es el codigo 10
	
	public int getCodigoBloque() { return codigoBloque;	}
	
	public boolean romperBloque(Bloque pBloque) {
		
		return false;
	}
	
	public boolean getJugadorChocaContraCelda() { return true; }
	
	public boolean getPuedeSerExplotado() { return false; }
	
	
}
