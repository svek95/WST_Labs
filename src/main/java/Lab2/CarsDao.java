package Lab2;

import Lab1.CarDAO;
import Lab1.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarsDao {

    public static Integer insertDB(String name, String model, String country, Date dateOfSales, int power) {
        String query = " insert into cars (name, model, country, dateOfSales, power)"
                + " values (?, ?, ?, ?, ?)";
        int index = 1;
        Integer id = null;
        try (Connection connection = ConnectionUtil.getConnection();) {

            try (PreparedStatement preparedStmt = connection.prepareStatement(query)) {
                preparedStmt.setString(index++, name);
                preparedStmt.setString(index++, model);
                preparedStmt.setDate(index++, dateOfSales != null
                        ? new java.sql.Date(dateOfSales.getTime())
                        : null);
                preparedStmt.setString(index++, country);
                preparedStmt.setObject(index, power);
                preparedStmt.executeUpdate();
                try (ResultSet result = connection.createStatement().executeQuery("select max(id) as maxid from cars")) {
                    result.next();
                    id = result.getInt("maxid");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    private static void addUpdatedValues(Object value, String fieldName, StringBuilder query) {
        if (value != null) {
            if (value instanceof String) {
                query.append(fieldName).append(" = '").append(value).append("'").append(", ");
            } else {
                query.append(fieldName).append(" = ").append(value).append(',');
            }
        }
    }

    public static CheckStatus updateDB(Integer id, String name, String model, String country, Date dateOfSales, int power) {
        StringBuilder query = new StringBuilder(" update cars set ");
        addUpdatedValues(name, "name", query);
        addUpdatedValues(model, "model", query);
        addUpdatedValues(country, "country", query);
        addUpdatedValues(dateOfSales, "dateOfSales", query);
        addUpdatedValues(power, "power", query);
        query.replace(query.lastIndexOf("."), query.length(), " ");
        query.append(" where id = " + id);
        try (Connection connection = ConnectionUtil.getConnection();) {
            try (PreparedStatement preparedStmt = connection.prepareStatement(query.toString())) {
                preparedStmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
            return CheckStatus.FAIL;
        }
        return CheckStatus.SUCCESS_UPDATE;
    }

    public static CheckStatus deleteDB(Integer id) {
        String query = "DELETE FROM cars WHERE id = " + id;
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStmt = connection.prepareStatement(query)) {
                preparedStmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
            return CheckStatus.FAIL;
        }
        return CheckStatus.SUCCESS_DELETE;
    }

}
