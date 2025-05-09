package entidad;

// Interfaz para representar los distintos estados de un bloque.
// Cada estado define su tipo, su comportamiento frente a explosiones, colisiones, etc.
public interface EntidadInamovibleBloqueState {
	public String getTipo();
	public int getCodigoBloque(String pantalla);
	public boolean romperBloque(EntidadInamovibleBloque pBloque);
	public boolean getJugadorChocaContraCelda();
	public boolean getPuedeSerExplotado();
}
