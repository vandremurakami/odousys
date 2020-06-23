/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.UsuarioDAO;
import com.vandremurakami.odousys.gui.dialogCadastro;
import com.vandremurakami.odousys.gui.panelCadastroUsuario;
import com.vandremurakami.odousys.gui.panelListarUsuario;
import com.vandremurakami.odousys.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vandre
 */
public class ControleListaUsuario {
    
    private final panelListarUsuario panelListaUsuario;
    
    final private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private List<Usuario> listaUsuarios;
    
    public ControleListaUsuario(panelListarUsuario panel) {
        this.panelListaUsuario = panel;
        PreencheTabelaUsuario();
    }
    
    public void PreencheTabelaUsuario() {        
             
        listaUsuarios = usuarioDAO.BuscaUsuarios();
        
        //filtra a lista contendo palavras chaves do textFieldFiltro
        if(!panelListaUsuario.getFiltro().isBlank()) {
            List<Usuario> listaFiltrada = new ArrayList<>();
            String[] splitStr = panelListaUsuario.getFiltro().split("\\s+", 10);
            for (String splitStr1 : splitStr) {
                String palavraChave = splitStr1.toLowerCase();
                listaFiltrada.addAll(listaUsuarios.parallelStream()
                        .filter(object -> (object.getLogin().toLowerCase().contains(palavraChave)) ||
                                (object.getLogin().toLowerCase().contains(palavraChave)))
                        .collect(Collectors.toList()));
            }
            //filtra objetos duplicados que caem em mais de um filtro
            listaUsuarios = listaFiltrada.stream().distinct().collect(Collectors.toList());
        }

        DefaultTableModel tabela = (DefaultTableModel) panelListaUsuario.getTabelaUsuarios().getModel();
        tabela.setRowCount(0);
        for(int i = 0; i < listaUsuarios.size(); i++) {
            Usuario o = listaUsuarios.get(i);
            String nomeDentista = "";
            if(o.getDentista() != null)
                nomeDentista = o.getDentista().getNome();
            Object[] dados = {o.getLogin(), nomeDentista};
            tabela.addRow(dados);
        }
        
    }
    
    public void CadastroAdicionarUsuario() {
        
        dialogCadastro dialogUsuario = new dialogCadastro(ControlePrincipal.framePrincipal, true);
        panelCadastroUsuario cadastroUsuario = new panelCadastroUsuario(dialogUsuario, null);
        
        dialogUsuario.setWindowName("Cadastro de Usuario");
        dialogUsuario.setPanel(cadastroUsuario);
        dialogUsuario.setVisible(true);

        PreencheTabelaUsuario();
    }
    
    public void CadastroAbrirUsuario() {
        int linhaSelecionada = panelListaUsuario.getTabelaUsuarios().getSelectedRow();
        if ( linhaSelecionada >= 0 ) {
            Usuario usuario = listaUsuarios.get(linhaSelecionada);
            
            dialogCadastro dialogUsuario = new dialogCadastro(ControlePrincipal.framePrincipal, true);
            panelCadastroUsuario cadastroUsuario = new panelCadastroUsuario(dialogUsuario, usuario);
                        
            
            dialogUsuario.setWindowName("Cadastro de Usuario");
            dialogUsuario.setPanel(cadastroUsuario);
            dialogUsuario.setVisible(true);
            
            PreencheTabelaUsuario();
        }
        else {
            JOptionPane.showMessageDialog(null, "Selecione um ítem para abrir.");
        }
    }
    
    public void RemoveUsuario() {
        int confimacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir.");
        if(confimacao == 0) {
            int linhaSelecionada = panelListaUsuario.getTabelaUsuarios().getSelectedRow();
            if ( linhaSelecionada >= 0 ) {
                try {
                    usuarioDAO.Deleta(listaUsuarios.get(linhaSelecionada));
                    listaUsuarios.remove(linhaSelecionada);
                    PreencheTabelaUsuario();
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

    public void FecharListaDentista() {
        ControlePrincipal.mostraHome();
    }

}
