
public class EntidadInamovibleBloqueStateDuro implements EntidadInamovibleBloqueState {
	
	private final int		codigoBloque	= 10;			// El codigo del bloque duro es el codigo 10
	private final String	tipoBloque		= "DURO";		// El tipo   del bloque duro es "DURO"

	public String getTipo() { return tipoBloque; }

	public int getCodigoBloque() { return codigoBloque;	}
	
	public boolean romperBloque(EntidadInamovibleBloque pBloque) {
		
		return false;
	}
	
	public boolean getJugadorChocaContraCelda() { return true; }
	
	public boolean getPuedeSerExplotado() { return false; }
	
	
}
