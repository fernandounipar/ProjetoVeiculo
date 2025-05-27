/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hibernate.projetocadastros.dao;

import com.hibernate.projetocadastros.model.Usuario;
import com.hibernate.projetocadastros.util.HibernateUtil;
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
