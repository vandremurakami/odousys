/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.dao;

import com.vandremurakami.hibernate.HibernateUtil;
import com.vandremurakami.odousys.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author vandre
 */
public class UsuarioDAO {

    SessionFactory  sessionFactory  = HibernateUtil.getSessionFactory();
    private Session session;
    
    public List<Usuario> BuscaUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();

        session = sessionFactory.openSession();

        Query query = session.createQuery("from Usuario order by login", Usuario.class);
        listaUsuarios.addAll(query.getResultList());
        session.close();

        return listaUsuarios;
    }
    
    public Usuario BuscaUsuario(String login) {

        Usuario usuario = null;
        
        session = sessionFactory.openSession();

        Query query = session.createQuery("from Usuario where login = :login", Usuario.class)
                .setParameter("login", login);
        
        if (!query.getResultList().isEmpty())
            usuario = (Usuario)query.getSingleResult();
        
        session.close();

        return usuario;
    }
    
    public void SalvaAtualiza(Usuario obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(obj);   
        transaction.commit();
        session.close();
    }

    public void Deleta(Usuario obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(obj);
        transaction.commit();
        session.close();
    }

}
