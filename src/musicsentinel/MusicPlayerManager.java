/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicsentinel;

import com.google.gson.Gson;
import musicsentinel.files.FileManager;
import musicsentinel.interfaces.ConsoleStatus;
import musicsentinel.pojos.CommunicationSettings;
import musicsentinel.services.TCPMusicClient;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author joaop
 */
public class MusicPlayerManager extends javax.swing.JFrame implements ConsoleStatus{

    private JFileChooser jFileChooser;
    
    private static String consoleLog="";

    /**
     * Creates new form MusicPlayerManager
     */
    public MusicPlayerManager() {
        initComponents();

        if(getCommunicationSettings()!=null){
            etIP.setText(getCommunicationSettings().getIp());
            etPort.setText(getCommunicationSettings().getPort());
        }
        else{
            etIP.setText(C.DEFAULT_IP);
            etPort.setText(C.DEFAULT_PORT);
        }
        taConsole.setLineWrap(true);
        taConsole.setWrapStyleWord(true);
        taConsole.setText(C.STATUS_OFFLINEMESSAGE);
        
        
        jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("C:"));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bFile = new javax.swing.JButton();
        bSave = new javax.swing.JButton();
        etChoose = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        etIP = new javax.swing.JTextField();
        etPort = new javax.swing.JTextField();
        rbOn = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taConsole = new javax.swing.JTextArea();
        rbOff = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Music Player");
        setResizable(false);

        bFile.setText("Open");
        bFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFileActionPerformed(evt);
            }
        });

        bSave.setText("Save");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        etChoose.setEditable(false);
        etChoose.setText("Open file");
        etChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etChooseActionPerformed(evt);
            }
        });

        jLabel1.setText("Change music");

        jLabel2.setText("Music Player Program");

        etIP.setText("IP");
        etIP.setToolTipText("IP");

        etPort.setText("Port");

        rbOn.setText("On");

        taConsole.setEditable(false);
        taConsole.setColumns(5);
        taConsole.setRows(4);
        taConsole.setAutoscrolls(false);
        jScrollPane1.setViewportView(taConsole);
        taConsole.getAccessibleContext().setAccessibleDescription("");

        rbOff.setSelected(true);
        rbOff.setText("Off");
        rbOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOffActionPerformed(evt);
            }
        });

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(etIP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etPort, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bFile, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbOn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbOff))
                            .addComponent(jLabel1)
                            .addComponent(etChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbOn)
                    .addComponent(rbOff))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bSave))
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFileActionPerformed
        // TODO add your handling code here:
        int result = jFileChooser.showOpenDialog(new JFrame());

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            etChoose.setText(selectedFile.getAbsolutePath());
        }
    }//GEN-LAST:event_bFileActionPerformed

    private void etChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etChooseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etChooseActionPerformed

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        // TODO add your handling code here:

        String selectedPath = etChoose.getText();
        String ext = FileManager.getFileExtension(selectedPath);
        
        System.out.println("extension: "+ext);
        System.out.println("filepath: "+selectedPath);

        if (ext.length() == C.ACCEPTED_EXTENSION.length() && ext.contains(C.ACCEPTED_EXTENSION)) {
            try {
                FileManager.copyFile(new File(selectedPath), new File(C.FILE_PATH + C.MUSIC_FILE_NAME + "." + ext));
            } catch (IOException ex) {
                Logger.getLogger(MusicPlayerManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            taConsole.setText(consoleLog.concat("This format is not allowed. Please use a mp3 file."));
        }

        etChoose.setText(C.etChooseString);

    }//GEN-LAST:event_bSaveActionPerformed

    private void rbOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbOffActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jButton1.setEnabled(false);
        saveCommunicationSettings(etIP.getText(),etPort.getText());
        TCPMusicClient mWorker = new TCPMusicClient(etIP.getText(),etPort.getText());
        mWorker.killService();
        mWorker.startService(this);
       // mWorker.startMusic();        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(MusicPlayerManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MusicPlayerManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MusicPlayerManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MusicPlayerManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MusicPlayerManager().setVisible(true);
            }
        });
    }
    
    private void saveCommunicationSettings(String ip, String port){
        
        if(ip==null || port==null){
            return;
        }
               
        Gson mGson = new Gson();
        CommunicationSettings mSettings = new CommunicationSettings(ip, port);
        String content = mGson.toJson(mSettings, CommunicationSettings.class);
        
        FileManager.saveTextFile(C.FILE_SETTINGSNAME, content);
    
    }
    
    private CommunicationSettings getCommunicationSettings(){  
        String content = FileManager.getTextFileContent(C.FILE_SETTINGSNAME);      
        
        if(content!=null){        
            Gson mGson = new Gson();
            CommunicationSettings mSettings = mGson.fromJson(content, CommunicationSettings.class);
            return mSettings;            
        }
        
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bFile;
    private javax.swing.JButton bSave;
    private javax.swing.JTextField etChoose;
    private javax.swing.JTextField etIP;
    private javax.swing.JTextField etPort;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbOff;
    private javax.swing.JRadioButton rbOn;
    private javax.swing.JTextArea taConsole;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateconsoleMessage(String message, boolean on) {   
        taConsole.setText(message);
        rbOn.setSelected(on);
        rbOff.setSelected(!on);
        
        if(!on){
            jButton1.setEnabled(true);
        }
    }
}
