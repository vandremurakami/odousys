/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.PrecoDAO;
import com.vandremurakami.odousys.gui.panelCadastroPreco;
import com.vandremurakami.odousys.modelo.Preco;
import com.vandremurakami.odousys.modelo.TabelaPreco;
import java.math.BigDecimal;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Vandre
 */
public class ControleCadastroPreco {
    
    final private PrecoDAO precoDAO = new PrecoDAO();
    
    private final JDialog dialog;
    private final panelCadastroPreco cadastroPreco;
    private Preco preco;
    private final TabelaPreco tabelaPreco;
    
    public ControleCadastroPreco(JDialog d, panelCadastroPreco c, TabelaPreco tp, Preco o) {
        this.dialog = d;
        this.cadastroPreco = c;
        this.tabelaPreco = tp;
        this.preco = o;
        inicializaPanelCadastroPreco();
    }
    
    private void inicializaPanelCadastroPreco() {
        PreencheCadastroPreco();
    }
    
    private void PreencheCadastroPreco() {
        if(preco != null) {
            cadastroPreco.setNome(preco.getNome());
            cadastroPreco.setValor(preco.getValor().toString());
        }
    }
     
    private void AtualizaDadosPreco() {
        preco = new Preco();
        preco.setNome(cadastroPreco.getNome());
        preco.setTabelaPreco(tabelaPreco);
        preco.setValor(new BigDecimal(cadastroPreco.getValor()));
    }
    
    public void AdicionaAtualizaPreco() {
        if(verificaCampos()) {
            try {
                AtualizaDadosPreco();
                precoDAO.Salva(preco);
                dialog.dispose();
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro.");
            }
        }
    }
    
    public void Fechar() {
        dialog.dispose();
    }
    
    private boolean verificaCampos() {
        boolean check = true;
        
        if (cadastroPreco.getNome().isBlank()) {
            JOptionPane.showMessageDialog(null, "Nome não preenchido.");
            check = false;
        }
        else if (cadastroPreco.getValor().isBlank()) {
            JOptionPane.showMessageDialog(null, "Valor vazio.");
            check = false;
        }

        return check;
    }
    
    
}
