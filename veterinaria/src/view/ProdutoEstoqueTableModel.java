package view;

import java.util.List;

import javax.swing.JOptionPane;

import model.Estoque;
import model.EstoqueDAO;
import model.Produto;
import model.ProdutoDAO;

import controller.Controller;

public class ProdutoEstoqueTableModel extends GenericTableModel{

	public ProdutoEstoqueTableModel(List vDados){
		super(vDados, new String[]{"Nome", "Tipo", "Preço (Clique para editar)", "Quantidade (Clique para editar)", "Necessita reposição"});
	}

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = (Produto) vDados.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return produto.getNome();
            case 1:
                return produto.getTipo();
            case 2:
                return produto.getPreco();
            case 3:
                return EstoqueDAO.getInstance().retrieveByIdProduto(produto.getId()).getQuantidade();
            case 4:
            	if(EstoqueDAO.getInstance().retrieveByIdProduto(produto.getId()).isNecessitaReposicao())
            	{
            		return "SIM";
            	}
            	else
            	{
            		return "NÃO";
            	}
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Produto produto = (Produto) vDados.get(rowIndex);
        Estoque estoque = EstoqueDAO.getInstance().retrieveById(produto.getId());
        switch(columnIndex) {
        	case 2:
                try {
                    double preco = Double.parseDouble(aValue.toString());
                    produto.setPreco(preco);
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter o valor para double: " + e.getMessage());
                    return;
                }
                break;
        	case 3:
        		try {
        			int qtd = Integer.parseInt(aValue.toString());
        			if (qtd < 0) {
		                JOptionPane.showMessageDialog(null, "Os valores para preço e quantidades não podem ser negativos.");
		                return;
		            }
        			estoque.setQuantidade(qtd);
        		} catch(NumberFormatException e) {
        			System.err.println("Erro ao converter o valor para inteiro: " + e.getMessage());
        			return;
        		}
        		break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        ProdutoDAO.getInstance().update(produto);
        EstoqueDAO.getInstance().update(estoque);
    }  
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 2 || columnIndex == 3) return true;
        return false;
    }      
}
