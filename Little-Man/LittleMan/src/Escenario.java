import java.util.Observable;
import java.util.Random;



public class Escenario extends Observable{
	private static final int FILAS =13,COLUMNAS =17;
	private static Escenario miEscenario;
	private Entidad[][] tablero;
	private Mov mov=Mov.Q, anterior=Mov.Q;
	private Random random = new Random();
	private Jugador Jseleccionado = new Bomberman_blanco();
	
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
				if (i % 2 == 0 && j % 2 == 0) {
					tablero[i][j]= new Bloque(Tipo.DURO); //Poner bloques duros en posiciones pares
				} else {
					if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {
						tablero[i][j]= new Bloque(Tipo.VACIO); // Poner bloques vacios en la esquina superior izquierda para zona de inicio
					} else {
						tablero[i][j]= random.nextBoolean() ? new Bloque(Tipo.VACIO) : new Bloque(Tipo.BLANDO);
					}
				}
			}
		}
		tablero[0][0] =  Jseleccionado;
		setChanged();
		notifyObservers();
	}
	
	private void actualizarEscenario() {
		//TODO Lógica de actualización
		setChanged();
		notifyObservers();
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
    	//TODO
    	System.out.println("presionado Bomba");
    }
    public void pressLeft() {
    	//TODO
    	System.out.println("presionado Left");
    }
    public void pressUp() {
    	//TODO
    	System.out.println("presionado Up");
    }
    public void pressRight() {
    	//TODO
    	System.out.println("presionado Right");
    }
    public void pressDown() {
    	//TODO
    	System.out.println("presionado Down");
    }
    public void releaseBomba() {
    	//TODO
    	System.out.println("soltado Bomba");
    }
    public void releaseLeft() {
    	//TODO
    	System.out.println("soltado Left");
    }
    public void releaseUp() {
    	//TODO
    	System.out.println("soltado Up");
    }
    public void releaseRight() {
    	//TODO
    	System.out.println("soltado Right");
    }
    public void releaseDown() {
    	//TODO
    	System.out.println("soltado Down");
    }
		                   
    
		                
		                
}
		        