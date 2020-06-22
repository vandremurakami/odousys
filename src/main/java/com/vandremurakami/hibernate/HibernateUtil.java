/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vandremurakami.hibernate;

import com.vandremurakami.odousys.modelo.Agenda;
import com.vandremurakami.odousys.modelo.Dentista;
import com.vandremurakami.odousys.modelo.HorariosAgenda;
import com.vandremurakami.odousys.modelo.Orcamento;
import com.vandremurakami.odousys.modelo.Paciente;
import com.vandremurakami.odousys.modelo.Pagamento;
import com.vandremurakami.odousys.modelo.Preco;
import com.vandremurakami.odousys.modelo.ServicoOrcamento;
import com.vandremurakami.odousys.modelo.Status;
import com.vandremurakami.odousys.modelo.TabelaPreco;
import com.vandremurakami.odousys.modelo.TipoPagamento;
import com.vandremurakami.odousys.modelo.Usuario;
import com.vandremurakami.util.Criptografia;
import java.io.File;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Vandre
 */
public class HibernateUtil {

//    private static final SessionFactory sessionFactory;
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static Configuration config;
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static String getLogin() throws Exception {
        byte username[] = new byte[]{ 36,110,78,-45,55,-69,2,55,-55,-32,-17,77,-103,-12,110,-14,-44,98,-111,51,-106,96,115,-77,11,124,38,-105,-51,-85,-44,-106};
        return Criptografia.decrypt(username);
    }
    
    public static String getPassword() throws Exception {
        byte password[] = new byte[]{ 84,-111,-68,56,-71,-32,85,84,-42,-67,95,50,-113,37,-113,-78,-86,-14,93,84,98,-113,-45,-8,-37,-91,12,-118,-27,-65,90,6};
        return Criptografia.decrypt(password);
    }
    
    public static String getDatabase() {
        String url = config.getProperty("hibernate.connection.url");
        return url.substring(url.lastIndexOf("/")+1).trim();
    }
    
    public static String getHost() {
        String url = config.getProperty("hibernate.connection.url");
        return url.substring(url.lastIndexOf("//")+2, url.lastIndexOf(":")).trim();
    }

    private static SessionFactory buildSessionFactory() {
                
        try {
            config = new Configuration().configure(new File("config/hibernate.cfg.xml"));
            config.setProperty("hibernate.connection.username", getLogin());
            config.setProperty("hibernate.connection.password", getPassword());
            config.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            config.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            config.addAnnotatedClass(Agenda.class);
            config.addAnnotatedClass(Dentista.class);
            config.addAnnotatedClass(Paciente.class);
            config.addAnnotatedClass(HorariosAgenda.class);
            config.addAnnotatedClass(Orcamento.class);
            config.addAnnotatedClass(Pagamento.class);
            config.addAnnotatedClass(Preco.class);
            config.addAnnotatedClass(ServicoOrcamento.class);
            config.addAnnotatedClass(Status.class);
            config.addAnnotatedClass(TabelaPreco.class);
            config.addAnnotatedClass(TipoPagamento.class);
            config.addAnnotatedClass(Usuario.class);

            
            return config.buildSessionFactory(); 
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static void shutdown() {
    	// Close caches and connection pools
    	getSessionFactory().close();
    }
}

