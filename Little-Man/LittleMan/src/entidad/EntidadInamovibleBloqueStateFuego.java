package entidad;

// Estado 'Fuego' del bloque. Indica celda en llamas temporalmente.
public class EntidadInamovibleBloqueStateFuego implements EntidadInamovibleBloqueState {
	
	private final int		codigoBloque	= 13;       // Código visual del estado fuego
	private final String	tipoBloque		= "FUEGO";

	public String getTipo() { return tipoBloque; }

	public int getCodigoBloque(String pantalla) { return codigoBloque; }

	public boolean romperBloque(EntidadInamovibleBloque pBloque) {
		pBloque.cambiarTipo(new EntidadInamovibleBloqueStateFuego(), tipoBloque);
		return true; // Aunque ya esté en fuego, se "reconfirma" ese estado
	}

	public boolean getJugadorChocaContraCelda() { return false; }

	public boolean getPuedeSerExplotado() { return true; }
}
