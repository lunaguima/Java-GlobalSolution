package br.com.fiap.to;

import jakarta.validation.constraints.*;

public class DemandaTO {

    @PositiveOrZero
    private int idDemanda;

    @PositiveOrZero
    private int idEmpresa;

    @PositiveOrZero
    private int idHabilidade;

    @PositiveOrZero
    private int prioridade;

    @NotBlank
    private String senioridade;

    @NotBlank
    private String statusRemoto;

    @PositiveOrZero
    private int qtdVagas;

    public DemandaTO() {
    }

    public DemandaTO(int idDemanda, int idEmpresa, int idHabilidade, int prioridade, String senioridade, String statusRemoto, int qtdVagas) {
        this.idDemanda = idDemanda;
        this.idEmpresa = idEmpresa;
        this.idHabilidade = idHabilidade;
        this.prioridade = prioridade;
        this.senioridade = senioridade;
        this.statusRemoto = statusRemoto;
        this.qtdVagas = qtdVagas;
    }

    public int getIdDemanda() {
        return idDemanda;
    }

    public void setIdDemanda(int idDemanda) {
        this.idDemanda = idDemanda;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdHabilidade() {
        return idHabilidade;
    }

    public void setIdHabilidade(int idHabilidade) {
        this.idHabilidade = idHabilidade;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getSenioridade() {
        return senioridade;
    }

    public void setSenioridade(String senioridade) {
        this.senioridade = senioridade;
    }

    public String getStatusRemoto() {
        return statusRemoto;
    }

    public void setStatusRemoto(String statusRemoto) {
        this.statusRemoto = statusRemoto;
    }

    public int getQtdVagas() {
        return qtdVagas;
    }

    public void setQtdVagas(int qtdVagas) {
        this.qtdVagas = qtdVagas;
    }
}