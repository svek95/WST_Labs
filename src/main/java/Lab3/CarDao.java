package Lab3;

import Lab1.CarDAO;
import Lab1.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CarDao extends Lab2.CarsDao{

    public boolean FindID(Integer id) {
        boolean isIdPresent = false;
        String query = "select count(*) from Cars where id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    isIdPresent = rs.getInt(1) == 1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return isIdPresent;
        }
    }

}
