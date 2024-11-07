package view;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import controller.Controller;
import model.Agendamento.StatusAgendamento;
import model.AgendamentoDAO;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AgendaInicio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable agendaInicioTable;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AgendaInicio frame = new AgendaInicio();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AgendaInicio() {
    	setResizable(false);
        setTitle("Gerenciador Clínica Veterinária");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 524, 757);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/logo.png"));
        Image logoImage = logoIcon.getImage();
        Image scaledImage = logoImage.getScaledInstance(224, 216, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        JLabel lblLogo = new JLabel(scaledIcon);
        lblLogo.setHorizontalAlignment(JLabel.CENTER);
        lblLogo.setBounds(138, 88, 224, 216);
        contentPane.add(lblLogo);
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        agendaInicioTable = new JTable();
        agendaInicioTable.setForeground(Color.BLACK);
        agendaInicioTable.setBackground(new Color(184, 239, 234));
        agendaInicioTable.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {
                    "Cliente", "Paciente", "Veterinario", "Serviço", "Data"
                }
            ));
        Controller.setTableModel(agendaInicioTable, new AgendaInicioTableModel(AgendamentoDAO.getInstance().retrieveByStatus(StatusAgendamento.AGENDADO)));
        JScrollPane scrollPane = new JScrollPane(agendaInicioTable);
        scrollPane.setBounds(10, 359, 488, 302);
        contentPane.add(scrollPane);
        
        JButton btnEntrarButton = new JButton("Entrar");
        btnEntrarButton.setForeground(Color.BLACK);
        btnEntrarButton.setBackground(new Color(129, 226, 217));
        btnEntrarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    setVisible(false);
                    
                    Principal principal = new Principal();
                    principal.frmVeterinria.setVisible(true);
                    
                    principal.frmVeterinria.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                        	Controller.setTableModel(agendaInicioTable, new AgendaInicioTableModel(AgendamentoDAO.getInstance().retrieveByStatus(StatusAgendamento.AGENDADO)));
                        	setVisible(true);
                        }
                    });
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        });
        btnEntrarButton.setBounds(10, 672, 236, 35);
        contentPane.add(btnEntrarButton);
        
        JLabel lblTitle = new JLabel("Consultório Veterinário");
        lblTitle.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 40));
        lblTitle.setBounds(30, 11, 450, 66);
        contentPane.add(lblTitle);
        
        JButton btnSairButton = new JButton("Sair");
        btnSairButton.setBackground(new Color(129, 226, 217));
        btnSairButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        btnSairButton.setBounds(262, 672, 236, 35);
        contentPane.add(btnSairButton);
        
        JLabel lblInfoTable = new JLabel("Agenda:");
        lblInfoTable.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 30));
        lblInfoTable.setBounds(10, 315, 331, 46);
        contentPane.add(lblInfoTable);
    }
}
