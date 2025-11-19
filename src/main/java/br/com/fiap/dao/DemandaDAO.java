package br.com.fiap.dao;

import br.com.fiap.to.DemandaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DemandaDAO {

    public DemandaTO save(DemandaTO demanda) {

        String sql = "insert into t_lum_demanda (id_demanda, id_empresa, id_habilidade, nr_prioridade, ds_senioridade, st_remoto, qt_vagas) values (seq_lum_demanda.nextval, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, demanda.getIdEmpresa());
            ps.setInt(2, demanda.getIdHabilidade());
            ps.setInt(3, demanda.getPrioridade());
            ps.setString(4, demanda.getSenioridade());
            ps.setString(5, demanda.getStatusRemoto());
            ps.setInt(6, demanda.getQtdVagas());

            if (ps.executeUpdate() > 0) {
                return demanda;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao salvar demanda: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public boolean delete(Integer idDemanda) {
        String sql = "delete from t_lum_demanda where id_demanda = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, idDemanda);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir demanda: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
    }

    public DemandaTO update(DemandaTO demanda) {

        String sql = "update t_lum_demanda set id_empresa = ?, id_habilidade = ?, nr_prioridade = ?, ds_senioridade = ?, st_remoto = ?, qt_vagas = ? where id_demanda = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, demanda.getIdEmpresa());
            ps.setInt(2, demanda.getIdHabilidade());
            ps.setInt(3, demanda.getPrioridade());
            ps.setString(4, demanda.getSenioridade());
            ps.setString(5, demanda.getStatusRemoto());
            ps.setInt(6, demanda.getQtdVagas());
            ps.setInt(7, demanda.getIdDemanda());

            if (ps.executeUpdate() > 0) {
                return demanda;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar demanda: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public ArrayList<DemandaTO> findAll() {

        ArrayList<DemandaTO> lista = new ArrayList<>();
        String sql = "select * from t_lum_demanda order by id_demanda";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DemandaTO d = new DemandaTO();

                d.setIdDemanda(rs.getInt("id_demanda"));
                d.setIdEmpresa(rs.getInt("id_empresa"));
                d.setIdHabilidade(rs.getInt("id_habilidade"));
                d.setPrioridade(rs.getInt("nr_prioridade"));
                d.setSenioridade(rs.getString("ds_senioridade"));
                d.setStatusRemoto(rs.getString("st_remoto"));
                d.setQtdVagas(rs.getInt("qt_vagas"));

                lista.add(d);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar demandas: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return lista;
    }

    public DemandaTO findById(Integer idDemanda) {

        String sql = "select * from t_lum_demanda where id_demanda = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, idDemanda);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                DemandaTO d = new DemandaTO();

                d.setIdDemanda(rs.getInt("id_demanda"));
                d.setIdEmpresa(rs.getInt("id_empresa"));
                d.setIdHabilidade(rs.getInt("id_habilidade"));
                d.setPrioridade(rs.getInt("nr_prioridade"));
                d.setSenioridade(rs.getString("ds_senioridade"));
                d.setStatusRemoto(rs.getString("st_remoto"));
                d.setQtdVagas(rs.getInt("qt_vagas"));

                return d;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar demanda: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public ArrayList<DemandaTO> findByEmpresa(Integer idEmpresa) {

        ArrayList<DemandaTO> lista = new ArrayList<>();
        String sql = "select * from t_lum_demanda where id_empresa = ? order by nr_prioridade";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, idEmpresa);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DemandaTO d = new DemandaTO();

                d.setIdDemanda(rs.getInt("id_demanda"));
                d.setIdEmpresa(rs.getInt("id_empresa"));
                d.setIdHabilidade(rs.getInt("id_habilidade"));
                d.setPrioridade(rs.getInt("nr_prioridade"));
                d.setSenioridade(rs.getString("ds_senioridade"));
                d.setStatusRemoto(rs.getString("st_remoto"));
                d.setQtdVagas(rs.getInt("qt_vagas"));

                lista.add(d);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar demandas da empresa: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return lista;
    }
}
