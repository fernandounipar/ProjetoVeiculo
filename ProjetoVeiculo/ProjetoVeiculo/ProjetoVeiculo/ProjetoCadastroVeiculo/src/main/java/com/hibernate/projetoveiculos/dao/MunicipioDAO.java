package com.hibernate.projetoveiculos.dao;

import com.hibernate.projetoveiculos.model.Municipio;
import com.hibernate.projetoveiculos.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MunicipioDAO {
    public void salvar(Municipio m) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.save(m);
            tx.commit();
        }
    }
    public void atualizar(Municipio m) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.update(m);
            tx.commit();
        }
    }
    public void excluir(Municipio m) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();
            s.delete(m);
            tx.commit();
        }
    }
    @SuppressWarnings("unchecked")
    public List<Municipio> listar() {
        try (Session s = HibernateUtil.getSession()) {
            return s.createQuery("from Municipio order by nome").list();
        }
    }
    public Municipio buscarPorId(Long id) {
        try (Session s = HibernateUtil.getSession()) {
            return s.get(Municipio.class, id);
        }
    }

    // Adicionado: Buscar pelo código do IBGE
    public Municipio buscarPorCodigoIbge(String codigo) {
        try (Session s = HibernateUtil.getSession()) {
            return (Municipio) s.createQuery("from Municipio where codIbge = :codigo")
                    .setParameter("codigo", codigo)
                    .uniqueResult();
        }
    }
}
