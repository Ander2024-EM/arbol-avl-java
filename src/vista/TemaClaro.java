package vista;

import java.awt.Color;

public class TemaClaro implements TemaVisual {

    @Override
    public Color fondoGeneral() {
        return new Color(240, 242, 245);
    }

    @Override
    public Color fondoCard() {
        return Color.WHITE;
    }

    @Override
    public Color fondoCampo() {
        return Color.WHITE;
    }

    @Override
    public Color fondoMenu() {
        return new Color(18, 41, 77);
    }

    @Override
    public Color fondoMenuBoton() {
        return new Color(28, 58, 100);
    }

    @Override
    public Color fondoSuperior() {
        return new Color(0, 188, 212);
    }

    @Override
    public Color textoPrincipal() {
        return new Color(18, 41, 77);
    }

    @Override
    public Color textoSecundario() {
        return new Color(60, 60, 60);
    }

    @Override
    public Color textoEnMenu() {
        return Color.WHITE;
    }

    @Override
    public Color borde() {
        return new Color(220, 225, 230);
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
        return Color.WHITE;
    }

    @Override
    public Color tablaTexto() {
        return Color.BLACK;
    }

    @Override
    public Color tablaSeleccionFondo() {
        return new Color(184, 207, 229);
    }

    @Override
    public Color tablaSeleccionTexto() {
        return Color.BLACK;
    }

    @Override
    public Color tablaHeaderFondo() {
        return new Color(18, 41, 77);
    }

    @Override
    public Color tablaHeaderTexto() {
        return Color.WHITE;
    }

    @Override
    public String nombreTema() {
        return "Claro";
    }
}