package frameTablero;


public class Frm__MenuPausa implements Frm__MenuState {
    @Override
    public void mostrar(Frm__Menu context) {
        context.mostrarMenu("PAUSA",
            new String[]{
                "<html><div style='text-align: center;'>REANUDAR<br>PARTIDA</div></html>",
                "<html><div style='text-align: center;'>VOLVER<br>AL MENÚ</div></html>"
            },
            new String[]{"REANUDAR", "VOLVER_MENU"}
        );
    }
}