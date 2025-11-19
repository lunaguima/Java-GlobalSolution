package br.com.fiap.to;

import jakarta.validation.constraints.*;


public class CursoTO {

    @PositiveOrZero
    private int idCurso;

    @NotBlank
    private String nomeDoCurso;

    @NotBlank
    private String plataforma;

    @NotBlank
    private String linkExterno;

    @PositiveOrZero
    private int cargaHoraria;

    public CursoTO() {
    }

    public CursoTO(int idCurso, String nomeDoCurso, String plataforma, String linkExterno, int cargaHoraria) {
        this.idCurso = idCurso;
        this.nomeDoCurso = nomeDoCurso;
        this.plataforma = plataforma;
        this.linkExterno = linkExterno;
        this.cargaHoraria = cargaHoraria;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomeDoCurso() {
        return nomeDoCurso;
    }

    public void setNomeDoCurso(String nomeDoCurso) {
        this.nomeDoCurso = nomeDoCurso;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getLinkExterno() {
        return linkExterno;
    }

    public void setLinkExterno(String linkExterno) {
        this.linkExterno = linkExterno;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}