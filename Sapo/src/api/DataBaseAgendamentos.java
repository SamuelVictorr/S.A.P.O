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
                        rs.getString("id_clinica"),
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

    public static void addSchedule(String diaHora, String typeTreatment, String idClinic, String idClient, String nameDentist, String statusTreatment, String details) throws SQLException {
        Connection connection = connect();
        String sql = "INSERT INTO clientes (diahora, tipo_tratamento, id_clinica, id_cliente, id_dentista, status, detalhes) VALUES (?,?,?,?,?,?,?)";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, diaHora);
        stmt.setString(2, typeTreatment);
        stmt.setString(3, idClinic);
        stmt.setString(4, idClient);
        stmt.setString(5, nameDentist);
        stmt.setString(6, statusTreatment);
        stmt.setString(7, details);

        stmt.executeUpdate();
    }

    public static void main(String[] args) {
        try {
            addSchedule("24-04-2007", "Restauração dentária", "1", "1", "Everaldo", "Marcado", "Paciente Iabadabadu");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
