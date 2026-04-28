package Modelo;

public class Estudiante {

    private int id;
    private String nombre;
    private int edad;
    private double promedio;
    private int idCarrera;
    private String telefono;
    private String correo;

    public Estudiante(int id, String nombre, int edad, double promedio, int idCarrera, String telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.promedio = promedio;
        this.idCarrera = idCarrera;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getPromedio() {
        return promedio;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               " | Nombre: " + nombre +
               " | Edad: " + edad +
               " | Promedio: " + promedio +
               " | Carrera: " + idCarrera +
               " | Tel: " + telefono +
               " | Correo: " + correo;
    }
}