package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.EstoqueDAO;
import model.Produto;
import model.ProdutoDAO;
import model.VeterinarioDAO;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrarProdutoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomeProduto;
	private JTextField textFieldTipoProduto;
	private JTextField textFieldValorProduto;
	private JTextField textFieldQtdMinEstoque;
	private JTextField textFieldQtdEstoque;

	public RegistrarProdutoFrame(Principal principalFrame) {
		setTitle("Registrar Produto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldNomeProduto = new JTextField();
		textFieldNomeProduto.setBounds(118, 28, 306, 20);
		contentPane.add(textFieldNomeProduto);
		textFieldNomeProduto.setColumns(10);
		
		JLabel lblNomeProduto = new JLabel("Nome:");
		lblNomeProduto.setLabelFor(textFieldNomeProduto);
		lblNomeProduto.setBounds(22, 31, 86, 14);
		contentPane.add(lblNomeProduto);
		
		textFieldTipoProduto = new JTextField();
		textFieldTipoProduto.setColumns(10);
		textFieldTipoProduto.setBounds(118, 78, 306, 20);
		contentPane.add(textFieldTipoProduto);
		
		JLabel lblTipoProduto = new JLabel("Tipo:");
		lblTipoProduto.setLabelFor(textFieldTipoProduto);
		lblTipoProduto.setBounds(22, 81, 86, 14);
		contentPane.add(lblTipoProduto);
		
		textFieldValorProduto = new JTextField();
		textFieldValorProduto.setText("0.0");
		textFieldValorProduto.setColumns(10);
		textFieldValorProduto.setBounds(118, 132, 306, 20);
		contentPane.add(textFieldValorProduto);
		
		JLabel lblValorProduto = new JLabel("Valor:");
		lblValorProduto.setLabelFor(textFieldValorProduto);
		lblValorProduto.setBounds(22, 135, 86, 14);
		contentPane.add(lblValorProduto);
		
		JLabel lblQtdMinEstoque = new JLabel("Qtd mínima:");
		lblQtdMinEstoque.setBounds(22, 190, 86, 14);
		contentPane.add(lblQtdMinEstoque);
		
		textFieldQtdMinEstoque = new JTextField();
		textFieldQtdMinEstoque.setText("0");
		lblQtdMinEstoque.setLabelFor(textFieldQtdMinEstoque);
		textFieldQtdMinEstoque.setColumns(10);
		textFieldQtdMinEstoque.setBounds(118, 187, 306, 20);
		contentPane.add(textFieldQtdMinEstoque);
		
		JLabel lblQtdEstoque = new JLabel("Qtd estoque:");
		lblQtdEstoque.setBounds(22, 249, 86, 14);
		contentPane.add(lblQtdEstoque);
		
		textFieldQtdEstoque = new JTextField();
		textFieldQtdEstoque.setText("0");
		lblQtdEstoque.setLabelFor(textFieldQtdEstoque);
		textFieldQtdEstoque.setColumns(10);
		textFieldQtdEstoque.setBounds(118, 246, 306, 20);
		contentPane.add(textFieldQtdEstoque);
		
		JButton btnRegistrarProduto = new JButton("Registrar");
		btnRegistrarProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!textFieldNomeProduto.getText().isEmpty() &&
			            !textFieldTipoProduto.getText().isEmpty() &&
			            !textFieldValorProduto.getText().isEmpty() &&
			            !textFieldQtdMinEstoque.getText().isEmpty() &&
			            !textFieldQtdEstoque.getText().isEmpty()) {
			        try {
			            double valorProduto = Double.parseDouble(textFieldValorProduto.getText());
			            int qtdEstoque = Integer.parseInt(textFieldQtdEstoque.getText());
			            int qtdMinEstoque = Integer.parseInt(textFieldQtdMinEstoque.getText());

			            if (valorProduto < 0 || qtdEstoque < 0 || qtdMinEstoque < 0) {
			                JOptionPane.showMessageDialog(null, "Os valores para preço e quantidades não podem ser negativos.");
			                return;
			            }

			            Produto P = ProdutoDAO.getInstance().create(
			                textFieldNomeProduto.getText(),
			                textFieldTipoProduto.getText(),
			                valorProduto);
			            EstoqueDAO.getInstance().create(P, qtdEstoque, qtdMinEstoque);
			            JOptionPane.showMessageDialog(null, "Produto registrado com sucesso!");
			            principalFrame.atualizarTableProdutoEstoque();
			            dispose();

			        } catch (NumberFormatException ex) {
			            JOptionPane.showMessageDialog(null, "Por favor, insira valores numéricos válidos para preço e quantidades.");
			        }
			    } else {
			        JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
			    }
			}
		});
		btnRegistrarProduto.setBounds(168, 290, 89, 23);
		contentPane.add(btnRegistrarProduto);
		
	}
}
