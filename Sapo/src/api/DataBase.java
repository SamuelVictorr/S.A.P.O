package api;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static void addClient(String name, String cpf, String telefone, String observacao, String activeState, String dateBirthday, String clinicId) throws SQLException {
        Connection connection = connect();
        String sql = "INSERT INTO clientes (name,cpf,telefone,observacao, active_state, data_nascimento, id_clinica) VALUES (?,?,?,?,?,?,?)";
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, cpf);
        stmt.setString(3, telefone);
        stmt.setString(4, observacao);
        stmt.setString(5, activeState);
        stmt.setString(6, dateBirthday);
        stmt.setString(7, clinicId);

        stmt.executeUpdate();
    }

    public static void removeClient() throws SQLException {
        Connection connection = connect();
        String sql = "DELETE FROM clientes WHERE cpf = ?";
        var stmt = connection.prepareStatement(sql);
        stmt.executeUpdate();
    }

    // Verificar se há algum caractere especial, qualquer que seja e onde seja e se o nome está escrito sem nenhum espaço)
    public static boolean verifyText(String namePatient) {
        Pattern textHasSpecChar = Pattern.compile("\\p{Punct}");
        Matcher haveSpecChar = textHasSpecChar.matcher(namePatient);

        Pattern textHasWhiteSpace = Pattern.compile("\\s");
        Matcher haveWhiteSpace = textHasWhiteSpace.matcher(namePatient);


        if (haveSpecChar.find() && haveWhiteSpace.find()) {
            return false;
        } else {
            return haveWhiteSpace.find();
        }
    }
    /*Árvore de possibilidades
     * Criar uma variável de possibilidade onde "sql" muda toda vez (sql[n] (teria coisa pra k7), seria um algorítmo O(n²)) tier: B+ (é possível, mas inescalável)
     * Fazer com que as funções se agrupem  (fazer um if dentro do outro e assim criar o input completo) tier: improvável
     *  */

    public static void updateClient(String namePatient, String cpfPatient, String phoneNumberPatient, int numIdPatient) throws SQLException {
        Connection connection = connect();
        String sql = "UPDATE clientes SET name = ?, telefone = ?, cpf = ? WHERE id = ?";
        System.out.println("All patient " + numIdPatient + " data got updated Successfully");
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, namePatient);
        stmt.setString(2, phoneNumberPatient);
        stmt.setString(3, cpfPatient);
        stmt.setInt(4, numIdPatient);
        stmt.executeUpdate();
    }

    public static boolean verifyCPF(String cpf) {
        boolean cpfIsReal;
        int[] cpfNumberslist = new int[11];
        int penultimateDigitVerify;
        int lastDigitVerify;

        //Convert the cpf string with the "." in a version without that
        String cpfConversor1 = cpf.replace(".", "").replace("-", "");

        //Get all the element in the cpf and put them on an array
        for (int i = 0; i < cpfConversor1.length(); i++) {
            String cpfElement = cpfConversor1.substring(i, (1 + i));
            int numCpf = Integer.parseInt(cpfElement);
            cpfNumberslist[i] = numCpf;
        }


        //Calculation to verify the 10th element of the CPF
        int calculo = 0;
        for (int i = 0; i < 9; i++) {
            calculo += (cpfNumberslist[i] * (10 - i));
        }
        int divisionPenultimateDigit = (calculo % 11);
        if (divisionPenultimateDigit > -1 && divisionPenultimateDigit < 2) {
            penultimateDigitVerify = 0;
        } else {
            penultimateDigitVerify = (11 - divisionPenultimateDigit);
        }

        //Calculation to verify the 11th element of the CPF
        int calculo2 = penultimateDigitVerify * 2;
        for (int i = 0; i < 9; i++) {
            calculo2 += (cpfNumberslist[i] * (11 - i));
        }
        int divisionForLastDidig = (calculo2 % 11);
        if (divisionForLastDidig > -1 && divisionForLastDidig < 2) {
            lastDigitVerify = 0;
        } else {
            lastDigitVerify = (11 - divisionForLastDidig);
        }
        System.out.println(calculo);
        System.out.println(calculo2);
        System.out.println(penultimateDigitVerify);
        System.out.println(lastDigitVerify);
        System.out.println(cpfConversor1);


        cpfIsReal = penultimateDigitVerify == cpfNumberslist[9] && lastDigitVerify == cpfNumberslist[10];

        return cpfIsReal;

    }
//    Para teste
    public static void main(String[] args) {
        try {
//            removeClient();
            addClient("Gabriel", "050", "81 9090", "Ala ", "ativo", "24/04/2007", "1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


