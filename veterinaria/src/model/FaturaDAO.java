package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FaturaDAO extends DAO {
    private static FaturaDAO instance;

    private FaturaDAO() {
        getConnection();
        createTable();
    }

    public static FaturaDAO getInstance() {
        if (instance == null) {
            instance = new FaturaDAO();
        }
        return instance;
    }

    public Fatura create(Proprietario proprietario, double valorTotal, Fatura.StatusPagamento status, LocalDate dataVencimento) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "INSERT INTO fatura (id_proprietario, valor_total, status, data_vencimento) VALUES (?, ?, ?, ?)"
            );
            stmt.setInt(1, proprietario.getId());
            stmt.setDouble(2, valorTotal);
            stmt.setString(3, status.name());
            stmt.setDate(4, java.sql.Date.valueOf(dataVencimento));
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(FaturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Fatura(lastId("fatura", "id"), proprietario, valorTotal, status, dataVencimento);
    }

    private Fatura buildObject(ResultSet rs) {
        Fatura fatura = null;
        try {
            int id = rs.getInt("id"); 
            Proprietario proprietario = ProprietarioDAO.getInstance().retrieveById(rs.getInt("id_proprietario"));
            double valorTotal = rs.getDouble("valor_total");
            Fatura.StatusPagamento status = Fatura.StatusPagamento.valueOf(rs.getString("status"));
            LocalDate dataVencimento = rs.getDate("data_vencimento").toLocalDate();
            fatura = new Fatura(id, proprietario, valorTotal, status, dataVencimento); 
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return fatura;
    }

    public List<Fatura> retrieve(String query) {
        List<Fatura> faturas = new ArrayList<>();
        try (ResultSet rs = getResultSet(query)) {
            while (rs.next()) {
                faturas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return faturas;
    }

    public List<Fatura> retrieveAll() {
        return this.retrieve("SELECT * FROM fatura");
    }
    
    public List<Fatura> retrieveByIdProprietario(int id_proprietario) {
        return this.retrieve("SELECT * FROM fatura WHERE id_proprietario = " + id_proprietario);
    }

    public Fatura retrieveById(int id) {
        List<Fatura> faturas = this.retrieve("SELECT * FROM fatura WHERE id = " + id);
        return (faturas.isEmpty() ? null : faturas.get(0));
    }
    
    public List<Fatura> retrieveByStatusProprietario(int id_proprietario, Fatura.StatusPagamento status)
    {
    	return this.retrieve("SELECT * FROM fatura WHERE id_proprietario = " + id_proprietario  + " AND status = '" + status.name() + "'");
    }
    
    public List<Fatura> retrieveByStatus(Fatura.StatusPagamento status)
    {
    	return this.retrieve("SELECT * FROM fatura WHERE status = '" + status.name() + "'");
    }

    public List<Agendamento> returnEmptyList() {
        return new ArrayList<>();
    }
    
    public void update(Fatura fatura) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "UPDATE fatura SET id_proprietario=?, valor_total=?, status=?, data_vencimento=? WHERE id=?"
            );
            stmt.setInt(1, fatura.getProprietario().getId());
            stmt.setDouble(2, fatura.getValorTotal());
            stmt.setString(3, fatura.getStatus().name());
            stmt.setDate(4, java.sql.Date.valueOf(fatura.getDataVencimento()));
            stmt.setInt(5, fatura.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void delete(Fatura fatura) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM fatura WHERE id = ?");
            stmt.setInt(1, fatura.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public void deleteByProprietario(int id_proprietario) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM fatura WHERE id_proprietario = ?");
            stmt.setInt(1, id_proprietario);
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}
