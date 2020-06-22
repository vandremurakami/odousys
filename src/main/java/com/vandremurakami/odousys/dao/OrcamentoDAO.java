/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.dao;

import com.vandremurakami.hibernate.HibernateUtil;
import com.vandremurakami.odousys.modelo.Orcamento;
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
public class OrcamentoDAO {
    
    SessionFactory  sessionFactory  = HibernateUtil.getSessionFactory();
    private Session session;
    
    public List<Orcamento> BuscarOrcamentos() throws HibernateException {
        List<Orcamento> listaOrcamento;
        session = sessionFactory.openSession();

        //aqui é descendente para exibir o mais novo no topo
        Query query = session.createQuery("from Orcamento order by codigo desc", Orcamento.class);

        listaOrcamento = query.getResultList();
        session.close();
        
        return listaOrcamento;        
    }
        
    //Utilizado nos filtros de Pagamentos e Orçamentos
    public List<Orcamento> BuscarOrcamentos(Dentista dentista) throws HibernateException {
        List<Orcamento> listaOrcamento;
        session = sessionFactory.openSession();
        
        Query query = session.createQuery("from Orcamento o where o.dentista = :dentista order by codigo desc", Orcamento.class)
                .setParameter("dentista", dentista);

        listaOrcamento = query.getResultList();
        session.close();
        
        return listaOrcamento;        
    }
    

    public List<Orcamento> BuscarOrcamentosExtrato(Dentista dentista) throws HibernateException {
        List<Orcamento> listaOrcamento;
        session = sessionFactory.openSession();
        
        Status statusFinalizado = new StatusDAO().BuscarStatusFinalizado();
        
        Query query = session.createQuery("from Orcamento o where o.dentista = :dentista and o.status = :status order by data asc, codigo asc", Orcamento.class)
                .setParameter("dentista", dentista)
                .setParameter("status", statusFinalizado);

        listaOrcamento = query.getResultList();
        session.close();
        
        return listaOrcamento;        
    }
    
    public List<Orcamento> BuscarOrcamentosExtrato() throws HibernateException {
        List<Orcamento> listaOrcamento;
        session = sessionFactory.openSession();
        
        Status statusFinalizado = new StatusDAO().BuscarStatusFinalizado();
        
        Query query = session.createQuery("from Orcamento o where o.status = :status order by data asc, codigo asc", Orcamento.class)
                .setParameter("status", statusFinalizado);

        listaOrcamento = query.getResultList();
        session.close();
        
        return listaOrcamento;        
    }
    
    //Utilizado para Processamento de pagamentos
    public List<Orcamento> BuscarOrcamentos(Dentista dentista, Status status) throws HibernateException {
        List<Orcamento> listaOrcamento;
        session = sessionFactory.openSession();
        
        //aqui é ascendente para processar do mais antigo para o mais novo
        Query query = session.createQuery("from Orcamento o where o.dentista = :dentista and o.status = :status order by codigo asc", Orcamento.class)
                .setParameter("dentista", dentista)
                .setParameter("status", status);
        
        listaOrcamento = query.getResultList();
        session.close();
        
        return listaOrcamento;        
    }
    
    public List<Orcamento> BuscaOrcamento(int mes, int ano) {
        List<Orcamento> listaOrcamento;
        
        session = sessionFactory.openSession();

        Query query = session.createQuery("from Orcamento o where (EXTRACT(MONTH FROM o.data_envio) = :mes) and (EXTRACT(YEAR FROM o.data_envio) = :ano)", Orcamento.class)
                .setParameter("mes", mes)
                .setParameter("ano", ano);
        
        listaOrcamento = query.getResultList();
        session.close();
        
        return listaOrcamento; 
    }
    
    public void SalvaAtualiza(Orcamento obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(obj);
        transaction.commit();
        session.close();
    }

    public void Deleta(Orcamento obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(obj);
        transaction.commit();
        session.close();
    }

}
