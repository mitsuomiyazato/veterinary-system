package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Veterinario;
import model.VeterinarioDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrarVeterinarioFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel RegistrarVeterinario;
	private JTextField textFieldRegistrarNome;
	private JLabel lblRegistrarEmail;
	private JTextField textFieldRegistrarTelefone;
	private JLabel lblRegistrarTelefone;
	private JButton btnRegistrarVeterinario;
	private JTextField textFieldRegistrarEmail;

	public RegistrarVeterinarioFrame(Principal principalFrame, Veterinario veterinario)
	{
		buildJFrame(principalFrame);
		textFieldRegistrarNome.setText(veterinario.getNome());
		textFieldRegistrarTelefone.setText(veterinario.getTelefone());
		textFieldRegistrarEmail.setText(veterinario.getEmail());
		
		btnRegistrarVeterinario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!textFieldRegistrarNome.getText().isEmpty() &&
			            !textFieldRegistrarEmail.getText().isEmpty() &&
			            !textFieldRegistrarTelefone.getText().isEmpty()) 
			        {
						Veterinario v = new Veterinario(veterinario.getId(), textFieldRegistrarNome.getText(), textFieldRegistrarEmail.getText(), textFieldRegistrarTelefone.getText());
			            VeterinarioDAO.getInstance().update(v);
			            JOptionPane.showMessageDialog(null, "Veterinario editado com sucesso!");
			            principalFrame.atualizarTableVeterinario();
			            dispose();
			        } 
			        else 
			        {
			            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
			        }
			}
		});
	}
	
	public RegistrarVeterinarioFrame(Principal principalFrame) {
		buildJFrame(principalFrame);
		btnRegistrarVeterinario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!textFieldRegistrarNome.getText().isEmpty() &&
			            !textFieldRegistrarEmail.getText().isEmpty() &&
			            !textFieldRegistrarTelefone.getText().isEmpty()) 
			        {
			            VeterinarioDAO.getInstance().create(
			                textFieldRegistrarNome.getText(),
			                textFieldRegistrarEmail.getText(),
			                textFieldRegistrarTelefone.getText()
			            );
			            JOptionPane.showMessageDialog(null, "Veterinario registrado com sucesso!");
			            principalFrame.atualizarTableVeterinario();
			            dispose();
			        } 
			        else 
			        {
			            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
			        }
			}
		});
	}
	
	public void buildJFrame(Principal principalFrame)
	{
		setTitle("Registrar Veterinario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 454, 249);
		RegistrarVeterinario = new JPanel();
		RegistrarVeterinario.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(RegistrarVeterinario);
		RegistrarVeterinario.setLayout(null);
		
		JLabel lblRegistrarNome = new JLabel("Nome:");
		lblRegistrarNome.setBounds(10, 29, 132, 14);
		RegistrarVeterinario.add(lblRegistrarNome);
		
		textFieldRegistrarNome = new JTextField();
		lblRegistrarNome.setLabelFor(textFieldRegistrarNome);
		textFieldRegistrarNome.setBounds(153, 26, 273, 20);
		RegistrarVeterinario.add(textFieldRegistrarNome);
		textFieldRegistrarNome.setColumns(10);
		
		textFieldRegistrarEmail = new JTextField();
		textFieldRegistrarEmail.setColumns(10);
		textFieldRegistrarEmail.setBounds(153, 73, 273, 20);
		RegistrarVeterinario.add(textFieldRegistrarEmail);
		
		lblRegistrarEmail = new JLabel("E-mail:");
		lblRegistrarEmail.setLabelFor(textFieldRegistrarEmail);
		lblRegistrarEmail.setBounds(10, 76, 132, 14);
		RegistrarVeterinario.add(lblRegistrarEmail);
		
		textFieldRegistrarTelefone = new JTextField();
		textFieldRegistrarTelefone.setColumns(10);
		textFieldRegistrarTelefone.setBounds(153, 121, 273, 20);
		RegistrarVeterinario.add(textFieldRegistrarTelefone);
		
		lblRegistrarTelefone = new JLabel("Telefone:");
		lblRegistrarTelefone.setLabelFor(textFieldRegistrarTelefone);
		lblRegistrarTelefone.setBounds(10, 124, 132, 14);
		RegistrarVeterinario.add(lblRegistrarTelefone);
		
		btnRegistrarVeterinario = new JButton("Registrar");
		btnRegistrarVeterinario.setBounds(174, 176, 89, 23);
		RegistrarVeterinario.add(btnRegistrarVeterinario);
	}
}
