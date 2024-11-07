package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Proprietario;
import model.ProprietarioDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrarClienteFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel RegistrarCliente;
	private JTextField textFieldRegistrarNomeCompleto;
	private JLabel lblRegistrarCPF;
	private JTextField textFieldRegistrarTelefone;
	private JLabel lblRegistrarTelefone;
	private JTextField textFieldRegistrarEndereco;
	private JLabel lblRegistrarEndereco;
	private JTextField textFieldRegistrarCPF;
	private JButton btnRegistrarCliente;

	public RegistrarClienteFrame(Principal principalFrame, Proprietario cliente)
	{
		buildJFrame(principalFrame);
		setResizable(false);
		textFieldRegistrarNomeCompleto.setText(cliente.getNomeCompleto());
		textFieldRegistrarCPF.setText(cliente.getCpf());
		textFieldRegistrarTelefone.setText(cliente.getTelefone());
		textFieldRegistrarEndereco.setText(cliente.getEndereco());
		
		btnRegistrarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!textFieldRegistrarNomeCompleto.getText().isEmpty() &&
			            !textFieldRegistrarCPF.getText().isEmpty() &&
			            !textFieldRegistrarTelefone.getText().isEmpty() &&
			            !textFieldRegistrarEndereco.getText().isEmpty()) 
			        {
						Proprietario p = new Proprietario(cliente.getId(), textFieldRegistrarNomeCompleto.getText(), textFieldRegistrarCPF.getText(), textFieldRegistrarTelefone.getText(), textFieldRegistrarEndereco.getText());
			            ProprietarioDAO.getInstance().update(p);
			            JOptionPane.showMessageDialog(null, "Cliente editado com sucesso!");
			            principalFrame.clearAll();
			            principalFrame.atualizarTableCliente();
			            dispose();
			        } 
			        else 
			        {
			            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
			        }
			}
		});
		ProprietarioDAO.getInstance().update(cliente);
	}
	
	public RegistrarClienteFrame(Principal principalFrame) {
		buildJFrame(principalFrame);
		btnRegistrarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!textFieldRegistrarNomeCompleto.getText().isEmpty() &&
			            !textFieldRegistrarCPF.getText().isEmpty() &&
			            !textFieldRegistrarTelefone.getText().isEmpty() &&
			            !textFieldRegistrarEndereco.getText().isEmpty()) 
			        {
			            ProprietarioDAO.getInstance().create(
			                textFieldRegistrarNomeCompleto.getText(),
			                textFieldRegistrarCPF.getText(),
			                textFieldRegistrarTelefone.getText(),
			                textFieldRegistrarEndereco.getText()
			            );
			            JOptionPane.showMessageDialog(null, "Cliente registrado com sucesso!");
			            principalFrame.atualizarTableCliente();
			            dispose();
			        } 
			        else 
			        {
			            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
			        }
			}
		});
	}
	
	public void buildJFrame(Principal principalFrame) {
		setTitle("Registrar Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 282);
		RegistrarCliente = new JPanel();
		RegistrarCliente.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(RegistrarCliente);
		RegistrarCliente.setLayout(null);
		
		JLabel lblRegistrarNome = new JLabel("Nome Completo:");
		lblRegistrarNome.setBounds(10, 29, 132, 14);
		RegistrarCliente.add(lblRegistrarNome);
		
		textFieldRegistrarNomeCompleto = new JTextField();
		lblRegistrarNome.setLabelFor(textFieldRegistrarNomeCompleto);
		textFieldRegistrarNomeCompleto.setBounds(153, 26, 273, 20);
		RegistrarCliente.add(textFieldRegistrarNomeCompleto);
		textFieldRegistrarNomeCompleto.setColumns(10);
		
		textFieldRegistrarCPF = new JTextField();
		textFieldRegistrarCPF.setColumns(10);
		textFieldRegistrarCPF.setBounds(153, 73, 273, 20);
		RegistrarCliente.add(textFieldRegistrarCPF);
		
		lblRegistrarCPF = new JLabel("CPF:");
		lblRegistrarCPF.setLabelFor(textFieldRegistrarCPF);
		lblRegistrarCPF.setBounds(10, 76, 132, 14);
		RegistrarCliente.add(lblRegistrarCPF);
		
		textFieldRegistrarTelefone = new JTextField();
		textFieldRegistrarTelefone.setColumns(10);
		textFieldRegistrarTelefone.setBounds(153, 121, 273, 20);
		RegistrarCliente.add(textFieldRegistrarTelefone);
		
		lblRegistrarTelefone = new JLabel("Telefone:");
		lblRegistrarTelefone.setLabelFor(textFieldRegistrarTelefone);
		lblRegistrarTelefone.setBounds(10, 124, 132, 14);
		RegistrarCliente.add(lblRegistrarTelefone);
		
		textFieldRegistrarEndereco = new JTextField();
		textFieldRegistrarEndereco.setColumns(10);
		textFieldRegistrarEndereco.setBounds(153, 171, 273, 20);
		RegistrarCliente.add(textFieldRegistrarEndereco);
		
		lblRegistrarEndereco = new JLabel("Endereço:");
		lblRegistrarEndereco.setLabelFor(textFieldRegistrarEndereco);
		lblRegistrarEndereco.setBounds(10, 174, 132, 14);
		RegistrarCliente.add(lblRegistrarEndereco);
		
		btnRegistrarCliente = new JButton("Registrar");
		btnRegistrarCliente.setBounds(176, 209, 89, 23);
		RegistrarCliente.add(btnRegistrarCliente);
	}
}
