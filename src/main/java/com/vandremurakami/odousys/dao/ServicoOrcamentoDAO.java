/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.dao;

import com.vandremurakami.hibernate.HibernateUtil;
import com.vandremurakami.odousys.modelo.ServicoOrcamento;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Vandre
 */
public class ServicoOrcamentoDAO {
    
    SessionFactory  sessionFactory  = HibernateUtil.getSessionFactory();
    private Session session;
    
    public void SalvaAtualiza(ServicoOrcamento obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(obj);
        transaction.commit();
        session.close();
    }

    public void Deleta(ServicoOrcamento obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(obj);
        transaction.commit();
        session.close();
    }

}
