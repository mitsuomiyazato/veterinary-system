package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.ProprietarioDAO;
import model.ReceitaMedicaDAO;
import model.VeterinarioDAO;
import model.Agendamento;
import model.AgendamentoDAO;
import model.EstoqueDAO;
import model.Fatura;
import model.FaturaDAO;
import model.Historico;
import model.HistoricoDAO;
import model.PacienteDAO;
import model.ProdutoDAO;
import model.Proprietario;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import java.awt.Component;

public class Principal {

	public JFrame frmVeterinria;
	private JTextField textFieldClienteSelecionado;
	private JTextField textFieldPacienteSelecionado;
	private JTextField textFieldEspecieSelecionada;
	private JButton btnEditarHistorico;
	private JComboBox<String> comboBoxHistoricoVacinas;
	private JComboBox<String> comboBoxHistoricoDoencas;
	private JTextField textFieldHistoricoPeso;
	private JTextArea textAreaHistoricoObservacoes;
	private int finalizado = 0;
	
	private JButton btnAddVacinas;
	private JButton btnDelVacinas;
	private JButton btnAddDoencas;
	private JButton btnDelDoencas;
	
	private JTextField textFieldBuscarCliente;
	private JTextField textFieldBuscarPaciente;
	private JTable tableCliente;
	
	private JTextField textFieldBuscarVeterinario;
	private JTable tableVeterinario;
	private JTable tableAgendamento;
	private JTextField textFieldAgendamentoPaciente;
	private JTextField textFieldAgendamentoVeterinario;
	private JTextField textFieldAgendamentoServico;
	private JTable tablePaciente;
	
	private JFormattedTextField formattedTextFieldDataAgendamento;
	private JFormattedTextField formattedTextFieldAgendamentoHora;
	
	private JTable tableReceita;
	
	private JTextField textFieldVeterinarioSelecionadoReceita;
	private JComboBox comboBoxMedicamentosReceita;
	private JTextArea textAreaObservacoesReceita;
	private LocalDateTime now;
	
	private JTextField textFieldBuscarProduto;
	private JTable tableProdutoEstoque;
	
	private JTable tableFatura;

	/**
	 * Launch the application.
	 */

	public Principal() {
		initialize();
		this.myInitComponents();
	}
	
	private void myInitComponents() {
		Controller.setTableModel(tableCliente, new ProprietarioTableModel(ProprietarioDAO.getInstance().retrieveAll()));
		Controller.setTextFields(textFieldClienteSelecionado, textFieldPacienteSelecionado, textFieldEspecieSelecionada, textFieldAgendamentoPaciente, textFieldAgendamentoVeterinario, textFieldVeterinarioSelecionadoReceita);
		Controller.setTableModel(tableVeterinario, new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveAllActive()));
		Controller.setTableModel(tableProdutoEstoque, new ProdutoEstoqueTableModel(ProdutoDAO.getInstance().retrieveAll()));
		Controller.setTableModel(tableFatura, new FaturaTableModel(FaturaDAO.getInstance().retrieveAll()));
	}
	
	public void atualizarTableCliente() {
		Controller.setTableModel(tableCliente, new ProprietarioTableModel(ProprietarioDAO.getInstance().retrieveAll()));
	}
	public void atualizarTablePacienteClienteSelecionado() {
		Controller.setTableModel(tablePaciente, new PacienteTableModel(PacienteDAO.getInstance().retrieveByIdProprietario(Controller.getClienteSelecionado().getId())));
	}
	public void atualizarTableVeterinario() {
		Controller.setTableModel(tableVeterinario, new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveAllActive()));
	}
	public void atualizarTableProdutoEstoque() {
		Controller.setTableModel(tableProdutoEstoque, new ProdutoEstoqueTableModel(ProdutoDAO.getInstance().retrieveAll()));
	}
	public void atualizarTableFatura() {
		Controller.setTableModel(tableFatura, new FaturaTableModel(FaturaDAO.getInstance().retrieveByIdProprietario(Controller.getClienteSelecionado().getId())));
	}
	
	public void clearAll() {
		textFieldClienteSelecionado.setText("");
		textFieldPacienteSelecionado.setText("");
		textFieldEspecieSelecionada.setText("");
	    comboBoxHistoricoVacinas.removeAllItems();
	    comboBoxHistoricoDoencas.removeAllItems();
	    textFieldHistoricoPeso.setText("");
	    textAreaHistoricoObservacoes.setText("");
	    btnEditarHistorico.setEnabled(false);
	    
		textFieldAgendamentoPaciente.setText("");
		textFieldAgendamentoVeterinario.setText("");
		textFieldAgendamentoServico.setText("");
		formattedTextFieldDataAgendamento.setText("");
		formattedTextFieldAgendamentoHora.setText("");
		
		textAreaObservacoesReceita.setText("");
		comboBoxMedicamentosReceita.removeAllItems();
		
		Controller.setClienteSelecionadoNull();
		Controller.setPacienteSelecionadoNull();
		Controller.setAgendamentoSelecionadoNull();
		Controller.setTableModel(tableAgendamento, new AgendamentoTableModel(AgendamentoDAO.getInstance().returnEmptyList()));
		Controller.setTableModel(tableReceita, new ReceitaTableModel(ReceitaMedicaDAO.getInstance().returnEmptyList()));
	}
	
	private void initialize() {
		
		//INÍCIO JFRAME PRINCIPAL
		frmVeterinria = new JFrame();
		frmVeterinria.getContentPane().setBackground(Color.WHITE);
		frmVeterinria.setResizable(false);
		frmVeterinria.setTitle("Veterinária");
		frmVeterinria.setBounds(100, 100, 869, 783);
		frmVeterinria.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVeterinria.getContentPane().setLayout(null);
		
		
		//INFORMACOES SELECIONADAS
		JPanel informacaoSelecionada = new JPanel();
		informacaoSelecionada.setBackground(Color.WHITE);
		informacaoSelecionada.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Informa\u00E7\u00F5es Selecionadas", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		informacaoSelecionada.setBounds(10, 11, 833, 231);
		frmVeterinria.getContentPane().add(informacaoSelecionada);
		informacaoSelecionada.setLayout(null);
		
		JLabel lblClienteSelecionado = new JLabel("Cliente:");
		lblClienteSelecionado.setBounds(10, 30, 62, 14);
		informacaoSelecionada.add(lblClienteSelecionado);
		textFieldClienteSelecionado = new JTextField();
		textFieldClienteSelecionado.setBackground(new Color(38, 166, 153));
		textFieldClienteSelecionado.setEditable(false);
		lblClienteSelecionado.setLabelFor(textFieldClienteSelecionado);
		textFieldClienteSelecionado.setBounds(82, 27, 189, 20);
		informacaoSelecionada.add(textFieldClienteSelecionado);
		textFieldClienteSelecionado.setColumns(10);
		
		JLabel lblPacienteSelecionado = new JLabel("Paciente:");
		lblPacienteSelecionado.setBounds(281, 30, 66, 14);
		informacaoSelecionada.add(lblPacienteSelecionado);
		textFieldPacienteSelecionado = new JTextField();
		textFieldPacienteSelecionado.setBackground(new Color(38, 166, 153));
		textFieldPacienteSelecionado.setEditable(false);
		lblPacienteSelecionado.setLabelFor(textFieldPacienteSelecionado);
		textFieldPacienteSelecionado.setColumns(10);
		textFieldPacienteSelecionado.setBounds(357, 27, 189, 20);
		informacaoSelecionada.add(textFieldPacienteSelecionado);
		
		JLabel lblEspecieSelecionada = new JLabel("Espécie:");
		lblEspecieSelecionada.setLabelFor(textFieldEspecieSelecionada);
		lblEspecieSelecionada.setBounds(556, 30, 68, 14);
		informacaoSelecionada.add(lblEspecieSelecionada);
		textFieldEspecieSelecionada = new JTextField();
		textFieldEspecieSelecionada.setBackground(new Color(38, 166, 153));
		textFieldEspecieSelecionada.setEditable(false);
		textFieldEspecieSelecionada.setColumns(10);
		textFieldEspecieSelecionada.setBounds(634, 27, 189, 20);
		informacaoSelecionada.add(textFieldEspecieSelecionada);
		
		JPanel panelHistoricoPacienteSelecionado = new JPanel();
		panelHistoricoPacienteSelecionado.setBackground(Color.WHITE);
		panelHistoricoPacienteSelecionado.setBorder(new TitledBorder(null, "Historico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelHistoricoPacienteSelecionado.setBounds(10, 55, 813, 165);
		informacaoSelecionada.add(panelHistoricoPacienteSelecionado);
		panelHistoricoPacienteSelecionado.setLayout(null);
		
		JLabel lblHistoricoVacinas =new JLabel("Vacinas:");
		lblHistoricoVacinas.setBounds(10, 22, 70, 14);
		panelHistoricoPacienteSelecionado.add(lblHistoricoVacinas);
		comboBoxHistoricoVacinas = new JComboBox();
		comboBoxHistoricoVacinas.setBackground(new Color(129, 226, 217));
		lblHistoricoVacinas.setLabelFor(comboBoxHistoricoVacinas);
		comboBoxHistoricoVacinas.setBounds(90, 18, 206, 22);
		panelHistoricoPacienteSelecionado.add(comboBoxHistoricoVacinas);
		
		JLabel lblHistoricoDoencas = new JLabel("Doenças:");
		lblHistoricoDoencas.setLabelFor(comboBoxHistoricoDoencas);
		lblHistoricoDoencas.setBounds(10, 78, 70, 14);
		panelHistoricoPacienteSelecionado.add(lblHistoricoDoencas);
		comboBoxHistoricoDoencas = new JComboBox();
		comboBoxHistoricoDoencas.setBackground(new Color(129, 226, 217));
		comboBoxHistoricoDoencas.setBounds(90, 74, 206, 22);
		panelHistoricoPacienteSelecionado.add(comboBoxHistoricoDoencas);
		
		JLabel lblHistoricoPeso = new JLabel("Peso:");
		lblHistoricoPeso.setBounds(10, 140, 70, 14);
		panelHistoricoPacienteSelecionado.add(lblHistoricoPeso);
		textFieldHistoricoPeso = new JTextField();
		textFieldHistoricoPeso.setBackground(new Color(129, 226, 217));
		textFieldHistoricoPeso.setEditable(false);
		lblHistoricoPeso.setLabelFor(textFieldHistoricoPeso);
		textFieldHistoricoPeso.setBounds(90, 137, 206, 20);
		panelHistoricoPacienteSelecionado.add(textFieldHistoricoPeso);
		textFieldHistoricoPeso.setColumns(10);
		
		JLabel lblHistoricoObservacao = new JLabel("Observações:");
		lblHistoricoObservacao.setBounds(475, 22, 115, 14);
		panelHistoricoPacienteSelecionado.add(lblHistoricoObservacao);
		textAreaHistoricoObservacoes = new JTextArea();
		textAreaHistoricoObservacoes.setBackground(new Color(129, 226, 217));
		textAreaHistoricoObservacoes.setEditable(false);
		textAreaHistoricoObservacoes.setLineWrap(true); 
		textAreaHistoricoObservacoes.setWrapStyleWord(true);
		JScrollPane scrollPaneHistoricoObservacoes = new JScrollPane(textAreaHistoricoObservacoes);
		scrollPaneHistoricoObservacoes.setBounds(475, 44, 328, 110);
		scrollPaneHistoricoObservacoes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneHistoricoObservacoes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		lblHistoricoObservacao.setLabelFor(textAreaHistoricoObservacoes);
		panelHistoricoPacienteSelecionado.add(scrollPaneHistoricoObservacoes);
		
		btnEditarHistorico = new JButton("Editar");
		btnEditarHistorico.setBackground(new Color(129, 226, 217));
		btnEditarHistorico.setEnabled(false);
		btnEditarHistorico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Controller.getPacienteSelecionado() != null)
				{
					if(finalizado == 0) {
						finalizado = 1;
						btnEditarHistorico.setText("Finalizar");
						btnAddVacinas.setEnabled(true);
						btnDelVacinas.setEnabled(true);
						btnAddDoencas.setEnabled(true);
						btnDelDoencas.setEnabled(true);
						
						comboBoxHistoricoVacinas.setEditable(true);
						JTextField editorVacinas = (JTextField) comboBoxHistoricoVacinas.getEditor().getEditorComponent();
						btnAddVacinas.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								String newItem = editorVacinas.getText();
				                if (!newItem.isEmpty()) {
				                    comboBoxHistoricoVacinas.addItem(newItem);
				                    comboBoxHistoricoVacinas.setSelectedItem(newItem);
				                    editorVacinas.setText("");
				                }
							}
						});
						
						comboBoxHistoricoDoencas.setEditable(true);
						JTextField editorDoencas = (JTextField) comboBoxHistoricoDoencas.getEditor().getEditorComponent();
						btnAddDoencas.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
				                String newItem = editorDoencas.getText();
				                if (!newItem.isEmpty()) {
				                	comboBoxHistoricoDoencas.addItem(newItem);
				                	comboBoxHistoricoDoencas.setSelectedItem(newItem);
				                	editorDoencas.setText("");
				                }
							}
						});
						
						textFieldHistoricoPeso.setEditable(true);
						textAreaHistoricoObservacoes.setEditable(true);
					}
					else
					{
						List<String> vacinasList = new ArrayList<>();
						if(comboBoxHistoricoVacinas.getItemCount() != 0)
						{
							for(int i = 0; i < comboBoxHistoricoVacinas.getItemCount(); i++) {
								vacinasList.add(comboBoxHistoricoVacinas.getItemAt(i));
							}
							comboBoxHistoricoVacinas.setSelectedItem(vacinasList.getFirst());
						}
						List<String> doencasList = new ArrayList<>();
						if(comboBoxHistoricoDoencas.getItemCount() != 0)
						{
							for(int i = 0; i < comboBoxHistoricoDoencas.getItemCount(); i++) {
								doencasList.add(comboBoxHistoricoDoencas.getItemAt(i));
							}			
							comboBoxHistoricoDoencas.setSelectedItem(doencasList.getFirst());
						}
						Historico historicoEditar = new Historico(HistoricoDAO.getInstance().retrieveByIdPaciente(Controller.getPacienteSelecionado().getId()).getId(),Controller.getPacienteSelecionado(),vacinasList, doencasList, textFieldHistoricoPeso.getText(),textAreaHistoricoObservacoes.getText());
						HistoricoDAO.getInstance().update(historicoEditar);
						comboBoxHistoricoVacinas.setEditable(false);
						comboBoxHistoricoDoencas.setEditable(false);
						textFieldHistoricoPeso.setEditable(false);
						textAreaHistoricoObservacoes.setEditable(false);
						finalizado = 0;
						btnAddVacinas.setEnabled(false);
						btnDelVacinas.setEnabled(false);
						btnAddDoencas.setEnabled(false);
						btnDelDoencas.setEnabled(false);
						btnEditarHistorico.setText("Editar");
					}
				}
			}
		});
		btnEditarHistorico.setBounds(714, 18, 89, 23);
		panelHistoricoPacienteSelecionado.add(btnEditarHistorico);
		
		btnAddVacinas = new JButton("Novo");
		btnAddVacinas.setBackground(new Color(129, 226, 217));
		btnAddVacinas.setEnabled(false);
		btnAddVacinas.setBounds(306, 18, 70, 23);
		panelHistoricoPacienteSelecionado.add(btnAddVacinas);
		
		btnAddDoencas = new JButton("Novo");
		btnAddDoencas.setBackground(new Color(129, 226, 217));
		btnAddDoencas.setEnabled(false);
		btnAddDoencas.setBounds(306, 74, 70, 23);
		panelHistoricoPacienteSelecionado.add(btnAddDoencas);
		
		btnDelVacinas = new JButton("Apagar");
		btnDelVacinas.setBackground(new Color(129, 226, 217));
		btnDelVacinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = comboBoxHistoricoVacinas.getSelectedIndex();
				if(selectedIndex != -1)
				{
					comboBoxHistoricoVacinas.removeItemAt(selectedIndex);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione um item!");
				}
			}
		});
		btnDelVacinas.setEnabled(false);
		btnDelVacinas.setBounds(385, 18, 80, 23);
		panelHistoricoPacienteSelecionado.add(btnDelVacinas);
		
		btnDelDoencas = new JButton("Apagar");
		btnDelDoencas.setBackground(new Color(129, 226, 217));
		btnDelDoencas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = comboBoxHistoricoDoencas.getSelectedIndex();
				if(selectedIndex != -1)
				{
					comboBoxHistoricoDoencas.removeItemAt(selectedIndex);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione um item!");
				}
			}
		});
		btnDelDoencas.setEnabled(false);
		btnDelDoencas.setBounds(385, 74, 80, 23);
		panelHistoricoPacienteSelecionado.add(btnDelDoencas);
		//FIM INFORMAÇÕES SELECIONADAS
		
		//TAB CLIENTES E PACIENTES
		//SELECIONAR CLIENTE
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 253, 833, 480);
		frmVeterinria.getContentPane().add(tabbedPane);
		JPanel TabbedPanelCliente = new JPanel();
		TabbedPanelCliente.setBackground(Color.WHITE);
		tabbedPane.addTab("Clientes e Pacientes", null, TabbedPanelCliente, null);
		TabbedPanelCliente.setLayout(null);
		
		JPanel GroupBoxCliente = new JPanel();
		GroupBoxCliente.setBackground(Color.WHITE);
		GroupBoxCliente.setBorder(new TitledBorder(null, "Selecione o Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupBoxCliente.setBounds(10, 11, 808, 210);
		TabbedPanelCliente.add(GroupBoxCliente);
		GroupBoxCliente.setLayout(null);

		JLabel lblBuscarCliente = new JLabel("Buscar:");
		lblBuscarCliente.setBounds(10, 26, 46, 14);
		GroupBoxCliente.add(lblBuscarCliente);
		textFieldBuscarCliente = new JTextField();
		textFieldBuscarCliente.setBackground(new Color(129, 226, 217));
		lblBuscarCliente.setLabelFor(textFieldBuscarCliente);
		textFieldBuscarCliente.setBounds(66, 23, 228, 20);
		GroupBoxCliente.add(textFieldBuscarCliente);
		textFieldBuscarCliente.setColumns(10);
		textFieldBuscarCliente.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				Controller.setTableModel(tableCliente, new ProprietarioTableModel(ProprietarioDAO.getInstance().retrieveBySimilarName(textFieldBuscarCliente.getText())));
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				Controller.setTableModel(tableCliente, new ProprietarioTableModel(ProprietarioDAO.getInstance().retrieveBySimilarName(textFieldBuscarCliente.getText())));
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				Controller.setTableModel(tableCliente, new ProprietarioTableModel(ProprietarioDAO.getInstance().retrieveBySimilarName(textFieldBuscarCliente.getText())));
			}
		});
		
		JButton btnTodosCliente = new JButton("Todos");
		btnTodosCliente.setBackground(new Color(129, 226, 217));
		btnTodosCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.setTableModel(tableCliente, new ProprietarioTableModel(ProprietarioDAO.getInstance().retrieveAll()));
			}
		});
		btnTodosCliente.setBounds(304, 22, 77, 23);
		GroupBoxCliente.add(btnTodosCliente);
		
		JButton btnNovoCliente = new JButton("Novo Cliente");
		btnNovoCliente.setBackground(new Color(129, 226, 217));
		btnNovoCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrarClienteFrame registrarClienteFrame = new RegistrarClienteFrame(Principal.this);
				registrarClienteFrame.setVisible(true);
			}
		});
		btnNovoCliente.setBounds(391, 22, 135, 23);
		GroupBoxCliente.add(btnNovoCliente);
		
		JButton btnApagaCliente = new JButton("Apagar Cliente");
		btnApagaCliente.setBackground(new Color(129, 226, 217));
		btnApagaCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Controller.getClienteSelecionado() != null) {
					PacienteDAO.getInstance().deleteByProprietario(Controller.getClienteSelecionado().getId());
					FaturaDAO.getInstance().deleteByProprietario(Controller.getClienteSelecionado().getId());
					ProprietarioDAO.getInstance().delete(Controller.getClienteSelecionado());
					Controller.setTableModel(tableFatura, new FaturaTableModel(FaturaDAO.getInstance().returnEmptyList()));
					Controller.setTableModel(tableCliente, new ProprietarioTableModel(ProprietarioDAO.getInstance().retrieveAll()));
					Controller.setTableModel(tablePaciente, new PacienteTableModel(PacienteDAO.getInstance().retrieveByIdProprietario(Controller.getClienteSelecionado().getId())));
					clearAll();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione um cliente!");
				}
			}
		});
		btnApagaCliente.setBounds(536, 22, 131, 23);
		GroupBoxCliente.add(btnApagaCliente);
		
		JButton btnEditarCliente = new JButton("Editar Cliente");
		btnEditarCliente.setBackground(new Color(129, 226, 217));
		btnEditarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Controller.getClienteSelecionado() != null)
				{
					RegistrarClienteFrame registrarClienteFrame = new RegistrarClienteFrame(Principal.this, Controller.getClienteSelecionado());
					registrarClienteFrame.setVisible(true);					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione um cliente!");
				}
			}
		});
		btnEditarCliente.setBounds(677, 22, 121, 23);
		GroupBoxCliente.add(btnEditarCliente);
		
		//TABELA CLIENTE 
		tableCliente = new JTable();
		tableCliente.setBackground(new Color(184, 239, 234));
		tableCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearAll();
				Controller.setSelected(((GenericTableModel)tableCliente.getModel()).getItem(tableCliente.getSelectedRow()));
				Controller.setTableModel(tablePaciente, new PacienteTableModel(PacienteDAO.getInstance().retrieveByIdProprietario(Controller.getClienteSelecionado().getId())));
				Controller.setTableModel(tableFatura, new FaturaTableModel(FaturaDAO.getInstance().retrieveByIdProprietario(Controller.getClienteSelecionado().getId())));
			}
		});
		tableCliente.setToolTipText("");
		tableCliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "CPF", "Telefone", "Endere\u00E7o"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableCliente.getColumnModel().getColumn(0).setResizable(false);
		tableCliente.getColumnModel().getColumn(1).setResizable(false);
		tableCliente.getColumnModel().getColumn(2).setResizable(false);
		tableCliente.getColumnModel().getColumn(3).setResizable(false);
		tableCliente.getColumnModel().getColumn(3).setPreferredWidth(81);
		JScrollPane scrollPaneTableCliente = new JScrollPane(tableCliente);
		scrollPaneTableCliente.setBounds(10, 51, 788, 148);  
		GroupBoxCliente.add(scrollPaneTableCliente);
		//FIM TABELA CLIENTE
		//FIM SELECIONAR CLIENTE
		
		//SELECIONAR PACIENTE
		//TABELA PACIENTE
		JPanel GroupBoxPaciente = new JPanel();
		GroupBoxPaciente.setBackground(Color.WHITE);
		GroupBoxPaciente.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Selecione o Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupBoxPaciente.setBounds(10, 224, 808, 217);
		TabbedPanelCliente.add(GroupBoxPaciente);
		GroupBoxPaciente.setLayout(null);
				
		JLabel lblBuscarPaciente = new JLabel("Buscar:");
		lblBuscarPaciente.setBounds(9, 26, 46, 14);
		GroupBoxPaciente.add(lblBuscarPaciente);		
		textFieldBuscarPaciente = new JTextField();
		textFieldBuscarPaciente.setBackground(new Color(129, 226, 217));
		lblBuscarPaciente.setLabelFor(textFieldBuscarPaciente);
		textFieldBuscarPaciente.setColumns(10);
		textFieldBuscarPaciente.setBounds(65, 23, 233, 20);
		textFieldBuscarPaciente.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(Controller.getClienteSelecionado() != null)
				{
					Controller.setTableModel(tablePaciente, new PacienteTableModel(PacienteDAO.getInstance().retrieveByIdProprietarioAndSimilarName(Controller.getClienteSelecionado().getId(), textFieldBuscarPaciente.getText())));					
				}
				else
				{
					Controller.setTableModel(tablePaciente, new PacienteTableModel(PacienteDAO.getInstance().retrieveBySimilarName(textFieldBuscarPaciente.getText())));
				}	
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(Controller.getClienteSelecionado() != null)
				{
					Controller.setTableModel(tablePaciente, new PacienteTableModel(PacienteDAO.getInstance().retrieveByIdProprietarioAndSimilarName(Controller.getClienteSelecionado().getId(), textFieldBuscarPaciente.getText())));					
				}
				else
				{
					Controller.setTableModel(tablePaciente, new PacienteTableModel(PacienteDAO.getInstance().retrieveBySimilarName(textFieldBuscarPaciente.getText())));
				}
				
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(Controller.getClienteSelecionado() != null)
				{
					Controller.setTableModel(tablePaciente, new PacienteTableModel(PacienteDAO.getInstance().retrieveByIdProprietarioAndSimilarName(Controller.getClienteSelecionado().getId(), textFieldBuscarPaciente.getText())));					
				}
				else
				{
					Controller.setTableModel(tablePaciente, new PacienteTableModel(PacienteDAO.getInstance().retrieveBySimilarName(textFieldBuscarPaciente.getText())));
				}	
			}
		});
		GroupBoxPaciente.add(textFieldBuscarPaciente);
	
		JButton btnTodosPaciente = new JButton("Todos");
		btnTodosPaciente.setBackground(new Color(129, 226, 217));
		btnTodosPaciente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.setTableModel(tablePaciente, new PacienteTableModel(PacienteDAO.getInstance().retrieveAll()));
			}
		});
		btnTodosPaciente.setBounds(308, 22, 77, 23);
		GroupBoxPaciente.add(btnTodosPaciente);
				
		JButton btnNovoPaciente = new JButton("Novo Paciente");
		btnNovoPaciente.setBackground(new Color(129, 226, 217));
		btnNovoPaciente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Controller.getClienteSelecionado() != null)
				{
					RegistrarPacienteFrame registrarPacienteFrame = new RegistrarPacienteFrame(Principal.this, Controller.getClienteSelecionado());
					registrarPacienteFrame.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione um cliente!");
				}
			}
		});
		btnNovoPaciente.setBounds(395, 22, 130, 23);
		GroupBoxPaciente.add(btnNovoPaciente);
				
		JButton btnApagaPaciente = new JButton("Apagar Paciente");
		btnApagaPaciente.setBackground(new Color(129, 226, 217));
		btnApagaPaciente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Controller.getPacienteSelecionado() != null) {
					AgendamentoDAO.getInstance().deleteByPaciente(Controller.getPacienteSelecionado().getId());
					PacienteDAO.getInstance().delete(Controller.getPacienteSelecionado());
					Controller.setTableModel(tablePaciente, new PacienteTableModel(PacienteDAO.getInstance().retrieveByIdProprietario(Controller.getClienteSelecionado().getId())));
					Controller.setTableModel(tableAgendamento, new AgendamentoTableModel(AgendamentoDAO.getInstance().retrieveByIdPaciente(Controller.getPacienteSelecionado().getId())));
					clearAll();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione um paciente!");
				}
			}
		});
		btnApagaPaciente.setBounds(535, 22, 131, 23);
		GroupBoxPaciente.add(btnApagaPaciente);
				
		JButton btnEditarPaciente = new JButton("Editar Paciente");
		btnEditarPaciente.setBackground(new Color(129, 226, 217));
		btnEditarPaciente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Controller.getClienteSelecionado() != null)
				{
					if(Controller.getPacienteSelecionado() != null)
					{
						RegistrarPacienteFrame registrarPacienteFrame = new RegistrarPacienteFrame(Principal.this, Controller.getClienteSelecionado(), Controller.getPacienteSelecionado());
						registrarPacienteFrame.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Selecione um paciente!");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione um cliente!");
				}
			}
		});
		btnEditarPaciente.setBounds(676, 22, 122, 23);
		GroupBoxPaciente.add(btnEditarPaciente);
				
		tablePaciente = new JTable();
		tablePaciente.setBackground(new Color(184, 239, 234));
		tablePaciente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Controller.setSelected(((GenericTableModel)tablePaciente.getModel()).getItem(tablePaciente.getSelectedRow()));
				Proprietario p = ProprietarioDAO.getInstance().retrieveById(Controller.getPacienteSelecionado().getProprietario().getId());
				Controller.setSelected(p);
				Controller.setSelected(((GenericTableModel)tablePaciente.getModel()).getItem(tablePaciente.getSelectedRow()));
				Controller.setTableModel(tableAgendamento, new AgendamentoTableModel(AgendamentoDAO.getInstance().retrieveByIdPaciente(Controller.getPacienteSelecionado().getId())));
				Controller.setTableModel(tableReceita, new ReceitaTableModel(ReceitaMedicaDAO.getInstance().retrieveByPaciente(Controller.getPacienteSelecionado().getId())));
				
				try {
					comboBoxHistoricoVacinas.removeAllItems();
					comboBoxHistoricoDoencas.removeAllItems();
					btnEditarHistorico.setEnabled(true);
				    Historico historicoSelecionado = HistoricoDAO.getInstance().retrieveByIdPaciente(Controller.getPacienteSelecionado().getId());
				    for (String vacina : historicoSelecionado.getVacinas()) {
				        comboBoxHistoricoVacinas.addItem(vacina);
				    }
				    for (String doenca : historicoSelecionado.getDoencas()) {
				        comboBoxHistoricoDoencas.addItem(doenca);
				    }
				    textFieldHistoricoPeso.setText(historicoSelecionado.getPeso());
				    textAreaHistoricoObservacoes.setText(historicoSelecionado.getObservacoes());
				} catch (Exception e1) {
				    clearAll();
				    btnEditarHistorico.setEnabled(false);
				    System.err.println("Erro ao carregar o histórico: " + e1.getMessage());
				}		
			}
		});
		tablePaciente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Nome", "Especie", "Idade", "Castra\u00E7\u00E3o", "Colora\u00E7\u00E3o", "Ra\u00E7a"
			}
		));
				
		JScrollPane scrollPaneTablePaciente = new JScrollPane(tablePaciente);
		scrollPaneTablePaciente.setBounds(9, 51, 789, 155);
		GroupBoxPaciente.add(scrollPaneTablePaciente);  
		//FIM TABELA PACIENTE
		//FIM SELECIONAR PACIENTE
		//FIM TAB CLIENTES E PACIENTES
		
		//TAB NOVA CONSULTA
		JPanel TabbedPanelAgendamento = new JPanel();
		TabbedPanelAgendamento.setBackground(Color.WHITE);
		tabbedPane.addTab("Veterinários e Consultas", null, TabbedPanelAgendamento, null);
		TabbedPanelAgendamento.setLayout(null);
		
		//SELECIONAR VETERINARIO
		JPanel GroupBoxVeterinario = new JPanel();
		GroupBoxVeterinario.setBackground(Color.WHITE);
		GroupBoxVeterinario.setBorder(new TitledBorder(null, "Selecionar Veterin\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupBoxVeterinario.setBounds(10, 11, 808, 213);
		TabbedPanelAgendamento.add(GroupBoxVeterinario);
		GroupBoxVeterinario.setLayout(null);
		
		JLabel lblBuscarVeterinario = new JLabel("Buscar:");
		lblBuscarVeterinario.setBounds(10, 29, 46, 14);
		GroupBoxVeterinario.add(lblBuscarVeterinario);
		
		textFieldBuscarVeterinario = new JTextField();
		textFieldBuscarVeterinario.setBackground(new Color(129, 226, 217));
		lblBuscarVeterinario.setLabelFor(textFieldBuscarVeterinario);
		textFieldBuscarVeterinario.setColumns(10);
		textFieldBuscarVeterinario.setBounds(66, 26, 203, 20);
		textFieldBuscarVeterinario.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
					Controller.setTableModel(tableVeterinario, new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveBySimilarName(textFieldBuscarVeterinario.getText())));
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				Controller.setTableModel(tableVeterinario, new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveBySimilarName(textFieldBuscarVeterinario.getText())));
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				Controller.setTableModel(tableVeterinario, new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveBySimilarName(textFieldBuscarVeterinario.getText())));
			}
		});
		GroupBoxVeterinario.add(textFieldBuscarVeterinario);
		
		JButton btnTodosVeterinario = new JButton("Todos");
		btnTodosVeterinario.setBackground(new Color(129, 226, 217));
		btnTodosVeterinario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.setTableModel(tableVeterinario, new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveAllActive()));
			}
		});
		btnTodosVeterinario.setBounds(279, 25, 77, 23);
		GroupBoxVeterinario.add(btnTodosVeterinario);
		
		JButton btnNovoVeterinario = new JButton("Novo Veterinário");
		btnNovoVeterinario.setBackground(new Color(129, 226, 217));
		btnNovoVeterinario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrarVeterinarioFrame registrarVeterinarioFrame = new RegistrarVeterinarioFrame(Principal.this);
				registrarVeterinarioFrame.setVisible(true);
			}
		});
		btnNovoVeterinario.setBounds(366, 25, 135, 23);
		GroupBoxVeterinario.add(btnNovoVeterinario);
		
		JButton btnApagaVeterinario = new JButton("Apagar Veterinário");
		btnApagaVeterinario.setBackground(new Color(129, 226, 217));
		btnApagaVeterinario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Controller.getVeterinarioSelecionado() != null)
				{
					VeterinarioDAO.getInstance().softDelete(Controller.getVeterinarioSelecionado());
					Controller.setTableModel(tableVeterinario, new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveAllActive()));
					textFieldAgendamentoVeterinario.setText("");
					textFieldVeterinarioSelecionadoReceita.setText("");
					Controller.setVeterinarioSelecionadoNull();		
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione um veterinário!");
				}
			}
		});
		btnApagaVeterinario.setBounds(511, 25, 146, 23);
		GroupBoxVeterinario.add(btnApagaVeterinario);
		
		JButton btnEditarVeterinario = new JButton("Editar Veterinário");
		btnEditarVeterinario.setBackground(new Color(129, 226, 217));
		btnEditarVeterinario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Controller.getVeterinarioSelecionado() != null)
				{
					RegistrarVeterinarioFrame registrarVeterinarioFrame = new RegistrarVeterinarioFrame(Principal.this, Controller.getVeterinarioSelecionado());
					registrarVeterinarioFrame.setVisible(true);
					textFieldAgendamentoVeterinario.setText("");
					textFieldVeterinarioSelecionadoReceita.setText("");
					textFieldVeterinarioSelecionadoReceita.setText("");
					Controller.setVeterinarioSelecionadoNull();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione um veterinário!");
				}
			}
		});
		btnEditarVeterinario.setBounds(663, 25, 135, 23);
		GroupBoxVeterinario.add(btnEditarVeterinario);
		
		//TABLE VETERINARIO
		tableVeterinario = new JTable();
		tableVeterinario.setBackground(new Color(184, 239, 234));
		tableVeterinario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.setSelected(((GenericTableModel)tableVeterinario.getModel()).getItem(tableVeterinario.getSelectedRow()));
			}
		});
		tableVeterinario.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Nome", "E-mail", "Telefone"
			}
		));	
		JScrollPane scrollPaneTableVeterinario = new JScrollPane(tableVeterinario);
		scrollPaneTableVeterinario.setBounds(9, 54, 789, 148);
		GroupBoxVeterinario.add(scrollPaneTableVeterinario);  
		//FIM TABLE VETERINARIO
		//FIM SELECIONAR VETERINARIO
		
		//AGENDAR NOVA CONSULTA
		JPanel GroupBoxAgendamento = new JPanel();
		GroupBoxAgendamento.setBackground(Color.WHITE);
		GroupBoxAgendamento.setBorder(new TitledBorder(null, "Agendar nova consulta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupBoxAgendamento.setBounds(10, 228, 808, 213);
		TabbedPanelAgendamento.add(GroupBoxAgendamento);
		GroupBoxAgendamento.setLayout(null);
		
		JLabel lblAgendamentoPaciente = new JLabel("Paciente:");
		lblAgendamentoPaciente.setBounds(10, 27, 59, 14);
		lblAgendamentoPaciente.setLabelFor(textFieldAgendamentoPaciente);
		GroupBoxAgendamento.add(lblAgendamentoPaciente);
		textFieldAgendamentoPaciente = new JTextField();
		textFieldAgendamentoPaciente.setBackground(new Color(38, 166, 153));
		textFieldAgendamentoPaciente.setEditable(false);
		textFieldAgendamentoPaciente.setColumns(10);
		textFieldAgendamentoPaciente.setBounds(79, 24, 203, 20);
		GroupBoxAgendamento.add(textFieldAgendamentoPaciente);
		
		JLabel lblAgendamentoVeterinario = new JLabel("Veterinário:");
		lblAgendamentoVeterinario.setLabelFor(textFieldAgendamentoVeterinario);
		lblAgendamentoVeterinario.setBounds(10, 58, 59, 14);
		GroupBoxAgendamento.add(lblAgendamentoVeterinario);
		textFieldAgendamentoVeterinario = new JTextField();
		textFieldAgendamentoVeterinario.setBackground(new Color(38, 166, 153));
		textFieldAgendamentoVeterinario.setEditable(false);
		textFieldAgendamentoVeterinario.setColumns(10);
		textFieldAgendamentoVeterinario.setBounds(79, 55, 203, 20);
		GroupBoxAgendamento.add(textFieldAgendamentoVeterinario);
		
		JLabel lblAgendamentoServico = new JLabel("Serviço:");
		lblAgendamentoServico.setLabelFor(textFieldAgendamentoServico);
		lblAgendamentoServico.setBounds(10, 89, 59, 14);
		GroupBoxAgendamento.add(lblAgendamentoServico);
		textFieldAgendamentoServico = new JTextField();
		textFieldAgendamentoServico.setBackground(new Color(129, 226, 217));
		textFieldAgendamentoServico.setColumns(10);
		textFieldAgendamentoServico.setBounds(79, 86, 203, 20);
		GroupBoxAgendamento.add(textFieldAgendamentoServico);
				
		JLabel lblAgendamentoData = new JLabel("Data:");
		lblAgendamentoData.setBounds(10, 120, 59, 14);
		lblAgendamentoData.setLabelFor(formattedTextFieldDataAgendamento);
		GroupBoxAgendamento.add(lblAgendamentoData);
		formattedTextFieldDataAgendamento = new JFormattedTextField();
		formattedTextFieldDataAgendamento.setBackground(new Color(129, 226, 217));
		formattedTextFieldDataAgendamento.setBounds(79, 117, 203, 20);
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_'); 
            dateMask.install(formattedTextFieldDataAgendamento);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		GroupBoxAgendamento.add(formattedTextFieldDataAgendamento);
		
		JLabel lblAgendamentoHora = new JLabel("Hora:");
		lblAgendamentoHora.setBounds(10, 154, 59, 14);
		lblAgendamentoHora.setLabelFor(formattedTextFieldAgendamentoHora);
		GroupBoxAgendamento.add(lblAgendamentoHora);
		formattedTextFieldAgendamentoHora = new JFormattedTextField();
		formattedTextFieldAgendamentoHora.setBackground(new Color(129, 226, 217));
		formattedTextFieldAgendamentoHora.setBounds(79, 148, 203, 20);
        try {
            MaskFormatter timeMask = new MaskFormatter("##:##");
            timeMask.setPlaceholderCharacter('_'); 
            timeMask.install(formattedTextFieldAgendamentoHora);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		GroupBoxAgendamento.add(formattedTextFieldAgendamentoHora);
		
		JButton btnAgendamentoNovo = new JButton("Novo Agendamento");
		btnAgendamentoNovo.setBackground(new Color(129, 226, 217));
		btnAgendamentoNovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Controller.getPacienteSelecionado() != null && Controller.getVeterinarioSelecionado() != null && !textFieldAgendamentoServico.getText().isEmpty())
				{
				    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
				    try {
				        LocalDate parsedDate = LocalDate.parse(formattedTextFieldDataAgendamento.getText(), dateFormatter);
				        LocalTime parsedTime = LocalTime.parse(formattedTextFieldAgendamentoHora.getText(), timeFormatter);
				        
				        AgendamentoDAO.getInstance().create(Controller.getPacienteSelecionado(), parsedDate.atTime(parsedTime), textFieldAgendamentoServico.getText(), Agendamento.StatusAgendamento.AGENDADO, Controller.getVeterinarioSelecionado());
				        Controller.setTableModel(tableAgendamento, new AgendamentoTableModel(AgendamentoDAO.getInstance().retrieveByIdPaciente(Controller.getPacienteSelecionado().getId())));
						
						textFieldAgendamentoVeterinario.setText("");
						textFieldAgendamentoServico.setText("");
						formattedTextFieldDataAgendamento.setText("");
						formattedTextFieldAgendamentoHora.setText("");
				    } catch (DateTimeParseException ex) {
				        JOptionPane.showMessageDialog(null, "Data ou Hora inválidas.");
				    }
				}
				else
				{
			        JOptionPane.showMessageDialog(null, "Preencha com todas as informações necessárias.");
				}
			}
		});
		btnAgendamentoNovo.setBounds(10, 179, 272, 23);
		GroupBoxAgendamento.add(btnAgendamentoNovo);
		
		//TABELA AGENDAMENTOS EXISTENTES
		JPanel panelAgendamentosExistentes = new JPanel();
		panelAgendamentosExistentes.setBackground(Color.WHITE);
		panelAgendamentosExistentes.setBorder(new TitledBorder(null, "Agendamentos existentes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAgendamentosExistentes.setBounds(292, 11, 506, 191);
		GroupBoxAgendamento.add(panelAgendamentosExistentes);
		panelAgendamentosExistentes.setLayout(null);
		
		tableAgendamento = new JTable();
		tableAgendamento .setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Data e hora", "Servi\u00E7o", "Veterinario", "Status"
			}
		));
			
		tableAgendamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.setSelected(((GenericTableModel)tableAgendamento.getModel()).getItem(tableAgendamento.getSelectedRow()));
			}});

		
		//FIM TABELA AGENDAMENTOS EXISTENTES
		JScrollPane scrollPaneTableAgendamentoPaciente = new JScrollPane(tableAgendamento);
		scrollPaneTableAgendamentoPaciente.setBounds(10, 21, 486, 129);
		panelAgendamentosExistentes.add(scrollPaneTableAgendamentoPaciente);
				
		JButton btnAgendamentoExcluir = new JButton("Excluir");
		btnAgendamentoExcluir.setBackground(new Color(129, 226, 217));
		btnAgendamentoExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Controller.getAgendamentoSelecionado() != null)
				{
					AgendamentoDAO.getInstance().delete(Controller.getAgendamentoSelecionado());
					Controller.setTableModel(tableAgendamento, new AgendamentoTableModel(AgendamentoDAO.getInstance().retrieveByIdPaciente(Controller.getPacienteSelecionado().getId())));			
					Controller.setAgendamentoSelecionadoNull();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione um agendamento!");
				}
			}
		});
		btnAgendamentoExcluir.setBounds(10, 157, 149, 23);
		panelAgendamentosExistentes.add(btnAgendamentoExcluir);
				
		JButton btnAgendamentoConcluir = new JButton("Concluir");
		btnAgendamentoConcluir.setBackground(new Color(129, 226, 217));
		btnAgendamentoConcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Controller.getAgendamentoSelecionado() != null)
				{
					Agendamento a = new Agendamento(Controller.getAgendamentoSelecionado().getId(), Controller.getAgendamentoSelecionado().getPaciente(), Controller.getAgendamentoSelecionado().getDataHora(), Controller.getAgendamentoSelecionado().getServico(), Agendamento.StatusAgendamento.CONCLUIDO, Controller.getAgendamentoSelecionado().getVeterinario());
					AgendamentoDAO.getInstance().update(a);
					Controller.setTableModel(tableAgendamento, new AgendamentoTableModel(AgendamentoDAO.getInstance().retrieveByIdPaciente(Controller.getPacienteSelecionado().getId())));					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione um agendamento!");
				}
			}
		});
		btnAgendamentoConcluir.setBounds(169, 157, 161, 23);
		panelAgendamentosExistentes.add(btnAgendamentoConcluir);
				
		JButton btnAgendamentoCancelar = new JButton("Cancelar");
		btnAgendamentoCancelar.setBackground(new Color(129, 226, 217));
		btnAgendamentoCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Controller.getAgendamentoSelecionado() != null)
				{
					Agendamento a = new Agendamento(Controller.getAgendamentoSelecionado().getId(), Controller.getAgendamentoSelecionado().getPaciente(), Controller.getAgendamentoSelecionado().getDataHora(), Controller.getAgendamentoSelecionado().getServico(), Agendamento.StatusAgendamento.CANCELADO, Controller.getAgendamentoSelecionado().getVeterinario());
					AgendamentoDAO.getInstance().update(a);
					Controller.setTableModel(tableAgendamento, new AgendamentoTableModel(AgendamentoDAO.getInstance().retrieveByIdPaciente(Controller.getPacienteSelecionado().getId())));					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione um agendamento!");
				}
			}
		});
		btnAgendamentoCancelar.setBounds(340, 157, 156, 23);
		panelAgendamentosExistentes.add(btnAgendamentoCancelar);
		
		//FIM AGENDAR NOVA CONSULTA
		
		//TAB RECEITA MÉDICA
		JPanel TabbedReceitaMedica = new JPanel();
		TabbedReceitaMedica.setBackground(Color.WHITE);
		tabbedPane.addTab("Receitas Médicas", null, TabbedReceitaMedica, null);
		TabbedReceitaMedica.setLayout(null);
				
		JPanel panelSelecionarInfo = new JPanel();
		panelSelecionarInfo.setBackground(Color.WHITE);
		panelSelecionarInfo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Selecione as informa\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSelecionarInfo.setBounds(10, 11, 808, 225);
		TabbedReceitaMedica.add(panelSelecionarInfo);
		panelSelecionarInfo.setLayout(null);
		
		JLabel lblVeterinarioSelecionadoReceita = new JLabel("Veterinário:");
		lblVeterinarioSelecionadoReceita.setBounds(10, 30, 66, 14);
		panelSelecionarInfo.add(lblVeterinarioSelecionadoReceita);
		textFieldVeterinarioSelecionadoReceita = new JTextField();
		textFieldVeterinarioSelecionadoReceita.setBackground(new Color(38, 166, 153));
		lblVeterinarioSelecionadoReceita.setLabelFor(textFieldVeterinarioSelecionadoReceita);
		textFieldVeterinarioSelecionadoReceita.setEditable(false);
		textFieldVeterinarioSelecionadoReceita.setColumns(10);
		textFieldVeterinarioSelecionadoReceita.setBounds(86, 27, 189, 20);
		panelSelecionarInfo.add(textFieldVeterinarioSelecionadoReceita);
		
		JLabel lblMedicamentosReceita = new JLabel("Medicamentos:");
		lblMedicamentosReceita.setLabelFor(comboBoxMedicamentosReceita);
		lblMedicamentosReceita.setBounds(330, 31, 97, 14);
		panelSelecionarInfo.add(lblMedicamentosReceita);
		comboBoxMedicamentosReceita = new JComboBox();
		comboBoxMedicamentosReceita.setBackground(new Color(129, 226, 217));
		comboBoxMedicamentosReceita.setBounds(437, 27, 241, 22);
		panelSelecionarInfo.add(comboBoxMedicamentosReceita);
		JButton btnAdicionarMedicamentoReceita = new JButton("Adicionar");
		btnAdicionarMedicamentoReceita.setBackground(new Color(129, 226, 217));
		btnAdicionarMedicamentoReceita.setBounds(688, 27, 110, 23);
		panelSelecionarInfo.add(btnAdicionarMedicamentoReceita);
		
		comboBoxMedicamentosReceita.setEditable(true);
		JTextField editorMedicamentos = (JTextField) comboBoxMedicamentosReceita.getEditor().getEditorComponent();
		
		btnAdicionarMedicamentoReceita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String newItem = editorMedicamentos.getText();
				if(!newItem.isEmpty()) {
					comboBoxMedicamentosReceita.addItem(newItem);
					comboBoxMedicamentosReceita.setSelectedItem(newItem);
					editorMedicamentos.setText("");
				}
			}
		});
		
		JLabel lblObservacoesReceita = new JLabel("Observações:");
		lblObservacoesReceita.setLabelFor(textAreaObservacoesReceita);
		lblObservacoesReceita.setBounds(10, 86, 110, 14);
		panelSelecionarInfo.add(lblObservacoesReceita);
		textAreaObservacoesReceita = new JTextArea();
		textAreaObservacoesReceita.setBackground(new Color(129, 226, 217));
		textAreaObservacoesReceita.setLineWrap(true); 
		textAreaObservacoesReceita.setWrapStyleWord(true);
		JScrollPane scrollPaneObservacoesReceita = new JScrollPane(textAreaObservacoesReceita);
		scrollPaneObservacoesReceita.setBounds(10, 111, 788, 73);
		scrollPaneObservacoesReceita.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneObservacoesReceita.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelSelecionarInfo.add(scrollPaneObservacoesReceita);
		
		JLabel lblDataHoraReceita = new JLabel("Data e hora:");
		lblDataHoraReceita.setBounds(510, 86, 97, 14);
		panelSelecionarInfo.add(lblDataHoraReceita);
		JLabel lblDataHoraReceitaAgora = new JLabel("##/##/## ##:##");
		lblDataHoraReceitaAgora.setBounds(617, 86, 160, 14);
		panelSelecionarInfo.add(lblDataHoraReceitaAgora);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                now = LocalDateTime.now();
                lblDataHoraReceitaAgora.setText(now.format(formatter));
            }
        });
		timer.start();
		
		JButton btnImprimirReceita = new JButton("Imprimir");
		btnImprimirReceita.setBackground(new Color(129, 226, 217));
		btnImprimirReceita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Controller.getPacienteSelecionado() != null && Controller.getVeterinarioSelecionado() != null)
				{
					List<String> medicamentos = new ArrayList<>();
					ComboBoxModel<String> model = comboBoxMedicamentosReceita.getModel();
					for(int i = 0; i < model.getSize(); i++) {
						String item = model.getElementAt(i);
						medicamentos.add(item);
					}
					
					if(textAreaObservacoesReceita.getText().trim().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Preencha com as informações necessárias!");
					}
					else
					{
						ReceitaMedicaDAO.getInstance().create(Controller.getPacienteSelecionado(), medicamentos, now, textAreaObservacoesReceita.getText(), Controller.getVeterinarioSelecionado());
						Controller.setTableModel(tableReceita, new ReceitaTableModel(ReceitaMedicaDAO.getInstance().retrieveByPaciente(Controller.getPacienteSelecionado().getId())));
						textAreaObservacoesReceita.setText("");
						comboBoxMedicamentosReceita.removeAllItems();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione o paciente e veterinário!");
				}
			}
		});
		btnImprimirReceita.setBounds(357, 191, 89, 23);
		panelSelecionarInfo.add(btnImprimirReceita);
			
		JPanel panelBuscarReceita = new JPanel();
		panelBuscarReceita.setBackground(Color.WHITE);
		panelBuscarReceita.setBorder(new TitledBorder(null, "Buscar Receitas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBuscarReceita.setBounds(10, 247, 808, 194);
		TabbedReceitaMedica.add(panelBuscarReceita);
		panelBuscarReceita.setLayout(null);
		
		//TABELA RECEITA
		tableReceita = new JTable();
		tableReceita.setBackground(new Color(184, 239, 234));
		tableReceita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.setSelected(((GenericTableModel)tableReceita.getModel()).getItem(tableReceita.getSelectedRow()));
				if(e.getClickCount() == 2 && tableReceita.getSelectedRow() != -1) {
					VisualizarReceitaMedica receita = new VisualizarReceitaMedica(Controller.getClienteSelecionado().getNomeCompleto(), Controller.getPacienteSelecionado().getNome(), Controller.getReceitaSelecionado().getVeterinario().getNome(), Controller.getReceitaSelecionado().getMedicamentos(), Controller.getReceitaSelecionado().getDataEmissao(), Controller.getReceitaSelecionado().getObservacoes());
					receita.setVisible(true);
				}
			}
		});
		tableReceita.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Veterin\u00E1rio", "Observacoes", "Data"
			}
		));
		JScrollPane scrollPaneTableReceita = new JScrollPane(tableReceita);
		scrollPaneTableReceita.setBounds(10, 49, 788, 134);
		panelBuscarReceita.add(scrollPaneTableReceita);  
		
		JLabel lblInfoReceitas = new JLabel("Clique para mais detalhes");
		lblInfoReceitas.setBounds(10, 24, 180, 14);
		panelBuscarReceita.add(lblInfoReceitas);
		
		JButton btnDeletarReceita = new JButton("Deletar");
		btnDeletarReceita.setBackground(new Color(129, 226, 217));
		btnDeletarReceita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Controller.getReceitaSelecionado() != null)
				{
					ReceitaMedicaDAO.getInstance().delete(Controller.getReceitaSelecionado());
					Controller.setTableModel(tableReceita, new ReceitaTableModel(ReceitaMedicaDAO.getInstance().retrieveByPaciente(Controller.getPacienteSelecionado().getId())));
					Controller.setreceitaSelecionadoNull();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione uma receita médica!");
				}
			}
		});
		btnDeletarReceita.setBounds(666, 20, 132, 23);
		panelBuscarReceita.add(btnDeletarReceita);
		//FIM TABELA RECEITA
		//FIM TAB RECEITA
				
		//TAB ESTOQUE
		JPanel TabbedPanelEstoque = new JPanel();
		TabbedPanelEstoque.setBackground(Color.WHITE);
		tabbedPane.addTab("Estoque", null, TabbedPanelEstoque, null);
		TabbedPanelEstoque.setLayout(null);
		
		JPanel GroupBoxProdutos = new JPanel();
		GroupBoxProdutos.setBackground(Color.WHITE);
		GroupBoxProdutos.setLayout(null);
		GroupBoxProdutos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Produtos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupBoxProdutos.setBounds(10, 11, 808, 430);
		TabbedPanelEstoque.add(GroupBoxProdutos);
		
		JButton btnTodosProdutos = new JButton("Todos");
		btnTodosProdutos.setBackground(new Color(129, 226, 217));
		btnTodosProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.setTableModel(tableProdutoEstoque, new ProdutoEstoqueTableModel(ProdutoDAO.getInstance().retrieveAll()));
			}
		});
		btnTodosProdutos.setBounds(395, 22, 117, 23);
		GroupBoxProdutos.add(btnTodosProdutos);
		
		JButton btnNovoProduto = new JButton("Novo Produto");
		btnNovoProduto.setBackground(new Color(129, 226, 217));
		btnNovoProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrarProdutoFrame registrarProdutoFrame = new RegistrarProdutoFrame(Principal.this);
				registrarProdutoFrame.setVisible(true);
			}
		});
		btnNovoProduto.setBounds(522, 22, 135, 23);
		GroupBoxProdutos.add(btnNovoProduto);
		
		JButton btnApagaProduto = new JButton("Apagar Produto");
		btnApagaProduto.setBackground(new Color(129, 226, 217));
		btnApagaProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Controller.getProdutoSelecionado() != null)
				{
					EstoqueDAO.getInstance().delete(EstoqueDAO.getInstance().retrieveByIdProduto(Controller.getProdutoSelecionado().getId()));
					ProdutoDAO.getInstance().delete(Controller.getProdutoSelecionado());
					Controller.setTableModel(tableProdutoEstoque, new ProdutoEstoqueTableModel(ProdutoDAO.getInstance().retrieveAll()));
					Controller.setProdutoSelecionadoNull();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione o produto!");
				}
			}
		});
		btnApagaProduto.setBounds(667, 22, 131, 23);
		GroupBoxProdutos.add(btnApagaProduto);
		
		//TABELA PRODUTOS
		
		tableProdutoEstoque = new JTable();
		tableProdutoEstoque.setBackground(new Color(184, 239, 234));
		tableProdutoEstoque.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Tipo", "Preço", "Quantidade", "Status"
			}
		));
		
		tableProdutoEstoque.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.setSelected(((GenericTableModel)tableProdutoEstoque.getModel()).getItem(tableProdutoEstoque.getSelectedRow()));
			}});
		
		JScrollPane scrollPaneProdutoEstoque = new JScrollPane(tableProdutoEstoque);
		scrollPaneProdutoEstoque.setBounds(10, 51, 788, 368);
		GroupBoxProdutos.add(scrollPaneProdutoEstoque);
		//FIM TABELA PRODUTOS
		
		textFieldBuscarProduto = new JTextField();
		textFieldBuscarProduto.setBackground(new Color(129, 226, 217));
		textFieldBuscarProduto.setColumns(10);
		textFieldBuscarProduto.setBounds(66, 22, 281, 20);
		textFieldBuscarProduto.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				Controller.setTableModel(tableProdutoEstoque, new ProdutoEstoqueTableModel(ProdutoDAO.getInstance().retrieveBySimilarName(textFieldBuscarProduto.getText())));
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				Controller.setTableModel(tableProdutoEstoque, new ProdutoEstoqueTableModel(ProdutoDAO.getInstance().retrieveBySimilarName(textFieldBuscarProduto.getText())));
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				Controller.setTableModel(tableProdutoEstoque, new ProdutoEstoqueTableModel(ProdutoDAO.getInstance().retrieveBySimilarName(textFieldBuscarProduto.getText())));
			}
		});
		GroupBoxProdutos.add(textFieldBuscarProduto);
		
		JLabel lblBuscarProduto = new JLabel("Buscar:");
		lblBuscarProduto.setLabelFor(textFieldBuscarProduto);
		lblBuscarProduto.setBounds(10, 25, 46, 14);
		GroupBoxProdutos.add(lblBuscarProduto);
		//FIM TAB ESTOQUE
		JPanel TabbedPanelFatura = new JPanel();
		TabbedPanelFatura.setBackground(Color.WHITE);
		tabbedPane.addTab("Faturas", null, TabbedPanelFatura, null);
		TabbedPanelFatura.setLayout(null);
		
		JPanel GroupBoxFatura = new JPanel();
		GroupBoxFatura.setBackground(Color.WHITE);
		GroupBoxFatura.setLayout(null);
		GroupBoxFatura.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Faturas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupBoxFatura.setBounds(10, 11, 808, 430);
		TabbedPanelFatura.add(GroupBoxFatura);
				
		JLabel lblBuscarFatura = new JLabel("Status:");
		lblBuscarFatura.setBounds(10, 26, 63, 14);
		GroupBoxFatura.add(lblBuscarFatura);
				
		JButton btnTodosFatura = new JButton("Todos");
		btnTodosFatura.setBackground(new Color(129, 226, 217));
		btnTodosFatura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.setTableModel(tableFatura, new FaturaTableModel(FaturaDAO.getInstance().retrieveAll()));
			}
		});
		btnTodosFatura.setBounds(304, 22, 77, 23);
		GroupBoxFatura.add(btnTodosFatura);
				
		JButton btnNovaFatura = new JButton("Nova fatura");
		btnNovaFatura.setBackground(new Color(129, 226, 217));
		btnNovaFatura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Controller.getClienteSelecionado() != null)
				{
					RegistrarFaturaFrame registrarFaturaFrame = new RegistrarFaturaFrame(Principal.this, Controller.getClienteSelecionado());
					registrarFaturaFrame.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Selecione um cliente!");
				}
			}
		});
		btnNovaFatura.setBounds(391, 22, 135, 23);
		GroupBoxFatura.add(btnNovaFatura);
				
		JButton btnApagarFatura = new JButton("Apagar Fatura");
		btnApagarFatura.setBackground(new Color(129, 226, 217));
		btnApagarFatura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					if(Controller.getFaturaSelecionado() != null)
					{
						FaturaDAO.getInstance().delete(Controller.getFaturaSelecionado());
						Controller.setFaturaSelecionadoNull();
						Controller.setTableModel(tableFatura, new FaturaTableModel(FaturaDAO.getInstance().retrieveAll()));
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Selecione uma fatura!");
					}
			}
		});
		btnApagarFatura.setBounds(536, 22, 131, 23);
		GroupBoxFatura.add(btnApagarFatura);
				
		JButton btnConcluirFatura = new JButton("Concluir Fatura");
		btnConcluirFatura.setBackground(new Color(129, 226, 217));
		btnConcluirFatura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					if(Controller.getFaturaSelecionado() != null)
					{
						Fatura f = new Fatura(Controller.getFaturaSelecionado().getId(), Controller.getFaturaSelecionado().getProprietario(), Controller.getFaturaSelecionado().getValorTotal(), Fatura.StatusPagamento.PAGO, Controller.getFaturaSelecionado().getDataVencimento());
						FaturaDAO.getInstance().update(f);
						Controller.setTableModel(tableFatura, new FaturaTableModel(FaturaDAO.getInstance().retrieveAll()));

					}
					else
					{
						JOptionPane.showMessageDialog(null, "Selecione uma fatura!");
					}
			}
		});
		btnConcluirFatura.setBounds(677, 22, 121, 23);
		GroupBoxFatura.add(btnConcluirFatura);
		
		tableFatura = new JTable();
		tableFatura.setBackground(new Color(184, 239, 234));
		tableFatura.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome","Valor", "Data de Vencimento", "Status"
			}
		));
		tableFatura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.setSelected(((GenericTableModel)tableFatura.getModel()).getItem(tableFatura.getSelectedRow()));
			}});
		JScrollPane scrollPaneFatura = new JScrollPane(tableFatura);
		scrollPaneFatura.setBounds(10, 51, 788, 368);
		GroupBoxFatura.add(scrollPaneFatura);
		
		JComboBox<Fatura.StatusPagamento> comboBoxBuscarFatura = new JComboBox();
		comboBoxBuscarFatura.setBackground(new Color(129, 226, 217));
		comboBoxBuscarFatura.setBounds(83, 22, 211, 22);
		GroupBoxFatura.add(comboBoxBuscarFatura);
		comboBoxBuscarFatura.addItem(null);
		comboBoxBuscarFatura.addItem(Fatura.StatusPagamento.EM_ATRASO);
		comboBoxBuscarFatura.addItem(Fatura.StatusPagamento.PENDENTE);
		comboBoxBuscarFatura.addItem(Fatura.StatusPagamento.PAGO);
		
		comboBoxBuscarFatura.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    		Fatura.StatusPagamento statusSelecionado = (Fatura.StatusPagamento) comboBoxBuscarFatura.getSelectedItem();
			        if (statusSelecionado == Fatura.StatusPagamento.EM_ATRASO) {
			            Controller.setTableModel(tableFatura, new FaturaTableModel(
			                FaturaDAO.getInstance().retrieveByStatus(Fatura.StatusPagamento.EM_ATRASO)));
			        } else if (statusSelecionado == Fatura.StatusPagamento.PENDENTE) {
			            Controller.setTableModel(tableFatura, new FaturaTableModel(
			                FaturaDAO.getInstance().retrieveByStatus(Fatura.StatusPagamento.PENDENTE)));
			        } else if (statusSelecionado == Fatura.StatusPagamento.PAGO){
			            Controller.setTableModel(tableFatura, new FaturaTableModel(
			                FaturaDAO.getInstance().retrieveByStatus(Fatura.StatusPagamento.PAGO)));
			        } else
			        {
			        	Controller.setTableModel(tableFatura, new FaturaTableModel(FaturaDAO.getInstance().retrieveAll()));	
			        }
		    }
		});
		
	}
}

