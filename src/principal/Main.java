package principal;

import conexion.ConexionBD;
import dao.PacienteDAO;
import modelo.Paciente;

import java.sql.SQLException;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        PacienteDAO dao = new PacienteDAO();
        Paciente paciente = new Paciente();

        String documento = "DEMO-" + UUID.randomUUID().toString().substring(0, 18);

        paciente.setNombre("Ana");
        paciente.setApellido("Prueba");
        paciente.setDocumento(documento);
        paciente.setEdad(30);
        paciente.setGenero("Femenino");
        paciente.setTelefono("0000000000");
        paciente.setCorreo("ana@example.com");
        paciente.setDireccion("Dirección de prueba");

        try {
            System.out.println("=== 1. REGISTRAR PACIENTE ===");
            dao.registrarPaciente(paciente);

            int id = buscarIdPorDocumento(documento);
            if (id == 0) {
                throw new SQLException("No se encontró el registro de prueba.");
            }
            paciente.setIdPaciente(id);
            System.out.println("Registro comprobado. ID asignado: " + id);

            System.out.println("\n=== 2. CONSULTAR PACIENTE ===");
            dao.listarPacientes();
            System.out.println("Paciente de esta prueba: ID " + id
                    + " | Documento " + documento);

            System.out.println("\n=== 3. ACTUALIZAR PACIENTE ===");
            paciente.setNombre("Ana María");
            dao.actualizarPaciente(paciente);

            String nombreGuardado = consultarNombre(id);
            if (!"Ana María".equals(nombreGuardado)) {
                throw new SQLException("No se pudo comprobar el cambio de nombre.");
            }
            System.out.println("ID " + id + " | Antes: Ana | Ahora: " + nombreGuardado);

            System.out.println("\n=== 4. ELIMINAR PACIENTE ===");
            dao.eliminarPaciente(id);

            if (buscarIdPorDocumento(documento) != 0) {
                throw new SQLException("El paciente de prueba todavía existe.");
            }
            System.out.println("Eliminación comprobada. El paciente con ID "
                    + id + " ya no figura en la tabla.");

            System.out.println("\nResultado: las cuatro operaciones CRUD se completaron.");

        } catch (SQLException e) {
            System.err.println("Prueba interrumpida: " + e.getMessage());
        }
    }

    private static int buscarIdPorDocumento(String documento) throws SQLException {
        String sql = "SELECT id_paciente FROM pacientes WHERE documento = ?";

        try (var cn = ConexionBD.conectar();
             var consulta = cn.prepareStatement(sql)) {
            consulta.setString(1, documento);

            try (var resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getInt("id_paciente");
                }
                return 0;
            }
        }
    }

    private static String consultarNombre(int id) throws SQLException {
        String sql = "SELECT nombre FROM pacientes WHERE id_paciente = ?";

        try (var cn = ConexionBD.conectar();
             var consulta = cn.prepareStatement(sql)) {
            consulta.setInt(1, id);

            try (var resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getString("nombre");
                }
                throw new SQLException("No se encontró el paciente con ID " + id);
            }
        }
    }
}