/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.klijent;

import domain.Klijent;
import java.awt.Frame;
import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sesion.Session;
import thread.MojInterfejs;
import thread.ThreadClock;

/**
 *
 * @author Rastko
 */
public class FormKlijentTable extends javax.swing.JDialog implements MojInterfejs{

    /**
     * Creates new form FormKlijentTable
     */
    private Thread tht,thc;
    public FormKlijentTable(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            setLocationRelativeTo(null);
            initComponents();
            tblKlijent.setModel(new TableModelKlijent(this));
            thc=new ThreadClock(txtClock);
            thc.start();
            tht=new Thread((TableModelKlijent)tblKlijent.getModel());
            tht.start();
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            Session.getInstance().getThread().setMojInterfejs(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblKlijent = new javax.swing.JTable();
        btnPrikazi = new javax.swing.JButton();
        txtKriterijum = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtClock = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        tblKlijent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblKlijent);

        btnPrikazi.setText("Prikazi");
        btnPrikazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrikaziActionPerformed(evt);
            }
        });

        txtKriterijum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKriterijumKeyReleased(evt);
            }
        });

        jLabel1.setText("Pretraga po prezimenu");

        txtClock.setText("jLabel2");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKriterijum, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtClock)
                    .addComponent(btnCancel)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(btnPrikazi)))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPrikazi)
                        .addGap(18, 18, 18)
                        .addComponent(txtClock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtKriterijum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(45, 45, 45)
                        .addComponent(btnCancel)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void endProgram(){
        JOptionPane.showMessageDialog(rootPane, "Server vam je prekinuo konekciju");
        this.dispose();      
        System.exit(0);

    }
    private void btnPrikaziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrikaziActionPerformed
        if(tblKlijent.getSelectedRow()!=-1){
        Klijent k=((TableModelKlijent)tblKlijent.getModel()).getKlijent(tblKlijent.getSelectedRow());
        FormKlijent fk=new FormKlijent((Frame)this.getParent(), rootPaneCheckingEnabled,k);
        fk.setVisible(true);
        }
    }//GEN-LAST:event_btnPrikaziActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        TableModelKlijent tbmk = (TableModelKlijent) tblKlijent.getModel();
        tbmk.refreshTable();
    }//GEN-LAST:event_formWindowGainedFocus

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
       thc.interrupt();
       tht.interrupt();
       this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtKriterijumKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKriterijumKeyReleased
        String kriterijum = txtKriterijum.getText().trim();
        try {
            TableModelKlijent tbmk = (TableModelKlijent) tblKlijent.getModel();
            tbmk.pretraga(kriterijum);

        } catch (Exception e) {
            if(e instanceof SocketException)
                endProgram();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_txtKriterijumKeyReleased

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnPrikazi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKlijent;
    private javax.swing.JLabel txtClock;
    private javax.swing.JTextField txtKriterijum;
    // End of variables declaration//GEN-END:variables
}
