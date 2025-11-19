package br.com.fiap.dao;

import br.com.fiap.to.EmpresaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpresaDAO {

    public EmpresaTO save(EmpresaTO empresa) {

        String sql = "insert into t_lum_empresa (id_empresa, nm_razao_social, nr_cnpj, ds_email_contato, ds_senha) values (seq_lum_empresa.nextval, ?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setString(1, empresa.getRazaoSocial());
            ps.setString(2, empresa.getCnpj());
            ps.setString(3, empresa.getEmailDeContato());
            ps.setString(4, empresa.getSenha());

            if (ps.executeUpdate() > 0) {
                return empresa;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao salvar empresa: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public boolean delete(Integer idEmpresa) {

        String sql = "delete from t_lum_empresa where id_empresa = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, idEmpresa);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir empresa: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return false;
    }

    public EmpresaTO update(EmpresaTO empresa) {

        String sql = "update t_lum_empresa set nm_razao_social = ?, nr_cnpj = ?, ds_email_contato = ?, ds_senha = ? where id_empresa = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setString(1, empresa.getRazaoSocial());
            ps.setString(2, empresa.getCnpj());
            ps.setString(3, empresa.getEmailDeContato());
            ps.setString(4, empresa.getSenha());
            ps.setInt(5, empresa.getIdEmpresa());

            if (ps.executeUpdate() > 0) {
                return empresa;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar empresa: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public ArrayList<EmpresaTO> findAll() {

        ArrayList<EmpresaTO> empresas = new ArrayList<>();

        String sql = "select * from t_lum_empresa order by id_empresa";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EmpresaTO empresa = new EmpresaTO();

                empresa.setIdEmpresa(rs.getInt("id_empresa"));
                empresa.setRazaoSocial(rs.getString("nm_razao_social"));
                empresa.setCnpj(rs.getString("nr_cnpj"));
                empresa.setEmailDeContato(rs.getString("ds_email_contato"));
                empresa.setSenha(rs.getString("ds_senha"));

                empresas.add(empresa);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar empresas: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return empresas;
    }

    public EmpresaTO findById(Integer idEmpresa) {

        String sql = "select * from t_lum_empresa where id_empresa = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setInt(1, idEmpresa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                EmpresaTO empresa = new EmpresaTO();

                empresa.setIdEmpresa(rs.getInt("id_empresa"));
                empresa.setRazaoSocial(rs.getString("nm_razao_social"));
                empresa.setCnpj(rs.getString("nr_cnpj"));
                empresa.setEmailDeContato(rs.getString("ds_email_contato"));
                empresa.setSenha(rs.getString("ds_senha"));

                return empresa;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar empresa: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public EmpresaTO findByEmail(String email) {

        String sql = "select * from t_lum_empresa where ds_email_contato = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                EmpresaTO empresa = new EmpresaTO();

                empresa.setIdEmpresa(rs.getInt("id_empresa"));
                empresa.setRazaoSocial(rs.getString("nm_razao_social"));
                empresa.setCnpj(rs.getString("nr_cnpj"));
                empresa.setEmailDeContato(rs.getString("ds_email_contato"));
                empresa.setSenha(rs.getString("ds_senha"));

                return empresa;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar empresa por email: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }

    public EmpresaTO login(String email, String senha) {

        String sql = "select * from t_lum_empresa where ds_email_contato = ? and ds_senha = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                EmpresaTO empresa = new EmpresaTO();

                empresa.setIdEmpresa(rs.getInt("id_empresa"));
                empresa.setRazaoSocial(rs.getString("nm_razao_social"));
                empresa.setCnpj(rs.getString("nr_cnpj"));
                empresa.setEmailDeContato(rs.getString("ds_email_contato"));
                empresa.setSenha(rs.getString("ds_senha"));

                return empresa;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao fazer login da empresa: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return null;
    }
}