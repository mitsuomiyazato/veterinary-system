package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgendamentoDAO extends DAO {
    private static AgendamentoDAO instance;

    private AgendamentoDAO() {
        getConnection();
        createTable();
    }

    public static AgendamentoDAO getInstance() {
        if (instance == null) {
            instance = new AgendamentoDAO();
        }
        return instance;
    }

    public Agendamento create(Paciente paciente, LocalDateTime dataHora, String servico, Agendamento.StatusAgendamento status, Veterinario veterinario) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "INSERT INTO agendamento (id_paciente, data_hora, servico, status, id_veterinario) VALUES (?,?,?,?,?)"
            );
            stmt.setInt(1, paciente.getId());
            stmt.setString(2, dataHora.toString()); 
            stmt.setString(3, servico);
            stmt.setString(4, status.name());
            stmt.setInt(5, veterinario.getId());
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("agendamento", "id"));
    }

    private Agendamento buildObject(ResultSet rs) {
        Agendamento agendamento = null;
        try {
            Paciente paciente = PacienteDAO.getInstance().retrieveById(rs.getInt("id_paciente"));
            Veterinario veterinario = VeterinarioDAO.getInstance().retrieveById(rs.getInt("id_veterinario"));
            LocalDateTime dataHora = LocalDateTime.parse(rs.getString("data_hora")); 
            Agendamento.StatusAgendamento status = Agendamento.StatusAgendamento.valueOf(rs.getString("status"));
            agendamento = new Agendamento(
                rs.getInt("id"),
                paciente,
                dataHora,
                rs.getString("servico"),
                status,
                veterinario
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return agendamento;
    }

    public List<Agendamento> retrieve(String query) {
        List<Agendamento> agendamentos = new ArrayList<>();
        try (ResultSet rs = getResultSet(query)) {
            while (rs.next()) {
                agendamentos.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return agendamentos;
    }

    public List<Agendamento> retrieveAll() {
        return this.retrieve("SELECT * FROM agendamento");
    }

    public Agendamento retrieveById(int id) {
        List<Agendamento> agendamentos = this.retrieve("SELECT * FROM agendamento WHERE id = " + id);
        return (agendamentos.isEmpty() ? null : agendamentos.get(0));
    }
    
    public List<Agendamento> retrieveByIdPaciente(int id_paciente) {
        return this.retrieve("SELECT * FROM agendamento WHERE id_paciente = " + id_paciente);
    }
    
    public List<Agendamento> retrieveByIdVeterinario(int id_veterinario) {
        return this.retrieve("SELECT * FROM agendamento WHERE id_veterinario = " + id_veterinario);
    }
    
    public List<Agendamento> returnEmptyList() {
        return new ArrayList<>();
    }

    public List<Agendamento> retrieveByStatus(Agendamento.StatusAgendamento status) {
        List<Agendamento> agendamentos = new ArrayList<>();
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "SELECT * FROM agendamento WHERE status = ?"
            );
            stmt.setString(1, status.name());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                agendamentos.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return agendamentos;
    }

    public void update(Agendamento agendamento) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "UPDATE agendamento SET id_paciente=?, data_hora=?, servico=?, status=?, id_veterinario=? WHERE id=?"
            );
            stmt.setInt(1, agendamento.getPaciente().getId());
            stmt.setString(2, agendamento.getDataHora().toString());
            stmt.setString(3, agendamento.getServico());
            stmt.setString(4, agendamento.getStatus().name());
            stmt.setInt(5, agendamento.getVeterinario().getId());
            stmt.setInt(6, agendamento.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void delete(Agendamento agendamento) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM agendamento WHERE id = ?");
            stmt.setInt(1, agendamento.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
