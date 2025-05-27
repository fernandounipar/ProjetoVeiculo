package com.hibernate.projetoveiculos.dao;

import com.hibernate.projetoveiculos.model.Usuario;
import com.hibernate.projetoveiculos.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Darlon
 */
public class UsuarioDAO {

    public Usuario login(String usuario, String senha) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                    "FROM Usuario WHERE usuario = :usuario AND senha = :senha",
                    Usuario.class)
                    .setParameter("usuario", usuario)
                    .setParameter("senha", senha)
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

}
