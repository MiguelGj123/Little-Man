package frameTablero;

import javax.swing.*;
import java.awt.*;

public class Frm__T__04__01__01__Label__Vida extends JLabel {

    private static final long serialVersionUID = 1L;
    private final String RUTA_LLENO = "Pixels/heart_full.png";
    private final String RUTA_VACIO = "Pixels/heart_empty.png";

    public Frm__T__04__01__01__Label__Vida() {
        setOpaque(false);
        setPreferredSize(new Dimension(T_CFG.PXL_CORA, T_CFG.PXL_CORA));
        setLleno(true);
    }

    public void setLleno(boolean lleno) {
        String ruta;
        if (lleno) {
            ruta = RUTA_LLENO;
        } else {
            ruta = RUTA_VACIO;
        }

        Image img = new ImageIcon(ruta).getImage().getScaledInstance(T_CFG.PXL_CORA, T_CFG.PXL_CORA, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(img));
    }
}
