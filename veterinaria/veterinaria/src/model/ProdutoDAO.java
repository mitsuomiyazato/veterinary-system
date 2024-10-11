package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO extends DAO {
    private static ProdutoDAO instance;

    private ProdutoDAO() {
        getConnection();
        createTable();
    }

    public static ProdutoDAO getInstance() {
        if (instance == null) {
            instance = new ProdutoDAO();
        }
        return instance;
    }

    public Produto create(String nome, String tipo, double preco) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "INSERT INTO produto (nome, tipo, preco) VALUES (?,?,?)"
            );
            stmt.setString(1, nome);
            stmt.setString(2, tipo);
            stmt.setDouble(3, preco);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("produto", "id"));
    }

    private Produto buildObject(ResultSet rs) {
        Produto produto = null;
        try {
            produto = new Produto(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("tipo"),
                rs.getDouble("preco")
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return produto;
    }

    public List<Produto> retrieve(String query) {
        List<Produto> produtos = new ArrayList<>();
        try (ResultSet rs = getResultSet(query)) {
            while (rs.next()) {
                produtos.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return produtos;
    }

    public List<Produto> retrieveAll() {
        return this.retrieve("SELECT * FROM produto");
    }

    public List<Produto> retrieveLast() {
        return this.retrieve("SELECT * FROM produto WHERE id = " + lastId("produto", "id"));
    }

    public Produto retrieveById(int id) {
        List<Produto> produtos = this.retrieve("SELECT * FROM produto WHERE id = " + id);
        return (produtos.isEmpty() ? null : produtos.get(0));
    }

    public List<Produto> retrieveBySimilarName(String nome) {
        List<Produto> produtos = new ArrayList<>();
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "SELECT * FROM produto WHERE nome LIKE ?"
            );
            stmt.setString(1, "%" + nome + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    produtos.add(buildObject(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return produtos;
    }

    public void update(Produto produto) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "UPDATE produto SET nome=?, tipo=?, preco=? WHERE id=?"
            );
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getTipo());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void delete(Produto produto) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "DELETE FROM produto WHERE id = ?"
            );
            stmt.setInt(1, produto.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
