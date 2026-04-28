package vista;

import javax.swing.*;
import java.awt.*;

public class BotonEstilizado extends JButton {

    public enum Tipo {
        PRIMARIO, EXITO, PELIGRO, ADVERTENCIA, INFO, SECUNDARIO
    }

    public BotonEstilizado(String texto, Tipo tipo) {
        super(texto);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 13));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        aplicarColor(tipo);
    }

    private void aplicarColor(Tipo tipo) {
        TemaVisual tema = GestorTema.getTema();

        switch (tipo) {
            case PRIMARIO:
                setBackground(tema.botonPrimario());
                break;
            case EXITO:
                setBackground(tema.botonExito());
                break;
            case PELIGRO:
                setBackground(tema.botonPeligro());
                break;
            case ADVERTENCIA:
                setBackground(tema.botonAdvertencia());
                break;
            case INFO:
                setBackground(tema.botonInfo());
                break;
            default:
                setBackground(tema.botonSecundario());
        }
    }
}