package entidad;

// Clase base para enemigos. Incluye vida, ticks (tiempo de acción), y código de sprite.
// Define métodos para gestionar daño, movimiento y muerte.
public abstract class EntidadMovibleEnemigo extends EntidadMovible {

	private int ticksInicial;
	private int ticks;
	private int vidas;
	private final int codigoEnemigo;

	public EntidadMovibleEnemigo(int pVidas, int pCodigoEnemigo, int pTicks, int posX, int posY) {
		super(posX, posY);
		this.vidas = pVidas;
		this.codigoEnemigo = pCodigoEnemigo;
		this.ticks = pTicks;
		this.ticksInicial = pTicks;
	}

	// Reduce el contador de ticks. Devuelve true si ya puede moverse.
	public boolean tick() { ticks--; return ticks <= 0; }

	public void resetTick() { ticks = ticksInicial; }

	public int getCodigoEnemigo() { return codigoEnemigo; }

	// Resta una vida al enemigo si aún está vivo.
	public void gestionarVida() { if (vidas > 0) { vidas--; } }

	// Devuelve true si el enemigo ya no tiene vidas.
	public boolean getEstaMuerto() { return vidas == 0; }

	public void sumarVida() { vidas = (vidas <= 0) ? 1 : vidas + 1; }

	public void setPosX(int pPosX) { super.setPosX(pPosX); }

	public void setPosY(int pPosY) { super.setPosY(pPosY); }

	public int getPosX() { return super.getPosX(); }

	public int getPosY() { return super.getPosY(); }
}
