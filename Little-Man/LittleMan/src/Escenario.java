import java.util.*;



public class Escenario extends Observable{
	private static final int FILAS =13,COLUMNAS =17;
	private static Escenario miEscenario;
	private Bloque[][] tablero;
	private Random random = new Random();
	private Jugador jug = new Bomberman_blanco();
	private Timer timer=null;
	private int cont=1;
	private boolean left=false, right=false, up=false, down=false, bomb=false, jugadorMuerto=false;
	private ArrayList<Bomba> bombas = new ArrayList<Bomba>();
	private ArrayList<Bloque> bloques = new ArrayList<Bloque>();

	
	private Escenario() {
		tablero =new Bloque[FILAS][COLUMNAS];
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
					tablero[i][j].setPosX(j);
					tablero[i][j].setPosY(i);

				} else {
					if (i % 2 != 0 && j % 2 != 0) {
						tablero[i][j]= new Bloque(Tipo.DURO); //Poner bloques duros en posiciones impares
						tablero[i][j].setPosX(j);
						tablero[i][j].setPosY(i);
					} else {
						tablero[i][j]= random.nextBoolean() ? new Bloque(Tipo.VACIO) : new Bloque(Tipo.BLANDO);
						tablero[i][j].setPosX(j);
						tablero[i][j].setPosY(i);
					}
				}
			}
		}

		
		//Todo a partir de aquí es solo pruebas
		
			
		
		//Aquí ya no son pruebas
		jug.setPosX(0);
		jug.setPosY(0);
		bombas.clear();
		bloques.clear();
		jugadorMuerto=false;
		jug.sumarVida();
		setChanged();
		notifyObservers();
		timerStep();
	}
	private void timerStep() {
		timer = new Timer();
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
		if (cont%2==0) {
			if(left) {
		    	int posBloque=posJX-1;
		    	if (posBloque!=-1) {
		    		boolean noPasarL=false;
		    		for (int i=0;i<bombas.size();i++) {
		    			Bomba pBomba = bombas.get(i);
		    			if (pBomba.getPosX()==posBloque && pBomba.getPosY()==posJY) {
		    				noPasarL=true;
		    			}
		    		}
			    	if (jugadorMuerto==false && noPasarL==false && tablero[posJY][posBloque].getTipo()!=Tipo.DURO && tablero[posJY][posBloque].getTipo()!=Tipo.BLANDO ) {
			    		jug.setPosX(posBloque);
			    	}
		    	}
	    	}
			if (up) {
		    	int posBloque=posJY-1;
		    	
		    	if (posBloque!=-1) {
		    		boolean noPasarU=false;
		    		for (int i=0;i<bombas.size();i++) {
		    			Bomba pBomba = bombas.get(i);
		    			if (pBomba.getPosX()==posJX && pBomba.getPosY()==posBloque) {
		    				noPasarU=true;
		    			}
		    		}
			    	if (jugadorMuerto==false && noPasarU==false && tablero[posBloque][posJX].getTipo()!=Tipo.DURO && tablero[posBloque][posJX].getTipo()!=Tipo.BLANDO ) {
			    		jug.setPosY(posBloque);
			    	}
		    	}
	    	}
			if(right) {
		    	int posBloque=posJX+1;
		    	if (posBloque!=COLUMNAS) {
		    		boolean noPasarR=false;
		    		for (int i=0;i<bombas.size();i++) {
		    			Bomba pBomba = bombas.get(i);
		    			if (pBomba.getPosX()==posBloque && pBomba.getPosY()==posJY) {
		    				noPasarR=true;
		    			}
		    		}
			    	if (jugadorMuerto==false && noPasarR==false && tablero[posJY][posBloque].getTipo()!=Tipo.DURO && tablero[posJY][posBloque].getTipo()!=Tipo.BLANDO ) {
			    		jug.setPosX(posBloque);
			    	}
		    	}
	    	}
			if(down) {
		    	int posBloque=posJY+1;
		    	if (posBloque!=FILAS) {
		    		boolean noPasarD=false;
		    		for (int i=0;i<bombas.size();i++) {
		    			Bomba pBomba = bombas.get(i);
		    			if (pBomba.getPosX()==posJX && pBomba.getPosY()==posBloque) {
		    				noPasarD=true;
		    			}
		    		}
			    	if (jugadorMuerto==false && noPasarD==false && tablero[posBloque][posJX].getTipo()!=Tipo.DURO && tablero[posBloque][posJX].getTipo()!=Tipo.BLANDO) {
			    		jug.setPosY(posBloque);
			    	}
		    	}
	    	}
			if (bomb) {
				if(jugadorMuerto==false) {
					crearBomba();
				}
			}
		}
		mirarBombas();
		mirarFuego();
		setChanged();
		notifyObservers(generarMatriz());
	}
	


	private void mirarFuego() {
		for (int i=0;i<bloques.size();i++) {
			Bloque pBloque = bloques.get(i);
			if (pBloque.tick()) {
				bloques.remove(i);
			}
			if (pBloque.getPosX()==jug.getPosX()&&pBloque.getPosY()==jug.getPosY()) {
				if(jug.gestionarVida()) {
					jugadorMuerto=true;	
				}
			}
		}
		
	}

	private void mirarBombas() {
		for (int i=0;i<bombas.size();i++) {
			Bomba pBomba = bombas.get(i);
			if (pBomba.tick()) {
				jug.bombaExplotada();
				explosion(i);
				bombas.remove(i);
			}
		}
		
	}

	private void explosion(int pBomb) {
		boolean seguirExplosionR=true;
		boolean seguirExplosionL=true;
		boolean seguirExplosionU=true;
		boolean seguirExplosionD=true;
		for (int i=0; i<jug.radioBomba()+1;i++) {
			int centroExplosionY=bombas.get(pBomb).getPosY();
			int centroExplosionX=bombas.get(pBomb).getPosX();
			if(i==0) {
				if(tablero[centroExplosionY][centroExplosionX].romperbloque()) {
					bloques.add(tablero[centroExplosionY][centroExplosionX]);
					
				}
			} else {
				
				if(seguirExplosionR && centroExplosionX+i<COLUMNAS && centroExplosionX+i>-1) {
					if(tablero[centroExplosionY][centroExplosionX+i].romperbloque()) {
						bloques.add(tablero[centroExplosionY][centroExplosionX+i]);
						
					}
					else {
						seguirExplosionR=false;
					}
				}
				if(seguirExplosionL && centroExplosionX-i>-1 && centroExplosionX-i<COLUMNAS) {
					if(tablero[centroExplosionY][centroExplosionX-i].romperbloque()) {
						bloques.add(tablero[centroExplosionY][centroExplosionX-i]);
					}
					else {
						seguirExplosionL=false;
					}
				}
				if(seguirExplosionU && centroExplosionY+i<FILAS && centroExplosionY+i>-1) {
					if(tablero[centroExplosionY+i][centroExplosionX].romperbloque()) {
						bloques.add(tablero[centroExplosionY+i][centroExplosionX]);
					}
					else {
						seguirExplosionU=false;
					}
				}
				if(seguirExplosionD && centroExplosionY-i>-1 && centroExplosionY-i<FILAS) {
					if(tablero[centroExplosionY-i][centroExplosionX].romperbloque()) {
						bloques.add(tablero[centroExplosionY-i][centroExplosionX]);
					}
					else {
						seguirExplosionD=false;
					}
				}
			}
		}
		
		
	}

	private int[][] generarMatriz(){
		int[][] casillas = new int[FILAS][COLUMNAS];
		for(int i=0;i<FILAS;i++) {
			for(int j=0;j<COLUMNAS;j++) {
                    switch (tablero[i][j].getTipo()) {
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
		for (int i=0;i<bombas.size();i++) {
			Bomba pBomba = bombas.get(i);
			casillas[pBomba.getPosY()][pBomba.getPosX()]=30;
		}
		if (casillas[jug.getPosY()][jug.getPosX()]==30) {
			casillas[jug.getPosY()][jug.getPosX()]=21;
		}	else {
				casillas[jug.getPosY()][jug.getPosX()]=20;
			}
		if (jugadorMuerto==true) {
			casillas[jug.getPosY()][jug.getPosX()]=22;
		}
		
		
		return casillas;
	}
	private int[][] generarPosBombas(){
		int[][] posBombas = new int[bombas.size()][2];
			for(int i=0;i<bombas.size();i++) {

				posBombas[i][0]=bombas.get(i).getPosX();
				posBombas[i][1]=bombas.get(i).getPosY();
					
			}
		return posBombas;
		}
		
	private void crearBomba()
	{
		if (jug.menosXBombas())											// si el jugador puede poner bombas
		{
			if (bombas.size()==0) {
				bombas.add(new Bomba(jug.duracionBomba(), jug.getPosX(), jug.getPosY()));
				jug.ponerBomba();
			}
			else if (!(bombas.get(bombas.size()-1).getPosX() == jug.getPosX() && 		// si no es la misma posicion
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
    	if (jugadorMuerto) {
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
		        