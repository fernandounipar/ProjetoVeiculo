package com.hibernate.projetoveiculos.util;

import com.hibernate.projetoveiculos.model.Cor;
import com.hibernate.projetoveiculos.model.Usuario;
import com.hibernate.projetoveiculos.model.Veiculo;
import com.hibernate.projetoveiculos.model.Pessoa;
import com.hibernate.projetoveiculos.model.Municipio;
import com.hibernate.projetoveiculos.model.TipoVeiculo;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            Properties settings = new Properties();
            settings.put(AvailableSettings.JAKARTA_JDBC_DRIVER, "org.postgresql.Driver");
            settings.put(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:postgresql://localhost:5432/veiculo");
            settings.put(AvailableSettings.JAKARTA_JDBC_USER, "postgres");
            settings.put(AvailableSettings.JAKARTA_JDBC_PASSWORD, "1234");
            settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            settings.put(AvailableSettings.HBM2DDL_AUTO, "update");
            settings.put(AvailableSettings.SHOW_SQL, "true");
            settings.put(AvailableSettings.FORMAT_SQL, "true");

            Configuration configuration = new Configuration();
            configuration.setProperties(settings);
            configuration.addAnnotatedClass(Cor.class);
            configuration.addAnnotatedClass(Usuario.class);
            configuration.addAnnotatedClass(Pessoa.class);
            configuration.addAnnotatedClass(Municipio.class);
            configuration.addAnnotatedClass(TipoVeiculo.class);
            configuration.addAnnotatedClass(Veiculo.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            System.err.println("Erro ao criar SessionFactory: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
