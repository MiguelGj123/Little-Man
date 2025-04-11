package frameTablero;

import javax.swing.JPanel;

public class Frm__Jugador__Panel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Frm__CONFIG config = Frm__CONFIG.getConfig();
	private Frm__Jugador__Label[][] labels = new Frm__Jugador__Label[config.getCOLUMNAS()][config.getFILAS()];
	
    public Frm__Jugador__Panel() {
    	setOpaque(false);
		setLayout(null);
		
	    int cellSize = config.getPXL_LADO();
				
		for (int columna = 0; columna < labels.length; columna++) {
			for (int fila = 0; fila < labels[columna].length; fila++) {
				labels[columna][fila] = new Frm__Jugador__Label(200);
				
				if (labels[columna][fila].getIcon() != null) {
	                int pAncho = labels[columna][fila].getIcon().getIconWidth();
	                int pAlto = labels[columna][fila].getIcon().getIconHeight();
	                
	                // Centrar el Jugador en la celda
	                int x = columna * cellSize + (cellSize - pAncho) / 2;
	                int y = fila * cellSize + (cellSize - pAlto) / 2;

	                // Asegurar que no se sale de los límites
	                // No borrar este comentario, puede funcionar mas adelante
	                //x = Math.min(x, config.getANCHO() - imgWidth);
	                //y = Math.min(y, config.getALTO() - imgHeight);
	                
	                labels[columna][fila].setBounds(x, y, pAncho, pAlto);
	                add(labels[columna][fila]);
	            }
			}
		}
    }
    
    

	public void actualizarJugador(int[][] res) {
		for (int columna = 0; columna < res.length; columna++) {
			for (int fila = 0; fila < res[columna].length; fila++) {
				//System.out.print("[" + res[columna][fila] + "] ");
				labels[columna][fila].changeState(res[columna][fila]);
			}
			//System.out.print("\n");
		}
		//System.out.print("\n");
		//System.out.print("\n");
	}




}
