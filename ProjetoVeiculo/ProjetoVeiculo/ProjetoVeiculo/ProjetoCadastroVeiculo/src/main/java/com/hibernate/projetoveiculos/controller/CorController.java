package com.hibernate.projetoveiculos.controller;

import com.hibernate.projetoveiculos.dao.CorDAO;
import com.hibernate.projetoveiculos.model.Cor;
import java.util.List;

/**
 * Controller para Cor.
 */
public class CorController {

    private final CorDAO dao = new CorDAO();

    public void salvar(Cor cor) {
        dao.salvar(cor);
    }

    public void atualizar(Cor cor) {
        dao.atualizar(cor);
    }

    public void excluir(Cor cor) {
        dao.excluir(cor);
    }

    public List<Cor> listar() {
        return dao.listar();
    }

    public Cor buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }
}
