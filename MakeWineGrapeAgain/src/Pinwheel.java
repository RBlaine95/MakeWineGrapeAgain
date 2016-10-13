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
public final class Pinwheel {

    static ArrayList<String> colour = new ArrayList();
    static ArrayList<String> type = new ArrayList();
    static ArrayList<String> supplier = new ArrayList();
    static Statement s;
    static Connection conn;
    static ResultSet rs;
    static Statement sChem;
    static Connection connChem;
    static ResultSet rsChem;
    static String sql;

    public static void connect() {
        connectChem();
        connectCCDB();
    }

    private static void connectChem() {
        try {

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("Driver loaded");

            String url = "jdbc:ucanaccess://chemdb.accdb";
            connChem = DriverManager.getConnection(url);
            System.out.println("Database Connected");

        } catch (Exception ex) {
            System.out.println("Error");
        }
    }

    public static void createChem(String s) {
        sql = "CREATE TABLE " + s + " (chemical varchar(100), amount varchar(15), value varchar(20))";
        updateChem(sql);

    }
    /*SELECT * FROM INFORMATION_SCHEMA.TABLES ----- FOR SEARCHING LATER
     WHERE TABLE_NAME LIKE '%%'*/

    public static ResultSet queryChem(String sql) throws SQLException {
        sChem = connChem.createStatement();
        rsChem = sChem.executeQuery(sql);
        return rsChem;
    }

    public static void updateChem(String sql) {

        try {
            sChem = connChem.createStatement();
            sChem.executeUpdate(sql);
        } catch (SQLException ex) {

        }

    }

    public static String[] getChemData(String chem) throws SQLException {
        String[] data = new String[6];
        String sql = "SELECT * FROM chemicaltbl WHERE chemical = '" + chem + "'";
        rsChem = queryChem(sql);
        rsChem.next();
        for (int i = 0; i < data.length; i++) {
            data[i] = rsChem.getNString(i + 1);
        }
        return data;
    }

    private static void connectCCDB() {
        try {

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("Driver loaded");

            String url = "jdbc:ucanaccess://ccdb.accdb";
            conn = DriverManager.getConnection(url);

            System.out.println("Database Connected");
        } catch (Exception ex) {
            System.out.println("Error");
        }

    }

    public static ResultSet queryCCDB(String sql) throws SQLException {
        s = conn.createStatement();
        rs = s.executeQuery(sql);
        return rs;
    }

    public static boolean updateCCDB(String sql) {
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

    public static ArrayList<String> getSupplier() throws SQLException {

        String sql = "SELECT DISTINCT sname FROM supplier";
        rs = queryCCDB(sql);
        while (rs.next()) {
            supplier.add(rs.getNString(1));
        }
        return supplier;
    }

    public static void setSupplier(ArrayList<String> supplier) {
        supplier = supplier;
    }

    public static ArrayList<String> getColour() throws SQLException {
        colour = new ArrayList();
        String sql = "SELECT DISTINCT colour FROM batch";
        rs = queryCCDB(sql);
        while (rs.next()) {
            colour.add(rs.getNString(1));
        }
        return colour;
    }

    public static void setColour(ArrayList<String> colour) {
        colour = colour;
    }

    public static ArrayList<String> getType() throws SQLException {
        String sql = "SELECT DISTINCT type FROM batch";
        rs = queryCCDB(sql);
        while (rs.next()) {
            type.add(rs.getNString(1));
        }
        return type;
    }

    public static void setType(ArrayList<String> type) {
        type = type;
    }

    public static String stageGetWord(String s) {
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

    public static String stageGetNo(String s) {
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

    public static void updateAll(String[] data, String id) throws SQLException {
        String sql = "";
        if (data.length == 6) {
            data[3] = stageGetNo(data[3]);
            sql = "UPDATE batch SET batchid = '" + data[0] + "', colour = '" + data[1] + "', type = '" + data[2]
                    + "', stage = '" + data[3] + "', mass = '" + data[4]
                    + "', supplierid = '" + data[5] + "' WHERE batchid = '" + id + "'";
            updateCCDB(sql);
        } else if (data.length == 4) {
            sql = "UPDATE supplier SET sname = '" + data[0] + "', tel = '" + data[1] + "', email = '" + data[2]
                    + "', liason = '" + data[3] + "' WHERE sname = '" + id + "'";
            updateCCDB(sql);
        } else if (data.length == 2) {
            sql = "UPDATE chemicaltbl SET chemical = '" + data[0] + "', chemvalue = '" + data[1] + "' WHERE chemical = '" + id + "'";
            runChems(data, id);
        }
    }

    public static void deleteAll(String[] data, String id) throws SQLException {
        String sql = "";
        if (data.length == 6) {
            data[3] = stageGetNo(data[3]);
            sql = "DELETE * FROM batch WHERE batchid = '" + data[0] + "'";
            updateCCDB(sql);
        } else if (data.length == 4) {
            sql = sql = "DELETE * FROM supplier WHERE sname = '" + data[0] + "'";
            updateCCDB(sql);
        } else if (data.length == 2) {
            sql = "DELETE * FROM chemicaltbl WHERE chemical = '" + data[0] + "'";
            updateChem(sql);
            runChems(data, id);
        }
    }

    public static String[] getSupplierData(String supplierid) throws SQLException {
        String[] data = new String[4];
        String sql = "SELECT sname, tel, email, liason FROM supplier WHERE sname = '" + supplierid + "'";
        rs = queryCCDB(sql);
        rs.next();
        for (int i = 0; i < data.length; i++) {
            data[i] = rs.getNString(i + 1);
        }
        return data;
    }

    private static void runChems(String data[], String id) throws SQLException {
        String sql = "SELECT * FROM information_schema.tables WHERE TABLE_TYPE='BASE TABLE'";
        ResultSet tb = queryChem(sql);
        tb.next();
        while (tb.next()) {
            String table = tb.getNString(1);
            sql = "UPDATE " + table + " SET chemical = '" + data[0] + "' WHERE chemical = '" + id + "'";
            updateChem(sql);
        }
    }

    public static ArrayList getChem() throws SQLException {
        ArrayList chem = new ArrayList();
        String sql = "SELECT DISTINCT chemical FROM chemicaltbl";
        rs = queryChem(sql);
        while (rs.next()) {
            chem.add(rs.getNString(1));
        }
        return chem;
    }
}
