package Package;
import java.util.*;



public class Escenario extends Observable{

    private Random random = new Random();

    private static Escenario miEscenario;
    private static final int FILAS = 11,COLUMNAS = 17;

    private Timer timer=null;
    private int cont=1;
    private boolean left=false, right=false, up=false, down=false, bomb=false;

    private Bloque[][] matrizTableroBloques;
    private EntidadMovibleJugador jug = EntidadMovibleJugadorFactory.getEntidadMovibleJugadorFactory().generate("BLANCO");
    private ArrayList<EntidadInamovibleBomba> listaTickBombas = new ArrayList<EntidadInamovibleBomba>();
    private ArrayList<int[]> listaTickPosBloquesFuego = new ArrayList<int[]>();


    private Escenario() {
        matrizTableroBloques =new Bloque[COLUMNAS][FILAS];
        inicializarTablero();
    }

    private void seleccionarJugador(String tipoJugador) { jug = EntidadMovibleJugadorFactory.getEntidadMovibleJugadorFactory().generate(tipoJugador); }

    public static Escenario getEscenario() {
        if (miEscenario == null) {
            miEscenario = new Escenario();
        }
        return miEscenario;
    }

    public static Escenario getEscenario(String playerTipo) {
        if (miEscenario == null) {
            miEscenario = new Escenario();
            miEscenario.seleccionarJugador(playerTipo);
        }
        return miEscenario;
    }
	
	private void inicializarTablero()
	{
		for (int i = 0; i < COLUMNAS; i++) {
			for (int j = 0; j < FILAS; j++) {
				if  ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)){
					matrizTableroBloques[i][j]= new Bloque("VACIO"); // Poner bloques vacios en la esquina superior izquierda para zona de inicio
					matrizTableroBloques[i][j].setPosX(i);
					matrizTableroBloques[i][j].setPosY(j);

				} else {
					if (i % 2 != 0 && j % 2 != 0) {
						matrizTableroBloques[i][j]= new Bloque("DURO"); //Poner bloques duros en posiciones impares
						matrizTableroBloques[i][j].setPosX(i);
						matrizTableroBloques[i][j].setPosY(j);
					} else {
						matrizTableroBloques[i][j]= random.nextBoolean() ? new Bloque("VACIO") : new Bloque("BLANDO");
						matrizTableroBloques[i][j].setPosX(i);
						matrizTableroBloques[i][j].setPosY(j);
					}
				}
			}
		}

		jug.setPosX(0);
		jug.setPosY(0);
		
		listaTickBombas.clear();
		listaTickPosBloquesFuego.clear();
		jug.sumarVida();
		timerStep();
	}
	
	private void timerStep()
	{
		timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				cont++;
				if (cont==121) {
					cont=1;
				}
				actualizarEscenario();
				
			}
		};
		timer.scheduleAtFixedRate(task, 0, 50);
	}
	
	private void actualizarEscenario()
	{
		actualizarPosicionJugador();
		actualizarTicksBombas();
		actualizarTicksFuego();
		setChanged();
		notifyObservers(generarMatriz());
	}
	
	private void actualizarPosicionJugador()
	{
		int posJX=jug.getPosX();						// posicion X actual del jugador	[COLUMNA]
		int posJY=jug.getPosY();						// posicion Y actual del jugador	[FILA]
		int posJXnew, posJYnew;							// posiciones XY nuevas despues del movimiento
		boolean movimientoJugadorChocaConParedOBomba;	// flag si choca contra pared en coordenadas nuevas
		
		if (cont%2==0)
		{
			posJXnew = posJX;
			posJYnew = posJY;
			movimientoJugadorChocaConParedOBomba = false;
			
			if (!jug.getEstaMuerto()) {
				if (bomb) { crearBomba(); }
				
				posJXnew = (left  && posJX != 0			  ) ? posJX - 1 : posJXnew;
				posJXnew = (right && posJX != COLUMNAS - 1) ? posJX + 1 : posJXnew;
				posJYnew = (up    && posJY != 0			  ) ? posJY - 1 : posJYnew;
				posJYnew = (down  && posJY != FILAS - 1   ) ? posJY + 1 : posJYnew;
				
				movimientoJugadorChocaConParedOBomba = 													// Jugador choca contra pared o bomba si
						(  ( left  && posJX == 0			)											// Choca contra exterior
						|| ( right && posJX == COLUMNAS - 1	)
						|| ( up    && posJY == 0			)
						|| ( down  && posJY == FILAS - 1	)
						|| matrizTableroBloques[posJXnew][posJYnew].getJugadorChocaContraCelda()		// o si la posicion es un bloque duro o blando
						|| getBombaEnPosicionXY(posJXnew, posJYnew)	!= null);							// o si hay una bomba en la nueva posicion
				
				if (!movimientoJugadorChocaConParedOBomba) {
					jug.setPosX(posJXnew);
					jug.setPosY(posJYnew);
				}
			}
		}
	}

	private void actualizarTicksFuego() {
		for ( int i=0; i<listaTickPosBloquesFuego.size(); i++) {
			int[] pBloque = listaTickPosBloquesFuego.get(i);				// Obtenemos el bloque que queremos actualizar
			
			if (matrizTableroBloques[pBloque[0]][pBloque[1]].tick()) {		// Comprobamos si al actualizar termina su cuenta atras del fuego
				listaTickPosBloquesFuego.remove(i);							// Si la cuenta atras del fuego termina eliminamos el bloque de la lista, ya que no nos hara falta actualizarlo mas.
				i--;														// El elemento i+1 de la lista pasa a ser el elemento i, por lo que para actualizarlo es necesario retroceder un elemento en la lista
			}
			
			if ( pBloque[0]==jug.getPosX() && pBloque[1]==jug.getPosY()) { jug.gestionarVida();	} // Si el jugador esta en la misma posicion que el fuego entonces se le gestiona el daño al jugador
		}
	}

	private void actualizarTicksBombas() {
		for ( int i=0; i<listaTickBombas.size(); i++) {
			EntidadInamovibleBomba pBomba = listaTickBombas.get(i);			// Obtenemos el bloque que queremos actualizar
			
			if (pBomba.tick()) {								// Comprobamos si al actualizar termina su cuenta atras para explotar
				jug.bombaExplotada();							// Indica al jugador que la bomba ha explotado
				explosion(i);									// Gestiona la explosion
				listaTickBombas.remove(i);						// Eliminamos la bomba de la lista de actualizaciones
				i--;											// El elemento i+1 de la lista pasa a ser el elemento i, por lo que para actualizarlo es necesario retroceder un elemento en la lista
			}
		}
	}
	
	private int[][] generarMatriz(){
		int[][] casillas = new int[COLUMNAS][FILAS];
		
		for(int i=0;i<COLUMNAS;i++){
			for(int j=0;j<FILAS;j++){
            	casillas[i][j]=matrizTableroBloques[i][j].getCodigoBloque();
			}
		}
		
		for (EntidadInamovibleBomba pBomba : listaTickBombas) {
			casillas[pBomba.getPosX()][pBomba.getPosY()]=30;
		}
		
		casillas[jug.getPosX()][jug.getPosY()] = (casillas[jug.getPosX()][jug.getPosY()]==30) ? jug.getCodigoJugador()+1 : ((jug.getEstaMuerto()) ? 22 : jug.getCodigoJugador());
		
		return casillas;
	}
	
	private EntidadInamovibleBomba getBombaEnPosicionXY (int pPosX, int pPosY)				// devuelve la bomba que hay en una posicion XY
	{																						// devuelve null si no hay bomba en esa posicion
		for (EntidadInamovibleBomba pBomba: listaTickBombas) {
			if (pBomba.getPosX()==pPosX && pBomba.getPosY()==pPosY) { return pBomba; }
		}
		return null;
	}
	
	private boolean hayFuegoEnPosicionXY (int pPosX, int pPosY)
	{
		for (int[] pFuegos : listaTickPosBloquesFuego) {
			if (pFuegos[0]==pPosX && pFuegos[1]==pPosY) { return true; }
		}
		return false;
	}

	private void explosion(int pBomb) {
		
		int centroExplosionX=listaTickBombas.get(pBomb).getPosX();
		int centroExplosionY=listaTickBombas.get(pBomb).getPosY();
		int posBloqueExplotarX, posBloqueExplotarY;
		boolean[] finLineaBomba = new boolean[] {false, false, false, false};
		
		matrizTableroBloques[centroExplosionX][centroExplosionY].romperbloque();
		if (!hayFuegoEnPosicionXY(centroExplosionX, centroExplosionY))
		{
			listaTickPosBloquesFuego.add (new int[] {centroExplosionX, centroExplosionY});
		}
		
		for (int i = 1; i <= jug.radioBomba() && !finLineaBomba.equals(new boolean[] {true, true, true, true}); i++) {
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
						|| !matrizTableroBloques[posBloqueExplotarX][posBloqueExplotarY].getPuedeSerExplotado())
					{
						finLineaBomba[j] = true;
					} else {
						matrizTableroBloques[posBloqueExplotarX][posBloqueExplotarY].romperbloque();
						if (!hayFuegoEnPosicionXY(posBloqueExplotarX, posBloqueExplotarY)) {
							listaTickPosBloquesFuego.add (new int[] {posBloqueExplotarX, posBloqueExplotarY});
						}
					}
				}
			}
		}
	}

	private void crearBomba()
	{
		if (jug.puedePonerBombas()) {
			if (listaTickBombas.size()==0) {
				listaTickBombas.add(new EntidadInamovibleBomba(jug.duracionBomba(), jug.getPosX(), jug.getPosY()));
				jug.ponerBomba();
			} else if ( !(listaTickBombas.get(listaTickBombas.size()-1).getPosX() == jug.getPosX() && 		// si no es la misma posicion
						  listaTickBombas.get(listaTickBombas.size()-1).getPosY() == jug.getPosY()))		// que la ultima bomba puesta
			{
				listaTickBombas.add(new EntidadInamovibleBomba(jug.duracionBomba(), jug.getPosX(), jug.getPosY()));
				jug.ponerBomba();
			}
		}
	}

	
	
	
    public void pressLeft() {
    	left	=true;
    	right =false;
    	up	  =false;
    	down  =false;
    	bomb  =false;
    }
    
    public void pressRight() {
    	left  =false;
    	right	=true;
    	up	  =false;
    	down  =false;
    	bomb  =false;
    }
    
    public void pressUp() {
    	left  =false;
    	right =false;
    	up		=true;
    	down  =false;
    	bomb  =false;
    }
    
    public void pressDown() {
    	left  = false;
    	right = false;
    	up	  = false;
    	down	= true;
    	bomb  = false;
    }
    
    public void pressEnter() {
    	if (jug.getEstaMuerto()) {
    		timer.purge();
    		timer.cancel();
    		inicializarTablero();
    	}
    }
    
    public void pressBomba() {
    	left  =false;
    	right =false;
    	up	  =false;
    	down  =false;
    	bomb	=true;
    }
    
    public void releaseBomba()	{ bomb	= false;	}
    public void releaseLeft()	{ left	= false;	}
    public void releaseUp()		{ up	= false;	}
    public void releaseRight()	{ right	= false;	}
    public void releaseDown()	{ down	= false;	}
    public void releaseEnter()	{ }
	
    public int getCOLUMNAS () { return COLUMNAS; }
    public int getFILAS () { return FILAS; }
    
		                
		                
}
		        