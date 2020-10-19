package game_play;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class WelcomeScreen extends javax.swing.JFrame {

    /**
     * Creates new form JFrame
     */
    public static String userName;

    public WelcomeScreen(String name) {
        setContentPane(new JLabel(new ImageIcon("Background2.png")));
        initComponents();
        welcomelabel.setBackground(new java.awt.Color(0, 0, 0, 1));
        if (userName != null) {
            welcomelabel.setText("Welcome, " + userName);
        } else {
            welcomelabel.setText("Welcome");
        }
        setBounds(400, 200, 505, 350);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          PlayButton = new javax.swing.JButton();
          InfoButton = new javax.swing.JButton();
          ExitButton = new javax.swing.JButton();
          welcomelabel = new javax.swing.JLabel();

          setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
          getContentPane().setLayout(null);

          PlayButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
          PlayButton.setForeground(new java.awt.Color(0, 0, 153));
          PlayButton.setText("Play");
          PlayButton.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    PlayButtonActionPerformed(evt);
               }
          });
          getContentPane().add(PlayButton);
          PlayButton.setBounds(110, 90, 90, 30);

          InfoButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
          InfoButton.setForeground(new java.awt.Color(0, 0, 153));
          InfoButton.setText("Info");
          InfoButton.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    InfoButtonActionPerformed(evt);
               }
          });
          getContentPane().add(InfoButton);
          InfoButton.setBounds(110, 140, 90, 30);

          ExitButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
          ExitButton.setForeground(new java.awt.Color(0, 0, 153));
          ExitButton.setText("Exit");
          ExitButton.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ExitButtonActionPerformed(evt);
               }
          });
          getContentPane().add(ExitButton);
          ExitButton.setBounds(110, 190, 90, 30);

          welcomelabel.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
          welcomelabel.setForeground(new java.awt.Color(0, 0, 153));
          getContentPane().add(welcomelabel);
          welcomelabel.setBounds(90, 30, 250, 50);

          pack();
     }// </editor-fold>//GEN-END:initComponents

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void PlayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayButtonActionPerformed
        WelcomeScreen.super.setVisible(false);
        PlayFrame playFrame = new PlayFrame();
        playFrame.setVisible(true);
    }//GEN-LAST:event_PlayButtonActionPerformed

     private void InfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InfoButtonActionPerformed
         WelcomeScreen.super.setVisible(false);
         InfoFrame infoFrame = new InfoFrame();
         infoFrame.setVisible(true);
     }//GEN-LAST:event_InfoButtonActionPerformed

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
            java.util.logging.Logger.getLogger(WelcomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new WelcomeScreen(userName).setVisible(true);
        });
    }

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JButton ExitButton;
     private javax.swing.JButton InfoButton;
     private javax.swing.JButton PlayButton;
     private javax.swing.JLabel welcomelabel;
     // End of variables declaration//GEN-END:variables
}
