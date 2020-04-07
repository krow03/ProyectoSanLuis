package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import DTO.EquipoDTO;

import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;

public class Main extends JFrame {
	JComboBox comboBox;
	JPanel aulas;
	private JPanel contentPane;
	int xx, xy;

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

		ArrayList<EquipoDTO> listaEquipos = new ArrayList<EquipoDTO>();
		listaEquipos.add(new EquipoDTO("1", "192.167"));
		listaEquipos.add(new EquipoDTO("2", "192.167"));
		listaEquipos.add(new EquipoDTO("3", "192.167"));
		listaEquipos.add(new EquipoDTO("4", "192.167"));
		listaEquipos.add(new EquipoDTO("5", "192.167"));
		listaEquipos.add(new EquipoDTO("6", "192.167"));
		listaEquipos.add(new EquipoDTO("7", "192.167"));
		listaEquipos.add(new EquipoDTO("8", "192.167"));
		listaEquipos.add(new EquipoDTO("9", "192.167"));
		listaEquipos.add(new EquipoDTO("10", "192.167"));

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

		JLabel lvlSalida = new JLabel("");
		lvlSalida.setIcon(new ImageIcon(Main.class.getResource("/images/salida (3).png")));
		lvlSalida.setBounds(10, 789, 46, 64);
		panel.add(lvlSalida);



		JLabel lvlSalida_1_1 = new JLabel("");
		lvlSalida_1_1.setIcon(new ImageIcon(Main.class.getResource("/images/ordenador-portatil.png")));
		lvlSalida_1_1.setBounds(10, 112, 46, 64);
		panel.add(lvlSalida_1_1);

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
		int linea1 = 0;
		int linea2 = 0;
		int linea3 = 0;
		int linea4 = 0;
		int linea5 = 0;

		JPanel perfil = new JPanel();
		perfil.setBackground(Color.WHITE);
		perfil.setBounds(88, 29, 1287, 767);
		contentPane.add(perfil);
		perfil.setLayout(null);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2_1.setBounds(116, 144, 329, 448);
		perfil.add(panel_2_1);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setBounds(10, 11, 118, 14);
		panel_2_1.add(lblNombre);

		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(10, 85, 71, 14);
		panel_2_1.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_2 = new JLabel("Ordenador asociado");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_2.setBounds(10, 162, 155, 14);
		panel_2_1.add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Instalaciones Pendientes");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1.setBounds(10, 319, 188, 14);
		panel_2_1.add(lblNewLabel_1_1_1_1);

		JLabel lblCaracteristicas_4 = new JLabel("");
		lblCaracteristicas_4.setBounds(20, 36, 408, 63);
		panel_2_1.add(lblCaracteristicas_4);

		JLabel lblCaracteristicas_1_1 = new JLabel("");
		lblCaracteristicas_1_1.setBounds(20, 135, 408, 63);
		panel_2_1.add(lblCaracteristicas_1_1);

		JLabel lblCaracteristicas_2_1 = new JLabel("");

		JButton btnSolicitud = new JButton("Solicitud");
		btnSolicitud.setBackground(new Color(51, 204, 153));
		btnSolicitud.setForeground(Color.BLACK);
		btnSolicitud.setBounds(604, 300, 179, 49);
		
		JButton btnIncidencia = new JButton("Incidencia");
		btnIncidencia.setBackground(new Color(51, 204, 153));
		btnIncidencia.setForeground(Color.BLACK);
		btnIncidencia.setBounds(622, 344, 179, 49);
		perfil.add(btnIncidencia);
		lblCaracteristicas_2_1.setBounds(20, 249, 408, 63);
		panel_2_1.add(lblCaracteristicas_2_1);

		JLabel lblCaracteristicas_3_1 = new JLabel("");
		lblCaracteristicas_3_1.setBounds(20, 353, 408, 63);
		panel_2_1.add(lblCaracteristicas_3_1);

		JButton btnNewButton = new JButton("Solicitud");
		btnNewButton.setBackground(new Color(255, 153, 102));
		btnNewButton.setBounds(622, 251, 179, 49);
		perfil.add(btnNewButton);

		aulas = new JPanel();
		aulas.setBounds(88, 29, 1287, 791);
		contentPane.add(aulas);
		aulas.setLayout(null);
		aulas.setVisible(false);
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox.addItem("Seleccione un aula");
		comboBox.addItem("Aula 1");
		comboBox.addItem("Aula 2");
		comboBox.setBounds(46, 44, 190, 31);
		aulas.add(comboBox);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(800, 208, 456, 448);
		aulas.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Caracteristicas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 118, 14);
		panel_2.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Software");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 110, 71, 14);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Hardware");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(10, 224, 82, 14);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Instalaciones Pendientes");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(10, 319, 188, 14);
		panel_2.add(lblNewLabel_1_1_1);

		JPanel panel_3 = new JPanel();
		panel_3.setAutoscrolls(true);
		panel_3.setBounds(47, 150, 691, 569);
		aulas.add(panel_3);

		panel_3.setLayout(null);

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
		for (int i = 0; i < listaEquipos.size(); i++) {

			int posicion1 = linea1 * 15;
			int posicion2 = linea2 * 15;

			int e = i;
			JLabel lvlNombreEquipo = new JLabel("Equipo " + listaEquipos.get(i).getNombre());
			JLabel lblEquipo = new JLabel("");
			lblEquipo.setName("equipo" + i);
			lblEquipo.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent arg0) {

					System.out.println();
					lblCaracteristicas.setText(listaEquipos.get(e).getNombre());
					lblCaracteristicas_1.setText(listaEquipos.get(e).getNombre());
					lblCaracteristicas_2.setText(listaEquipos.get(e).getNombre());
					lblCaracteristicas_3.setText(listaEquipos.get(e).getNombre());
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

			panel_3.revalidate();
			JLabel lvlSalida_1 = new JLabel("");
			lvlSalida_1.setIcon(new ImageIcon(Main.class.getResource("/images/cuenta.png")));
			lvlSalida_1.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent arg0) {

					aulas.setVisible(true);
					perfil.setVisible(false);
				}
			});
			lvlSalida_1.setBounds(10, 11, 46, 64);
			panel.add(lvlSalida_1);
			
			JLabel lvlSalida_1_1_2 = new JLabel("");
			lvlSalida_1_1_2.setIcon(new ImageIcon(Main.class.getResource("/images/colegio (2).png")));
			lvlSalida_1_1_2.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					aulas.setVisible(false);
					perfil.setVisible(true);
					
				}
			});
			lvlSalida_1.setBounds(10, 11, 46, 64);
			panel.add(lvlSalida_1);
			lvlSalida_1_1_2.setBounds(10, 204, 46, 64);
			panel.add(lvlSalida_1_1_2);
		}

	}

	public void ventana1() {

	}
}
