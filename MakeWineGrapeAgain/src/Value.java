
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Se7en
 */
public class Value extends javax.swing.JFrame {

    DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Creates new form value
     */
    public Value() {
        initComponents();
        this.amountPerBottle.setText("750");
        this.selectedTxt.setText(Pinwheel.data[0]);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                backBtn.doClick();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        selectedTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        costPerBottle = new javax.swing.JTextField();
        costPerCork = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        afterBottling = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        beforeBottling = new javax.swing.JTextField();
        calculateBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        amountPerBottle = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        pricePerBottle = new javax.swing.JTextField();
        backBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Value Calculator");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setText("Selected:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 17, -1, -1));

        selectedTxt.setEditable(false);
        selectedTxt.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        getContentPane().add(selectedTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 11, 238, -1));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setText("Cost per Bottle");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 87, -1, -1));

        costPerBottle.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        costPerBottle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                costPerBottleKeyReleased(evt);
            }
        });
        getContentPane().add(costPerBottle, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 84, 70, -1));

        costPerCork.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        costPerCork.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                costPerCorkKeyReleased(evt);
            }
        });
        getContentPane().add(costPerCork, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 125, 70, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel3.setText("Cost per Cork/Screw cap");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel4.setText("After Bottling");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(293, 128, -1, -1));

        afterBottling.setEditable(false);
        afterBottling.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        getContentPane().add(afterBottling, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 125, 80, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel5.setText("Before Bottling");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 87, -1, -1));

        beforeBottling.setEditable(false);
        beforeBottling.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        getContentPane().add(beforeBottling, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 84, 80, -1));

        calculateBtn.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        calculateBtn.setText("Calculate");
        calculateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateBtnActionPerformed(evt);
            }
        });
        getContentPane().add(calculateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 231, -1, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel9.setText("Amount (ml) per bottle");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 169, -1, -1));

        amountPerBottle.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        amountPerBottle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                amountPerBottleKeyReleased(evt);
            }
        });
        getContentPane().add(amountPerBottle, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 166, 70, -1));

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel10.setText("Price Per Bottle");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 169, -1, -1));

        pricePerBottle.setEditable(false);
        pricePerBottle.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        getContentPane().add(pricePerBottle, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 166, 80, -1));

        backBtn.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        getContentPane().add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 231, -1, -1));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagery/WoodNew.jpg"))); // NOI18N
        jLabel6.setText("jLabel6");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 270));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void calculateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateBtnActionPerformed
        try {
            this.recalculate();
        } catch (SQLException ex) {
            Logger.getLogger(Value.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_calculateBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void costPerBottleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costPerBottleKeyReleased
        this.costPerBottle.setText(this.costPerBottle.getText().replaceAll("[^\\d.]", ""));
    }//GEN-LAST:event_costPerBottleKeyReleased

    private void costPerCorkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costPerCorkKeyReleased
        this.costPerCork.setText(this.costPerCork.getText().replaceAll("[^\\d.]", ""));
    }//GEN-LAST:event_costPerCorkKeyReleased

    private void amountPerBottleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountPerBottleKeyReleased
        this.amountPerBottle.setText(this.amountPerBottle.getText().replaceAll("[^\\d.]", ""));
    }//GEN-LAST:event_amountPerBottleKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Value.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Value.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Value.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Value.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Value().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField afterBottling;
    private javax.swing.JTextField amountPerBottle;
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField beforeBottling;
    private javax.swing.JButton calculateBtn;
    private javax.swing.JTextField costPerBottle;
    private javax.swing.JTextField costPerCork;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField pricePerBottle;
    private javax.swing.JTextField selectedTxt;
    // End of variables declaration//GEN-END:variables
private void recalculate() throws SQLException {
        if (!costPerBottle.getText().isEmpty() && !costPerCork.getText().isEmpty() && !amountPerBottle.getText().isEmpty()) {
            double cpb = Double.parseDouble(costPerBottle.getText());
            double cpc = Double.parseDouble(costPerCork.getText());
            double apb = Double.parseDouble(amountPerBottle.getText());
            double chemvalue = 0;
            String chemical;
            double amount;
            String sql = "SELECT * FROM " + this.selectedTxt.getText();
            ResultSet rs = Pinwheel.queryChem(sql);
            while (rs.next()) {
                System.out.println("found chem");
                chemical = rs.getNString(1);
                amount = rs.getDouble(2);
                System.out.println("Chemical" + chemical + "\nAmount: " + amount);
                sql = "SELECT value FROM chemicaltbl WHERE chemical = '" + chemical + "'";
                ResultSet rs1 = Pinwheel.queryChem(sql);
                rs1.next();
                double value = rs1.getDouble(1);
                System.out.println("Value:" + value);
                chemvalue += amount * value;
            }
            this.beforeBottling.setText(df.format(chemvalue) + "");
            double mass = Double.parseDouble(Pinwheel.data[4]);
            int bottles = (int) (mass / apb);
            double bottlevalue = (bottles * (cpb + cpc));
            this.afterBottling.setText((df.format(chemvalue + bottlevalue)) + "");

            this.pricePerBottle.setText(df.format(Double.parseDouble(this.afterBottling.getText()) / bottles) + "");
        }
    }
}
