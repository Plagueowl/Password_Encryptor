import java.sql.*;
import java.nio.channels.ScatteringByteChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class DbOp {
    static String url = "jdbc:mysql://localhost:3306/college";
    static String user = "root";
    static String pwd = "";


    public void insert(String password, String salt, String userName) {
        try {
            Connection con = DriverManager.getConnection(url, user, pwd);
            String statement = "Insert into user_passwords values('" + password + "','" + salt + "','" + userName + "')";

            Statement st = con.createStatement();
            st.executeUpdate(statement);
            System.out.println("Inserted into database suxxessfully");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void getData(List<UserInfo> lst){
        PreparedStatement p = null;
        ResultSet rs = null;
        try {
            Connection con = DriverManager.getConnection(url, user, pwd);
            // SQL command data stored in String datatype
            String sql = "select * from user_passwords";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            while (rs.next()) {
                UserInfo entry = new UserInfo(rs.getString("username"),rs.getString("Salt"),rs.getString("Password"));
                lst.add(entry);
            }
            con.close();
        }

        // Catch block to handle exception
        catch (SQLException e) {

            // Print exception pop-up on screen
            System.out.println(e);
        }
    }
    public void delete(String userName) {
        try {
            Connection con = DriverManager.getConnection(url, user, pwd);
            String statement = "Delete from user_passwords where userName='"+userName+"'";

            Statement st = con.createStatement();
            st.executeUpdate(statement);
            System.out.println("Deleted from database suxxessfully");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}





