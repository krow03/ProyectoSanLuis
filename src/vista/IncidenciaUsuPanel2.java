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
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.Font;

public class IncidenciaUsuPanel2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IncidenciaUsuPanel2 frame = new IncidenciaUsuPanel2();
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
	public IncidenciaUsuPanel2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Solicitud para el equipo: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(162, 11, 220, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.setBackground(new Color(0x43B581));
		btnNewButton.setBounds(140, 283, 89, 41);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBackground(new Color(0xF44444));
		btnNewButton_1.setBounds(283, 283, 89, 41);
		contentPane.add(btnNewButton_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Hardware");
		rdbtnNewRadioButton.setBounds(147, 58, 109, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Software");
		rdbtnNewRadioButton_1.setBounds(273, 58, 109, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(140, 99, 242, 20);
		contentPane.add(comboBox);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Solicitud fuera de stcok");
		rdbtnNewRadioButton_2.setBounds(185, 126, 163, 23);
		contentPane.add(rdbtnNewRadioButton_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 172, 521, 96);
		contentPane.add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setBounds(10, 154, 68, 14);
		contentPane.add(lblNewLabel_1);
	}
}
