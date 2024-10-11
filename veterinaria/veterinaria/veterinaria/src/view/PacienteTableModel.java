package view;

import java.util.List;
import model.Paciente;

public class PacienteTableModel extends GenericTableModel{

	public PacienteTableModel(List vDados){
		super(vDados, new String[]{"Nome", "Especie", "Idade", "Castração", "Coloração", "Raça"});
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
            case 5:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Paciente paciente = (Paciente) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return paciente.getNome();
            case 1:
                return paciente.getEspecie();
            case 2:
                return paciente.getIdade();
            case 3:
                return paciente.getEstadoCastracao();
            case 4:
                return paciente.getColoracao();
            case 5:
                return paciente.getRaca();
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
