/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.DentistaDAO;
import com.vandremurakami.odousys.gui.panelCadastroDentista;
import com.vandremurakami.odousys.modelo.Dentista;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Vandre
 */
public class ControleCadastroDentista {
    
    final private DentistaDAO dentistaDAO = new DentistaDAO();
    
    private final JDialog dialog;
    private final panelCadastroDentista cadastroDentista;
    private Dentista dentista;
    
    public ControleCadastroDentista(JDialog d, panelCadastroDentista c, Dentista dent) {
        this.dialog = d;
        this.cadastroDentista = c;
        this.dentista = dent;
        PreencheCadastroDentista();
    }
    
    private void PreencheCadastroDentista() {
        if(dentista != null) {
            cadastroDentista.setNome(dentista.getNome());
            cadastroDentista.setEndereco(dentista.getEndereco());
            cadastroDentista.setCidade(dentista.getCidade());
            cadastroDentista.setEstado(dentista.getEstado());
            cadastroDentista.setCEP(dentista.getCEP());
            cadastroDentista.setTelefone(dentista.getTelefone());
            cadastroDentista.setCelular(dentista.getCelular());
            cadastroDentista.setEmail(dentista.getEmail());
        }
    }
     
    private void AtualizaDadosDentista() {
        if(dentista == null)
            dentista = new Dentista();
        dentista.setNome(cadastroDentista.getNome());
        dentista.setEndereco(cadastroDentista.getEndereco());
        dentista.setCidade(cadastroDentista.getCidade());
        dentista.setEstado(cadastroDentista.getEstado());
        dentista.setCEP(cadastroDentista.getCEP());
        dentista.setTelefone(cadastroDentista.getTelefone());
        dentista.setCelular(cadastroDentista.getCelular());
        dentista.setEmail(cadastroDentista.getEmail());
    }
    
    public void AdicionaAtualizaDentista() {
        if(verificaCampos()) {
            try {
                AtualizaDadosDentista();
                dentistaDAO.SalvaAtualiza(dentista);
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
        if( cadastroDentista.getNome().isBlank()) {
            JOptionPane.showMessageDialog(null, "Nome vazio.");
            check = false;
        }
        return check;            
    }
    
}
