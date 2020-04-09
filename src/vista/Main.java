package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DTO.AulaDTO;
import DTO.EquipoDTO;
import DTO.IncidenciaDTO;
import DTO.SolicitudDTO;
import DTO.UsuarioDTO;
import Gestores.GestorAulas;
import Gestores.GestorEquipos;
import Gestores.GestorUsuarios;

import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.codec.digest.DigestUtils;
import DAO.AulaDAO;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;

public class Main extends JFrame {
	int linea1 = 0;
	int linea2 = 0;
	int linea3 = 0;
	int linea4 = 0;
	int linea5 = 0;
	private GestorUsuarios gu = new GestorUsuarios();
	private GestorEquipos ge = new GestorEquipos();
	ArrayList<EquipoDTO> listaEquipos = new ArrayList<EquipoDTO>();
	JPanel panel_3;
	private GestorAulas ga = new GestorAulas();
	private JComboBox comboBox;
	private JPanel aulas;
	private JPanel contentPane;
	int xx, xy;
	private JTable table;
	private JTable table_1;
	private JTextField txtNombreOnline;
	private JTextField txtEmailOnline;
	private JTextField txtEquipoOnline;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setUndecorated(true);
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
	public Main() {

		ga.cargarListaAulas(1);
		ge.cargarListaEquipos();

		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1417, 866);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0x141d26));
		panel.setBounds(0, 0, 65, 878);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lvlCerrarSesion = new JLabel("");
		lvlCerrarSesion.setIcon(new ImageIcon(Main.class.getResource("/images/salida (3).png")));
		lvlCerrarSesion.setBounds(10, 789, 46, 64);
		panel.add(lvlCerrarSesion);
		lvlCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				logOut();
			}
		});

		JLabel lvlSalida_1_1_1 = new JLabel("");
		lvlSalida_1_1_1.setIcon(new ImageIcon(Main.class.getResource("/images/producto.png")));
		lvlSalida_1_1_1.setBounds(10, 413, 46, 64);
		panel.add(lvlSalida_1_1_1);

		JLabel lvlSalida_1_1_2_1 = new JLabel("");
		lvlSalida_1_1_2_1.setIcon(new ImageIcon(Main.class.getResource("/images/almacen.png")));
		lvlSalida_1_1_2_1.setBounds(10, 306, 46, 64);
		panel.add(lvlSalida_1_1_2_1);

		JLabel lbl_close = new JLabel("X");
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				System.exit(0);
			}
		});
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_close.setBounds(1381, 0, 37, 27);
		contentPane.add(lbl_close);

		JPanel perfil = new JPanel();
		perfil.setBackground(Color.WHITE);
		perfil.setBounds(64, 1000, 1311, 878);
		contentPane.add(perfil);
		perfil.setLayout(null);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(0x566573));
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2_1.setBounds(0, 0, 462, 878);
		perfil.add(panel_2_1);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setBounds(20, 300, 118, 14);
		panel_2_1.add(lblNombre);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(20, 376, 71, 14);
		panel_2_1.add(lblEmail);

		JLabel lblEquipoAsociado = new JLabel("Ordenador asociado");
		lblEquipoAsociado.setForeground(Color.WHITE);
		lblEquipoAsociado.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEquipoAsociado.setBounds(20, 451, 155, 14);
		panel_2_1.add(lblEquipoAsociado);

		JLabel lblInstPendientes = new JLabel("Instalaciones Pendientes");
		lblInstPendientes.setForeground(Color.WHITE);
		lblInstPendientes.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInstPendientes.setBounds(20, 614, 188, 14);
		panel_2_1.add(lblInstPendientes);

		JLabel lblCaracteristicas_4 = new JLabel("");
		lblCaracteristicas_4.setBounds(10, 327, 408, 63);
		panel_2_1.add(lblCaracteristicas_4);

		JLabel lblCaracteristicas_1_1 = new JLabel("");
		lblCaracteristicas_1_1.setBounds(20, 426, 408, 63);
		panel_2_1.add(lblCaracteristicas_1_1);

		JLabel lblCaracteristicas_2_1 = new JLabel("");

		JButton btnSolicitud = new JButton("Solicitud");
		btnSolicitud.setBackground(new Color(51, 204, 153));
		btnSolicitud.setForeground(Color.BLACK);
		btnSolicitud.setBounds(604, 300, 179, 49);
		JButton btnIncidencia = new JButton("Incidencia");
		btnIncidencia.setBackground(new Color(51, 204, 153));
		btnIncidencia.setForeground(Color.BLACK);
		btnIncidencia.setBounds(975, 295, 179, 146);
		perfil.add(btnIncidencia);
		lblCaracteristicas_2_1.setBounds(20, 540, 408, 63);
		panel_2_1.add(lblCaracteristicas_2_1);

		JLabel lblCaracteristicas_3_1 = new JLabel("");
		lblCaracteristicas_3_1.setBounds(20, 635, 408, 63);
		panel_2_1.add(lblCaracteristicas_3_1);

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(Main.class.getResource("/images/usuario (1).png")));
		lblNewLabel_7.setBounds(58, 21, 265, 281);
		panel_2_1.add(lblNewLabel_7);

		txtNombreOnline = new JTextField();
		txtNombreOnline.setBounds(20, 329, 118, 22);
		panel_2_1.add(txtNombreOnline);
		txtNombreOnline.setColumns(10);

		txtEmailOnline = new JTextField();
		txtEmailOnline.setColumns(10);
		txtEmailOnline.setBounds(20, 403, 118, 22);
		panel_2_1.add(txtEmailOnline);

		txtEquipoOnline = new JTextField();
		txtEquipoOnline.setColumns(10);
		txtEquipoOnline.setBounds(20, 478, 118, 22);
		panel_2_1.add(txtEquipoOnline);

		JButton btnNewButton = new JButton("Solicitud");
		btnNewButton.setBackground(new Color(255, 153, 102));
		btnNewButton.setBounds(635, 295, 179, 146);
		perfil.add(btnNewButton);

		aulas = new JPanel();
		aulas.setBackground(Color.WHITE);
		aulas.setBounds(88, 37, 1287, 791);
		contentPane.add(aulas);
		aulas.setLayout(null);
		aulas.setVisible(false);

		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setAutoscrolls(true);
		panel_3.setBounds(40, 150, 691, 569);
		aulas.add(panel_3);

		panel_3.setLayout(null);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_3.removeAll();
				panel_3.revalidate();
				panel_3.repaint();
				cargarEquiposAula();
				//panel_3.removeAll();

				

			}
		});
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox.addItem("Seleccione un aula");
		comboBox.setBounds(46, 44, 190, 31);
		aulas.add(comboBox);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0x566573));
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(768, 44, 458, 701);
		aulas.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Caracteristicas");
		lblNewLabel.setForeground(Color.WHITE);

		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 118, 14);
		panel_2.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Software");
		lblNewLabel_1.setForeground(Color.WHITE);

		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 110, 71, 14);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Hardware");
		lblNewLabel_1_1.setForeground(Color.WHITE);

		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(10, 224, 82, 14);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Instalaciones Pendientes");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);

		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(10, 319, 188, 14);
		panel_2.add(lblNewLabel_1_1_1);

		JLabel lblCaracteristicas = new JLabel("");
		lblCaracteristicas.setBounds(20, 36, 408, 63);
		panel_2.add(lblCaracteristicas);

		JLabel lblCaracteristicas_1 = new JLabel("");
		lblCaracteristicas_1.setBounds(20, 135, 408, 63);
		panel_2.add(lblCaracteristicas_1);

		JLabel lblCaracteristicas_2 = new JLabel("");
		lblCaracteristicas_2.setBounds(20, 249, 408, 63);
		panel_2.add(lblCaracteristicas_2);

		JLabel lblCaracteristicas_3 = new JLabel("");
		lblCaracteristicas_3.setBounds(20, 353, 408, 63);
		panel_2.add(lblCaracteristicas_3);
		
		JButton btnNewButton_2 = new JButton("Modificar");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setBackground(Color.ORANGE);
		btnNewButton_2.setBounds(768, 744, 229, 36);
		aulas.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Eliminar");
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.setBackground(new Color(220, 20, 60));
		
		btnNewButton_3.setBounds(997, 744, 229, 36);
		aulas.add(btnNewButton_3);

		JPanel incidencias = new JPanel();
		incidencias.setBackground(Color.WHITE);
		incidencias.setBounds(88, 1000, 1287, 757);
		contentPane.add(incidencias);
		incidencias.setLayout(null);
		JList list = new JList(new Object[] {
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si",
				"2020-06-02 Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha si", });
		list.setFont(new Font("Tahoma", Font.PLAIN, 11));
		list.setBounds(28, 82, 558, 581);
		incidencias.add(list);

		JButton btnNewButton_1 = new JButton("Visualizar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(list.getSelectedValue());
			}
		});
		btnNewButton_1.setBounds(558, 690, 128, 45);
		incidencias.add(btnNewButton_1);

		table = new JTable();
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setModel(new DefaultTableModel(new Object[][] { { "2020-06-02",
				"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. I" },
				{ "2020-06-02",
						"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. I" },
				{ "2020-06-02",
						"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. I" },
				{ "2020-06-02",
						"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. I" },
				{ "2020-06-02",
						"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. I" }, },
				new String[] { "Fecha", "Descripci\u00F3n" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(546);

		table.setBounds(690, 82, 558, 581);
		incidencias.add(table);

		JPanel crud = new JPanel();
		crud.setBounds(101, 1000, 1278, 767);
		crud.setBackground(Color.WHITE);

		contentPane.add(crud);
		crud.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(54, 28, 1177, 89);
		crud.add(panel_4);
		panel_4.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0x566573));
		panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_5.setBounds(235, 0, 235, 89);
		panel_4.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Eliminar");
		lblNewLabel_2.setForeground(Color.WHITE);

		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(105, 32, 76, 20);
		panel_5.add(lblNewLabel_2);

		JLabel lblNewLabel_11 = new JLabel("New label");
		lblNewLabel_11.setIcon(new ImageIcon(Main.class.getResource("/images/eliminar.png")));
		lblNewLabel_11.setBounds(10, 11, 69, 67);
		panel_5.add(lblNewLabel_11);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0x566573));

		panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_6.setBounds(471, 0, 235, 89);
		panel_4.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Modificar");
		lblNewLabel_3.setForeground(Color.WHITE);

		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(111, 34, 87, 19);
		panel_6.add(lblNewLabel_3);

		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setIcon(new ImageIcon(Main.class.getResource("/images/cambio (1).png")));
		lblNewLabel_12.setBounds(10, 11, 75, 67);
		panel_6.add(lblNewLabel_12);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(0x566573));

		panel_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_7.setBounds(706, 0, 235, 89);
		panel_4.add(panel_7);
		panel_7.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Crear");
		lblNewLabel_4.setForeground(Color.WHITE);

		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(135, 36, 53, 19);
		panel_7.add(lblNewLabel_4);

		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon(Main.class.getResource("/images/anadir (1).png")));
		lblNewLabel_13.setBounds(28, 11, 64, 67);
		panel_7.add(lblNewLabel_13);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(0x566573));

		panel_8.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_8.setBounds(942, 0, 235, 89);
		panel_4.add(panel_8);
		panel_8.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Promocionar");
		lblNewLabel_5.setForeground(Color.WHITE);

		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(82, 35, 95, 19);
		panel_8.add(lblNewLabel_5);

		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setBounds(2, 0, 69, 89);
		panel_8.add(lblNewLabel_10);
		lblNewLabel_10.setIcon(new ImageIcon(Main.class.getResource("/images/flecha-arriba (4).png")));

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(0x566573));

		panel_9.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_9.setBounds(0, 0, 235, 89);
		panel_4.add(panel_9);
		panel_9.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("Degradar");
		lblNewLabel_6.setForeground(Color.WHITE);

		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6.setBounds(93, 37, 91, 19);
		panel_9.add(lblNewLabel_6);

		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon(Main.class.getResource("/images/descargar.png")));
		lblNewLabel_9.setBounds(10, 11, 64, 67);
		panel_9.add(lblNewLabel_9);

		table_1 = new JTable();
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column", "New column",
						"New column", "New column", "New column" }));
		table_1.setBounds(54, 178, 1183, 544);
		crud.add(table_1);

		JLabel lvlSalida_1 = new JLabel("");
		lvlSalida_1.setIcon(new ImageIcon(Main.class.getResource("/images/cuenta.png")));
		lvlSalida_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				aulas.setVisible(false);
				perfil.setVisible(true);
				incidencias.setVisible(false);
				crud.setVisible(false);

			}
		});
		lvlSalida_1.setBounds(10, 11, 46, 64);
		panel.add(lvlSalida_1);

		JLabel lvlSalida_1_1_2 = new JLabel("");
		lvlSalida_1_1_2.setIcon(new ImageIcon(Main.class.getResource("/images/colegio (2).png")));
		lvlSalida_1_1_2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				aulas.setVisible(true);
				perfil.setVisible(false);
				incidencias.setVisible(false);
				crud.setVisible(false);

			}
		});
		lvlSalida_1.setBounds(10, 11, 46, 64);
		panel.add(lvlSalida_1);
		lvlSalida_1_1_2.setBounds(10, 204, 46, 64);
		panel.add(lvlSalida_1_1_2);
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(Main.class.getResource("/images/comunicacion (2).png")));
		lblNewLabel_8.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				aulas.setVisible(false);
				perfil.setVisible(false);
				incidencias.setVisible(false);
				crud.setVisible(true);
			}
		});
		lblNewLabel_8.setBounds(10, 488, 46, 39);
		panel.add(lblNewLabel_8);
		JLabel lvlSalida_1_1 = new JLabel("");
		lvlSalida_1_1.setIcon(new ImageIcon(Main.class.getResource("/images/ordenador-portatil.png")));
		lvlSalida_1_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				aulas.setVisible(false);
				perfil.setVisible(false);
				incidencias.setVisible(true);
				crud.setVisible(false);

			}
		});
		lvlSalida_1_1.setBounds(10, 112, 46, 64);
		panel.add(lvlSalida_1_1);

		incidencias.setVisible(false);

		cargarUsuarioOnline();
		cargarDesplegableAula();
	}

	private void cargarUsuarioOnline() {
		UsuarioDTO uo = gu.getUserOnline();
		try {
			if (uo != null) {
				txtNombreOnline.setText(uo.getUserName());
				txtEmailOnline.setText(uo.getEmail());
				txtEquipoOnline.setText(((Integer) uo.getIdEquipo()).toString());
				/*
				 * for(IncidenciaDTO inci : uo.getIncidencias()) { if(inci instanceof
				 * SolicitudDTO) { //TODO } }
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void logOut() {
		gu.logOut();
		this.dispose();
		Home h = new Home();
		h.setUndecorated(true);
		h.setVisible(true);
	}

	private void cargarDesplegableAula() {
		ArrayList<AulaDTO> listaAulas = ga.getListaAulas();

		for (AulaDTO a : listaAulas) {
			comboBox.addItem(a.getNombre());
		}
	}

	private void cargarEquiposAula() {
		this.linea1 = 0;
		this.linea2 = 0;
		listaEquipos.clear();
		System.out.println(listaEquipos.size());
		try {
			AulaDTO adto = ga.getAulaByNombre(comboBox.getSelectedItem().toString());
			listaEquipos = ge.getEquiposAula(adto.getIdAula());
			for (EquipoDTO eq : listaEquipos) {
				//System.out.println(eq.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(listaEquipos.size());

		for (int i = 0; i < listaEquipos.size(); i++) {

			int posicion1 = linea1 * 15;
			int posicion2 = linea2 * 15;

			int e = i;
			JLabel lvlNombreEquipo = new JLabel(listaEquipos.get(i).getNombre());
			JLabel lblEquipo = new JLabel("");
			lblEquipo.setName(listaEquipos.get(i).getNombre());
			lblEquipo.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent arg0) {

					/*
					 * lblCaracteristicas.setText(listaEquipos.get(e).getNombre());
					 * lblCaracteristicas_1.setText(listaEquipos.get(e).getNombre());
					 * lblCaracteristicas_2.setText(listaEquipos.get(e).getNombre());
					 * lblCaracteristicas_3.setText(listaEquipos.get(e).getNombre());
					 */
				}
			});
			if (i < 6) {
				lblEquipo.setBounds(10 * posicion1, 0, 76, 100);
				lvlNombreEquipo.setBounds(10 * posicion1, 78, 56, 14);
				panel_3.add(lblEquipo);
				lblEquipo.setIcon(new ImageIcon(Main.class.getResource("/images/pc-de-la-torre (2).png")));
				linea1++;
			}
			if (i >= 5 && i <= 11) {
				lblEquipo.setBounds(10 * posicion2, 90, 120, 100);
				lvlNombreEquipo.setBounds(10 * posicion2, 165, 56, 14);
				panel_3.add(lblEquipo);
				lblEquipo.setIcon(new ImageIcon(Main.class.getResource("/images/pc-de-la-torre (2).png")));
				linea2++;
			}

			panel_3.add(lvlNombreEquipo);
			lvlNombreEquipo.setFont(new Font("Tahoma", Font.PLAIN, 13));

		}
	}

	private boolean actualizarPerfil() {
		System.out.println(gu.getUserOnline());
		UsuarioDTO u = gu.getUserOnline();
		u.setUserName(txtNombreOnline.getText());
		u.setEmail(txtEmailOnline.getText());
		System.out.println(gu.getUserOnline());
		return gu.modificarUsuario(u);
	}

	private boolean cambiarPass() {
		UsuarioDTO u = gu.getUserOnline();
		JTextField oldPass = new JTextField();
		JTextField newPass = new JTextField();
		JTextField repeatNewPass = new JTextField();
		Object[] message = { "Old pass:", oldPass, "New pass:", newPass, "Repeat new pass:", repeatNewPass };
		int option = JOptionPane.showConfirmDialog(null, message, "Llena el formulario", JOptionPane.OK_CANCEL_OPTION);
		try {
			if (DigestUtils.sha256Hex(oldPass.getText()).equals(u.getPass())) {
				if (newPass.getText().equals(repeatNewPass.getText()) && !newPass.getText().equals(oldPass.getText())) {
					u.setPass(newPass.getText());
					return gu.modificarUsuario(u);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private void tal() {
		cargarEquiposAula();

	}
}
