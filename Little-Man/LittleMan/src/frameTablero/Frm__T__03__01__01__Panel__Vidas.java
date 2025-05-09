package frameTablero;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;


//Panel que representa las vidas del jugador como corazones visibles o vacíos.
public class Frm__T__03__01__01__Panel__Vidas extends JPanel {

    private static final long serialVersionUID = 1L;

    private final int VIDA_TOPE_ABSOLUTO = 5;
    private final List<Frm__T__04__01__01__Label__Vida> corazones = new ArrayList<>();

    private int vidaInicialMaxima = 0;
    private int vidasActuales = 0;

    public Frm__T__03__01__01__Panel__Vidas() {
    	setOpaque(false);
    	setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(300, 45));

        // Crear los 5 corazones desde el principio, invisibles
        for (int i = 0; i < VIDA_TOPE_ABSOLUTO; i++) {
            Frm__T__04__01__01__Label__Vida corazon = new Frm__T__04__01__01__Label__Vida();
            corazon.setBounds(T_CFG.DIST_ENTRE_CORA * i, 0, T_CFG.PXL_CORA, T_CFG.PXL_CORA);
            corazon.setVisible(false);
            corazon.setLleno(false);
            corazones.add(corazon);
            add(corazon);
        }

    }

 // Activa la visibilidad de los iconos de corazones según el número de vidas iniciales.
    public void inicializarVidas(int vidasIniciales) {
        vidaInicialMaxima = Math.min(vidasIniciales, VIDA_TOPE_ABSOLUTO);
        vidasActuales = vidaInicialMaxima;

        for (int i = 0; i < VIDA_TOPE_ABSOLUTO; i++) {
            Frm__T__04__01__01__Label__Vida corazon = corazones.get(i);
            boolean visible = i < vidaInicialMaxima;
            corazon.setVisible(visible);
            corazon.setLleno(visible);
        }

        revalidate();
        repaint();
    }

 // Oculta el último icono de vida visible (corazón).
    private void perderVida() {
        if (vidasActuales > 0) {
            vidasActuales--;
            corazones.get(vidasActuales).setLleno(false);
        }
    }

 // Muestra un corazón adicional activando el siguiente icono oculto.
    private void ganarVida() {
        if (vidasActuales < VIDA_TOPE_ABSOLUTO) {
            Frm__T__04__01__01__Label__Vida corazon = corazones.get(vidasActuales);
            if (!corazon.isVisible()) {
                corazon.setVisible(true);
            }
            corazon.setLleno(true);
            vidasActuales++;
        }
    }
    
 // Añade o quita corazones según si el jugador gana o pierde vidas.
    public void actualizarVidas(int vidas) 
    {
    	if(vidas < vidasActuales)
    	{
    		perderVida();
    	}
    	else if (vidas > vidasActuales)
    	{
    		ganarVida();
    	}

    }
    
}
