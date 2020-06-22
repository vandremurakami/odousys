/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.hibernate.HibernateUtil;
import com.vandremurakami.odousys.gui.dialogConexaoBD;
import com.vandremurakami.odousys.gui.dialogLogin;
import com.vandremurakami.odousys.gui.framePrincipal;
import com.vandremurakami.odousys.gui.panelExtrato;
import com.vandremurakami.odousys.gui.panelFaturamentoMensal;
import com.vandremurakami.odousys.gui.panelListarAgenda;
import com.vandremurakami.odousys.gui.panelListarDentista;
import com.vandremurakami.odousys.gui.panelListarOrcamento;
import com.vandremurakami.odousys.gui.panelListarPagamento;
import com.vandremurakami.odousys.gui.panelHome;
import com.vandremurakami.odousys.gui.panelListarTabelaPreco;
import com.vandremurakami.util.BackupBD;
import javax.swing.JOptionPane;

/**
 *
 * @author vandre
 */
public class ControlePrincipal {
    
    public static framePrincipal framePrincipal;
    
    public ControlePrincipal(framePrincipal frame) {
        ControlePrincipal.framePrincipal = frame;
    }
    
    public static void mostraHome() {
        //O panel Home não possui controlador por não conter nenhuma ação para ser controlada
        framePrincipal.setPanelBody(new panelHome(), "Seja bem vindo !");
    }
    
    public void mostraDentista() {
        framePrincipal.setPanelBody(new panelListarDentista(), "Dentistas");
    }
    
    public void mostraPreco() {
        framePrincipal.setPanelBody(new panelListarTabelaPreco(), "Tabela de Preços");
    }
    
    public void mostraAgenda() {
        framePrincipal.setPanelBody(new panelListarAgenda(), "Agendamentos");
    }
    
    public void mostraOrcamento() {
        framePrincipal.setPanelBody(new panelListarOrcamento(), "Orçamentos");
    }
    
    public void mostraPagamento() {
        framePrincipal.setPanelBody(new panelListarPagamento(), "Pagamentos");
    }
    
    public void mostraExtrato() {
        framePrincipal.setPanelBody(new panelExtrato(), "Extrato");
    }
    
    public void mostraFaturamento() {
        framePrincipal.setPanelBody(new panelFaturamentoMensal(), "Faturamento Mensal");
    }
    
    public void conectaBD() {
        dialogConexaoBD dialogConexao = new dialogConexaoBD(framePrincipal, true);
        if(!dialogConexao.conectado()) {
            dialogConexao.dispose();
            JOptionPane.showMessageDialog(framePrincipal, "Erro ao se conectar ao Banco de dados.");
            shutdown();
        }  
    }
    
    public void login() {
        dialogLogin login = new dialogLogin(framePrincipal, true);
        login.setVisible(true);
        if(!login.validado()) {
            shutdown();
        }
        login.dispose();
    }
    
    public void shutdown() {
        try {
            HibernateUtil.shutdown();
        }
        finally {
            System.exit(0);
        } 
    }

    public void backupBD() {
        try {
            new BackupBD().executeCommand(HibernateUtil.getHost(), HibernateUtil.getDatabase(), HibernateUtil.getLogin(), HibernateUtil.getPassword(), "backup");
            JOptionPane.showMessageDialog(framePrincipal, "Backup realizado com sucesso.");
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(framePrincipal, "Não foi possível realizar o Backup.");
        }
    }
    
}
