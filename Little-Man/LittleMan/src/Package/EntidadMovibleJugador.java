package Package;

public abstract class EntidadMovibleJugador extends EntidadMovible 
{
	
	private int contadorBombas = 0;
	private int maxBombas;
	private int vidas;
	private final int radioBomba;
	private final int duracionBomba;
	private final int codigoJugador;
	
	public EntidadMovibleJugador(int maxBombas, int vidas, int radioBomba, int duracionBomba, int codigoJugador) {
		this.maxBombas = maxBombas;
		this.vidas = vidas;
		this.radioBomba = radioBomba;
		this.duracionBomba = duracionBomba;
		this.codigoJugador = codigoJugador;
	}
	public int		getCodigoJugador()	{ return codigoJugador;}
	public void		gestionarVida()		{ if (vidas > 0) { vidas--; } }
	
	public boolean	getEstaMuerto()		{ return vidas == 0; }
	
	public boolean	puedePonerBombas() 	{ return contadorBombas < maxBombas; }
	
	public void		sumarVida()			{ vidas = (vidas<= 0) ? 1 : vidas + 1; }
	
	public void		ponerBomba()		{ contadorBombas++; }
	
	public void		bombaExplotada()	{ contadorBombas--; }
	
	public int		radioBomba()		{ return radioBomba; }
	
	public int		duracionBomba()		{ return duracionBomba; }
	
	public void		setPosX(int pPosX)	{ super.setPosX(pPosX); }
	
	public void		setPosY(int pPosY)	{ super.setPosY(pPosY); }
	
	public int		getPosX()			{ return super.getPosX(); }
	
	public int		getPosY()			{ return super.getPosY(); }

}
