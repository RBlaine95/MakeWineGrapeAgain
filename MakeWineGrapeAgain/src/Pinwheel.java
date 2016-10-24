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
    static ArrayList<String> colourAll = new ArrayList();
    static ArrayList<String> supplierAll = new ArrayList();
    static ArrayList<String> typeAll = new ArrayList();
    static Statement s;
    static Connection conn;
    static ResultSet rs;
    static Statement sChem;
    static Connection connChem;
    static ResultSet rsChem;
    static String sql;
    static Connection connGraph;
    static ResultSet rsGraph;
    static Statement sGraph;
    static String[] data;
    static String[] tempdataId = new String[9];
    static String[] tempdataMass = new String[9];

    static String searchType;
    static String previousID;
    static String bounce;

    public static void connect() throws SQLException {
        for (int i = 0; i < tempdataId.length; i++) {
            tempdataId[i] = "";
        }
        connectChem();
        connectCCDB();
        connectGraphDB();
        colourAll.add("All");
        supplierAll.add("All");
        typeAll.add("All");

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

            System.out.println("Database Connected - CCDB");
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
            System.out.println("Database Connected - CHEMS");

        } catch (Exception ex) {
            System.out.println("Error");
        }
    }

    private static void connectGraphDB() {
        try {

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("Driver loaded");

            String url = "jdbc:ucanaccess://graphdb.accdb";
            connGraph = DriverManager.getConnection(url);

            System.out.println("Database Connected - GRAPH");
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }

    public static void createChem() {
        sql = "CREATE TABLE " + data[0] + " (chemical varchar(100), amount FLOAT(15))";
        updateChem(sql);
    }

    public static void createChem(String a) {
        sql = "CREATE TABLE " + a + " (chemical varchar(100), amount FLOAT(15))";
        updateChem(sql);
    }

    public static void insertBatch() {
        data[3] = stageGetNo(data[3]);
        sql = "INSERT INTO batch (batchid, colour, type, stage, mass, supplierid) VALUES('" + data[0] + "', '" + data[1] + "', '" + data[2] + "', '" + data[3] + "', '" + data[4]
                + "', '" + data[5] + "')";
        updateCCDB(sql);
    }

    public static void deleteBatch() {
        sql = "DELETE FROM batch WHERE batchid = '" + data[0] + "'";
        updateCCDB(sql);
        sql = "DROP TABLE " + data[0];
        updateChem(sql);
    }

    public static void insertSupplier() {
        sql = "INSERT INTO supplier (sname, tel, email, liason) VALUES('" + data[0] + "', '" + data[1] + "', '" + data[2] + "', '" + data[3] + "')";
        updateCCDB(sql);
    }

    public static void deleteSupplier() {
        sql = "DELETE FROM supplier WHERE sname = '" + data[0] + "'";
        updateCCDB(sql);
        sql = "UPDATE batch SET supplierid = DELETED WHERE supplierid = '" + data[0] + "'";
        updateCCDB(sql);
    }

    public static void insertChemical() {
        sql = "INSERT INTO chemicaltbl (chemical, value) VALUES('" + data[0] + "', '" + data[1] + "')";
        updateChem(sql);
    }

    public static void deleteChemical() {
        sql = "DELETE FROM chemicaltbl WHERE chemical = '" + data[0] + "'";
        updateChem(sql);

    }

    public static void insertChemicalAt(String c, String a) throws SQLException {
        sql = "SELECT * FROM " + data[0] + " WHERE chemical = '" + c + "'";
        rs = queryChem(sql);

        if (rs.next()) {
            sql = "UPDATE " + data[0] + " SET amount = amount + " + a + " WHERE chemical = '" + c + "'";
        } else {
            sql = "INSERT INTO " + data[0] + " (chemical, amount) VALUES('" + c + "', '" + a + "')";
        }
        updateChem(sql);
    }

    public static void insertCustomChemicalAt(String id, String c, String a) throws SQLException {
        sql = "SELECT * FROM " + id + " WHERE chemical = '" + c + "'";
        rs = queryChem(sql);

        if (rs.next()) {
            sql = "UPDATE " + id + " SET amount = amount + " + a + " WHERE chemical = '" + c + "'";
        } else {
            sql = "INSERT INTO " + id + " (chemical, amount) VALUES('" + c + "', '" + a + "')";
        }
        updateChem(sql);
    }

    public static ResultSet queryChem(String sql) throws SQLException {
        sChem = connChem.createStatement();
        System.out.println("Queries Chem");
        rsChem = sChem.executeQuery(sql);
        return rsChem;
    }

    public static boolean updateChem(String sql) {

        try {
            sChem = connChem.createStatement();
            sChem.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

    public static ResultSet queryCCDB(String sql) throws SQLException {
        s = conn.createStatement();
        System.out.println("Queries Database, Not too many of these pls");
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

    public static ResultSet queryGraphDB(String sql) throws SQLException {
        sGraph = connGraph.createStatement();
        System.out.println("Queries GraphDB");
        rsGraph = sGraph.executeQuery(sql);
        return rsGraph;
    }

    public static void updateGraphDB(String sql) {

        try {
            sGraph = connGraph.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Pinwheel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            sGraph.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("fails running sql");
            Logger.getLogger(Pinwheel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ArrayList<String> getColourAll() {
        return colourAll;
    }

    public static void setColourAll(ArrayList<String> colourAll) {
        Pinwheel.colourAll = colourAll;
    }

    public static ArrayList<String> getTypeAll() {
        return typeAll;
    }

    public static void setTypeAll(ArrayList<String> typeAll) {
        Pinwheel.typeAll = typeAll;
    }

    public static ArrayList<String> getSupplierAll() {
        return supplierAll;
    }

    public static void setSupplierAll(ArrayList<String> supplierAll) {
        Pinwheel.supplierAll = supplierAll;
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
        for (int i = 0; i < colour.size(); i++) {
            colourAll.add(colour.get(i));
        }

    }

    public static void refreshType() throws SQLException {

        type = new ArrayList();
        sql = "SELECT DISTINCT type FROM batch";
        rs = queryCCDB(sql);
        while (rs.next()) {
            type.add(rs.getNString(1));
        }
        for (int i = 0; i < type.size(); i++) {
            typeAll.add(type.get(i));
        }

    }

    public static void refreshSupplier() throws SQLException {

        supplier = new ArrayList();
        sql = "SELECT DISTINCT sname FROM supplier";
        rs = queryCCDB(sql);
        while (rs.next()) {

            supplier.add(rs.getNString(1));
        }
        for (int i = 0; i < supplier.size(); i++) {
            supplierAll.add(supplier.get(i));
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

    public static String getPreviousID() {
        return previousID;
    }

    public static void setPreviousID(String previousID) {
        Pinwheel.previousID = previousID;
    }

    public static String[] getTempdata() {
        return tempdataId;
    }

    public static String[] getTempdataMass() {
        return tempdataMass;
    }

    public static void setTempdataMass(String[] tempdataMass) {
        Pinwheel.tempdataMass = tempdataMass;
    }

    public static void setSpecTempdataMass(String tempdataMass, int i) {
        Pinwheel.tempdataMass[i] = tempdataMass;
    }

    public static void setTempData(String data[]) {
        tempdataId = data;
    }

    public static void setSpecTempData(String data, int i) {
        tempdataId[i] = data;
    }

    public static void insertGraphAt(String date, double sugar, double temp) throws SQLException {
        sql = "SELECT * FROM " + data[0] + " WHERE date = '" + date + "'"; //if date already exists in table
        rsGraph = queryGraphDB(sql);

        if (rsGraph.next()) { //if date already exists
            sql = "UPDATE " + data[0] + " SET balling = " + sugar + ", temperature = " + temp + " WHERE date = '" + date + "'"; //update
        } else {
            sql = "INSERT INTO " + data[0] + " (date, balling, temperature) VALUES ('" + date + "', '" + sugar + "', '" + temp + "')"; //insert
        }
        updateGraphDB(sql);
    }

    public static void createGraph() {
        sql = "CREATE TABLE " + data[0] + " (date TEXT(255), balling DECIMAL(5,2), temperature DECIMAL(5,2))";
        updateGraphDB(sql);
    }
}
