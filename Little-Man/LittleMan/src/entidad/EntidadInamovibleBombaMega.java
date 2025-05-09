package entidad;

//Bomba Mega con un radio de 3 y un retardo de explosión de 5 ticks.
//Subclase concreta de EntidadInamovibleBomba.
public class EntidadInamovibleBombaMega extends EntidadInamovibleBomba {
	
	private static final int radioBomba			= 3;
	private static final int ticksHastaExplotar = 5;
	private static final int codigoBomba		= 351;
	
	public EntidadInamovibleBombaMega(int posX, int posY) {
		super(radioBomba, ticksHastaExplotar, codigoBomba, posX, posY);
	}

}
