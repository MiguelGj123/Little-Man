package entidad;

public abstract class EntidadMovibleJugador extends EntidadMovible 
{
	
	private int contadorBombas = 0;
	private int vidas;
	private int codigoMov=1;
	private final int maxBombas;
	private final String tipoBomba;
	private final int[] codigosJugador;
	private final String tipoJugador;
	private int ticks;
	private int ticksInicial;
	
	public EntidadMovibleJugador(int maxBombas, int vidas, String tipoBomba, int[] codigosJugador, String tipoJugador, int posX, int posY, int pInvencibilidad) {
		super (posX, posY);
		this.maxBombas = maxBombas;
		this.vidas = vidas;
		this.tipoBomba = tipoBomba;
		this.codigosJugador = codigosJugador;
		this.tipoJugador = tipoJugador;
		this.ticks= pInvencibilidad;
		this.ticksInicial = pInvencibilidad;
	}
	
	public int		getCodigoJugador()			{ return codigosJugador[0];}

	public int		getCodigoJugadorConBomba()	{ return codigosJugador[1];}
	
	public int		getCodigoJugadorMuerto()	{ return codigosJugador[2];}
	
	public int		getCodigoJugadorVictoria()	{ return codigosJugador[3];}
	
	public String	getTipoJugador()			{ return tipoJugador;}
	
	public String	getTipoBomba()				{ return tipoBomba; }
	
	public int		getVidas()					{ return vidas; }
	
	public void		sumarVida()					{ vidas = (vidas<= 0) ? 1 : vidas + 1; }
	
	public void		gestionarVida()				{ if (vidas > 0) { vidas--; } }
	
	public boolean	getEstaMuerto()				{ return vidas == 0; }
	
	public void		ponerBomba()				{ contadorBombas++; }
	
	public void		bombaExplotada()			{ contadorBombas--; }
	
	public boolean	puedePonerBombas() 			{ return contadorBombas < maxBombas; }
	
	public void		setCodigoMov(int pCodigoMov){ codigoMov=pCodigoMov; }
	
	public int		getCodigoMov()				{ return codigoMov; }
	
	public void		setPosX(int pPosX)			{ super.setPosX(pPosX); }
	
	public void		setPosY(int pPosY)			{ super.setPosY(pPosY); }
	
	public int		getPosX()					{ return super.getPosX(); }
	
	public int		getPosY()					{ return super.getPosY(); }
	
	public boolean	tick()						{ticks--; return ticks <= 0; }
	
	public void		resetTick()					{ticks=ticksInicial;}

	
	
}
