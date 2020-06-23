/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.DentistaDAO;
import com.vandremurakami.odousys.dao.UsuarioDAO;
import com.vandremurakami.odousys.gui.panelCadastroUsuario;
import com.vandremurakami.odousys.modelo.Dentista;
import com.vandremurakami.odousys.modelo.Usuario;
import com.vandremurakami.util.Criptografia;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author vandre
 */
public class ControleCadastroUsuario {

    final private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final DentistaDAO dentistaDAO = new DentistaDAO();
    
    private List<Dentista> listaDentista;
    
    private final JDialog dialog;
    private final panelCadastroUsuario cadastroUsuario;
    private Usuario usuario;
    
    public ControleCadastroUsuario(JDialog d, panelCadastroUsuario c, Usuario u) {
        this.dialog = d;
        this.cadastroUsuario = c;
        this.usuario = u;
        PreencheComboboxDentista(cadastroUsuario.getComboBoxDentista());
        PreencheCadastroUsuario();
    }
    
    private void PreencheCadastroUsuario() {
        if(usuario != null) {
            if(usuario.getDentista() != null)
                cadastroUsuario.setNomeDentista(usuario.getDentista().getNome());
            cadastroUsuario.setLogin(usuario.getLogin());
            cadastroUsuario.setSenha(usuario.getSenha());
        }
    }
    
    public void AdicionaAtualizaUsuario() {
        if(verificaCampos()) {
            try {
                AtualizaDadosUsuario();
                usuarioDAO.SalvaAtualiza(usuario);
                dialog.dispose();
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro. Verifique se o nome já existe.");
            }
        }
    }
    
    private void AtualizaDadosUsuario() {
        if(usuario == null)
            usuario = new Usuario();
        usuario.setLogin(cadastroUsuario.getLogin());
        if(cadastroUsuario.getPosicaoDentista() >= 0)
            usuario.setDentista(listaDentista.get(cadastroUsuario.getPosicaoDentista()));
        if (cadastroUsuario.senhaAlterada() || usuario.getSenha() == null) {
            usuario.setSenha(Criptografia.hash(cadastroUsuario.getSenha()));
        }
    }
    
    private void PreencheComboboxDentista(JComboBox<String> comboBoxDentista) {
        listaDentista = dentistaDAO.BuscarDentistas();
        for(int i = 0; i < listaDentista.size(); i++) {
            comboBoxDentista.addItem(listaDentista.get(i).getNome());
        } 
    }
    
    public void Fechar() {
        dialog.dispose();
    }
    
    private boolean verificaCampos() {
        boolean check = true;
        if( cadastroUsuario.getLogin().isBlank()) {
            JOptionPane.showMessageDialog(null, "Login vazio.");
            check = false;
        }
        return check;            
    }

}
