package com.hibernate.projetoveiculos.controller;

import com.hibernate.projetoveiculos.dao.MotoristaDAO;
import com.hibernate.projetoveiculos.model.Motorista;
import java.util.List;

/**
 * Controller para Motorista / Proprietário.
 */
public class MotoristaController {

    private final MotoristaDAO dao = new MotoristaDAO();

    public void salvar(Motorista motorista) {
        dao.salvar(motorista);
    }

    public void atualizar(Motorista motorista) {
        dao.atualizar(motorista);
    }

    public void excluir(Motorista motorista) {
        dao.excluir(motorista);
    }

    public List<Motorista> listar() {
        return dao.listar();
    }

    public Motorista buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }
}
