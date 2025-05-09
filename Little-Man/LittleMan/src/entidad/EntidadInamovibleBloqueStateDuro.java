package entidad;

// Estado 'Duro' de un bloque. No puede ser destruido ni afectado por explosiones.
public class EntidadInamovibleBloqueStateDuro implements EntidadInamovibleBloqueState {
	
	private final int		codigoBloque	= 10;       // Código visual del bloque duro
	private final String	tipoBloque		= "DURO";

	public String getTipo() { return tipoBloque; }

	public int getCodigoBloque(String pantalla) { return codigoBloque; }

	public boolean romperBloque(EntidadInamovibleBloque pBloque) {
		return false; // No se puede romper
	}

	public boolean getJugadorChocaContraCelda() { return true; }

	public boolean getPuedeSerExplotado() { return false; }
}
