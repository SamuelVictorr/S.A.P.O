package api;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static void removeClient(String cpf) throws SQLException{
        Connection connection = connect();
        String sql = "DELETE FROM clientes WHERE cpf = ? ";
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, cpf);
        stmt.executeUpdate();
    }

    public static void updateClient(String cpf, String telefone) throws SQLException{
        Connection connection = connect();
        String sql = "UPDATE clientes SET telefone = ? WHERE cpf = ? ";
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, telefone);
        stmt.setString(2, cpf);
        stmt.executeUpdate();
    }
    //Teste p/ ver se dá para verificar dentro do próprio DB
    static boolean verifyCPF( String cpf) throws SQLException{
        boolean resultado;
        int[] lista = new int[11];
        int digitoVerificador1;
        int digitoVerificador2;

        for(int i = 0; i < cpf.length(); i ++) {
            String parteExtraida = cpf.substring(i, (1 + i));
            int numCpf = Integer.parseInt(parteExtraida);
            lista[i] = numCpf;
        }
        int calculo = (lista[0] * 10) + (lista[1] * 9) + (lista[2] * 8) + (lista[3] * 7) + ( 6 * lista[4]) + (5 * lista[5]) + ( 4 * lista[6]) + ( lista[7] * 3)+ (lista[8] * 2);
        System.out.println(calculo);
        int divisaoVerificador1 = (calculo % 11);
        if(divisaoVerificador1 > -1 && divisaoVerificador1 < 2){
            digitoVerificador1 = 0;
        } else {
            digitoVerificador1 = (11 - divisaoVerificador1);
        }
        int calculo2 = (lista[0] * 11) + (lista[1] * 10) + (lista[2] * 9) + (lista[3] * 8) + ( 7 * lista[4]) + (6 * lista[5]) + ( 5 * lista[6]) + ( lista[7] * 4)+ (lista[8] * 3) + (digitoVerificador1 * 2);
        System.out.println(calculo2);
        int divisaoVerificador2 = (calculo2 % 11);

        if(divisaoVerificador2 > -1 && divisaoVerificador2 < 2){
            digitoVerificador2 = 0;
        } else {
            digitoVerificador2 = (11 - divisaoVerificador2);
        }

        if (digitoVerificador1 == lista [9] && digitoVerificador2 == lista[10]){
            resultado = true;
        } else {
            resultado = false;
        }

        return resultado;

    }
    //Para teste
    public static void main(String[] args) {
        try {
//            addClient("Gabriel","050.149.073.69","(81) 98369-7190","Sla" );
//            removeClient("050.149.073.69");
//            updateClient("050.149.073.69", "(81) 98369-7190");
            if (verifyCPF("05014907369")){
//                removeClient("050.149.073.69");
                System.out.println("It works!!");
            } else {
                System.out.println("CPF não encontrado");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}