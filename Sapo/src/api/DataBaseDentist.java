package api;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DataBaseDentist {
    private static Connection connection = DataBase.connect();

    public static List<Professionals> getDentists() throws SQLException {
        String sql = "SELECT f.*" +
                "FROM funcionarios AS f" +
                "WHERE tipo_funcionario = 'Dentista'";

        List<Professionals> dentistList = new ArrayList<>();

        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                Professionals dentist = new Professionals(
                        rs.getString("name_funcionario"),
                        rs.getString("cpf_funcionario"),
                        rs.getString("data_nascimento"),
                        rs.getString("cro")
                );
                dentistList.add(dentist);
            }
        }
        return dentistList;
    }

    public static void addDentist(String dentistName, String dentistCpf, String dentistBirthDate, String dentistCro, Integer clinicId) throws SQLException {
        String sql = "INSERT INTO funcionarios(name_funcionario, data_nascimento, tipo_funcionario, cpf_funcionario, cro id_clinica)" +
                "VALUES (?, ?, ?, ?, ?, ? )";
        String employerType = "Dentista";
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, dentistName);
        stmt.setString(2, dentistBirthDate);
        stmt.setString(3, employerType);
        stmt.setString(4, dentistCpf);
        stmt.setString(5, dentistCro);
        stmt.setInt(6, clinicId);

        stmt.executeUpdate();
    }

    public static void updateDentistInfo(String dentistName, String dentistCpf, String dentistBirthDate, String dentistCro, Integer clinicId) throws SQLException {
        String sql = "UPDATE funcionarios " +
                "SET name_funcionario = ?, data_nascimento = ?, cpf_funcionario = ? , cro = ?, id_clinica = ?)";
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, dentistName);
        stmt.setString(2, dentistBirthDate);
        stmt.setString(3, dentistCpf);
        stmt.setString(4, dentistCro);
        stmt.setInt(5, clinicId);

        stmt.executeUpdate();
    }

    public static void deleteDentistInfo(Integer idDentist) throws SQLException {
        String sqlCommand = "DELETE FROM funcionarios WHERE id_funcionario = ?";
        var stmt = connection.prepareStatement(sqlCommand);
        stmt.setInt(1, idDentist);
        stmt.executeUpdate();
    }

}
