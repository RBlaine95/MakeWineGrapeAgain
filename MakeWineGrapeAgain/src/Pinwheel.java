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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Se7en
 */
public final class Pinwheel {

    static ArrayList<String> colour = new ArrayList();
    static ArrayList<String> chemicals = new ArrayList();
    static ArrayList<String> type = new ArrayList();
    static ArrayList<String> supplier = new ArrayList();
    static Statement s;
    static Connection conn;
    static ResultSet rs;
    static Statement sChem;
    static Connection connChem;
    static ResultSet rsChem;
    static String sql;
    static String[] data;
    static String searchType;
    static String bounce;

    public static void connect() throws SQLException {
        connectChem();
        connectCCDB();
        refreshChemicals();
        refreshColour();
        refreshSupplier();
        refreshType();
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

    public static void createChem() {
        sql = "CREATE TABLE " + data[0] + " (chemical varchar(100), amount varchar(15))";
        updateChem(sql);
    }

    public static void insertNewChem(String c, String v) {
        sql = "INSERT INTO chemicaltbl (chemical, value) VALUES('" + c + "', '" + v + "')";
        updateChem(sql);
    }

    public static void insertChem(String c, String a) {
        sql = "INSERT INTO " + data[0] + " (chemical, amount) VALUES('" + c + "', '" + a + "')";
        System.out.println(sql);
        updateChem(sql);
    }

    public static void insertBatch() {
        data[3] = stageGetNo(data[3]);
        sql = "INSERT INTO batch (batchid, colour, type, stage, mass, supplierid) VALUES('" + data[0] + "', '" + data[1] + "', '" + data[2] + "', '" + data[3] + "', '" + data[4]
                + "', '" + data[5] + "')";
        System.out.println(data[3]);
        updateCCDB(sql);
    }

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

    public static ResultSet queryCCDB(String sql) throws SQLException {
        s = conn.createStatement();
        rs = s.executeQuery(sql);
        return rs;
    }

    public static void updateCCDB(String sql) {

        try {
            s = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Pinwheel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("fails running sql");
            Logger.getLogger(Pinwheel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ArrayList<String> getSupplier() throws SQLException {
        return supplier;
    }

    public static void setSupplier(ArrayList<String> supplier) {
        supplier = supplier;
    }

    public static ArrayList<String> getChemicals() {
        return chemicals;
    }

    public static void setChemicals(ArrayList<String> chemicals) {
        Pinwheel.chemicals = chemicals;
    }

    public static ArrayList<String> getColour() throws SQLException {
        return colour;
    }

    public static void setColour(ArrayList<String> colour) {
        colour = colour;
    }

    public static ArrayList<String> getType() throws SQLException {
        return type;
    }

    public static void setType(ArrayList<String> type) {
        type = type;
    }

    public static void refreshColour() throws SQLException {

        colour = new ArrayList();
        sql = "SELECT DISTINCT colour FROM batch";
        rs = queryCCDB(sql);
        while (rs.next()) {
            
            colour.add(rs.getNString(1));
        }

    }

    public static void refreshType() throws SQLException {

        type = new ArrayList();
        sql = "SELECT DISTINCT type FROM batch";
        rs = queryCCDB(sql);
        while (rs.next()) {

            type.add(rs.getNString(1));
        }

    }

    public static void refreshSupplier() throws SQLException {

        supplier = new ArrayList();
        sql = "SELECT DISTINCT sname FROM supplier";
        rs = queryCCDB(sql);
        while (rs.next()) {

            supplier.add(rs.getNString(1));
        }

    }

    public static void refreshChemicals() throws SQLException {

        chemicals = new ArrayList();
        sql = "SELECT DISTINCT chemical FROM chemicaltbl";
        rs = queryChem(sql);
        while (rs.next()) {
            
            chemicals.add(rs.getNString(1));
        }

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

    public static String[] getData() {
        return data;
    }

    public static void setData(String[] data) {
        Pinwheel.data = data;
    }

    public static String getSearchType() {
        return searchType;
    }

    public static void setSearchType(String searchType) {
        Pinwheel.searchType = searchType;
    }

    public static String getBounce() {
        return bounce;
    }

    public static void setBounce(String bounce) {
        Pinwheel.bounce = bounce;
    }

}
