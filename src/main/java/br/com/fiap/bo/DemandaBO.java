package br.com.fiap.bo;

import br.com.fiap.dao.DemandaDAO;
import br.com.fiap.to.DemandaTO;

import java.util.ArrayList;

public class DemandaBO {

    private DemandaDAO demandaDAO;

    public ArrayList<DemandaTO> findAll() {
        demandaDAO = new DemandaDAO();
        return demandaDAO.findAll();
    }

    public DemandaTO findById(Integer id) {
        demandaDAO = new DemandaDAO();
        return demandaDAO.findById(id);
    }

    public DemandaTO save(DemandaTO demanda) {

        demandaDAO = new DemandaDAO();

        if (demanda.getIdEmpresa() <= 0) {
            System.out.println("Erro: O id_empresa é inválido!");
            return null;
        }

        if (demanda.getIdHabilidade() <= 0) {
            System.out.println("Erro: O id_habilidade é inválido.");
            return null;
        }

        if (demanda.getPrioridade() < 1 || demanda.getPrioridade() > 5) {
            System.out.println("Erro: A prioridade da demanda tem que ser entre 1 e 5.");
            return null;
        }

        if (demanda.getSenioridade() == null || demanda.getSenioridade().trim().isEmpty()) {
            System.out.println("Erro: A senioridade não pode ser vazia!");
            return null;
        }

        if (demanda.getStatusRemoto() == null ||
                !(demanda.getStatusRemoto().equalsIgnoreCase("S") || demanda.getStatusRemoto().equalsIgnoreCase("N"))) {
            System.out.println("Erro: O statusRemoto deve ser 'S' ou 'N'.");
            return null;
        }

        if (demanda.getQtdVagas() <= 0) {
            System.out.println("Erro: A quantidade de vagas deve ser maior que zero.");
            return null;
        }

        demanda.setSenioridade(demanda.getSenioridade().trim());
        demanda.setStatusRemoto(demanda.getStatusRemoto().trim().toUpperCase());

        return demandaDAO.save(demanda);
    }

    public boolean delete(Integer id) {
        demandaDAO = new DemandaDAO();
        return demandaDAO.delete(id);
    }

    public DemandaTO update(DemandaTO demanda) {

        demandaDAO = new DemandaDAO();

        if (demanda.getIdEmpresa() <= 0) {
            System.out.println("Erro: O id_empresa é inválido.");
            return null;
        }

        if (demanda.getIdHabilidade() <= 0) {
            System.out.println("Erro: O id_habilidade é inválido.");
            return null;
        }

        if (demanda.getPrioridade() < 1 || demanda.getPrioridade() > 5) {
            System.out.println("Erro: A prioridade deve ser entre 1 e 5.");
            return null;
        }

        if (demanda.getSenioridade() == null || demanda.getSenioridade().trim().isEmpty()) {
            System.out.println("Erro: A senioridade não pode ser vazia.");
            return null;
        }


        if (demanda.getStatusRemoto() == null ||
                !(demanda.getStatusRemoto().equalsIgnoreCase("S") || demanda.getStatusRemoto().equalsIgnoreCase("N"))) {
            System.out.println("Erro: O statusRemoto deve ser 'S' ou 'N'.");
            return null;
        }

        if (demanda.getQtdVagas() <= 0) {
            System.out.println("Erro: A quantidade de vagas deve ser maior que zero.");
            return null;
        }

        demanda.setSenioridade(demanda.getSenioridade().trim());
        demanda.setStatusRemoto(demanda.getStatusRemoto().trim().toUpperCase());

        return demandaDAO.update(demanda);
    }

    public ArrayList<DemandaTO> findByEmpresa(Integer idEmpresa) {
        demandaDAO = new DemandaDAO();
        return demandaDAO.findByEmpresa(idEmpresa);
    }
}