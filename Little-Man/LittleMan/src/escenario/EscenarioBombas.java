package escenario;

import java.util.ArrayList;

import entidad.EntidadInamovibleBomba;
import entidad.EntidadInamovibleBombaFactory;
import sonido.SonidoCodigo;




public class EscenarioBombas {

	private static EscenarioBombas misBombas;
	private int cambioRadio=0;
	private int cambioTick=0;
	private String tipoDeBomba="";
	private EscenarioFacade miEscenarioFacade;
    private ArrayList<EntidadInamovibleBomba> listaBombas = new ArrayList<EntidadInamovibleBomba>();
    private ArrayList<String> listaSonidos = new ArrayList<String>();

    
    private EscenarioBombas() {}
    
    public static EscenarioBombas getBombas(){
    	if (misBombas == null) {
    		misBombas = new EscenarioBombas();
    	}
    	return misBombas;
    }

    
    public void inicializarBombas() {
    	listaBombas.clear();
    	miEscenarioFacade = EscenarioFacade.getEscenarioFacade();
    	cambioRadio=0;
    	cambioTick=0;
    }
    
	public EntidadInamovibleBomba getBombaEnPosicionXY (int pPosX, int pPosY)										// devuelve la bomba que hay en una posicion XY
	{																												// devuelve null si no hay bomba en esa posicion
		for (EntidadInamovibleBomba pBomba: listaBombas) {
			if (pBomba.getPosX()==pPosX && pBomba.getPosY()==pPosY) { return pBomba; }
		}
		return null;
	}
    
	public boolean hayBombaEnPosicionXY (int pPosX, int pPosY)										// devuelve la bomba que hay en una posicion XY
	{																												// devuelve null si no hay bomba en esa posicion
		for (EntidadInamovibleBomba pBomba: listaBombas) {
			if (pBomba.getPosX()==pPosX && pBomba.getPosY()==pPosY) { return true; }
		}
		return false;
	}
	public Double actualizarTicksBombas() {
		Double puntos = 0.;

		for ( int i=0; i<listaBombas.size(); i++) {
			EntidadInamovibleBomba pBomba = listaBombas.get(i);													// Obtenemos el bloque que queremos actualizar
			
			if (pBomba.tick()) {																					// Comprobamos si al actualizar termina su cuenta atras para explotar
				puntos=miEscenarioFacade.gestionarExplosion(listaBombas.get(i).getPosX(), listaBombas.get(i).getPosY(), listaBombas.get(i).getRadioBomba())+puntos;																				// Indica al jugador que la bomba ha explotado
				listaBombas.remove(i);																			// Eliminamos la bomba de la lista de actualizaciones
				i--;																							// El elemento i+1 de la lista pasa a ser el elemento i, por lo que para actualizarlo es necesario retroceder un elemento en la lista
			}
		}
		return puntos;
	}
	
	public boolean ponerBomba(String tipoBomba, int posJX, int posJY, int radio){
		boolean bombaPuesta = false;
		tipoDeBomba=tipoBomba;
		
		if (listaBombas.size()==0) {
			listaSonidos.add(SonidoCodigo.PLACE_BOMB.sonar());
			listaBombas.add(EntidadInamovibleBombaFactory.getBombaFactory().generate(tipoBomba, posJX, posJY, radio));
			listaBombas.getLast().cambioRadio(cambioRadio);
			listaBombas.getLast().cambioTick(cambioTick);
			bombaPuesta = true;
			
		} else if ( !(listaBombas.get(listaBombas.size()-1).getPosX() == posJX && listaBombas.get(listaBombas.size()-1).getPosY() == posJY )) {		// si no es la misma posicion que la ultima bomba puesta
			listaSonidos.add(SonidoCodigo.PLACE_BOMB.sonar());
			listaBombas.add(EntidadInamovibleBombaFactory.getBombaFactory().generate(tipoBomba, posJX, posJY, radio));
			listaBombas.getLast().cambioRadio(cambioRadio);
			listaBombas.getLast().cambioTick(cambioTick);
			bombaPuesta = true;
			
		}
		return bombaPuesta;
	}
		
	public int[][] generarMatrizAniadirBombas(int COLUMNAS, int FILAS){
		int[][] matrizGenerada = new int[COLUMNAS][FILAS];

		for (EntidadInamovibleBomba pBomba : listaBombas) {
			matrizGenerada[pBomba.getPosX()][pBomba.getPosY()] = pBomba.getCodigoBomba();
		}
		return matrizGenerada;
	}

	public String[] getListaSonidos() {
	    return listaSonidos.toArray(new String[0]);
	}
	
	public void listaSonidosClear() {
		listaSonidos.clear();
	}
	public int getTicks()		{return EntidadInamovibleBombaFactory.getBombaFactory().generate(tipoDeBomba, 0, 0, 1).getTickBomba();}
	public int getRadio()		{return EntidadInamovibleBombaFactory.getBombaFactory().generate(tipoDeBomba, 0, 0, 1).getRadioBomba();}
	public void sumarRadio() 	{cambioRadio++;}
	public void restarRadio() 	{cambioRadio--;}
	public void sumarTicks() 	{cambioTick=cambioTick+5;}
	public void restarTicks() 	{cambioTick=cambioTick-5;}

	public void resetBombas() {
		listaBombas.clear();
    	miEscenarioFacade = null;
    	cambioRadio=0;
    	cambioTick=0;
    	tipoDeBomba="";
    	listaSonidos.clear();
		
	}


    
}
