package com.hibernate.projetoveiculos.dao;

import com.hibernate.projetoveiculos.model.Cor;
import com.hibernate.projetoveiculos.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CorDAO {
    /** Salva ou atualiza conforme ID presente ou não. */
    public void salvarOuAtualizar(Cor c) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSession()) {
            tx = s.beginTransaction();
            if (c.getId() == null) {
                s.save(c); // Novo
            } else {
                s.update(c); // Edita existente
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    /** Exclui cor por objeto. */
    public void excluir(Cor c) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSession()) {
            tx = s.beginTransaction();
            s.delete(c);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    /** Exclui cor pelo ID (facilita uso na tela). */
    public void excluir(Long id) {
        Cor c = buscarPorId(id);
        if (c != null) {
            excluir(c);
        }
    }

    /** Lista todas as cores ordenadas por descrição. */
    @SuppressWarnings("unchecked")
    public List<Cor> listar() {
        try (Session s = HibernateUtil.getSession()) {
            return s.createQuery("from Cor order by descricao").list();
        }
    }

    /** Busca uma cor pelo ID. */
    public Cor buscarPorId(Long id) {
        try (Session s = HibernateUtil.getSession()) {
            return s.get(Cor.class, id);
        }
    }
}
