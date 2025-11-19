package br.com.fiap.to;

import jakarta.validation.constraints.*;

public class CursoHabilidadeTO {

    @PositiveOrZero
    private int idCursoHabilidade;

    @PositiveOrZero
    private int idHabilidade;

    @PositiveOrZero
    private int idCurso;

    private String statusFocoPrincipal;

    private String modulo;

    private String nivelEnsinado;

    @PositiveOrZero
    private int tempoDedicado;

    public CursoHabilidadeTO() {
    }

    public CursoHabilidadeTO(int idCursoHabilidade, int idHabilidade, int idCurso, String statusFocoPrincipal, String modulo, String nivelEnsinado, int tempoDedicado) {
        this.idCursoHabilidade = idCursoHabilidade;
        this.idHabilidade = idHabilidade;
        this.idCurso = idCurso;
        this.statusFocoPrincipal = statusFocoPrincipal;
        this.modulo = modulo;
        this.nivelEnsinado = nivelEnsinado;
        this.tempoDedicado = tempoDedicado;
    }

    public int getIdCursoHabilidade() {
        return idCursoHabilidade;
    }

    public void setIdCursoHabilidade(int idCursoHabilidade) {
        this.idCursoHabilidade = idCursoHabilidade;
    }

    public int getIdHabilidade() {
        return idHabilidade;
    }

    public void setIdHabilidade(int idHabilidade) {
        this.idHabilidade = idHabilidade;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getStatusFocoPrincipal() {
        return statusFocoPrincipal;
    }

    public void setStatusFocoPrincipal(String statusFocoPrincipal) {
        this.statusFocoPrincipal = statusFocoPrincipal;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getNivelEnsinado() {
        return nivelEnsinado;
    }

    public void setNivelEnsinado(String nivelEnsinado) {
        this.nivelEnsinado = nivelEnsinado;
    }

    public int getTempoDedicado() {
        return tempoDedicado;
    }

    public void setTempoDedicado(int tempoDedicado) {
        this.tempoDedicado = tempoDedicado;
    }
}