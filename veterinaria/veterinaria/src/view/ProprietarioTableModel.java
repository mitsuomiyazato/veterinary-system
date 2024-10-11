package view;


import java.util.List;
import model.Proprietario;
import model.ProprietarioDAO;

public class ProprietarioTableModel extends GenericTableModel{
	private boolean editable = false;

	public ProprietarioTableModel(List vDados){
		super(vDados, new String[]{"Nome", "CPF", "Telefone", "Endereço"});
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
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proprietario proprietario = (Proprietario) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return proprietario.getNomeCompleto();
            case 1:
                return proprietario.getCpf();
            case 2:
                return proprietario.getTelefone();
            case 3:
                return proprietario.getEndereco();                
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Proprietario proprietario = (Proprietario) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
            	proprietario.setNomeCompleto((String)aValue);
                break;
            case 1:
            	proprietario.setCpf((String)aValue);
                break;
            case 2:
            	proprietario.setTelefone((String)aValue);    
                break;
            case 3:
                proprietario.setEndereco((String)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        ProprietarioDAO.getInstance().update(proprietario);
    }    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //if (columnIndex == 0) return false;
        return editable;
    }
    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
