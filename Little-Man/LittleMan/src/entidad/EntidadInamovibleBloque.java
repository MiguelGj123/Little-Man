package entidad;

public class EntidadInamovibleBloque extends EntidadInamovible 
{
	private int ticks;
	private String tipo;
	private EntidadInamovibleBloqueState state;
	
	public EntidadInamovibleBloque (String pState, int posX, int posY) {
		super(posX, posY);
		tipo = pState;
		state = EntidadInamovibleBloqueFactory.getBloqueFactory().generate(pState);
	}
	
	public void cambiarTipo(EntidadInamovibleBloqueState state, String tipo) {
		this.state = state;
		this.tipo = tipo;
	}
	
	public void romperbloque() {
		if (state.romperBloque(this)) {
			ticks = 40;
		}
	}
	
	public boolean tick(){
		ticks--;
		if(ticks==0) { cambiarTipo(EntidadInamovibleBloqueFactory.getBloqueFactory().generate("VACIO"), "VACIO" ); }
		return ticks <= 0;
	}
	
	public String getTipo() { return tipo; }
	
	public int getCodigoBloque(String pantalla) { return state.getCodigoBloque(pantalla); }
	
	public boolean getChocaContraCelda() { return state.getJugadorChocaContraCelda(); }
	
	public boolean getPuedeSerExplotado() { return state.getPuedeSerExplotado(); }
	
	public int getPosX() { return super.getPosX(); }
	
	public int getPosY() { return super.getPosY(); }
	
	
}
