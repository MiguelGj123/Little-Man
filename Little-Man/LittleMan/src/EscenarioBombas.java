
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;



public class EscenarioBombas {
	
	Random random = new Random();
	
	private static EscenarioBombas misBombas;
    private ArrayList<EntidadInamovibleBomba> listaBombas = new ArrayList<EntidadInamovibleBomba>();
    
    private EscenarioBombas() {}
    
    public static EscenarioBombas getBombas(){
    	if (misBombas == null) {
    		misBombas = new EscenarioBombas();
    	}
    	return misBombas;
    }

    
    public void inicializarBombas() { listaBombas.clear(); }
    
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
	
	public void actualizarTicksBombas() {
		for ( int i=0; i<listaBombas.size(); i++) {
			EntidadInamovibleBomba pBomba = listaBombas.get(i);													// Obtenemos el bloque que queremos actualizar
			
			if (pBomba.tick()) {																					// Comprobamos si al actualizar termina su cuenta atras para explotar
				Escenario.getEscenario().gestionarExplosion(listaBombas.get(i).getPosX(), listaBombas.get(i).getPosY(), listaBombas.get(i).getRadioBomba());																				// Indica al jugador que la bomba ha explotado
				listaBombas.remove(i);																			// Eliminamos la bomba de la lista de actualizaciones
				i--;																							// El elemento i+1 de la lista pasa a ser el elemento i, por lo que para actualizarlo es necesario retroceder un elemento en la lista
			}
		}
	}
	
	public boolean ponerBomba(String tipoBomba, int posJX, int posJY){
		boolean bombaPuesta = false;
		
		if (listaBombas.size()==0) {
			SoundManager.getSoundManager().playSound("placeBomb");
			listaBombas.add(EntidadInamovibleBombaFactory.getBombaFactory().generate(tipoBomba, posJX, posJY));
			bombaPuesta = true;
		} else if ( !(listaBombas.get(listaBombas.size()-1).getPosX() == posJX && listaBombas.get(listaBombas.size()-1).getPosY() == posJY )) {		// si no es la misma posicion que la ultima bomba puesta
			SoundManager.getSoundManager().playSound("placeBomb");
			listaBombas.add(EntidadInamovibleBombaFactory.getBombaFactory().generate(tipoBomba, posJX, posJY));
			bombaPuesta = true;
		}
		return bombaPuesta;
	}
	
	public int[][][] generarMatrizAniadirBloques(int[][][] matrizEditar){
		for (EntidadInamovibleBomba pBomba : listaBombas) {
			matrizEditar[pBomba.getPosX()][pBomba.getPosY()][3] = pBomba.getCodigoBomba();
		}
		return matrizEditar;
	}

    
}
