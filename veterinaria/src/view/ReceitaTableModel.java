package view;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import model.ReceitaMedica;

public class ReceitaTableModel extends GenericTableModel{

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public ReceitaTableModel(List vDados){
		super(vDados, new String[]{"Veterinário", "Observacoes", "Data e hora"});
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
            	LocalDateTime localDateTime = receita.getDataEmissao();
                Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                return dateFormat.format(date);
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
