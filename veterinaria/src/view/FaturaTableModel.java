package view;

import java.util.List;

import model.Fatura;
import model.ReceitaMedica;

public class FaturaTableModel extends GenericTableModel{

	public FaturaTableModel(List vDados){
		super(vDados, new String[]{"Valor", "Data de Vencimento", "Status"});
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
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fatura fatura = (Fatura) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return fatura.getValorTotal();
            case 1:
                return fatura.getDataVencimento();
            case 2:
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
