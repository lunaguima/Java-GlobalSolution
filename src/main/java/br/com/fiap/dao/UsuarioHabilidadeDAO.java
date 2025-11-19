package br.com.fiap.dao;

import br.com.fiap.to.UsuarioHabilidadeTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

public class UsuarioHabilidadeDAO {

    public UsuarioHabilidadeTO save(UsuarioHabilidadeTO usuarioHabilidade) {

        String sql = "insert into t_lum_usuario_habilidade (id_relacao, id_usuario, id_habilidade, st_relacao, nr_nivel, nr_prioridade, dt_registro) values (seq_lum_usuario_habilidade.nextval, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, usuarioHabilidade.getIdUsuario());
            ps.setInt(2, usuarioHabilidade.getIdHabilidade());
            ps.setString(3, usuarioHabilidade.getStatusRelacao());
            ps.setInt(4, usuarioHabilidade.getNivel());
            ps.setInt(5, usuarioHabilidade.getPrioridade());
            ps.setDate(6, Date.valueOf(usuarioHabilidade.getDataDeRegistro()));

            if (ps.executeUpdate() > 0) {
                return usuarioHabilidade;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao salvar o vínculo Usuario-Habilidade: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public boolean delete(int idRelacao) {

        String sql = "delete from t_lum_usuario_habilidade where id_relacao = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, idRelacao);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir o vínculo: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
    }

    public UsuarioHabilidadeTO update(UsuarioHabilidadeTO usuarioHabilidade) {

        String sql = "update t_lum_usuario_habilidade set id_usuario = ?, id_habilidade = ?, st_relacao = ?, nr_nivel = ?, nr_prioridade = ? where id_relacao = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, usuarioHabilidade.getIdUsuario());
            ps.setInt(2, usuarioHabilidade.getIdHabilidade());
            ps.setString(3, usuarioHabilidade.getStatusRelacao());
            ps.setInt(4, usuarioHabilidade.getNivel());
            ps.setInt(5, usuarioHabilidade.getPrioridade());
            ps.setInt(6, usuarioHabilidade.getIdRelacao());

            if (ps.executeUpdate() > 0) {
                return usuarioHabilidade;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o vínculo Usuario-Habilidade: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public UsuarioHabilidadeTO findById(int idRelacao) {

        String sql = "select * from t_lum_usuario_habilidade where id_relacao = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, idRelacao);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UsuarioHabilidadeTO usuarioHabilidade = new UsuarioHabilidadeTO();
                usuarioHabilidade.setIdRelacao(rs.getInt("id_relacao"));
                usuarioHabilidade.setIdUsuario(rs.getInt("id_usuario"));
                usuarioHabilidade.setIdHabilidade(rs.getInt("id_habilidade"));
                usuarioHabilidade.setStatusRelacao(rs.getString("st_relacao"));
                usuarioHabilidade.setNivel(rs.getInt("nr_nivel"));
                usuarioHabilidade.setPrioridade(rs.getInt("nr_prioridade"));
                usuarioHabilidade.setDataDeRegistro(rs.getDate("dt_registro").toLocalDate());

                return usuarioHabilidade;
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta de vínculo por ID: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public ArrayList<UsuarioHabilidadeTO> findByUsuario(int idUsuario) {

        ArrayList<UsuarioHabilidadeTO> lista = new ArrayList<>();
        String sql = "select * from t_lum_usuario_habilidade where id_usuario = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UsuarioHabilidadeTO usuarioHabilidade = new UsuarioHabilidadeTO();
                usuarioHabilidade.setIdRelacao(rs.getInt("id_relacao"));
                usuarioHabilidade.setIdUsuario(rs.getInt("id_usuario"));
                usuarioHabilidade.setIdHabilidade(rs.getInt("id_habilidade"));
                usuarioHabilidade.setStatusRelacao(rs.getString("st_relacao"));
                usuarioHabilidade.setNivel(rs.getInt("nr_nivel"));
                usuarioHabilidade.setPrioridade(rs.getInt("nr_prioridade"));
                usuarioHabilidade.setDataDeRegistro(rs.getDate("dt_registro").toLocalDate());

                lista.add(usuarioHabilidade);
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta de habilidades do usuário: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return lista;
    }
}