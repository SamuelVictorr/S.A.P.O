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
                        rs.getInt("id_agendamentos"),
                        rs.getString("diahora"),
                        rs.getString("tipo_tratamento"),
                        rs.getString("id_cliente"),
                        rs.getString("id_dentista"),
                        rs.getString("status"),
                        rs.getString("detalhes")
                );
                schedule.add(schedules);
            }
        }
        return schedule;
    }

    public static void addSchedule(String diaHora, String typeTreatment, String idClient, String nameDentist, String statusTreatment, String details) throws SQLException {
        Connection connection = connect();
        String sql = "INSERT INTO agendamentos (diahora, tipo_tratamento, id_cliente, id_dentista, status, detalhes) VALUES (?,?,?,?,?,?)";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, diaHora);
        stmt.setString(2, typeTreatment);
        stmt.setString(3, idClient);
        stmt.setString(4, nameDentist);
        stmt.setString(5, statusTreatment);
        stmt.setString(6, details);

        stmt.executeUpdate();
    }
    public static void removeSchedule(boolean allData, int idSchedule) throws SQLException {
        Connection connection = connect();
        String novoEstado = "Finalizado";
        String sql = "";
        short situation = 0;
        if (!allData){
            sql = "UPDATE agendamentos SET status = ? WHERE id_agendamento = ?";
            situation = 1;
        } else {
            sql = "UPDATE agendamentos SET status = ?";
            situation = 2;
        }
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        if (situation == 1){
            stmt.setString(1, novoEstado);
            stmt.setInt(2, idSchedule);
        } else {
            stmt.setString(1, novoEstado);
        }
        stmt.executeUpdate();
    }
    public static void updateSchedule(String diahora, String typeTreatment, String clientId, String dentistId, String status, String details, int idSchedule) throws SQLException {
        Connection connection = connect();
        String sql = "UPDATE agendamentos SET diahora = ?, tipo_tratamento = ?, id_cliente = ? , id_dentista = ?, status = ?, detalhes = ? WHERE id_agendamento = ?";
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

    public static void main(String[] args) {
        try{
            addSchedule("24-04-2007", "24-04-2007","24-04-2007","24-04-2007","24-04-2007","24-04-2007");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
