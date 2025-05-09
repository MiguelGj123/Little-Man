package entidad;

// Power-up que aumenta el número de bombas disponibles. Código 601.
public class EntidadInamoviblePowerupBombaMas extends EntidadInamoviblePowerup {

	private static final int codigoPowerup = 601;

	public EntidadInamoviblePowerupBombaMas(int posX, int posY) {
		super(posX, posY, codigoPowerup);
	}
}