/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.PacienteDAO;
import com.vandremurakami.odousys.gui.panelCadastroPaciente;
import com.vandremurakami.odousys.modelo.Paciente;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Vandre
 */
public class ControleCadastroPaciente {
    
    final private PacienteDAO pacienteDAO = new PacienteDAO();
    
    private final JDialog dialog;
    private final panelCadastroPaciente cadastroPaciente;
    private Paciente paciente;
    
    public ControleCadastroPaciente(JDialog d, panelCadastroPaciente c, Paciente pac) {
        this.dialog = d;
        this.cadastroPaciente = c;
        this.paciente = pac;
        PreencheCadastroPaciente();
    }
    
    private void PreencheCadastroPaciente() {
        if(paciente != null) {
            cadastroPaciente.setNome(paciente.getNome());
            cadastroPaciente.setEndereco(paciente.getEndereco());
            cadastroPaciente.setCidade(paciente.getCidade());
            cadastroPaciente.setEstado(paciente.getEstado());
            cadastroPaciente.setCEP(paciente.getCEP());
            cadastroPaciente.setTelefone(paciente.getTelefone());
            cadastroPaciente.setCelular(paciente.getCelular());
            cadastroPaciente.setEmail(paciente.getEmail());
        }
    }
     
    private void AtualizaDadosPaciente() {
        if(paciente == null)
            paciente = new Paciente();
        paciente.setNome(cadastroPaciente.getNome());
        paciente.setEndereco(cadastroPaciente.getEndereco());
        paciente.setCidade(cadastroPaciente.getCidade());
        paciente.setEstado(cadastroPaciente.getEstado());
        paciente.setCEP(cadastroPaciente.getCEP());
        paciente.setTelefone(cadastroPaciente.getTelefone());
        paciente.setCelular(cadastroPaciente.getCelular());
        paciente.setEmail(cadastroPaciente.getEmail());
    }
    
    public void AdicionaAtualizaPaciente() {
        if(verificaCampos()) {
            try {
                AtualizaDadosPaciente();
                pacienteDAO.SalvaAtualiza(paciente);
                dialog.dispose();
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro. Verifique se o nome já existe.");
            }
        }
    }
    
    public void Fechar() {
        dialog.dispose();
    }
    
    private boolean verificaCampos() {
        boolean check = true;
        if( cadastroPaciente.getNome().isBlank()) {
            JOptionPane.showMessageDialog(null, "Nome vazio.");
            check = false;
        }
        return check;            
    }
    
}
