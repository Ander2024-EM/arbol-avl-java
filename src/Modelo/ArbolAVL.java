package Modelo;

import java.util.ArrayList;
import java.util.List;

public class ArbolAVL {

    private Nodo raiz;

    public Nodo getRaiz() {
        return raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    // =========================
    // MÉTODOS BÁSICOS AVL
    // =========================
    private int altura(Nodo n) {
        return (n == null) ? 0 : n.altura;
    }

    public int alturaArbol() {
        return altura(raiz);
    }

    private int max(int a, int b) {
        return Math.max(a, b);
    }

    private int getBalance(Nodo n) {
        return (n == null) ? 0 : altura(n.izquierda) - altura(n.derecha);
    }

    private Nodo rotarDerecha(Nodo y) {
        Nodo x = y.izquierda;
        Nodo t2 = x.derecha;

        x.derecha = y;
        y.izquierda = t2;

        y.altura = max(altura(y.izquierda), altura(y.derecha)) + 1;
        x.altura = max(altura(x.izquierda), altura(x.derecha)) + 1;

        return x;
    }

    private Nodo rotarIzquierda(Nodo x) {
        Nodo y = x.derecha;
        Nodo t2 = y.izquierda;

        y.izquierda = x;
        x.derecha = t2;

        x.altura = max(altura(x.izquierda), altura(x.derecha)) + 1;
        y.altura = max(altura(y.izquierda), altura(y.derecha)) + 1;

        return y;
    }

    // =========================
    // INSERTAR
    // =========================
    public boolean insertar(Estudiante dato) {
        if (existe(dato.getId())) {
            return false;
        }
        raiz = insertarRec(raiz, dato);
        return true;
    }

    private Nodo insertarRec(Nodo nodo, Estudiante dato) {
        if (nodo == null) {
            return new Nodo(dato);
        }

        if (dato.getId() < nodo.dato.getId()) {
            nodo.izquierda = insertarRec(nodo.izquierda, dato);
        } else if (dato.getId() > nodo.dato.getId()) {
            nodo.derecha = insertarRec(nodo.derecha, dato);
        } else {
            return nodo;
        }

        nodo.altura = 1 + max(altura(nodo.izquierda), altura(nodo.derecha));
        int balance = getBalance(nodo);

        // Izquierda-Izquierda
        if (balance > 1 && dato.getId() < nodo.izquierda.dato.getId()) {
            return rotarDerecha(nodo);
        }

        // Derecha-Derecha
        if (balance < -1 && dato.getId() > nodo.derecha.dato.getId()) {
            return rotarIzquierda(nodo);
        }

        // Izquierda-Derecha
        if (balance > 1 && dato.getId() > nodo.izquierda.dato.getId()) {
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }

        // Derecha-Izquierda
        if (balance < -1 && dato.getId() < nodo.derecha.dato.getId()) {
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    // =========================
    // BUSCAR
    // =========================
    public Estudiante buscar(int id) {
        return buscarRec(raiz, id);
    }

    private Estudiante buscarRec(Nodo nodo, int id) {
        if (nodo == null) {
            return null;
        }

        if (id == nodo.dato.getId()) {
            return nodo.dato;
        }

        if (id < nodo.dato.getId()) {
            return buscarRec(nodo.izquierda, id);
        } else {
            return buscarRec(nodo.derecha, id);
        }
    }

    public boolean existe(int id) {
        return buscar(id) != null;
    }

    private Nodo buscarNodo(Nodo nodo, int id) {
        if (nodo == null) {
            return null;
        }

        if (id == nodo.dato.getId()) {
            return nodo;
        }

        if (id < nodo.dato.getId()) {
            return buscarNodo(nodo.izquierda, id);
        } else {
            return buscarNodo(nodo.derecha, id);
        }
    }

    // =========================
    // ACTUALIZAR DATOS
    // =========================
    public boolean actualizar(Estudiante estudianteActualizado) {
        Nodo nodo = buscarNodo(raiz, estudianteActualizado.getId());

        if (nodo != null) {
            nodo.dato.setNombre(estudianteActualizado.getNombre());
            nodo.dato.setEdad(estudianteActualizado.getEdad());
            nodo.dato.setPromedio(estudianteActualizado.getPromedio());
            nodo.dato.setIdCarrera(estudianteActualizado.getIdCarrera());
            nodo.dato.setTelefono(estudianteActualizado.getTelefono());
            nodo.dato.setCorreo(estudianteActualizado.getCorreo());
            return true;
        }

        return false;
    }

    // =========================
    // ELIMINAR
    // =========================
    public boolean eliminar(int id) {
        if (!existe(id)) {
            return false;
        }
        raiz = eliminarRec(raiz, id);
        return true;
    }

    private Nodo eliminarRec(Nodo nodo, int id) {
        if (nodo == null) {
            return nodo;
        }

        if (id < nodo.dato.getId()) {
            nodo.izquierda = eliminarRec(nodo.izquierda, id);
        } else if (id > nodo.dato.getId()) {
            nodo.derecha = eliminarRec(nodo.derecha, id);
        } else {
            // Nodo con un hijo o sin hijos
            if (nodo.izquierda == null || nodo.derecha == null) {
                Nodo temp;

                if (nodo.izquierda == null) {
                    temp = nodo.derecha;
                } else {
                    temp = nodo.izquierda;
                }

                if (temp == null) {
                    nodo = null;
                } else {
                    nodo = temp;
                }
            } else {
                // Nodo con dos hijos
                Nodo temp = nodoMinimo(nodo.derecha);
                nodo.dato = temp.dato;
                nodo.derecha = eliminarRec(nodo.derecha, temp.dato.getId());
            }
        }

        if (nodo == null) {
            return nodo;
        }

        nodo.altura = max(altura(nodo.izquierda), altura(nodo.derecha)) + 1;
        int balance = getBalance(nodo);

        // Izquierda-Izquierda
        if (balance > 1 && getBalance(nodo.izquierda) >= 0) {
            return rotarDerecha(nodo);
        }

        // Izquierda-Derecha
        if (balance > 1 && getBalance(nodo.izquierda) < 0) {
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }

        // Derecha-Derecha
        if (balance < -1 && getBalance(nodo.derecha) <= 0) {
            return rotarIzquierda(nodo);
        }

        // Derecha-Izquierda
        if (balance < -1 && getBalance(nodo.derecha) > 0) {
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    private Nodo nodoMinimo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.izquierda != null) {
            actual = actual.izquierda;
        }
        return actual;
    }

    // =========================
    // RECORRIDOS EN CONSOLA
    // =========================
    public void inOrden() {
        inOrdenRec(raiz);
    }

    private void inOrdenRec(Nodo nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.izquierda);
            System.out.println(nodo.dato);
            inOrdenRec(nodo.derecha);
        }
    }

    public void preOrden() {
        preOrdenRec(raiz);
    }

    private void preOrdenRec(Nodo nodo) {
        if (nodo != null) {
            System.out.println(nodo.dato);
            preOrdenRec(nodo.izquierda);
            preOrdenRec(nodo.derecha);
        }
    }

    public void postOrden() {
        postOrdenRec(raiz);
    }

    private void postOrdenRec(Nodo nodo) {
        if (nodo != null) {
            postOrdenRec(nodo.izquierda);
            postOrdenRec(nodo.derecha);
            System.out.println(nodo.dato);
        }
    }

    // =========================
    // RECORRIDOS PARA INTERFAZ
    // =========================
    public List<Estudiante> obtenerInOrdenLista() {
        List<Estudiante> lista = new ArrayList<>();
        obtenerInOrdenListaRec(raiz, lista);
        return lista;
    }

    private void obtenerInOrdenListaRec(Nodo nodo, List<Estudiante> lista) {
        if (nodo != null) {
            obtenerInOrdenListaRec(nodo.izquierda, lista);
            lista.add(nodo.dato);
            obtenerInOrdenListaRec(nodo.derecha, lista);
        }
    }

    public List<Estudiante> obtenerPreOrdenLista() {
        List<Estudiante> lista = new ArrayList<>();
        obtenerPreOrdenListaRec(raiz, lista);
        return lista;
    }

    private void obtenerPreOrdenListaRec(Nodo nodo, List<Estudiante> lista) {
        if (nodo != null) {
            lista.add(nodo.dato);
            obtenerPreOrdenListaRec(nodo.izquierda, lista);
            obtenerPreOrdenListaRec(nodo.derecha, lista);
        }
    }

    public List<Estudiante> obtenerPostOrdenLista() {
        List<Estudiante> lista = new ArrayList<>();
        obtenerPostOrdenListaRec(raiz, lista);
        return lista;
    }

    private void obtenerPostOrdenListaRec(Nodo nodo, List<Estudiante> lista) {
        if (nodo != null) {
            obtenerPostOrdenListaRec(nodo.izquierda, lista);
            obtenerPostOrdenListaRec(nodo.derecha, lista);
            lista.add(nodo.dato);
        }
    }

    public String obtenerInOrdenTexto() {
        StringBuilder sb = new StringBuilder();
        for (Estudiante e : obtenerInOrdenLista()) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    public String obtenerPreOrdenTexto() {
        StringBuilder sb = new StringBuilder();
        for (Estudiante e : obtenerPreOrdenLista()) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    public String obtenerPostOrdenTexto() {
        StringBuilder sb = new StringBuilder();
        for (Estudiante e : obtenerPostOrdenLista()) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    // =========================
    // DATOS GENERALES
    // =========================
    public int contarNodos() {
        return contarNodosRec(raiz);
    }

    private int contarNodosRec(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodosRec(nodo.izquierda) + contarNodosRec(nodo.derecha);
    }

    public List<Estudiante> obtenerTodosLosEstudiantes() {
        return obtenerInOrdenLista();
    }

    public void limpiar() {
        raiz = null;
    }
}