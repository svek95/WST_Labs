package Lab4.common;


import java.sql.Connection;

public class StandaloneCarDAO extends CarDAO {
    @Override
    public Connection getConnection() {
        return ConnectionUtil.getConnection();
    }
}
