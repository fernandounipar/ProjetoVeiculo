package com.hibernate.projetoveiculos.dao;

import com.hibernate.projetoveiculos.model.Veiculo;
import com.hibernate.projetoveiculos.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * DAO para Veiculo.
 * Estrutura copiada do ClienteDAO original: métodos CRUD simples
 * usando Session/Transaction e HQL genérico.
 */
public class VeiculoDAO {

    public void salvar(Veiculo v) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.save(v);
            tx.commit();
        }
    }

    public void atualizar(Veiculo v) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.update(v);
            tx.commit();
        }
    }

    public void excluir(Veiculo v) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.delete(v);
            tx.commit();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Veiculo> listar() {
        try (Session s = HibernateUtil.getSession()) {
            return s.createQuery("from Veiculo order by placa").list();
        }
    }

    public Veiculo buscarPorId(Long id) {
        try (Session s = HibernateUtil.getSession()) {
            return s.get(Veiculo.class, id);
        }
    }
}
