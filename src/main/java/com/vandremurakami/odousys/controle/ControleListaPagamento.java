/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.PacienteDAO;
import com.vandremurakami.odousys.dao.PagamentoDAO;
import com.vandremurakami.odousys.dao.StatusDAO;
import com.vandremurakami.odousys.gui.caixaSenha;
import com.vandremurakami.odousys.gui.dialogCadastro;
import com.vandremurakami.odousys.gui.panelListarPagamento;
import com.vandremurakami.odousys.gui.panelCadastroPagamento;
import java.awt.Color;
import java.awt.Component;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.vandremurakami.odousys.modelo.Paciente;
import com.vandremurakami.odousys.modelo.Pagamento;
import com.vandremurakami.odousys.modelo.Status;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author Vandre
 */
public class ControleListaPagamento {

    private final panelListarPagamento painelListaPagamento;
    
    private final StatusDAO statusDAO = new StatusDAO();
    private final PagamentoDAO pagamentoDAO = new PagamentoDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private List<Pagamento> listaPagamento;
    private List<Paciente> listaPaciente;
    private List<Pagamento> listaFiltradaPagamento;
    
    private final int NUM_COLUNA_STATUS = 6;
    private final int NUM_COLUNA_CHECADO = 7;
    
    public ControleListaPagamento(panelListarPagamento panel) {
        this.painelListaPagamento = panel;
        inicializaPanelListaPagamento();
    }
    
    private void inicializaPanelListaPagamento() {
        PreencheComboboxPaciente();
        carregaListaBD();
        PreencheTabelaPagamento();
        configuraCoresStatusTabelaPagamento();
    }
    
    private void carregaListaBD() {
        listaPagamento = pagamentoDAO.BuscarPagamentos();
    }
    
    public void PreencheTabelaPagamento() {
        
        int linhaSelecionada = painelListaPagamento.getComboBoxPaciente().getSelectedIndex();
        if (linhaSelecionada == 0) {            
            listaFiltradaPagamento = listaPagamento;
            
        } else if (linhaSelecionada > 0) {
        
            Paciente paciente = listaPaciente.get(linhaSelecionada-1);
            
            listaFiltradaPagamento = new ArrayList<>();
                listaFiltradaPagamento.addAll(listaPagamento.parallelStream()
                        .filter(object -> (object.getPaciente().getCodigo() == paciente.getCodigo()))
                        .collect(Collectors.toList()));
            //filtra objetos duplicados que caem em mais de um filtro
            listaFiltradaPagamento = listaFiltradaPagamento.stream().distinct().collect(Collectors.toList());

        }
        
                
        DefaultTableModel tabela = (DefaultTableModel) painelListaPagamento.getTabelaPagamento().getModel();
        tabela.setRowCount(0);
        for(int i = 0; i < listaFiltradaPagamento.size(); i++) { 
            Pagamento p = listaFiltradaPagamento.get(i);
            
            String dataPagamento = "";
            if(p.getDataPagamento() != null)
                dataPagamento = p.getDataPagamento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
            
            Object[] dados = {p.getCodigo(),
                p.getData().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)), dataPagamento,
                p.getPaciente().getNome(), p.getTipoPagamento().getNome(), p.getValor(),
                p.getStatus().getNome(), false};
            tabela.addRow(dados);

        }

    }
    
    public void CadastroAdicionarPagamento() {
        
        dialogCadastro dialogPagamento = new dialogCadastro(ControlePrincipal.framePrincipal, true);
        panelCadastroPagamento cadastroPagamento = new panelCadastroPagamento(dialogPagamento, null);
        
        int posicao = painelListaPagamento.getComboBoxPaciente().getSelectedIndex()-1;
        if(posicao >= 0)
             cadastroPagamento.setNomePaciente(listaPaciente.get(posicao).getNome());
        
        dialogPagamento.setWindowName("Cadastro de Pagamento");
        dialogPagamento.setPanel(cadastroPagamento);
        dialogPagamento.setVisible(true);
        
        carregaListaBD();
        PreencheTabelaPagamento();
    }
    
    public void CadastroAbrirPagamento() {
        int linhaSelecionada = painelListaPagamento.getTabelaPagamento().getSelectedRow();
        if ( linhaSelecionada >= 0 ) {
            Pagamento pagamento = listaFiltradaPagamento.get(linhaSelecionada);
            
            dialogCadastro dialogPagamento = new dialogCadastro(ControlePrincipal.framePrincipal, true);
            panelCadastroPagamento cadastroPagamento = new panelCadastroPagamento(dialogPagamento, pagamento);
                        
            dialogPagamento.setWindowName("Cadastro de Pagamento");
            dialogPagamento.setPanel(cadastroPagamento);
            dialogPagamento.setVisible(true);
            
            carregaListaBD();
            PreencheTabelaPagamento();
        }
        else {
            JOptionPane.showMessageDialog(null, "Selecione um ítem para abrir.");
        }
    }

    private void PreencheComboboxPaciente() {
        listaPaciente = pacienteDAO.BuscarPacientes();
        for(int i = 0; i < listaPaciente.size(); i++) {
            painelListaPagamento.getComboBoxPaciente().addItem(listaPaciente.get(i).getNome());
        } 
    }

    public void ConferePagamento() {
            caixaSenha caixa = new caixaSenha(ControlePrincipal.framePrincipal);
            caixa.setVisible(true);
            if (caixa.senhaValida()) {

                Status statusAprovado = statusDAO.BuscarStatusAprovado();

                DefaultTableModel tabela = (DefaultTableModel) painelListaPagamento.getTabelaPagamento().getModel();
                for(int i = 0; i < listaFiltradaPagamento.size(); i++) { 
                    Pagamento p = listaFiltradaPagamento.get(i);

                    if( p.getStatus().getCodigo() == StatusDAO.STATUS_ABERTO ) {
                        boolean checado = (boolean) tabela.getValueAt(i, NUM_COLUNA_CHECADO);

                        if( checado ) {
                            p.setStatus(statusAprovado);
                            pagamentoDAO.SalvaAtualiza(p);
                        }                    

                    }

                }  
                carregaListaBD();
                PreencheTabelaPagamento();
            }
    }

    public void FecharListaPagamento() {
        ControlePrincipal.mostraHome();
    }

    private void configuraCoresStatusTabelaPagamento() {
        painelListaPagamento.getTabelaPagamento().getColumnModel().getColumn(NUM_COLUNA_STATUS).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = table.getValueAt(row, NUM_COLUNA_STATUS).toString();
                switch (status) {
                    case "Aberto":
                        c.setBackground(Color.BLUE);
                        break;
                    case "Aprovado":
                        c.setBackground(Color.GREEN);
                        break;
                    case "Cancelado":
                        c.setBackground(Color.RED);
                        break;
                    default:
                        break;
                }
                return c;
            }
        });
    }
    
}
