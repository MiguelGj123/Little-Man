package frameTablero;

public class Frm__MenuVictoria implements Frm__MenuState {
    @Override
    public void mostrar(Frm__Menu context) {
        context.mostrarMenu("FELICIDADES, HAS GANADO!",
            new String[]{
                "<html><div style='text-align: center;'>VOLVER<br>AL JUGAR</div></html>",
                "<html><div style='text-align: center;'>GUARDAR Y VOLVER<br>AL MENÚ</div></html>"
            },
            new String[]{"VOLVER_JUGAR", "GUARDAR_Y_MENU"}
        );
    }
}
