package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.sun.javafx.binding.SelectBinding.AsInteger;

import DTO.ComponenteDTO;
import DTO.HardwareDTO;
import DTO.IncidenciaDTO;
import DTO.SoftwareDTO;
import DTO.SolicitudDTO;
import gestores.GestorComponentes;
import gestores.GestorSolicitudes;
import javafx.scene.control.ComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class SolicitudTecnicoPanel extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private GestorSolicitudes gs = GestorSolicitudes.getInstance();
	private String hardSoft;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public SolicitudTecnicoPanel(SolicitudDTO idto) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLACK));

		setContentPane(contentPane);
		contentPane.setBackground(new Color(0x566573));

		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Solicitud para el equipo: ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(162, 11, 220, 14);
		contentPane.add(lblNewLabel);
		
		if(idto.getComponente() instanceof HardwareDTO) {
			hardSoft = "Hardware";
		}
		if(idto.getComponente() instanceof SoftwareDTO) {
			hardSoft = "Software";
		}
		JButton btnNewButton = new JButton("Resolver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar c2 = new GregorianCalendar();
				String fecha = Integer.toString(c2.get(Calendar.YEAR)) + "-" + Integer.toString(c2.get(Calendar.MONTH))
						+ "-" + Integer.toString(c2.get(Calendar.DATE));
				IncidenciaDTO idtoObj = gs.getIncidencia(idto.getCodigo());
				idtoObj.setFechaFin(fecha);
				idtoObj.setEstado("atendida");
				gs.modificarIncidencia(idtoObj);
				dispose();
				
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
		ButtonGroup bg = new ButtonGroup();

		textArea = new JTextArea(idto.getDescripcion());
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(10, 172, 521, 96);
		textArea.setBackground(new Color(0x141d26));
		contentPane.add(textArea);

		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setBounds(10, 154, 68, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("TIPO:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 81, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblHardSoft = new JLabel(hardSoft);
		lblHardSoft.setForeground(Color.WHITE);
		lblHardSoft.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHardSoft.setBounds(66, 82, 98, 14);
		contentPane.add(lblHardSoft);
		
		JLabel lblNewLabel_3 = new JLabel("\u00BFHay stock?");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 119, 117, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblStockSiNo = new JLabel("");
		lblStockSiNo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStockSiNo.setForeground(Color.WHITE);
		lblStockSiNo.setBounds(135, 120, 46, 14);
		contentPane.add(lblStockSiNo);
	}
}
