/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.gui;

import com.vandremurakami.odousys.controle.ControleListaPagamento;
import javax.swing.JComboBox;
import javax.swing.JTable;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Vandre
 */
public class panelListarPagamento extends javax.swing.JPanel {

    private final ControleListaPagamento controleListaPagamento;
    
    /**
     * Creates new form painelListarPagamento
     */
    public panelListarPagamento() {
        initComponents();
        controleListaPagamento = new ControleListaPagamento(this);
    }

    public JTable getTabelaPagamento() {
        return tablePagamento; 
    }
    
    public JComboBox getComboBoxDentista() {
        return comboBoxDentista; 
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPaneTabelaPagamento = new javax.swing.JScrollPane();
        tablePagamento = new javax.swing.JTable();
        labelFiltroDentista = new javax.swing.JLabel();
        comboBoxDentista = new javax.swing.JComboBox<>();
        labelFechar = new javax.swing.JLabel();
        labelAbrir = new javax.swing.JLabel();
        labelAdicionar = new javax.swing.JLabel();
        labelConfere = new javax.swing.JLabel();
        labelLimpar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(254, 254, 254));
        setMaximumSize(new java.awt.Dimension(1260, 630));
        setMinimumSize(new java.awt.Dimension(1260, 630));
        setPreferredSize(new java.awt.Dimension(1260, 630));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollPaneTabelaPagamento.setMaximumSize(new java.awt.Dimension(1085, 410));
        scrollPaneTabelaPagamento.setMinimumSize(new java.awt.Dimension(1085, 410));
        scrollPaneTabelaPagamento.setPreferredSize(new java.awt.Dimension(1085, 410));

        tablePagamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Data", "Data Pagamento", "Dentista", "Tipo de Pagamento", "Valor", "Status", "Conferido"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePagamento.setMaximumSize(null);
        tablePagamento.setMinimumSize(null);
        tablePagamento.setPreferredSize(null);
        tablePagamento.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablePagamento.getTableHeader().setReorderingAllowed(false);
        tablePagamento.getColumnModel().getColumn(0).setPreferredWidth(10);
        tablePagamento.getColumnModel().getColumn(1).setPreferredWidth(20);
        tablePagamento.getColumnModel().getColumn(2).setPreferredWidth(20);
        tablePagamento.getColumnModel().getColumn(3).setPreferredWidth(200);
        tablePagamento.getColumnModel().getColumn(4).setPreferredWidth(50);
        tablePagamento.getColumnModel().getColumn(5).setPreferredWidth(20);
        tablePagamento.getColumnModel().getColumn(6).setPreferredWidth(50);
        tablePagamento.getColumnModel().getColumn(7).setPreferredWidth(5);
        tablePagamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePagamentoMouseClicked(evt);
            }
        });
        scrollPaneTabelaPagamento.setViewportView(tablePagamento);

        add(scrollPaneTabelaPagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 1085, 410));

        labelFiltroDentista.setText("Filtrar por Dentista:");
        add(labelFiltroDentista, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        comboBoxDentista.setEditable(true);
        comboBoxDentista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecione uma das opções>" }));
        AutoCompleteDecorator.decorate(comboBoxDentista);
        comboBoxDentista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxDentistaActionPerformed(evt);
            }
        });
        add(comboBoxDentista, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 35, 250, 25));

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
        add(labelFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 570, -1, -1));

        labelAbrir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAbrir.setText("Abrir");
        labelAbrir.setMaximumSize(new java.awt.Dimension(90, 35));
        labelAbrir.setMinimumSize(new java.awt.Dimension(90, 35));
        labelAbrir.setOpaque(true);
        labelAbrir.setPreferredSize(new java.awt.Dimension(90, 35));
        labelAbrir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAbrirMouseClicked(evt);
            }
        });
        add(labelAbrir, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 570, -1, -1));

        labelAdicionar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAdicionar.setText("Adicionar");
        labelAdicionar.setMaximumSize(new java.awt.Dimension(90, 35));
        labelAdicionar.setMinimumSize(new java.awt.Dimension(90, 35));
        labelAdicionar.setOpaque(true);
        labelAdicionar.setPreferredSize(new java.awt.Dimension(90, 35));
        labelAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAdicionarMouseClicked(evt);
            }
        });
        add(labelAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 570, -1, -1));

        labelConfere.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelConfere.setText("Confere");
        labelConfere.setMaximumSize(new java.awt.Dimension(90, 35));
        labelConfere.setMinimumSize(new java.awt.Dimension(90, 35));
        labelConfere.setOpaque(true);
        labelConfere.setPreferredSize(new java.awt.Dimension(90, 35));
        labelConfere.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelConfereMouseClicked(evt);
            }
        });
        add(labelConfere, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 540, -1, -1));

        labelLimpar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLimpar.setText("Limpar");
        labelLimpar.setMaximumSize(new java.awt.Dimension(90, 35));
        labelLimpar.setMinimumSize(new java.awt.Dimension(90, 35));
        labelLimpar.setOpaque(true);
        labelLimpar.setPreferredSize(new java.awt.Dimension(90, 35));
        labelLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelLimparMouseClicked(evt);
            }
        });
        add(labelLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 30, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void tablePagamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePagamentoMouseClicked
        if (evt.getClickCount() == 2) {
            if(controleListaPagamento != null)
                controleListaPagamento.CadastroAbrirPagamento();
        }
    }//GEN-LAST:event_tablePagamentoMouseClicked

    private void labelFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFecharMouseClicked
        if(controleListaPagamento != null)
            controleListaPagamento.FecharListaPagamento();
    }//GEN-LAST:event_labelFecharMouseClicked

    private void labelAbrirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAbrirMouseClicked
        if(controleListaPagamento != null)
            controleListaPagamento.CadastroAbrirPagamento();
    }//GEN-LAST:event_labelAbrirMouseClicked

    private void labelAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAdicionarMouseClicked
        if(controleListaPagamento != null)
            controleListaPagamento.CadastroAdicionarPagamento();
    }//GEN-LAST:event_labelAdicionarMouseClicked

    private void labelConfereMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelConfereMouseClicked
        if(controleListaPagamento != null)
            controleListaPagamento.ConferePagamento();
    }//GEN-LAST:event_labelConfereMouseClicked

    private void labelLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLimparMouseClicked
        comboBoxDentista.setSelectedIndex(0);
        if(controleListaPagamento != null)
            controleListaPagamento.PreencheTabelaPagamento();
    }//GEN-LAST:event_labelLimparMouseClicked

    private void comboBoxDentistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxDentistaActionPerformed
        if(controleListaPagamento != null)
            controleListaPagamento.PreencheTabelaPagamento();
    }//GEN-LAST:event_comboBoxDentistaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxDentista;
    private javax.swing.JLabel labelAbrir;
    private javax.swing.JLabel labelAdicionar;
    private javax.swing.JLabel labelConfere;
    private javax.swing.JLabel labelFechar;
    private javax.swing.JLabel labelFiltroDentista;
    private javax.swing.JLabel labelLimpar;
    private javax.swing.JScrollPane scrollPaneTabelaPagamento;
    private javax.swing.JTable tablePagamento;
    // End of variables declaration//GEN-END:variables
}