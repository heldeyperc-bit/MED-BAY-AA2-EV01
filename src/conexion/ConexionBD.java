package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/med_bay";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "123456";

    public static Connection conectar() {

        try {

            Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);

            System.out.println("================================");
            System.out.println("Conexión exitosa a MED-BAY");
            System.out.println("================================");

            return conexion;

        } catch (SQLException e) {

            System.out.println("Error al conectar con la base de datos");
            e.printStackTrace();

            return null;
        }

    }

}