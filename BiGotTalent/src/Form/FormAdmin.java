/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import Koneksi.koneksi;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormAdmin extends javax.swing.JFrame {
    DefaultTableModel model;

    public FormAdmin() {
        initComponents();
        setLocationRelativeTo(null);
        loadDataPendaftar();
    }

    public void loadDataPendaftar() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama Siswa");
        model.addColumn("Mata Lomba");
        model.addColumn("Status Tahapan");
        tblPendaftar.setModel(model);
        
        try {
            Connection c = koneksi.configDB();
            Statement s = c.createStatement();
            String sql = "SELECT pendaftaran.id, users.username, lomba.nama_lomba, pendaftaran.status " +
                         "FROM pendaftaran " +
                         "JOIN users ON pendaftaran.user_id = users.id " +
                         "JOIN lomba ON pendaftaran.lomba_id = lomba.id";
            var r = s.executeQuery(sql);
            while (r.next()) {
                model.addRow(new Object[]{
                    r.getString("id"),
                    r.getString("username"),
                    r.getString("nama_lomba"),
                    r.getString("status")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage());
        }
    }

    private void btnSimpanLombaActionPerformed(java.awt.event.ActionEvent evt) {                                               
        try {
            Connection c = koneksi.configDB();
            Statement s = c.createStatement();
            String sql = "INSERT INTO lomba (nama_lomba) VALUES ('" + txtNamaLomba.getText() + "')";
            s.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Mata Lomba berhasil ditambahkan!");
            txtNamaLomba.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal tambah lomba: " + e.getMessage());
        }
    }                                              

    private void btnUbahStatusActionPerformed(java.awt.event.ActionEvent evt) {                                              
        int baris = tblPendaftar.getSelectedRow();
        if (baris == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data siswa di tabel terlebih dahulu!");
            return;
        }
        String idPendaftaran = model.getValueAt(baris, 0).toString();
        String statusBaru = JOptionPane.showInputDialog(this, "Masukkan Status Baru (Contoh: Tahap Briefing / Pelatihan / Selesai):");
        
        if (statusBaru != null && !statusBaru.trim().isEmpty()) {
            try {
                Connection c = koneksi.configDB();
                Statement s = c.createStatement();
                String sql = "UPDATE pendaftaran SET status='" + statusBaru + "' WHERE id='" + idPendaftaran + "'";
                s.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Status berhasil diperbarui!");
                loadDataPendaftar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal update status: " + e.getMessage());
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNamaLomba = new javax.swing.JTextField();
        btnSimpanLomba = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPendaftar = new javax.swing.JTable();
        btnUbahStatus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PANEL ADMIN- BI GOT TALENT");

        jLabel2.setText("INPUT LOMBA");

        txtNamaLomba.addActionListener(this::txtNamaLombaActionPerformed);

        btnSimpanLomba.setText("input");

        tblPendaftar.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblPendaftar);

        btnUbahStatus.setText("update");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(47, 47, 47)
                        .addComponent(txtNamaLomba)
                        .addGap(18, 18, 18)
                        .addComponent(btnSimpanLomba, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnUbahStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 60, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNamaLomba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpanLomba))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUbahStatus)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaLombaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaLombaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaLombaActionPerformed

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
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FormAdmin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSimpanLomba;
    private javax.swing.JButton btnUbahStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPendaftar;
    private javax.swing.JTextField txtNamaLomba;
    // End of variables declaration//GEN-END:variables
}
