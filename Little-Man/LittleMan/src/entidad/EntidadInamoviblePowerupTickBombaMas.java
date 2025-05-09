package entidad;

// Power-up que aumenta el tiempo (ticks) antes de que una bomba explote. Código 609.
public class EntidadInamoviblePowerupTickBombaMas extends EntidadInamoviblePowerup {

	private static final int codigoPowerup = 609;

	public EntidadInamoviblePowerupTickBombaMas(int posX, int posY) {
		super(posX, posY, codigoPowerup);
	}
}
