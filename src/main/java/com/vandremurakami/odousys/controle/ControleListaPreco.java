/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.PrecoDAO;
import com.vandremurakami.odousys.gui.dialogCadastro;
import com.vandremurakami.odousys.gui.panelListarPreco;
import com.vandremurakami.odousys.gui.panelCadastroPreco;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.vandremurakami.odousys.modelo.Preco;
import com.vandremurakami.odousys.modelo.TabelaPreco;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Vandre
 */
public class ControleListaPreco {
    
    private final panelListarPreco painelListaPreco;
    private final JDialog dialog;
    
    final private PrecoDAO precoDAO = new PrecoDAO();
    
    private final TabelaPreco tabelaPreco;
    List<Preco> listaPreco;
    
    public ControleListaPreco(JDialog d, panelListarPreco panel, TabelaPreco tp) {
        this.dialog = d;
        this.painelListaPreco = panel;
        this.tabelaPreco = tp;
        PreencheTabelaPreco();
    }
    
    //A tabela de Preço precisa pegar os preços mais atuais
    public void PreencheTabelaPreco() {
        listaPreco = precoDAO.BuscarPrecos(tabelaPreco);
        
        //filtra a lista contendo palavras chaves do textFieldFiltro
        if(!painelListaPreco.getFiltro().isBlank()) {
            List<Preco> listaFiltrada = new ArrayList<>();
            String[] splitStr = painelListaPreco.getFiltro().split("\\s+", 10);
            for (String splitStr1 : splitStr) {
                String palavraChave = splitStr1.toLowerCase();
                listaFiltrada.addAll(listaPreco.parallelStream()
                        .filter(object -> (object.getNome().toLowerCase().contains(palavraChave)))
                        .collect(Collectors.toList()));
            }
            //filtra objetos duplicados que caem em mais de um filtro
            listaPreco = listaFiltrada.stream().distinct().collect(Collectors.toList());
        }

        
        DefaultTableModel tabela = (DefaultTableModel) painelListaPreco.getTabelaPreco().getModel();
        tabela.setRowCount(0);
        for(int i = 0; i < listaPreco.size(); i++) {
            Preco o = listaPreco.get(i);
            Object[] dados = {o.getNome(), o.getValor()};
            tabela.addRow(dados);
        }        

    }
   
    
    public void CadastroAdicionarPreco() {
        
        dialogCadastro dialogPreco = new dialogCadastro(ControlePrincipal.framePrincipal, true);
        panelCadastroPreco cadastroPreco = new panelCadastroPreco(dialogPreco, tabelaPreco, null);
        
        dialogPreco.setWindowName("Cadastro de Preco");
        dialogPreco.setPanel(cadastroPreco);
        dialogPreco.setVisible(true);

        PreencheTabelaPreco();
    }
    
    public void CadastroAbrirPreco() {
        int linhaSelecionada = painelListaPreco.getTabelaPreco().getSelectedRow();
        if ( linhaSelecionada >= 0 ) {
            Preco preco = listaPreco.get(linhaSelecionada);
            
            dialogCadastro dialogPreco = new dialogCadastro(ControlePrincipal.framePrincipal, true);
            panelCadastroPreco cadastroPreco = new panelCadastroPreco(dialogPreco, tabelaPreco, preco);
                        
            
            dialogPreco.setWindowName("Cadastro de Preco");
            dialogPreco.setPanel(cadastroPreco);
            dialogPreco.setVisible(true);
            
            PreencheTabelaPreco();
        }
        else {
            JOptionPane.showMessageDialog(null, "Selecione um ítem para abrir.");
        }
    }
    
    
    public void RemovePreco() {
        int confimacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir.");
        if(confimacao == 0) {
            int linhaSelecionada = painelListaPreco.getTabelaPreco().getSelectedRow();
            if ( linhaSelecionada >= 0 ) {
                try {
                    Preco preco = listaPreco.get(linhaSelecionada);
                    precoDAO.Deleta(listaPreco.get(linhaSelecionada));
                    listaPreco.remove(linhaSelecionada);
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

    public void FecharListaPreco() {
        dialog.dispose();
    }
    
    
}
