package proyecto_arbol;

import vista.FrmPrincipal;
import vista.GestorTema;
import vista.TemaClaro;

public class Proyecto_Arbol {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            GestorTema.setTema(new TemaClaro());
            new FrmPrincipal().setVisible(true);
        });
    }
}