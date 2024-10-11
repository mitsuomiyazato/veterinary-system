package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProprietarioDAO extends DAO {
    private static ProprietarioDAO instance;

    private ProprietarioDAO() {
        getConnection();
        createTable();
    }

    public static ProprietarioDAO getInstance() {
        if (instance == null) {
            instance = new ProprietarioDAO();
        }
        return instance;
    }

    public Proprietario create(String nomeCompleto, String cpf, String telefone, String endereco) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement(
                "INSERT INTO proprietario (nome_completo, cpf, telefone, endereco) VALUES (?,?,?,?)"
            );
            stmt.setString(1, nomeCompleto);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);
            stmt.setString(4, endereco);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ProprietarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("proprietario", "id"));
    }

    private Proprietario buildObject(ResultSet rs) {
        Proprietario proprietario = null;
        try {
            proprietario = new Proprietario(
                rs.getInt("id"),
                rs.getString("nome_completo"), 
                rs.getString("cpf"),            
                rs.getString("telefone"),       
                rs.getString("endereco")        
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return proprietario;
    }

    public List<Proprietario> retrieve(String query) {
        List<Proprietario> proprietarios = new ArrayList<>();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                proprietarios.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return proprietarios;
    }
    
    public List<Proprietario> retrieveAll() {
        return this.retrieve("SELECT * FROM proprietario");
    }

    
    public List<Proprietario> retrieveLast() {
        return this.retrieve("SELECT * FROM proprietario WHERE id = " + lastId("proprietario", "id"));
    }

    
    public Proprietario retrieveById(int id) {
        List<Proprietario> proprietarios = this.retrieve("SELECT * FROM proprietario WHERE id = " + id);
        return (proprietarios.isEmpty() ? null : proprietarios.get(0));
    }
    
    public Proprietario retrieveByNomeCompleto(String nomeCompleto) {
        List<Proprietario> proprietarios = this.retrieve("SELECT * FROM proprietario WHERE nome_completo = '" + nomeCompleto + "'");
        return (proprietarios.isEmpty() ? null : proprietarios.get(0));
    }

    
    public List<Proprietario> retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM proprietario WHERE nome_completo LIKE '%" + nome + "%'");
    }

    
    public void update(Proprietario proprietario) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement(
                "UPDATE proprietario SET nome_completo=?, cpf=?, telefone=?, endereco=? WHERE id=?"
            );
            stmt.setString(1, proprietario.getNomeCompleto());
            stmt.setString(2, proprietario.getCpf());
            stmt.setString(3, proprietario.getTelefone());
            stmt.setString(4, proprietario.getEndereco());
            stmt.setInt(5, proprietario.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    
    public void delete(Proprietario proprietario) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("DELETE FROM proprietario WHERE id = ?");
            stmt.setInt(1, proprietario.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
