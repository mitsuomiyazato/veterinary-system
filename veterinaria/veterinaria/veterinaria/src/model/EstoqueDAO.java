package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstoqueDAO extends DAO {
    private static EstoqueDAO instance;

    private EstoqueDAO() {
        getConnection();
        createTable();
    }

    public static EstoqueDAO getInstance() {
        if (instance == null) {
            instance = new EstoqueDAO();
        }
        return instance;
    }

    public Estoque create(Produto produto, int quantidade, int quantidadeMinima) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "INSERT INTO estoque (id_produto, quantidade, quantidade_minima) VALUES (?,?,?)"
            );
            stmt.setInt(1, produto.getId());  
            stmt.setInt(2, quantidade);
            stmt.setInt(3, quantidadeMinima);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("estoque", "id"));
    }

    private Estoque buildObject(ResultSet rs) {
        Estoque estoque = null;
        try {
            Produto produto = ProdutoDAO.getInstance().retrieveById(rs.getInt("id_produto")); 
            int quantidade = rs.getInt("quantidade");
            int quantidadeMinima = rs.getInt("quantidade_minima");
            estoque = new Estoque(produto, quantidade, quantidadeMinima);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return estoque;
    }

    public List<Estoque> retrieve(String query) {
        List<Estoque> estoques = new ArrayList<>();
        try (ResultSet rs = getResultSet(query)) {
            while (rs.next()) {
                estoques.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return estoques;
    }

    public List<Estoque> retrieveAll() {
        return this.retrieve("SELECT * FROM estoque");
    }

    public List<Estoque> retrieveLast() {
        return this.retrieve("SELECT * FROM estoque WHERE id = " + lastId("estoque", "id"));
    }

    public Estoque retrieveById(int id) {
        List<Estoque> estoques = this.retrieve("SELECT * FROM estoque WHERE id = " + id);
        return (estoques.isEmpty() ? null : estoques.get(0));
    }
    
    public Estoque retrieveByIdProduto(int id_produto) {
        List<Estoque> estoques = this.retrieve("SELECT * FROM estoque WHERE id_produto = " + id_produto);
        return (estoques.isEmpty() ? null : estoques.get(0));
    }

    public void update(Estoque estoque) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "UPDATE estoque SET quantidade=?, quantidade_minima=? WHERE id_produto=?"
            );
            stmt.setInt(1, estoque.getQuantidade());
            stmt.setInt(2, estoque.getQuantidadeMinima());
            stmt.setInt(3, estoque.getProduto().getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void delete(Estoque estoque) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM estoque WHERE id_produto = ?");
            stmt.setInt(1, estoque.getProduto().getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
