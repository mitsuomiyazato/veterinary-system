package view;

import java.time.LocalDateTime;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class VisualizarReceitaMedica extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldCliente;
    private JTextField textFieldPaciente;
    private JTextField textFieldVeterinario;

    public VisualizarReceitaMedica(String cliente, String paciente, String veterinario, List<String> medicamentos, LocalDateTime data, String observacoes) {
        setTitle("Receita Médica");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 406, 461);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblPaciente = new JLabel("Paciente:");
        lblPaciente.setBounds(10, 50, 72, 14);
        contentPane.add(lblPaciente);
        
        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(10, 25, 72, 14);
        contentPane.add(lblCliente);
        
        JLabel lblVeterinario = new JLabel("Veterinário:");
        lblVeterinario.setBounds(10, 75, 72, 14);
        contentPane.add(lblVeterinario);
        
        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(283, 25, 97, 14);
        contentPane.add(lblData);
        
        JLabel lblObservacoes = new JLabel("Observações:");
        lblObservacoes.setBounds(10, 246, 97, 14);
        contentPane.add(lblObservacoes);
        
        JLabel lblObservacoesResp = new JLabel(observacoes);
        lblObservacoesResp.setBounds(10, 271, 370, 140);
        contentPane.add(lblObservacoesResp);

        // Criando o modelo da lista para os medicamentos
        DefaultListModel<String> listModel = new DefaultListModel<>();
        
        // Adicionando os medicamentos ao modelo da lista
        for (String medicamento : medicamentos) {
            listModel.addElement(medicamento);
        }

        // Criando o JList com o modelo preenchido e adicionando ao JScrollPane
        JList<String> listMedicamentos = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listMedicamentos);
        scrollPane.setBounds(10, 136, 370, 99);
        contentPane.add(scrollPane);

        JLabel lblMedicamentos = new JLabel("Medicamentos:");
        lblMedicamentos.setBounds(10, 111, 97, 14);
        contentPane.add(lblMedicamentos);
        
        textFieldCliente = new JTextField();
        textFieldCliente.setEditable(false);
        textFieldCliente.setBounds(92, 22, 164, 20);
        textFieldCliente.setColumns(10);
        textFieldCliente.setText(cliente);
        contentPane.add(textFieldCliente);
        
        textFieldPaciente = new JTextField();
        textFieldPaciente.setEditable(false);
        textFieldPaciente.setColumns(10);
        textFieldPaciente.setBounds(92, 47, 164, 20);
        textFieldPaciente.setText(paciente);
        contentPane.add(textFieldPaciente);
        
        textFieldVeterinario = new JTextField();
        textFieldVeterinario.setEditable(false);
        textFieldVeterinario.setColumns(10);
        textFieldVeterinario.setBounds(92, 72, 164, 20);
        textFieldVeterinario.setText(veterinario);
        contentPane.add(textFieldVeterinario);
        
        JLabel lblDataVal = new JLabel("##/##/##");
        lblDataVal.setBounds(283, 50, 97, 14);
        int day = data.getDayOfMonth();
        int month = data.getMonthValue();
        int year = data.getYear();
        lblDataVal.setText(day + "/" + month + "/" + year);
        contentPane.add(lblDataVal);
    }
}
