/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.gui;

import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.vandremurakami.odousys.modelo.Orcamento;
import com.vandremurakami.odousys.modelo.ServicoOrcamento;

/**
 *
 * @author Vandre
 */
public class caixaImprimirOrcamento extends javax.swing.JDialog {

    final int MARGEM = 5;
    
    private JFrame jFrame;    
    
    private Orcamento orcamento;
    
    /**
     * Creates new form caixaImprimirOrcamento
     */
    public caixaImprimirOrcamento(java.awt.Frame parent, Orcamento o) {
        super(parent, true);
        initComponents();
        this.orcamento = o;
        jFrame = (JFrame)parent;
        preencheFormulario();
    }

    public void preencheFormulario() {
    
        panelFormularioOrcamento1.setNumeroOrcamento(Integer.toString(orcamento.getCodigo()));
        panelFormularioOrcamento1.setNomeDentista(orcamento.getDentista().getNome());
        panelFormularioOrcamento1.setNomePaciente(orcamento.getPaciente().getNome());
        panelFormularioOrcamento1.setEnvio("");
        panelFormularioOrcamento1.setValorDesconto(orcamento.getValorDesconto().toString());
        panelFormularioOrcamento1.setValorTotal(orcamento.getValorFinal().toString());
        panelFormularioOrcamento1.setObservacao(orcamento.getObservacao());
        panelFormularioOrcamento1.setEnvio(orcamento.getData().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        
        for(int i=0; i<orcamento.getServicoOrcamento().size(); i++) {
            ServicoOrcamento servico = orcamento.getServicoOrcamento().get(i);
            Object[] dados = {servico.getCodigo(), servico.getPreco().getNome(), servico.getPreco().getValor(), servico.getValorDesconto(), servico.getQuantidade(), servico.getTotal()};
            panelFormularioOrcamento1.addLinhaTabela(dados);
        }
        
        //Adicionar sempre 12 linhas na tabela
        for(int i=0; i<12-orcamento.getServicoOrcamento().size(); i++) {
            Object[] dados = {null,null,null,null,null,null};
            panelFormularioOrcamento1.addLinhaTabela(dados);
        }        
        
    }      
    
    private void takePicture(JPanel panel) {
        BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        panel.print(img.getGraphics());
        try {
            ImageIO.write(img, "jpg", new File("panel.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelFormularioOrcamento1 = new com.vandremurakami.odousys.gui.panelFormularioOrcamento();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Impressão de Orçamento");
        setMaximumSize(new java.awt.Dimension(560, 730));
        setMinimumSize(new java.awt.Dimension(560, 730));
        setPreferredSize(new java.awt.Dimension(560, 730));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setText("Fechar");
        jButton2.setMaximumSize(new java.awt.Dimension(100, 35));
        jButton2.setMinimumSize(new java.awt.Dimension(100, 35));
        jButton2.setPreferredSize(new java.awt.Dimension(100, 35));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 660, -1, -1));

        jButton1.setText("Imprimir");
        jButton1.setMaximumSize(new java.awt.Dimension(100, 35));
        jButton1.setMinimumSize(new java.awt.Dimension(100, 35));
        jButton1.setPreferredSize(new java.awt.Dimension(100, 35));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 660, -1, -1));

        jScrollPane1.setMaximumSize(null);
        jScrollPane1.setMinimumSize(null);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(560, 650));
        jScrollPane1.setViewportView(panelFormularioOrcamento1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 561, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        PrinterJob printer = PrinterJob.getPrinterJob();
        PageFormat formatoPagina = printer.defaultPage();
        formatoPagina.setOrientation(PageFormat.PORTRAIT);
        Paper papel = formatoPagina.getPaper();
        papel.setImageableArea(MARGEM, MARGEM, papel.getWidth()-MARGEM, papel.getHeight()-MARGEM);
        formatoPagina.setPaper(papel);
       
        printer.setPrintable(panelFormularioOrcamento1, formatoPagina);
        
        //takePicture(painelFormularioOrcamento1);
        
        if( printer.printDialog() ) {
            try {
                
                printer.print();
                
            }
            catch(Exception ex) {
                
            }
        }

    }//GEN-LAST:event_jButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.vandremurakami.odousys.gui.panelFormularioOrcamento panelFormularioOrcamento1;
    // End of variables declaration//GEN-END:variables
}
