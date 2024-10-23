package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeterinarioDAO extends DAO {
    private static VeterinarioDAO instance;

    private VeterinarioDAO() {
        getConnection();
        createTable();
    }

    public static VeterinarioDAO getInstance() {
        if (instance == null) {
            instance = new VeterinarioDAO();
        }
        return instance;
    }

    public Veterinario create(String nome, String email, String telefone) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "INSERT INTO veterinario (nome, email, telefone, is_active) VALUES (?,?,?,?)"
            );
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);
            stmt.setBoolean(4, true);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("veterinario", "id"));
    }

    private Veterinario buildObject(ResultSet rs) {
        Veterinario veterinario = null;
        try {
            veterinario = new Veterinario(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("telefone")
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return veterinario;
    }

    public List<Veterinario> retrieve(String query) {
        List<Veterinario> veterinarios = new ArrayList<>();
        try (ResultSet rs = getResultSet(query)) {
            while (rs.next()) {
                veterinarios.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return veterinarios;
    }

    public List<Veterinario> retrieveAll() {
        return this.retrieve("SELECT * FROM veterinario");
    }
    
    public List<Veterinario> retrieveAllActive() {
        return this.retrieve("SELECT * FROM veterinario WHERE is_active = TRUE");
    }
    
    public List<Veterinario> retrieveAllInactive() {
        return this.retrieve("SELECT * FROM veterinario WHERE is_active = FALSE");
    }

    public List<Veterinario> retrieveLast() {
        return this.retrieve("SELECT * FROM veterinario WHERE id = " + lastId("veterinario", "id"));
    }

    public Veterinario retrieveById(int id) {
        List<Veterinario> veterinarios = this.retrieve("SELECT * FROM veterinario WHERE id = " + id);
        return (veterinarios.isEmpty() ? null : veterinarios.get(0));
    }
    
    public List<Veterinario> retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM veterinario WHERE nome LIKE '%" + nome + "%'");
    }

    public void update(Veterinario veterinario) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "UPDATE veterinario SET nome=?, email=?, telefone=?, is_active=? WHERE id=?"
            );
            stmt.setString(1, veterinario.getNome());
            stmt.setString(2, veterinario.getEmail());
            stmt.setString(3, veterinario.getTelefone());
            stmt.setBoolean(4, veterinario.Is_active());
            stmt.setInt(5, veterinario.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void delete(Veterinario veterinario) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM veterinario WHERE id = ?");
            stmt.setInt(1, veterinario.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public void softDelete(Veterinario veterinario) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "UPDATE veterinario SET is_active = FALSE WHERE id = ?"
            );
            stmt.setInt(1, veterinario.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
