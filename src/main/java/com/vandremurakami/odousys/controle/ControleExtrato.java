/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.PacienteDAO;
import com.vandremurakami.odousys.dao.OrcamentoDAO;
import com.vandremurakami.odousys.dao.PagamentoDAO;
import com.vandremurakami.odousys.gui.dialogCadastro;
import com.vandremurakami.odousys.gui.panelCadastroOrcamento;
import com.vandremurakami.odousys.gui.panelCadastroPagamento;
import com.vandremurakami.odousys.gui.panelExtrato;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.vandremurakami.odousys.modelo.Paciente;
import com.vandremurakami.odousys.modelo.Orcamento;
import com.vandremurakami.odousys.modelo.Pagamento;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.PageRanges;
import javax.swing.JOptionPane;

/**
 *
 * @author vandr
 */
public class ControleExtrato {

    final private OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
    final private PagamentoDAO pagamentoDAO = new PagamentoDAO();
    final private PacienteDAO pacienteDAO = new PacienteDAO();
    private List<Orcamento> listaOrcamento;
    private List<Pagamento> listaPagamento;
    private List<Orcamento> listaFiltradaOrcamento;
    private List<Pagamento> listaFiltradaPagamento;
    private List<Paciente> listaPaciente;
    private List<Object> listaExtrato;

    final private panelExtrato panelExtrato;

    public ControleExtrato(panelExtrato panel) {
        this.panelExtrato = panel;
        PreencheComboboxPaciente();
        carregaListaBD();
    }
    
    private void carregaListaBD() {
        listaOrcamento = orcamentoDAO.BuscarOrcamentosExtrato();
        listaPagamento = pagamentoDAO.BuscarPagamentosExtrato();
    }

    private Object getExtrato(int indice) {
        if (!listaExtrato.isEmpty()) {
            return listaExtrato.get(indice);
        }
        return null;
    }
    
    public void AbrirCadastro() {

        int linhaSelecionada = panelExtrato.getTabelaExtrato().getSelectedRow();
        if (linhaSelecionada >= 0) {
            Object extrato = getExtrato(linhaSelecionada);
            dialogCadastro dialog = new dialogCadastro(ControlePrincipal.framePrincipal, true);
            if (extrato.getClass() == Orcamento.class) {
                panelCadastroOrcamento cadastroOrcamento = new panelCadastroOrcamento(dialog, (Orcamento) extrato);
                cadastroOrcamento.setNaoEditavel();
                dialog.setWindowName("Cadastro de Orçamento");
                dialog.setPanel(cadastroOrcamento);
            } else if (extrato.getClass() == Pagamento.class) {
                panelCadastroPagamento cadastroPagamento = new panelCadastroPagamento(dialog, (Pagamento) extrato);
                cadastroPagamento.setNaoEditavel();
                dialog.setWindowName("Cadastro de Pagamento");
                dialog.setPanel(cadastroPagamento);
            }
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um ítem para abrir.");
        }

    }

    public void PreencheTabelaExtrato() {

        int linhaSelecionada = panelExtrato.getComboBoxPaciente().getSelectedIndex();
        if (linhaSelecionada == 0) {
            
            DefaultTableModel tabela = (DefaultTableModel) panelExtrato.getTabelaExtrato().getModel();
            tabela.setRowCount(0);
            
        } else if (linhaSelecionada > 0) {
            Paciente paciente = listaPaciente.get(linhaSelecionada-1);
            
            
            listaFiltradaPagamento = new ArrayList<>();
                listaFiltradaPagamento.addAll(listaPagamento.parallelStream()
                        .filter(object -> (object.getPaciente().getCodigo() == paciente.getCodigo()))
                        .collect(Collectors.toList()));
            //filtra objetos duplicados que caem em mais de um filtro
            listaFiltradaPagamento = listaFiltradaPagamento.stream().distinct().collect(Collectors.toList());
            
            listaFiltradaOrcamento = new ArrayList<>();
                listaFiltradaOrcamento.addAll(listaOrcamento.parallelStream()
                        .filter(object -> (object.getPaciente().getCodigo() == paciente.getCodigo()))
                        .collect(Collectors.toList()));
            //filtra objetos duplicados que caem em mais de um filtro
            listaFiltradaOrcamento = listaFiltradaOrcamento.stream().distinct().collect(Collectors.toList());
            
            
            listaExtrato = new ArrayList<>();

            DefaultTableModel tabela = (DefaultTableModel) panelExtrato.getTabelaExtrato().getModel();
            tabela.setRowCount(0);

            BigDecimal saldo = BigDecimal.ZERO;
            Pagamento p = null;

            Iterator<Pagamento> iterator = listaFiltradaPagamento.iterator();
            if (iterator.hasNext()) {
                p = iterator.next();
            }

            LocalDate dataOcamento;
            LocalDate dataPagamento;
            for (int i = 0; i < listaFiltradaOrcamento.size(); i++) {

                Orcamento o = listaFiltradaOrcamento.get(i);

                dataOcamento = o.getData();

                while (p != null) {

                    if (p.getDataPagamento() == null)
                        dataPagamento = p.getData();
                    else
                        dataPagamento = p.getDataPagamento();

                    if (dataOcamento.isAfter(dataPagamento)) {

                        String data = dataPagamento.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                        saldo = saldo.add(p.getValor());

                        Object[] dados = {data, "", "", p.getValor().toString(), saldo.toString()};
                        tabela.insertRow(0, dados);
                        listaExtrato.add(0, p);

                        if (iterator.hasNext())
                            p = iterator.next();
                        else
                            p = null;

                    } else {
                        break;
                    }
                }

                String data = dataOcamento.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                saldo = saldo.subtract(o.getValorFinal());

                Object[] dados = {data, Integer.toString(o.getCodigo()), o.getValorFinal().toString(), "", saldo.toString()};
                tabela.insertRow(0, dados);
                listaExtrato.add(0, o);
            }

            while (p != null) {

                if (p.getDataPagamento() == null)
                    dataPagamento = p.getData();
                else
                    dataPagamento = p.getDataPagamento();

                String data = dataPagamento.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));

                saldo = saldo.add(p.getValor());

                Object[] dados = {data, "", "", p.getValor().toString(), saldo.toString()};
                tabela.insertRow(0, dados);
                listaExtrato.add(0, p);
                if (iterator.hasNext())
                    p = iterator.next();
                else
                    p = null;
            }
        }

    }

    private void PreencheComboboxPaciente() {
        listaPaciente = pacienteDAO.BuscarPacientes();
        for (int i = 0; i < listaPaciente.size(); i++) {
            Paciente o = listaPaciente.get(i);
            panelExtrato.getComboBoxPaciente().addItem(o.getNome());
        }
    }

    public void Fechar() {
        ControlePrincipal.mostraHome();
    }

    public void ImprimirExtrato() {
        int linhaSelecionada = panelExtrato.getComboBoxPaciente().getSelectedIndex();
        if (linhaSelecionada > 0) {
            PrinterJob printer = PrinterJob.getPrinterJob();
            PageFormat formatoPagina = printer.defaultPage();
            formatoPagina.setOrientation(PageFormat.PORTRAIT);
            Paper papel = formatoPagina.getPaper();
            papel.setImageableArea(0, 0, papel.getWidth(), papel.getHeight());
            formatoPagina.setPaper(papel);
            printer.setPrintable(panelExtrato.getTabelaExtrato()
                    .getPrintable(JTable.PrintMode.FIT_WIDTH, 
                            new MessageFormat(panelExtrato.getComboBoxPaciente().getSelectedItem().toString()), 
                            new MessageFormat("Página - {0}")),
                    formatoPagina);
            HashPrintRequestAttributeSet printParams = new HashPrintRequestAttributeSet();
            printParams.add(new MediaPrintableArea(5, 5, 138, 200, MediaPrintableArea.MM));  
            printParams.add(new PageRanges(1, 1));
            boolean ok = printer.printDialog(printParams);
            if (ok) {
                try {
                    printer.print(printParams);
                } catch (PrinterException e) {
                    JOptionPane.showMessageDialog(null, "Falha na impressão.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Paciente.");
        }
    }


}
