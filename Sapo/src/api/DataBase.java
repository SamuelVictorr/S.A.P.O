package api;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataBase {

    // Function to connect the jdbc on DB
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

    //List to pull all client registered on DB
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
                        rs.getString("observacao"),
                        rs.getString("active_state"),
                        rs.getString("data_nascimento"),
                        rs.getString("id_clinica")
                );
                clients.add(client);
            }
        }
        return clients;
    }

    //Input a new client on DB
    public static void addClient(String name, String cpf, String telefone, String observacao, String activeState, String birthDate, String clinicId) throws SQLException {

        Connection connection = connect();
        String sql = "INSERT INTO clientes (name,cpf,telefone,observacao, active_state, data_nascimento, id_clinica) VALUES (?, ?, ?, ?, ?, ?, ?)";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setString(2,cpf);
        stmt.setString(3,telefone);
        stmt.setString(4,observacao);
        stmt.setString(5,activeState);
        stmt.setString(6,birthDate);
        stmt.setString(7,clinicId);
        stmt.executeUpdate();
    }

    //Change client status_active to it don't show on screen
    public static void removeClient(String clientId) throws SQLException{
        Connection connection = connect();
        String newState = "Desativado";
        String sql = "UPDATE  clientes  SET active_state = ? WHERE id = ?";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, newState);
        stmt.setString(2, clientId);
        stmt.executeUpdate();
    }

    // Update client info on DB, logically it's a specific client. That's identify
    public static void updateClient(String name, String cpf, String telefone, String observacao, String activeState, String birthDate, String clinicId, String clientId) throws SQLException{
        Connection connection = connect();
        String sql = "UPDATE clientes SET name = ? ,cpf = ?,telefone = ?,observacao = ?, active_state = ?, data_nascimento = ?, id_clinica = ? WHERE id = ?";
        assert connection != null;
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setString(2,cpf);
        stmt.setString(3,telefone);
        stmt.setString(4,observacao);
        stmt.setString(5,activeState);
        stmt.setString(6,birthDate);
        stmt.setString(7,clinicId);
        stmt.setString(8, clientId);
        stmt.executeUpdate();
    }

    //Verify Cpf input on code
    public static boolean verifyCPF(String cpf) {
        boolean resultado;
        int[] lista = new int[11];
        int digitoVerificador1;
        int digitoVerificador2;

        //Convert the cpf string with the "." in a version without that
        String stringModificada = cpf.replace(".", "").replace("-","");

        // Do a regex to not accept non digit in DB
        Pattern pattern = Pattern.compile("\\D");
        Matcher matcher = pattern.matcher(stringModificada);

        if (matcher.find()) {
            return false;
        }


        //Get all the element in the cpf and put them on an array
        for(int i = 0; i < stringModificada.length(); i ++) {
            String parteExtraida = stringModificada.substring(i, (1 + i));
            int numCpf = Integer.parseInt(parteExtraida);
            lista[i] = numCpf;
        }
        System.out.println(Arrays.toString(lista));

        //Calculation to verify the 10th element of the CPF
        int calculo = 0;
        for(int i = 10; i < 1; i-- ){
            calculo += lista[i] * i;
        }
        int divisaoVerificador1 = (calculo % 11);
        if(divisaoVerificador1 > -1 && divisaoVerificador1 < 2){
            digitoVerificador1 = 0;
        } else {
            digitoVerificador1 = (11 - divisaoVerificador1);
        }

        //Calculation to verify the 11th element of the CPF
        int calculo2 = digitoVerificador1 * 2;
        for( int j = 11; j < 2; j-- ){
            calculo2 += lista[j] * j;
        }
        int divisaoVerificador2 = (calculo2 % 11);
        if(divisaoVerificador2 > -1 && divisaoVerificador2 < 2){
            digitoVerificador2 = 0;
        } else {
            digitoVerificador2 = (11 - divisaoVerificador2);
        }

        resultado = digitoVerificador1 == lista[9] && digitoVerificador2 == lista[10];
        return resultado;
    }

}