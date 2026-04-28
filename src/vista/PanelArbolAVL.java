package vista;

import Modelo.ArbolAVL;
import Modelo.Nodo;

import javax.swing.*;
import java.awt.*;

public class PanelArbolAVL extends JPanel {

    private final ArbolAVL arbol;

    public PanelArbolAVL(ArbolAVL arbol) {
        this.arbol = arbol;
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        TemaVisual tema = GestorTema.getTema();
        setBackground(tema.fondoCard());

        if (arbol == null || arbol.getRaiz() == null) {
            g.setFont(new Font("Segoe UI", Font.BOLD, 22));
            g.setColor(tema.textoSecundario());
            g.drawString("Árbol AVL vacío", getWidth() / 2 - 80, getHeight() / 2);
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        dibujarNodo(g2, arbol.getRaiz(), getWidth() / 2, 60, getWidth() / 4);
    }

    private void dibujarNodo(Graphics2D g, Nodo nodo, int x, int y, int separacion) {
        if (nodo == null) {
            return;
        }

        TemaVisual tema = GestorTema.getTema();

        int radio = 22;
        int diametro = radio * 2;

        if (nodo.izquierda != null) {
            int xIzq = x - separacion;
            int yIzq = y + 85;

            g.setColor(tema.textoSecundario());
            g.drawLine(x, y, xIzq, yIzq);
            dibujarNodo(g, nodo.izquierda, xIzq, yIzq, Math.max(separacion / 2, 40));
        }

        if (nodo.derecha != null) {
            int xDer = x + separacion;
            int yDer = y + 85;

            g.setColor(tema.textoSecundario());
            g.drawLine(x, y, xDer, yDer);
            dibujarNodo(g, nodo.derecha, xDer, yDer, Math.max(separacion / 2, 40));
        }

        g.setColor(tema.fondoMenu());
        g.fillOval(x - radio, y - radio, diametro, diametro);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Segoe UI", Font.BOLD, 13));

        String texto = String.valueOf(nodo.dato.getId());
        FontMetrics fm = g.getFontMetrics();
        int textoAncho = fm.stringWidth(texto);
        int textoAlto = fm.getAscent();

        g.drawString(texto, x - textoAncho / 2, y + textoAlto / 4);

        g.setColor(tema.botonInfo());
        g.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        String balance = "h:" + nodo.altura;
        int anchoBalance = g.getFontMetrics().stringWidth(balance);
        g.drawString(balance, x - anchoBalance / 2, y + 35);
    }

    public void refrescar() {
        repaint();
    }
}