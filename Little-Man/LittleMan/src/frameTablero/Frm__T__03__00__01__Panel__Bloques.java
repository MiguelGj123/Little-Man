package frameTablero;

import javax.swing.JPanel;

//Panel que contiene todas las celdas de bloques del escenario.
//Se actualiza dinámicamente en función de los cambios en el juego.
public class Frm__T__03__00__01__Panel__Bloques extends JPanel implements ActualizableImagen{
	
	private static final long serialVersionUID = 1L;
	
	private Frm__T__04__00__01__Label__Bloques[][] labels = new Frm__T__04__00__01__Label__Bloques[T_CFG.COLUMNAS][T_CFG.FILAS];
	
	int cellSize = T_CFG.PXL_LADO;
	
    public Frm__T__03__00__01__Panel__Bloques() {
    	setOpaque(false);
		setLayout(null);
				
		for (int columna = 0; columna < labels.length; columna++) {
			for (int fila = 0; fila < labels[columna].length; fila++) {
				labels[columna][fila] = new Frm__T__04__00__01__Label__Bloques();
				
				if (labels[columna][fila].getIcon() != null) {
	                int pAncho = labels[columna][fila].getIcon().getIconWidth();
	                int pAlto = labels[columna][fila].getIcon().getIconHeight();
	                
	                int x = columna * cellSize + (cellSize - pAncho) / 2;
	                int y = fila * cellSize + (cellSize - pAlto) / 2;
	                
	                labels[columna][fila].setBounds(x, y, pAncho, pAlto);
	                add(labels[columna][fila]);
	            }
			}
		}
    }
    
 // Cambia el icono del bloque en una celda específica según su nuevo estado.
    public void actualizarImagen(int codigo, int col, int fil) {
		labels[col][fil].changeState(codigo, "NORMAL");
		
		if (labels[col][fil].getIcon() != null) {
            int pAncho = labels[col][fil].getIcon().getIconWidth();
            int pAlto = labels[col][fil].getIcon().getIconHeight();
            
            int x = col * cellSize + (cellSize - pAncho) / 2;
            int y = fil * cellSize + (cellSize - pAlto) / 2;
            
            labels[col][fil].setBounds(x, y, pAncho, pAlto);
        } else {
        	setVisible(false);
        }
	}
}
