package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoricoDAO extends DAO {
    private static HistoricoDAO instance;

    private HistoricoDAO() {
        getConnection();
        createTable();
    }

    public static HistoricoDAO getInstance() {
        if (instance == null) {
            instance = new HistoricoDAO();
        }
        return instance;
    }

    public Historico create(Paciente paciente, List<String> vacinas, List<String> doencas, String peso, String observacoes) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "INSERT INTO historico (id_paciente, vacinas, doencas, peso, observacoes) VALUES (?,?,?,?,?)"
            );
            stmt.setInt(1, paciente.getId());
            stmt.setString(2, String.join(",", vacinas));  
            stmt.setString(3, String.join(",", doencas));  
            stmt.setString(4, peso);
            stmt.setString(5, String.join(",", observacoes));
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(HistoricoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("historico", "id"));
    }

    private Historico buildObject(ResultSet rs) {
        Historico historico = null;
        try {
            Paciente paciente = PacienteDAO.getInstance().retrieveById(rs.getInt("id_paciente"));
            List<String> vacinas = List.of(rs.getString("vacinas").split(","));
            List<String> doencas = List.of(rs.getString("doencas").split(","));
            String peso = rs.getString("peso");
            String observacoes = rs.getString("observacoes");

            historico = new Historico(
                rs.getInt("id"),
                paciente,
                vacinas,
                doencas,
                peso,
                observacoes
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return historico;
    }

    public List<Historico> retrieve(String query) {
        List<Historico> historicos = new ArrayList<>();
        try (ResultSet rs = getResultSet(query)) {
            while (rs.next()) {
                historicos.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return historicos;
    }

    public List<Historico> retrieveAll() {
        return this.retrieve("SELECT * FROM historico");
    }

    public Historico retrieveById(int id) {
        List<Historico> historicos = this.retrieve("SELECT * FROM historico WHERE id = " + id);
        return (historicos.isEmpty() ? null : historicos.get(0));
    }
    
    public Historico retrieveByIdPaciente(int id_paciente) {
        List<Historico> historicos = this.retrieve("SELECT * FROM historico WHERE id_paciente = " + id_paciente);
        return (historicos.isEmpty() ? null : historicos.get(0));
    }
    
    public void update(Historico historico) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "UPDATE historico SET id_paciente=?, vacinas=?, doencas=?, peso=?, observacoes=? WHERE id=?"
            );
            stmt.setInt(1, historico.getPaciente().getId());
            stmt.setString(2, String.join(",", historico.getVacinas()));
            stmt.setString(3, String.join(",", historico.getDoencas()));
            stmt.setString(4, historico.getPeso());
            stmt.setString(5, String.join(",", historico.getObservacoes()));
            stmt.setInt(6, historico.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void delete(Historico historico) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM historico WHERE id = ?");
            stmt.setInt(1, historico.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
