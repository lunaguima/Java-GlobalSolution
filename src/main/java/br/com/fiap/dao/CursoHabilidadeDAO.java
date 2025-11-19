package br.com.fiap.dao;

import br.com.fiap.to.CursoHabilidadeTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CursoHabilidadeDAO {

    public CursoHabilidadeTO save(CursoHabilidadeTO cursoHabilidade) {

        String sql = "insert into t_lum_curso_habilidade (id_curso_habil, id_habilidade, id_curso, st_foco_principal, ds_modulo, ds_nivel_ensinado, nr_tempo_dedicado) values (seq_lum_curso_habil.nextval, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, cursoHabilidade.getIdHabilidade());
            ps.setInt(2, cursoHabilidade.getIdCurso());
            ps.setString(3, cursoHabilidade.getStatusFocoPrincipal());
            ps.setString(4, cursoHabilidade.getModulo());
            ps.setString(5, cursoHabilidade.getNivelEnsinado());
            ps.setInt(6, cursoHabilidade.getTempoDedicado());

            if (ps.executeUpdate() > 0) {
                return cursoHabilidade;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao salvar Curso-Habilidade: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public boolean delete(int id) {

        String sql = "delete from t_lum_curso_habilidade where id_curso_habil = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir Curso-Habilidade: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
    }

    public CursoHabilidadeTO update(CursoHabilidadeTO cursoHabilidade) {

        String sql = "update t_lum_curso_habilidade set id_habilidade = ?, id_curso = ?, st_foco_principal = ?, ds_modulo = ?, ds_nivel_ensinado = ?, nr_tempo_dedicado = ? where id_curso_habil = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, cursoHabilidade.getIdHabilidade());
            ps.setInt(2, cursoHabilidade.getIdCurso());
            ps.setString(3, cursoHabilidade.getStatusFocoPrincipal());
            ps.setString(4, cursoHabilidade.getModulo());
            ps.setString(5, cursoHabilidade.getNivelEnsinado());
            ps.setInt(6, cursoHabilidade.getTempoDedicado());
            ps.setInt(7, cursoHabilidade.getIdCursoHabilidade());

            if (ps.executeUpdate() > 0) {
                return cursoHabilidade;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Curso-Habilidade: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public ArrayList<CursoHabilidadeTO> findAll() {

        ArrayList<CursoHabilidadeTO> lista = new ArrayList<>();
        String sql = "select * from t_lum_curso_habilidade order by id_curso_habil";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CursoHabilidadeTO cursoHabilidade = new CursoHabilidadeTO();

                cursoHabilidade.setIdCursoHabilidade(rs.getInt("id_curso_habil"));
                cursoHabilidade.setIdHabilidade(rs.getInt("id_habilidade"));
                cursoHabilidade.setIdCurso(rs.getInt("id_curso"));
                cursoHabilidade.setStatusFocoPrincipal(rs.getString("st_foco_principal"));
                cursoHabilidade.setModulo(rs.getString("ds_modulo"));
                cursoHabilidade.setNivelEnsinado(rs.getString("ds_nivel_ensinado"));
                cursoHabilidade.setTempoDedicado(rs.getInt("nr_tempo_dedicado"));

                lista.add(cursoHabilidade);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar Curso-Habilidade: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return lista;
    }

    public CursoHabilidadeTO findById(int id) {

        String sql = "select * from t_lum_curso_habilidade where id_curso_habil = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                CursoHabilidadeTO cursoHabilidade = new CursoHabilidadeTO();

                cursoHabilidade.setIdCursoHabilidade(rs.getInt("id_curso_habil"));
                cursoHabilidade.setIdHabilidade(rs.getInt("id_habilidade"));
                cursoHabilidade.setIdCurso(rs.getInt("id_curso"));
                cursoHabilidade.setStatusFocoPrincipal(rs.getString("st_foco_principal"));
                cursoHabilidade.setModulo(rs.getString("ds_modulo"));
                cursoHabilidade.setNivelEnsinado(rs.getString("ds_nivel_ensinado"));
                cursoHabilidade.setTempoDedicado(rs.getInt("nr_tempo_dedicado"));

                return cursoHabilidade;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar Curso-Habilidade por ID: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }
}