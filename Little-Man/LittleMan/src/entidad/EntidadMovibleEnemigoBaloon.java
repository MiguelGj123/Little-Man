package entidad;

// Enemigo tipo "Baloon" con 1 vida, 20 ticks por acción, código visual 40.
public class EntidadMovibleEnemigoBaloon extends EntidadMovibleEnemigo {

	private static final int ticks = 20;
	private static final int vidas = 1;
	private static final int codigoEnemigo = 40;

	public EntidadMovibleEnemigoBaloon(int posX, int posY) {
		super(vidas, codigoEnemigo, ticks, posX, posY);
	}
}
