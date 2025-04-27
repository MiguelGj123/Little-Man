package entidad;

public class EntidadInamovibleBombaMega extends EntidadInamovibleBomba {
	
	private static final int radioBomba			= 3;
	private static final int ticksHastaExplotar = 5;
	private static final int codigoBomba		= 351;
	
	public EntidadInamovibleBombaMega(int posX, int posY) {
		super(radioBomba, ticksHastaExplotar, codigoBomba, posX, posY);
	}

}
