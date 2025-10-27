package api;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
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

    public static List<Client> getClients() throws SQLException {
        Connection connection = connect();

        String sql = "SELECT * FROM clientes";
        List<Client> clients = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Client client = new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("observacao")
                );
                clients.add(client);
            }
        }
        return clients;
    }

    public static void addClient(String name, String cpf, String telefone, String observacao) throws SQLException {
        Connection connection = connect();
        String sql = "INSERT INTO clientes (name,cpf,telefone,observacao) VALUES (?,?,?,?)";
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setString(2,cpf);
        stmt.setString(3,telefone);
        stmt.setString(4,observacao);
        stmt.executeUpdate();
    }

    //Para teste
    public static void main(String[] args) {

    }
}