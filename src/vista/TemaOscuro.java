package vista;

import java.awt.Color;

public class TemaOscuro implements TemaVisual {

    @Override
    public Color fondoGeneral() {
        return new Color(34, 40, 49);
    }

    @Override
    public Color fondoCard() {
        return new Color(47, 54, 64);
    }

    @Override
    public Color fondoCampo() {
        return new Color(57, 62, 70);
    }

    @Override
    public Color fondoMenu() {
        return new Color(24, 28, 34);
    }

    @Override
    public Color fondoMenuBoton() {
        return new Color(38, 44, 54);
    }

    @Override
    public Color fondoSuperior() {
        return new Color(0, 121, 140);
    }

    @Override
    public Color textoPrincipal() {
        return Color.WHITE;
    }

    @Override
    public Color textoSecundario() {
        return new Color(220, 220, 220);
    }

    @Override
    public Color textoEnMenu() {
        return Color.WHITE;
    }

    @Override
    public Color borde() {
        return new Color(85, 85, 85);
    }

    @Override
    public Color botonPrimario() {
        return new Color(0, 123, 255);
    }

    @Override
    public Color botonExito() {
        return new Color(40, 167, 69);
    }

    @Override
    public Color botonPeligro() {
        return new Color(220, 53, 69);
    }

    @Override
    public Color botonAdvertencia() {
        return new Color(255, 193, 7);
    }

    @Override
    public Color botonInfo() {
        return new Color(23, 162, 184);
    }

    @Override
    public Color botonSecundario() {
        return new Color(108, 117, 125);
    }

    @Override
    public Color tablaFondo() {
        return new Color(57, 62, 70);
    }

    @Override
    public Color tablaTexto() {
        return Color.WHITE;
    }

    @Override
    public Color tablaSeleccionFondo() {
        return new Color(0, 173, 181);
    }

    @Override
    public Color tablaSeleccionTexto() {
        return Color.WHITE;
    }

    @Override
    public Color tablaHeaderFondo() {
        return new Color(24, 28, 34);
    }

    @Override
    public Color tablaHeaderTexto() {
        return Color.WHITE;
    }

    @Override
    public String nombreTema() {
        return "Oscuro";
    }
}