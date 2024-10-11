package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO {
    
    public static final String DBURL = "jdbc:sqlite:veterinaria.db";
    private static Connection con;
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    
    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(DBURL);
                if (con != null) {
                    DatabaseMetaData meta = con.getMetaData();
                    System.out.println("Conectado ao banco de dados SQLite: " + meta.getDriverName());
                }
            } catch (SQLException e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }
        return con;
    }

    
    protected ResultSet getResultSet(String query) {
        Statement s;
        ResultSet rs = null;
        try {
            s = con.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return rs;
    }

    
    protected int executeUpdate(PreparedStatement queryStatement) throws SQLException {
        return queryStatement.executeUpdate();
    }

    
    protected int lastId(String tableName, String primaryKey) {
        Statement s;
        int lastId = -1;
        try {
            s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(" + primaryKey + ") AS id FROM " + tableName);
            if (rs.next()) {
                lastId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return lastId;
    }

    
    public static void terminar() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    
    protected final boolean createTable() {
        try {
            PreparedStatement stmt;
            
            
            stmt = DAO.getConnection().prepareStatement(
                "CREATE TABLE IF NOT EXISTS proprietario( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome_completo VARCHAR, " +
                "cpf VARCHAR, " +
                "telefone VARCHAR, " +
                "endereco VARCHAR );"
            );
            executeUpdate(stmt);

            
            stmt = DAO.getConnection().prepareStatement(
                "CREATE TABLE IF NOT EXISTS paciente( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR, " +
                "estado_castracao VARCHAR, " +
                "idade INTEGER, " +
                "raca VARCHAR, " +
                "coloracao VARCHAR, " +
                "id_proprietario INTEGER, " +
                "especie VARCHAR );"
            );
            executeUpdate(stmt);

            
            stmt = DAO.getConnection().prepareStatement(
                "CREATE TABLE IF NOT EXISTS historico( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_paciente INTEGER, " +
                "vacinas VARCHAR, " +
                "doencas VARCHAR, " +
                "peso VARCHAR, " +
                "observacoes VARCHAR );"
            );
            executeUpdate(stmt);

            
            stmt = DAO.getConnection().prepareStatement(
                "CREATE TABLE IF NOT EXISTS agendamento( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_paciente INTEGER, " +
                "data_hora TIMESTAMP, " +
                "servico VARCHAR, " +
                "status VARCHAR, " +
                "id_veterinario INTEGER );"
            );
            executeUpdate(stmt);

            
            stmt = DAO.getConnection().prepareStatement(
                "CREATE TABLE IF NOT EXISTS veterinario( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR, " +
                "email VARCHAR, " +
                "telefone VARCHAR );"
            );
            executeUpdate(stmt);

            
            stmt = DAO.getConnection().prepareStatement(
                "CREATE TABLE IF NOT EXISTS receita_medica( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_paciente INTEGER, " +
                "medicamentos VARCHAR, " +
                "data_emissao TIMESTAMP, " +
                "observacoes VARCHAR, " +
                "id_veterinario INTEGER );"
            );
            executeUpdate(stmt);

            
            stmt = DAO.getConnection().prepareStatement(
                "CREATE TABLE IF NOT EXISTS produto( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR, " +
                "tipo VARCHAR, " +
                "preco DOUBLE );"
            );
            executeUpdate(stmt);

            
            stmt = DAO.getConnection().prepareStatement(
                "CREATE TABLE IF NOT EXISTS estoque( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_produto INTEGER, " +
                "quantidade INTEGER, " +
                "quantidade_minima INTEGER, " +
                "necessita_reposicao BOOLEAN );"
            );
            executeUpdate(stmt);
            
            stmt = DAO.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS fatura( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "id_proprietario INTEGER, " +
                    "valor_total DOUBLE, " +
                    "status VARCHAR, " +
                    "data_vencimento DATE );"
            );
            executeUpdate(stmt);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
