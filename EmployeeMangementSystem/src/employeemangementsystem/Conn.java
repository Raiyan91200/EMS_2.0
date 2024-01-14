package employeemangementsystem;

import java.sql.*;

public class Conn {

    Connection c;
    Statement s;
    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///employeemangementsystem","root","91200");
            s= c.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
