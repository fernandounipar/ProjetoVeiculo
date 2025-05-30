package com.hibernate.projetoveiculos.controller;

import com.hibernate.projetoveiculos.dao.PessoaDAO;
import com.hibernate.projetoveiculos.model.Pessoa;
import java.util.List;

/**
 * Controller para Pessoa / Proprietário.
 */
public class PessoaController {

    private final PessoaDAO dao = new PessoaDAO();

    public void salvar(Pessoa pessoa) {
        dao.salvar(pessoa);
    }

    public void atualizar(Pessoa pessoa) {
        dao.atualizar(pessoa);
    }

    public void excluir(Pessoa pessoa) {
        dao.excluir(pessoa);
    }

    public List<Pessoa> listar() {
        return dao.listar();
    }

    public Pessoa buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }
}
