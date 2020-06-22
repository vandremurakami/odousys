/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.DentistaDAO;
import com.vandremurakami.odousys.gui.dialogCadastro;
import com.vandremurakami.odousys.gui.panelListarDentista;
import com.vandremurakami.odousys.gui.panelCadastroDentista;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.vandremurakami.odousys.modelo.Dentista;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author Vandre
 */
public class ControleListaDentista {
    
    private final panelListarDentista painelListaDentista;
    
    final private DentistaDAO dentistaDAO = new DentistaDAO();
    private List<Dentista> listaDentista;
    
    public ControleListaDentista(panelListarDentista panel) {
        this.painelListaDentista = panel;
        PreencheTabelaDentista();
    }
    
    public void PreencheTabelaDentista() {        
        listaDentista = dentistaDAO.BuscarDentistas();

        List<Dentista> listaFiltrada = null;
        //filtra a lista contendo palavras chaves do textFieldFiltro
        if(!painelListaDentista.getFiltro().isBlank()) {
            listaFiltrada = new ArrayList<>();
            String[] splitStr = painelListaDentista.getFiltro().split("\\s+", 10);
            for (String splitStr1 : splitStr) {
                String palavraChave = splitStr1.toLowerCase();
                listaFiltrada.addAll(listaDentista.parallelStream()
                        .filter(object -> (object.getNome().toLowerCase().contains(palavraChave)))
                        .collect(Collectors.toList()));
            }
            //filtra objetos duplicados que caem em mais de um filtro
            listaDentista = listaFiltrada.stream().distinct().collect(Collectors.toList());
        }
        
        DefaultTableModel tabela = (DefaultTableModel) painelListaDentista.getTabelaDentista().getModel();
        tabela.setRowCount(0);
        for(int i = 0; i < listaDentista.size(); i++) {
            Dentista o = listaDentista.get(i);
            Object[] dados = {o.getNome(), o.getEndereco(), o.getCidade(), o.getTelefone(), o.getCelular(), o.getEmail()};
            tabela.addRow(dados);
        }

    }
    
    public void CadastroAdicionarDentista() {
        
        dialogCadastro dialogDentista = new dialogCadastro(ControlePrincipal.framePrincipal, true);
        panelCadastroDentista cadastroDentista = new panelCadastroDentista(dialogDentista, null);
        
        dialogDentista.setWindowName("Cadastro de Dentista");
        dialogDentista.setPanel(cadastroDentista);
        dialogDentista.setVisible(true);

        PreencheTabelaDentista();
    }
    
    public void CadastroAbrirDentista() {
        int linhaSelecionada = painelListaDentista.getTabelaDentista().getSelectedRow();
        if ( linhaSelecionada >= 0 ) {
            Dentista dentista = listaDentista.get(linhaSelecionada);
            
            dialogCadastro dialogDentista = new dialogCadastro(ControlePrincipal.framePrincipal, true);
            panelCadastroDentista cadastroDentista = new panelCadastroDentista(dialogDentista, dentista);
                        
            
            dialogDentista.setWindowName("Cadastro de Dentista");
            dialogDentista.setPanel(cadastroDentista);
            dialogDentista.setVisible(true);
            
            PreencheTabelaDentista();
        }
        else {
            JOptionPane.showMessageDialog(null, "Selecione um ítem para abrir.");
        }
    }
    
    
    public void RemoveDentista() {
        int confimacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir.");
        if(confimacao == 0) {
            int linhaSelecionada = painelListaDentista.getTabelaDentista().getSelectedRow();
            if ( linhaSelecionada >= 0 ) {
                try {
                    dentistaDAO.Deleta(listaDentista.get(linhaSelecionada));
                    listaDentista.remove(linhaSelecionada);
                    PreencheTabelaDentista();
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

    public void FecharListaDentista() {
        ControlePrincipal.mostraHome();
    }
    
}
