/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.UsuarioDAO;
import com.vandremurakami.odousys.gui.dialogCadastro;
import com.vandremurakami.odousys.gui.panelAbas;
import com.vandremurakami.odousys.gui.panelCadastroUsuario;
import com.vandremurakami.odousys.gui.panelListarUsuario;
import com.vandremurakami.odousys.modelo.Usuario;
import com.vandremurakami.util.Criptografia;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vandre
 */
public class ControleUsuario {

    final private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final panelListarUsuario panelListaUsuario;
    private List<Usuario> listaUsuarios;
    private panelAbas abasUsuario;
    private Usuario usuario;
    private dialogCadastro dialogUsuario;
    
    public ControleUsuario(panelListarUsuario panel) {
        this.panelListaUsuario = panel;
    }
    
    public Usuario getUsuario(int i) {
        return listaUsuarios.get(i);
    }
    
    public void CadastroAdicionarUsuario() {

        usuario = new Usuario();
        
        dialogUsuario = new dialogCadastro(ControlePrincipal.framePrincipal, true);
        panelCadastroUsuario cadastroUsuario = new panelCadastroUsuario(this);
        
        abasUsuario = new panelAbas();
        abasUsuario.adicionarAba(cadastroUsuario, "Usuário");
        
        dialogUsuario.setWindowName("Cadastro de Usuário");
        dialogUsuario.setPanel(abasUsuario);
        dialogUsuario.setVisible(true);

        PreencheTabelaUsuario();
    }
    
    public void CadastroAbrirUsuario() {
        int linhaSelecionada = panelListaUsuario.getTabelaUsuarios().getSelectedRow();
        if ( linhaSelecionada >= 0 ) {
            usuario = listaUsuarios.get(linhaSelecionada);
            
            dialogUsuario = new dialogCadastro(ControlePrincipal.framePrincipal, true);
            panelCadastroUsuario cadastroUsuario = new panelCadastroUsuario(this);
            PreencheCadastroUsuario(cadastroUsuario);
            
            abasUsuario = new panelAbas();
            abasUsuario.adicionarAba(cadastroUsuario, "Usuário");
            
            dialogUsuario.setWindowName("Cadastro de Usuário");
            dialogUsuario.setPanel(abasUsuario);
            dialogUsuario.setVisible(true);
            
            PreencheTabelaUsuario();
        }
        else {
            JOptionPane.showMessageDialog(null, "Selecione um ítem para abrir.");
        }
    }
    
    public void PreencheTabelaUsuario() {        
             
        listaUsuarios = usuarioDAO.BuscaUsuarios();
        
        //filtra a lista contendo palavras chaves do textFieldFiltro
        if(!panelListaUsuario.getFiltro().getText().equals("")) {
            List<Usuario> listaFiltrada = new ArrayList<>();
            String[] splitStr = panelListaUsuario.getFiltro().getText().trim().split("\\s+", 10);
            for (String splitStr1 : splitStr) {
                String palavraChave = splitStr1.toLowerCase();
                listaFiltrada.addAll(listaUsuarios.parallelStream()
                        .filter(object -> (object.getNome().toLowerCase().contains(palavraChave)) ||
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
            Object[] dados = {o.getNome(), o.getLogin()};
            tabela.addRow(dados);
        }
        
    }
    
    private void PreencheCadastroUsuario(panelCadastroUsuario cadastroUsuario) {
        int linhaSelecionada = panelListaUsuario.getTabelaUsuarios().getSelectedRow();
        if ( linhaSelecionada >= 0 ) {
            usuario = listaUsuarios.get(panelListaUsuario.getTabelaUsuarios().getSelectedRow());
            cadastroUsuario.setNome(usuario.getNome());
            cadastroUsuario.setLogin(usuario.getLogin());
            cadastroUsuario.setSenha(usuario.getSenha());
        }
        
    }
    
    public void AdicionaAtualizaUsuario(panelCadastroUsuario cadastroUsuario) {
        AtualizaDadosUsuario(usuario, cadastroUsuario);
        //Apenas criptografa se a senha foi alterada para não criptografar senha já criptografada
        if (cadastroUsuario.senhaAlterada() || usuario.getSenha() == null) {
            usuario.setSenha(Criptografia.hash(cadastroUsuario.getSenha()));
        }
        usuarioDAO.SalvaAtualiza(usuario);
        JOptionPane.showMessageDialog(null, "Informações salvas com sucesso.");
    }
    
    public void AtualizaDadosUsuario(Usuario usuario, panelCadastroUsuario cadastroUsuario) {
        usuario.setNome(cadastroUsuario.getNome());
        usuario.setLogin(cadastroUsuario.getLogin());
    }
    
    public void RemoveUsuario() {
        int linhaSelecionada = panelListaUsuario.getTabelaUsuarios().getSelectedRow();
        if ( linhaSelecionada >= 0 ) {
            usuarioDAO.Deleta(listaUsuarios.get(linhaSelecionada));
            listaUsuarios.remove(linhaSelecionada);
            PreencheTabelaUsuario();
        }
        else {
            JOptionPane.showMessageDialog(null, "Selecione um ítem para remover.");
        }
    }
    
    public void FecharCadastroUsuario() {
        dialogUsuario.dispose();
    }

    public void FecharListaUsuario() {
        ControlePrincipal.mostraHome();
    }

}
