/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.dao;

import com.vandremurakami.hibernate.HibernateUtil;
import java.util.List;
import com.vandremurakami.odousys.modelo.Status;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author Vandre
 */
public class StatusDAO {
    
    public final static int STATUS_ABERTO = 1;
    public final static int STATUS_CANCELADO = 2;
    public final static int STATUS_APROVADO = 3;
    
    
    SessionFactory  sessionFactory  = HibernateUtil.getSessionFactory();
    private Session session;

    public Status BuscarStatusAberto() throws HibernateException {

        session = sessionFactory.openSession();
        
        Query query = session.createQuery("from Status where codigo = :codigo", Status.class)
                .setParameter("codigo", STATUS_ABERTO);
        
        Status status = (Status)query.getSingleResult();
        session.close();
        
        return status;
    }
    
    public Status BuscarStatusCancelado() throws HibernateException {

        session = sessionFactory.openSession();
        
        Query query = session.createQuery("from Status where codigo = :codigo", Status.class)
                .setParameter("codigo", STATUS_CANCELADO);
        
        Status status = (Status)query.getSingleResult();
        session.close();
        
        return status;
    }
    
    public Status BuscarStatusAprovado() throws HibernateException {

        session = sessionFactory.openSession();
        
        Query query = session.createQuery("from Status where codigo = :codigo", Status.class)
                .setParameter("codigo", STATUS_APROVADO);
        
        Status status = (Status)query.getSingleResult();
        session.close();
        
        return status;
    }   

    
    public List<Status> BuscarStatus() throws HibernateException {
        List<Status> listaStatus;
        session = sessionFactory.openSession();
        
        Query query = session.createQuery("from Status order by nome", Status.class);         
        
        listaStatus = query.getResultList();
        session.close();
        
        return listaStatus; 
    }
    
}
