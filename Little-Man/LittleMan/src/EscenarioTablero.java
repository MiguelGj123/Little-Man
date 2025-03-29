import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.IOException;

public class EscenarioTablero {
	
    private Random random = new Random();
	
	private static EscenarioTablero miTablero;
    private static final int FILAS = 11,COLUMNAS = 17, NIVEL =5;
    
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
    
    
	public void inicializarTablero()
	{
		matrizTablero = new EntidadInamovibleBloque[COLUMNAS][FILAS];
		listaPosFuegos.clear();
		
		for (int columna = 0; columna < COLUMNAS; columna++) {
			for (int fila = 0; fila < FILAS; fila++) {
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
	
	public ArrayList<int[]> getPosicionesVacias() {
		ArrayList<int[]> posicionesVacias = new ArrayList<>();
		
		for (int columna = 0; columna < COLUMNAS; columna++) {
			for (int fila = 0; fila < FILAS; fila++) {
				if (matrizTablero[columna][fila].getTipo().equals("VACIO")) {
					posicionesVacias.add(new int[] {columna, fila});
				}
			}
		}
		return posicionesVacias;
	}
	
	public boolean hayFuegoEnPosicionXY (int pPosX, int pPosY)
	{
		for (int[] pFuegos : listaPosFuegos) {
			if (pFuegos[0]==pPosX && pFuegos[1]==pPosY) { return true; }
		}
		return false;
	}
	
	public boolean getChocaContraCelda(int posX, int posY) { return matrizTablero[posX][posY].getChocaContraCelda(); }
	
	
	public void actualizarTicksFuego() {
		
		for ( int i=0; i<listaPosFuegos.size(); i++) {
			int[] pBloque = listaPosFuegos.get(i);														// Obtenemos el bloque que queremos actualizar
			
			if (matrizTablero[pBloque[0]][pBloque[1]].tick()) {											// Comprobamos si al actualizar termina su cuenta atras del fuego
				listaPosFuegos.remove(i);																// Si la cuenta atras del fuego termina eliminamos el bloque de la lista, ya que no nos hara falta actualizarlo mas.
				i--;																					// El elemento i+1 de la lista pasa a ser el elemento i, por lo que para actualizarlo es necesario retroceder un elemento en la lista
			}
			
			Escenario.getEscenario().gestionarFuego(pBloque[0],pBloque[1]);								// El escenario se encarga de notificar a los jugadores y enemigos que en esa posicion mueren
		}
	}
	
	public void gestionarExplosion(int centroExplosionX, int centroExplosionY, int radioBomba) {

		int posBloqueExplotarX, posBloqueExplotarY;
		boolean[] finLineaBomba = new boolean[] {false, false, false, false};
		listaSonidos.add(SonidoCodigos.getSonidoCodigos().getCodigoPararSonido(SonidoCodigosEnum.BOMB_EXPLODE));
		listaSonidos.add(SonidoCodigos.getSonidoCodigos().getCodigoSonarSonido(SonidoCodigosEnum.BOMB_EXPLODE));
		
		
		
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
						case 0:		// izquierda
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
						|| posBloqueExplotarX >= COLUMNAS
						|| posBloqueExplotarY < 0
						|| posBloqueExplotarY >= FILAS
						|| !matrizTablero[posBloqueExplotarX][posBloqueExplotarY].getPuedeSerExplotado())
					{
						finLineaBomba[j] = true;
					} else {
						matrizTablero[posBloqueExplotarX][posBloqueExplotarY].romperbloque();
						if (!hayFuegoEnPosicionXY(posBloqueExplotarX, posBloqueExplotarY)) {
							listaPosFuegos.add (new int[] {posBloqueExplotarX, posBloqueExplotarY});
						}
					}
				}
			}
		}
	}
	
	public int[][][] generarMatrizAniadirBloques()
	{
		int[][][] matrizGenerada = new int[COLUMNAS][FILAS][NIVEL];
		
		for (int columna = 0; columna < COLUMNAS; columna++) {
			for (int fila = 0; fila < FILAS; fila++) {
				matrizGenerada[columna][fila][1] = matrizTablero[columna][fila].getCodigoBloque();
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
