/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import Koneksi.koneksi;
import java.sql.*;
import javax.swing.JOptionPane;

public class FormSiswa extends javax.swing.JFrame {
    int idLoginSiswa;

    public FormSiswa(int idUser) {
        initComponents();
        setLocationRelativeTo(null);
        this.idLoginSiswa = idUser;
        loadPilihanLomba();
        cekStatusSiswa();
    }

    private FormSiswa() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void loadPilihanLomba() {
        cmbLomba.removeAllItems();
        try {
            Connection c = koneksi.configDB();
            Statement s = c.createStatement();
            var r = s.executeQuery("SELECT * FROM lomba");
            while (r.next()) {
                cmbLomba.addItem(r.getString("id") + " - " + r.getString("nama_lomba"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat lomba: " + e.getMessage());
        }
    }

    private void cekStatusSiswa() {
        try {
            Connection c = koneksi.configDB();
            Statement s = c.createStatement();
            var r = s.executeQuery("SELECT * FROM pendaftaran WHERE user_id='" + idLoginSiswa + "'");
            if (r.next()) {
                String status = r.getString("status");
                lblStatus.setText("Status Tahapan Anda: " + status);
            } else {
                lblStatus.setText("Status Tahapan Anda: Belum Mendaftarkan Diri");
            }
        } catch (Exception e) {
            lblStatus.setText("Status: Gagal memuat data");
        }
    }

    private void btnDaftarActionPerformed(java.awt.event.ActionEvent evt) {                                        
        try {
            String selectedItem = cmbLomba.getSelectedItem().toString();
            String idLomba = selectedItem.split(" - ")[0]; // Ambil ID lomba di depan
            
            Connection c = koneksi.configDB();
            Statement s = c.createStatement();
            
            // Cek apakah sudah pernah daftar
            var r = s.executeQuery("SELECT * FROM pendaftaran WHERE user_id='" + idLoginSiswa + "'");
            if (r.next()) {
                JOptionPane.showMessageDialog(this, "Anda sudah terdaftar di lomba!");
                return;
            }
            
            String sql = "INSERT INTO pendaftaran (user_id, lomba_id, status) VALUES ('" 
                    + idLoginSiswa + "', '" + idLomba + "', 'Dokumen dalam Tinjauan')";
            s.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Pendaftaran Berhasil! Menunggu verifikasi admin.");
            cekStatusSiswa();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal mendaftar: " + e.getMessage());
        }
    }  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbLomba = new javax.swing.JComboBox<>();
        btnDaftar = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("DASBOARD SISWA - BI GOT TALENT");

        cmbLomba.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnDaftar.setText("daftar");

        lblStatus.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbLomba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDaftar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(187, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cmbLomba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDaftar)
                .addGap(18, 18, 18)
                .addComponent(lblStatus)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FormSiswa().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDaftar;
    private javax.swing.JComboBox<String> cmbLomba;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables
}
