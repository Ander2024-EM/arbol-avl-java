    package dao;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

    public class Conexion {

        private static final String URL = "jdbc:mariadb://localhost:3308/proyecto_arbol_avl?useUnicode=true&characterEncoding=UTF-8";
        private static final String USER = "root";
        private static final String PASSWORD = "Programador24.ñ";

        static {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("No se encontró el driver de MariaDB: " + e.getMessage());
            }
        }

        public static Connection getConexion() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }