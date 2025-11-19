package br.com.fiap.bo;

import br.com.fiap.dao.*;
import br.com.fiap.to.*;

import java.util.ArrayList;
import java.util.List;

public class DashboardBO {

    private DemandaDAO demandaDAO = new DemandaDAO();
    private UsuarioHabilidadeDAO usuarioHabilidadeDAO = new UsuarioHabilidadeDAO();
    private CursoHabilidadeDAO cursoHabilidadeDAO = new CursoHabilidadeDAO();
    private CursoDAO cursoDAO = new CursoDAO();

    private HabilidadeDAO habilidadeDAO = new HabilidadeDAO();

    public ArrayList<CursoTO> gerarRecomendacoes(int idUsuario) {
        ArrayList<CursoTO> cursosRecomendados = new ArrayList<>();

        ArrayList<UsuarioHabilidadeTO> minhasHabilidades = usuarioHabilidadeDAO.findByUsuario(idUsuario);
        List<Integer> idsMinhasHabilidades = new ArrayList<>();

        for (UsuarioHabilidadeTO usuarioHabilidade : minhasHabilidades) {
            if ("ATUAL".equalsIgnoreCase(usuarioHabilidade.getStatusRelacao())) {
                idsMinhasHabilidades.add(usuarioHabilidade.getIdHabilidade());
            }
        }

        ArrayList<DemandaTO> demandasMercado = demandaDAO.findAll();

        List<Integer> idsLacunas = new ArrayList<>();
        for (DemandaTO demanda : demandasMercado) {
            if (!idsMinhasHabilidades.contains(demanda.getIdHabilidade())) {
                if (!idsLacunas.contains(demanda.getIdHabilidade())) {
                    idsLacunas.add(demanda.getIdHabilidade());
                }
            }
        }

        ArrayList<CursoHabilidadeTO> cursosVinculados = cursoHabilidadeDAO.findAll();

        for (Integer idLacuna : idsLacunas) {

            for (CursoHabilidadeTO cursoHabilidade : cursosVinculados) {

                if (cursoHabilidade.getIdHabilidade() == idLacuna) {
                    CursoTO curso = cursoDAO.findById(cursoHabilidade.getIdCurso());

                    if (curso != null) {
                        boolean jaEstaNaLista = false;

                        for (CursoTO cursoVerificacao : cursosRecomendados) {
                            if (cursoVerificacao.getIdCurso() == curso.getIdCurso()) {
                                jaEstaNaLista = true;
                                break;
                            }
                        }

                        if (!jaEstaNaLista) {
                            cursosRecomendados.add(curso);
                        }
                    }
                }
            }
        }

        return cursosRecomendados;
    }
}