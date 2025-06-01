package com.hibernate.projetoveiculos.dao;

import com.hibernate.projetoveiculos.model.Veiculo;
import com.hibernate.projetoveiculos.model.Municipio;
import com.hibernate.projetoveiculos.model.Cor;
import com.hibernate.projetoveiculos.model.TipoVeiculo;
import com.hibernate.projetoveiculos.model.Pessoa;
import com.hibernate.projetoveiculos.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * DAO para Veiculo.
 * Atualizado para realizar o merge das entidades relacionadas antes de
 * salvar/atualizar.
 */
public class VeiculoDAO {

    public void salvar(Veiculo v) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();

            // Realiza o merge das entidades relacionadas para garantir que estejam
            // atachadas na sessão
            if (v.getMunicipio() != null)
                v.setMunicipio((Municipio) s.merge(v.getMunicipio()));
            if (v.getCor() != null)
                v.setCor((Cor) s.merge(v.getCor()));
            if (v.getTipoVeiculo() != null)
                v.setTipoVeiculo((TipoVeiculo) s.merge(v.getTipoVeiculo()));
            if (v.getProprietario() != null)
                v.setProprietario((Pessoa) s.merge(v.getProprietario()));
            if (v.getMotorista() != null)
                v.setMotorista((Pessoa) s.merge(v.getMotorista()));

            s.save(v);
            tx.commit();
        }
    }

    public void atualizar(Veiculo v) {
        try (Session s = HibernateUtil.getSession()) {
            Transaction tx = s.beginTransaction();

            // Realiza o merge das entidades relacionadas para garantir que estejam
            // atachadas na sessão
            if (v.getMunicipio() != null)
                v.setMunicipio((Municipio) s.merge(v.getMunicipio()));
            if (v.getCor() != null)
                v.setCor((Cor) s.merge(v.getCor()));
            if (v.getTipoVeiculo() != null)
                v.setTipoVeiculo((TipoVeiculo) s.merge(v.getTipoVeiculo()));
            if (v.getProprietario() != null)
                v.setProprietario((Pessoa) s.merge(v.getProprietario()));
            if (v.getMotorista() != null)
                v.setMotorista((Pessoa) s.merge(v.getMotorista()));

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
            return s.createQuery(
                    "SELECT v FROM Veiculo v " +
                            "LEFT JOIN FETCH v.municipio " +
                            "LEFT JOIN FETCH v.cor " +
                            "LEFT JOIN FETCH v.tipoVeiculo " +
                            "LEFT JOIN FETCH v.proprietario " +
                            "LEFT JOIN FETCH v.motorista " +
                            "ORDER BY v.placa")
                    .list();
        }
    }

    public Veiculo buscarPorId(Long id) {
        try (Session s = HibernateUtil.getSession()) {
            return s.get(Veiculo.class, id);
        }
    }

    public Veiculo buscarPorRenavam(String renavam) {
        try (Session s = HibernateUtil.getSession()) {
            return (Veiculo) s.createQuery("from Veiculo where renavam = :renavam")
                    .setParameter("renavam", renavam)
                    .uniqueResult();
        }
    }

    public Veiculo buscarPorChassi(String chassi) {
        try (Session s = HibernateUtil.getSession()) {
            return (Veiculo) s.createQuery("from Veiculo where chassi = :chassi")
                    .setParameter("chassi", chassi)
                    .uniqueResult();
        }
    }

}
