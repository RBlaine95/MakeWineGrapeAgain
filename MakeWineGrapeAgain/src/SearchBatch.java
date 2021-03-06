
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
    String note;
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

                data = new String[7];

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

                data = new String[7];
                break;
            case "batch":
                this.colourBox.setModel(new DefaultComboBoxModel(Pinwheel.getColourAll().toArray()));
                this.typeBox.setModel(new DefaultComboBoxModel(Pinwheel.getTypeAll().toArray()));
                this.suppBox.setModel(new DefaultComboBoxModel(Pinwheel.getSupplierAll().toArray()));
                this.stageBox.setModel(new DefaultComboBoxModel(Pinwheel.getStagesAll().toArray()));
                data = new String[7];
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

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                backBtn.doClick();
            }
        });
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
        backBtn = new javax.swing.JButton();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel6.setText("Wine Type");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search Batches");
        setMinimumSize(new java.awt.Dimension(1500, 630));
        setResizable(false);
        getContentPane().setLayout(null);

        batchTbl.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
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

        backBtn.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        searchBtn.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        selectBtn.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        selectBtn.setText("Select");
        selectBtn.setEnabled(false);
        selectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectBtnActionPerformed(evt);
            }
        });

        suppBox.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        suppBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        suppBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suppBoxActionPerformed(evt);
            }
        });

        supplierLbl.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        supplierLbl.setText("Supplier");

        stageBox.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        stageBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        stageLbl.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        stageLbl.setText("Stage");

        typeBox.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        typeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        typeLbl.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        typeLbl.setText("Wine Type");

        colourBox.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        colourBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        colourLbl.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        colourLbl.setText("Wine Colour");

        batchIdTxt.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        batchLbl.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        batchLbl.setText("Batch ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(batchIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batchLbl))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(colourLbl)
                    .addComponent(colourBox, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(typeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeLbl))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stageBox, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stageLbl))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(supplierLbl)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(suppBox, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(selectBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backBtn)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(batchLbl)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(typeLbl)
                        .addComponent(stageLbl)
                        .addComponent(supplierLbl)
                        .addComponent(colourLbl)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batchIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colourBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stageBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suppBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectBtn)
                    .addComponent(searchBtn)
                    .addComponent(backBtn))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(170, 560, 800, 80);

        txtArea.setEditable(false);
        txtArea.setColumns(20);
        txtArea.setLineWrap(true);
        txtArea.setRows(5);
        txtArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtArea);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(1090, 40, 390, 340);

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setText("Notes");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(1090, 10, 100, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagery/WoodNew_1.jpg"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, -20, 1530, 690);

        setSize(new java.awt.Dimension(1516, 669));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void selectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBtnActionPerformed

        for (int i = 0; i < data.length; i++) {
            if (i < data.length-1) {
                data[i] = (String) this.batchTbl.getValueAt(this.batchTbl.getSelectedRow(), i);

            } else {
                System.out.println("adds note");
                data[i] = note;
            }

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
                        for (int i = 0; i < list.length - 1; i++) {
                            list[i] = (rs.getString(i + 1));
                        }
                        String combine = "";
                        for (int i = 6; i < 14; i++) {
                            String buffer = rs.getNString(i);

                            combine += (buffer == (null)) ? "" : buffer + ", ";
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
            String id = (String) this.batchTbl.getValueAt(this.batchTbl.getSelectedRow(), 0);
            String sql = "";
            switch (Pinwheel.searchType) {
                case "batch":
                    sql = "SELECT note FROM batch WHERE batchid = '" + id + "'";
                    break;
                case "subbatch":
                    sql = "SELECT note FROM subbatch WHERE subbatchid = '" + id + "'";
                    break;
                case "blend":
                    sql = "SELECT note FROM blend WHERE bid = '" + id + "'";
                    break;
            }
            try {
                ResultSet rs = Pinwheel.queryCCDB(sql);
                if (rs.next()) {
                    this.note = rs.getNString(1);
                    this.txtArea.setText(note);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SearchBatch.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField batchIdTxt;
    private javax.swing.JLabel batchLbl;
    private javax.swing.JTable batchTbl;
    private javax.swing.JComboBox colourBox;
    private javax.swing.JLabel colourLbl;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton selectBtn;
    private javax.swing.JComboBox stageBox;
    private javax.swing.JLabel stageLbl;
    private javax.swing.JComboBox suppBox;
    private javax.swing.JLabel supplierLbl;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JComboBox typeBox;
    private javax.swing.JLabel typeLbl;
    // End of variables declaration//GEN-END:variables
}
