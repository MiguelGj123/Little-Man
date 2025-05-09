package entidad;

// Power-up que hace al jugador invencible temporalmente. Código 607.
public class EntidadInamoviblePowerupInvencible extends EntidadInamoviblePowerup {

	private static final int codigoPowerup = 607;

	public EntidadInamoviblePowerupInvencible(int posX, int posY) {
		super(posX, posY, codigoPowerup);
	}
}
