package view;

import java.util.List;
import model.Agendamento;
import model.AgendamentoDAO;

public class AgendamentoTableModel extends GenericTableModel{

	public AgendamentoTableModel(List vDados){
		super(vDados, new String[]{"Data e Hora", "Serviço", "Veterinario", "Status"});
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
        Agendamento agendamento = (Agendamento) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return agendamento.getDataHora();
            case 1:
                return agendamento.getServico();
            case 2:
                return agendamento.getVeterinario().getNome();
            case 3:
                return agendamento.getStatus();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Agendamento agendamento = (Agendamento) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
            	//agendamento.setDataHora((LocalDateTime)aValue);
                break;
            case 1:
            	agendamento.setServico((String)aValue);
                break;
            case 2:
            	//agendamento.setVeterinario((String)aValue);;    
                break;
            case 3:
            	//agendamento.setStatus(null);    
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        AgendamentoDAO.getInstance().update(agendamento);
    }    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //if (columnIndex == 0) return false;
        return false;
    }      
}
