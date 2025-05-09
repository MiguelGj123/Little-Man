package entidad;

// Power-up que otorga puntos al jugador. Código 608.
public class EntidadInamoviblePowerupPuntos extends EntidadInamoviblePowerup {

	private static final int codigoPowerup = 608;

	public EntidadInamoviblePowerupPuntos(int posX, int posY) {
		super(posX, posY, codigoPowerup);
	}
}