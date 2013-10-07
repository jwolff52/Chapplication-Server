package chapplicationserver;

import chapplicationserver.GUI.LoginGUI;
import java.sql.*;

/**
 * @author james.wolff
 * @date Sep 20, 2013
 */
public class ChapplicationServer {
    /**
     * @param args the command line arguments
     */
    private static Statement stmt;
    private static String url, username, password;
    private static Connection con;
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        final LoginGUI l=new LoginGUI();
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginGUI().setVisible(true);
            }
        });
        while(l.isVisible());
    }
    public static boolean connect(String user, String pass) throws ClassNotFoundException, SQLException{
        username=user;
        password=pass;
        Class.forName("com.mysql.jdbc.Driver");
        url = "jdbc:mysql://92.82.64.168:4444/mysql";
        con = DriverManager.getConnection( url,username,password);
        return true;
    }
    /*
     * @param sqlCommand and String SQL Command eg. SELECT * FROM users
     */
    public static void executeUpdate(String sqlCommand) throws SQLException{
        stmt=con.createStatement();
        stmt.executeUpdate(sqlCommand);
    }
}
