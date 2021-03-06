/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import sesion.Session;

/**
 *
 * @author Rastko
 */
public class FormServer extends javax.swing.JFrame {

    /**
     * Creates new form FormServer
     */
    public FormServer() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        txtPort = new javax.swing.JTextField();
        btnKonektujSe = new javax.swing.JButton();
        lblAdresaServera = new javax.swing.JLabel();
        txtAdresaServera = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Broj porta:");

        btnKonektujSe.setText("Konektuj se");
        btnKonektujSe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonektujSeActionPerformed(evt);
            }
        });

        lblAdresaServera.setText("Adresa servera:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAdresaServera)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAdresaServera))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtPort))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(btnKonektujSe)
                        .addGap(0, 89, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAdresaServera)
                    .addComponent(txtAdresaServera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnKonektujSe)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKonektujSeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonektujSeActionPerformed
        int port;
        try{
             port=Integer.parseInt(txtPort.getText().trim());
             String url=txtAdresaServera.getText().trim();
             Session.setPort(port);
             Session.setUrl(url);
             Session.getInstance();
             FileWriter fw=new FileWriter("configFile.txt");
             fw.write("Broj porta="+Integer.toString(port)+'\n');
             fw.write("IP adresa servera="+url);
             fw.flush();
             fw.close();
             this.dispose();
             new FormLogin().setVisible(true);
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Niste pravilno uneli broj porta");
        }catch(IOException e){
            JOptionPane.showMessageDialog(rootPane, "Port je zauzet ili server ne postoji na zadatoj adresi");
        }
        
    }//GEN-LAST:event_btnKonektujSeActionPerformed

    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKonektujSe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAdresaServera;
    private javax.swing.JTextField txtAdresaServera;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables
}
