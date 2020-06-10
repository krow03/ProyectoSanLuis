package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DAO.ProviderDAO;
import DTO.CompraDTO;
import DTO.EquipoDTO;
import DTO.IncidenciaDTO;
import gestores.GestorCompras;
import gestores.GestorEquipos;
import gestores.GestorProveedores;
import gestores.GestorSolicitudes;
import gestores.GestorUsuarios;
import javax.swing.SwingConstants;

public class ProveedoresPantalla extends JDialog {
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProveedoresPantalla frame = new ProveedoresPantalla();
					frame.setUndecorated(true);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	private JPanel contentPane;
	private GestorCompras gc = GestorCompras.getInstance();
	private GestorProveedores gp = GestorProveedores.getInstance();
	private GestorProveedores gu = GestorProveedores.getInstance();

	public ProveedoresPantalla() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 965, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLACK));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(0x566573));

		contentPane.setLayout(null);

		System.out.println();
		JLabel lblNewLabel = new JLabel("Bienvenido:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(39, 11, 135, 26);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPaneEquipos = new JScrollPane();
		scrollPaneEquipos.setBounds(39, 85, 717, 288);
		contentPane.add(scrollPaneEquipos);
		JTable tableEquipos = new JTable();
		tableEquipos.setFillsViewportHeight(true);
		tableEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel defaultModelEquipos = (new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Fecha", "Precio" }));
		tableEquipos.setModel(defaultModelEquipos);
		tableEquipos.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableEquipos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableEquipos.getColumnModel().getColumn(2).setPreferredWidth(100);
		ArrayList<CompraDTO> comprasLista = new ArrayList<CompraDTO>();
		comprasLista = gc.getComprasProveedor(gp.getProvOnline().getIdProveedor());

		for (CompraDTO e : comprasLista) {

			Object[] fila = { e.getIdCompra(), e.getFechaCompra(), e.getPrecioTotal() };
			defaultModelEquipos.addRow(fila);
		}
		tableEquipos.setModel(defaultModelEquipos);
		scrollPaneEquipos.setViewportView(tableEquipos);

		JButton btnNewButton = new JButton("Añadir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer
						.parseInt((String) defaultModelEquipos.getValueAt(tableEquipos.getSelectedRow(), 0).toString());
				CompraDTO cdto = gc.getCompraById(id);
				cdto.setEstado(1);
				gc.modificar(cdto);

			}
		});
		btnNewButton.setBackground(new Color(0x43B581));
		btnNewButton.setBounds(39, 384, 89, 41);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Denegar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer
						.parseInt((String) defaultModelEquipos.getValueAt(tableEquipos.getSelectedRow(), 0).toString());
				CompraDTO cdto = gc.getCompraById(id);
				cdto.setEstado(2);
				gc.modificar(cdto);
			}
		});
		btnNewButton_1.setBackground(new Color(0xF44444));
		btnNewButton_1.setBounds(171, 384, 89, 41);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Visualizar compra");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer
						.parseInt((String) defaultModelEquipos.getValueAt(tableEquipos.getSelectedRow(), 0).toString());
				CompraDTO cdto = gc.getCompraById(id);

				ProveedoresPantallaCompras frame = new ProveedoresPantallaCompras(cdto.getListaProdPorCompra());
				frame.setUndecorated(true);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		btnNewButton_1_1.setBackground(new Color(153, 204, 153));
		btnNewButton_1_1.setBounds(305, 384, 257, 41);
		contentPane.add(btnNewButton_1_1);

		JButton btnNewButton_2 = new JButton("A\u00F1adir catalogo");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gp.insertarPdf();
			}
		});
		btnNewButton_2.setBounds(778, 88, 149, 50);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_2_1 = new JButton("Descargar Catalogo");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_2_1.setBounds(778, 149, 149, 50);
		contentPane.add(btnNewButton_2_1);
		JLabel lblNewLabel_1 = new JLabel(gu.getProvOnline().getNombre());
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(193, 11, 135, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lbl_close = new JLabel("X");
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_close.setBounds(925, 0, 37, 27);
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		contentPane.add(lbl_close);
	}
}
