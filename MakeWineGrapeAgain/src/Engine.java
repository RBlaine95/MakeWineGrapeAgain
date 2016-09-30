/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Matthew
 */
public class Engine {
    ArrayList<String> colour;
    ArrayList<String> type;
    Statement s;
    Connection conn;
    ResultSet rs;

    public Engine() {
        this.colour = new ArrayList();
        this.type = new ArrayList();
    }

    public void connect() {
        try {
            
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("Driver loaded");

            String url = "jdbc:ucanaccess://ccdb.accdb";
            conn = DriverManager.getConnection(url);

            System.out.println("Database Connected");
            System.out.println("Setting Coloumns");
            this.setcols();
            System.out.println("Coloumns Set");
        } catch (Exception ex) {
            System.out.println("Error");
        }

    }

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

    public void setcols() throws SQLException {
        this.colour.add("All");
        this.type.add("All");
        String sql = "SELECT DISTINCT colour FROM batch";
        rs = this.query(sql);
        while(rs.next()){
            colour.add(rs.getNString(1));
        }
        sql = "SELECT DISTINCT type FROM batch";
        rs = this.query(sql);
        while(rs.next()){
            type.add(rs.getNString(1));
        }
    }

    public ArrayList<String> getColour() {
        return colour;
    }

    public void setColour(ArrayList<String> colour) {
        this.colour = colour;
    }

    public ArrayList<String> getType() {
        return type;
    }

    public void setType(ArrayList<String> type) {
        this.type = type;
    } 
}
