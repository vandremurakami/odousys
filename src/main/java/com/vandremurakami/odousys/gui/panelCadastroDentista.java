/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.gui;

import com.vandremurakami.odousys.controle.ControleCadastroDentista;
import com.vandremurakami.odousys.modelo.Dentista;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;

/**
 *
 * @author vandre
 */
public class panelCadastroDentista extends javax.swing.JPanel {

    
    private final ControleCadastroDentista controleCadastroDentista;
    
    /**
     * Creates new form panelCadastroDentista
     * @param dialog
     * @param dentista
     */
    public panelCadastroDentista(JDialog dialog, Dentista dentista) {
        initComponents();
        controleCadastroDentista = new ControleCadastroDentista(dialog, this, dentista);
    }
    
    public String getNome() {
        return textFieldNome.getText().trim();
    }
    
    public void setNome(String nome) {
        textFieldNome.setText(nome);
    }
    
    public String getEndereco() {
        return textFieldEndereco.getText().trim();
    }
    
    public void setEndereco(String endereco) {
        textFieldEndereco.setText(endereco);
    }
    
    public String getCidade() {
        return textFieldCidade.getText().trim();
    }
    
    public void setCidade(String cidade) {
        textFieldCidade.setText(cidade);
    }
    
    public String getEstado() {
        return textFieldEstado.getText().trim();
    }
    
    public void setEstado(String estado) {
        textFieldEstado.setText(estado);
    }
    
    public String getCEP() {
        return textFieldCEP.getText().trim();
    }
    
    public void setCEP(String cep) {
        textFieldCEP.setText(cep);
    }
    
    public String getTelefone() {
        return textFieldTelefone.getText().trim();
    }
    
    public void setTelefone(String telefone) {
        textFieldTelefone.setText(telefone);
    }
    
    public String getCelular() {
        return textFieldCelular.getText().trim();
    }
    
    public void setCelular(String celular) {
        textFieldCelular.setText(celular);
    }
    
    public String getEmail() {
        return textFieldEmail.getText().trim();
    }
    
    public void setEmail(String email) {
        textFieldEmail.setText(email);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldCEP = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        textFieldTelefone = new javax.swing.JFormattedTextField();
        textFieldCidade = new javax.swing.JTextField();
        textFieldCelular = new javax.swing.JFormattedTextField();
        textFieldEmail = new javax.swing.JTextField();
        textFieldEstado = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textFieldEndereco = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textFieldNome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelSalvar = new javax.swing.JLabel();
        labelFechar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(254, 254, 254));
        setMaximumSize(new java.awt.Dimension(1060, 660));
        setMinimumSize(new java.awt.Dimension(1060, 660));
        setPreferredSize(new java.awt.Dimension(1060, 660));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        try {
            textFieldCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textFieldCEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldCEPKeyPressed(evt);
            }
        });
        add(textFieldCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 250, 110, 25));

        jLabel9.setText("Email:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, -1, -1));

        try {
            textFieldTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textFieldTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldTelefoneKeyPressed(evt);
            }
        });
        add(textFieldTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 180, 25));

        textFieldCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldCidadeKeyPressed(evt);
            }
        });
        add(textFieldCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 180, 25));

        try {
            textFieldCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textFieldCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldCelularKeyPressed(evt);
            }
        });
        add(textFieldCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 180, 25));
        add(textFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 350, 480, 25));

        try {
            textFieldEstado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("**")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textFieldEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldEstadoKeyPressed(evt);
            }
        });
        add(textFieldEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 250, 40, 25));

        jLabel6.setText("CEP:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, -1, -1));

        jLabel3.setText("Endereço:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, -1, -1));

        textFieldEndereco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldEnderecoKeyPressed(evt);
            }
        });
        add(textFieldEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 480, 25));

        jLabel8.setText("Telefone :");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, -1, -1));

        jLabel1.setText("Nome:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, -1, -1));

        jLabel4.setText("Cidade:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, -1, -1));

        textFieldNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldNomeKeyPressed(evt);
            }
        });
        add(textFieldNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 480, 25));

        jLabel10.setText("Celular :");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 310, -1, -1));

        jLabel5.setText("Estado:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, -1, -1));

        labelSalvar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSalvar.setText("Salvar");
        labelSalvar.setMaximumSize(new java.awt.Dimension(90, 35));
        labelSalvar.setMinimumSize(new java.awt.Dimension(90, 35));
        labelSalvar.setOpaque(true);
        labelSalvar.setPreferredSize(new java.awt.Dimension(90, 35));
        labelSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSalvarMouseClicked(evt);
            }
        });
        add(labelSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, -1, -1));

        labelFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFechar.setText("Fechar");
        labelFechar.setMaximumSize(new java.awt.Dimension(90, 35));
        labelFechar.setMinimumSize(new java.awt.Dimension(90, 35));
        labelFechar.setOpaque(true);
        labelFechar.setPreferredSize(new java.awt.Dimension(90, 35));
        labelFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelFecharMouseClicked(evt);
            }
        });
        add(labelFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 400, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void labelSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSalvarMouseClicked
        if(controleCadastroDentista != null)
            controleCadastroDentista.AdicionaAtualizaDentista();
    }//GEN-LAST:event_labelSalvarMouseClicked

    private void labelFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFecharMouseClicked
        if(controleCadastroDentista != null)
            controleCadastroDentista.Fechar();
    }//GEN-LAST:event_labelFecharMouseClicked

    private void textFieldNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldNomeKeyPressed
        int key = evt.getKeyCode();
        //tecla enter pressionada
        if (key == KeyEvent.VK_ENTER) {
            textFieldEndereco.requestFocus();
        }
    }//GEN-LAST:event_textFieldNomeKeyPressed

    private void textFieldEnderecoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldEnderecoKeyPressed
        int key = evt.getKeyCode();
        //tecla enter pressionada
        if (key == KeyEvent.VK_ENTER) {
            textFieldCidade.requestFocus();
        }
    }//GEN-LAST:event_textFieldEnderecoKeyPressed

    private void textFieldCidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldCidadeKeyPressed
        int key = evt.getKeyCode();
        //tecla enter pressionada
        if (key == KeyEvent.VK_ENTER) {
            textFieldEstado.requestFocus();
        }
    }//GEN-LAST:event_textFieldCidadeKeyPressed

    private void textFieldEstadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldEstadoKeyPressed
        int key = evt.getKeyCode();
        //tecla enter pressionada
        if (key == KeyEvent.VK_ENTER) {
            textFieldCEP.requestFocus();
        }
    }//GEN-LAST:event_textFieldEstadoKeyPressed

    private void textFieldCEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldCEPKeyPressed
        int key = evt.getKeyCode();
        //tecla enter pressionada
        if (key == KeyEvent.VK_ENTER) {
            textFieldTelefone.requestFocus();
        }
    }//GEN-LAST:event_textFieldCEPKeyPressed

    private void textFieldTelefoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldTelefoneKeyPressed
        int key = evt.getKeyCode();
        //tecla enter pressionada
        if (key == KeyEvent.VK_ENTER) {
            textFieldCelular.requestFocus();
        }
    }//GEN-LAST:event_textFieldTelefoneKeyPressed

    private void textFieldCelularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldCelularKeyPressed
        int key = evt.getKeyCode();
        //tecla enter pressionada
        if (key == KeyEvent.VK_ENTER) {
            textFieldEmail.requestFocus();
        }
    }//GEN-LAST:event_textFieldCelularKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelFechar;
    private javax.swing.JLabel labelSalvar;
    private javax.swing.JFormattedTextField textFieldCEP;
    private javax.swing.JFormattedTextField textFieldCelular;
    private javax.swing.JTextField textFieldCidade;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldEndereco;
    private javax.swing.JFormattedTextField textFieldEstado;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JFormattedTextField textFieldTelefone;
    // End of variables declaration//GEN-END:variables
}
