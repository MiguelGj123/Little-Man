package Package;

public interface BloqueState {
	public int getCodigoBloque();
	public boolean romperBloque(Bloque pBloque);
	public boolean getJugadorChocaContraCelda();
	public boolean getPuedeSerExplotado();
	
}
