/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.odousys.dao;

import com.vandremurakami.hibernate.HibernateUtil;
import java.util.List;
import com.vandremurakami.odousys.modelo.TipoPagamento;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author Vandre
 */
public class TipoPagamentoDAO{
    
    public final static int TIPO_PAGAMENTO_CHEQUE = 2;
    public final static int TIPO_PAGAMENTO_DINHEIRO = 1;
    
    SessionFactory  sessionFactory  = HibernateUtil.getSessionFactory();
    private Session session;

    public List<TipoPagamento> BuscarTipoPagamento() throws HibernateException {
        List<TipoPagamento> listaTipoPagamento;
        session = sessionFactory.openSession();
        
        Query query = session.createQuery("from TipoPagamento order by nome asc", TipoPagamento.class);
        
        listaTipoPagamento = query.getResultList();
        session.close();
        
        return listaTipoPagamento; 
    }
  

}
