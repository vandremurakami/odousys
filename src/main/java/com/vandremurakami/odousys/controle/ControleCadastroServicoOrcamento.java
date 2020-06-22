/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.PrecoDAO;
import com.vandremurakami.odousys.gui.panelCadastroServicoOrcamento;
import com.vandremurakami.odousys.modelo.Preco;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import com.vandremurakami.odousys.modelo.ServicoOrcamento;
import com.vandremurakami.odousys.modelo.TabelaPreco;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Vandre
 */
public class ControleCadastroServicoOrcamento {
    
    private final PrecoDAO precoDAO = new PrecoDAO();
    private List<Preco> listaPreco;
    
    private final JDialog dialog;
    private final panelCadastroServicoOrcamento cadastroServicoOrcamento;
    private ServicoOrcamento servicoOrcamento;
    private final TabelaPreco tabelaPreco;
    
    
    public ControleCadastroServicoOrcamento(JDialog d, panelCadastroServicoOrcamento c, TabelaPreco tp, ServicoOrcamento s) {
        this.dialog = d;
        this.cadastroServicoOrcamento = c;
        this.servicoOrcamento = s;
        this.tabelaPreco = tp;
        inicializaPanelCadastroServicoOrcamento();
    }

    private void inicializaPanelCadastroServicoOrcamento() {
        PreencheComboBoxServico(cadastroServicoOrcamento.getComboBoxServico());
        PreencheCadastroServicoOrcamento();
    }
    
    private void PreencheCadastroServicoOrcamento() {
        if(servicoOrcamento != null) {
            cadastroServicoOrcamento.setNomeServico(servicoOrcamento.getPreco().getNome());
            cadastroServicoOrcamento.setQuantidade(servicoOrcamento.getQuantidade());
            cadastroServicoOrcamento.setPorcentagemDescontoUnitario(servicoOrcamento.getPorcentagemDesconto().toString());
            cadastroServicoOrcamento.setDescontoUnitario(servicoOrcamento.getValorDesconto().toString());
            cadastroServicoOrcamento.setValorUnitario(servicoOrcamento.getValorUnitatio().toString());
            cadastroServicoOrcamento.setObservacao(servicoOrcamento.getObservacao());
        }       
    }
    
    private void AtualizaDadosServicoOrcamento() {
        if(servicoOrcamento == null)
            servicoOrcamento = new ServicoOrcamento();
        servicoOrcamento.setPreco(listaPreco.get(cadastroServicoOrcamento.getPosicaoServico()));
        servicoOrcamento.setQuantidade(cadastroServicoOrcamento.getQuantidade());
        if(!cadastroServicoOrcamento.getPorcentagemDescontoUnitario().trim().equals(""))
            servicoOrcamento.setPorcentagemDesconto(new BigDecimal(cadastroServicoOrcamento.getPorcentagemDescontoUnitario()));
        else
            servicoOrcamento.setPorcentagemDesconto(BigDecimal.ZERO);
        if(!cadastroServicoOrcamento.getDescontoUnitario().trim().equals(""))
            servicoOrcamento.setValorDesconto(new BigDecimal(cadastroServicoOrcamento.getDescontoUnitario()));
        else
            servicoOrcamento.setValorDesconto(BigDecimal.ZERO);
        servicoOrcamento.setObservacao(cadastroServicoOrcamento.getObservacao());
    }
    
    public void AdicionaAtualizaServicoOrcamento() {
        if (verificaCampos()) {
            AtualizaDadosServicoOrcamento();
            dialog.dispose();
        }
    }

    public void Fechar() {
        dialog.dispose();
    }

    
    public void PreencheComboBoxServico(JComboBox combobox) {
        listaPreco = precoDAO.BuscarPrecos(tabelaPreco);
        for(int i = 0; i < listaPreco.size(); i++) {
            combobox.addItem(listaPreco.get(i).getNome());
        }  
    }
    
    public void setValorDesconto() {
        String texto = cadastroServicoOrcamento.getDescontoUnitario().trim();
        if(!texto.isBlank()) {
            BigDecimal preco = listaPreco.get(cadastroServicoOrcamento.getPosicaoServico()).getValor();
            BigDecimal valorDesconto = new BigDecimal(cadastroServicoOrcamento.getDescontoUnitario());
            BigDecimal porcetagemDesconto = valorDesconto.divide(preco, 4, RoundingMode.DOWN).multiply(BigDecimal.valueOf(100.0));
            cadastroServicoOrcamento.setPorcentagemDescontoUnitario(porcetagemDesconto.setScale(2).toString());
            cadastroServicoOrcamento.setValorUnitario(preco.subtract(valorDesconto).setScale(2).toString());
        }
        else {
            cadastroServicoOrcamento.setPorcentagemDescontoUnitario("0");
            cadastroServicoOrcamento.setValorUnitario(listaPreco.get(cadastroServicoOrcamento.getPosicaoServico()).getValor().toString());
        }
    }
    
    public void setPorcentagemDesconto() {
        String texto = cadastroServicoOrcamento.getPorcentagemDescontoUnitario().trim();
        if(!texto.isBlank()) {
            BigDecimal preco = listaPreco.get(cadastroServicoOrcamento.getPosicaoServico()).getValor();
            BigDecimal porcetagemDesconto = new BigDecimal(cadastroServicoOrcamento.getPorcentagemDescontoUnitario());
            BigDecimal valorDesconto = porcetagemDesconto.divide(BigDecimal.valueOf(100.0)).multiply(preco);
            cadastroServicoOrcamento.setDescontoUnitario(valorDesconto.setScale(2).toString());
            cadastroServicoOrcamento.setValorUnitario(preco.subtract(valorDesconto).setScale(2).toString());
        }
        else {
            cadastroServicoOrcamento.setDescontoUnitario("0");
            cadastroServicoOrcamento.setValorUnitario(listaPreco.get(cadastroServicoOrcamento.getPosicaoServico()).getValor().toString());
        }
    }
    
    public void setValorUnitario() {
        if (cadastroServicoOrcamento.getPosicaoServico() >= 0) {
            cadastroServicoOrcamento.setPorcentagemDescontoUnitario("0");
            cadastroServicoOrcamento.setDescontoUnitario("0");
            cadastroServicoOrcamento.setValorUnitario(listaPreco.get(cadastroServicoOrcamento.getPosicaoServico()).getValor().toString());
        }
    }

    public ServicoOrcamento getServicoOrcamento() {
        return servicoOrcamento;
    }
    
    private boolean verificaCampos() {
        boolean check = true;
        
        if (cadastroServicoOrcamento.getComboBoxServico().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Tipo de Serviço não selecionado.");
            check = false;
        }

        return check;
    }
    
    
}
