package vista;

import java.awt.Color;

public interface TemaVisual {

    Color fondoGeneral();
    Color fondoCard();
    Color fondoCampo();

    Color fondoMenu();
    Color fondoMenuBoton();
    Color fondoSuperior();

    Color textoPrincipal();
    Color textoSecundario();
    Color textoEnMenu();

    Color borde();

    Color botonPrimario();
    Color botonExito();
    Color botonPeligro();
    Color botonAdvertencia();
    Color botonInfo();
    Color botonSecundario();

    Color tablaFondo();
    Color tablaTexto();
    Color tablaSeleccionFondo();
    Color tablaSeleccionTexto();
    Color tablaHeaderFondo();
    Color tablaHeaderTexto();

    String nombreTema();
}