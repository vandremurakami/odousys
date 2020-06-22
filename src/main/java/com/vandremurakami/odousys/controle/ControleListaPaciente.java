/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.PacienteDAO;
import com.vandremurakami.odousys.gui.dialogCadastro;
import com.vandremurakami.odousys.gui.panelListarPaciente;
import com.vandremurakami.odousys.gui.panelCadastroPaciente;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.vandremurakami.odousys.modelo.Paciente;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author Vandre
 */
public class ControleListaPaciente {
    
    private final panelListarPaciente painelListaPaciente;
    
    final private PacienteDAO pacienteDAO = new PacienteDAO();
    private List<Paciente> listaPaciente;
    
    public ControleListaPaciente(panelListarPaciente panel) {
        this.painelListaPaciente = panel;
        PreencheTabelaPaciente();
    }
    
    public void PreencheTabelaPaciente() {        
        listaPaciente = pacienteDAO.BuscarPacientes();

        List<Paciente> listaFiltrada = null;
        //filtra a lista contendo palavras chaves do textFieldFiltro
        if(!painelListaPaciente.getFiltro().isBlank()) {
            listaFiltrada = new ArrayList<>();
            String[] splitStr = painelListaPaciente.getFiltro().split("\\s+", 10);
            for (String splitStr1 : splitStr) {
                String palavraChave = splitStr1.toLowerCase();
                listaFiltrada.addAll(listaPaciente.parallelStream()
                        .filter(object -> (object.getNome().toLowerCase().contains(palavraChave)))
                        .collect(Collectors.toList()));
            }
            //filtra objetos duplicados que caem em mais de um filtro
            listaPaciente = listaFiltrada.stream().distinct().collect(Collectors.toList());
        }
        
        DefaultTableModel tabela = (DefaultTableModel) painelListaPaciente.getTabelaPaciente().getModel();
        tabela.setRowCount(0);
        for(int i = 0; i < listaPaciente.size(); i++) {
            Paciente o = listaPaciente.get(i);
            Object[] dados = {o.getNome(), o.getEndereco(), o.getCidade(), o.getTelefone(), o.getCelular(), o.getEmail()};
            tabela.addRow(dados);
        }

    }
    
    public void CadastroAdicionarPaciente() {
        
        dialogCadastro dialogPaciente = new dialogCadastro(ControlePrincipal.framePrincipal, true);
        panelCadastroPaciente cadastroPaciente = new panelCadastroPaciente(dialogPaciente, null);
        
        dialogPaciente.setWindowName("Cadastro de Paciente");
        dialogPaciente.setPanel(cadastroPaciente);
        dialogPaciente.setVisible(true);

        PreencheTabelaPaciente();
    }
    
    public void CadastroAbrirPaciente() {
        int linhaSelecionada = painelListaPaciente.getTabelaPaciente().getSelectedRow();
        if ( linhaSelecionada >= 0 ) {
            Paciente paciente = listaPaciente.get(linhaSelecionada);
            
            dialogCadastro dialogPaciente = new dialogCadastro(ControlePrincipal.framePrincipal, true);
            panelCadastroPaciente cadastroPaciente = new panelCadastroPaciente(dialogPaciente, paciente);
                        
            
            dialogPaciente.setWindowName("Cadastro de Paciente");
            dialogPaciente.setPanel(cadastroPaciente);
            dialogPaciente.setVisible(true);
            
            PreencheTabelaPaciente();
        }
        else {
            JOptionPane.showMessageDialog(null, "Selecione um ítem para abrir.");
        }
    }
    
    
    public void RemovePaciente() {
        int confimacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir.");
        if(confimacao == 0) {
            int linhaSelecionada = painelListaPaciente.getTabelaPaciente().getSelectedRow();
            if ( linhaSelecionada >= 0 ) {
                try {
                    pacienteDAO.Deleta(listaPaciente.get(linhaSelecionada));
                    listaPaciente.remove(linhaSelecionada);
                    PreencheTabelaPaciente();
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

    public void FecharListaPaciente() {
        ControlePrincipal.mostraHome();
    }
    
}
