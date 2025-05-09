package entidad;

// Estado 'Blando' de un bloque. Puede ser destruido y convertido en 'FUEGO'.
public class EntidadInamovibleBloqueStateBlando implements EntidadInamovibleBloqueState {

	private final int		codigoBloqueNormal	= 11;      // Código visual en estado normal
	private final int		codigoBloqueNoDuro	= 15;      // Código en estado alternativo
	private final String	tipoBloque			= "BLANDO";

	public String getTipo() { return tipoBloque; }

	public int getCodigoBloque(String pantalla) {
		return pantalla.equals("NORMAL") ? codigoBloqueNormal : codigoBloqueNoDuro;
	}

	public boolean romperBloque(EntidadInamovibleBloque pBloque) {
		pBloque.cambiarTipo(new EntidadInamovibleBloqueStateFuego(), tipoBloque);
		return true;
	}

	public boolean getJugadorChocaContraCelda() { return true; }

	public boolean getPuedeSerExplotado() { return true; }
}
