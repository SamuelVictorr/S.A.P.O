package api;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseAgendamentos {
    public static Connection connect() {
        // connection string
        var url = "jdbc:sqlite:Sapo/src/api/mydb.db";

        try {
            var conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static List<Schedule> getSchedule() throws SQLException {
        Connection connection = connect();

        String sql = "SELECT * FROM agendamentos";
        List<Schedule> schedule = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Schedule schedules = new Schedule(
                        rs.getInt("id_agendamento"),
                        rs.getString("diahora"),
                        rs.getString("tipo_tratamento"),
                        rs.getString("nome_cliente"),
                        rs.getString("nome_dentista"),
                        rs.getString("id_dentista"),
                        rs.getString("status_tratamento"),
                        rs.getString("detalhes")
                );
                schedule.add(schedules);
            }
        }
        return schedule;
    }

    public static void addSchedule(String diaHora, String details, String statusTreatment, String nameDentist, String idClient, String clinicId, String treatmentType, String idDentista) throws SQLException {
        Connection connection = connect();
        String sql = "INSERT INTO agendamentos (diahora,detalhes, status_tratamento, id_clinica, id_cliente, nome_dentista, tipo_tratamento, id_dentista) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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

        stmt.executeUpdate();
    }

    public static void removeSchedule(int idSchedule) throws SQLException {
        Connection connection = connect();
        String novoEstado = "Finalzado";
        String sql = "UPDATE agendamentos SET status_tratamento = ? WHERE id_agendamento = ?";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, novoEstado);
        stmt.setInt(2, idSchedule);

        stmt.executeUpdate();
    }

    public static void updateSchedule(String diahora, String typeTreatment, String clientId, String dentistId, String status, String details, int idSchedule) throws SQLException {
        Connection connection = connect();
        String sql = "UPDATE agendamentos SET diahora = ?, tipo_tratamento = ?, id_cliente = ?, id_dentista = ?, status_tratamento = ?, detalhes = ? WHERE id_agendamento = ?";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, diahora);
        stmt.setString(2, typeTreatment);
        stmt.setString(3, clientId);
        stmt.setString(4, dentistId);
        stmt.setString(5, status);
        stmt.setString(6, details);
        stmt.setInt(7, idSchedule);
        stmt.executeUpdate();
    }

    // code to pull all the names added in the DB by itself ID
    public static void selectNameClientById() throws SQLException {
        Connection connection = connect();
        String sql = "UPDATE agendamentos AS a\n" +
                "SET\n" +
                "    nome_cliente = (SELECT c.name\n" +
                "                   FROM clientes AS c\n" +
                "                   WHERE c.id = a.id_cliente),\n" +
                "WHERE EXISTS (SELECT 1 \n" +
                "              FROM clientes AS c \n" +
                "              WHERE c.id = a.id_cliente);";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.executeUpdate();
    }
    public static void selectNameDentistById() throws SQLException {
        Connection connection = connect();
        String sql = "UPDATE agendamentos AS a\n" +
                "SET\n" +
                "    nome_dentista = (SELECT f.name_funcionario\n" +
                "                   FROM funcionarios AS f\n" +
                "                   WHERE f.id_funcionarios = a.id_dentista)\n" +
                "WHERE EXISTS (SELECT 1 \n" +
                "              FROM clientes AS c \n" +
                "              WHERE c.id = a.id_cliente);";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.executeUpdate();
    }

    public static void main(String[] args) {
        try{
            updateSchedule("12/05/2023 18:30","Ala","5","1","Finalizado","Clareamento dental",1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
