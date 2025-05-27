package com.hibernate.projetocadastros.controller;

import com.hibernate.projetocadastros.dao.VeiculoDAO;
import com.hibernate.projetocadastros.model.Veiculo;
import java.util.List;

/**
 * Controller para a entidade Veiculo.
 * Repete o mesmo padrão do ClienteController original: delega as operações
 * CRUD ao respectivo DAO, sem regras de negócio adicionais.
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
}
