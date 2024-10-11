package controller;

import javax.swing.JTable;
import javax.swing.JTextField;

import view.GenericTableModel;
import model.Paciente;
import model.Proprietario;
import model.Veterinario;

public class Controller {
		private static Proprietario clienteSelecionado = null;
		private static Paciente pacienteSelecionado = null;
		private static Veterinario veterinarioSelecionado = null;
		private static JTextField clienteSelecionadoTextField = null;
		private static JTextField pacienteSelecionadoTextField = null;
		private static JTextField pacienteEspecieSelecionadoTextField = null;
		private static JTextField agendamentoPacienteSelecionadoTextField = null;
		private static JTextField veterinarioSelecionadoTextField = null;
		private static JTextField veterinarioSelecionadoReceita = null;
		
		public static void setTextFields(JTextField cliente, JTextField paciente, JTextField especie, JTextField pacienteAgendamento, JTextField veterinario, JTextField veterinarioReceita) {
			clienteSelecionadoTextField = cliente;
			pacienteSelecionadoTextField = paciente;
			pacienteEspecieSelecionadoTextField = especie;
			agendamentoPacienteSelecionadoTextField = pacienteAgendamento;
			veterinarioSelecionadoTextField = veterinario;
			veterinarioSelecionadoReceita = veterinarioReceita;
		}
		
		public static void setTableModel(JTable table, GenericTableModel tableModel) {
			table.setModel(tableModel);
		}
		
		public static Proprietario getClienteSelecionado(){
			return clienteSelecionado;
		}
		public static void setClienteSelecionadoNull(){
			clienteSelecionado = null;
		}
		
		public static Paciente getPacienteSelecionado(){
			return pacienteSelecionado;
		}
		public static void setPacienteSelecionadoNull(){
			clienteSelecionado = null;
		}
		
		public static Veterinario getVeterinarioSelecionado() {
			return veterinarioSelecionado;
		}
		
		public static void setVeterinarioSelecionadoNull(){
			veterinarioSelecionado = null;
		}
		
		public static void setSelected(Object selected) {
			if(selected instanceof Proprietario) {
				clienteSelecionado = (Proprietario)selected;
				clienteSelecionadoTextField.setText(clienteSelecionado.getNomeCompleto());
				pacienteSelecionadoTextField.setText("");
				pacienteEspecieSelecionadoTextField.setText("");
				agendamentoPacienteSelecionadoTextField.setText("");
			} else if (selected instanceof Paciente) {
				pacienteSelecionado = (Paciente)selected;
				pacienteSelecionadoTextField.setText(pacienteSelecionado.getNome());
				pacienteEspecieSelecionadoTextField.setText(pacienteSelecionado.getEspecie());
				agendamentoPacienteSelecionadoTextField.setText(pacienteSelecionado.getNome());
			} else if (selected instanceof Veterinario) {
				veterinarioSelecionado = (Veterinario)selected;
				veterinarioSelecionadoTextField.setText(veterinarioSelecionado.getNome());
				veterinarioSelecionadoReceita.setText(veterinarioSelecionado.getNome());
			}
		}
	
}
