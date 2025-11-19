package br.com.fiap.bo;

import br.com.fiap.dao.EmpresaDAO;
import br.com.fiap.to.EmpresaTO;

import java.util.ArrayList;

public class EmpresaBO {

    private EmpresaDAO empresaDAO;

    public ArrayList<EmpresaTO> findAll() {
        empresaDAO = new EmpresaDAO();
        return empresaDAO.findAll();
    }

    public EmpresaTO findById(Integer id) {
        empresaDAO = new EmpresaDAO();

        return empresaDAO.findById(id);
    }

    public EmpresaTO save(EmpresaTO empresa) {

        empresaDAO = new EmpresaDAO();

        if (empresa.getRazaoSocial() == null || empresa.getRazaoSocial().trim().isEmpty()) {
            System.out.println("Erro: A Razão social não pode ser vazia.");
            return null;
        }

        if (empresa.getCnpj() == null || empresa.getCnpj().trim().isEmpty()) {
            System.out.println("Erro: O CNPJ é inválido!");
            return null;
        }

        if (empresa.getEmailDeContato() == null ||
                !empresa.getEmailDeContato().contains("@") ||
                !empresa.getEmailDeContato().contains("."))
        {
            System.out.println("Erro: O Email é inválido!");
            return null;
        }

        if (empresa.getSenha() == null || empresa.getSenha().length() < 8) {
            System.out.println("Erro: A senha deve ter pelo menos 8 caracteres.");
            return null;
        }

        if (empresaDAO.findByEmail(empresa.getEmailDeContato()) != null) {
            System.out.println("Erro: Este email já foi cadastrado.");
            return null;
        }

        return empresaDAO.save(empresa);
    }

    public boolean delete(Integer id) {
        empresaDAO = new EmpresaDAO();
        return empresaDAO.delete(id);
    }

    public EmpresaTO update(EmpresaTO empresa) {

        empresaDAO = new EmpresaDAO();

        if (empresa.getRazaoSocial() == null || empresa.getRazaoSocial().trim().isEmpty()) {
            System.out.println("Erro: Razão social da empresa não pode ser vazia.");
            return null;
        }

        if (empresa.getCnpj() == null || empresa.getCnpj().trim().isEmpty()) {
            System.out.println("Erro: CNPJ inválido.");
            return null;
        }

        if (empresa.getEmailDeContato() == null ||
                !empresa.getEmailDeContato().contains("@") ||
                !empresa.getEmailDeContato().contains("."))
        {
            System.out.println("Erro: Email inválido.");
            return null;
        }

        if (empresa.getSenha() == null || empresa.getSenha().length() < 8) {
            System.out.println("Erro: A senha deve ter pelo menos 8 caracteres.");
            return null;
        }

        EmpresaTO empresaPorEmail = empresaDAO.findByEmail(empresa.getEmailDeContato());
        if (empresaPorEmail != null && empresaPorEmail.getIdEmpresa() != empresa.getIdEmpresa()) {
            System.out.println("Erro: Este e-mail pertence a outra empresa.");
            return null;
        }

        return empresaDAO.update(empresa);
    }

    public EmpresaTO login(String email, String senha) {

        empresaDAO = new EmpresaDAO();

        if (email == null || senha == null ||
                email.trim().isEmpty() || senha.trim().isEmpty())
        {
            return null;
        }

        email = email.trim();

        EmpresaTO empresa = empresaDAO.login(email, senha);

        if (empresa == null) {
            System.out.println("Erro: Email ou senha incorretos.");
        }

        return empresa;
    }
}