package entidad;

// Estado 'Vacío' del bloque. Representa una celda libre donde no hay colisión ni contenido visual.
public class EntidadInamovibleBloqueStateVacio implements EntidadInamovibleBloqueState {
	
	private final int		codigoBloque	= 12;       // Código del bloque vacío
	private final String	tipoBloque		= "VACIO";

	public String getTipo() { return tipoBloque; }

	public int getCodigoBloque(String pantalla) { return codigoBloque; }

	public boolean romperBloque(EntidadInamovibleBloque pBloque) {
		pBloque.cambiarTipo(new EntidadInamovibleBloqueStateFuego(), tipoBloque);
		return true;
	}

	public boolean getJugadorChocaContraCelda() { return false; }

	public boolean getPuedeSerExplotado() { return true; }
}