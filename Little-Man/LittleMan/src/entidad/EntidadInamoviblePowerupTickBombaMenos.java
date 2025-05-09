package entidad;

// Power-up que reduce el tiempo (ticks) antes de la explosión de una bomba. Código 610.
public class EntidadInamoviblePowerupTickBombaMenos extends EntidadInamoviblePowerup {

	private static final int codigoPowerup = 610;

	public EntidadInamoviblePowerupTickBombaMenos(int posX, int posY) {
		super(posX, posY, codigoPowerup);
	}
}