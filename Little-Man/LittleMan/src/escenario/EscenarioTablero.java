package escenario;
import java.util.ArrayList;
import java.util.Random;

import entidad.EntidadInamovibleBloque;
import sonido.SonidoCodigo;

//Clase Singleton que representa el tablero del juego.
//Gestiona los bloques, fuego, explosiones y proporciona información visual para la vista.

public class EscenarioTablero {
	
    private Random random = new Random();
	private Escenario_CONFIG esCfg = new Escenario_CONFIG();
	
	private static EscenarioTablero miTablero;
	private EscenarioFacade miEscenarioFacade;
    
    private EntidadInamovibleBloque[][] matrizTablero;
    private ArrayList<int[]> listaPosFuegos = new ArrayList<int[]>();
    private ArrayList<String> listaSonidos = new ArrayList<String>();

    
    
    
    private EscenarioTablero() {}
    
    

    
    public static EscenarioTablero getTablero(){
    	if (miTablero == null) {
    		miTablero = new EscenarioTablero();
    	}
    	return miTablero;
    }
    
 // Inicializa el tablero según el tipo indicado (NORMAL, VACIO, NO_DURO), asignando bloques en cada celda.
	public void inicializarTablero(String tipo)
	{
		matrizTablero = new EntidadInamovibleBloque[esCfg.col][esCfg.fil];
		listaPosFuegos.clear();
		miEscenarioFacade = EscenarioFacade.getEscenarioFacade();
	
		switch (tipo) {
        case "NORMAL":
        	inicializarTableroNormal();
            break;
        case "VACIO":
        	inicializarTableroVacio();
            break;
        case "NO_DURO":
        	inicializarTableroNoDuros();
            break;
        default:
        	inicializarTableroNormal();
		}	
	}
	
	
	
	private void inicializarTableroNormal() {
		for (int columna = 0; columna < esCfg.col; columna++) {
			for (int fila = 0; fila < esCfg.fil; fila++) {
				if  (columna + fila <= 1){
					matrizTablero[columna][fila] = new EntidadInamovibleBloque("VACIO", columna, fila); 													// Poner bloques vacios en la esquina superior izquierda para zona de inicio
				} else {
					if (columna % 2 != 0 && fila % 2 != 0) {
						matrizTablero[columna][fila] = new EntidadInamovibleBloque("DURO", columna, fila); 												//Poner bloques duros en posiciones impares
					} else {
						matrizTablero[columna][fila] = random.nextBoolean() ? new EntidadInamovibleBloque("VACIO", columna, fila) : new EntidadInamovibleBloque("BLANDO", columna, fila);	//Poner bloques blandos o vacios aleatoriamente en el resto de posiciones
					}
				}
			}
		}
	}
	
	
	private void inicializarTableroNoDuros() {	
		for (int columna = 0; columna < esCfg.col; columna++) {
	        for (int fila = 0; fila < esCfg.fil; fila++) {
	            if (columna + fila <= 1) {
	            	matrizTablero[columna][fila] = new EntidadInamovibleBloque("VACIO", columna, fila);
	            } else {
	            	matrizTablero[columna][fila] = random.nextBoolean() ? new EntidadInamovibleBloque("VACIO", columna, fila) : new EntidadInamovibleBloque("BLANDO", columna, fila); // Bloques blandos o vacíos aleatoriamente
	            }
	        }
	    }
	}
	
	
	private void inicializarTableroVacio() {
		for (int columna = 0; columna < esCfg.col; columna++) {
	        for (int fila = 0; fila < esCfg.fil; fila++) {
	        	if ((columna == 0 && fila == 0) || (columna == 1 && fila == 0) || (columna == 0 && fila == 1)) {
	            	matrizTablero[columna][fila] = new EntidadInamovibleBloque("VACIO", columna, fila);
	            } else {
	            	matrizTablero[columna][fila] = new EntidadInamovibleBloque("VACIO", columna, fila);	            
	            	}
	        	}
	        }
	}
	
	
	
	public ArrayList<int[]> getPosicionesVacias() {
		ArrayList<int[]> posicionesVacias = new ArrayList<>();
		
		for (int columna = 0; columna < esCfg.col; columna++) {
			for (int fila = 0; fila < esCfg.fil; fila++) {
				if (matrizTablero[columna][fila].getTipo().equals("VACIO")) {
					posicionesVacias.add(new int[] {columna, fila});
				}
			}
		}
		return posicionesVacias;
	}
	
	public boolean hayFuegoEnPosicionXY (int pPosX, int pPosY)
	{
		return listaPosFuegos.stream()
			    .anyMatch(f -> f[0] == pPosX && f[1] == pPosY);

	}
	
	public boolean getChocaContraCelda(int posX, int posY) { return matrizTablero[posX][posY].getChocaContraCelda(); }
	
	public boolean hayFuego() { return !listaPosFuegos.isEmpty(); }
	
	// Actualiza la duración del fuego en cada celda. Elimina fuegos que terminan y genera efectos colaterales.
	public int actualizarTicksFuego() {
		int puntos = 0;
		int mult = 1;
		for ( int i=0; i<listaPosFuegos.size(); i++) {
			int[] pBloque = listaPosFuegos.get(i);														// Obtenemos el bloque que queremos actualizar
			
			if (matrizTablero[pBloque[0]][pBloque[1]].tick()) {											// Comprobamos si al actualizar termina su cuenta atras del fuego
				listaPosFuegos.remove(i);																// Si la cuenta atras del fuego termina eliminamos el bloque de la lista, ya que no nos hara falta actualizarlo mas.
				i--;																					// El elemento i+1 de la lista pasa a ser el elemento i, por lo que para actualizarlo es necesario retroceder un elemento en la lista
			}
			
			puntos=(miEscenarioFacade.gestionarFuego(pBloque[0],pBloque[1])*mult)+puntos;				// El escenario se encarga de notificar a los jugadores y enemigos que en esa posicion mueren
			mult++;
		}
		return puntos;
	}
	
	// Aplica la lógica de una explosión desde una celda central con cierto radio.
	// Rompe bloques, añade fuego y genera power-ups aleatoriamente.
	public int gestionarExplosion(int centroExplosionX, int centroExplosionY, int radioBomba) {
    	int puntos = 0;
		int posBloqueExplotarX, posBloqueExplotarY;
		boolean[] finLineaBomba = new boolean[] {false, false, false, false};
		listaSonidos.add(SonidoCodigo.BOMB_EXPLODE.parar());
		listaSonidos.add(SonidoCodigo.BOMB_EXPLODE.sonar());
		
		
		
		matrizTablero[centroExplosionX][centroExplosionY].romperbloque();
		if (!hayFuegoEnPosicionXY(centroExplosionX, centroExplosionY))
		{
			listaPosFuegos.add (new int[] {centroExplosionX, centroExplosionY});
		}
		
		for (int i = 1; i <= radioBomba && !finLineaBomba.equals(new boolean[] {true, true, true, true}); i++) {
			for (int j = 0; j < 4; j++) {
				posBloqueExplotarX = centroExplosionX;
				posBloqueExplotarY = centroExplosionY;
				
				if(!finLineaBomba[j]) {
					
					switch (j)
					{
						case 0:		
							posBloqueExplotarX = centroExplosionX - i;
							break;
						case 1:
							posBloqueExplotarX = centroExplosionX + i;
							break;
						case 2:
							posBloqueExplotarY = centroExplosionY - i;
							break;
						case 3:
							posBloqueExplotarY = centroExplosionY + i;
							break;
					}
					
					if (   posBloqueExplotarX < 0
						|| posBloqueExplotarX >= esCfg.col
						|| posBloqueExplotarY < 0
						|| posBloqueExplotarY >= esCfg.fil
						|| !matrizTablero[posBloqueExplotarX][posBloqueExplotarY].getPuedeSerExplotado())
					{
						finLineaBomba[j] = true;
					} else {
						if (matrizTablero[posBloqueExplotarX][posBloqueExplotarY].getTipo().equals("BLANDO")) {
							puntos = 10 + puntos;
							if (random.nextDouble()<= 0.10) {
								miEscenarioFacade.generarPowerup(posBloqueExplotarX, posBloqueExplotarY);
							}
						}
						matrizTablero[posBloqueExplotarX][posBloqueExplotarY].romperbloque();
						
						if (!hayFuegoEnPosicionXY(posBloqueExplotarX, posBloqueExplotarY)) {
							listaPosFuegos.add (new int[] {posBloqueExplotarX, posBloqueExplotarY});
						}
						
					}
				}
			}
		}
    	return puntos;
	}
	
	// Genera una matriz con los códigos visuales de los bloques para la representación en pantalla.
	public int[][] generarMatrizAniadirBloques(String pantalla)
	{
		int[][] matrizGenerada = new int[esCfg.col][esCfg.fil];
		
		for (int columna = 0; columna < esCfg.col; columna++) {
			for (int fila = 0; fila < esCfg.fil; fila++) {
				matrizGenerada[columna][fila] = matrizTablero[columna][fila].getCodigoBloque(pantalla);
			}
		}
		
		return matrizGenerada;
	}
	
	public String[] getListaSonidos() {
	    return listaSonidos.toArray(new String[0]);
	}
	
	public void listaSonidosClear() {
		listaSonidos.clear();
	}




	
	
}
