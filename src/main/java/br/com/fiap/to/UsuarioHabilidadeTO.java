package br.com.fiap.to;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class UsuarioHabilidadeTO {

    @PositiveOrZero
    private int idRelacao;

    @PositiveOrZero
    private int idUsuario;

    @PositiveOrZero
    private int idHabilidade;

    @NotBlank
    private String statusRelacao;

    private int nivel;

    @PositiveOrZero
    private int prioridade;

    @PastOrPresent
    private LocalDate dataDeRegistro;

    public UsuarioHabilidadeTO() {
    }

    public UsuarioHabilidadeTO(int idRelacao, int idUsuario, int idHabilidade, String statusRelacao, int nivel, int prioridade, LocalDate dataDeRegistro) {
        this.idRelacao = idRelacao;
        this.idUsuario = idUsuario;
        this.idHabilidade = idHabilidade;
        this.statusRelacao = statusRelacao;
        this.nivel = nivel;
        this.prioridade = prioridade;
        this.dataDeRegistro = dataDeRegistro;
    }

    public int getIdRelacao() {
        return idRelacao;
    }

    public void setIdRelacao(int idRelacao) {
        this.idRelacao = idRelacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdHabilidade() {
        return idHabilidade;
    }

    public void setIdHabilidade(int idHabilidade) {
        this.idHabilidade = idHabilidade;
    }

    public String getStatusRelacao() {
        return statusRelacao;
    }

    public void setStatusRelacao(String statusRelacao) {
        this.statusRelacao = statusRelacao;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public LocalDate getDataDeRegistro() {
        return dataDeRegistro;
    }

    public void setDataDeRegistro(LocalDate dataDeRegistro) {
        this.dataDeRegistro = dataDeRegistro;
    }
}