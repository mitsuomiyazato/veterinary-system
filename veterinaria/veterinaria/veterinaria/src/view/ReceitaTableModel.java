package view;

import java.util.List;
import model.ReceitaMedica;

public class ReceitaTableModel extends GenericTableModel{

	public ReceitaTableModel(List vDados){
		super(vDados, new String[]{"Veterinário", "Observacoes", "Data"});
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
        ReceitaMedica receita = (ReceitaMedica) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return receita.getVeterinario().getNome();
            case 1:
                return receita.getObservacoes();
            case 2:
                return receita.getDataEmissao();                
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
