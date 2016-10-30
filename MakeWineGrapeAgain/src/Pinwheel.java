/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
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
    static ArrayList<String> stages = new ArrayList();
    static ArrayList<String> stagesAll = new ArrayList();

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
    
    static String bakDir;
    static String bakDate;

    static String searchType;
    static String previousID;
    static String bounce;

    public static void connect() throws SQLException, FileNotFoundException {
        connectChem();
        connectCCDB();
        connectGraphDB();

        for (int i = 0; i < tempdataId.length; i++) {
            tempdataId[i] = "";
        }

        colourAll.add("All");
        supplierAll.add("All");
        typeAll.add("All");
        stagesAll.add("All");

        refreshChemicals();
        refreshColour();
        refreshSupplier();
        refreshType();
        stages.add("Storage");
        stages.add("Crushing");
        stages.add("Fermentation");
        stages.add("Pressing");
        stages.add("Maturation");
        stages.add("Blending");
        stages.add("Prep for Bottling");
        stages.add("Bottling");

        for (int i = 0; i < stages.size(); i++) {
            stagesAll.add(stages.get(i));
            System.out.println(stagesAll.get(i));
        }

    }

    private static void connectCCDB() {
        try {

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("Driver loaded");

            String url = "jdbc:ucanaccess://docs\\ccdb.accdb";
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

            String url = "jdbc:ucanaccess://docs\\chemdb.accdb";
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

            String url = "jdbc:ucanaccess://docs\\graphdb.accdb";
            connGraph = DriverManager.getConnection(url);

            System.out.println("Database Connected - GRAPH");
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }

    public static void createChem() {
        sql = "CREATE TABLE "
                + data[0] + " (chemical varchar(100), amount FLOAT(15))";
        updateChem(sql);
    }

    public static void createChem(String a) {
        sql = "CREATE TABLE "
                + clean(a) + " (chemical varchar(100), amount FLOAT(15))";
        updateChem(sql);
    }

    public static void insertBatch() {
        data[3] = stageGetNo(data[3]) + "";
        sql = "INSERT INTO batch (batchid, colour, type, stage, mass, supplierid) VALUES('"
                + data[0] + "', '"
                + data[1] + "', '"
                + data[2] + "', '"
                + data[3] + "', '"
                + data[4] + "', '"
                + data[5] + "')";
        updateCCDB(sql);
    }

    public static void updateBatch() {

        sql = "SELECT * FROM batch WHERE batchid = '"
                + data[0] + "'";
        try {
            rs = Pinwheel.queryCCDB(sql);
            while (rs.next()) {
                data[0] = rs.getNString(1);
                data[1] = rs.getNString(2);
                data[2] = rs.getNString(3);
                data[3] = rs.getNString(4);
                data[4] = rs.getInt(5) + "";
                data[5] = rs.getNString(6);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Pinwheel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void deleteBatch() {
        sql = "DELETE FROM batch WHERE batchid = '"
                + data[0] + "'";
        updateCCDB(sql);
        sql = "DROP TABLE "
                + data[0];
        updateChem(sql);
    }

    public static void deleteSubBatch() {
        sql = "DELETE FROM subbatch WHERE subbatchid = '"
                + data[0] + "'";
        updateCCDB(sql);
        sql = "DROP TABLE "
                + data[0];
        updateChem(sql);
    }

    public static void deleteBlend() {
        sql = "DELETE FROM blend WHERE bid = '"
                + data[0] + "'";
        updateCCDB(sql);
        sql = "DROP TABLE "
                + data[0];
        updateChem(sql);
    }

    public static void insertSupplier() {
        sql = "INSERT INTO supplier (sname, tel, email, liason) VALUES('"
                + data[0] + "', '"
                + data[1] + "', '"
                + data[2] + "', '"
                + data[3] + "')";
        updateCCDB(sql);
    }

    public static void deleteSupplier() {
        sql = "UPDATE batch SET supplierid = 'DELETED' WHERE supplierid = '"
                + data[0] + "'";
        updateCCDB(sql);
        sql = "DELETE FROM supplier WHERE sname = '"
                + data[0] + "'";
        updateCCDB(sql);
    }

    public static void insertChemical() {
        sql = "INSERT INTO chemicaltbl (chemical, value) VALUES('"
                + data[0] + "', '"
                + data[1] + "')";
        updateChem(sql);
    }

    public static void deleteChemical() {
        sql = "DELETE FROM chemicaltbl WHERE chemical = '"
                + data[0] + "'";
        updateChem(sql);

    }

    public static void insertChemicalAt(String c, String a) throws SQLException {
        sql = "SELECT * FROM "
                + data[0] + " WHERE chemical = '"
                + clean(c) + "'";
        rs = queryChem(sql);

        if (rs.next()) {
            sql = "UPDATE "
                    + data[0] + " SET amount = amount + "
                    + clean(a) + " WHERE chemical = '"
                    + clean(c) + "'";
        } else {
            sql = "INSERT INTO "
                    + data[0] + " (chemical, amount) VALUES('"
                    + clean(c) + "', '"
                    + clean(a) + "')";
        }
        updateChem(sql);
    }

    public static void insertCustomChemicalAt(String id, String c, String a) throws SQLException {
        sql = "SELECT * FROM "
                + clean(id) + " WHERE chemical = '"
                + clean(c) + "'";

        rs = queryChem(sql);

        if (rs.next()) {
            sql = "UPDATE "
                    + clean(id) + " SET amount = amount + "
                    + clean(a) + " WHERE chemical = '"
                    + clean(c) + "'";
        } else {
            sql = "INSERT INTO "
                    + clean(id) + " (chemical, amount) VALUES('"
                    + clean(c) + "', '"
                    + clean(a) + "')";
        }
        updateChem(sql);
    }

    public static ResultSet queryChem(String sql) throws SQLException {
        sChem = connChem.createStatement();
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

    public static ArrayList<String> getStages() {
        return stages;
    }

    public static ArrayList<String> getStagesAll() {
        return stagesAll;
    }

    public static void refreshColour() throws SQLException, FileNotFoundException {
        Scanner sc = new Scanner(new File("docs\\colour.txt"));
        while (sc.hasNextLine()) {
            colour.add(sc.nextLine());
        }
        for (int i = 0; i < colour.size(); i++) {
            colourAll.add(colour.get(i));
        }
        sc.close();
    }

    public static void refreshType() throws SQLException, FileNotFoundException {
        Scanner sc = new Scanner(new File("docs\\type.txt"));
        while (sc.hasNextLine()) {
            type.add(sc.nextLine());
        }
        for (int i = 0; i < type.size(); i++) {
            typeAll.add(type.get(i));
        }
        sc.close();
    }

    public static void refreshSupplier() throws SQLException, FileNotFoundException {
        Scanner sc = new Scanner(new File("docs\\supp.txt"));
        while (sc.hasNextLine()) {
            supplier.add(sc.nextLine());
        }
        for (int i = 0; i < type.size(); i++) {
            supplierAll.add(type.get(i));
        }
        sc.close();
    }

    public static void refreshChemicals() throws SQLException, FileNotFoundException {
        Scanner sc = new Scanner(new File("docs\\chem.txt"));
        while (sc.hasNextLine()) {
            chemicals.add(sc.nextLine());
        }
        sc.close();

    }

    public static String stageGetWord(int s) {
        return stages.get(s);
    }

    public static int stageGetNo(String s) {
        return stages.indexOf(s);
    }

    public static String[] getSupplierData(String supplierid) throws SQLException {
        String[] data = new String[4];
        String sql = "SELECT sname, tel, email, liason FROM supplier WHERE sname = '"
                + clean(supplierid) + "'";
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
        sql = "SELECT * FROM "
                + data[0] + " WHERE date = '"
                + date + "'"; //if date already exists in table
        rsGraph = queryGraphDB(sql);

        if (rsGraph.next()) { //if date already exists
            sql = "UPDATE "
                    + data[0] + " SET balling = "
                    + sugar + ", temperature = "
                    + temp + " WHERE date = '"
                    + date + "'"; //update
        } else {
            sql = "INSERT INTO "
                    + data[0] + " (date, balling, temperature) VALUES ('"
                    + date + "', '"
                    + sugar + "', '"
                    + temp + "')"; //insert
        }
        updateGraphDB(sql);
    }

    public static void createSpecGraph(String a) {
        sql = "CREATE TABLE "
                + clean(a) + " (date TEXT(255), balling DECIMAL(5,2), temperature DECIMAL(5,2))";
        updateGraphDB(sql);
    }

    public static void learnColour(String c) {
        try (FileWriter fw = new FileWriter("docs\\colour.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(c);
        } catch (IOException ex) {

        }
    }

    public static void learnSupplier(String c) {
        try (FileWriter fw = new FileWriter("docs\\supp.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(c);
        } catch (IOException ex) {

        }
    }

    public static void learnChemical(String c) {
        try (FileWriter fw = new FileWriter("docs\\chem.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(c);
        } catch (IOException ex) {

        }
    }

    public static void learnType(String c) {
        try (FileWriter fw = new FileWriter("docs\\type.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(c);
        } catch (IOException ex) {

        }
    }

    public static String numEx(String s) {
        return s.replaceAll("[^\\d.]", "");
    }

    public static String clean(String s) {
        return s.replace("'", "''");
    }

    public static void backup() throws SQLException {        
//===================================================================================================================================CCDB
//---------------------------------------------------------------------------------------------------------------------------BATCHES

        sql = "SELECT batchid, colour, type, stage, mass, supplierid FROM batch";
        try (FileWriter fw = new FileWriter("BAK\\CCDB-Backup.sql", false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println("DROP TABLE batch" + " IF EXISTS");
            out.println("CREATE TABLE batch (batchid TEXT(15), colour TEXT(15), type TEXT(25), stage TEXT(2), mass FLOAT(15), supplierid TEXT(30))");
            rs = queryCCDB(sql);
            while (rs.next()) {
                String id = rs.getNString(1);
                String colour = rs.getNString(2);
                String type = rs.getNString(3);
                String stage = rs.getNString(4);
                double mass = rs.getDouble(5);
                String supp = rs.getNString(6);
                out.println("INSERT INTO batch (batchid, colour, type, stage, mass, supplierid) VALUES('"
                        + id + "', '" + colour + "', '" + type + "', '" + stage + "', " + mass + ", '" + supp + "')");
            }

//---------------------------------------------------------------------------------------------------------------------------SUBBATCHES
            sql = "SELECT subbatchid, colour, type, stage, mass, supplierid FROM subbatch";
            out.println("DROP TABLE subbatch" + " IF EXISTS");
            out.println("CREATE TABLE subbatch (subbatchid TEXT(15), colour TEXT(15), type TEXT(25), stage TEXT(2), mass FLOAT(15), supplierid TEXT(30))");
            rs = queryCCDB(sql);
            while (rs.next()) {
                String id = rs.getNString(1);
                String colour = rs.getNString(2);
                String type = rs.getNString(3);
                String stage = rs.getNString(4);
                double mass = rs.getDouble(5);
                String supp = rs.getNString(6);
                out.println("INSERT INTO subbatch (subbatchid, colour, type, stage, mass, supplierid) VALUES('"
                        + id + "', '" + colour + "', '" + type + "', '" + stage + "', " + mass + ", '" + supp + "')");
            }

            //---------------------------------------------------------------------------------------------------------------------------SUPPLIERS
            sql = "SELECT sname, tel, email, liason FROM supplier";
            out.println("DROP TABLE supplier" + " IF EXISTS");
            out.println("CREATE TABLE supplier (sname TEXT(30), tel TEXT(10), email TEXT(50), liason TEXT(30))");
            rs = queryCCDB(sql);
            while (rs.next()) {
                String id = rs.getNString(1);
                String tel = rs.getNString(2);
                String email = rs.getNString(3);
                String liason = rs.getNString(4);

                out.println("INSERT INTO supplier (sname, tel, email, liason) VALUES('"
                        + id + "', '" + tel + "', '" + email + "', '" + liason + "')");
            }

//---------------------------------------------------------------------------------------------------------------------------BLENDS
            out.println("DROP TABLE blend" + " IF EXISTS");
            sql = "SELECT bid, winename, colour, volume, stage, fid1, pid1, fid2, pid2, fid3, pid3, fid4, pid4, fid5, pid5, fid6, pid6, fid7, pid7, fid8, pid8, fid9, pid9 FROM blend";
            out.println("CREATE TABLE blend (bid TEXT(50), winename TEXT(50), colour TEXT(30), volume FLOAT(15), stage TEXT(2), "
                    + "fid1 TEXT(15), pid1 FLOAT(15), fid2 TEXT(15), pid2 FLOAT(15), fid3 TEXT(15), pid3 FLOAT(15), fid4 TEXT(15), pid4 FLOAT(15),"
                    + "fid5 TEXT(15), pid5 FLOAT(15), fid6 TEXT(15), pid6 FLOAT(15), fid7 TEXT(15), pid7 FLOAT(15), fid8 TEXT(15), pid8 FLOAT(15), fid9 TEXT(15), pid9 FLOAT(15))");

            rs = queryCCDB(sql);
            while (rs.next()) {
                String id = rs.getNString(1);
                String name = rs.getNString(2);
                String colour = rs.getNString(3);
                double vol = rs.getDouble(4);
                String stage = rs.getNString(5);
                String[] fid = new String[9];
                double[] pid = new double[9];

                for (int i = 0; i < fid.length; i++) {
                    if (i % 2 == 0) {
                        fid[i] = rs.getNString(i + 6);
                        System.out.println(6 + i);

                        pid[i] = rs.getDouble(i + 7);
                    }
                }
                String ready = "INSERT INTO blend (bid, winename, colour, volume, stage, fid1, pid1, fid2, pid2, fid3, pid3, fid4, pid4, fid5, pid5, fid6, pid6, fid7, pid7, fid8, pid8, fid9, pid9) VALUES('"
                        + id + "', '" + name + "', '" + colour + "', " + vol + ", '" + stage + "', ";
                for (int i = 0; i < fid.length - 1; i++) {
                    ready += "'" + fid[i] + "', " + pid[i] + ", ";
                }
                ready += "'" + fid[8] + "', " + pid[8] + ")";
                out.println(ready);
            }
            out.close();
        } catch (IOException ex) {

        }
//===================================================================================================================================CHEMDB
//---------------------------------------------------------------------------------------------------------------------------EVERYTHING 

        rs = connChem.getMetaData().getTables(null, null, "%", null);

        try (FileWriter fw = new FileWriter("BAK\\ChemDB-Backup.sql", false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {

            while (rs.next()) {
                String id = rs.getString(3);
                if (!id.equals("chemicaltbl")) {
                    out.println("DROP TABLE " + id + " IF EXISTS");
                    sql = "CREATE TABLE " + id + " (chemical TEXT(100), amount FLOAT(15))";

                    out.println(sql);
                    sql = "SELECT * FROM " + id;
                    ResultSet rs1 = queryChem(sql);
                    while (rs1.next()) {
                        String chem = rs1.getNString(1);
                        double mass = rs1.getDouble(2);
                        out.println("INSERT INTO " + id + " (chemical, amount) VALUES('" + chem + "', " + mass + ")");
                    }
                } else {
                    out.println("DROP TABLE " + id + " IF EXISTS");
                    sql = "CREATE TABLE chemicaltbl (chemical TEXT(100), value FLOAT(15))";
                    out.println(sql);
                    sql = "SELECT * FROM " + id;
                    ResultSet rs1 = queryChem(sql);
                    while (rs1.next()) {
                        String chem = rs1.getNString(1);
                        double mass = rs1.getDouble(2);

                        out.println("INSERT INTO " + id + " (chemical, value) VALUES('" + chem + "', " + mass + ")");
                    }
                }
            }

        } catch (IOException ex) {

        }
        //===================================================================================================================================GRAPHDB
//---------------------------------------------------------------------------------------------------------------------------EVERYTHING 
        rs = connGraph.getMetaData().getTables(null, null, "%", null);

        try (FileWriter fw = new FileWriter("BAK\\GraphDB-Backup.sql", false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {

            while (rs.next()) {
                String id = rs.getString(3);
                out.println("DROP TABLE " + id + " IF EXISTS");
                sql = "CREATE TABLE " + id + " (date TEXT(255), balling FLOAT(15), temperature FLOAT(15))";

                out.println(sql);
                sql = "SELECT * FROM " + id;
                ResultSet rs1 = queryGraphDB(sql);
                while (rs1.next()) {
                    String date = rs1.getNString(1);
                    double balling = rs1.getDouble(2);
                    double temp = rs1.getDouble(3);
                    out.println("INSERT INTO " + id + " (date, balling, temperature) VALUES('" + date + "', " + balling + ", " + temp + ")");
                }

            }

        } catch (IOException ex) {

        }
    }

    public static void restore() throws FileNotFoundException {
        //RESTORE BATCH
        Scanner sc = new Scanner(new File("BAK\\CCDB-Backup.sql"));
        while (sc.hasNextLine()) {
            updateCCDB(sc.nextLine());
        }
        sc = new Scanner(new File("BAK\\ChemDB-Backup.sql"));
        while (sc.hasNextLine()) {
            updateChem(sc.nextLine());
        }
        sc = new Scanner(new File("BAK\\GraphDB-Backup.sql"));
        while (sc.hasNextLine()) {
            updateGraphDB(sc.nextLine());
        }
    }
}
