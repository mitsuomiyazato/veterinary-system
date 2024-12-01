package view;

import java.util.List;
import model.Agendamento;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class AgendaInicioTableModel extends GenericTableModel {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public AgendaInicioTableModel(List vDados) {
        super(vDados, new String[]{"Cliente", "Paciente", "Veterinario", "Serviço", "Data"});
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
        Agendamento agendamento = (Agendamento) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return agendamento.getPaciente().getProprietario().getNomeCompleto();
            case 1:
                return agendamento.getPaciente().getNome();
            case 2:
                return agendamento.getVeterinario().getNome();
            case 3:
                return agendamento.getServico();
            case 4:
                LocalDateTime localDateTime = agendamento.getDataHora();
                Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                return dateFormat.format(date);
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
}
