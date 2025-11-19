package br.com.fiap.to;

import jakarta.validation.constraints.*;


public class HabilidadeTO {

    @PositiveOrZero
    private int idHabilidade;

    @NotBlank
    private String nomeHabilidade;

    @NotBlank
    private String descricao;

    @NotBlank
    private String categoria;

    public HabilidadeTO() {
    }

    public HabilidadeTO(int idHabilidade, String nomeHabilidade, String descricao, String categoria) {
        this.idHabilidade = idHabilidade;
        this.nomeHabilidade = nomeHabilidade;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public int getIdHabilidade() {
        return idHabilidade;
    }

    public void setIdHabilidade(int idHabilidade) {
        this.idHabilidade = idHabilidade;
    }

    public String getNomeHabilidade() {
        return nomeHabilidade;
    }

    public void setNomeHabilidade(String nomeHabilidade) {
        this.nomeHabilidade = nomeHabilidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}