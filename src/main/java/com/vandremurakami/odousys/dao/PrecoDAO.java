/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.dao;

import com.vandremurakami.hibernate.HibernateUtil;
import com.vandremurakami.odousys.modelo.TabelaPreco;
import java.util.List;
import com.vandremurakami.odousys.modelo.Preco;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Vandre
 */
public class PrecoDAO {
    
    SessionFactory  sessionFactory  = HibernateUtil.getSessionFactory();
    private Session session;
    
    
    public List<Preco> BuscarPrecos(TabelaPreco tabelaPreco) throws HibernateException {
        
        List<Preco> listaPreco;
        
        session = sessionFactory.openSession();

        Query query = session.createQuery("from Preco p where p.codigo in "
                + "(select max(p2.codigo) from Preco p2 where p2.tabela_preco = :tabela_preco group by p2.nome) "
                + "order by lower(p.nome)", Preco.class).
                setParameter("tabela_preco", tabelaPreco);
        listaPreco = query.getResultList();
        session.close();
        
        return listaPreco;
        
    }

    //O Preco sempre se salva um novo, nunca se perde o preco antigo por causa das notas antigas
    public void Salva(Preco obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(obj);   
        transaction.commit();
        session.close();
    }

    public void Deleta(Preco obj) throws HibernateException {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(obj);
        transaction.commit();
        session.close();
    }

}
