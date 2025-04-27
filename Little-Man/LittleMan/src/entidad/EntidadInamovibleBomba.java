package entidad;

public abstract class EntidadInamovibleBomba extends EntidadInamovible {
	
	private int ticks;
	private int radioBomba;
	private final int codigoBomba;
	
	public EntidadInamovibleBomba (int radio, int ticksHastaExplotar, int codigoBomba, int posX, int posY)
	{
		super(posX, posY);
		this.radioBomba = radio;
		ticks = ticksHastaExplotar;
		this.codigoBomba = codigoBomba;
	}
	
	public boolean tick() 
	{
			ticks--;

		return ticks<0;
		
	}
	public void cambioRadio(int cambioRadio) {
		radioBomba = radioBomba+cambioRadio;
	}
	
	public void cambioTick(int cambioTicks) {
		ticks = ticks+cambioTicks;
	}
	public int getTickBomba() { return ticks; }
	
	public int getRadioBomba() { return radioBomba; }
	
	public int getCodigoBomba() { return codigoBomba; }
	
	public int getPosX() { return super.getPosX(); }
	
	public int getPosY() { return super.getPosY(); }


}
