import java.util.Observable;
import java.util.Random;
import java.util.*;



public class Escenario extends Observable{
	private static final int FILAS =13,COLUMNAS =17;
	private static Escenario miEscenario;
	private Entidad[][] tablero;
	private Mov mov=Mov.Q, anterior=Mov.Q;
	private Random random = new Random();
	private Jugador Jseleccionado = new Bomberman_blanco();
	private int posJX=0, posJY=0;
	private Timer timer=null;
	private int cont=1;
	private boolean left=false, right=false, up=false, down=false, bomb=false;
	
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
		getEscenario().Jseleccionado=jugador;
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
		tablero[0][0] =  Jseleccionado;
		
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
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				if (tablero[i][j] instanceof Jugador) {
                    System.out.print(" J ");
                } else if (tablero[i][j] instanceof Bloque) {
                    Bloque bloque = (Bloque) tablero[i][j];
                    switch (bloque.getTipo()) {
                        case DURO:
                            System.out.print(" D ");
                            break;
                        case BLANDO:
                            System.out.print(" B ");
                            break;
                        case VACIO:
                            System.out.print(" . ");
                            break;
                    }
                } else {
                    System.out.print(" ? ");
                }
            }
            System.out.println();
		}
		System.out.println("");
		if (cont%3==0) {
			if(left) {
		    	int posBloque=posJX-1;
		    	if (posBloque!=-1) {
			    	if (tablero[posJY][posBloque].getTipo()!=Tipo.DURO && tablero[posJY][posBloque].getTipo()!=Tipo.BLANDO ) {
			    		tablero[posJY][posJX]=new Bloque(Tipo.VACIO);
			    		tablero[posJY][posBloque]=Jseleccionado;
			    		posJX=posBloque;
			    	}
		    	}
	    	}
			if (up) {
		    	int posBloque=posJY-1;
		    	
		    	if (posBloque!=-1) {
			    	if (tablero[posBloque][posJX].getTipo()!=Tipo.DURO && tablero[posBloque][posJX].getTipo()!=Tipo.BLANDO ) {
			    		tablero[posJY][posJX]=new Bloque(Tipo.VACIO);
			    		tablero[posBloque][posJX]=Jseleccionado;
			    		posJY=posBloque;
			    	}
		    	}
	    	}
			if(right) {
		    	int posBloque=posJX+1;
		    	if (posBloque!=FILAS+1) {
			    	if (tablero[posJY][posBloque].getTipo()!=Tipo.DURO && tablero[posJY][posBloque].getTipo()!=Tipo.BLANDO ) {
			    		tablero[posJY][posJX]=new Bloque(Tipo.VACIO);
			    		tablero[posJY][posBloque]=Jseleccionado;
			    		posJX=posBloque;
			    	}
		    	}
	    	}
			if(down) {
		    	int posBloque=posJY+1;
		    	if (posBloque!=COLUMNAS+1) {
			    	if (tablero[posBloque][posJX].getTipo()!=Tipo.DURO && tablero[posBloque][posJX].getTipo()!=Tipo.BLANDO) {
			    		tablero[posJY][posJX]=new Bloque(Tipo.VACIO);
			    		tablero[posBloque][posJX]=Jseleccionado;
			    		posJY=posBloque;
			    	}
		    	}
	    	}
			if (bomb) {
				
			}
		}
		setChanged();
		notifyObservers(this.tablero);
	}
	
	public Entidad getEntidad(int fila, int colu) {
		Entidad miEntidad = new Entidad();
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
	
    public void getKeyPressed(int key) {

            switch (key) {
	            case 32: mov = Mov.B;
	                    break;
	            case 37: mov = Mov.L;
	                    break;
	            case 38: mov = Mov.U;
	                    break;
	            case 39: mov = Mov.R;
	                    break;
	            case 40: mov = Mov.D;
	                    break;      
	            default: mov = Mov.Q;
            			break;
                  }
            
            if (mov!=anterior) {
                anterior=mov;
                switch (mov) {
	            	case Mov.B: this.pressBomba();
	            			break;
			        case Mov.L: this.pressLeft();
			                break;
			        case Mov.U: this.pressUp();
			                break;
			        case Mov.R: this.pressRight();
			                break;
			        case Mov.D: this.pressDown();
			                break;
			        default: ;
			       			break;
                }
        	}
    	}
    public void getKeyReleased(int key) {
    	
            switch (key) {
                case 32: mov = Mov.B;
                         break;
                case 37: mov = Mov.L;
                        break;
                case 38: mov = Mov.U;
                        break;
                case 39: mov = Mov.R;
                        break;
                case 40: mov = Mov.D;
                        break;
                default: mov = Mov.Q;
                		break;
                    }
            
            if (anterior==mov) {
            	anterior=Mov.Q;
            	switch (mov) {
	            	case Mov.B: this.releaseBomba();
	            			break;
			        case Mov.L: this.releaseLeft();
			                break;
			        case Mov.U: this.releaseUp();
			                break;
			        case Mov.R: this.releaseRight();
			                break;
			        case Mov.D: this.releaseDown();
			                break;
			        default: ;
			       			break;
            	}
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
		        