import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class EscenarioEnemigos {
	
	Random random = new Random();

	private int COLUMNAS, FILAS;
	private static EscenarioEnemigos misEnemigos;
    private ArrayList<EntidadMovibleEnemigo> listaEnemigos = new ArrayList<EntidadMovibleEnemigo>();
    
    private EscenarioEnemigos() {}
    
    public static EscenarioEnemigos getEnemigos(){
    	if (misEnemigos == null) {
    		misEnemigos = new EscenarioEnemigos();
    	}
    	return misEnemigos;
    }
    
    public void darValoresColumnasFilas (int COLUMNAS, int FILAS) {
    	this.COLUMNAS = COLUMNAS;
    	this.FILAS = FILAS;
    }
	
	public void inicializarEnemigos(ArrayList<int[]> posicionesVacias, int COLUMNAS, int FILAS) {
		this.COLUMNAS = COLUMNAS;
		this.FILAS = FILAS;
		
		listaEnemigos.clear();
		Iterator<int[]> posicionesVaciasIterator = posicionesVacias.iterator();
		EntidadMovibleEnemigo enemigo;
		
		while (posicionesVaciasIterator.hasNext())
		{
			int[] posV = posicionesVaciasIterator.next();
			enemigo = null;
			
			if (posV[0] + posV[1] > 2 && random.nextDouble() < 0.25) {
				 enemigo = EntidadMovibleEnemigoFactory.getEntidadMovibleEnemigoFactory().generate("BALOON", posV[0], posV[1]);
				 listaEnemigos.add(enemigo);
			}
		}
	}
	
	public void actualizarTicksEnemigos() {
		for ( int i=0; i<listaEnemigos.size(); i++) {
			EntidadMovibleEnemigo pEnemigo = listaEnemigos.get(i);								// Obtenemos el Enemigo que queremos actualizar
			
			if (pEnemigo.tick()) {																// Comprobamos si al actualizar termina su cuenta atras del enemigo
				actualizarPosicionEnemigo(i);													// Gestiona el movimiento del enemigo
				pEnemigo.resetTick();
				i--;																			// El elemento i+1 de la lista pasa a ser el elemento i, por lo que para actualizarlo es necesario retroceder un elemento en la lista
			}
		}
		for ( int i = 0; i < listaEnemigos.size(); i++) {
			Escenario.getEscenario().gestionarEnemigo(listaEnemigos.get(i).getPosX(),listaEnemigos.get(i).getPosY());						// El enemigo se encarga de notificar a los jugadores que en esa posicion hay un enemigo
		}
	}
	
	
	private void actualizarPosicionEnemigo(int i) {			
		int posEX=listaEnemigos.get(i).getPosX();												// posicion X actual del Enemigo	[COLUMNA]
		int posEY=listaEnemigos.get(i).getPosY();												// posicion Y actual del Enemigo	[FILA]
		int posEXnew = posEX, posEYnew = posEY;													// posiciones XY nuevas despues del movimiento
		boolean movimientoEnemigoChocaConParedOBomba = false;
		
		int mov = random.nextInt(4) + 1 ;
		
		posEXnew = (mov == 1  && posEX != 0			  ) ? posEX - 1 : posEXnew;
		posEXnew = (mov == 2  && posEX != COLUMNAS - 1) ? posEX + 1 : posEXnew;
		posEYnew = (mov == 3  && posEY != 0			  ) ? posEY - 1 : posEYnew;
		posEYnew = (mov == 4  && posEY != FILAS - 1   ) ? posEY + 1 : posEYnew;
		
		movimientoEnemigoChocaConParedOBomba = 													// Enemigo choca contra pared o bomba si
				(  ( mov == 1  && posEX == 0			)										// Choca contra exterior
				|| ( mov == 2  && posEX == COLUMNAS - 1	)
				|| ( mov == 3  && posEY == 0			)
				|| ( mov == 4  && posEY == FILAS - 1	)
				|| Escenario.getEscenario().chocaConPos(posEXnew, posEYnew));					// o si hay una bomba o bloque en la nueva posicion
		
		for ( int j=0; j<listaEnemigos.size(); j++) {											// Choca contra otro Enemigo?
			movimientoEnemigoChocaConParedOBomba =
				  (  posEXnew == listaEnemigos.get(j).getPosX()
				  && posEYnew == listaEnemigos.get(j).getPosY()) ?
							true : movimientoEnemigoChocaConParedOBomba;
		}
		
		if (!movimientoEnemigoChocaConParedOBomba) {
			listaEnemigos.get(i).setPosX(posEXnew);
			listaEnemigos.get(i).setPosY(posEYnew);
		}

	}
	
	
	
	public void gestionarFuego(int posFX, int posFY) {
		for (int i = 0; i < listaEnemigos.size(); i++) {
			if (listaEnemigos.get(i).getPosX() == posFX && listaEnemigos.get(i).getPosY() == posFY) {
				listaEnemigos.remove(i);
				i--;
			}
		}
	}
	
	
	public int[][] generarMatrizAniadirBloques(int[][] matrizEditar){
		for (EntidadMovibleEnemigo pEnemigo : listaEnemigos) {
			matrizEditar[pEnemigo.getPosX()][pEnemigo.getPosY()] = pEnemigo.getCodigoEnemigo();
		}
		return matrizEditar;
	}
	
	
    
}
