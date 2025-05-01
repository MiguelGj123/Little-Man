package frameTablero;

public class Frm__MenuDerrota implements Frm__MenuState {
    @Override
    public void mostrar(Frm__Menu context) {
        context.mostrarMenu("HAS MUERTO",
            new String[]{
                "<html><div style='text-align: center;'>REINTENTAR</div></html>",
                "<html><div style='text-align: center;'>GUARDAR Y VOLVER<br>AL MENÚ</div></html>"
            },
            new String[]{"REINTENTAR", "GUARDAR_Y_MENU"}
        );
    }
}
