package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceitaMedicaDAO extends DAO {
    private static ReceitaMedicaDAO instance;

    private ReceitaMedicaDAO() {
        getConnection();
        createTable();
    }

    public static ReceitaMedicaDAO getInstance() {
        if (instance == null) {
            instance = new ReceitaMedicaDAO();
        }
        return instance;
    }

    public ReceitaMedica create(Paciente paciente, List<String> medicamentos, LocalDateTime dataEmissao, String observacoes, Veterinario veterinario) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "INSERT INTO receita_medica (id_paciente, medicamentos, data_emissao, observacoes, id_veterinario) VALUES (?,?,?,?,?)"
            );
            stmt.setInt(1, paciente.getId());
            stmt.setString(2, String.join(",", medicamentos));
            stmt.setTimestamp(3, Timestamp.valueOf(dataEmissao));
            stmt.setString(4, observacoes);
            stmt.setInt(5, veterinario.getId());
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ReceitaMedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("receita_medica", "id"));
    }

    private ReceitaMedica buildObject(ResultSet rs) {
        ReceitaMedica receitaMedica = null;
        try {
            Paciente paciente = PacienteDAO.getInstance().retrieveById(rs.getInt("id_paciente"));
            List<String> medicamentos = List.of(rs.getString("medicamentos").split(","));
            LocalDateTime dataEmissao = rs.getTimestamp("data_emissao").toLocalDateTime();
            Veterinario veterinario = VeterinarioDAO.getInstance().retrieveById(rs.getInt("id_veterinario"));
            receitaMedica = new ReceitaMedica(
                rs.getInt("id"),
                paciente,
                medicamentos,
                dataEmissao,
                rs.getString("observacoes"),
                veterinario
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return receitaMedica;
    }

    public List<ReceitaMedica> retrieve(String query) {
        List<ReceitaMedica> receitas = new ArrayList<>();
        try (ResultSet rs = getResultSet(query)) {
            while (rs.next()) {
                receitas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return receitas;
    }

    public List<ReceitaMedica> retrieveAll() {
        return this.retrieve("SELECT * FROM receita_medica");
    }

    public List<ReceitaMedica> retrieveLast() {
        return this.retrieve("SELECT * FROM receita_medica WHERE id = " + lastId("receita_medica", "id"));
    }

    public ReceitaMedica retrieveById(int id) {
        List<ReceitaMedica> receitas = this.retrieve("SELECT * FROM receita_medica WHERE id = " + id);
        return (receitas.isEmpty() ? null : receitas.get(0));
    }

    public void update(ReceitaMedica receitaMedica) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "UPDATE receita_medica SET id_paciente=?, medicamentos=?, data_emissao=?, observacoes=?, id_veterinario=? WHERE id=?"
            );
            stmt.setInt(1, receitaMedica.getPaciente().getId());
            stmt.setString(2, String.join(",", receitaMedica.getMedicamentos()));
            stmt.setTimestamp(3, Timestamp.valueOf(receitaMedica.getDataEmissao()));
            stmt.setString(4, receitaMedica.getObservacoes());
            stmt.setInt(5, receitaMedica.getVeterinario().getId());
            stmt.setInt(6, receitaMedica.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void delete(ReceitaMedica receitaMedica) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM receita_medica WHERE id = ?");
            stmt.setInt(1, receitaMedica.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
