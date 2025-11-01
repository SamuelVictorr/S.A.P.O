package api;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataBase {
    public static Connection connect()  {
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

    public static boolean verifyNamePatient(String namePatient) {
        Pattern nameVerifierPattern = Pattern.compile("\\W");
        Matcher patternMatcher = nameVerifierPattern.matcher(namePatient);

        return patternMatcher.find();
    }
/*Árvore de possibilidades
* Criar uma variável de possibilidade onde "sql" muda toda vez (sql[n] (teria coisa pra k7), seria um algorítmo O(n²)) tier: B+ (é possível, mas inescalável)
* Fazer com que as funções se agrupem  (fazer um if dentro do outro e assim criar o input completo) tier: improvável
*  */

    public static void updateClient(String namePatient, String cpfPatient, String phoneNumberPatient, int numIdPatient) throws SQLException{
        Connection connection = connect();
        String sql = "";
        int situation = 0;

        if (!namePatient.isEmpty() && !cpfPatient.isBlank() && !phoneNumberPatient.isEmpty()){
            sql = "UPDATE clientes SET name = ?, telefone = ?, cpf = ? WHERE id = ?";
            situation = 1;
            System.out.println("All patient " + numIdPatient + " data got updated Successfully");
        } else if (!namePatient.isEmpty() && !cpfPatient.isBlank()){
            sql = "UPDATE clientes SET name = ?, telefone = (telefone), cpf = ? WHERE id = ? ";
            System.out.println("Name and CPF of the patient got changed");
            situation = 2;
        } else if (!namePatient.isEmpty() && !phoneNumberPatient.isEmpty()) {
            sql = "UPDATE clientes SET name = ? , telefone = ?, cpf = (cpf) WHERE id = ? ";
            System.out.println("Patient name and phoneNumber Updated Successfully");
            situation = 3;
        } else if (!cpfPatient.isBlank() && !phoneNumberPatient.isEmpty()) {
            sql = "UPDATE clientes SET name = (name) , telefone = ?, cpf = ? WHERE id = ?";
            situation = 4;
            System.out.println("Patient phoneNumber and CPF updated Successfully");
        } else if (!phoneNumberPatient.isEmpty()) {
            sql = "UPDATE clientes SET name = (name), telefone = ?, cpf = (cpf) WHERE id = ?";
            situation = 5;
            System.out.println("Patient's phoneNumber changed");
        }else if (!namePatient.isEmpty()) {
            sql = "UPDATE clientes SET name = ?, telefone = (telefone), cpf = (cpf) WHERE id = ?";
            situation = 6;
            System.out.println("Patient name updated Successfully");
        } else if (!cpfPatient.isBlank()) {
            sql = "UPDATE clientes SET name = (name), telefone = (telefone), cpf = ? WHERE id = ?";
            situation = 7;
            System.out.println("Patient CPF updated Successfully");
        } else {
            System.out.println("Nada mudou");
        }
        System.out.println(situation);
        var stmt = connection.prepareStatement(sql);
        System.out.println(sql);
        switch (situation) {
            case 1:
                stmt.setString(1, namePatient);
                stmt.setString(2, phoneNumberPatient);
                stmt.setString(3, cpfPatient);
                stmt.setInt(4, numIdPatient);
                break;
            case 2:
                stmt.setString(1, namePatient);
                stmt.setString(2, cpfPatient);
                stmt.setInt(3, numIdPatient);
                break;
            case 3:
                stmt.setString(1, namePatient);
                stmt.setString(2, phoneNumberPatient);
                stmt.setInt(3, numIdPatient);
                break;
            case 4:
                stmt.setString(1, phoneNumberPatient);
                stmt.setString(2, cpfPatient);
                stmt.setInt(3, numIdPatient);
                break;
            case 5:
                stmt.setString(1, phoneNumberPatient);
                stmt.setInt(2, numIdPatient);
                break;
            case 6:
                stmt.setString(1, namePatient);
                stmt.setInt(2, numIdPatient);
                break;
            case 7:
                stmt.setString(1, cpfPatient);
                stmt.setInt(2, numIdPatient);
                break;
            default:
                System.out.println("De alguma forma isso deu errado :D");
        }

        stmt.executeUpdate();

    }

    static boolean verifyCPF( String cpf) throws  SQLException{
        boolean cpfIsReal;
        int[] cpfNumberslist = new int[11];
        int penultimateDigitVerify;
        int lastDigitVerify;

        //Convert the cpf string with the "." in a version without that
        String cpfConversor1 = cpf.replace(".", "").replace("-", "");

        //Get all the element in the cpf and put them on an array
        for(int i = 0; i < cpfConversor1.length(); i ++) {
            String cpfElement = cpfConversor1.substring(i, (1 + i));
            int numCpf = Integer.parseInt(cpfElement);
            cpfNumberslist[i] = numCpf;
        }


        //Calculation to verify the 10th element of the CPF
        int calculo = 0;
        for (int i = 0; i < 9; i ++ ){
            calculo += (cpfNumberslist[i] * (10 - i));
        }
        int divisionPenultimateDigit = (calculo % 11);
        if(divisionPenultimateDigit > -1 && divisionPenultimateDigit < 2){
            penultimateDigitVerify = 0;
        } else {
            penultimateDigitVerify = (11 - divisionPenultimateDigit);
        }

        //Calculation to verify the 11th element of the CPF
        int calculo2 = penultimateDigitVerify * 2;
        for (int i = 0; i < 9; i ++ ){
            calculo2 += (cpfNumberslist[i] * (11 - i));
        }
        int divisionForLastDidig = (calculo2 % 11);
        if(divisionForLastDidig > -1 && divisionForLastDidig < 2){
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
    //Para teste
    public static void main(String[] args) {
            System.out.println(verifyNamePatient("G4abriel"));
//        try {
//            addClient("Gabriel","050.149.073.69","(81) 98369-7190","Sla" );
//            System.out.println(verifyCPF("175.108.554-62"));
//            removeClient("050.149.073.69");
//            updateClient( "","", "(81)98369-711190", 15);
//            if (verifyCPF("050.149.073.69")){
//                removeClient("050.149.073.69");
//                System.out.println("It works!!");
//            } else {
//                System.out.println("CPF não encontrado");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}