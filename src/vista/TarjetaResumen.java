package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TarjetaResumen extends JPanel {

    private final JLabel titulo;
    private final JLabel valor;
    private final JPanel franjaSuperior;
    private Color colorPrincipal;

    public TarjetaResumen(String tituloTexto, String valorTexto, Color colorPrincipal) {
        this.colorPrincipal = colorPrincipal;

        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 218, 230), 1),
                new EmptyBorder(0, 0, 0, 0)
        ));

        franjaSuperior = new JPanel();
        franjaSuperior.setOpaque(true);
        franjaSuperior.setBackground(colorPrincipal);
        franjaSuperior.setPreferredSize(new Dimension(0, 10));

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);
        contenido.setBorder(new EmptyBorder(14, 18, 14, 18));

        titulo = new JLabel(tituloTexto);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(new Color(85, 85, 85));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        valor = new JLabel(valorTexto);
        valor.setFont(new Font("Segoe UI", Font.BOLD, 27));
        valor.setForeground(colorPrincipal);
        valor.setAlignmentX(Component.LEFT_ALIGNMENT);

        contenido.add(titulo);
        contenido.add(Box.createVerticalStrut(5));
        contenido.add(valor);

        add(franjaSuperior, BorderLayout.NORTH);
        add(contenido, BorderLayout.CENTER);
    }

    public void setValor(String nuevoValor) {
        valor.setText(nuevoValor);
    }

    public void setColorPrincipal(Color nuevoColor) {
        this.colorPrincipal = nuevoColor;
        franjaSuperior.setBackground(nuevoColor);
        valor.setForeground(nuevoColor);
        repaint();
    }
}