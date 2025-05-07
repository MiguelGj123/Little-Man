package entidad;

public class EntidadInamovibleBloqueStateVacio implements EntidadInamovibleBloqueState {
	
	private final int		codigoBloque	= 12;			// El codigo del bloque vacio es el codigo 12
	private final String	tipoBloque		= "VACIO";		// El tipo   del bloque vacio es "vacio"
	
	public String getTipo() { return tipoBloque; }

	public int getCodigoBloque(String pantalla) { return codigoBloque;	}
	
	public boolean romperBloque(EntidadInamovibleBloque pBloque)
	{
		pBloque.cambiarTipo(new EntidadInamovibleBloqueStateFuego(), tipoBloque);
		return true;
	}
	
	public boolean getJugadorChocaContraCelda() { return false; }
	
	public boolean getPuedeSerExplotado() { return true; }
	
	
	
}
