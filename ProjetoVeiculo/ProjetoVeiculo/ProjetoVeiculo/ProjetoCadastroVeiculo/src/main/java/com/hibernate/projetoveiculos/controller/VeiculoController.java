package com.hibernate.projetoveiculos.controller;

import com.hibernate.projetoveiculos.dao.VeiculoDAO;
import com.hibernate.projetoveiculos.model.Veiculo;
import java.util.List;

/**
 * Controller para a entidade Veiculo.
 * Repete o padrão de delegar operações CRUD ao respectivo DAO.
 */
public class VeiculoController {

    private final VeiculoDAO dao = new VeiculoDAO();

    public void salvar(Veiculo veiculo) {
        dao.salvar(veiculo);
    }

    public void atualizar(Veiculo veiculo) {
        dao.atualizar(veiculo);
    }

    public void excluir(Veiculo veiculo) {
        dao.excluir(veiculo);
    }

    public List<Veiculo> listar() {
        return dao.listar();
    }

    public Veiculo buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    public boolean renavamJaExiste(String renavam, Long idAtual) {
        Veiculo v = dao.buscarPorRenavam(renavam);
        return v != null && (idAtual == null || !v.getId().equals(idAtual));
    }

    public boolean chassiJaExiste(String chassi, Long idAtual) {
        Veiculo v = dao.buscarPorChassi(chassi);
        return v != null && (idAtual == null || !v.getId().equals(idAtual));
    }

}
