package com.hibernate.projetoveiculos.dao;

import com.hibernate.projetoveiculos.model.Cor;
import com.hibernate.projetoveiculos.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CorDAO {
    public void salvar(Cor c) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.save(c);
            tx.commit();
        }
    }
    public void atualizar(Cor c) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.update(c);
            tx.commit();
        }
    }
    public void excluir(Cor c) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.delete(c);
            tx.commit();
        }
    }
    @SuppressWarnings("unchecked")
    public List<Cor> listar() {
        try (Session s = HibernateUtil.getSession()) {
            return s.createQuery("from Cor order by descricao").list();
        }
    }
    public Cor buscarPorId(Long id) {
        try (Session s = HibernateUtil.getSession()) {
            return s.get(Cor.class, id);
        }
    }
}
