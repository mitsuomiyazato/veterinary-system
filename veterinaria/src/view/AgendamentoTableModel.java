package view;

import java.text.SimpleDateFormat;
import java.util.List;
import model.Agendamento;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class AgendamentoTableModel extends GenericTableModel {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public AgendamentoTableModel(List vDados) {
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
                LocalDateTime localDateTime = agendamento.getDataHora();
                Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                return dateFormat.format(date); 
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
