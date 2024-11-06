package view;

import java.util.List;
import model.Agendamento;

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
}
