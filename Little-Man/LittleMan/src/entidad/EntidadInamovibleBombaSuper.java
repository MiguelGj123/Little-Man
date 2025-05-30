package entidad;

// Bomba Super (BLANCO), bomba estándar con duración media.
public class EntidadInamovibleBombaSuper extends EntidadInamovibleBomba {

	private static final int radioBomba = 1;
	private static final int ticksHastaExplotar = 60;
	private static final int codigoBomba = 30;

	public EntidadInamovibleBombaSuper(int posX, int posY) {
		super(radioBomba, ticksHastaExplotar, codigoBomba, posX, posY);
	}
}