/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.dao;

import com.vandremurakami.hibernate.HibernateUtil;
import java.time.LocalDate;
import java.util.List;
import com.vandremurakami.odousys.modelo.Agenda;
import com.vandremurakami.odousys.modelo.HorariosAgenda;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Vandre
 */
public class AgendaDAO {
    
    SessionFactory  sessionFactory  = HibernateUtil.getSessionFactory();
    private Session session = null;

    public List<Agenda> BuscarAgenda(LocalDate data) throws HibernateException {
        List<Agenda> listaAgenda;
        session = sessionFactory.openSession();
        
        Query query = session.createQuery("from Agenda where data = :data order by hora", Agenda.class)
                .setParameter("data", data);
        
        listaAgenda = query.getResultList();
        session.close();
        
        return listaAgenda;      
    } 
    
    public List<HorariosAgenda> BuscarHorarios() throws HibernateException {
        List<HorariosAgenda> listaHorariosAgenda;
        session = sessionFactory.openSession();

        Query query = session.createQuery("from HorariosAgenda order by hora", HorariosAgenda.class);
        
        listaHorariosAgenda = query.getResultList();
        session.close();
        
        return listaHorariosAgenda;
    }

    public void SalvaAtualiza(Agenda obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(obj);
        transaction.commit();
        session.close();
    }

    public void Deleta(Agenda obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(obj);
        transaction.commit();
        session.close();
    }

}
