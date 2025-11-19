package br.com.fiap.bo;

import br.com.fiap.dao.CursoDAO;
import br.com.fiap.dao.CursoHabilidadeDAO;
import br.com.fiap.dao.HabilidadeDAO;
import br.com.fiap.to.CursoHabilidadeTO;

import java.util.ArrayList;

public class CursoHabilidadeBO {

    private CursoHabilidadeDAO cursoHabilidadeDAO;
    private CursoDAO cursoDAO;
    private HabilidadeDAO habilidadeDAO;

    public ArrayList<CursoHabilidadeTO> findAll() {
        cursoHabilidadeDAO = new CursoHabilidadeDAO();
        return cursoHabilidadeDAO.findAll();
    }

    public CursoHabilidadeTO findById(Integer idCursoHabilidade) {
        cursoHabilidadeDAO = new CursoHabilidadeDAO();
        return cursoHabilidadeDAO.findById(idCursoHabilidade);
    }

    public CursoHabilidadeTO save(CursoHabilidadeTO cursoHabilidade) {

        cursoHabilidadeDAO = new CursoHabilidadeDAO();
        cursoDAO = new CursoDAO();
        habilidadeDAO = new HabilidadeDAO();

        if (cursoDAO.findById(cursoHabilidade.getIdCurso()) == null) {
            System.out.println("Erro: O Curso informado não existe!");
            return null;
        }

        if (habilidadeDAO.findById(cursoHabilidade.getIdHabilidade()) == null) {
            System.out.println("Erro: A Habilidade informada não existe!");
            return null;
        }

        if (cursoHabilidade.getStatusFocoPrincipal() != null) {
            String foco = cursoHabilidade.getStatusFocoPrincipal().trim().toUpperCase();
            if (!foco.equals("S") && !foco.equals("N")) {
                System.out.println("Erro: O statusFocoPrincipal deve ser 'S' ou 'N'!");
                return null;
            }
            cursoHabilidade.setStatusFocoPrincipal(foco);
        }

        if (cursoHabilidade.getModulo() == null || cursoHabilidade.getModulo().trim().isEmpty()) {
            System.out.println("Erro: O módulo não pode ser vazio!");
            return null;
        }

        if (cursoHabilidade.getNivelEnsinado() == null || cursoHabilidade.getNivelEnsinado().trim().isEmpty()) {
            System.out.println("Erro: O nível ensinado não pode ser vazio!");
            return null;
        }

        if (cursoHabilidade.getTempoDedicado() <= 0) {
            System.out.println("Erro: O tempo dedicado deve ser maior que 0!");
            return null;
        }

        return cursoHabilidadeDAO.save(cursoHabilidade);
    }

    public CursoHabilidadeTO update(CursoHabilidadeTO cursoHabilidade) {

        cursoHabilidadeDAO = new CursoHabilidadeDAO();
        cursoDAO = new CursoDAO();
        habilidadeDAO = new HabilidadeDAO();


        if (cursoDAO.findById(cursoHabilidade.getIdCurso()) == null) {
            System.out.println("Erro: O Curso informado não existe!");
            return null;
        }

        if (habilidadeDAO.findById(cursoHabilidade.getIdHabilidade()) == null) {
            System.out.println("Erro: A Habilidade informada não existe!");
            return null;
        }

        if (cursoHabilidade.getStatusFocoPrincipal() != null) {
            String foco = cursoHabilidade.getStatusFocoPrincipal().trim().toUpperCase();
            if (!foco.equals("S") && !foco.equals("N")) {
                System.out.println("Erro: O statusFocoPrincipal deve ser 'S' ou 'N'!");
                return null;
            }
            cursoHabilidade.setStatusFocoPrincipal(foco);
        }

        if (cursoHabilidade.getModulo() == null || cursoHabilidade.getModulo().trim().isEmpty()) {
            System.out.println("Erro: O módulo não pode ser vazio!");
            return null;
        }

        if (cursoHabilidade.getNivelEnsinado() == null || cursoHabilidade.getNivelEnsinado().trim().isEmpty()) {
            System.out.println("Erro: O nível ensinado não pode ser vazio.");
            return null;
        }

        if (cursoHabilidade.getTempoDedicado() <= 0) {
            System.out.println("Erro: O tempo dedicado deve ser maior que 0!");
            return null;
        }

        return cursoHabilidadeDAO.update(cursoHabilidade);
    }

    public boolean delete(Integer idCursoHabilidade) {
        cursoHabilidadeDAO = new CursoHabilidadeDAO();
        return cursoHabilidadeDAO.delete(idCursoHabilidade);
    }
}