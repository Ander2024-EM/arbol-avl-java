package vista;

import java.awt.Color;

public class TemaAzulNoche implements TemaVisual {

    @Override
    public Color fondoGeneral() {
        return new Color(225, 232, 242);
    }

    @Override
    public Color fondoCard() {
        return new Color(245, 248, 252);
    }

    @Override
    public Color fondoCampo() {
        return new Color(230, 237, 247);
    }

    @Override
    public Color fondoMenu() {
        return new Color(10, 32, 60);
    }

    @Override
    public Color fondoMenuBoton() {
        return new Color(20, 52, 92);
    }

    @Override
    public Color fondoSuperior() {
        return new Color(26, 115, 232);
    }

    @Override
    public Color textoPrincipal() {
        return new Color(10, 32, 60);
    }

    @Override
    public Color textoSecundario() {
        return new Color(55, 71, 90);
    }

    @Override
    public Color textoEnMenu() {
        return Color.WHITE;
    }

    @Override
    public Color borde() {
        return new Color(180, 200, 225);
    }

    @Override
    public Color botonPrimario() {
        return new Color(26, 115, 232);
    }

    @Override
    public Color botonExito() {
        return new Color(0, 153, 102);
    }

    @Override
    public Color botonPeligro() {
        return new Color(214, 51, 108);
    }

    @Override
    public Color botonAdvertencia() {
        return new Color(255, 153, 0);
    }

    @Override
    public Color botonInfo() {
        return new Color(0, 166, 214);
    }

    @Override
    public Color botonSecundario() {
        return new Color(99, 110, 114);
    }

    @Override
    public Color tablaFondo() {
        return new Color(245, 248, 252);
    }

    @Override
    public Color tablaTexto() {
        return new Color(10, 32, 60);
    }

    @Override
    public Color tablaSeleccionFondo() {
        return new Color(179, 215, 255);
    }

    @Override
    public Color tablaSeleccionTexto() {
        return new Color(10, 32, 60);
    }

    @Override
    public Color tablaHeaderFondo() {
        return new Color(10, 32, 60);
    }

    @Override
    public Color tablaHeaderTexto() {
        return Color.WHITE;
    }

    @Override
    public String nombreTema() {
        return "Azul Noche";
    }
}