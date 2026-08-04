package principal;

import dao.PacienteDAO;
import modelo.Paciente;

public class Main {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("        MED-BAY");
        System.out.println(" Sistema de Gestión Médica");
        System.out.println("================================");

        // Crear objeto Paciente
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(6);
        // Asignar datos
        paciente.setNombre("Helmer Deyber");
        paciente.setApellido("Pérez Gómez");
        paciente.setDocumento("10203040");
        paciente.setEdad(33);
        paciente.setGenero("Masculino");
        paciente.setTelefono("3119876543");
        paciente.setCorreo("helmerdeyber@email.com");
        paciente.setDireccion("Medellín");

        // Crear DAO
        PacienteDAO dao = new PacienteDAO();

        // Eliminar el paciente con ID 6
        dao.eliminarPaciente(6);

        // Mostrar la lista actualizada
        dao.listarPacientes();
    }
}