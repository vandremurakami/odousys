/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.DentistaDAO;
import com.vandremurakami.odousys.dao.OrcamentoDAO;
import com.vandremurakami.odousys.gui.dialogCadastro;
import com.vandremurakami.odousys.gui.panelListarOrcamento;
import com.vandremurakami.odousys.gui.panelCadastroOrcamento;
import com.vandremurakami.odousys.modelo.Dentista;
import java.awt.Color;
import java.awt.Component;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.vandremurakami.odousys.modelo.Orcamento;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author Vandre
 */
public class ControleListaOrcamento {
    
    private final panelListarOrcamento painelListaOrcamento;
    
    private final OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
    private final DentistaDAO dentistaDAO = new DentistaDAO();
    private List<Orcamento> listaOrcamento;
    private List<Dentista> listaDentista;
    private List<Orcamento> listaFiltradaOrcamento;
    
    private final int NUM_COLUNA_STATUS = 5;

    public ControleListaOrcamento(panelListarOrcamento panel) {
        this.painelListaOrcamento = panel;
        inicializaPanelListaOrcamento();
    }
    
    private void inicializaPanelListaOrcamento() {
        PreencheComboboxDentista();
        carregaListaBD();
        PreencheTabelaOrcamento();
        configuraCoresStatusTabelaOrcamento();
    }
    
    private void carregaListaBD() {
        listaOrcamento = orcamentoDAO.BuscarOrcamentos();
    }
    
    public void PreencheTabelaOrcamento() {        
        List<Orcamento> listaFiltrada;
        
        int linhaSelecionada = painelListaOrcamento.getComboBoxDentista().getSelectedIndex();
        if (linhaSelecionada == 0) {            
            listaFiltradaOrcamento = listaOrcamento;
            
        } else if (linhaSelecionada > 0) {
        
            Dentista dentista = listaDentista.get(linhaSelecionada-1);
            
            listaFiltradaOrcamento = new ArrayList<>();
                listaFiltradaOrcamento.addAll(listaOrcamento.parallelStream()
                        .filter(object -> (object.getDentista().getCodigo() == dentista.getCodigo()))
                        .collect(Collectors.toList()));
            //filtra objetos duplicados que caem em mais de um filtro
            listaFiltradaOrcamento = listaFiltradaOrcamento.stream().distinct().collect(Collectors.toList());

        }
        
        //filtra a lista contendo palavras chaves do textFieldFiltro
        if(!painelListaOrcamento.getFiltro().isBlank()) {
            listaFiltrada = new ArrayList<>();
            String[] splitStr = painelListaOrcamento.getFiltro().split("\\s+", 10);
            for (String splitStr1 : splitStr) {
                String palavraChave = splitStr1.toLowerCase();
                listaFiltrada.addAll(listaFiltradaOrcamento.parallelStream()
                        .filter(object -> (object.getPaciente().getNome().toLowerCase().contains(palavraChave)))
                        .collect(Collectors.toList()));
            }
            //filtra objetos duplicados que caem em mais de um filtro
            listaFiltradaOrcamento = listaFiltrada.stream().distinct().collect(Collectors.toList());
        }
        
        DefaultTableModel tabela = (DefaultTableModel) painelListaOrcamento.getTabelaOrcamento().getModel();
        tabela.setRowCount(0);
        for(int i = 0; i < listaFiltradaOrcamento.size(); i++) {
            
            Orcamento o = listaFiltradaOrcamento.get(i);
            
            Object[] dados = {o.getCodigo(), o.getData().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)), 
                o.getDentista().getNome(), o.getPaciente(), o.getValorFinal(), o.getStatus().getNome()};
            tabela.addRow(dados);


        }

    }
    
    public void CadastroAdicionarOrcamento() {
        
        dialogCadastro dialogOrcamento = new dialogCadastro(ControlePrincipal.framePrincipal, true);
        panelCadastroOrcamento cadastroOrcamento = new panelCadastroOrcamento(dialogOrcamento, null);
        
        int posicao = painelListaOrcamento.getComboBoxDentista().getSelectedIndex()-1;
        if(posicao >= 0)
             cadastroOrcamento.setNomeDentista(listaDentista.get(posicao).getNome());
        
        dialogOrcamento.setWindowName("Cadastro de Orcamento");
        dialogOrcamento.setPanel(cadastroOrcamento);
        dialogOrcamento.setVisible(true);

        carregaListaBD();
        PreencheTabelaOrcamento();
    }
    
    public void CadastroAbrirOrcamento() {
        int linhaSelecionada = painelListaOrcamento.getTabelaOrcamento().getSelectedRow();
        if ( linhaSelecionada >= 0 ) {
            Orcamento orcamento = listaFiltradaOrcamento.get(linhaSelecionada);
            
            dialogCadastro dialogOrcamento = new dialogCadastro(ControlePrincipal.framePrincipal, true);
            panelCadastroOrcamento cadastroOrcamento = new panelCadastroOrcamento(dialogOrcamento, orcamento);
                        
            
            dialogOrcamento.setWindowName("Cadastro de Orcamento");
            dialogOrcamento.setPanel(cadastroOrcamento);
            dialogOrcamento.setVisible(true);
            
            carregaListaBD();
            PreencheTabelaOrcamento();
        }
        else {
            JOptionPane.showMessageDialog(null, "Selecione um ítem para abrir.");
        }
    }

    private void PreencheComboboxDentista() {
        listaDentista = dentistaDAO.BuscarDentistas();
        for(int i = 0; i < listaDentista.size(); i++) {
            painelListaOrcamento.getComboBoxDentista().addItem(listaDentista.get(i).getNome());
        } 
    }

    public void FecharListaOrcamento() {
        ControlePrincipal.mostraHome();
    }
    
    private void configuraCoresStatusTabelaOrcamento() {
        painelListaOrcamento.getTabelaOrcamento().getColumnModel().getColumn(NUM_COLUNA_STATUS).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = table.getValueAt(row, NUM_COLUNA_STATUS).toString();
                switch (status) {
                    case "Aberto":
                        c.setBackground(Color.BLUE);
                        break;
                    case "Finalizado":
                        c.setBackground(Color.GREEN);
                        break;
                    case "Cancelado":
                        c.setBackground(Color.RED);
                        break;
                    default:
                        break;
                }
                return c;
            }
        });
    }

}
