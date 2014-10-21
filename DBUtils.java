import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

    private static Connection con;

    public static void reset(String dbDriver, String url, String user,
            String pwd) throws SQLException {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (con != null)
            con.close();
        con = DriverManager.getConnection(url, user, pwd);
    }

    public static ResultSet readData(String sql) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    public static int updateData(String sql) throws SQLException {
        Statement stmt = con.createStatement();
        int affectedNum = stmt.executeUpdate(sql);
        return affectedNum;
    }

    public static void close() throws SQLException {
        if (con != null)
            con.close();
    }

}
