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
 * @author Se7en
 */
public class Eddi {

    ArrayList<String> colour;
    ArrayList<String> type;
    ArrayList<String> supplier;
    Statement s;
    Connection conn;
    ResultSet rs;

    public Eddi() {
        this.colour = new ArrayList();
        this.type = new ArrayList();
        this.supplier = new ArrayList();
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
        this.colour = new ArrayList();
        this.type = new ArrayList();
        this.supplier = new ArrayList();
        String sql = "SELECT DISTINCT colour FROM batch";
        rs = this.query(sql);
        while (rs.next()) {
            colour.add(rs.getNString(1));
        }
        sql = "SELECT DISTINCT type FROM batch";
        rs = this.query(sql);
        while (rs.next()) {
            type.add(rs.getNString(1));
        }
        sql = "SELECT DISTINCT sname FROM supplier";
        rs = this.query(sql);
        while (rs.next()) {
            supplier.add(rs.getNString(1));
        }
    }

    public ArrayList<String> getSupplier() throws SQLException {
        this.setcols();
        return supplier;
    }

    public void setSupplier(ArrayList<String> supplier) {
        this.supplier = supplier;
    }

    public ArrayList<String> getColour() throws SQLException {
        this.setcols();
        return colour;
    }

    public void setColour(ArrayList<String> colour) {
        this.colour = colour;
    }

    public ArrayList<String> getType() throws SQLException {
        this.setcols();
        return type;
    }

    public void setType(ArrayList<String> type) {
        this.type = type;
    }

    public String stageGetWord(String s) {
        switch (s) {
            case "1":
                s = "Fermentation";
                break;
            case "2":
                s = "Pressed";
                break;
            case "3":
                s = "Maturation";
                break;
            case "4":
                s = "Blending";
                break;
            case "5":
                s = "Prep For Bottling";
                break;
            case "6":
                s = "Bottling";
                break;
            case "7":
                s = "Storage";
                break;
        }
        return s;
    }

    public String stageGetNo(String s) {
        switch (s) {
            case "Fermentation":
                s = "1";
                break;
            case "Pressed":
                s = "2";
                break;
            case "Maturation":
                s = "3";
                break;
            case "Blending":
                s = "4";
                break;
            case "Prep For Bottling":
                s = "5";
                break;
            case "Bottling":
                s = "6";
                break;
            case "Storage":
                s = "7";
                break;
        }
        return s;
    }

    public String[] getBatchData(String batch) throws SQLException {
        String [] data = new String [6];
        String sql = "SELECT * FROM batch WHERE batchid = '" + batch + "'";
        rs = this.query(sql);
        rs.next();
        for (int i = 0; i < data.length; i++) {
            data[i] = rs.getNString(i+1);
        }
        return data;
    }
    public String[] getSupplierData(String supplierid) throws SQLException {
        String [] data = new String [4];
        String sql = "SELECT * FROM supplier WHERE sname = '" + supplierid + "'";
        rs = this.query(sql);
        rs.next();
        for (int i = 0; i < data.length; i++) {
            data[i] = rs.getNString(i+1);
        }
        return data;
    }
    
}
