package entidad;

//Representa un bloque del escenario que puede tener distintos estados (duro, blando, fuego, vacío).
//Usa el patrón State para cambiar su comportamiento tras romperse o explotar.
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
	
	// Intenta romper el bloque según su estado actual; si se rompe, inicia cuenta regresiva para desaparecer.

	public void romperbloque() {
		if (state.romperBloque(this)) {
			ticks = 40;
		}
	}
	
	// Reduce el contador de ticks tras ser roto; al llegar a 0 lo convierte en bloque vacío.
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
