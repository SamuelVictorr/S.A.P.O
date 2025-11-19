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

        assert connection != null;
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
        String sql = "INSERT INTO clientes (name,cpf,telefone,observacao,active_state,data_nascimento,id_clinica) VALUES (?,?,?,?,?,?,?)";
        String status = "ativo";
        String dataNascimento = "nascido";
        String idClinica = "clinicado";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setString(2,cpf);
        stmt.setString(3,telefone);
        stmt.setString(4,observacao);
        stmt.setString(5,status);
        stmt.setString(6, dataNascimento);
        stmt.setString(7,idClinica);
        stmt.executeUpdate();
    }

    public static void removeClient(String cpf) throws SQLException{
        Connection connection = connect();
        String sql = "DELETE FROM clientes WHERE cpf = ?";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, cpf);
        stmt.executeUpdate();
    }

    public static void updateClient(String cpf, String telefone) throws SQLException{
        Connection connection = connect();
        String sql = "UPDATE clientes SET telefone = ? WHERE cpf = ? ";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, telefone);
        stmt.setString(2, cpf);
        stmt.executeUpdate();
    }

    //Teste p/ ver se dá para verificar dentro do próprio DB
    public static boolean verifyCPF(String cpf) {
        boolean resultado;
        int[] lista = new int[11];
        int digitoVerificador1;
        int digitoVerificador2;

        //Convert the cpf string with the "." in a version without that
        String stringModificada = cpf.replace(".", "");

        //Get all the element in the cpf and put them on an array
        for(int i = 0; i < stringModificada.length(); i ++) {
            String parteExtraida = stringModificada.substring(i, (1 + i));
            int numCpf = Integer.parseInt(parteExtraida);
            lista[i] = numCpf;
        }
        System.out.println(Arrays.toString(lista));

        //Calculation to verify the 10th element of the CPF
        int calculo = (lista[0] * 10) + (lista[1] * 9) + (lista[2] * 8) + (lista[3] * 7) + ( 6 * lista[4]) + (5 * lista[5]) + ( 4 * lista[6]) + ( lista[7] * 3)+ (lista[8] * 2);
        int divisaoVerificador1 = (calculo % 11);
        if(divisaoVerificador1 > -1 && divisaoVerificador1 < 2){
            digitoVerificador1 = 0;
        } else {
            digitoVerificador1 = (11 - divisaoVerificador1);
        }

        //Calculation to verify the 11th element of the CPF
        int calculo2 = (lista[0] * 11) + (lista[1] * 10) + (lista[2] * 9) + (lista[3] * 8) + ( 7 * lista[4]) + (6 * lista[5]) + ( 5 * lista[6]) + ( lista[7] * 4)+ (lista[8] * 3) + (digitoVerificador1 * 2);
        int divisaoVerificador2 = (calculo2 % 11);
        if(divisaoVerificador2 > -1 && divisaoVerificador2 < 2){
            digitoVerificador2 = 0;
        } else {
            digitoVerificador2 = (11 - divisaoVerificador2);
        }

        resultado = digitoVerificador1 == lista[9] && digitoVerificador2 == lista[10];
        return resultado;
    }

    //Para teste
    public static void main(String[] args) {
        try {
            removeClient("027.136.870.55");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}