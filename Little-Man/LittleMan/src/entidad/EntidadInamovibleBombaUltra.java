package entidad;

public class EntidadInamovibleBombaUltra extends EntidadInamovibleBomba {
	
	private static final int radioBomba			= 20;
	private static final int ticksHastaExplotar = 60;
	private static final int codigoBomba		= 35;
	
	public EntidadInamovibleBombaUltra(int posX, int posY) {
		super(radioBomba, ticksHastaExplotar, codigoBomba, posX, posY);
	}
	
}
