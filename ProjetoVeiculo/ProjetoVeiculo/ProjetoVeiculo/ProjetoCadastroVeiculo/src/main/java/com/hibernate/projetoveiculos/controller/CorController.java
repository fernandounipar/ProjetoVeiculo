package com.hibernate.projetoveiculos.controller;

import com.hibernate.projetoveiculos.dao.CorDAO;
import com.hibernate.projetoveiculos.model.Cor;
import java.util.List;

/**
 * Controller para Cor.
 */
public class CorController {

    private final CorDAO dao = new CorDAO();

    /** Salva ou atualiza uma cor. */
    public void salvar(Cor cor) {
        dao.salvarOuAtualizar(cor);
    }

    /** Exclui cor pelo ID. */
    public void excluir(Long id) {
        dao.excluir(id);
    }

    /** Lista todas as cores. */
    public List<Cor> listar() {
        return dao.listar();
    }

    /** Busca uma cor pelo ID. */
    public Cor buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }
}
