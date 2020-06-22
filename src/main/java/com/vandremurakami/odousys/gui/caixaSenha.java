/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.gui;

import com.vandremurakami.util.ConfigXML;
import com.vandremurakami.util.Criptografia;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
/**
 *
 * @author Vandre
 */
public class caixaSenha extends javax.swing.JDialog {

    private boolean senhaValida = false;
    
    private final String senhaCriptografada;
    /**
     * Creates new form caixaAdicionarTipoServico
     * @param parent
     */
    public caixaSenha(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        senhaCriptografada = ConfigXML.getSenha();
    }

    public boolean senhaValida() {
        return senhaValida;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGerarSenha = new javax.swing.JPanel();
        labelSenha = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        labelOK = new javax.swing.JLabel();
        labelCancelar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Senha para Processamento");

        panelGerarSenha.setBackground(new java.awt.Color(254, 254, 254));
        panelGerarSenha.setPreferredSize(new java.awt.Dimension(1280, 720));
        panelGerarSenha.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelSenha.setText("Senha:");
        panelGerarSenha.add(labelSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 95, -1, -1));

        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        panelGerarSenha.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 220, 25));

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
        panelGerarSenha.add(labelOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, -1, -1));

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
        panelGerarSenha.add(labelCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGerarSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGerarSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        int key = evt.getKeyCode();
        //tecla enter pressionada
        if (key == KeyEvent.VK_ENTER) {
            try {
                if (Criptografia.validaHash(jPasswordField1.getText(), senhaCriptografada)) {
                    senhaValida = true;
                    this.setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Senha inválida.");
                    senhaValida = false;
                }
            }
            catch(Exception ex) {}
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void labelOKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelOKMouseClicked
        try {
            if (Criptografia.validaHash(jPasswordField1.getText(), senhaCriptografada)) {
                senhaValida = true;
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Senha inválida.");
                senhaValida = false;
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_labelOKMouseClicked

    private void labelCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCancelarMouseClicked
        senhaValida = false;
        this.setVisible(false);
    }//GEN-LAST:event_labelCancelarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JLabel labelCancelar;
    private javax.swing.JLabel labelOK;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JPanel panelGerarSenha;
    // End of variables declaration//GEN-END:variables
}
