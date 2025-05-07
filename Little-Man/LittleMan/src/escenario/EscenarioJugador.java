package escenario;

import java.util.ArrayList;

import entidad.EntidadMovibleJugador;
import entidad.EntidadMovibleJugadorFactory;
import sonido.SonidoCodigo;


public class EscenarioJugador {
	
	private Escenario_CONFIG esCfg = new Escenario_CONFIG();
	
	private static EscenarioJugador miJugador;
	private EscenarioFacade miEscenarioFacade;
    private EntidadMovibleJugador jug;
    private int lastDir = 0;
    private int contadorDano;
    private boolean visible;
    private boolean invencible;
    private boolean sfx=true, win=false, golpeable=true;
    private ArrayList<String> listaSonidos = new ArrayList<String>();
    
    private EscenarioJugador() {}
    
    public static EscenarioJugador getJugador(){
    	if (miJugador == null) {
    		miJugador = new EscenarioJugador();
    	}
    	return miJugador;
    }
	
    
	
	public void inicializarJugador(String playerTipo){
		sfx = true;
		win = false;
		golpeable = true;
		visible = true;
		contadorDano = 0;
		jug = EntidadMovibleJugadorFactory.getEntidadMovibleJugadorFactory().generate(playerTipo, 0, 0);
		listaSonidos.add(SonidoCodigo.MUSIC.sonar());
		miEscenarioFacade = EscenarioFacade.getEscenarioFacade();
	}

	public void actualizarPosicionJugador(String[] mov)
	{
		boolean movimientoRealizado = false;
		
		if (mov != null) {
			for (int i = 0; i < mov.length && !movimientoRealizado; i++) {
				int posJX = jug.getPosX();																				// posicion X actual del jugador	[COLUMNA]
				int posJY = jug.getPosY();																				// posicion Y actual del jugador	[FILA]
				int posJXnew = posJX, posJYnew = posJY;																	// posiciones XY nuevas despues del movimiento
				boolean movimientoJugadorChocaConParedOBomba = false;													// flag si choca contra pared en coordenadas nuevas
				
				if (!jug.getEstaMuerto() && mov[i] != null) {
					if (mov[i].equals("L") && posJX != 0				) { posJXnew = posJX - 1; lastDir = 1; /*System.out.println("JJJ " + lastDir);*/ }
					if (mov[i].equals("R") && posJX != esCfg.col - 1	) { posJXnew = posJX + 1; lastDir = 2; /*System.out.println("JJJ " + lastDir);*/ }
					if (mov[i].equals("U") && posJY != 0				) { posJYnew = posJY - 1; lastDir = 3; /*System.out.println("JJJ " + lastDir);*/ }
					if (mov[i].equals("D") && posJY != esCfg.fil - 1	) { posJYnew = posJY + 1; lastDir = 4; /*System.out.println("JJJ " + lastDir);*/ }
				
					movimientoJugadorChocaConParedOBomba = 																// Jugador choca contra pared o bomba si
							(  ( mov[i].equals("L") && posJX == 0				)											// Choca contra exterior
							|| ( mov[i].equals("R") && posJX == esCfg.col - 1	)
							|| ( mov[i].equals("U") && posJY == 0				)
							|| ( mov[i].equals("D") && posJY == esCfg.fil - 1		)
							|| miEscenarioFacade.chocaConPos(posJXnew, posJYnew));					// o si hay una bomba o bloque en la nueva posicion
		
						
					
					if (!movimientoJugadorChocaConParedOBomba && (posJX != posJXnew || posJY != posJYnew)) {
						listaSonidos.add(SonidoCodigo.WALK.sonar());
						if (posJXnew==posJX-1) {
							jug.setCodigoMov(2);
						}
						if (posJXnew==posJX+1) {
							jug.setCodigoMov(4);
						}
						if (posJYnew==posJY-1) {
							jug.setCodigoMov(3);
						}
						if (posJYnew==posJY+1) {
							jug.setCodigoMov(1);
						}
						jug.setPosX(posJXnew);
						jug.setPosY(posJYnew);
						movimientoRealizado = true;
					}
				}
			}
		}
	}
	
	public void actualizarTicksJugador() {

		if (golpeable==false && jug.tick() && !invencible) {
			golpeable=true;
			jug.resetTick();
		}
		
		if (contadorDano==0&&golpeable==false) {
			if (!invencible) {
				contadorDano=40;
			}
			invencible=false;
		}
		if (contadorDano > 0 && !getWin() && !getEstaMuerto()) {
			contadorDano--;
			if (contadorDano%5 == 0) visible = !visible;
			if (contadorDano == 0) visible = true;
		} 

	}
	
	public void gestionarInvencibilidad() {
		golpeable=false;
		invencible=true;
		contadorDano=200;
	}
	
	public int gestionarFuego(int posFX, int posFY) {
		int puntos = 0;
		if (getPosX() == posFX && getPosY() == posFY) { puntos = gestionarVida(); }
		return puntos;
	}
	
	public int gestionarEnemigo(int posEX, int posEY) {
    	int puntos = 0;
		if (getPosX() == posEX && getPosY() == posEY) { puntos=gestionarVida();}
		return puntos;
	}
	
	
	
	public int[][] generarMatrizAniadirjugador(boolean sobreBomba){
		int[][] matrizGenerada = new int[esCfg.col][esCfg.fil];
		if (visible) {
			int codigoJugador;
			
			if (win) codigoJugador = jug.getCodigoJugadorVictoria();
			else if (jug.getEstaMuerto()) codigoJugador = jug.getCodigoJugadorMuerto();
			else if (sobreBomba) codigoJugador = jug.getCodigoJugadorConBomba();
			else codigoJugador = jug.getCodigoJugador()*10 + lastDir;
			
			matrizGenerada[getPosX()][getPosY()] = codigoJugador;
		}
		return matrizGenerada;
	}
	
	public String[] getListaSonidos() {
	    return listaSonidos.toArray(new String[0]);
	}
	
	public void listaSonidosClear() {
		listaSonidos.clear();
	}
	

	public void setWin() {
		win=true;
		if (sfx) {
			sfx=false;
			
			listaSonidos.add(SonidoCodigo.MUSIC.parar());
			listaSonidos.add(SonidoCodigo.WIN.sonar());
		}
	}
	
	public void resetWin() {win=false;}
	public boolean getWin() {return win;}
	public int getPosX() { return jug.getPosX(); }
	public int getPosY() { return jug.getPosY(); }
	public int getCodigoMov() { return jug.getCodigoMov(); }
	public int getVidas() { return jug.getVidas(); }
	public int getNumBombas() { return jug.getNumBombas(); }
	public boolean getPuedePonerBomba() { return jug.puedePonerBombas(); }
	public boolean getEstaMuerto() { return jug.getEstaMuerto(); }
	public void gestionarTiempo() {
		java.util.stream.IntStream.rangeClosed(0, jug.getVidas() + 1)
	    .forEach(i -> jug.gestionarVida());

		if (jug.getEstaMuerto())  {
			if (sfx) {
				sfx=false;
				
				listaSonidos.add(SonidoCodigo.MUSIC.parar());
				listaSonidos.add(SonidoCodigo.DEATH.sonar());
			}
		}
	}
	
	public int gestionarVida() {
		int puntos = 0;
		if (golpeable) {
			puntos = -250;
			jug.gestionarVida();
			golpeable=false;
		}
			
		if (jug.getEstaMuerto())  {
			if (sfx) {
				sfx=false;
				
				listaSonidos.add(SonidoCodigo.MUSIC.parar());
				listaSonidos.add(SonidoCodigo.DEATH.sonar());
			}
		}
		return puntos;
	}
	
	public void sumarBomba()			{ jug.sumarBomba();}
	public void restarBomba()			{ jug.restarBomba();}
	public void sumarVida()				{ jug.aumentarVida();}
	public void curarVida()				{ jug.sumarVida();}
	public void gestionarExplosion() 	{ jug.bombaExplotada(); }
	public void gestionarPonerBomba() 	{ jug.ponerBomba(); }
	public int	getMaxBombas()			{ return jug.getMaxBombas();}
	public String getTipoBomba() 		{ return jug.getTipoBomba(); }
	public String getTipoJugador() 		{ return jug.getTipoJugador(); }
	
	
}
