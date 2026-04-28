package dao;

import Modelo.Estudiante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    public List<Estudiante> obtenerTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";

        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Estudiante e = new Estudiante(
                        rs.getInt("id_estudiante"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getDouble("promedio"),
                        rs.getInt("id_carrera"),
                        rs.getString("telefono"),
                        rs.getString("correo")
                );
                lista.add(e);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener estudiantes: " + e.getMessage());
        }

        return lista;
    }

    public Estudiante buscarPorId(int id) {
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Estudiante(
                            rs.getInt("id_estudiante"),
                            rs.getString("nombre"),
                            rs.getInt("edad"),
                            rs.getDouble("promedio"),
                            rs.getInt("id_carrera"),
                            rs.getString("telefono"),
                            rs.getString("correo")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar estudiante: " + e.getMessage());
        }

        return null;
    }

    public boolean existeId(int id) {
        return buscarPorId(id) != null;
    }

    public boolean insertar(Estudiante e) {
        String sql = "INSERT INTO estudiante (id_estudiante, nombre, edad, promedio, id_carrera, telefono, correo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, e.getId());
            ps.setString(2, e.getNombre());
            ps.setInt(3, e.getEdad());
            ps.setDouble(4, e.getPromedio());
            ps.setInt(5, e.getIdCarrera());
            ps.setString(6, e.getTelefono());
            ps.setString(7, e.getCorreo());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error al insertar: " + ex.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Estudiante e) {
        String sql = "UPDATE estudiante SET nombre=?, edad=?, promedio=?, id_carrera=?, telefono=?, correo=? WHERE id_estudiante=?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setInt(2, e.getEdad());
            ps.setDouble(3, e.getPromedio());
            ps.setInt(4, e.getIdCarrera());
            ps.setString(5, e.getTelefono());
            ps.setString(6, e.getCorreo());
            ps.setInt(7, e.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
            return false;
        }
    }

    public boolean vaciarTabla() {
        String sql = "DELETE FROM estudiante";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al vaciar tabla: " + e.getMessage());
            return false;
        }
    }

    public boolean guardarTodos(List<Estudiante> lista) {
        String sql = "INSERT INTO estudiante (id_estudiante, nombre, edad, promedio, id_carrera, telefono, correo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (Estudiante e : lista) {
                ps.setInt(1, e.getId());
                ps.setString(2, e.getNombre());
                ps.setInt(3, e.getEdad());
                ps.setDouble(4, e.getPromedio());
                ps.setInt(5, e.getIdCarrera());
                ps.setString(6, e.getTelefono());
                ps.setString(7, e.getCorreo());
                ps.addBatch();
            }

            ps.executeBatch();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al guardar todos: " + e.getMessage());
            return false;
        }
    }

    public boolean sincronizarDesdeArbol(List<Estudiante> lista) {
        try (Connection conn = Conexion.getConexion()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psDelete = conn.prepareStatement("DELETE FROM estudiante");
                 PreparedStatement psInsert = conn.prepareStatement(
                         "INSERT INTO estudiante (id_estudiante, nombre, edad, promedio, id_carrera, telefono, correo) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

                psDelete.executeUpdate();

                for (Estudiante e : lista) {
                    psInsert.setInt(1, e.getId());
                    psInsert.setString(2, e.getNombre());
                    psInsert.setInt(3, e.getEdad());
                    psInsert.setDouble(4, e.getPromedio());
                    psInsert.setInt(5, e.getIdCarrera());
                    psInsert.setString(6, e.getTelefono());
                    psInsert.setString(7, e.getCorreo());
                    psInsert.addBatch();
                }

                psInsert.executeBatch();
                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error al sincronizar desde árbol: " + e.getMessage());
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error general de sincronización: " + e.getMessage());
            return false;
        }
    }
    public int obtenerSiguienteId() {
    String sql = "SELECT IFNULL(MAX(id_estudiante), 0) + 1 AS siguiente FROM estudiante";

    try (Connection conn = Conexion.getConexion();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        if (rs.next()) {
            return rs.getInt("siguiente");
        }

    } catch (SQLException e) {
        System.out.println("Error al obtener siguiente ID: " + e.getMessage());
    }

    return 1; // por si algo falla
}
}