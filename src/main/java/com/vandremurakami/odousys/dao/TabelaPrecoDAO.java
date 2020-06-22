/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.dao;

import com.vandremurakami.hibernate.HibernateUtil;
import com.vandremurakami.odousys.modelo.TabelaPreco;
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
public class TabelaPrecoDAO{
    SessionFactory  sessionFactory  = HibernateUtil.getSessionFactory();
    private Session session;

    public List<TabelaPreco> BuscarTabelaPreco() throws HibernateException {
        List<TabelaPreco> listaTabelaPreco;
        session = sessionFactory.openSession();
        
        Query query = session.createQuery("from TabelaPreco order by codigo desc", TabelaPreco.class);

        listaTabelaPreco = query.getResultList();
        session.close();
        
        return listaTabelaPreco; 
    }
    
    public TabelaPreco BuscarTabelaPrecoAtivo() throws HibernateException {
        session = sessionFactory.openSession();
        
         Query query = session.createQuery("from TabelaPreco where ativo = true order by codigo desc", TabelaPreco.class);
         
         TabelaPreco tabela;
                 
        if (!query.getResultList().isEmpty()) {
            tabela = (TabelaPreco) query.getResultList().get(0);
        }
        else {
            tabela = null;
        }
        
        return tabela;
    }
    
    public void SalvaAtualiza(TabelaPreco obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        //só pode haver uma tabela de preço ativo
        if(obj.getAtivo()) {
            Query query = session.createNativeQuery("update tabela_preco set ativo = false");
            query.executeUpdate();
        }
        
        session.saveOrUpdate(obj);   
        transaction.commit();
        session.close();
    }

    public void Deleta(TabelaPreco obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(obj);
        transaction.commit();
        session.close();
    }

}
