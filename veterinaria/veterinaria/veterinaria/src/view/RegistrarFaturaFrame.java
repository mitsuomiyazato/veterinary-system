package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Fatura;
import model.FaturaDAO;
import model.Proprietario;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

public class RegistrarFaturaFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldValorFatura;
    private JFormattedTextField formattedTextFieldDataVencimento;

    public RegistrarFaturaFrame(Principal principalFrame, Proprietario cliente) {
        setTitle("Registrar Fatura");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 211);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        textFieldValorFatura = new JTextField();
        textFieldValorFatura.setText("0.0");
        textFieldValorFatura.setColumns(10);
        textFieldValorFatura.setBounds(138, 24, 286, 20);
        contentPane.add(textFieldValorFatura);

        JLabel lblValorFatura = new JLabel("Valor:");
        lblValorFatura.setLabelFor(textFieldValorFatura);
        lblValorFatura.setBounds(10, 27, 98, 14);
        contentPane.add(lblValorFatura);

        JLabel lblDataVencimento = new JLabel("Data de Vencimento:");
        lblDataVencimento.setBounds(10, 92, 118, 14);
        contentPane.add(lblDataVencimento);

        formattedTextFieldDataVencimento = new JFormattedTextField();
        formattedTextFieldDataVencimento.setBounds(138, 89, 286, 20);
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            dateMask.install(formattedTextFieldDataVencimento);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        contentPane.add(formattedTextFieldDataVencimento);

        JButton btnRegistrarFatura = new JButton("Registrar");
        btnRegistrarFatura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        btnRegistrarFatura.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!textFieldValorFatura.getText().isEmpty() && !formattedTextFieldDataVencimento.getText().isEmpty()) {
                    double valorFatura;
                    try {
                        valorFatura = Double.parseDouble(textFieldValorFatura.getText());
                        if (valorFatura <= 0) {
                            throw new NumberFormatException("O valor deve ser positivo.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Insira um valor numérico válido e maior que zero para a fatura.");
                        return;
                    }
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String dataVencimento = formattedTextFieldDataVencimento.getText();
                    try {
                        LocalDate parsedDate = LocalDate.parse(dataVencimento, dateFormatter);
                        LocalDate dataAtual = LocalDate.now();
                        Fatura.StatusPagamento status;
                        if(dataAtual.isAfter(parsedDate))
                        {
                        	status = Fatura.StatusPagamento.EM_ATRASO;
                        }
                        else
                        {
                        	status = Fatura.StatusPagamento.PENDENTE;
                        }
                        
                        FaturaDAO.getInstance().create(cliente, valorFatura, status, parsedDate);
                        JOptionPane.showMessageDialog(null, "Fatura registrada com sucesso!");
                        principalFrame.atualizarTableFatura();
                        dispose();
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(null, "Data de vencimento inválida. Use o formato dd/MM/yyyy.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                }
            }
        });
        btnRegistrarFatura.setBounds(167, 141, 89, 23);
        contentPane.add(btnRegistrarFatura);
    }
}
