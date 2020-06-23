/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.gui;

import java.awt.event.KeyEvent;
import com.vandremurakami.odousys.controle.ControleLogin;
import com.vandremurakami.odousys.modelo.Usuario;
import javax.swing.JOptionPane;


/**
 *
 * @author vandre
 */
public class dialogLogin extends javax.swing.JDialog {

    private boolean loginValido = false;
    
    final private ControleLogin controleLogin = new ControleLogin();
    
    /**
     * Creates new form Login
     * @param parent
     * @param modal
     */
    public dialogLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public Usuario getUsuario() {
        return controleLogin.getUsuario();
    }
    
    public boolean validado() {
        return this.loginValido;
    }

    private void valida() {
        loginValido = controleLogin.validaUsuarioSenha(textFieldLogin.getText(), passwordFieldSenha.getText());
        if( loginValido ) {
            this.setVisible(false);
        }
        else {
            JOptionPane.showMessageDialog(null, "Login ou Senha inválida.");
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

        bg = new javax.swing.JPanel();
        panelLogo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelLoginSenha = new javax.swing.JPanel();
        passwordFieldSenha = new javax.swing.JPasswordField();
        textFieldLogin = new javax.swing.JTextField();
        labelOK = new javax.swing.JLabel();
        labelCancelar = new javax.swing.JLabel();
        labelLogin = new javax.swing.JLabel();
        labelSenha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setMinimumSize(null);
        setResizable(false);

        bg.setPreferredSize(new java.awt.Dimension(400, 600));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelLogo.setBackground(new java.awt.Color(250, 250, 250));
        panelLogo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N
        panelLogo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        bg.add(panelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 240));

        panelLoginSenha.setBackground(new java.awt.Color(250, 250, 250));
        panelLoginSenha.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        passwordFieldSenha.setMaximumSize(new java.awt.Dimension(17, 25));
        passwordFieldSenha.setMinimumSize(new java.awt.Dimension(17, 25));
        passwordFieldSenha.setPreferredSize(new java.awt.Dimension(17, 25));
        passwordFieldSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFieldSenhaFocusGained(evt);
            }
        });
        passwordFieldSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldSenhaKeyPressed(evt);
            }
        });
        panelLoginSenha.add(passwordFieldSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 100, 200, 30));

        textFieldLogin.setMaximumSize(new java.awt.Dimension(17, 25));
        textFieldLogin.setMinimumSize(new java.awt.Dimension(17, 25));
        textFieldLogin.setPreferredSize(new java.awt.Dimension(17, 25));
        textFieldLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textFieldLoginFocusGained(evt);
            }
        });
        textFieldLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldLoginKeyPressed(evt);
            }
        });
        panelLoginSenha.add(textFieldLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 50, 200, 30));

        labelOK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelOK.setText("OK");
        labelOK.setMaximumSize(new java.awt.Dimension(90, 35));
        labelOK.setMinimumSize(new java.awt.Dimension(90, 35));
        labelOK.setOpaque(true);
        labelOK.setPreferredSize(new java.awt.Dimension(90, 35));
        labelOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelOKMouseClicked(evt);
            }
        });
        panelLoginSenha.add(labelOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 158, -1, -1));

        labelCancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCancelar.setText("Cancelar");
        labelCancelar.setMaximumSize(new java.awt.Dimension(90, 35));
        labelCancelar.setMinimumSize(new java.awt.Dimension(90, 35));
        labelCancelar.setOpaque(true);
        labelCancelar.setPreferredSize(new java.awt.Dimension(90, 35));
        labelCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCancelarMouseClicked(evt);
            }
        });
        panelLoginSenha.add(labelCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 158, -1, -1));

        labelLogin.setText("Login:");
        panelLoginSenha.add(labelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 55, -1, -1));

        labelSenha.setText("Senha:");
        panelLoginSenha.add(labelSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 105, -1, -1));

        bg.add(panelLoginSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 390, 240));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldLoginKeyPressed
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            passwordFieldSenha.requestFocus();
        }
    }//GEN-LAST:event_textFieldLoginKeyPressed

    private void passwordFieldSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldSenhaKeyPressed
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            valida();
        }
    }//GEN-LAST:event_passwordFieldSenhaKeyPressed

    private void textFieldLoginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFieldLoginFocusGained
        textFieldLogin.selectAll();
    }//GEN-LAST:event_textFieldLoginFocusGained

    private void passwordFieldSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldSenhaFocusGained
        passwordFieldSenha.selectAll();
    }//GEN-LAST:event_passwordFieldSenhaFocusGained

    private void labelOKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelOKMouseClicked
        valida();
    }//GEN-LAST:event_labelOKMouseClicked

    private void labelCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCancelarMouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_labelCancelarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelCancelar;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelOK;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JPanel panelLoginSenha;
    private javax.swing.JPanel panelLogo;
    private javax.swing.JPasswordField passwordFieldSenha;
    private javax.swing.JTextField textFieldLogin;
    // End of variables declaration//GEN-END:variables
}
