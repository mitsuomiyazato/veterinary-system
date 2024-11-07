package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.HistoricoDAO;
import model.Paciente;
import model.PacienteDAO;
import model.Proprietario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;

import javax.swing.JComboBox;

public class RegistrarPacienteFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel RegistrarPaciente;
	private JTextField textFieldRegistrarNome;
	private JLabel lblRegistrarEspecie;
	private JTextField textFieldRegistrarIdade;
	private JLabel lblRegistrarIdade;
	private JLabel lblRegistrarCastracao;
	private JTextField textFieldRegistrarRaca;
	private JTextField textFieldRegistrarColoracao;
	private JLabel lblRegistrarColoracao;
	private JLabel lblRegistrarRaca;
	private JComboBox<String> comboBoxRegistrarCastracao;
	private JButton btnRegistrarPaciente;
	private JTextField textFieldRegistrarEspecie;

	public RegistrarPacienteFrame(Principal principalFrame, Proprietario cliente, Paciente paciente) {
		buildJFrame(principalFrame);
		setResizable(false);
		textFieldRegistrarNome.setText(paciente.getNome());
		textFieldRegistrarEspecie.setText(paciente.getEspecie());
		textFieldRegistrarIdade.setText(paciente.getIdade() + "");
		textFieldRegistrarRaca.setText(paciente.getRaca());
		textFieldRegistrarColoracao.setText(paciente.getColoracao());
		textFieldRegistrarRaca.setText(paciente.getRaca());
		
		if(paciente.getEstadoCastracao() == Paciente.EstadoCastracao.CASTRADO)
		{
			comboBoxRegistrarCastracao.setSelectedIndex(0);
		}
		else if(paciente.getEstadoCastracao() == Paciente.EstadoCastracao.FERTIL)
		{
			comboBoxRegistrarCastracao.setSelectedIndex(1);
		}
		else
		{
			comboBoxRegistrarCastracao.setSelectedIndex(2);
		}
		
		btnRegistrarPaciente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!textFieldRegistrarNome.getText().isEmpty() &&
			            !textFieldRegistrarEspecie.getText().isEmpty() &&
			            !textFieldRegistrarIdade.getText().isEmpty() &&
			            !textFieldRegistrarColoracao.getText().isEmpty() &&
			            !textFieldRegistrarRaca.getText().isEmpty()) 
			        {
	                	try {
	                		int idade = Integer.parseInt(textFieldRegistrarIdade.getText());
	                		Paciente.EstadoCastracao castracao;
	                		if(comboBoxRegistrarCastracao.getSelectedItem().toString().equals("Castrado"))
	                		{
	                			castracao = Paciente.EstadoCastracao.CASTRADO;
	                		} else if(comboBoxRegistrarCastracao.getSelectedItem().toString().equals("Fértil"))
	                		{
	                			castracao = Paciente.EstadoCastracao.FERTIL;
	                		} else
	                		{
	                			castracao = Paciente.EstadoCastracao.DESCONHECIDO;
	                		}
	               
	                		if(idade < 0)
	                		{
	                			JOptionPane.showMessageDialog(null, "Por favor, insira uma idade válida.", "Erro", JOptionPane.ERROR_MESSAGE);
	                		}
	                		else
	                		{
	                			Paciente p = new Paciente(paciente.getId(), textFieldRegistrarNome.getText(), textFieldRegistrarEspecie.getText(), textFieldRegistrarRaca.getText(), idade, textFieldRegistrarColoracao.getText(), castracao, cliente);
	                			PacienteDAO.getInstance().update(p);
	                			JOptionPane.showMessageDialog(null, "Paciente editado com sucesso!");
	                			principalFrame.atualizarTablePacienteClienteSelecionado();
	                			principalFrame.clearAll();
	        			        dispose();
	                		}
	                	} catch (NumberFormatException ex) {
	                		JOptionPane.showMessageDialog(null, "Por favor, insira uma idade válida.", "Erro", JOptionPane.ERROR_MESSAGE);
	                	}
			        } 
			        else 
			        {
			            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
			        }
			}
		});
	}
	
	public RegistrarPacienteFrame(Principal principalFrame, Proprietario cliente) {
		buildJFrame(principalFrame);
		btnRegistrarPaciente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!textFieldRegistrarNome.getText().isEmpty() &&
			            !textFieldRegistrarEspecie.getText().isEmpty() &&
			            !textFieldRegistrarIdade.getText().isEmpty() &&
			            !textFieldRegistrarColoracao.getText().isEmpty() &&
			            !textFieldRegistrarRaca.getText().isEmpty()) 
			        {
	                	try {
	                		int idade = Integer.parseInt(textFieldRegistrarIdade.getText());
	                		Paciente.EstadoCastracao castracao;
	                		if(comboBoxRegistrarCastracao.getSelectedItem().toString().equals("Castrado"))
	                		{
	                			castracao = Paciente.EstadoCastracao.CASTRADO;
	                		} else if(comboBoxRegistrarCastracao.getSelectedItem().toString().equals("Fértil"))
	                		{
	                			castracao = Paciente.EstadoCastracao.FERTIL;
	                		} else
	                		{
	                			castracao = Paciente.EstadoCastracao.DESCONHECIDO;
	                		}
	               
	                		if(idade < 0)
	                		{
	                			JOptionPane.showMessageDialog(null, "Por favor, insira uma idade válida.", "Erro", JOptionPane.ERROR_MESSAGE);
	                		}
	                		else
	                		{
	                			Paciente p = PacienteDAO.getInstance().create(
	        			                textFieldRegistrarNome.getText(),
	        			                textFieldRegistrarEspecie.getText(),
	        			                textFieldRegistrarRaca.getText(),
	        			                idade,
	        			                textFieldRegistrarColoracao.getText(),
	        			                castracao,
	        			                cliente
	        			            );
	                			HistoricoDAO.getInstance().create(p, Collections.emptyList(), Collections.emptyList(), "", "");
	                			JOptionPane.showMessageDialog(null, "Paciente registrado com sucesso!");
	        			        principalFrame.atualizarTablePacienteClienteSelecionado();
	        			        dispose();
	                		}
	                	} catch (NumberFormatException ex) {
	                		JOptionPane.showMessageDialog(null, "Por favor, insira uma idade válida.", "Erro", JOptionPane.ERROR_MESSAGE);
	                	}
			        } 
			        else 
			        {
			            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
			        }
			}
		});
	}
	
	public void buildJFrame(Principal principalFrame) {
		setTitle("Registrar Paciente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 394);
		RegistrarPaciente = new JPanel();
		RegistrarPaciente.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(RegistrarPaciente);
		RegistrarPaciente.setLayout(null);
		
		JLabel lblRegistrarNome = new JLabel("Nome:");
		lblRegistrarNome.setBounds(10, 29, 132, 14);
		RegistrarPaciente.add(lblRegistrarNome);
		
		textFieldRegistrarNome = new JTextField();
		lblRegistrarNome.setLabelFor(textFieldRegistrarNome);
		textFieldRegistrarNome.setBounds(153, 26, 273, 20);
		RegistrarPaciente.add(textFieldRegistrarNome);
		textFieldRegistrarNome.setColumns(10);
		
		textFieldRegistrarEspecie = new JTextField();
		textFieldRegistrarEspecie.setColumns(10);
		textFieldRegistrarEspecie.setBounds(153, 73, 273, 20);
		RegistrarPaciente.add(textFieldRegistrarEspecie);
		
		lblRegistrarEspecie = new JLabel("Especie:");
		lblRegistrarEspecie.setLabelFor(textFieldRegistrarEspecie);
		lblRegistrarEspecie.setBounds(10, 76, 132, 14);
		RegistrarPaciente.add(lblRegistrarEspecie);
		
		textFieldRegistrarIdade = new JTextField();
		textFieldRegistrarIdade.setColumns(10);
		textFieldRegistrarIdade.setBounds(153, 121, 273, 20);
		RegistrarPaciente.add(textFieldRegistrarIdade);
		
		lblRegistrarIdade = new JLabel("Idade:");
		lblRegistrarIdade.setLabelFor(textFieldRegistrarIdade);
		lblRegistrarIdade.setBounds(10, 124, 132, 14);
		RegistrarPaciente.add(lblRegistrarIdade);
		
		lblRegistrarCastracao = new JLabel("Castração:");
		lblRegistrarCastracao.setBounds(10, 174, 132, 14);
		RegistrarPaciente.add(lblRegistrarCastracao);
		
		btnRegistrarPaciente = new JButton("Registrar");
		btnRegistrarPaciente.setBounds(169, 317, 89, 23);
		RegistrarPaciente.add(btnRegistrarPaciente);
		
		textFieldRegistrarRaca = new JTextField();
		textFieldRegistrarRaca.setColumns(10);
		textFieldRegistrarRaca.setBounds(153, 272, 273, 20);
		RegistrarPaciente.add(textFieldRegistrarRaca);
		
		textFieldRegistrarColoracao = new JTextField();
		textFieldRegistrarColoracao.setColumns(10);
		textFieldRegistrarColoracao.setBounds(153, 222, 273, 20);
		RegistrarPaciente.add(textFieldRegistrarColoracao);
		
		lblRegistrarColoracao = new JLabel("Coloração:");
		lblRegistrarColoracao.setBounds(10, 225, 132, 14);
		RegistrarPaciente.add(lblRegistrarColoracao);
		
		lblRegistrarRaca = new JLabel("Raça:");
		lblRegistrarRaca.setBounds(10, 275, 132, 14);
		RegistrarPaciente.add(lblRegistrarRaca);
		
		comboBoxRegistrarCastracao = new JComboBox<String>();
		comboBoxRegistrarCastracao.setBounds(153, 170, 273, 22);
		
		comboBoxRegistrarCastracao.addItem("Castrado");
		comboBoxRegistrarCastracao.addItem("Fértil");
		comboBoxRegistrarCastracao.addItem("Desconhecido");
		
		RegistrarPaciente.add(comboBoxRegistrarCastracao);
	}
}
