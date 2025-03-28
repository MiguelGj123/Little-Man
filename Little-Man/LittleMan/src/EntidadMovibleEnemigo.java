
public abstract class EntidadMovibleEnemigo extends EntidadMovible 
{
	
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
	
	public boolean 	tick()				{ticks--; return ticks <= 0; }
	
	public void		resetTick()			{ticks=ticksInicial;}
	
	public int		getCodigoEnemigo()	{ return codigoEnemigo;}
	
	public void		gestionarVida()		{ if (vidas > 0) { vidas--; } }
	
	public boolean	getEstaMuerto()		{ return vidas == 0; }
	
	public void		sumarVida()			{ vidas = (vidas<= 0) ? 1 : vidas + 1; }
	
	public void		setPosX(int pPosX)	{ super.setPosX(pPosX); }
	
	public void		setPosY(int pPosY)	{ super.setPosY(pPosY); }
	
	public int		getPosX()			{ return super.getPosX(); }
	
	public int		getPosY()			{ return super.getPosY(); }

}
