package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.sun.javafx.binding.SelectBinding.AsInteger;

import DTO.ComponenteDTO;
import DTO.EquipoDTO;
import DTO.IncidenciaDTO;
import DTO.RequisitoDTO;
import DTO.SoftwareDTO;
import DTO.SolicitudDTO;
import gestores.GestorComponentes;
import gestores.GestorEquipos;
import gestores.GestorSolicitudes;
import gestores.GestorUsuarios;
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
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class SolicitudUsuarioPanel extends JFrame {

	private JPanel contentPane;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JComboBox comboBox;
	private JTextArea textArea;
	private GestorComponentes gc = GestorComponentes.getInstance();
	private GestorEquipos ge = GestorEquipos.getInstance();
	private GestorSolicitudes gs = GestorSolicitudes.getInstance();
	private GestorUsuarios gu = GestorUsuarios.getInstance();

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public SolicitudUsuarioPanel() {
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

		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EquipoDTO edto= gu.getUserOnline().getEquipo();
				for(ComponenteDTO cdto : edto.getComponentes()) {
					
				}

				Calendar c2 = new GregorianCalendar();
				String fecha = Integer.toString(c2.get(Calendar.YEAR)) + "-" + Integer.toString(c2.get(Calendar.MONTH))
						+ "-" + Integer.toString(c2.get(Calendar.DATE));
				System.out.println(comboBox.getSelectedItem().toString());
				if (comboBox.getSelectedItem().toString().equals("Sin seleccion")) {
					JOptionPane.showMessageDialog(null, "Seleccione un componente");
					return;
				} else {
					SolicitudDTO solicitud;
					if (rdbtnNewRadioButton.isSelected()) {
						int part1 = 0;
						ComponenteDTO temp = null;
						if (!comboBox.getSelectedItem().equals("Sin seleccionar")) {
							String[] parts = ((String) comboBox.getSelectedItem()).split(" -");
							part1 = Integer.parseInt(parts[0]);
							temp = gc.getComponenteId(part1);
						}
						
						solicitud = new SolicitudDTO(gu.getUserOnline(),
								gu.getUserOnline().getEquipo().getIdEquipo(), fecha, "pendiente", textArea.getText(), part1);
						try {
							boolean req = false;
							boolean peso =false;
							for(ComponenteDTO cdto : gu.getUserOnline().getEquipo().getComponentes()) {
								for(RequisitoDTO rdto : temp.getRequisitos()) {
									if(rdto.getClave().equals(cdto.getDescripcion())) req=true;
								}
								if((gu.getUserOnline().getEquipo().getDisponibleHDD()-((SoftwareDTO)cdto).getPeso())>=0) peso =true;					
							}
							if(req && peso) {
								gs.crearSolicitud(solicitud);
								ge.cargarComponentesEquipo();
							}

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if (rdbtnNewRadioButton_1.isSelected()) {
						int part1 = 0;
						ComponenteDTO temp = null;
						if (!comboBox.getSelectedItem().equals("Sin seleccionar")) {
							String[] parts = ((String) comboBox.getSelectedItem()).split(" -");
							part1 = Integer.parseInt(parts[0]);
							temp = gc.getComponenteId(part1);
						}
						
						solicitud = new SolicitudDTO(gu.getUserOnline(),
								gu.getUserOnline().getEquipo().getIdEquipo(), fecha, "pendiente", textArea.getText(), part1);
					}
					JOptionPane.showMessageDialog(null, "Solicitud creada correctamente");

				}

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

		rdbtnNewRadioButton = new JRadioButton("Hardware");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();

				for (ComponenteDTO componente : gc.getListaHardware()) {
					comboBox.addItem(componente.getIdComponente() + " -" + componente.getDescripcion());
				
				}

			}
		});
		rdbtnNewRadioButton.setForeground(Color.WHITE);
		rdbtnNewRadioButton.setBounds(147, 58, 109, 23);
		rdbtnNewRadioButton.setBackground(new Color(0x566573));

		contentPane.add(rdbtnNewRadioButton);

		rdbtnNewRadioButton_1 = new JRadioButton("Software");
		rdbtnNewRadioButton_1.setForeground(Color.WHITE);
		rdbtnNewRadioButton_1.setBounds(273, 58, 109, 23);
		rdbtnNewRadioButton_1.setBackground(new Color(0x566573));
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				for (ComponenteDTO componente : gc.getListaSoftware()) {
					comboBox.addItem(componente.getIdComponente() + " -" + componente.getDescripcion());
				
				}

			}
		});

		contentPane.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton);
		comboBox = new JComboBox();
		comboBox.setForeground(Color.WHITE);
		comboBox.setBounds(10, 99, 521, 33);
		comboBox.setBackground(new Color(0x566573));
		comboBox.addItem("Sin seleccion");

		contentPane.add(comboBox);

		textArea = new JTextArea();
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(10, 172, 521, 96);
		textArea.setBackground(new Color(0x141d26));
		contentPane.add(textArea);

		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setBounds(10, 154, 68, 14);
		contentPane.add(lblNewLabel_1);
	}
}
