package Package;

public class Bloque extends EntidadInamovible 
{
	private int ticks;
	
	private BloqueState state;
	
	public Bloque (String pState) 
	{
		state = BloqueFactory.getBloqueFactory().generate(pState);
	}
	
	public void cambiarTipo(BloqueState state)
	{
		this.state = state;
	}
	
	public void romperbloque() {
		if (state.romperBloque(this)) {
			ticks = 40;
		}
	}
	
	public boolean tick(){
		ticks--;
		if(ticks==0) { cambiarTipo(BloqueFactory.getBloqueFactory().generate("VACIO")); }
		return ticks <= 0;
	}
	
	public int getCodigoBloque() { return state.getCodigoBloque(); }
	
	public boolean getJugadorChocaContraCelda() { return state.getJugadorChocaContraCelda(); }
	
	public boolean getPuedeSerExplotado() { return state.getPuedeSerExplotado(); }
	
	public void setPosX(int pPosX) { super.setPosX(pPosX); }
	
	public void setPosY(int pPosY) { super.setPosY(pPosY); }
	
	public int getPosX() { return super.getPosX(); }
	
	public int getPosY() { return super.getPosY(); }
	
	
}
