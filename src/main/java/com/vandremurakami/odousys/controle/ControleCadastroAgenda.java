/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.AgendaDAO;
import com.vandremurakami.odousys.dao.DentistaDAO;
import com.vandremurakami.odousys.gui.panelCadastroAgenda;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JComboBox;
import com.vandremurakami.odousys.modelo.Agenda;
import com.vandremurakami.odousys.modelo.Dentista;
import com.vandremurakami.odousys.modelo.HorariosAgenda;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Vandre
 */
public class ControleCadastroAgenda {
    
    final private AgendaDAO agendaDAO = new AgendaDAO();
    private final DentistaDAO dentistaDAO = new DentistaDAO();
    private List<HorariosAgenda> listaHorariosAgenda;
    private List<Dentista> listaDentista;
    
    private final JDialog dialog;
    private final panelCadastroAgenda cadastroAgenda;
    private Agenda agenda;
    private final LocalDate data;

    public ControleCadastroAgenda(JDialog d, panelCadastroAgenda c, Agenda a, LocalDate ld) {
        this.dialog = d;
        this.cadastroAgenda = c;
        this.agenda = a;
        this.data = ld;
        inicializaPanelCadastroAgenda();
    }
    
    private void inicializaPanelCadastroAgenda() {
        PreencheComboBoxDentista(cadastroAgenda.getComboBoxDentista());
        PreencheComboBoxHorario(cadastroAgenda.getComboBoxHorario());
        PreencheCadastroAgenda();
    }
    
    private void PreencheCadastroAgenda() {
        if(agenda != null) {
            cadastroAgenda.setHorario(agenda.getHora());
            cadastroAgenda.setNomeDentista(agenda.getDentista().getNome());
            cadastroAgenda.setAnotacao(agenda.getAnotacao());
        }
    }
     
    private void AtualizaDadosAgenda() {
        if(agenda == null)
            agenda = new Agenda();
        agenda.setData(data);
        agenda.setHora(cadastroAgenda.getHorario());
        agenda.setDentista(listaDentista.get(cadastroAgenda.getPosicaoDentista()));
        agenda.setAnotacao(cadastroAgenda.getAnotacao());
    }
    
    public void AdicionaAtualizaAgenda() {
        if(verificaCampos()) {
            try {
                AtualizaDadosAgenda();
                agendaDAO.SalvaAtualiza(agenda);
                dialog.dispose();
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro. Verifique se o agendamento já existe.");
            }
        }
    }
    
    public void Fechar() {
        dialog.dispose();
    }
    
    private void PreencheComboBoxHorario(JComboBox comboBox) {     
        listaHorariosAgenda = agendaDAO.BuscarHorarios();
        for(int i = 0; i < listaHorariosAgenda.size(); i++) {
            HorariosAgenda o = listaHorariosAgenda.get(i);
            comboBox.addItem(o.getHora());
        }  
    }
    
    private void PreencheComboBoxDentista(JComboBox comboBox) {     
        listaDentista = dentistaDAO.BuscarDentistas();
        for(int i = 0; i < listaDentista.size(); i++) {
            Dentista o = listaDentista.get(i);
            comboBox.addItem(o.getNome());
        }
    }
    
    private boolean verificaCampos() {
        boolean check = true;
        
        if (cadastroAgenda.getComboBoxDentista().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Dentista não selecionado.");
            check = false;
        }
        else if (cadastroAgenda.getComboBoxHorario().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Horário não selecionado.");
            check = false;
        }
        
        return check;
    }
    
}
