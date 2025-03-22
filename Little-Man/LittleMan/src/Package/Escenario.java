package Package;
import java.util.*;



public class Escenario extends Observable{
	private static final int FILAS =13,COLUMNAS =17;
	private static Escenario miEscenario;
	private Bloque[][] tablero;
	private Random random = new Random();
	private Jugador jug = new Bomberman_blanco();
	private Timer timer=null;
	private int cont=1;
	private boolean left=false, right=false, up=false, down=false, bomb=false;
	private ArrayList<Bomba> bombas = new ArrayList<Bomba>();
	private ArrayList<int[]> bloques = new ArrayList<int[]>();

	private Escenario() {
		tablero =new Bloque[COLUMNAS][FILAS];
		inicializarTablero(); // Inicializa el tablero con bloques
	}
	
	public static Escenario getEscenario() {
		if (miEscenario == null) {
			miEscenario = new Escenario();
		}
		return miEscenario;
	}
	
	public void seleccionarJugador(Jugador jugador) {
		getEscenario().jug=jugador;
	}
	
	private void inicializarTablero()
	{
		// Lógica para inicializar bloques en el tablero
		for (int i = 0; i < COLUMNAS; i++) {
			for (int j = 0; j < FILAS; j++) {
				if  ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)){
					tablero[i][j]= new Bloque(Tipo.VACIO); // Poner bloques vacios en la esquina superior izquierda para zona de inicio
					tablero[i][j].setPosX(i);
					tablero[i][j].setPosY(j);

				} else {
					if (i % 2 != 0 && j % 2 != 0) {
						tablero[i][j]= new Bloque(Tipo.DURO); //Poner bloques duros en posiciones impares
						tablero[i][j].setPosX(i);
						tablero[i][j].setPosY(j);
					} else {
						tablero[i][j]= random.nextBoolean() ? new Bloque(Tipo.VACIO) : new Bloque(Tipo.BLANDO);
						tablero[i][j].setPosX(i);
						tablero[i][j].setPosY(j);
					}
				}
			}
		}


		jug.setPosX(0);
		jug.setPosY(0);
		bombas.clear();
		bloques.clear();
		jug.sumarVida();
		setChanged();
		notifyObservers();
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
				actualizarEscenario(); // Actualiza el escenario cada 50ms
				
			}
		};
		timer.scheduleAtFixedRate(task, 0, 50);
	}
	
	private Bomba getBombaEnPosicionXY (int pPosX, int pPosY)				// devuelve la bomba que hay en una posicion XY
	{																		// devuelve null si no hay bomba en esa posicion
		Bomba pBomba;
		
		for (int i=0;i<bombas.size();i++)
		{
			pBomba = bombas.get(i);
			if (pBomba.getPosX()==pPosX && pBomba.getPosY()==pPosY) {
				return pBomba;
			}
		}
		return null;
	}
	
	private boolean hayFuegoEnPosicionXY (int pPosX, int pPosY)
	{
		for (int i=0;i<bloques.size();i++)
		{
			int[] pFuegos = bloques.get(i);
			if (pFuegos[0]==pPosX && pFuegos[1]==pPosY) {
				return true;
			}
		}
		return false;
	}
	
	private void actualizarEscenario() {
		
		int posJX=jug.getPosX();						// posicion X actual del jugador	[COLUMNA]
		int posJY=jug.getPosY();						// posicion Y actual del jugador	[FILA]
		int cordNuevaX, cordNuevaY;						// posiciones XY nuevas despues del movimiento
		boolean movimientoJugadorChocaConParedOBomba;	// flag si choca contra pared en coordenadas nuevas
		
		// Lógica de movimiento del jugador
		if (cont%2==0) {
			cordNuevaX = posJX;
			cordNuevaY = posJY;
			movimientoJugadorChocaConParedOBomba = false;
			
			if (!jug.getEstaMuerto())
			{
				if (bomb) {
					crearBomba(); // Crea una bomba si se presiona la tecla correspondiente
				}
				// Lógica para mover al jugador según la dirección
				if (left) {
					if (posJX != 0)
					{
						cordNuevaX = posJX - 1;
					} else {
						movimientoJugadorChocaConParedOBomba = true;
					}
					
				} else if (right) {
					if (posJX != COLUMNAS - 1)
					{
						cordNuevaX = posJX + 1;
					} else {
						movimientoJugadorChocaConParedOBomba = true;
					}
					
				} else if (up) {
					if (posJY != 0)
					{
						cordNuevaY = posJY - 1;
					} else {
						movimientoJugadorChocaConParedOBomba = true;
					}
					
				} else if (down) {
					if (posJY != FILAS - 1)
					{
						cordNuevaY = posJY + 1;
					} else {
						movimientoJugadorChocaConParedOBomba = true;
					}
					
				}
				
				movimientoJugadorChocaConParedOBomba = 													// Jugador choca contra pared o bomba si
						(  movimientoJugadorChocaConParedOBomba											// Ya chocaba contra el muro exterior
						|| tablero[cordNuevaX][cordNuevaY].getTipo().getJugadorChocaContraCelda()		// o si la posicion es un bloque duro o blando
						|| getBombaEnPosicionXY(cordNuevaX, cordNuevaY)	!= null);						// o si hay una bomba en la nueva posicion
				
				if (!movimientoJugadorChocaConParedOBomba)
				{
					jug.setPosX(cordNuevaX);
					jug.setPosY(cordNuevaY);
				}
			}
		}
		
		actualizarTicksBombas();
		actualizarTicksFuego();
		setChanged();
		notifyObservers(generarMatriz());
	}
	


	private void actualizarTicksFuego()
	{
		for ( int i=0; i<bloques.size(); i++)					// Recorrer la lista de bloques para actualizarlos
		{
			int[] pBloque = bloques.get(i);						// Obtenemos el bloque que queremos actualizar
			if (tablero[pBloque[0]][pBloque[1]].tick())	{		// Comprobamos si al actualizar termina su cuenta atras del fuego
				bloques.remove(i);								// Si la cuenta atras del fuego termina eliminamos el bloque de la lista, ya que no nos hara falta actualizarlo mas.
				i--;											// El elemento i+1 de la lista pasa a ser el elemento i, por lo que para actualizarlo es necesario retroceder un elemento en la lista
			}
			// Si el fuego afecta al jugador, le quita una vida
			if (  tablero[pBloque[0]][pBloque[1]].getPosX()==jug.getPosX()
				&&tablero[pBloque[0]][pBloque[1]].getPosY()==jug.getPosY()) {		// Comprobar si este bloque (que al estar actualizandose sabemos que es fuego), y el jugador estan en la misma posicion      
				jug.gestionarVida();												// Si es asi gestionamos el daño, le quitamos una vida
			}
		}
	}

	private void actualizarTicksBombas()
	{
		for ( int i=0; i<bombas.size(); i++)					// Recorrer la lista de bloques para actualizarlos
		{
			Bomba pBomba = bombas.get(i);						// Obtenemos el bloque que queremos actualizar
			if (pBomba.tick()) {								// Comprobamos si al actualizar termina su cuenta atras para explotar
				jug.bombaExplotada();							// Indica al jugador que la bomba ha explotado
				explosion(i);									// Gestiona la explosion
				bombas.remove(i);								// Eliminamos la bomba de la lista de actualizaciones
				i--;
			}
		}
		
	}

	private void explosion(int pBomb) {
		
		int centroExplosionX=bombas.get(pBomb).getPosX();
		int centroExplosionY=bombas.get(pBomb).getPosY();
		int posBloqueExplotarX, posBloqueExplotarY;
		boolean[] finLineaBomba = new boolean[] {false, false, false, false};
		// Manejo de la explosión en el centro y en las direcciones
		tablero[centroExplosionX][centroExplosionY].romperbloque();
		if (!hayFuegoEnPosicionXY(centroExplosionX, centroExplosionY))
		{
			bloques.add (new int[] {centroExplosionX, centroExplosionY});
		}
		
		for (int i = 1; i <= jug.radioBomba() || finLineaBomba.equals(new boolean[] {false, false, false, false}); i++)
		{
			for (int j = 0; j < 4; j++)
			{
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
						|| !tablero[posBloqueExplotarX][posBloqueExplotarY].getTipo().getPuedeSerExplotado())
					{
						finLineaBomba[j] = true;
					} else {
						tablero[posBloqueExplotarX][posBloqueExplotarY].romperbloque();
						if (!hayFuegoEnPosicionXY(posBloqueExplotarX, posBloqueExplotarY))
						{
							bloques.add (new int[] {posBloqueExplotarX, posBloqueExplotarY});
						}
					}
				}
			}
		}
	}
		
	
	private int[][] generarInversaMatriz(int[][] matrizA)
	{
		int[][] matrizReturn = new int[matrizA[0].length][matrizA.length];
		for (int i = 0; i < matrizA.length; i++)
		{
			for (int j = 0; j < matrizA[i].length; j++)
			{
				matrizReturn[j][i] = matrizA[i][j];
			}
		}
		return matrizReturn;
	}
	

	private int[][] generarMatriz(){
		int[][] casillas = new int[COLUMNAS][FILAS];
		
		// Lógica para generar la matriz del escenario
		for(int i=0;i<COLUMNAS;i++)
		{
			for(int j=0;j<FILAS;j++)
			{
                    switch (tablero[i][j].getTipo())
                    {
                        case DURO:
                            casillas[i][j]=10;
                            break;
                        case BLANDO:
                        	casillas[i][j]=11;
                            break;
                        case VACIO:
                        	casillas[i][j]=12;
                            break;
                        case FUEGO:
                        	casillas[i][j]=13;
                            break;     
                    } 
			}
		}
		
		for (int i=0;i<bombas.size();i++)
		{
			Bomba pBomba = bombas.get(i);
			casillas[pBomba.getPosX()][pBomba.getPosY()]=30;
		}
		
		if (casillas[jug.getPosX()][jug.getPosY()]==30)
		{
			casillas[jug.getPosX()][jug.getPosY()]=21;
		}	else {
			casillas[jug.getPosX()][jug.getPosY()]=20;
		}
		
		if (jug.getEstaMuerto()==true)
		{
			casillas[jug.getPosX()][jug.getPosY()]=22;
		}
		
		return generarInversaMatriz(casillas);
	}
	
	private void crearBomba()
	{
		// Crear bomba si es posible
		if (jug.menosXBombas())											// si el jugador puede poner bombas
		{
			if (bombas.size()==0) {
				bombas.add(new Bomba(jug.duracionBomba(), jug.getPosX(), jug.getPosY()));
				jug.ponerBomba();
			}
			else if ( !(bombas.get(bombas.size()-1).getPosX() == jug.getPosX() && 		// si no es la misma posicion
						bombas.get(bombas.size()-1).getPosY() == jug.getPosY()))		// que la ultima bomba puesta
			{
				bombas.add(new Bomba(jug.duracionBomba(), jug.getPosX(), jug.getPosY()));
				jug.ponerBomba();
			}
		}
	}

	public Entidad getEntidad(int fila, int colu) {
		Entidad miEntidad = null;
		if(fila>=0 && fila<FILAS && colu>=0 && colu < COLUMNAS) {
			miEntidad= tablero[fila][colu];
		}
		return miEntidad;
	}
	
	public void setEntidad(int fila, int colu, Bloque miEntidad) {
		if(fila>=0 && fila<FILAS && colu>=0 && colu < COLUMNAS) {
			tablero[fila][colu]=miEntidad;
			setChanged();
			notifyObservers();
		}
		
		
		
	}
	
	// Métodos para manejar las entradas del jugador
    public void pressBomba() {
    	bomb=true;
    	left=false;
    	right=false;
    	down=false;
    	up=false;
    	
    }
    public void pressLeft() {
    	left=true;
    	right=false;
    	down=false;
    	up=false;
    	bomb=false;

    }
    public void pressUp() {
    	up=true;
    	left=false;
    	right=false;
    	down=false;
    	bomb=false;

    }
    public void pressRight() {
    	right=true;
    	left=false;
    	down=false;
    	up=false;
    	bomb=false;
 
    }
    public void pressDown() {
    	down=true;
    	left=false;
    	right=false;
    	up=false;
    	bomb=false;
 
    }
    public void pressEnter() {
    	
    	//Resetear el juego solo si el jugador está muerto
    	if (jug.getEstaMuerto()) {
    		timer.purge();
    		timer.cancel();
    		inicializarTablero();
    	}
    }
    public void releaseBomba() {
    	bomb=false;

    }
    public void releaseLeft() {
    	left=false;

    }
    public void releaseUp() {
    	up=false;

    }
    public void releaseRight() {
    	right=false;

    }
    public void releaseDown() {
    	down=false;

    }
    public void releaseEnter() {
    	
    }
		                   
    
		                
		                
}
		        