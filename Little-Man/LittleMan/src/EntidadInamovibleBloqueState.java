
public interface EntidadInamovibleBloqueState {
	public String getTipo();
	public int getCodigoBloque();
	public boolean romperBloque(EntidadInamovibleBloque pBloque);
	public boolean getJugadorChocaContraCelda();
	public boolean getPuedeSerExplotado();
	
}
