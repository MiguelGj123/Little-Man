package frameTablero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;



public class Frm__T__03__01__01__Panel__Vidas extends JPanel {

    private static final long serialVersionUID = 1L;

    private final int VIDA_TOPE_ABSOLUTO = 5;
    private final List<Frm__T__04__01__01__Label__Vida> corazones = new ArrayList<>();

    private int vidaInicialMaxima = 0;
    private int vidasActuales = 0;

    public Frm__T__03__01__01__Panel__Vidas() {
        //setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
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

        //revalidate();
        //repaint();
    }

    //UTILIZAR SOLO CUANDO SE CREE UNA NUEVA PARTIDA O CUANDO SE VUELVA A EMPEZAR
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

    //RESTA UNA VIDA, CAMBIA UN CORAZON ROJO POR UNO NEGRO
    private void perderVida() {
        if (vidasActuales > 0) {
            vidasActuales--;
            corazones.get(vidasActuales).setLleno(false);
        }
    }

    //SUMA UNA VIDA (CAMBIA UNO NEGRO POR UNO ROJO) SI ESTA AL MAXIMO PONE UN CORAZON MAS
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
