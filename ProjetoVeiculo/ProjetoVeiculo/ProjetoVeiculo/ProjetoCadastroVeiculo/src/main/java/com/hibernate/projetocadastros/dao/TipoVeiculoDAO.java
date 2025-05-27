package com.hibernate.projetocadastros.dao;

import com.hibernate.projetocadastros.model.TipoVeiculo;
import com.hibernate.projetocadastros.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TipoVeiculoDAO {
    public void salvar(TipoVeiculo t) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.save(t);
            tx.commit();
        }
    }
    public void atualizar(TipoVeiculo t) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.update(t);
            tx.commit();
        }
    }
    public void excluir(TipoVeiculo t) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.delete(t);
            tx.commit();
        }
    }
    @SuppressWarnings("unchecked")
    public List<TipoVeiculo> listar() {
        try (Session s = HibernateUtil.getSession()) {
            return s.createQuery("from TipoVeiculo order by descricao").list();
        }
    }
    public TipoVeiculo buscarPorId(Long id) {
        try (Session s = HibernateUtil.getSession()) {
            return s.get(TipoVeiculo.class, id);
        }
    }
}
