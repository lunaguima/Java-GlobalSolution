package br.com.fiap.dao;

import br.com.fiap.to.CursoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CursoDAO {

    public CursoTO save(CursoTO curso) {

        String sql = "insert into t_lum_curso (id_curso, nm_curso, nm_plataforma, ds_link_externo, nr_carga_horaria) values (seq_lum_curso.nextval, ?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setString(1, curso.getNomeDoCurso());
            ps.setString(2, curso.getPlataforma());
            ps.setString(3, curso.getLinkExterno());
            ps.setInt(4, curso.getCargaHoraria());

            if (ps.executeUpdate() > 0) {
                return curso;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao salvar curso: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public boolean delete(int idCurso) {

        String sql = "delete from t_lum_curso where id_curso = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, idCurso);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir curso: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
    }

    public CursoTO update(CursoTO curso) {


        String sql = "update t_lum_curso set nm_curso = ?, nm_plataforma = ?, ds_link_externo = ?, nr_carga_horaria = ? where id_curso = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setString(1, curso.getNomeDoCurso());
            ps.setString(2, curso.getPlataforma());
            ps.setString(3, curso.getLinkExterno());
            ps.setInt(4, curso.getCargaHoraria());
            ps.setInt(5, curso.getIdCurso());

            if (ps.executeUpdate() > 0) {
                return curso;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar curso: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public ArrayList<CursoTO> findAll() {

        ArrayList<CursoTO> lista = new ArrayList<>();
        String sql = "select * from t_lum_curso order by nm_curso";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CursoTO curso = new CursoTO();

                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setNomeDoCurso(rs.getString("nm_curso"));
                curso.setPlataforma(rs.getString("nm_plataforma"));
                curso.setLinkExterno(rs.getString("ds_link_externo"));
                curso.setCargaHoraria(rs.getInt("nr_carga_horaria"));

                lista.add(curso);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar cursos: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return lista;
    }

    public CursoTO findById(int idCurso) {

        String sql = "select * from t_lum_curso where id_curso = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, idCurso);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                CursoTO curso = new CursoTO();

                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setNomeDoCurso(rs.getString("nm_curso"));
                curso.setPlataforma(rs.getString("nm_plataforma"));
                curso.setLinkExterno(rs.getString("ds_link_externo"));
                curso.setCargaHoraria(rs.getInt("nr_carga_horaria"));

                return curso;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar curso por ID: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }
}