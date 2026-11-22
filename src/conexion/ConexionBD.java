package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = System.getenv().getOrDefault(
        "MEDBAY_DB_URL",
        "jdbc:mysql://localhost:3306/med_bay"
    );

    public static Connection conectar() throws SQLException {
        String usuario = System.getenv("MEDBAY_DB_USER");
        String clave = System.getenv("MEDBAY_DB_PASSWORD");

        if (usuario == null || usuario.isBlank() || clave == null) {
            throw new SQLException(
                "Configura MEDBAY_DB_USER y MEDBAY_DB_PASSWORD antes de iniciar MED-BAY."
            );
        }

        return DriverManager.getConnection(URL, usuario, clave);
    }
}