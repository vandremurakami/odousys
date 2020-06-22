/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.TabelaPrecoDAO;
import com.vandremurakami.odousys.gui.panelCadastroTabelaPreco;
import com.vandremurakami.odousys.modelo.TabelaPreco;
import java.math.BigDecimal;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Vandre
 */
public class ControleCadastroTabelaPreco {
    
    final private TabelaPrecoDAO tabelaPrecoDAO = new TabelaPrecoDAO();
    
    private final JDialog dialog;
    private final panelCadastroTabelaPreco cadastroTabelaPreco;
    private TabelaPreco tabelaPreco;
    
    public ControleCadastroTabelaPreco(JDialog d, panelCadastroTabelaPreco pctp, TabelaPreco tb) {
        this.dialog = d;
        this.cadastroTabelaPreco = pctp;
        this.tabelaPreco = tb;
        inicializaPanelCadastroTabelaPreco();
    }
    
    private void inicializaPanelCadastroTabelaPreco() {
        PreencheCadastroPreco();
    }
    
    private void PreencheCadastroPreco() {
        if(tabelaPreco != null) {
            cadastroTabelaPreco.setNome(tabelaPreco.getNome());
            cadastroTabelaPreco.setAtivo(tabelaPreco.getAtivo());
        }
    }
     
    private void AtualizaDadosTabelaPreco() {
        if(tabelaPreco == null)
            tabelaPreco = new TabelaPreco();
        tabelaPreco.setNome(cadastroTabelaPreco.getNome());
        tabelaPreco.setAtivo(cadastroTabelaPreco.getAtivo());
    }
    
    public void AdicionaAtualizaTabelaPreco() {
        if(verificaCampos()) {
            try {
                AtualizaDadosTabelaPreco();
                tabelaPrecoDAO.SalvaAtualiza(tabelaPreco);
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
        
        if (cadastroTabelaPreco.getNome().isBlank()) {
            JOptionPane.showMessageDialog(null, "Nome não preenchido.");
            check = false;
        }

        return check;
    }
    
    
}
