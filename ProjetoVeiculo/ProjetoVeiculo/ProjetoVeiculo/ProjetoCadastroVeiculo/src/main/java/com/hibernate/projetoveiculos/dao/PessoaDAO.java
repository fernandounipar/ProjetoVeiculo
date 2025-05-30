package com.hibernate.projetoveiculos.dao;

import com.hibernate.projetoveiculos.model.Pessoa;
import com.hibernate.projetoveiculos.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PessoaDAO {

    public void salvar(Pessoa m) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.save(m);
            tx.commit();
        }
    }

    public void atualizar(Pessoa m) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.update(m);
            tx.commit();
        }
    }

    public void excluir(Pessoa m) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.delete(m);
            tx.commit();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Pessoa> listar() {
        try (Session s = HibernateUtil.getSession()) {
            return s.createQuery("from Pessoa order by nome").list();
        }
    }

    public Pessoa buscarPorId(Long id) {
        try (Session s = HibernateUtil.getSession()) {
            return s.get(Pessoa.class, id);
        }
    }
}
