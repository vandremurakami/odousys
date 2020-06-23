/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.DentistaDAO;
import com.vandremurakami.odousys.dao.OrcamentoDAO;
import com.vandremurakami.odousys.dao.PacienteDAO;
import com.vandremurakami.odousys.dao.ServicoOrcamentoDAO;
import com.vandremurakami.odousys.dao.StatusDAO;
import com.vandremurakami.odousys.dao.TabelaPrecoDAO;
import com.vandremurakami.odousys.gui.caixaImprimirOrcamento;
import com.vandremurakami.odousys.gui.dialogCadastro;
import com.vandremurakami.odousys.gui.panelCadastroOrcamento;
import com.vandremurakami.odousys.gui.panelCadastroServicoOrcamento;
import com.vandremurakami.odousys.modelo.Dentista;
import java.util.List;
import com.vandremurakami.odousys.modelo.Orcamento;
import com.vandremurakami.odousys.modelo.Paciente;
import com.vandremurakami.odousys.modelo.ServicoOrcamento;
import com.vandremurakami.odousys.modelo.Status;
import com.vandremurakami.odousys.modelo.TabelaPreco;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vandre
 */
public class ControleCadastroOrcamento {
    
    private final OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
    private final DentistaDAO dentistaDAO = new DentistaDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final StatusDAO statusDAO = new StatusDAO();
    private final TabelaPrecoDAO tabelaPrecoDAO = new TabelaPrecoDAO();
    private final ServicoOrcamentoDAO servicoOrcamentoDAO= new ServicoOrcamentoDAO();
    private List<Dentista> listaDentista;
    private List<Paciente> listaPaciente;
    private List<Status> listaStatus;
    private List<ServicoOrcamento> listaServicoOrcamentos = new ArrayList<>();
    
    private final JDialog dialog;
    private final panelCadastroOrcamento cadastroOrcamento;
    private Orcamento orcamento;
    private TabelaPreco tabelaPreco;
    
    public ControleCadastroOrcamento(JDialog d, panelCadastroOrcamento c, Orcamento o) {
        this.dialog = d;
        this.cadastroOrcamento = c;
        this.orcamento = o;
        inicializaPanelCadastroOrcamento();
    }
    
    private void inicializaPanelCadastroOrcamento() {
        PreencheComboboxDentista(cadastroOrcamento.getComboBoxDentista());
        PreencheComboboxPaciente(cadastroOrcamento.getComboBoxPaciente());
        PreencheComboboxStatus(cadastroOrcamento.getComboBoxStatus());
        EscolheTabelaPreco();
        PreencheCadastroOrcamento();
    }
    
    private void EscolheTabelaPreco() {
        if(orcamento == null)
            tabelaPreco = tabelaPrecoDAO.BuscarTabelaPrecoAtivo();
        else
            tabelaPreco = orcamento.getTabelaPreco();
        
        cadastroOrcamento.setTabelaPreco(tabelaPreco.getNome());
    }
    
    private void PreencheCadastroOrcamento() {
        if(orcamento != null) {
            cadastroOrcamento.setNomeDentista(orcamento.getDentista().getNome());
            cadastroOrcamento.setNomePaciente(orcamento.getPaciente().getNome());
            cadastroOrcamento.setNomeStatus(orcamento.getStatus().getNome());
            cadastroOrcamento.setPorcentagemDesconto(orcamento.getPorcentagemDesconto().toString());
            cadastroOrcamento.setValorDesconto(orcamento.getValorDesconto().toString());
            cadastroOrcamento.setValorFinal(orcamento.getValorFinal().toString());
            cadastroOrcamento.setObservacao(orcamento.getObservacao());
            listaServicoOrcamentos = orcamento.getServicoOrcamento();
            PreencheTabelaServicosOrcamento();
            if(orcamento.getStatus().getCodigo() == StatusDAO.STATUS_FINALIZADO)
                cadastroOrcamento.setNaoEditavel();
        }
        else {
            cadastroOrcamento.setNomeStatus(statusDAO.BuscarStatusAberto().getNome());
        }
    }
    
    private void PreencheTabelaServicosOrcamento() {        
        
        if(!listaServicoOrcamentos.isEmpty()) {
            DefaultTableModel tabela = (DefaultTableModel) cadastroOrcamento.getTabelaServicos().getModel();
            tabela.setRowCount(0);
            for(int i = 0; i < listaServicoOrcamentos.size(); i++) {
                ServicoOrcamento servico =  listaServicoOrcamentos.get(i);
                Object[] dados = {servico.getPreco().getNome(), servico.getPreco().getValor(),
                    servico.getValorDesconto(), servico.getQuantidade(), servico.getObservacao(), servico.getTotal()};
                tabela.addRow(dados);

            }

            //preenche o campo do valor total dos serviços listados
            cadastroOrcamento.setValorFinal(valorTotal()
                    .subtract(new BigDecimal(cadastroOrcamento.getValorDesconto())
                            .setScale(2, RoundingMode.DOWN)).toString());
        }
        else {
            DefaultTableModel tabela = (DefaultTableModel) cadastroOrcamento.getTabelaServicos().getModel();
            tabela.setRowCount(0);
        }
    }
     
    private void AtualizaDadosOrcamento() {
        if(orcamento == null)
            orcamento = new Orcamento();
        
        orcamento.setDentista(listaDentista.get(cadastroOrcamento.getPosicaoDentista()));
        orcamento.setPaciente(listaPaciente.get(cadastroOrcamento.getPosicaoPaciente()));
        orcamento.setStatus(listaStatus.get(cadastroOrcamento.getPosicaoStatus()));
        orcamento.setData(LocalDate.now());
        if(!cadastroOrcamento.getPorcentagemDesconto().equals(""))
            orcamento.setPorcentagemDesconto(new BigDecimal(cadastroOrcamento.getPorcentagemDesconto()));
        else
            orcamento.setPorcentagemDesconto(BigDecimal.ZERO);
        if(!cadastroOrcamento.getValorDesconto().equals(""))
            orcamento.setValorDesconto(new BigDecimal(cadastroOrcamento.getValorDesconto()));
        else
            orcamento.setValorDesconto(BigDecimal.ZERO);
        orcamento.setValorFinal(new BigDecimal(cadastroOrcamento.getValorFinal()));
        orcamento.setObservacao(cadastroOrcamento.getObservacao());
        orcamento.setTabelaPreco(tabelaPreco);
        AtualizaOrcamentoServicos();
        orcamento.setServicoOrcamento(listaServicoOrcamentos);
    }
    
    public void AdicionaAtualizaOrcamento() {
        if(verificaCampos()) {
            try {
                AtualizaDadosOrcamento();
                orcamentoDAO.SalvaAtualiza(orcamento);
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

    private void PreencheComboboxDentista(JComboBox<String> comboBoxDentista) {
        listaDentista = dentistaDAO.BuscarDentistas();
        for(int i = 0; i < listaDentista.size(); i++) {
            comboBoxDentista.addItem(listaDentista.get(i).getNome());
        } 
    }
    
    private void PreencheComboboxPaciente(JComboBox<String> comboBoxPaciente) {
        listaPaciente = pacienteDAO.BuscarPacientes();
        for(int i = 0; i < listaPaciente.size(); i++) {
            comboBoxPaciente.addItem(listaPaciente.get(i).getNome());
        } 
    }
    
    private void PreencheComboboxStatus(JComboBox<String> comboBoxStatus) {
        listaStatus = statusDAO.BuscarStatus();
        for(int i = 0; i < listaStatus.size(); i++) {
            comboBoxStatus.addItem(listaStatus.get(i).getNome());
        } 
    }
    
    private BigDecimal valorTotal() {
        BigDecimal valor = BigDecimal.ZERO;
        for (int i = 0; i < listaServicoOrcamentos.size() ; i++) {
            valor = valor.add(listaServicoOrcamentos.get(i).getTotal());
        }
        return valor;
    }
    
    public void setValorDesconto() {
        String texto = cadastroOrcamento.getValorDesconto().trim();
        if(!texto.isBlank()) {
            BigDecimal valorTotal = valorTotal();
            BigDecimal valorDesconto = new BigDecimal(cadastroOrcamento.getValorDesconto()).setScale(2, RoundingMode.DOWN);
            BigDecimal porcentagem = valorDesconto.divide(valorTotal, 4, RoundingMode.DOWN).multiply(BigDecimal.valueOf(100.0)).setScale(2, RoundingMode.DOWN);

//            cadastroOrcamento.setValorDesconto(valorDesconto.toString());
            cadastroOrcamento.setPorcentagemDesconto(porcentagem.toString());
            cadastroOrcamento.setValorFinal(valorTotal.subtract(valorDesconto).toString());
        }
        else {
            cadastroOrcamento.setValorDesconto("");
            cadastroOrcamento.setPorcentagemDesconto("");
            cadastroOrcamento.setValorFinal(valorTotal().setScale(2, RoundingMode.DOWN).toString());
        }
    }
    
    public void setPorcentagemDesconto() {
        String texto = cadastroOrcamento.getPorcentagemDesconto().trim();
        if(!texto.isBlank()) {        
            BigDecimal valorTotal = valorTotal();
            BigDecimal porcentagem = new BigDecimal(cadastroOrcamento.getPorcentagemDesconto()).setScale(2, RoundingMode.DOWN);
            BigDecimal valorDesconto = porcentagem.divide(BigDecimal.valueOf(100.0), 4, RoundingMode.DOWN).multiply(valorTotal).setScale(2, RoundingMode.DOWN);

            cadastroOrcamento.setValorDesconto(valorDesconto.toString());
//            cadastroOrcamento.setPorcentagemDesconto(porcentagem.toString());
            cadastroOrcamento.setValorFinal(valorTotal.subtract(valorDesconto).toString());
        }
        else {
            cadastroOrcamento.setValorDesconto("");
            cadastroOrcamento.setPorcentagemDesconto("");
            cadastroOrcamento.setValorFinal(valorTotal().setScale(2, RoundingMode.DOWN).toString());
        }
    }

    public void CadastroAbrirServico() {
        int linhaSelecionada = cadastroOrcamento.getTabelaServicos().getSelectedRow();
        if ( linhaSelecionada >= 0 ) {
            ServicoOrcamento servico = listaServicoOrcamentos.get(linhaSelecionada);
            
            dialogCadastro dialogServico = new dialogCadastro(ControlePrincipal.framePrincipal, true);
            panelCadastroServicoOrcamento cadastroServico = new panelCadastroServicoOrcamento(dialogServico, tabelaPreco, servico);
                        
            dialogServico.setWindowName("Cadastro de Serviço");
            dialogServico.setPanel(cadastroServico);
            dialogServico.setVisible(true);
            
            PreencheTabelaServicosOrcamento();
        }
        else {
            JOptionPane.showMessageDialog(null, "Selecione um ítem para abrir.");
        }
    }

    public void CadastroAdicionarServico() {
        dialogCadastro dialogServico = new dialogCadastro(ControlePrincipal.framePrincipal, true);
        panelCadastroServicoOrcamento cadastroServico = new panelCadastroServicoOrcamento(dialogServico, tabelaPreco, null);
        
        dialogServico.setWindowName("Cadastro de Serviço");
        dialogServico.setPanel(cadastroServico);
        dialogServico.setVisible(true);
        
        if(cadastroServico.getServicoOrcamento() != null)
            listaServicoOrcamentos.add(cadastroServico.getServicoOrcamento());
        PreencheTabelaServicosOrcamento();
    }

    public void RemoverServico() {
        int linhaSelecionada = cadastroOrcamento.getTabelaServicos().getSelectedRow();
        if ( linhaSelecionada >= 0 ) {
            servicoOrcamentoDAO.Deleta(listaServicoOrcamentos.get(linhaSelecionada));
            listaServicoOrcamentos.remove(linhaSelecionada);
            PreencheTabelaServicosOrcamento();
        }
        else {
            JOptionPane.showMessageDialog(null, "Selecione um ítem para remover.");
        }
    }
    
    private void AtualizaOrcamentoServicos() {
        for(int i = 0; i < listaServicoOrcamentos.size(); i++) {
            listaServicoOrcamentos.get(i).setOrcamento(orcamento);
        }
    }

    private boolean verificaCampos() {
        boolean check = true;
        
        if (cadastroOrcamento.getComboBoxDentista().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Dentista não selecionado.");
            check = false;
        }
        else if (cadastroOrcamento.getComboBoxStatus().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Status não selecionado.");
            check = false;
        }
        else if (listaServicoOrcamentos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Serviços não adicionados.");
            check = false;
        }
        return check;
    }

    public void ImprimirOrcamento() {
        AdicionaAtualizaOrcamento();
        new caixaImprimirOrcamento(ControlePrincipal.framePrincipal, orcamento).setVisible(true);
    }

    public void ConfiguraStatus() {
        if (cadastroOrcamento.getPosicaoStatus() >= 0) {
            if((listaStatus.get(cadastroOrcamento.getPosicaoStatus()).getCodigo() == StatusDAO.STATUS_ABERTO))
                cadastroOrcamento.setEditavel();
            else 
                cadastroOrcamento.setNaoEditavel();
        }
    }
    
}
