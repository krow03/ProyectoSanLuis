package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class SolicitudUsuPanel extends JFrame {

	private JPanel contentPane;
	private String dios;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SolicitudUsuPanel frame = new SolicitudUsuPanel();
					frame.setLocationRelativeTo(null);
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
	public SolicitudUsuPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLACK));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(0x566573));

		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Incidencia para el equipo: ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(171, 11, 195, 26);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea(dios);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(10, 83, 521, 170);
		textArea.setBackground(new Color(0x141d26));
		contentPane.add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 48, 136, 24);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.getText();
			}
		});
		btnNewButton.setBackground(new Color(0x43B581));
		btnNewButton.setBounds(140, 283, 89, 41);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(0xF44444));
		btnNewButton_1.setBounds(283, 283, 89, 41);
		contentPane.add(btnNewButton_1);
	}
	public void soli(String hola) {
		this.dios = hola;
		System.out.println(hola);
	}
}
