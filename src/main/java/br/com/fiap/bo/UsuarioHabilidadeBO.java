package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioHabilidadeDAO;
import br.com.fiap.dao.HabilidadeDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioHabilidadeTO;

import java.util.ArrayList;

public class UsuarioHabilidadeBO {

    private UsuarioHabilidadeDAO usuarioHabilidadeDAO;
    private UsuarioDAO usuarioDAO;
    private HabilidadeDAO habilidadeDAO;

    public ArrayList<UsuarioHabilidadeTO> findByUsuario(int idUsuario) {

        usuarioHabilidadeDAO = new UsuarioHabilidadeDAO();

        if (idUsuario <= 0) {
            System.out.println("Erro: O ID do usuário é inválido.");
            return null;
        }

        return usuarioHabilidadeDAO.findByUsuario(idUsuario);
    }

    public UsuarioHabilidadeTO findById(int idRelacao) {

        usuarioHabilidadeDAO = new UsuarioHabilidadeDAO();

        if (idRelacao <= 0) {
            System.out.println("Erro: O ID da relação é inválido.");
            return null;
        }

        return usuarioHabilidadeDAO.findById(idRelacao);
    }

    public UsuarioHabilidadeTO save(UsuarioHabilidadeTO usuarioHabilidade) {

        usuarioHabilidadeDAO = new UsuarioHabilidadeDAO();
        usuarioDAO = new UsuarioDAO();
        habilidadeDAO = new HabilidadeDAO();

        if (usuarioHabilidade.getIdUsuario() <= 0) {
            System.out.println("Erro: O ID do usuário é inválido.");
            return null;
        }

        if (usuarioDAO.findById(usuarioHabilidade.getIdUsuario()) == null) {
            System.out.println("Erro: O Usuário não foi encontrado!");
            return null;
        }

        if (usuarioHabilidade.getIdHabilidade() <= 0) {
            System.out.println("Erro: O ID da habilidade é inválido.");
            return null;
        }

        if (habilidadeDAO.findById(usuarioHabilidade.getIdHabilidade()) == null) {
            System.out.println("Erro: A Habilidade não foi encontrada.");
            return null;
        }

        if (usuarioHabilidade.getStatusRelacao() == null || usuarioHabilidade.getStatusRelacao().trim().isEmpty()) {
            System.out.println("Erro: O status da relação não pode ser vazio.");
            return null;
        }

        if (usuarioHabilidade.getNivel() < 0 || usuarioHabilidade.getNivel() > 10) {
            System.out.println("Erro: O nível deve ser entre 0 e 10.");
            return null;
        }

        if (usuarioHabilidade.getPrioridade() < 0 || usuarioHabilidade.getPrioridade() > 5) {
            System.out.println("Erro: A prioridade deve ser entre 0 e 5.");
            return null;
        }

        usuarioHabilidade.setStatusRelacao(usuarioHabilidade.getStatusRelacao().trim().toUpperCase());

        ArrayList<UsuarioHabilidadeTO> lista = usuarioHabilidadeDAO.findByUsuario(usuarioHabilidade.getIdUsuario());
        for (UsuarioHabilidadeTO existente : lista) {
            if (existente.getIdHabilidade() == usuarioHabilidade.getIdHabilidade()) {
                System.out.println("Erro: O Usuário já possui essa habilidade cadastrada!");
                return null;
            }
        }

        return usuarioHabilidadeDAO.save(usuarioHabilidade);
    }

    public UsuarioHabilidadeTO update(UsuarioHabilidadeTO usuarioHabilidade) {

        usuarioHabilidadeDAO = new UsuarioHabilidadeDAO();
        usuarioDAO = new UsuarioDAO();
        habilidadeDAO = new HabilidadeDAO();

        if (usuarioHabilidade.getIdRelacao() <= 0) {
            System.out.println("Erro: O ID da relação é inválido.");
            return null;
        }

        if (usuarioHabilidade.getIdUsuario() <= 0 || usuarioDAO.findById(usuarioHabilidade.getIdUsuario()) == null) {
            System.out.println("Erro: O Usuário está inválido ou não foi encontrado.");
            return null;
        }

        if (usuarioHabilidade.getIdHabilidade() <= 0 || habilidadeDAO.findById(usuarioHabilidade.getIdHabilidade()) == null) {

            System.out.println("Erro: A habilidade é inválida ou não foi encontrada.");
            return null;
        }

        if (usuarioHabilidade.getStatusRelacao() == null || usuarioHabilidade.getStatusRelacao().trim().isEmpty()) {
            System.out.println("Erro: O status da relação não pode ser vazio.");
            return null;
        }

        if (usuarioHabilidade.getNivel() < 0 || usuarioHabilidade.getNivel() > 10) {
            System.out.println("Erro: O nível deve ser entre 0 e 10.");
            return null;
        }

        if (usuarioHabilidade.getPrioridade() < 0 || usuarioHabilidade.getPrioridade() > 5) {
            System.out.println("Erro: A prioridade deve ser entre 0 e 5.");
            return null;
        }

        usuarioHabilidade.setStatusRelacao(usuarioHabilidade.getStatusRelacao().trim().toUpperCase());

        return usuarioHabilidadeDAO.update(usuarioHabilidade);
    }

    public boolean delete(int idRelacao) {

        usuarioHabilidadeDAO = new UsuarioHabilidadeDAO();

        if (idRelacao <= 0) {
            System.out.println("Erro: O ID da relação inválido.");
            return false;
        }

        return usuarioHabilidadeDAO.delete(idRelacao);
    }
}