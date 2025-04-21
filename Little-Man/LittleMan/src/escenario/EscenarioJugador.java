package escenario;
import java.util.ArrayList;

import entidad.EntidadMovibleJugador;
import entidad.EntidadMovibleJugadorFactory;
import sonido.SonidoCodigos;
import sonido.SonidoCodigosEnum;

public class EscenarioJugador {
	
	
	private static EscenarioJugador miJugador;
	private EscenarioFacade miEscenarioFacade;
    private EntidadMovibleJugador jug;
    private int lastDir = 0;
    private boolean sfx=true, win=false;
    private ArrayList<String> listaSonidos = new ArrayList<String>();
    
    private EscenarioJugador() {}
    
    public static EscenarioJugador getJugador(){
    	if (miJugador == null) {
    		miJugador = new EscenarioJugador();
    	}
    	return miJugador;
    }
	
    
	
	public void inicializarJugador(String playerTipo){
		win=false;
		sfx=true;
		jug = EntidadMovibleJugadorFactory.getEntidadMovibleJugadorFactory().generate(playerTipo, 0, 0);
		listaSonidos.add(SonidoCodigos.getSonidoCodigos().getCodigoSonarSonido(SonidoCodigosEnum.MUSIC));
		miEscenarioFacade = EscenarioFacade.getEscenarioFacade();
	}
	
	
	

	public void actualizarPosicionJugador(String[] mov, int COLUMNAS, int FILAS)
	{
		boolean movimientoRealizado = false;
		
		if (mov != null) {
			for (int i = 0; i < mov.length && !movimientoRealizado; i++) {
				int posJX = jug.getPosX();																				// posicion X actual del jugador	[COLUMNA]
				int posJY = jug.getPosY();																				// posicion Y actual del jugador	[FILA]
				int posJXnew = posJX, posJYnew = posJY;																	// posiciones XY nuevas despues del movimiento
				boolean movimientoJugadorChocaConParedOBomba = false;													// flag si choca contra pared en coordenadas nuevas
				
				if (!jug.getEstaMuerto() && mov[i] != null) {
					if (mov[i].equals("L") && posJX != 0			) { posJXnew = posJX - 1; lastDir = 1; /*System.out.println("JJJ " + lastDir);*/ }
					if (mov[i].equals("R") && posJX != COLUMNAS - 1 ) { posJXnew = posJX + 1; lastDir = 2; /*System.out.println("JJJ " + lastDir);*/ }
					if (mov[i].equals("U") && posJY != 0			) { posJYnew = posJY - 1; lastDir = 3; /*System.out.println("JJJ " + lastDir);*/ }
					if (mov[i].equals("D") && posJY != FILAS - 1	) { posJYnew = posJY + 1; lastDir = 4; /*System.out.println("JJJ " + lastDir);*/ }
				
					movimientoJugadorChocaConParedOBomba = 																// Jugador choca contra pared o bomba si
							(  ( mov[i].equals("L") && posJX == 0				)											// Choca contra exterior
							|| ( mov[i].equals("R") && posJX == COLUMNAS - 1	)
							|| ( mov[i].equals("U") && posJY == 0				)
							|| ( mov[i].equals("D") && posJY == FILAS - 1		)
							|| miEscenarioFacade.chocaConPos(posJXnew, posJYnew));					// o si hay una bomba o bloque en la nueva posicion
		
						
					
					if (!movimientoJugadorChocaConParedOBomba && (posJX != posJXnew || posJY != posJYnew)) {
						listaSonidos.add(SonidoCodigos.getSonidoCodigos().getCodigoSonarSonido(SonidoCodigosEnum.WALK));
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
	
	public void gestionarFuego(int posFX, int posFY) {
		if (getPosX() == posFX && getPosY() == posFY) { gestionarVida(); }
	}
	
	public void gestionarEnemigo(int posFX, int posFY) {
		if (getPosX() == posFX && getPosY() == posFY) { gestionarVida(); }
	}
	
	
	
	public int[][] generarMatrizAniadirjugador(int COLUMNAS, int FILAS, boolean sobreBomba){
		int[][] matrizGenerada = new int[COLUMNAS][FILAS];
		
		int codigoJugador;
		
		if (win) codigoJugador = jug.getCodigoJugadorVictoria();
		else if (jug.getEstaMuerto()) codigoJugador = jug.getCodigoJugadorMuerto();
		else if (sobreBomba) codigoJugador = jug.getCodigoJugadorConBomba();
		else codigoJugador = jug.getCodigoJugador()*10 + lastDir;
		
		//System.out.println("AAAAAAAA" + codigoJugador);
		
		matrizGenerada[getPosX()][getPosY()] = codigoJugador;
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
			
			listaSonidos.add(SonidoCodigos.getSonidoCodigos().getCodigoPararSonido(SonidoCodigosEnum.MUSIC));
			listaSonidos.add(SonidoCodigos.getSonidoCodigos().getCodigoSonarSonido(SonidoCodigosEnum.WIN));
		}
	}
	public void resetWin() {win=false;}
	public boolean getWin() {return win;}
	public int getPosX() { return jug.getPosX(); }
	public int getPosY() { return jug.getPosY(); }
	public int getCodigoMov() { return jug.getCodigoMov(); }
	public boolean getPuedePonerBomba() { return jug.puedePonerBombas(); }
	public boolean getEstaMuerto() { return jug.getEstaMuerto(); }
	public void gestionarVida() {
		jug.gestionarVida();
		if (jug.getEstaMuerto())  {
			if (sfx) {
				sfx=false;
				
				listaSonidos.add(SonidoCodigos.getSonidoCodigos().getCodigoPararSonido(SonidoCodigosEnum.MUSIC));
				listaSonidos.add(SonidoCodigos.getSonidoCodigos().getCodigoSonarSonido(SonidoCodigosEnum.DEATH));
			}
		}
	}
	public void gestionarExplosion() { jug.bombaExplotada(); }
	public void gestionarPonerBomba() { jug.ponerBomba(); }
	public String getTipoBomba() { return jug.getTipoBomba(); }
	public String getTipoJugador() { return jug.getTipoJugador(); }
	
	
}
