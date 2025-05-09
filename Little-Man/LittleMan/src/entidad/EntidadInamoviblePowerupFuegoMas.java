package entidad;

// Power-up que aumenta el alcance del fuego de la bomba. Código 605.
public class EntidadInamoviblePowerupFuegoMas extends EntidadInamoviblePowerup {

	private static final int codigoPowerup = 605;

	public EntidadInamoviblePowerupFuegoMas(int posX, int posY) {
		super(posX, posY, codigoPowerup);
	}
}
