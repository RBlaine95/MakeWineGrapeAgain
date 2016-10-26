
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Se7en
 */
public class Update extends javax.swing.JFrame {

    String batch;
    String[] data;

    /**
     * Creates new form Update
     */
    public Update() {
        initComponents();
        this.stageBox.setModel(new DefaultComboBoxModel(Pinwheel.getStages().toArray()));
        this.data = Pinwheel.getData();
        this.massTxt.setEnabled(false);
        this.massTxt.setVisible(false);
        this.massLbl.setVisible(false);
        batch = data[0];
        this.stageBox.setSelectedIndex(Pinwheel.stageGetNo(data[3]));
        this.selectedTxt.setText(batch);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        selectedTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        massLbl = new javax.swing.JLabel();
        stageBox = new javax.swing.JComboBox();
        massTxt = new javax.swing.JTextField();
        okBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        spillBox = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Batch");
        getContentPane().setLayout(null);

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setText("Update Batch");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(120, 30, 110, 23);

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Selected:");

        selectedTxt.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Stage:");

        massLbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        massLbl.setText("New Mass");

        stageBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fermentation", "Pressed", "Maturation", "Blending", "Prep for Bottling", "Bottling", "Stroage" }));

        massTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                massTxtActionPerformed(evt);
            }
        });
        massTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                massTxtKeyReleased(evt);
            }
        });

        okBtn.setText("Update");
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });

        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        spillBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        spillBox.setText("Spillage");
        spillBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spillBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(stageBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(selectedTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(backBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(massLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spillBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(massTxt)
                    .addComponent(okBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectedTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stageBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(spillBox))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(massLbl)
                    .addComponent(massTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn)
                    .addComponent(okBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 90, 350, 170);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagery/WoodNew.jpg"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(-6, 0, 360, 310);

        setSize(new java.awt.Dimension(366, 339));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void spillBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spillBoxActionPerformed
        this.massTxt.setText("");
        if (spillBox.isSelected()) {
            this.massTxt.setVisible(true);
            this.massTxt.setEnabled(true);
            this.massLbl.setVisible(true);
        } else {
            this.massTxt.setEnabled(false);
            this.massTxt.setVisible(false);
            this.massLbl.setVisible(false);
        }
    }//GEN-LAST:event_spillBoxActionPerformed

    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed
        int check = JOptionPane.YES_OPTION;
        int masscheck = 0;
        if (this.stageBox.getSelectedIndex() < Pinwheel.stageGetNo(data[3])) {
            check = JOptionPane.showConfirmDialog(null, "You are attempting to roll back stage/s\nPlease Confirm", "Warning: Rolling back stages", JOptionPane.OK_CANCEL_OPTION);
        }

        String sql;
        if (check == JOptionPane.YES_OPTION) {
            if (this.massTxt.getText().isEmpty()) {
                sql = "UPDATE batch SET stage = '" + Pinwheel.stageGetNo(this.stageBox.getSelectedIndex() + 1 + "") + "' WHERE batchid = '" + data[0] + "'";
                Pinwheel.updateCCDB(sql);
            } else {
                if (Double.parseDouble(this.massTxt.getText()) > Double.parseDouble(data[4])) {
                    masscheck = JOptionPane.showConfirmDialog(null, "The new mass you have entered is more than the existing mass.\nPlease Confirm", "Warning: Added Mass", JOptionPane.OK_CANCEL_OPTION);
                }
                if (masscheck == JOptionPane.YES_OPTION) {
                    sql = "UPDATE batch SET stage = '" + Pinwheel.stageGetNo(this.stageBox.getSelectedIndex() + 1 + "") + "', mass = " + Double.parseDouble(this.massTxt.getText()) + " WHERE batchid = '" + data[0] + "'";
                    Pinwheel.updateCCDB(sql);
                }
            }

        }
    }//GEN-LAST:event_okBtnActionPerformed

    private void massTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_massTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_massTxtActionPerformed

    private void massTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_massTxtKeyReleased
        this.massTxt.setText(this.massTxt.getText().replaceAll("[^\\d.]", ""));
    }//GEN-LAST:event_massTxtKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel massLbl;
    private javax.swing.JTextField massTxt;
    private javax.swing.JButton okBtn;
    private javax.swing.JTextField selectedTxt;
    private javax.swing.JCheckBox spillBox;
    private javax.swing.JComboBox stageBox;
    // End of variables declaration//GEN-END:variables
}
