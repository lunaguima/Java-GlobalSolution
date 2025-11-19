package br.com.fiap.bo;

import br.com.fiap.dao.CursoDAO;
import br.com.fiap.to.CursoTO;

import java.util.ArrayList;

public class CursoBO {

    private CursoDAO cursoDAO;

    public ArrayList<CursoTO> findAll() {
        cursoDAO = new CursoDAO();
        return cursoDAO.findAll();
    }

    public CursoTO findById(Integer idCurso) {

        cursoDAO = new CursoDAO();

        if (idCurso == null || idCurso <= 0) {
            System.out.println("Erro: O ID de curso inválido.");
            return null;
        }

        return cursoDAO.findById(idCurso);
    }


    public CursoTO save(CursoTO curso) {

        cursoDAO = new CursoDAO();

        if (curso.getNomeDoCurso() == null || curso.getNomeDoCurso().trim().isEmpty()) {
            System.out.println("Erro: O nome do curso não pode ser vazio.");
            return null;
        }

        if (curso.getPlataforma() == null || curso.getPlataforma().trim().isEmpty()) {
            System.out.println("Erro: A Plataforma não pode ser vazia.");
            return null;
        }

        if (curso.getLinkExterno() == null || curso.getLinkExterno().trim().isEmpty()) {
            System.out.println("Erro: O Link externo não pode ser vazio.");
            return null;
        }

        if (!curso.getLinkExterno().startsWith("http")) {
            System.out.println("Erro: O link do curso deve começar com http ou https.");
            return null;
        }

        if (curso.getCargaHoraria() < 1) {
            System.out.println("Erro: A carga horária deve ser maior que 0.");
            return null;
        }


        curso.setNomeDoCurso(curso.getNomeDoCurso().trim());
        curso.setPlataforma(curso.getPlataforma().trim());
        curso.setLinkExterno(curso.getLinkExterno().trim());


        ArrayList<CursoTO> lista = cursoDAO.findAll();
        for (CursoTO c : lista) {
            if (c.getNomeDoCurso().equalsIgnoreCase(curso.getNomeDoCurso())) {
                System.out.println("Erro: Já existe um curso cadastrado com esse nome!");
                return null;
            }
        }

        return cursoDAO.save(curso);
    }

    public CursoTO update(CursoTO curso) {

        cursoDAO = new CursoDAO();

        if (curso.getIdCurso() <= 0) {
            System.out.println("Erro: O ID de curso é inválido!");
            return null;
        }

        if (curso.getNomeDoCurso() == null || curso.getNomeDoCurso().trim().isEmpty()) {
            System.out.println("Erro: O nome do curso não pode ser vazio.");
            return null;
        }

        if (curso.getPlataforma() == null || curso.getPlataforma().trim().isEmpty()) {
            System.out.println("Erro: A Plataforma não pode ser vazia!");
            return null;
        }

        if (curso.getLinkExterno() == null || curso.getLinkExterno().trim().isEmpty()) {
            System.out.println("Erro: O Link externo não pode ser vazio!");
            return null;
        }

        if (!curso.getLinkExterno().startsWith("http")) {
            System.out.println("Erro: O link do curso deve começar com http ou https!");
            return null;
        }

        if (curso.getCargaHoraria() < 1) {
            System.out.println("Erro: A carga horária deve ser maior que 0.");
            return null;
        }

        curso.setNomeDoCurso(curso.getNomeDoCurso().trim());
        curso.setPlataforma(curso.getPlataforma().trim());
        curso.setLinkExterno(curso.getLinkExterno().trim());

        ArrayList<CursoTO> lista = cursoDAO.findAll();
        for (CursoTO c : lista) {
            if (c.getNomeDoCurso().equalsIgnoreCase(curso.getNomeDoCurso()) && c.getIdCurso() != curso.getIdCurso()) {
                System.out.println("Erro: Já existe outro curso com esse nome!");
                return null;
            }
        }

        return cursoDAO.update(curso);
    }

    public boolean delete(Integer idCurso) {

        cursoDAO = new CursoDAO();

        if (idCurso == null || idCurso <= 0) {
            System.out.println("Erro: O ID inválido para excluir o curso!");
            return false;
        }

        return cursoDAO.delete(idCurso);
    }
}
