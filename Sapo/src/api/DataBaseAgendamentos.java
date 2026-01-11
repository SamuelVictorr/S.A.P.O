package api;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DataBaseAgendamentos {

    //Pull appointment info from DB
    public static List<Schedule> getSchedules() throws SQLException {
        Connection connection = DataBase.connect();

        String sql = "SELECT a.*, ct.name, f.name_funcionario, c.nome_fantasia\n" +
                "FROM agendamentos as a \n" +
                "LEFT JOIN clientes as ct, funcionarios as f, clinicas as c \n" +
                "ON a.id_cliente = ct.id AND f.tipo_funcionario = 'Dentista' ";
        List<Schedule> schedule = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Schedule schedules = new Schedule(
                        rs.getInt("id_agendamento"),
                        rs.getString("diahora"),
                        rs.getString("name_funcionario"),
                        rs.getString("status_tratamento"),
                        rs.getString("tipo_tratamento"),
                        rs.getString("detalhes"),
                        rs.getString("name")
                );
                schedule.add(schedules);
            }
        }
        return schedule;
    }

    //Register a new appointment in DB
    public static void addSchedule(String diaHora, String details, String statusTreatment, String nameDentist, String idClient, String clinicId, String treatmentType, String idDentista, String nameClient) throws SQLException {
        Connection connection = DataBase.connect();
        String sql = "INSERT INTO agendamentos (diahora,detalhes, status_tratamento, id_clinica, id_cliente, nome_dentista, tipo_tratamento, id_dentista, nome_cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, diaHora);
        stmt.setString(2, details);
        stmt.setString(3, statusTreatment);
        stmt.setString(4, clinicId);
        stmt.setString(5, idClient);
        stmt.setString(6, nameDentist);
        stmt.setString(7, treatmentType);
        stmt.setString(8, idDentista);
        stmt.setString(9, nameClient);

        stmt.executeUpdate();
    }

    //Update treatmentStatus to "Finalized"
    public static void removeSchedule(int idSchedule) throws SQLException {
        Connection connection = DataBase.connect();
        String novoEstado = "Finalzado";
        String sql = "UPDATE agendamentos SET status_tratamento = ? WHERE id_agendamento = ?";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, novoEstado);
        stmt.setInt(2, idSchedule);

        stmt.executeUpdate();
    }

    //Update all info related to a specific appointment on DB
    public static void updateSchedule(String diahora, String typeTreatment, int clientId, String dentistId, String status, String details, int idSchedule) throws SQLException {
        Connection connection = DataBase.connect();
        String sql = "UPDATE agendamentos SET diahora = ?, tipo_tratamento = ?, id_cliente = ?, id_dentista = ?, status_tratamento = ?, detalhes = ? WHERE id_agendamento = ?";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, diahora);
        stmt.setString(2, typeTreatment);
        stmt.setString(3, String.valueOf(clientId));
        stmt.setString(4, dentistId);
        stmt.setString(5, status);
        stmt.setString(6, details);
        stmt.setInt(7, idSchedule);
        stmt.executeUpdate();
    }

    //Update treatmentStatus of a specific appointment
    public static void updateStatusSchedule(String newTreatmentStatus, int idSchedule) throws SQLException {
        Connection connection = DataBase.connect();
        String sql = "UPDATE agendamentos SET status_tratamento = ? WHERE id_agendamento = ? ";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, newTreatmentStatus);
        stmt.setInt(2, idSchedule);
        stmt.executeUpdate();
    }

}