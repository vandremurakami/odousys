/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.dao;

import com.vandremurakami.hibernate.HibernateUtil;
import com.vandremurakami.odousys.modelo.Paciente;
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
public class PacienteDAO {

    SessionFactory  sessionFactory  = HibernateUtil.getSessionFactory();
    private Session session;
    
    public List<Paciente> BuscarPacientes() throws HibernateException {
        List<Paciente> listaPaciente;
        session = sessionFactory.openSession();

        Query query = session.createQuery("from Paciente order by lower(nome)", Paciente.class);

        listaPaciente = query.getResultList();
        session.close();
        
        return listaPaciente;
    }
    
    public void SalvaAtualiza(Paciente obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(obj);   
        transaction.commit();
        session.close();
    }

    public void Deleta(Paciente obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(obj);
        transaction.commit();
        session.close();
    }

}
