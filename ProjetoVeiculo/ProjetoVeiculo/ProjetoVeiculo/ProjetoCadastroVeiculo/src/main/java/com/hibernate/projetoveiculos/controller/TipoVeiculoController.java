package com.hibernate.projetoveiculos.controller;

import com.hibernate.projetoveiculos.dao.TipoVeiculoDAO;
import com.hibernate.projetoveiculos.model.TipoVeiculo;
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
