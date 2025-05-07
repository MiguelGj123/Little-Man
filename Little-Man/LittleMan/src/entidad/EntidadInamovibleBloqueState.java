package entidad;

public interface EntidadInamovibleBloqueState {
	public String getTipo();
	public int getCodigoBloque(String pantalla);
	public boolean romperBloque(EntidadInamovibleBloque pBloque);
	public boolean getJugadorChocaContraCelda();
	public boolean getPuedeSerExplotado();
	
}
