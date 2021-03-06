/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.gui;

import com.vandremurakami.odousys.controle.ControlePrincipal;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author vandre
 */
public class framePrincipal extends javax.swing.JFrame {
    
    final private ControlePrincipal controlePrincipal = new ControlePrincipal(this);
    
    /**
     * Creates new form TelaPrincipal
     */
    public framePrincipal() {

        controlePrincipal.conectaBD();
        
        controlePrincipal.login();
        
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/aparelho.png")));
        
        panelMenuCadastro.setVisible(false);
        labelMenuFaturamentoMensal.setVisible(false);
        
        ControlePrincipal.mostraHome();
    }
    
    public void setPanelBody(JPanel panel, String titulo) {
        labelTitulo.setText(titulo);
        bodyScrollPanel.setViewportView(panel);
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
        topPane = new javax.swing.JPanel();
        labelMenuCadastro = new javax.swing.JLabel();
        labelMenuAgenda = new javax.swing.JLabel();
        labelMenuOrcamento = new javax.swing.JLabel();
        labelMenuPagamento = new javax.swing.JLabel();
        labelMenuExtrato = new javax.swing.JLabel();
        labelMenuFaturamentoMensal = new javax.swing.JLabel();
        labelMenuBackup = new javax.swing.JLabel();
        panelMenuCadastro = new javax.swing.JPanel();
        labelMenuDentista = new javax.swing.JLabel();
        labelMenuTabelaPreco = new javax.swing.JLabel();
        labelMenuUsuario = new javax.swing.JLabel();
        labelMenuPaciente = new javax.swing.JLabel();
        panelTitulo = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        bodyScrollPanel = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Odousys");
        setMaximumSize(new java.awt.Dimension(1280, 760));
        setMinimumSize(new java.awt.Dimension(1280, 760));
        setPreferredSize(new java.awt.Dimension(1280, 760));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bg.setBackground(new java.awt.Color(230, 230, 230));
        bg.setMaximumSize(new java.awt.Dimension(1280, 760));
        bg.setMinimumSize(new java.awt.Dimension(1280, 760));
        bg.setName(""); // NOI18N
        bg.setPreferredSize(new java.awt.Dimension(1280, 760));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topPane.setBackground(new java.awt.Color(0, 68, 85));
        topPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelMenuCadastro.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelMenuCadastro.setForeground(new java.awt.Color(254, 254, 254));
        labelMenuCadastro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMenuCadastro.setText("Cadastro");
        labelMenuCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMenuCadastroMouseClicked(evt);
            }
        });
        topPane.add(labelMenuCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -2, 140, 40));

        labelMenuAgenda.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelMenuAgenda.setForeground(new java.awt.Color(254, 254, 254));
        labelMenuAgenda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMenuAgenda.setText("Agenda");
        labelMenuAgenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMenuAgendaMouseClicked(evt);
            }
        });
        topPane.add(labelMenuAgenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, -2, 140, 40));

        labelMenuOrcamento.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelMenuOrcamento.setForeground(new java.awt.Color(254, 254, 254));
        labelMenuOrcamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMenuOrcamento.setText("Orçamento");
        labelMenuOrcamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMenuOrcamentoMouseClicked(evt);
            }
        });
        topPane.add(labelMenuOrcamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, -2, 140, 40));

        labelMenuPagamento.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelMenuPagamento.setForeground(new java.awt.Color(254, 254, 254));
        labelMenuPagamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMenuPagamento.setText("Pagamento");
        labelMenuPagamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMenuPagamentoMouseClicked(evt);
            }
        });
        topPane.add(labelMenuPagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, -2, 140, 40));

        labelMenuExtrato.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelMenuExtrato.setForeground(new java.awt.Color(254, 254, 254));
        labelMenuExtrato.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMenuExtrato.setText("Extrato");
        labelMenuExtrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMenuExtratoMouseClicked(evt);
            }
        });
        topPane.add(labelMenuExtrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, -2, 140, 40));

        labelMenuFaturamentoMensal.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelMenuFaturamentoMensal.setForeground(new java.awt.Color(254, 254, 254));
        labelMenuFaturamentoMensal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMenuFaturamentoMensal.setText("Faturamento");
        labelMenuFaturamentoMensal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMenuFaturamentoMensalMouseClicked(evt);
            }
        });
        topPane.add(labelMenuFaturamentoMensal, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, -2, 140, 40));

        labelMenuBackup.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelMenuBackup.setForeground(new java.awt.Color(254, 254, 254));
        labelMenuBackup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMenuBackup.setText("Backup");
        labelMenuBackup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMenuBackupMouseClicked(evt);
            }
        });
        topPane.add(labelMenuBackup, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, -2, 140, 40));

        bg.add(topPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 40));

        panelMenuCadastro.setBackground(new java.awt.Color(0, 68, 85));
        panelMenuCadastro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelMenuDentista.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelMenuDentista.setForeground(new java.awt.Color(254, 254, 254));
        labelMenuDentista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMenuDentista.setText("Dentistas");
        labelMenuDentista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMenuDentistaMouseClicked(evt);
            }
        });
        panelMenuCadastro.add(labelMenuDentista, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 140, 40));

        labelMenuTabelaPreco.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelMenuTabelaPreco.setForeground(new java.awt.Color(254, 254, 254));
        labelMenuTabelaPreco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMenuTabelaPreco.setText("Tabela Preços");
        labelMenuTabelaPreco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMenuTabelaPrecoMouseClicked(evt);
            }
        });
        panelMenuCadastro.add(labelMenuTabelaPreco, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 140, 40));

        labelMenuUsuario.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelMenuUsuario.setForeground(new java.awt.Color(254, 254, 254));
        labelMenuUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMenuUsuario.setText("Usuários");
        labelMenuUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMenuUsuarioMouseClicked(evt);
            }
        });
        panelMenuCadastro.add(labelMenuUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 140, 40));

        labelMenuPaciente.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelMenuPaciente.setForeground(new java.awt.Color(254, 254, 254));
        labelMenuPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMenuPaciente.setText("Pacientes");
        labelMenuPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMenuPacienteMouseClicked(evt);
            }
        });
        panelMenuCadastro.add(labelMenuPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 140, 40));

        bg.add(panelMenuCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 210));

        panelTitulo.setBackground(new java.awt.Color(75, 45, 60));
        panelTitulo.setForeground(new java.awt.Color(254, 254, 254));
        panelTitulo.setPreferredSize(new java.awt.Dimension(0, 60));
        panelTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelTituloMouseEntered(evt);
            }
        });
        panelTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTitulo.setBackground(new java.awt.Color(58, 46, 51));
        labelTitulo.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(254, 254, 254));
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Título");
        panelTitulo.add(labelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 60));

        bg.add(panelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 46, 1280, -1));

        bodyScrollPanel.setBackground(new java.awt.Color(254, 254, 254));
        bodyScrollPanel.setMaximumSize(null);
        bodyScrollPanel.setMinimumSize(null);
        bodyScrollPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bodyScrollPanelMouseEntered(evt);
            }
        });
        bg.add(bodyScrollPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1280, 650));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       controlePrincipal.shutdown();
    }//GEN-LAST:event_formWindowClosing

    private void labelMenuCadastroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMenuCadastroMouseClicked
        panelMenuCadastro.setVisible(!panelMenuCadastro.isVisible());
    }//GEN-LAST:event_labelMenuCadastroMouseClicked

    private void labelMenuDentistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMenuDentistaMouseClicked
        panelMenuCadastro.setVisible(false);
        controlePrincipal.mostraDentista();
    }//GEN-LAST:event_labelMenuDentistaMouseClicked

    private void labelMenuTabelaPrecoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMenuTabelaPrecoMouseClicked
        panelMenuCadastro.setVisible(false);
        controlePrincipal.mostraPreco();
    }//GEN-LAST:event_labelMenuTabelaPrecoMouseClicked

    private void labelMenuAgendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMenuAgendaMouseClicked
        panelMenuCadastro.setVisible(false);
        controlePrincipal.mostraAgenda();
    }//GEN-LAST:event_labelMenuAgendaMouseClicked

    private void labelMenuOrcamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMenuOrcamentoMouseClicked
        panelMenuCadastro.setVisible(false);
        controlePrincipal.mostraOrcamento();
    }//GEN-LAST:event_labelMenuOrcamentoMouseClicked

    private void labelMenuPagamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMenuPagamentoMouseClicked
        panelMenuCadastro.setVisible(false);
        controlePrincipal.mostraPagamento();
    }//GEN-LAST:event_labelMenuPagamentoMouseClicked

    private void labelMenuExtratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMenuExtratoMouseClicked
        panelMenuCadastro.setVisible(false);
        controlePrincipal.mostraExtrato();
    }//GEN-LAST:event_labelMenuExtratoMouseClicked

    private void panelTituloMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTituloMouseEntered
        panelMenuCadastro.setVisible(false);
    }//GEN-LAST:event_panelTituloMouseEntered

    private void bodyScrollPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bodyScrollPanelMouseEntered
        panelMenuCadastro.setVisible(false);
    }//GEN-LAST:event_bodyScrollPanelMouseEntered

    private void labelMenuFaturamentoMensalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMenuFaturamentoMensalMouseClicked
        panelMenuCadastro.setVisible(false);
        controlePrincipal.mostraFaturamento();
    }//GEN-LAST:event_labelMenuFaturamentoMensalMouseClicked

    private void labelMenuBackupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMenuBackupMouseClicked
        controlePrincipal.backupBD();
    }//GEN-LAST:event_labelMenuBackupMouseClicked

    private void labelMenuUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMenuUsuarioMouseClicked
        panelMenuCadastro.setVisible(false);
        controlePrincipal.mostraUsuario();
    }//GEN-LAST:event_labelMenuUsuarioMouseClicked

    private void labelMenuPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMenuPacienteMouseClicked
        panelMenuCadastro.setVisible(false);
        controlePrincipal.mostraPaciente();
    }//GEN-LAST:event_labelMenuPacienteMouseClicked

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(framePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(framePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(framePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(framePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new framePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JScrollPane bodyScrollPanel;
    private javax.swing.JLabel labelMenuAgenda;
    private javax.swing.JLabel labelMenuBackup;
    private javax.swing.JLabel labelMenuCadastro;
    private javax.swing.JLabel labelMenuDentista;
    private javax.swing.JLabel labelMenuExtrato;
    private javax.swing.JLabel labelMenuFaturamentoMensal;
    private javax.swing.JLabel labelMenuOrcamento;
    private javax.swing.JLabel labelMenuPaciente;
    private javax.swing.JLabel labelMenuPagamento;
    private javax.swing.JLabel labelMenuTabelaPreco;
    private javax.swing.JLabel labelMenuUsuario;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelMenuCadastro;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JPanel topPane;
    // End of variables declaration//GEN-END:variables
}
