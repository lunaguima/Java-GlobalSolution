package br.com.fiap.to;

import jakarta.validation.constraints.*;

public class EmpresaTO {

    @PositiveOrZero
    private int idEmpresa;

    @NotBlank
    private String razaoSocial;

    @NotBlank
    private String cnpj;

    @NotBlank
    private String emailDeContato;

    @NotBlank
    private String senha;

    public EmpresaTO() {
    }

    public EmpresaTO(int idEmpresa, String razaoSocial, String cnpj, String emailDeContato, String senha) {
        this.idEmpresa = idEmpresa;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.emailDeContato = emailDeContato;
        this.senha = senha;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmailDeContato() {
        return emailDeContato;
    }

    public void setEmailDeContato(String emailDeContato) {
        this.emailDeContato = emailDeContato;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}