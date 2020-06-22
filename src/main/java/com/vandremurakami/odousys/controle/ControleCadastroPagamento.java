/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.PacienteDAO;
import com.vandremurakami.odousys.dao.PagamentoDAO;
import com.vandremurakami.odousys.dao.StatusDAO;
import com.vandremurakami.odousys.dao.TipoPagamentoDAO;
import com.vandremurakami.odousys.gui.caixaSenha;
import com.vandremurakami.odousys.gui.panelCadastroPagamento;
import java.math.BigDecimal;
import java.util.List;
import com.vandremurakami.odousys.modelo.Paciente;
import com.vandremurakami.odousys.modelo.Pagamento;
import com.vandremurakami.odousys.modelo.Status;
import com.vandremurakami.odousys.modelo.TipoPagamento;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Vandre
 */
public class ControleCadastroPagamento {
    
    private final StatusDAO statusDAO = new StatusDAO();
    private final TipoPagamentoDAO tipoPagamentoDAO = new TipoPagamentoDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final PagamentoDAO pagamentoDAO = new PagamentoDAO();
    private List<Status> listaStatus;
    private List<TipoPagamento> listaTipoPagamento;
    private List<Paciente> listaPaciente;

    private final JDialog dialog;
    private final panelCadastroPagamento cadastroPagamento;
    private Pagamento pagamento;
    
    public ControleCadastroPagamento(JDialog d, panelCadastroPagamento c, Pagamento p) {
        this.dialog = d;
        this.cadastroPagamento = c;
        this.pagamento = p;
        inicializaPanelCadastroPagamento();
    }
    
    private void inicializaPanelCadastroPagamento() {
        PreencheComboBoxStatus(cadastroPagamento.getComboBoxStatus());
        PreencheComboBoxTipoPagamento(cadastroPagamento.getComboBoxTipoPagamento());
        PreencheComboBoxPaciente(cadastroPagamento.getComboBoxPaciente());
        PreencheCadastroPagamento();
    }
    
    private void PreencheCadastroPagamento() {
        if(pagamento != null) {
            cadastroPagamento.setNomeStatus(pagamento.getStatus().getNome());
            cadastroPagamento.setDataPagamento(pagamento.getDataPagamento());
            cadastroPagamento.setNomePaciente(pagamento.getPaciente().getNome());
            cadastroPagamento.setValor(pagamento.getValor().toString());
            cadastroPagamento.setNomeTipoPagamento(pagamento.getTipoPagamento().getNome());
            cadastroPagamento.setObservacao(pagamento.getObservacao());
            if(pagamento.getStatus().getCodigo() == StatusDAO.STATUS_FINALIZADO)
                cadastroPagamento.setNaoEditavel();
        }
        else {
            cadastroPagamento.setNomeStatus(statusDAO.BuscarStatusAberto().getNome());
        }
    }
    
    private void AtualizaDadosPagamento() {
        if(pagamento == null)
            pagamento = new Pagamento();
        pagamento.setData(LocalDate.now());
        pagamento.setStatus(listaStatus.get(cadastroPagamento.getPosicaoStatus()));
        pagamento.setDataPagamento(cadastroPagamento.getDataPagamento());
        pagamento.setPaciente(listaPaciente.get(cadastroPagamento.getPosicaoPaciente()));
        pagamento.setValor(new BigDecimal(cadastroPagamento.getValor()));
        pagamento.setTipoPagamento(listaTipoPagamento.get(cadastroPagamento.getPosicaoTipoPagamento()));
        pagamento.setObservacao(cadastroPagamento.getObservacao());
    }
    
    public void AdicionaAtualizaPagamento() {
        if(verificaCampos()) {
            try {
                AtualizaDadosPagamento();
                pagamentoDAO.SalvaAtualiza(pagamento);
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
    
    public void PreencheComboBoxStatus(JComboBox combobox) {        
        listaStatus = statusDAO.BuscarStatus();
        for(int i = 0; i < listaStatus.size(); i++) {
            combobox.addItem(listaStatus.get(i).getNome());
        }
    }
    
    public void PreencheComboBoxPaciente(JComboBox combobox) {
        listaPaciente = pacienteDAO.BuscarPacientes();
        for(int i = 0; i < listaPaciente.size(); i++) {
            combobox.addItem(listaPaciente.get(i).getNome());
        }  
    }
    
    public void PreencheComboBoxTipoPagamento(JComboBox combobox) {
        listaTipoPagamento = tipoPagamentoDAO.BuscarTipoPagamento();
        for(int i = 0; i < listaTipoPagamento.size(); i++) {
            combobox.addItem(listaTipoPagamento.get(i).getNome());
        }
    }
  
    private boolean verificaCampos() {
        boolean check = true;
        
        if (cadastroPagamento.getComboBoxStatus().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Status não selecionado.");
            check = false;
        }
        else if (listaStatus.get(cadastroPagamento.getPosicaoStatus()).getCodigo() == StatusDAO.STATUS_FINALIZADO) {
            JOptionPane.showMessageDialog(null, "Status Finalizado não pode ser selecionado.");
            check = false;
        }
        else if (cadastroPagamento.getComboBoxPaciente().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Paciente não selecionado.");
            check = false;
        }
        else if (cadastroPagamento.getValor().isBlank()) {
            JOptionPane.showMessageDialog(null, "Valor vazio.");
            check = false;
        }
        else if (cadastroPagamento.getComboBoxTipoPagamento().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Tipo de Pagamento não selecionado.");
            check = false;
        }
        else if (cadastroPagamento.getDataPagamento() == null) {
            JOptionPane.showMessageDialog(null, "Data do pagamento não selecionado.");
            check = false;
        }
        else if ((pagamento != null) && (pagamento.getCodigo() !=0 ) && (pagamento.getStatus().getCodigo() == StatusDAO.STATUS_FINALIZADO) && 
                (listaStatus.get(cadastroPagamento.getPosicaoStatus()).getCodigo() != StatusDAO.STATUS_FINALIZADO)) {
            caixaSenha caixa = new caixaSenha(ControlePrincipal.framePrincipal);
            caixa.setVisible(true);
            if (!caixa.senhaValida()) {
                check = false;
            }
           
        }
        return check;
    }
    
    public void ConfiguraStatus() {
        if (cadastroPagamento.getPosicaoStatus() >= 0) {
            if((listaStatus.get(cadastroPagamento.getPosicaoStatus()).getCodigo() == StatusDAO.STATUS_ABERTO))
                cadastroPagamento.setEditavel();
            else 
                cadastroPagamento.setNaoEditavel();
        }
    }
    
}
