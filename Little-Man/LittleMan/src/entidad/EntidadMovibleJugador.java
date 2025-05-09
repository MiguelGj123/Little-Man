package entidad;

//Clase base abstracta para todos los jugadores del juego.
//Controla sus vidas, bombas, tipo, sprite y estado de invulnerabilidad.
public abstract class EntidadMovibleJugador extends EntidadMovible {

	private int contadorBombas = 0;
	private int vidas;
	private int codigoMov = 1;
	private int maxBombas;
	private int maxVidas;
	private final String tipoBomba;
	private final int[] codigosJugador;
	private final String tipoJugador;
	private int ticks;
	private int ticksInicial;

	public EntidadMovibleJugador(int maxBombas, int vidas, String tipoBomba, int[] codigosJugador, String tipoJugador, int posX, int posY, int pInvencibilidad) {
		super(posX, posY);
		this.maxBombas = maxBombas;
		this.maxVidas = vidas;
		this.vidas = vidas;
		this.tipoBomba = tipoBomba;
		this.codigosJugador = codigosJugador;
		this.tipoJugador = tipoJugador;
		this.ticks = pInvencibilidad;
		this.ticksInicial = pInvencibilidad;
	}

	public int getCodigoJugador() { return codigosJugador[0]; }
	public int getCodigoJugadorConBomba() { return codigosJugador[1]; }
	public int getCodigoJugadorMuerto() { return codigosJugador[2]; }
	public int getCodigoJugadorVictoria() { return codigosJugador[3]; }
	public String getTipoJugador() { return tipoJugador; }
	public String getTipoBomba() { return tipoBomba; }
	public int getVidas() { return vidas; }
	public int getNumBombas() { return maxBombas - contadorBombas; }
	public int getMaxBombas() { return maxBombas; }

	public void aumentarVida() {
		if (vidas < maxVidas) { vidas++; }
		else if (maxVidas < 5) { maxVidas++; vidas++; }
	}
	public void sumarVida() { if (vidas < maxVidas) vidas++; }
	
	// Reduce en 1 la vida del jugador si aún tiene alguna.
	public void gestionarVida() { if (vidas > 0) vidas--; }
	
	// Indica si al jugador le quedan 0 vidas.
	public boolean getEstaMuerto() { return vidas == 0; }

	public void ponerBomba() { contadorBombas++; }
	public void sumarBomba() { maxBombas++; }
	public void restarBomba() { maxBombas--; }
	
	// Reduce el contador interno de bombas tras una explosión.
	public void bombaExplotada() { contadorBombas--; }

	public boolean puedePonerBombas() { return contadorBombas < maxBombas; }

	public void setCodigoMov(int pCodigoMov) { codigoMov = pCodigoMov; }
	public int getCodigoMov() { return codigoMov; }

	public void setPosX(int pPosX) { super.setPosX(pPosX); }
	public void setPosY(int pPosY) { super.setPosY(pPosY); }
	public int getPosX() { return super.getPosX(); }
	public int getPosY() { return super.getPosY(); }

	
	public boolean tick() { ticks--; return ticks <= 0; }
	public void resetTick() { ticks = ticksInicial; }
}
