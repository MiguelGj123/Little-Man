package entidad;

// Power-up que reduce el alcance del fuego. Código 606.
public class EntidadInamoviblePowerupFuegoMenos extends EntidadInamoviblePowerup {

	private static final int codigoPowerup = 606;

	public EntidadInamoviblePowerupFuegoMenos(int posX, int posY) {
		super(posX, posY, codigoPowerup);
	}
}