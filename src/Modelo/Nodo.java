package Modelo;

public class Nodo {
    public Estudiante dato;
    public Nodo izquierda;
    public Nodo derecha;
    public int altura;

    public Nodo(Estudiante dato) {
        this.dato = dato;
        this.altura = 1;
    }
}