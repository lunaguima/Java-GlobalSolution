package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.util.ArrayList;

public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public ArrayList<UsuarioTO> findAll() {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findAll();
    }

    public UsuarioTO findById(Integer id) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findById(id);
    }

    public UsuarioTO save(UsuarioTO usuario) {

        usuarioDAO = new UsuarioDAO();

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            System.out.println("Erro: O nome do usuário não pode ser vazio!");
            return null;
        }

        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            System.out.println("Erro: O username do usuário não pode ser vazio!");
            return null;
        }

        if (usuario.getEmail() == null ||
                !usuario.getEmail().contains("@") ||
                !usuario.getEmail().contains(".")) {
            System.out.println("Erro: O Email é inválido!");
            return null;
        }

        if (usuario.getSenha() == null || usuario.getSenha().length() < 8) {
            System.out.println("Erro: A senha deve ter pelo menos 8 caracteres.");
            return null;
        }

        if (usuario.getDataDeCadastro() == null) {
            System.out.println("Erro: A data de cadastro do usuário não pode ser nula.");
            return null;
        }


        String username = usuario.getUsername().trim();
        if (!username.startsWith("@")) {
            username = "@" + username;
        }
        usuario.setUsername(username);

        usuario.setNome(usuario.getNome().trim());
        usuario.setEmail(usuario.getEmail().trim());


        if (usuarioDAO.findByEmail(usuario.getEmail()) != null) {
            System.out.println("Erro: Este e-mail já foi cadastrado.");
            return null;
        }

        if (usuarioDAO.findByUsername(usuario.getUsername()) != null) {
            System.out.println("Erro: Este username já está em uso por outro usuário.");
            return null;
        }

        return usuarioDAO.save(usuario);
    }

    public boolean delete(Integer id) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.delete(id);
    }

    public UsuarioTO update(UsuarioTO usuario) {

        usuarioDAO = new UsuarioDAO();

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            System.out.println("Erro: O nome não pode ser vazio.");
            return null;
        }

        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            System.out.println("Erro: O username não pode ser vazio.");
            return null;
        }

        if (usuario.getEmail() == null || !usuario.getEmail().contains("@") || !usuario.getEmail().contains(".")) {
            System.out.println("Erro: Email inválido.");
            return null;
        }

        if (usuario.getSenha() == null || usuario.getSenha().length() < 8) {
            System.out.println("Erro: A senha deve ter pelo menos 8 caracteres.");
            return null;
        }

        if (usuario.getDataDeCadastro() == null) {
            System.out.println("Erro: A data de cadastro não pode ser nula.");
            return null;
        }


        String username = usuario.getUsername().trim();
        if (!username.startsWith("@")) {
            username = "@" + username;
        }
        usuario.setUsername(username);

        usuario.setNome(usuario.getNome().trim());
        usuario.setEmail(usuario.getEmail().trim());

        UsuarioTO porEmail = usuarioDAO.findByEmail(usuario.getEmail());
        if (porEmail != null && porEmail.getIdUsuario() != usuario.getIdUsuario()) {
            System.out.println("Erro: Este e-mail já está em uso por outro usuário.");
            return null;
        }

        UsuarioTO porUsername = usuarioDAO.findByUsername(usuario.getUsername());
        if (porUsername != null && porUsername.getIdUsuario() != usuario.getIdUsuario()) {
            System.out.println("Erro: Este username já está em uso por outro usuário.");
            return null;
        }

        return usuarioDAO.update(usuario);
    }

    public UsuarioTO login(String login, String senha) {

        usuarioDAO = new UsuarioDAO();

        if (login == null || senha == null ||
                login.trim().isEmpty() || senha.trim().isEmpty()) {
            return null;
        }

        login = login.trim();

        if (!login.contains("@") && !login.contains(".")) {
            login = "@" + login;
        }

        UsuarioTO usuario = usuarioDAO.login(login, senha);

        if (usuario == null) {
            System.out.println("Erro: Login ou senha inválidos.");
        }

        return usuario;
    }
}