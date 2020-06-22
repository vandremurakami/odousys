/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.dao;

import com.vandremurakami.hibernate.HibernateUtil;
import com.vandremurakami.odousys.modelo.Pagamento;
import java.util.List;
import com.vandremurakami.odousys.modelo.Dentista;
import com.vandremurakami.odousys.modelo.Status;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Vandre
 */
public class PagamentoDAO {
    
    SessionFactory  sessionFactory  = HibernateUtil.getSessionFactory();
    private Session session;
    
    public List<Pagamento> BuscarPagamentos() throws HibernateException {
        List<Pagamento> listaPagamento;
        session = sessionFactory.openSession();
        
        //aqui é descendente para exibir o mais recente no topo
        Query query = session.createQuery("from Pagamento order by codigo desc", Pagamento.class);
        
        listaPagamento = query.getResultList();
        session.close();

        return listaPagamento;
    }
    
    public List<Pagamento> BuscarPagamentos(Dentista dentista) throws HibernateException {
        List<Pagamento> listaPagamento;
        session = sessionFactory.openSession();
        
        Query query = session.createQuery("from Pagamento where dentista = :dentista order by codigo desc", Pagamento.class)
                .setParameter("dentista", dentista);
        
        listaPagamento = query.getResultList();
        session.close();

        return listaPagamento;
    }
    
    public List<Pagamento> BuscarPagamentosExtrato(Dentista dentista) throws HibernateException {
        List<Pagamento> listaPagamento;
        session = sessionFactory.openSession();
        
        Status statusFinalizado = new StatusDAO().BuscarStatusFinalizado();
        
        Query query = session.createQuery("from Pagamento where dentista = :dentista and status = :status order by data_pagamento asc, codigo asc", Pagamento.class)
                .setParameter("dentista", dentista)
                .setParameter("status", statusFinalizado);
        
        listaPagamento = query.getResultList();
        session.close();

        return listaPagamento;
    }
    
    public List<Pagamento> BuscarPagamentosExtrato() throws HibernateException {
        List<Pagamento> listaPagamento;
        session = sessionFactory.openSession();
        
        Status statusFinalizado = new StatusDAO().BuscarStatusFinalizado();
        
        Query query = session.createQuery("from Pagamento where status = :status order by data_pagamento asc, codigo asc", Pagamento.class)
                .setParameter("status", statusFinalizado);
        
        listaPagamento = query.getResultList();
        session.close();

        return listaPagamento;
    }
    
    public List<Pagamento> BuscarPagamentos(Dentista dentista, Status status) throws HibernateException {
        List<Pagamento> listaPagamento;
        session = sessionFactory.openSession();
        
        //aqui é ascendente para fazer o processamento do mais antigo para o mais novo
        Query query = session.createQuery("from Pagamento where dentista = :dentista and status = :status order by codigo asc", Pagamento.class)
                .setParameter("dentista", dentista)
                .setParameter("status", status);
        
        listaPagamento = query.getResultList();
        session.close();

        return listaPagamento;
    }
    
    public List<Pagamento> BuscarPagamentos(int mes, int ano) {
    
        List<Pagamento> listaPagamento;
        
        session = sessionFactory.openSession();
        
        Query query = session.createQuery("from Pagamento where "
                + "(EXTRACT(MONTH FROM data_pagamento) = :mes) and "
                + "(EXTRACT(YEAR FROM data_pagamento) = :ano)", Pagamento.class)
                .setParameter("mes", mes)
                .setParameter("ano", ano);
        listaPagamento = query.getResultList();
        session.close();
        
        return listaPagamento; 
    }
    
    public void SalvaAtualiza(Pagamento obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(obj);
        transaction.commit();
        session.close();
    }

    public void Deleta(Pagamento obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(obj);
        transaction.commit();
        session.close();
    }
    
}
