package entidad;

// Power-up que añade una vida extra al jugador. Código 612.
public class EntidadInamoviblePowerupVidaMas extends EntidadInamoviblePowerup {

	private static final int codigoPowerup = 612;

	public EntidadInamoviblePowerupVidaMas(int posX, int posY) {
		super(posX, posY, codigoPowerup);
	}
}
