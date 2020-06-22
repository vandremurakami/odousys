/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.TabelaPrecoDAO;
import com.vandremurakami.odousys.dao.PrecoDAO;
import com.vandremurakami.odousys.gui.dialogCadastro;
import com.vandremurakami.odousys.gui.panelAbas;
import com.vandremurakami.odousys.gui.panelListarTabelaPreco;
import com.vandremurakami.odousys.gui.panelCadastroTabelaPreco;
import com.vandremurakami.odousys.gui.panelListarPreco;
import com.vandremurakami.odousys.modelo.Preco;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.vandremurakami.odousys.modelo.TabelaPreco;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Vandre
 */
public class ControleListaTabelaPreco {
    
    private final panelListarTabelaPreco painelListaTabelaPreco;
    private panelAbas abasTabelaPreco;
    private dialogCadastro dialogTabelaPreco;
    
    final private TabelaPrecoDAO tabelaPrecoDAO = new TabelaPrecoDAO();
    final private PrecoDAO precoDAO = new PrecoDAO();
    
    private TabelaPreco tabelaPreco;
    
    private List<TabelaPreco> listaTabelaPreco;
    
    private final int NUM_COLUNA_ATIVO = 1;
    
    public ControleListaTabelaPreco(panelListarTabelaPreco panel) {
        this.painelListaTabelaPreco = panel;
        PreencheTabelaPreco();
        configuraCoresAtivoTabelaPreco();
    }
    
    //A tabela de Preço precisa pegar os preços mais atuais
    public void PreencheTabelaPreco() {
        listaTabelaPreco = tabelaPrecoDAO.BuscarTabelaPreco();
        
        List<TabelaPreco> listaFiltrada;
        //filtra a lista contendo palavras chaves do textFieldFiltro
        if(!painelListaTabelaPreco.getFiltro().isBlank()) {
            listaFiltrada = new ArrayList<>();
            String[] splitStr = painelListaTabelaPreco.getFiltro().split("\\s+", 10);
            for (String splitStr1 : splitStr) {
                String palavraChave = splitStr1.toLowerCase();
                listaFiltrada.addAll(listaTabelaPreco.parallelStream()
                        .filter(object -> (object.getNome().toLowerCase().contains(palavraChave)))
                        .collect(Collectors.toList()));
            }
            //filtra objetos duplicados que caem em mais de um filtro
            listaTabelaPreco = listaFiltrada.stream().distinct().collect(Collectors.toList());
        }
        
        DefaultTableModel tabela = (DefaultTableModel) painelListaTabelaPreco.getTabelaPreco().getModel();
        tabela.setRowCount(0);
        for(int i = 0; i < listaTabelaPreco.size(); i++) {
            TabelaPreco o = listaTabelaPreco.get(i);
            Object[] dados = {o.getNome(), o.getAtivo()};
            tabela.addRow(dados);
        }        

    }
   
    
    public void CadastroAdicionarPreco() {

        dialogTabelaPreco = new dialogCadastro(ControlePrincipal.framePrincipal, true);
        panelCadastroTabelaPreco cadastroTabelaPreco = new panelCadastroTabelaPreco(dialogTabelaPreco, null);

        abasTabelaPreco = new panelAbas();
        abasTabelaPreco.adicionarAba(cadastroTabelaPreco, "Tabela de Preço");

        dialogTabelaPreco.setWindowName("Cadastro de Tabela de Preço");
        dialogTabelaPreco.setPanel(abasTabelaPreco);
        dialogTabelaPreco.setVisible(true);

        PreencheTabelaPreco();
    }
    
    public void CadastroAbrirPreco() {
        int linhaSelecionada = painelListaTabelaPreco.getTabelaPreco().getSelectedRow();
        if ( linhaSelecionada >= 0 ) {
            tabelaPreco = listaTabelaPreco.get(linhaSelecionada);
            
            dialogTabelaPreco = new dialogCadastro(ControlePrincipal.framePrincipal, true);
            panelCadastroTabelaPreco cadastroTabelaPreco = new panelCadastroTabelaPreco(dialogTabelaPreco, tabelaPreco);

            abasTabelaPreco = new panelAbas();
            abasTabelaPreco.adicionarAba(cadastroTabelaPreco, "Tabela de Preço");
            
            ExibirAbasTabelaPrecos();
            
            dialogTabelaPreco.setWindowName("Cadastro de Tabela de Preço");
            dialogTabelaPreco.setPanel(abasTabelaPreco);
            dialogTabelaPreco.setVisible(true);
            
            PreencheTabelaPreco();
        }
        else {
            JOptionPane.showMessageDialog(null, "Selecione um ítem para abrir.");
        }
    }
    
    private void ExibirAbasTabelaPrecos() {
        if (abasTabelaPreco.numeroAbas() == 1) {
            abasTabelaPreco.adicionarAba(new panelListarPreco(dialogTabelaPreco, tabelaPreco), "Preços");
        }
    }
    
    public void RemoveTabelaPreco() {
        int confimacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir.");
        if(confimacao == 0) {
            int linhaSelecionada = painelListaTabelaPreco.getTabelaPreco().getSelectedRow();
            if ( linhaSelecionada >= 0 ) {
                try {
                    tabelaPreco = listaTabelaPreco.get(linhaSelecionada);

                    List<Preco> listaPreco= precoDAO.BuscarPrecos(listaTabelaPreco.get(linhaSelecionada));
                    for(int i=0; i<listaPreco.size(); i++) {
                        precoDAO.Deleta(listaPreco.get(i));
                    }

                    tabelaPrecoDAO.Deleta(listaTabelaPreco.get(linhaSelecionada));
                    listaTabelaPreco.remove(linhaSelecionada);
                    PreencheTabelaPreco();
                }
                catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possível remover o cadastro.");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Selecione um ítem para remover.");
            }
        }
    }

    public void FecharListaTabelaPreco() {
        ControlePrincipal.mostraHome();
    }
    
    private void configuraCoresAtivoTabelaPreco() {
        painelListaTabelaPreco.getTabelaPreco().getColumnModel().getColumn(NUM_COLUNA_ATIVO).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                boolean check = (boolean) table.getValueAt(row, NUM_COLUNA_ATIVO);
                if (check) {
                    c.setBackground(Color.GREEN);
                    c.setForeground(Color.GREEN);
                } else {
                    c.setBackground(Color.BLUE);
                    c.setForeground(Color.BLUE);
                }

                return c;
            }
        });
    }

    public void ClonarTabelaPreco() {
        int linhaSelecionada = painelListaTabelaPreco.getTabelaPreco().getSelectedRow();
        if ( linhaSelecionada >= 0 ) {
            try {
                TabelaPreco tabela = new TabelaPreco();
                tabela.setNome(listaTabelaPreco.get(linhaSelecionada).getNome() + " (clone)");
                tabelaPrecoDAO.SalvaAtualiza(tabela);
                List<Preco> listaPreco= precoDAO.BuscarPrecos(listaTabelaPreco.get(linhaSelecionada));
                for(int i=0; i<listaPreco.size(); i++) {
                    Preco preco = new Preco();
                    preco.setTabelaPreco(tabela);
                    preco.setNome(listaPreco.get(i).getNome());
                    preco.setValor(listaPreco.get(i).getValor());
                    precoDAO.Salva(preco);
                }
                PreencheTabelaPreco();
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível clonar a Tabela de Preco.");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Selecione um ítem para remover.");
        }
    }
    
    
}
