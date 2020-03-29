package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DTO.EquipoDTO;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.border.BevelBorder;
import java.awt.Scrollbar;
import java.awt.ScrollPane;

public class Main extends JFrame {
	JComboBox comboBox;
	private JPanel contentPane;
	int xx, xy;
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*ArrayList<EquipoDTO> listaEquipos = new ArrayList<EquipoDTO>();
		listaEquipos.add(new EquipoDTO("dfdsfsdf", "192.167"));
		listaEquipos.add(new EquipoDTO("dfdsfsdf", "192.167"));
*/
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
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1417, 866);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0x1b384a));
		panel.setBounds(0, 0, 65, 878);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lvlSalida = new JLabel("");
		lvlSalida.setIcon(new ImageIcon(Main.class.getResource("/images/salida (3).png")));
		lvlSalida.setBounds(10, 789, 46, 64);
		panel.add(lvlSalida);
		
		JLabel lvlSalida_1 = new JLabel("");
		lvlSalida_1.setIcon(new ImageIcon(Main.class.getResource("/images/cuenta.png")));
		lvlSalida_1.setBounds(10, 11, 46, 64);
		panel.add(lvlSalida_1);
		
		JLabel lvlSalida_1_1 = new JLabel("");
		lvlSalida_1_1.setIcon(new ImageIcon(Main.class.getResource("/images/ordenador-portatil.png")));
		lvlSalida_1_1.setBounds(10, 112, 46, 64);
		panel.add(lvlSalida_1_1);
		
		JLabel lvlSalida_1_1_1 = new JLabel("");
		lvlSalida_1_1_1.setIcon(new ImageIcon(Main.class.getResource("/images/producto.png")));
		lvlSalida_1_1_1.setBounds(10, 413, 46, 64);
		panel.add(lvlSalida_1_1_1);
		
		JLabel lvlSalida_1_1_2 = new JLabel("");
		lvlSalida_1_1_2.setIcon(new ImageIcon(Main.class.getResource("/images/colegio (2).png")));
		lvlSalida_1_1_2.setBounds(10, 204, 46, 64);
		panel.add(lvlSalida_1_1_2);
		
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(91, 36, 1300, 791);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox.addItem("Seleccione un aula");
		comboBox.addItem("Aula 1");
		comboBox.addItem("Aula 2");
		comboBox.setBounds(59, 91, 190, 31);
		panel_1.add(comboBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(995, 208, 295, 448);
		panel_1.add(panel_2);
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
		panel_3.setBounds(46, 246, 906, 272);
		panel_1.add(panel_3);
		
		panel_3.setLayout(null);
		
		JLabel lvlNombreEquipo = new JLabel("Equipo 1");
		lvlNombreEquipo.setBounds(10, 78, 56, 14);
		panel_3.add(lvlNombreEquipo);
		lvlNombreEquipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lvlEquipo = new JLabel("");
		lvlEquipo.setBounds(10, 0, 76, 81);
		panel_3.add(lvlEquipo);
		lvlEquipo.setIcon(new ImageIcon(Main.class.getResource("/images/pc-de-la-torre (2).png")));
		
	}
}
