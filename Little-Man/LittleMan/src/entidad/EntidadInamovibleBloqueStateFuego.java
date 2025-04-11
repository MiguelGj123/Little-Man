package entidad;

public class EntidadInamovibleBloqueStateFuego implements EntidadInamovibleBloqueState {
	
	private final int 		codigoBloque	= 13;			// El codigo del bloque fuego es el codigo 13
	private final String	tipoBloque		= "FUEGO";		// El tipo   del bloque fuego es "FUEGO"
	
	public String getTipo() { return tipoBloque; }

	public int getCodigoBloque() { return codigoBloque;	}
	
	public boolean romperBloque(EntidadInamovibleBloque pBloque)
	{
		pBloque.cambiarTipo(new EntidadInamovibleBloqueStateFuego(), tipoBloque);
		return true;
	}
	
	public boolean getJugadorChocaContraCelda() { return false; }
	
	public boolean getPuedeSerExplotado() { return true; }
	
	
	
}
