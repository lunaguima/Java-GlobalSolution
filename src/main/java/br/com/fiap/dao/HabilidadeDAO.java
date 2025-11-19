package br.com.fiap.dao;

import br.com.fiap.to.HabilidadeTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HabilidadeDAO {

    public HabilidadeTO save(HabilidadeTO habilidade) {

        String sql = "insert into t_lum_habilidade (id_habilidade, nm_habilidade, ds_descricao, ds_categoria) values (seq_lum_habilidade.nextval, ?, ?, ?) ";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setString(1, habilidade.getNomeHabilidade());
            ps.setString(2, habilidade.getDescricao());
            ps.setString(3, habilidade.getCategoria());

            if (ps.executeUpdate() > 0) {
                return habilidade;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao salvar habilidade: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public boolean delete(int idHabilidade) {

        String sql = "delete from t_lum_habilidade where id_habilidade = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, idHabilidade);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar habilidade: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
    }

    public HabilidadeTO update(HabilidadeTO habilidade) {

        String sql = " update t_lum_habilidade set nm_habilidade = ?, ds_descricao = ?, ds_categoria = ? where id_habilidade = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setString(1, habilidade.getNomeHabilidade());
            ps.setString(2, habilidade.getDescricao());
            ps.setString(3, habilidade.getCategoria());
            ps.setInt(4, habilidade.getIdHabilidade());

            if (ps.executeUpdate() > 0) {
                return habilidade;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar habilidade: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public ArrayList<HabilidadeTO> findAll() {

        ArrayList<HabilidadeTO> lista = new ArrayList<>();

        String sql = "select * from t_lum_habilidade order by nm_habilidade";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HabilidadeTO habilidade = new HabilidadeTO();

                habilidade.setIdHabilidade(rs.getInt("id_habilidade"));
                habilidade.setNomeHabilidade(rs.getString("nm_habilidade"));
                habilidade.setDescricao(rs.getString("ds_descricao"));
                habilidade.setCategoria(rs.getString("ds_categoria"));

                lista.add(habilidade);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar habilidades: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return lista;
    }

    public HabilidadeTO findById(int id) {

        String sql = "select * from t_lum_habilidade where id_habilidade = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HabilidadeTO habilidade = new HabilidadeTO();

                habilidade.setIdHabilidade(rs.getInt("id_habilidade"));
                habilidade.setNomeHabilidade(rs.getString("nm_habilidade"));
                habilidade.setDescricao(rs.getString("ds_descricao"));
                habilidade.setCategoria(rs.getString("ds_categoria"));

                return habilidade;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar habilidade por ID: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public HabilidadeTO findByNome(String nome) {

        String sql = "select * from t_lum_habilidade where nm_habilidade = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HabilidadeTO habilidade = new HabilidadeTO();

                habilidade.setIdHabilidade(rs.getInt("id_habilidade"));
                habilidade.setNomeHabilidade(rs.getString("nm_habilidade"));
                habilidade.setDescricao(rs.getString("ds_descricao"));
                habilidade.setCategoria(rs.getString("ds_categoria"));

                return habilidade;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar habilidade por nome: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }
}