package com.hibernate.projetocadastros.controller;

import com.hibernate.projetocadastros.dao.TipoVeiculoDAO;
import com.hibernate.projetocadastros.model.TipoVeiculo;
import java.util.List;

/**
 * Controller para Tipo de Veículo.
 */
public class TipoVeiculoController {

    private final TipoVeiculoDAO dao = new TipoVeiculoDAO();

    public void salvar(TipoVeiculo tipo) {
        dao.salvar(tipo);
    }

    public void atualizar(TipoVeiculo tipo) {
        dao.atualizar(tipo);
    }

    public void excluir(TipoVeiculo tipo) {
        dao.excluir(tipo);
    }

    public List<TipoVeiculo> listar() {
        return dao.listar();
    }

    public TipoVeiculo buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }
}
