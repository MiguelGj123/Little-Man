
public class EntidadInamovibleBombaBlanca extends EntidadInamovibleBomba {
	
	private static final int radioBomba			= 1;
	private static final int ticksHastaExplotar = 60;
	private static final int codigoBomba		= 30;
	
	public EntidadInamovibleBombaBlanca(int posX, int posY) {
		super(radioBomba, ticksHastaExplotar, codigoBomba, posX, posY);
	}

}
