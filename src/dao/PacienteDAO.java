package dao;

import conexion.ConexionBD;
import modelo.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteDAO {

    // ==========================================
    // REGISTRAR PACIENTE (INSERT)
    // ==========================================
    public void registrarPaciente(Paciente paciente) {

        String sql = """
                INSERT INTO pacientes
                (nombre, apellido, documento, edad, genero, telefono, correo, direccion)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getDocumento());
            ps.setInt(4, paciente.getEdad());
            ps.setString(5, paciente.getGenero());
            ps.setString(6, paciente.getTelefono());
            ps.setString(7, paciente.getCorreo());
            ps.setString(8, paciente.getDireccion());

            ps.executeUpdate();

            System.out.println("================================");
            System.out.println("✅ Paciente registrado correctamente");
            System.out.println("================================");

            ps.close();
            conexion.close();

        } catch (SQLException e) {

            System.out.println("❌ Error al registrar el paciente.");
            e.printStackTrace();

        }
    }

    // ==========================================
    // LISTAR PACIENTES (SELECT)
    // ==========================================
    public void listarPacientes() {

        String sql = "SELECT * FROM pacientes";

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps = conexion.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== LISTA DE PACIENTES =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id_paciente") + " | " +
                        rs.getString("nombre") + " | " +
                        rs.getString("apellido") + " | " +
                        rs.getString("documento"));

            }

            rs.close();
            ps.close();
            conexion.close();

        } catch (SQLException e) {

            System.out.println("❌ Error al consultar pacientes.");
            e.printStackTrace();

        }

    }

    // ==========================================
    // ACTUALIZAR PACIENTE (UPDATE)
    // ==========================================
    public void actualizarPaciente(Paciente paciente) {

        String sql = """
                UPDATE pacientes
                SET nombre = ?, apellido = ?, documento = ?, edad = ?, genero = ?, telefono = ?, correo = ?, direccion = ?
                WHERE id_paciente = ?
                """;

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getDocumento());
            ps.setInt(4, paciente.getEdad());
            ps.setString(5, paciente.getGenero());
            ps.setString(6, paciente.getTelefono());
            ps.setString(7, paciente.getCorreo());
            ps.setString(8, paciente.getDireccion());
            ps.setInt(9, paciente.getIdPaciente());

            int filas = ps.executeUpdate();

            if (filas > 0) {

                System.out.println("================================");
                System.out.println("✅ Paciente actualizado correctamente");
                System.out.println("================================");

            } else {

                System.out.println("⚠ No se encontró el paciente.");

            }

            ps.close();
            conexion.close();

        } catch (SQLException e) {

            System.out.println("❌ Error al actualizar paciente.");
            e.printStackTrace();

        }

    }
    
    // ==========================================
    // ELIMINAR PACIENTE (DELETE)
    // ==========================================
    public void eliminarPaciente(int idPaciente) {

        String sql = "DELETE FROM pacientes WHERE id_paciente = ?";

        try {

            Connection conexion = ConexionBD.conectar();

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, idPaciente);

            int filas = ps.executeUpdate();

            if (filas > 0) {

                 System.out.println("================================");
                 System.out.println("✅ Paciente eliminado correctamente");
                 System.out.println("================================");

            } else {

             System.out.println("⚠ No existe un paciente con ese ID.");

            }

               ps.close();
               conexion.close();

        }  catch (SQLException e) {

            System.out.println("❌ Error al eliminar el paciente.");
            e.printStackTrace();

        }

    }
}