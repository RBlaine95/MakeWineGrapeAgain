
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Se7en
 */
public class SearchBatch extends javax.swing.JFrame {

    /**
     * Creates new form SearchBatch
     */
    String batchid;
    String colour;
    String type;
    String stage;
    String supplier;
    boolean temp = false;
    String[] data;
    int num;

    /**
     * Creates new form main
     */
    public SearchBatch() {

        initComponents();

        switch (Pinwheel.getSearchType()) {
            case "blend":
                batchTbl.getColumnModel().getColumn(0).setHeaderValue("Blend ID");
                batchTbl.getColumnModel().getColumn(1).setHeaderValue("Name");
                batchTbl.getColumnModel().getColumn(2).setHeaderValue("Colour");
                batchTbl.getColumnModel().getColumn(3).setHeaderValue("Volume");
                batchTbl.getColumnModel().getColumn(4).setHeaderValue("Stage");
                batchTbl.getColumnModel().getColumn(5).setHeaderValue("Blend Of");
                
                this.colourBox.setModel(new DefaultComboBoxModel(Pinwheel.getColourAll().toArray()));
                this.typeBox.setModel(new DefaultComboBoxModel(Pinwheel.getTypeAll().toArray()));
                this.stageBox.setModel(new DefaultComboBoxModel(Pinwheel.getStagesAll().toArray()));
                
                
                
                data = new String[6];
                
                this.batchLbl.setText("Blend Name");
                this.supplierLbl.setText("");
                
                this.suppBox.setVisible(false);
                
                this.suppBox.setEnabled(false);
                break;
            case "subbatch":
                batchTbl.getColumnModel().getColumn(0).setHeaderValue("SubBatch ID");
                this.batchLbl.setText("SubBatch ID");
                this.colourBox.setModel(new DefaultComboBoxModel(Pinwheel.getColourAll().toArray()));
                this.typeBox.setModel(new DefaultComboBoxModel(Pinwheel.getTypeAll().toArray()));
                this.suppBox.setModel(new DefaultComboBoxModel(Pinwheel.getSupplierAll().toArray()));
                this.stageBox.setModel(new DefaultComboBoxModel(Pinwheel.getStagesAll().toArray()));
                
                data = new String[6];
                break;
            case "batch":
                this.colourBox.setModel(new DefaultComboBoxModel(Pinwheel.getColourAll().toArray()));
                this.typeBox.setModel(new DefaultComboBoxModel(Pinwheel.getTypeAll().toArray()));
                this.suppBox.setModel(new DefaultComboBoxModel(Pinwheel.getSupplierAll().toArray()));
                this.stageBox.setModel(new DefaultComboBoxModel(Pinwheel.getStagesAll().toArray()));
                data = new String[6];
                break;
            case "supplier":
                batchTbl.getColumnModel().getColumn(0).setHeaderValue("Supplier name");
                batchTbl.getColumnModel().getColumn(1).setHeaderValue("Contact No");
                batchTbl.getColumnModel().getColumn(2).setHeaderValue("Email");
                batchTbl.getColumnModel().getColumn(3).setHeaderValue("Contact Liason");
                batchTbl.getColumnModel().removeColumn(batchTbl.getColumnModel().getColumn(4));
                batchTbl.getColumnModel().removeColumn(batchTbl.getColumnModel().getColumn(4));
                data = new String[4];

                this.batchLbl.setText("Supplier Name");
                this.colourLbl.setText("");
                this.typeLbl.setText("");
                this.stageLbl.setText("");
                this.supplierLbl.setText("");

                this.stageBox.setVisible(false);
                this.colourBox.setVisible(false);
                this.typeBox.setVisible(false);
                this.suppBox.setVisible(false);

                this.suppBox.setEnabled(false);
                this.stageBox.setEnabled(false);
                this.colourBox.setEnabled(false);
                this.typeBox.setEnabled(false);
                break;
            case "chemical":
                batchTbl.getColumnModel().getColumn(0).setHeaderValue("Chemical");
                batchTbl.getColumnModel().getColumn(1).setHeaderValue("Value");
                batchTbl.getColumnModel().removeColumn(batchTbl.getColumnModel().getColumn(2));
                batchTbl.getColumnModel().removeColumn(batchTbl.getColumnModel().getColumn(2));
                batchTbl.getColumnModel().removeColumn(batchTbl.getColumnModel().getColumn(2));
                batchTbl.getColumnModel().removeColumn(batchTbl.getColumnModel().getColumn(2));
                data = new String[2];

                this.batchLbl.setText("Chemical Name");
                this.colourLbl.setText("");
                this.typeLbl.setText("");
                this.stageLbl.setText("");
                this.supplierLbl.setText("");

                this.stageBox.setVisible(false);
                this.colourBox.setVisible(false);
                this.typeBox.setVisible(false);
                this.suppBox.setVisible(false);

                this.suppBox.setEnabled(false);
                this.stageBox.setEnabled(false);
                this.colourBox.setEnabled(false);
                this.typeBox.setEnabled(false);

                break;
        }
    }

    public SearchBatch(boolean a, int i) {

        initComponents();
        num = i;
        temp = a;
        this.colourBox.setModel(new DefaultComboBoxModel(Pinwheel.getColourAll().toArray()));
        this.typeBox.setModel(new DefaultComboBoxModel(Pinwheel.getTypeAll().toArray()));
        this.suppBox.setModel(new DefaultComboBoxModel(Pinwheel.getSupplierAll().toArray()));
        this.stageBox.setModel(new DefaultComboBoxModel(Pinwheel.getStagesAll().toArray()));

        data = new String[5];
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        batchTbl = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        exitBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        selectBtn = new javax.swing.JButton();
        suppBox = new javax.swing.JComboBox();
        supplierLbl = new javax.swing.JLabel();
        stageBox = new javax.swing.JComboBox();
        stageLbl = new javax.swing.JLabel();
        typeBox = new javax.swing.JComboBox();
        typeLbl = new javax.swing.JLabel();
        colourBox = new javax.swing.JComboBox();
        colourLbl = new javax.swing.JLabel();
        batchIdTxt = new javax.swing.JTextField();
        batchLbl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jLabel6.setText("Wine Type");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search Batches");
        getContentPane().setLayout(null);

        batchTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Batch ID", "Colour", "Type", "Stage", "Mass", "Supplier"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        batchTbl.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        batchTbl.getTableHeader().setReorderingAllowed(false);
        batchTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                batchTblMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                batchTblMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(batchTbl);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 10, 1070, 550);

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 270));

        exitBtn.setText("Back");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        selectBtn.setText("Select");
        selectBtn.setEnabled(false);
        selectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectBtnActionPerformed(evt);
            }
        });

        suppBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        suppBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suppBoxActionPerformed(evt);
            }
        });

        supplierLbl.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        supplierLbl.setText("Supplier");

        stageBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        stageLbl.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        stageLbl.setText("Stage");

        typeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        typeLbl.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        typeLbl.setText("Wine Type");

        colourBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        colourLbl.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        colourLbl.setText("Wine Colour");

        batchLbl.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        batchLbl.setText("Batch ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(batchIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batchLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(colourLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(colourBox, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(typeLbl)
                    .addComponent(typeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stageBox, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stageLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(supplierLbl)
                        .addGap(80, 80, 80))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(suppBox, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(batchLbl)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(supplierLbl)
                                .addComponent(stageLbl)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(batchIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colourBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(typeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stageBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(suppBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectBtn)
                            .addComponent(searchBtn)
                            .addComponent(exitBtn)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(typeLbl)
                        .addComponent(colourLbl)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(240, 560, 680, 80);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagery/WoodNew.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1090, 660);

        setSize(new java.awt.Dimension(1101, 692));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_exitBtnActionPerformed

    private void selectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBtnActionPerformed

        for (int i = 0; i < data.length; i++) {
            data[i] = (String) this.batchTbl.getValueAt(this.batchTbl.getSelectedRow(), i);
        }
        if (this.temp) {
            Pinwheel.setSpecTempData(data[0], num);
            Pinwheel.setSpecTempdataMass(data[4], num);
        } else {
            Pinwheel.setData(data);
        }
        switch (Pinwheel.getBounce()) {
            case "mainsearch":
                Edit ed = new Edit();
                ed.setVisible(true);
                this.dispose();
                break;
            case "delete":
                Prompt p = new Prompt();
                p.setVisible(true);
                this.dispose();
                break;
            case "edit":
                AdminEdit ae = new AdminEdit();
                ae.setVisible(true);
                this.dispose();
                break;
            case "blend":
                Blend b = new Blend();
                b.setVisible(true);
                this.dispose();
        }
    }//GEN-LAST:event_selectBtnActionPerformed

    private void batchTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batchTblMouseClicked
        if (batchTbl.getSelectedRow() != -1) {
            this.selectBtn.setEnabled(true);
        }
    }//GEN-LAST:event_batchTblMouseClicked

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        this.selectBtn.setEnabled(false);
        DefaultTableModel model = (DefaultTableModel) this.batchTbl.getModel();
        model.setRowCount(0);
        ResultSet rs;
        String sql;

        this.batchid = this.batchIdTxt.getText();
        this.colour = this.colourBox.getSelectedItem().toString();
        this.type = this.typeBox.getSelectedItem().toString();
        this.stage = this.stageBox.getSelectedItem().toString();
        this.supplier = this.suppBox.getSelectedItem().toString();
        boolean batch = !this.batchid.equals("");
        boolean col = !this.colour.equals("All");
        boolean wtype = !this.type.equals("All");
        boolean wstage = !this.stage.equals("All");
        boolean supp = !this.supplier.equals("All");
        boolean whered = false;
        sql = "";
        Object[] list;

        switch (Pinwheel.getSearchType()) {
            case "subbatch":
                sql = "SELECT subbatchid, colour, type, stage, mass, supplierid FROM subbatch";
                list = new Object[6];
                if (batch || col || wtype || wstage || supp) {
                    sql += " WHERE ";
                }
                if (batch) {
                    if (whered) {
                        sql += " AND ";
                    }
                    whered = true;
                    sql += " subbatchid LIKE '*" + this.batchid + "*'";
                }
                if (col) {
                    if (whered) {
                        sql += " AND ";
                    }
                    whered = true;
                    sql += " colour = '" + this.colour + "'";
                }
                if (wtype) {
                    if (whered) {
                        sql += " AND ";
                    }
                    whered = true;
                    sql += " type = '" + this.type + "'";
                }
                if (wstage) {
                    if (whered) {
                        sql += " AND ";
                    }
                    whered = true;
                    sql += " stage = '" + Pinwheel.stageGetNo(this.stage) + "'";
                }
               
                try {
                    System.out.println(sql);
                    rs = Pinwheel.queryCCDB(sql);
                    int count = 0;
                    while (rs.next()) {

                        for (int i = 0; i < list.length; i++) {
                            list[i] = (rs.getString(i + 1));
                        }
                        list[3] = Pinwheel.stageGetWord(Integer.parseInt(list[3] + ""));
                        
                        
                        
                        
                        ((DefaultTableModel) this.batchTbl.getModel()).insertRow(count, list);
                        count++;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SearchBatch.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            case "blend":
                sql = "SELECT bid, winename, colour, volume, stage, fid1, fid2, fid3, fid4, fid5, fid6, fid7, fid8, fid9 FROM blend";
                list = new Object[6];
                if (batch || col || wtype || wstage) {
                    sql += " WHERE ";
                }
                if (batch) {
                    if (whered) {
                        sql += " AND ";
                    }
                    whered = true;
                    sql += " winename LIKE '*" + this.batchid + "*'";
                }
                if (col) {
                    if (whered) {
                        sql += " AND ";
                    }
                    whered = true;
                    sql += " colour = '" + this.colour + "'";
                }
                if (wtype) {
                    if (whered) {
                        sql += " AND ";
                    }
                    whered = true;
                    sql += " type = '" + this.type + "'";
                }
                if (wstage) {
                    if (whered) {
                        sql += " AND ";
                    }
                    whered = true;
                    sql += " stage = '" + Pinwheel.stageGetNo(this.stage) + "'";
                }
                
                try {
                    rs = Pinwheel.queryCCDB(sql);
                    int count = 0;
                    while (rs.next()) {
                        for (int i = 0; i < list.length-1; i++) {
                            list[i] = (rs.getString(i + 1));
                        }
                        String combine = "";
                        for (int i = 6; i < 14; i++) {
                            String buffer = rs.getNString(i);
                            
                            combine += (buffer == (null))?"":buffer + ", ";
                        }
                        list[5] = combine;
  
                        list[4] = Pinwheel.stageGetWord(Integer.parseInt(list[4] + ""));
                        ((DefaultTableModel) this.batchTbl.getModel()).insertRow(count, list);
                        count++;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SearchBatch.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case "batch":
                sql = "SELECT batchid, colour, type, stage, mass, supplierid FROM batch";
                list = new Object[6];
                if (batch || col || wtype || wstage || supp) {
                    sql += " WHERE ";
                }
                if (batch) {
                    if (whered) {
                        sql += " AND ";
                    }
                    whered = true;
                    sql += " batchid LIKE '*" + this.batchid + "*'";
                }
                if (col) {
                    if (whered) {
                        sql += " AND ";
                    }
                    whered = true;
                    sql += " colour = '" + this.colour + "'";
                }
                if (wtype) {
                    if (whered) {
                        sql += " AND ";
                    }
                    whered = true;
                    sql += " type = '" + this.type + "'";
                }
                if (wstage) {
                    if (whered) {
                        sql += " AND ";
                    }
                    whered = true;
                    sql += " stage = '" + Pinwheel.stageGetNo(this.stage) + "'";
                }
                if (supp) {
                    if (whered) {
                        sql += " AND ";
                    }
                    whered = true;
                    sql += " supplierid = '" + this.supplier + "'";
                }
                try {
                    rs = Pinwheel.queryCCDB(sql);
                    int count = 0;
                    while (rs.next()) {

                        for (int i = 0; i < list.length; i++) {
                            list[i] = (rs.getString(i + 1));
                        }
                        list[3] = Pinwheel.stageGetWord(Integer.parseInt(list[3] + ""));
                        ((DefaultTableModel) this.batchTbl.getModel()).insertRow(count, list);
                        count++;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SearchBatch.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "supplier":
                sql = "SELECT sname, tel, email, liason FROM supplier";
                list = new Object[4];
                if (batch) {
                    sql += " WHERE sname LIKE '*" + this.batchid + "*'";
                }
                try {
                    rs = Pinwheel.queryCCDB(sql);
                    int count = 0;
                    while (rs.next()) {

                        for (int i = 0; i < list.length; i++) {
                            list[i] = (rs.getString(i + 1));
                        }

                        ((DefaultTableModel) this.batchTbl.getModel()).insertRow(count, list);
                        count++;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SearchBatch.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "chemical":
                sql = "SELECT chemical, value FROM chemicaltbl";
                list = new Object[2];
                if (batch) {
                    sql += " WHERE chemical LIKE '*" + this.batchid + "*'";
                }
                try {
                    rs = Pinwheel.queryChem(sql);
                    int count = 0;
                    while (rs.next()) {

                        for (int i = 0; i < list.length; i++) {
                            list[i] = (rs.getString(i + 1));
                        }

                        ((DefaultTableModel) this.batchTbl.getModel()).insertRow(count, list);
                        count++;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SearchBatch.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }


    }//GEN-LAST:event_searchBtnActionPerformed

    private void batchTblMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batchTblMousePressed
        if (batchTbl.getSelectedRow() != -1) {
            this.selectBtn.setEnabled(true);
        }
    }//GEN-LAST:event_batchTblMousePressed

    private void suppBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suppBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_suppBoxActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField batchIdTxt;
    private javax.swing.JLabel batchLbl;
    private javax.swing.JTable batchTbl;
    private javax.swing.JComboBox colourBox;
    private javax.swing.JLabel colourLbl;
    private javax.swing.JButton exitBtn;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton selectBtn;
    private javax.swing.JComboBox stageBox;
    private javax.swing.JLabel stageLbl;
    private javax.swing.JComboBox suppBox;
    private javax.swing.JLabel supplierLbl;
    private javax.swing.JComboBox typeBox;
    private javax.swing.JLabel typeLbl;
    // End of variables declaration//GEN-END:variables
}
