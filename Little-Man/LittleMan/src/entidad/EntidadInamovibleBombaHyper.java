package entidad;

public class EntidadInamovibleBombaHyper extends EntidadInamovibleBomba {
	
	private static final int radioBomba			= 1;
	private static final int ticksHastaExplotar = 80;
	private static final int codigoBomba		= 301;
	
	public EntidadInamovibleBombaHyper(int posX, int posY, int radio) {
		super(radioBomba+radio-1, ticksHastaExplotar, codigoBomba, posX, posY);
	}

}
