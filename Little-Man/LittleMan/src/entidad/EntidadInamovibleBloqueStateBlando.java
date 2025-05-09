package entidad;

public class EntidadInamovibleBloqueStateBlando implements EntidadInamovibleBloqueState {

	private final int		codigoBloqueNormal	= 11;			// El codigo del bloque blando es el codigo 11
	private final int		codigoBloqueNoDuro	= 15;			// El codigo del bloque NoDuro es el codigo 11
	private final String	tipoBloque			= "BLANDO";		// El tipo   del bloque blando es "BLANDO"
	
	public String getTipo() { return tipoBloque; }

	public int getCodigoBloque(String pantalla) { return pantalla.equals("NORMAL")? codigoBloqueNormal : codigoBloqueNoDuro;}
	
	public boolean romperBloque(EntidadInamovibleBloque pBloque)
	{
		pBloque.cambiarTipo(new EntidadInamovibleBloqueStateFuego(), tipoBloque);
		return true;
	}
	
	public boolean getJugadorChocaContraCelda() { return true; }
	
	public boolean getPuedeSerExplotado() { return true; }
	
	
	
}
