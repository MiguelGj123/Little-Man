import java.util.Observable;
import java.util.Random;
import java.util.*;



public class Escenario extends Observable{
	private static final int FILAS =13,COLUMNAS =17;
	private static Escenario miEscenario;
	private Entidad[][] tablero;
	private Mov mov=Mov.Q, anterior=Mov.Q;
	private Random random = new Random();
	private Jugador jug = new Bomberman_blanco();
	private Timer timer=null;
	private int cont=1;
	private boolean left=false, right=false, up=false, down=false, bomb=false;
	private ArrayList<Bomba> bombas = new ArrayList<Bomba>();
	private ArrayList<int[]> posBloques = new ArrayList<int[]>();

	
	private Escenario() {
		tablero =new Entidad[FILAS][COLUMNAS];
		inicializarTablero();
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
	
	private void inicializarTablero() {
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				if  ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)){
					tablero[i][j]= new Bloque(Tipo.VACIO); // Poner bloques vacios en la esquina superior izquierda para zona de inicio
				} else {
					if (i % 2 != 0 && j % 2 != 0) {
						tablero[i][j]= new Bloque(Tipo.DURO); //Poner bloques duros en posiciones impares
					} else {
						tablero[i][j]= random.nextBoolean() ? new Bloque(Tipo.VACIO) : new Bloque(Tipo.BLANDO);
					}
				}
			}
		}
		tablero[0][0] =  jug;
		
		//Todo a partir de aquí es solo pruebas
		
			
		
		//Aquí ya no son pruebas
		setChanged();
		notifyObservers();
		timerStep();
	}
	private void timerStep() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				//System.out.println("Tick:"+cont);
				cont++;
				if (cont==121) {
					cont=1;
				}
				actualizarEscenario();
				
			}
		};
		timer.scheduleAtFixedRate(task, 0, 50);
				
				
	}
	private void actualizarEscenario() {
		int posJX=jug.getPosX();
		int posJY=jug.getPosY();
		if (cont%3==0) {
			if(left) {
		    	int posBloque=posJX-1;
		    	if (posBloque!=-1) {
			    	if (tablero[posJY][posBloque].getTipo()!=Tipo.DURO && tablero[posJY][posBloque].getTipo()!=Tipo.BLANDO ) {
			    		tablero[posJY][posJX]=new Bloque(Tipo.VACIO);
			    		tablero[posJY][posBloque]=jug;
			    		jug.setPosX(posBloque);
			    	}
		    	}
	    	}
			if (up) {
		    	int posBloque=posJY-1;
		    	
		    	if (posBloque!=-1) {
			    	if (tablero[posBloque][posJX].getTipo()!=Tipo.DURO && tablero[posBloque][posJX].getTipo()!=Tipo.BLANDO ) {
			    		tablero[posJY][posJX]=new Bloque(Tipo.VACIO);
			    		tablero[posBloque][posJX]=jug;
			    		jug.setPosY(posBloque);
			    	}
		    	}
	    	}
			if(right) {
		    	int posBloque=posJX+1;
		    	if (posBloque!=FILAS+1) {
			    	if (tablero[posJY][posBloque].getTipo()!=Tipo.DURO && tablero[posJY][posBloque].getTipo()!=Tipo.BLANDO ) {
			    		tablero[posJY][posJX]=new Bloque(Tipo.VACIO);
			    		tablero[posJY][posBloque]=jug;
			    		jug.setPosX(posBloque);
			    	}
		    	}
	    	}
			if(down) {
		    	int posBloque=posJY+1;
		    	if (posBloque!=COLUMNAS+1) {
			    	if (tablero[posBloque][posJX].getTipo()!=Tipo.DURO && tablero[posBloque][posJX].getTipo()!=Tipo.BLANDO) {
			    		tablero[posJY][posJX]=new Bloque(Tipo.VACIO);
			    		tablero[posBloque][posJX]=jug;
			    		jug.setPosY(posBloque);
			    	}
		    	}
	    	}
			if (bomb) {
				
			}
		}
		ArrayList<Integer> casillas = generarMatriz();
		setChanged();
		notifyObservers(casillas);
	}
	
	private ArrayList<Integer> generarMatriz(){
		ArrayList<Integer> casillas = new ArrayList<Integer>();
		for(int i=0;i<FILAS;i++) {
			for(int j=0;j<COLUMNAS;j++) {
				if (tablero[i][j] instanceof Jugador) {
                    casillas.add(20);
                } else if (tablero[i][j] instanceof Bloque) {
                    Bloque bloque = (Bloque) tablero[i][j];
                    switch (bloque.getTipo()) {
                        case DURO:
                            casillas.add(10);
                            break;
                        case BLANDO:
                        	casillas.add(11);
                            break;
                        case VACIO:
                        	casillas.add(12);
                            break;
                    }
                }
			}
		}
		return casillas;
	}
	private void crearBomba()
	{
		if (jug.menosXBombas())											// si el jugador puede poner bombas
		{
			if (!(bombas.get(bombas.size()-1).getPosX() == jug.getPosX() && 		// si no es la misma posicion
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
	
	public void setEntidad(int fila, int colu, Entidad miEntidad) {
		if(fila>=0 && fila<FILAS && colu>=0 && colu < COLUMNAS) {
			tablero[fila][colu]=miEntidad;
			setChanged();
			notifyObservers();
		}
		
		
		
	}
	
   
    public void pressBomba() {
    	bomb=true;
    	left=false;
    	right=false;
    	down=false;
    	up=false;
    	
    	System.out.println("presionado Bomba");
    	
    	
    }
    public void pressLeft() {
    	left=true;
    	right=false;
    	down=false;
    	up=false;

    	
    	System.out.println("presionado Left");
    }
    public void pressUp() {
    	up=true;
    	left=false;
    	right=false;
    	down=false;

    	
    	System.out.println("presionado Up");
    }
    public void pressRight() {
    	right=true;
    	left=false;
    	down=false;
    	up=false;
 
    	
    	System.out.println("presionado Right"+tablero.length);
    }
    public void pressDown() {
    	down=true;
    	left=false;
    	right=false;
    	up=false;
 
    	
    	
    	System.out.println("presionado Down");
    }
    public void releaseBomba() {
    	bomb=false;
    	System.out.println("soltado Bomba");
    }
    public void releaseLeft() {
    	left=false;
    	System.out.println("soltado Left");
    }
    public void releaseUp() {
    	up=false;
    	System.out.println("soltado Up");
    }
    public void releaseRight() {
    	right=false;
    	System.out.println("soltado Right");
    }
    public void releaseDown() {
    	down=false;
    	System.out.println("soltado Down");
    }
		                   
    
		                
		                
}
		        