package Lab1;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CarDAO {

    private Boolean addChecking(Object value, String fieldName, Boolean isNotFirst, StringBuilder query) {
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

    public List<Car> getDataByFields(String name, String director, String country, Date dateOfStart, Double duration) {
        List<Car> cars = Collections.emptyList();
        try (Connection connection = getConnection()) {
            StringBuilder query = new StringBuilder("select * from cars");
            boolean notFirstField = false;
            if (name != null || director != null || country != null && dateOfStart != null || duration != null) {
                query.append(" where ");
            }
            notFirstField = addChecking(name, "name", notFirstField, query);
            notFirstField = addChecking(director, "director", notFirstField, query);
            notFirstField = addChecking(country, "country", notFirstField, query);
            notFirstField = addChecking(dateOfStart, "dateOfStart", notFirstField, query);
            addChecking(duration, "duration", notFirstField, query);

            PreparedStatement stmt = connection.prepareStatement(query.toString());
            ResultSet rs = stmt.executeQuery();
            cars = extractFilmsFromResultSet(rs);
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cars;
    }

    private List<Car> extractFilmsFromResultSet(ResultSet rs) throws SQLException{
        List<Car> cars = new ArrayList<Car>();
        while (rs.next()) {
            String name = rs.getString("name");
            Date date = rs.getDate("dateOfStart");
            String director = rs.getString("director");
            String country = rs.getString("country");
            double duration = rs.getDouble("duration");

            Car car = new Car(name, date, country, duration, director);
            cars.add(car);
        } return cars;
    }
}
