package vista;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EstilosTema {

    private static final Set<String> BOTONES_MENU = new HashSet<>(Arrays.asList(
            "Inicio", "Estudiantes", "Árbol AVL", "Recorridos",
            "Reportes", "Configuración", "Ayuda", "Salir"
    ));

    public static void aplicarTema(Component c) {
        if (c == null) {
            return;
        }

        TemaVisual tema = GestorTema.getTema();

        if (c instanceof JFrame) {
            JFrame frame = (JFrame) c;
            frame.getContentPane().setBackground(tema.fondoGeneral());
            recorrer(frame.getContentPane(), tema);
            SwingUtilities.updateComponentTreeUI(frame);
            frame.repaint();
            frame.revalidate();
            return;
        }

        recorrer(c, tema);
    }

    private static void recorrer(Component c, TemaVisual tema) {

        // No tocar tarjetas resumen ni sus hijos
        if (c instanceof TarjetaResumen) {
            return;
        }

        // No tocar botones estilizados ni sus hijos
        if (c instanceof BotonEstilizado) {
            return;
        }

        if (c instanceof JPanel) {
            JPanel panel = (JPanel) c;
            panel.setBackground(tema.fondoGeneral());
        }

        if (c instanceof JLabel) {
            JLabel lbl = (JLabel) c;
            lbl.setForeground(tema.textoPrincipal());
        }

        if (c instanceof JTextField) {
            JTextField txt = (JTextField) c;
            txt.setBackground(tema.fondoCampo());
            txt.setForeground(tema.textoPrincipal());
            txt.setCaretColor(tema.textoPrincipal());
            txt.setBorder(new LineBorder(tema.borde()));
        }

        if (c instanceof JTextArea) {
            JTextArea txt = (JTextArea) c;
            txt.setBackground(tema.fondoCampo());
            txt.setForeground(tema.textoPrincipal());
            txt.setCaretColor(tema.textoPrincipal());
            txt.setBorder(new LineBorder(tema.borde()));
        }

        if (c instanceof JComboBox) {
            JComboBox<?> combo = (JComboBox<?>) c;
            combo.setBackground(tema.fondoCampo());
            combo.setForeground(tema.textoPrincipal());
        }

        if (c instanceof JCheckBox) {
            JCheckBox chk = (JCheckBox) c;
            chk.setOpaque(false);
            chk.setForeground(tema.textoPrincipal());
            chk.setBackground(tema.fondoCard());
        }

        if (c instanceof JTable) {
            JTable tabla = (JTable) c;
            tabla.setBackground(tema.tablaFondo());
            tabla.setForeground(tema.tablaTexto());
            tabla.setSelectionBackground(tema.tablaSeleccionFondo());
            tabla.setSelectionForeground(tema.tablaSeleccionTexto());
            tabla.setGridColor(tema.borde());

            JTableHeader header = tabla.getTableHeader();
            if (header != null) {
                header.setBackground(tema.tablaHeaderFondo());
                header.setForeground(tema.tablaHeaderTexto());
            }
        }

        if (c instanceof JScrollPane) {
            JScrollPane sp = (JScrollPane) c;
            sp.getViewport().setBackground(tema.fondoCard());
            sp.setBorder(new LineBorder(tema.borde()));
        }

        if (c instanceof JButton) {
            JButton btn = (JButton) c;
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);

            String texto = btn.getText() == null ? "" : btn.getText().trim();

            if (BOTONES_MENU.contains(texto)) {
                btn.setBackground(tema.fondoMenuBoton());
            } else if (texto.equalsIgnoreCase("Hogar")) {
                btn.setBackground(tema.botonInfo());
            } else if (texto.equalsIgnoreCase("Salir")) {
                btn.setBackground(tema.botonPeligro());
            } else {
                btn.setBackground(tema.botonSecundario());
            }
        }

        if (c instanceof Container) {
            Container cont = (Container) c;
            for (Component hijo : cont.getComponents()) {
                recorrer(hijo, tema);
            }
        }
    }
}