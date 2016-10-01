
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Se7en
 */
public class Kenji {

    Statement s;
    Connection conn;
    ResultSet rs;

    public void connect() {
        try {

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("Driver loaded");

            String url = "jdbc:ucanaccess://chemdb.accdb";
            conn = DriverManager.getConnection(url);
            System.out.println("Database Connected");

        } catch (Exception ex) {
            System.out.println("Error");
        }
    }

    public void createChem(String s) {
        String sql = "CREATE TABLE " + s + " (chemical varchar(100), amount varchar(15), value varchar(20))";
        this.update(sql);

    }
    /*SELECT * FROM INFORMATION_SCHEMA.TABLES ----- FOR SEARCHING LATER
     WHERE TABLE_NAME LIKE '%%'*/

    public ResultSet query(String sql) throws SQLException {
        s = conn.createStatement();
        rs = s.executeQuery(sql);
        return rs;
    }

    public boolean update(String sql) {
        boolean delete;
        try {
            s = conn.createStatement();

            s.executeUpdate(sql);
            delete = true;
            return delete;
        } catch (SQLException ex) {
            delete = false;
            return delete;
        }

    }

    public String[] getChemData(String chem) throws SQLException {
        String[] data = new String[6];
        String sql = "SELECT * FROM chemicaltbl WHERE chemical = '" + chem + "'";
        rs = this.query(sql);
        rs.next();
        for (int i = 0; i < data.length; i++) {
            data[i] = rs.getNString(i + 1);
        }
        return data;
    }
}
