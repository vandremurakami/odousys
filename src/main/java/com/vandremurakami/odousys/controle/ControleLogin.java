/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.controle;

import com.vandremurakami.odousys.dao.UsuarioDAO;
import com.vandremurakami.odousys.modelo.Usuario;
import com.vandremurakami.util.Criptografia;

/**
 *
 * @author vandre
 */
public class ControleLogin {
    
    final private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public ControleLogin() {
    
    }
    
    public boolean validaUsuarioSenha(String login, String senha) {
        Usuario usuario = usuarioDAO.BuscaUsuario(login);
        
        if (usuario == null)
            return false;
        
        return Criptografia.validaHash(senha, usuario.getSenha());
    
    }
    
}
