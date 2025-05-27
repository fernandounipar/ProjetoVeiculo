package com.hibernate.projetoveiculos.dao;

import com.hibernate.projetoveiculos.model.Motorista;
import com.hibernate.projetoveiculos.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MotoristaDAO {

    public void salvar(Motorista m) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.save(m);
            tx.commit();
        }
    }

    public void atualizar(Motorista m) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.update(m);
            tx.commit();
        }
    }

    public void excluir(Motorista m) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.delete(m);
            tx.commit();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Motorista> listar() {
        try (Session s = HibernateUtil.getSession()) {
            return s.createQuery("from Motorista order by nome").list();
        }
    }

    public Motorista buscarPorId(Long id) {
        try (Session s = HibernateUtil.getSession()) {
            return s.get(Motorista.class, id);
        }
    }
}
