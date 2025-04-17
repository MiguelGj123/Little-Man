package entidad;

public class EntidadMovibleEnemigoDoria extends EntidadMovibleEnemigo
{
	private static final int ticks = 20;
	private static final int vidas = 1;
	private static final int codigoEnemigo = 41;

	
	public EntidadMovibleEnemigoDoria(int posX, int posY) {
		super(vidas, codigoEnemigo, ticks, posX, posY);
	}
}
