/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.dao;

import com.vandremurakami.hibernate.HibernateUtil;
import com.vandremurakami.odousys.modelo.Dentista;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Vandre
 */
public class DentistaDAO {

    SessionFactory  sessionFactory  = HibernateUtil.getSessionFactory();
    private Session session;
    
    public List<Dentista> BuscarDentistas() throws HibernateException {
        List<Dentista> listaDentista;
        session = sessionFactory.openSession();

        Query query = session.createQuery("from Dentista order by lower(nome)", Dentista.class);

        listaDentista = query.getResultList();
        session.close();
        
        return listaDentista;
    }
    
    public void SalvaAtualiza(Dentista obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(obj);   
        transaction.commit();
        session.close();
    }

    public void Deleta(Dentista obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(obj);
        transaction.commit();
        session.close();
    }

}
