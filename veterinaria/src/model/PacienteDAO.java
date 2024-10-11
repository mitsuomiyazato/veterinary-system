package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Paciente.EstadoCastracao;

public class PacienteDAO extends DAO {
    private static PacienteDAO instance;

    private PacienteDAO() {
        getConnection();
        createTable();
    }

    public static PacienteDAO getInstance() {
        if (instance == null) {
            instance = new PacienteDAO();
        }
        return instance;
    }

    public Paciente create(String nome, String especie, String raca, int idade, String coloracao, EstadoCastracao estadoCastracao, Proprietario proprietario) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "INSERT INTO paciente (nome, especie, raca, idade, coloracao, estado_castracao, id_proprietario) VALUES (?,?,?,?,?,?,?)"
            );
            stmt.setString(1, nome);
            stmt.setString(2, especie);
            stmt.setString(3, raca);
            stmt.setInt(4, idade);
            stmt.setString(5, coloracao);
            stmt.setString(6, estadoCastracao.name());
            stmt.setInt(7, proprietario.getId());
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("paciente", "id"));
    }

    private Paciente buildObject(ResultSet rs) {
        Paciente paciente = null;
        try {
        	Proprietario proprietario = ProprietarioDAO.getInstance().retrieveById(rs.getInt("id_proprietario"));
            EstadoCastracao estado = EstadoCastracao.valueOf(rs.getString("estado_castracao"));
            paciente = new Paciente(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("especie"),
                rs.getString("raca"),
                rs.getInt("idade"),
                rs.getString("coloracao"),
                estado,
                proprietario
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return paciente;
    }

    public List<Paciente> retrieve(String query) {
        List<Paciente> pacientes = new ArrayList<>();
        try (ResultSet rs = getResultSet(query)) {
            while (rs.next()) {
                pacientes.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return pacientes;
    }

    public List<Paciente> retrieveAll() {
        return this.retrieve("SELECT * FROM paciente");
    }

    public List<Paciente> retrieveLast() {
        return this.retrieve("SELECT * FROM paciente WHERE id = " + lastId("paciente", "id"));
    }

    public Paciente retrieveById(int id) {
        List<Paciente> pacientes = this.retrieve("SELECT * FROM paciente WHERE id = " + id);
        return (pacientes.isEmpty() ? null : pacientes.get(0));
    }
    
    public Paciente retrieveByNome(String nome) {
        List<Paciente> paciente = this.retrieve("SELECT * FROM proprietario WHERE nome = '" + nome + "'");
        return (paciente.isEmpty() ? null : paciente.get(0));
    }
    
    public List<Paciente> retrieveByIdProprietario(int id_proprietario){
    	return this.retrieve("SELECT * FROM paciente WHERE id_proprietario = " + id_proprietario);
    }

    public List<Paciente> retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM paciente WHERE nome LIKE '%" + nome + "%'");
    }
    
    public List<Paciente> retrieveByIdProprietarioAndSimilarName(int id_proprietario, String nome) {
        return this.retrieve("SELECT * FROM paciente WHERE id_proprietario = " + id_proprietario + " AND nome LIKE '%" + nome + "%'");
    }

    public void update(Paciente paciente) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "UPDATE paciente SET nome=?, especie=?, raca=?, idade=?, coloracao=?, estado_castracao=?, id_proprietario=? WHERE id=?"
            );
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getEspecie());
            stmt.setString(3, paciente.getRaca());
            stmt.setInt(4, paciente.getIdade());
            stmt.setString(5, paciente.getColoracao());
            stmt.setString(6, paciente.getEstadoCastracao().name());
            stmt.setInt(7, paciente.getProprietario().getId());
            stmt.setInt(8, paciente.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void delete(Paciente paciente) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM paciente WHERE id = ?");
            stmt.setInt(1, paciente.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public void deleteByProprietario(int id_proprietario) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM paciente WHERE id_proprietario = ?");
            stmt.setInt(1, id_proprietario);
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
