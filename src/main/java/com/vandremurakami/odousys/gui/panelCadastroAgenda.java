/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.gui;

import com.vandremurakami.odousys.controle.ControleCadastroAgenda;
import com.vandremurakami.odousys.modelo.Agenda;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author vandre
 */
public class panelCadastroAgenda extends javax.swing.JPanel {

    
    private final ControleCadastroAgenda controleCadastroAgenda;
    
    /**
     * Creates new form panelCadastroAgenda
     * @param dialog
     * @param agenda
     * @param data
     */
    public panelCadastroAgenda(JDialog dialog, Agenda agenda, LocalDate data) {
        initComponents();
        controleCadastroAgenda = new ControleCadastroAgenda(dialog, this, agenda, data);
    }
    
    public LocalTime getHorario() {
        return (LocalTime)comboBoxHorario.getSelectedItem();
    }
    
    public void setHorario(LocalTime hora) {
        comboBoxHorario.setSelectedItem(hora);
    }
    
    public int getPosicaoDentista() {
        return comboBoxDentista.getSelectedIndex()-1;
    }
    
    public void setNomeDentista(String nome) {
        comboBoxDentista.setSelectedItem(nome);
    }
    
    public String getAnotacao() {
        return textPaneAnotacao.getText();
    }
    
    public void setAnotacao(String anotacao) {
        textPaneAnotacao.setText(anotacao);
    }

    public JComboBox getComboBoxDentista() {
        return comboBoxDentista;
    }
    
    public JComboBox getComboBoxHorario() {
        return comboBoxHorario;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelSalvar = new javax.swing.JLabel();
        labelFechar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboBoxDentista = new javax.swing.JComboBox<>();
        labelAnotacao = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        comboBoxHorario = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        textPaneAnotacao = new javax.swing.JTextPane();

        setBackground(new java.awt.Color(254, 254, 254));
        setMaximumSize(new java.awt.Dimension(1060, 660));
        setMinimumSize(new java.awt.Dimension(1060, 660));
        setPreferredSize(new java.awt.Dimension(1060, 660));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        add(labelSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 380, -1, -1));

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
        add(labelFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, -1, -1));

        jLabel2.setText("Horário:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 175, -1, -1));

        comboBoxDentista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecione uma das opções>" }));
        AutoCompleteDecorator.decorate(comboBoxDentista);
        add(comboBoxDentista, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 250, 25));

        labelAnotacao.setText("Anotação:");
        add(labelAnotacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, -1, -1));

        jLabel1.setText("Dentista:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 175, -1, -1));

        comboBoxHorario.setEditable(true);
        comboBoxHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecione uma das opções>" }));
        AutoCompleteDecorator.decorate(comboBoxHorario);
        add(comboBoxHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 250, 25));

        jScrollPane1.setViewportView(textPaneAnotacao);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 600, 120));
    }// </editor-fold>//GEN-END:initComponents

    private void labelSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSalvarMouseClicked
        if(controleCadastroAgenda != null)
        controleCadastroAgenda.AdicionaAtualizaAgenda();
    }//GEN-LAST:event_labelSalvarMouseClicked

    private void labelFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFecharMouseClicked
        if(controleCadastroAgenda != null)
            controleCadastroAgenda.Fechar();
    }//GEN-LAST:event_labelFecharMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxDentista;
    private javax.swing.JComboBox<String> comboBoxHorario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAnotacao;
    private javax.swing.JLabel labelFechar;
    private javax.swing.JLabel labelSalvar;
    private javax.swing.JTextPane textPaneAnotacao;
    // End of variables declaration//GEN-END:variables
}
