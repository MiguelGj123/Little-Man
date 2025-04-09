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
					posJXnew = (mov[i].equals("L") && posJX != 0			) ? posJX - 1 : posJXnew;
					posJXnew = (mov[i].equals("R") && posJX != COLUMNAS - 1) ? posJX + 1 : posJXnew;
					posJYnew = (mov[i].equals("U") && posJY != 0			) ? posJY - 1 : posJYnew;
					posJYnew = (mov[i].equals("D") && posJY != FILAS - 1	) ? posJY + 1 : posJYnew;
				
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
	
	
	
	public int[][][] generarMatrizAniadirBloques(int[][][] matrizEditar){
		if (win) {
			if (sfx) {
				sfx=false;
				listaSonidos.add(SonidoCodigos.getSonidoCodigos().getCodigoPararSonido(SonidoCodigosEnum.MUSIC));
				listaSonidos.add(SonidoCodigos.getSonidoCodigos().getCodigoSonarSonido(SonidoCodigosEnum.WIN));
			}
			if (jug.getCodigoJugador()==20) {
				matrizEditar[getPosX()][getPosY()][4] = 23;
			} else if (jug.getCodigoJugador()==25) {
				matrizEditar[getPosX()][getPosY()][4] = 28;
				
			}
			
		} else if (jug.getEstaMuerto())  {
			
			if (sfx) {
				sfx=false;
				
				listaSonidos.add(SonidoCodigos.getSonidoCodigos().getCodigoPararSonido(SonidoCodigosEnum.MUSIC));
				listaSonidos.add(SonidoCodigos.getSonidoCodigos().getCodigoSonarSonido(SonidoCodigosEnum.DEATH));
			}
			matrizEditar[getPosX()][getPosY()][4] = jug.getCodigoJugadorMuerto();
		} else if (miEscenarioFacade.hayBombaEn(getPosX(), getPosY())) {
			matrizEditar[getPosX()][getPosY()][4] = jug.getCodigoJugadorConBomba();
		} else {
			sfx=true;
			int codigoFinal = Integer.parseInt(jug.getCodigoJugador() + "" + jug.getCodigoMov());
			matrizEditar[getPosX()][getPosY()][4] = codigoFinal;
		}
		
		
		
		return matrizEditar;
	}
	
	public String[] getListaSonidos() {
	    return listaSonidos.toArray(new String[0]);
	}
	
	public void listaSonidosClear() {
		listaSonidos.clear();
	}

	
	
	
	

	public void setWin() {win=true;}
	public void resetWin() {win=false;}
	public boolean getWin() {return win;}
	public int getPosX() { return jug.getPosX(); }
	public int getPosY() { return jug.getPosY(); }
	public int getCodigoMov() { return jug.getCodigoMov(); }
	public boolean getPuedePonerBomba() { return jug.puedePonerBombas(); }
	public boolean getEstaMuerto() { return jug.getEstaMuerto(); }
	public void gestionarVida() { jug.gestionarVida(); }
	public void gestionarExplosion() { jug.bombaExplotada(); }
	public void gestionarPonerBomba() { jug.ponerBomba(); }
	public String getTipoBomba() { return jug.getTipoBomba(); }
	public String getTipoJugador() { return jug.getTipoJugador(); }
	
	
}
