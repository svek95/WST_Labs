package Lab1;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CarDAO {

    private Boolean CheckIN(Object value, String fieldName, Boolean isNotFirst, StringBuilder query) {
        if (value != null) {
            if (isNotFirst) {
                query.append(" and ");
            }
            if (value instanceof String) {
                query.append(fieldName).append(" = '").append(value).append("'");
            } else {
                query.append(fieldName).append(" = ").append(value);
            }
            if (!isNotFirst) {
                return true;
            }
        }
        return isNotFirst;
    }

    public abstract Connection getConnection();

    public List<Car> getDataByFields(String name, String model, String country, String dateOfSales, int power) {
        List<Car> cars = Collections.emptyList();
        try (Connection connection = getConnection()) {
            StringBuilder query = new StringBuilder("select * from cars");
            boolean notFirstField = false;
            if (name != null || model != null || country != null && dateOfSales != null || power != 0) {
                query.append(" where ");
            }
            notFirstField = CheckIN(name, "name", notFirstField, query);
            notFirstField = CheckIN(model, "model", notFirstField, query);
            notFirstField = CheckIN(country, "country", notFirstField, query);
            notFirstField = CheckIN(dateOfSales, "dateOfSales", notFirstField, query);
            CheckIN(power, "power", notFirstField, query);

            PreparedStatement stmt = connection.prepareStatement(query.toString());
            ResultSet rs = stmt.executeQuery();
            cars = extractCarsFromResult(rs);
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cars;
    }

    private List<Car> extractCarsFromResult(ResultSet rs) throws SQLException{
        List<Car> cars = new ArrayList<Car>();
        while (rs.next()) {
            String name = rs.getString("name");
            Date date = rs.getDate("dateOfSales");
            String model = rs.getString("model");
            String country = rs.getString("country");
            double power = rs.getDouble("power");

            Car car = new Car(name, date, country, power, model);
            cars.add(car);
        } return cars;
    }
}
