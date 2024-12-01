package view;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.LocalDate;

import model.Fatura;

public class FaturaTableModel extends GenericTableModel{

	public FaturaTableModel(List vDados){
		super(vDados, new String[]{"Nome", "Valor", "Data de Vencimento", "Status"});
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
        Fatura fatura = (Fatura) vDados.get(rowIndex);

        switch (columnIndex) {
        	case 0:
            return fatura.getProprietario().getNomeCompleto();
            case 1:
                return fatura.getValorTotal();
            case 2:
            	LocalDate localDate = fatura.getDataVencimento();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return localDate.format(formatter);
            case 3:
                return fatura.getStatus();               
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //if (columnIndex == 0) return false;
        return false;
    }      
}
