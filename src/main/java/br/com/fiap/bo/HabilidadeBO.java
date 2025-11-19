package br.com.fiap.bo;

import br.com.fiap.dao.HabilidadeDAO;
import br.com.fiap.to.HabilidadeTO;

import java.util.ArrayList;

public class HabilidadeBO {

    private HabilidadeDAO habilidadeDAO;

    public ArrayList<HabilidadeTO> findAll() {
        habilidadeDAO = new HabilidadeDAO();
        return habilidadeDAO.findAll();
    }

    public HabilidadeTO findById(int id) {

        habilidadeDAO = new HabilidadeDAO();

        if (id <= 0) {
            System.out.println("Erro: O ID da habilidade deve ser positivo para encontrá-la.");
            return null;
        }

        return habilidadeDAO.findById(id);
    }

    public HabilidadeTO save(HabilidadeTO habilidade) {

        habilidadeDAO = new HabilidadeDAO();

        if (habilidade.getNomeHabilidade() == null || habilidade.getNomeHabilidade().trim().isEmpty()) {
            System.out.println("Erro: O nome da habilidade não pode ser vazio.");
            return null;
        }

        if (habilidade.getDescricao() == null || habilidade.getDescricao().trim().isEmpty()) {
            System.out.println("Erro: A Descrição não pode ser vazia.");
            return null;
        }

        if (habilidade.getCategoria() == null || habilidade.getCategoria().trim().isEmpty()) {
            System.out.println("Erro: A Categoria não pode ser vazia.");
            return null;
        }

        habilidade.setNomeHabilidade(habilidade.getNomeHabilidade().trim());
        habilidade.setDescricao(habilidade.getDescricao().trim());
        habilidade.setCategoria(habilidade.getCategoria().trim().toUpperCase());


        if (habilidadeDAO.findByNome(habilidade.getNomeHabilidade()) != null) {
            System.out.println("Erro: Já existe uma habilidade com esse nome!");
            return null;
        }

        return habilidadeDAO.save(habilidade);
    }

    public boolean delete(int idHabilidade) {

        habilidadeDAO = new HabilidadeDAO();

        if (idHabilidade <= 0) {
            System.out.println("Erro: ID inválido.");
            return false;
        }

        return habilidadeDAO.delete(idHabilidade);
    }

    public HabilidadeTO update(HabilidadeTO habilidade) {

        habilidadeDAO = new HabilidadeDAO();

        if (habilidade.getIdHabilidade() <= 0) {
            System.out.println("Erro: O ID da habilidade é inválido.");
            return null;
        }


        if (habilidade.getNomeHabilidade() == null || habilidade.getNomeHabilidade().trim().isEmpty()) {
            System.out.println("Erro: O nome da habilidade não pode ser vazio.");
            return null;
        }

        if (habilidade.getDescricao() == null || habilidade.getDescricao().trim().isEmpty()) {
            System.out.println("Erro: A Descrição não pode ser vazia.");
            return null;
        }

        if (habilidade.getCategoria() == null || habilidade.getCategoria().trim().isEmpty()) {
            System.out.println("Erro: A Categoria não pode ser vazia.");
            return null;
        }

        habilidade.setNomeHabilidade(habilidade.getNomeHabilidade().trim());
        habilidade.setDescricao(habilidade.getDescricao().trim());
        habilidade.setCategoria(habilidade.getCategoria().trim().toUpperCase());

        HabilidadeTO existente = habilidadeDAO.findByNome(habilidade.getNomeHabilidade());
        if (existente != null && existente.getIdHabilidade() != habilidade.getIdHabilidade()) {
            System.out.println("Erro: Já existe outra habilidade com esse nome!");
            return null;
        }

        return habilidadeDAO.update(habilidade);
    }
}