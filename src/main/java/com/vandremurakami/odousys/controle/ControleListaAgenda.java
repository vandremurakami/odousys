/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.AgendaDAO;
import com.vandremurakami.odousys.gui.dialogCadastro;
import com.vandremurakami.odousys.gui.panelListarAgenda;
import com.vandremurakami.odousys.gui.panelCadastroAgenda;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.vandremurakami.odousys.modelo.Agenda;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Vandre
 */
public class ControleListaAgenda {
    
    private final panelListarAgenda painelListaAgenda;
    
    private final AgendaDAO agendaDAO = new AgendaDAO();
    private List<Agenda> listaAgenda;
    
    private final int NUM_COLUNA_CHECK = 3;

    public ControleListaAgenda(panelListarAgenda panel) {
        this.painelListaAgenda = panel;
        PreencheTabelaAgenda();
        configuraCoresStatusTabelaAgenda();
    }
    
    public void PreencheTabelaAgenda() {
        LocalDate data = painelListaAgenda.getData();
        
        listaAgenda = agendaDAO.BuscarAgenda(data);

        DefaultTableModel tabela = (DefaultTableModel) painelListaAgenda.getTabelaAgenda().getModel();
        tabela.setRowCount(0);
        for(int i = 0; i < listaAgenda.size(); i++) {
            Agenda a = listaAgenda.get(i);
            Object[] dados = {a.getHora(), a.getDentista().getNome(), a.getAnotacao(), a.getCheck()};
            tabela.addRow(dados);
        }
        
    }
    

    public void CadastroAdicionarAgenda() {
        
        dialogCadastro dialogAgenda = new dialogCadastro(ControlePrincipal.framePrincipal, true);
        panelCadastroAgenda cadastroAgenda = new panelCadastroAgenda(dialogAgenda, null, painelListaAgenda.getData());
        
        dialogAgenda.setWindowName("Cadastro de Agenda");
        dialogAgenda.setPanel(cadastroAgenda);
        dialogAgenda.setVisible(true);

        PreencheTabelaAgenda();
    }
    
    public void CadastroAbrirAgenda() {
        int linhaSelecionada = painelListaAgenda.getTabelaAgenda().getSelectedRow();
        if ( linhaSelecionada >= 0 ) {
            Agenda agenda = listaAgenda.get(linhaSelecionada);
            
            dialogCadastro dialogAgenda = new dialogCadastro(ControlePrincipal.framePrincipal, true);
            panelCadastroAgenda cadastroAgenda = new panelCadastroAgenda(dialogAgenda, agenda, painelListaAgenda.getData());
                        
            
            dialogAgenda.setWindowName("Cadastro de Agenda");
            dialogAgenda.setPanel(cadastroAgenda);
            dialogAgenda.setVisible(true);
            
            PreencheTabelaAgenda();
        }
        else {
            JOptionPane.showMessageDialog(null, "Selecione um ítem para abrir.");
        }
    }
    
    
    public void RemoveListaAgenda() {
        int confimacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir.");
        if(confimacao == 0) {
            int linhaSelecionada = painelListaAgenda.getTabelaAgenda().getSelectedRow();
            if ( linhaSelecionada >= 0 ) {
                try {
                    agendaDAO.Deleta(listaAgenda.get(linhaSelecionada));
                    listaAgenda.remove(linhaSelecionada);
                    PreencheTabelaAgenda();
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

    public void FecharListaAgenda() {
        ControlePrincipal.mostraHome();
    }
    
    public void checkAgenda() {
        int linhaSelecionada = painelListaAgenda.getTabelaAgenda().getSelectedRow();
        if ( linhaSelecionada >= 0 ) {
            Agenda a = listaAgenda.get(linhaSelecionada);
            a.setCheck((boolean)painelListaAgenda.getTabelaAgenda().getValueAt(painelListaAgenda.getTabelaAgenda().getSelectedRow(), NUM_COLUNA_CHECK));
            agendaDAO.SalvaAtualiza(a);
        }
    }
    
    public void proximoDia() {
        painelListaAgenda.setData(painelListaAgenda.getData().plusDays(1));
        PreencheTabelaAgenda();
    }
    
    public void anteriorDia() {
        painelListaAgenda.setData(painelListaAgenda.getData().minusDays(1));
         PreencheTabelaAgenda();
    }
    
    public void hojeDia() {
        painelListaAgenda.setData(LocalDate.now());
         PreencheTabelaAgenda();
    }
    
    private void configuraCoresStatusTabelaAgenda() {
        painelListaAgenda.getTabelaAgenda().getColumnModel().getColumn(NUM_COLUNA_CHECK).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                boolean check = (boolean) table.getValueAt(row, NUM_COLUNA_CHECK);
                if (check) {
                    c.setBackground(Color.GREEN);
                    c.setForeground(Color.GREEN);
                } else {
                    c.setBackground(Color.BLUE);
                    c.setForeground(Color.BLUE);
                }

                return c;
            }
        });
    }
    
}
