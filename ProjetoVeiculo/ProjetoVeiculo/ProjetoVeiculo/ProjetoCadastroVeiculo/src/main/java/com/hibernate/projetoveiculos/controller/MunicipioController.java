package com.hibernate.projetoveiculos.controller;

import com.hibernate.projetoveiculos.dao.MunicipioDAO;
import com.hibernate.projetoveiculos.model.Municipio;
import java.util.List;

/**
 * Controller para Município / Cidade.
 */
public class MunicipioController {

    private final MunicipioDAO dao = new MunicipioDAO();

    public void salvar(Municipio municipio) {
        dao.salvar(municipio);
    }

    public void atualizar(Municipio municipio) {
        dao.atualizar(municipio);
    }

    public void excluir(Municipio municipio) {
        dao.excluir(municipio);
    }

    public List<Municipio> listar() {
        return dao.listar();
    }

    public Municipio buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    // Implementação correta:
    public Municipio buscarPorCodigoIbge(String codigo) {
        return dao.buscarPorCodigoIbge(codigo);
    }
}
