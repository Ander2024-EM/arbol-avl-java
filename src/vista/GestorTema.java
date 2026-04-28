package vista;

public class GestorTema {

    private static TemaVisual temaActual = new TemaClaro();

    public static TemaVisual getTema() {
        return temaActual;
    }

    public static void setTema(TemaVisual nuevoTema) {
        if (nuevoTema != null) {
            temaActual = nuevoTema;
        }
    }

    public static void setTemaPorNombre(String nombre) {
        if (nombre == null) {
            temaActual = new TemaClaro();
            return;
        }

        switch (nombre.trim().toLowerCase()) {
            case "oscuro":
                temaActual = new TemaOscuro();
                break;
            case "azul noche":
                temaActual = new TemaAzulNoche();
                break;
            default:
                temaActual = new TemaClaro();
                break;
        }
    }
}