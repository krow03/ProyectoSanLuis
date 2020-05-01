package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DTO.AdministradorDTO;
import DTO.AulaDTO;
import DTO.ComponenteDTO;
import DTO.EquipoDTO;
import DTO.HardwareDTO;
import DTO.IncidenciaDTO;
import DTO.SoftwareDTO;
import DTO.SolicitudDTO;
import DTO.StockDTO;
import DTO.TecnicoDTO;
import DTO.UsuarioDTO;
import Gestores.GestorAulas;
import Gestores.GestorComponentes;
import Gestores.GestorEquipos;
import Gestores.GestorSolicitudes;
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
import DAO.UsuarioDAO;

import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Main extends JFrame {

	private GestorUsuarios gu = new GestorUsuarios();
	private GestorSolicitudes gs = new GestorSolicitudes();
	private GestorComponentes gc = new GestorComponentes();
	private GestorEquipos ge = new GestorEquipos();
	private GestorAulas ga = new GestorAulas();
	private ArrayList<EquipoDTO> listaEquipos = new ArrayList<EquipoDTO>();
	private static DefaultTableModel defaultModel = new DefaultTableModel();
	private static DefaultTableModel defaultModel2 = new DefaultTableModel();
	private static DefaultTableModel defaultModelAula = new DefaultTableModel();
	private static DefaultTableModel defaultModelAlumno = new DefaultTableModel();
	private String rol2;
	private String idEquipo2;
	private JComboBox comboBox;
	private Image img;
	private JTable tableAula;
	private JPanel panel_3;
	private EquipoDTO equipoSeleccionado;
	private JPanel aulas;
	private JPanel perfil;
	private JPanel crud;
	private JPanel stock;
	private JPanel crudEquipo;
	private JPanel incidencias;
	private JPanel contentPane;
	private String nombreSeleccionado;
	private int aulaSeleccionada = 0;
	int xx, xy;
	private JTable table;
	private JTable table_1;
	private JTextField txtNombreOnline;
	private JTextField txtEmailOnline;
	private JTextField txtEquipoOnline;
	private JTextField txtNombreEquipo;
	private JTextField txtIp;
	private JTextField txtDiscoDuro;
	private JTextField txtRam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setUndecorated(true);
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
	public Main() {
		gs.cargarLista();
		gc.cargarLista();
		ge.cargarListaEquipos();
		gu.cargarListaUsuarios();
		ga.cargarListaAulas();
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1417, 866);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);

		visualizarPerfil();
		visualizarCrudAulas();
		visualizarAulas();
		visualizarIncidencias();
		visualizarCrudEquipos();
		visualizarIncidencias2();
		aulas.setVisible(false);
		perfil.setVisible(true);
		incidencias.setVisible(false);
		crud.setVisible(false);
		crudEquipo.setVisible(false);
		stock.setVisible(false);
		// JPANEL LATERAL
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0x141d26));
		panel.setBounds(0, 0, 65, 878);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lvlSalida_1_1_2 = new JLabel("");
		lvlSalida_1_1_2.setIcon(new ImageIcon(Main.class.getResource("/images/colegio (2).png")));
		lvlSalida_1_1_2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				visualizarAulas();
				aulas.setVisible(true);
				cargarDesplegableAula();
				perfil.setVisible(false);
				incidencias.setVisible(false);
				crud.setVisible(false);
				crudEquipo.setVisible(false);
				stock.setVisible(false);

			}
		});
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
				crudEquipo.setVisible(false);
				stock.setVisible(false);

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
				crudEquipo.setVisible(false);
				stock.setVisible(false);

			}
		});
		lvlSalida_1_1.setBounds(10, 112, 46, 64);
		panel.add(lvlSalida_1_1);
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

		JLabel lblPedidos = new JLabel("");
		lblPedidos.setIcon(new ImageIcon(Main.class.getResource("/images/producto.png")));
		lblPedidos.setBounds(10, 400, 46, 64);
		lblPedidos.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				aulas.setVisible(false);
				perfil.setVisible(false);
				incidencias.setVisible(false);
				crud.setVisible(false);
				crudEquipo.setVisible(false);
				stock.setVisible(true);
			}
		});
		panel.add(lblPedidos);

		JLabel lblStock = new JLabel("");
		lblStock.setIcon(new ImageIcon(Main.class.getResource("/images/almacen.png")));
		lblStock.setBounds(10, 306, 46, 64);
		panel.add(lblStock);

		JLabel lblEquipos = new JLabel("");
		lblEquipos.setIcon(new ImageIcon(Main.class.getResource("/images/ordenador-portatil.png")));
		lblEquipos.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				aulas.setVisible(false);
				perfil.setVisible(false);
				incidencias.setVisible(false);
				crud.setVisible(false);
				crudEquipo.setVisible(true);
				stock.setVisible(false);

			}
		});
		lblEquipos.setBounds(10, 550, 46, 64);
		panel.add(lblEquipos);

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

		JLabel lvlSalida_1 = new JLabel("");
		lvlSalida_1.setIcon(new ImageIcon(Main.class.getResource("/images/cuenta.png")));
		lvlSalida_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				aulas.setVisible(false);
				perfil.setVisible(true);
				incidencias.setVisible(false);
				crud.setVisible(false);
				crudEquipo.setVisible(false);
				stock.setVisible(false);

			}
		});
		lvlSalida_1.setBounds(10, 11, 46, 64);
		panel.add(lvlSalida_1);

		cargarUsuarioOnline();

	}

	private boolean modificarAula(AulaDTO a) {
		return ga.modificarAula(a);
	}

	private boolean eliminarAula(int idAula) {
		return ga.borrarAula(idAula);
	}

	private boolean crearAula(AulaDTO a) {
		try {
			return ga.crearAula(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///**************************************************************PERFIL********************************************************************************///
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void visualizarPerfil() {
		perfil = new JPanel();
		perfil.setBackground(Color.WHITE);
		perfil.setBounds(64, 0, 1311, 878);
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

		JButton btnSolicitud = new JButton("Solicitud");
		btnSolicitud.setBackground(new Color(51, 204, 153));
		btnSolicitud.setForeground(Color.BLACK);
		btnSolicitud.setBounds(604, 300, 179, 49);

		JLabel lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon(Main.class.getResource("/images/usuario (1).png")));
		lblFoto.setBounds(58, 21, 265, 281);
		panel_2_1.add(lblFoto);

		JButton bntModificarPerfil = new JButton("Modificar perfil");
		bntModificarPerfil.setBounds(320, 3, 139, 32);

		bntModificarPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Cambios realizados correctamente");
				bntModificarPerfil.setVisible(false);
			}
		});
		bntModificarPerfil.setBackground(new Color(0x43B581));
		panel_2_1.add(bntModificarPerfil);
		// txtNombreOnline = new JTextField(gu.getUserOnline().getUserName());
		txtNombreOnline = new JTextField();
		txtNombreOnline.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {

				bntModificarPerfil.setVisible(true);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {

				bntModificarPerfil.setVisible(true);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

		});
		txtNombreOnline.setBounds(20, 329, 118, 22);
		txtNombreOnline.setBackground(new Color(0x566573));
		panel_2_1.add(txtNombreOnline);
		txtNombreOnline.setColumns(10);

		txtEmailOnline = new JTextField();
		txtEmailOnline.setBackground(new Color(0x566573));
		txtEmailOnline.setColumns(10);
		txtEmailOnline.setBounds(20, 403, 118, 22);
		txtEmailOnline.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				bntModificarPerfil.setVisible(true);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {

				bntModificarPerfil.setVisible(true);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}

		});
		panel_2_1.add(txtEmailOnline);

		txtEquipoOnline = new JTextField();
		txtEquipoOnline.setBackground(new Color(0x566573));
		txtEquipoOnline.setColumns(10);
		txtEquipoOnline.setBounds(20, 478, 118, 22);

		txtEquipoOnline.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				bntModificarPerfil.setVisible(true);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				bntModificarPerfil.setVisible(true);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}
			// implement the methods
		});
		panel_2_1.add(txtEquipoOnline);
		// Oculto el btn despues de los inputs
		bntModificarPerfil.setVisible(false);

		JPanel panel_4_1 = new JPanel();
		panel_4_1.setLayout(null);
		panel_4_1.setBounds(506, 196, 793, 89);
		perfil.add(panel_4_1);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(506, 300, 795, 500);
		perfil.add(scrollPane2);
		JTable table2 = new JTable();
		table2.setFillsViewportHeight(true);
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defaultModel2 = (new DefaultTableModel(new Object[][] {}, new String[] { "DNI", "Rol", "Nombre", "Apellidos",
				"Email", "Direccion", "Telefono", "User Name", "Password", "Nombre Equipo" }) {
			public boolean isCellEditable(int row, int column) {
			    if(column == 1 || column == 9) {
			        return false;
			    }else {
				    return true;

			    }
			}
		});
		table2.setModel(defaultModel2);
		table2.getColumnModel().getColumn(0).setPreferredWidth(86);
		table2.getColumnModel().getColumn(1).setPreferredWidth(106);
		table2.getColumnModel().getColumn(2).setPreferredWidth(86);
		table2.getColumnModel().getColumn(3).setPreferredWidth(192);
		table2.getColumnModel().getColumn(4).setPreferredWidth(192);
		table2.getColumnModel().getColumn(5).setPreferredWidth(192);
		table2.getColumnModel().getColumn(6).setPreferredWidth(192);
		table2.getColumnModel().getColumn(7).setPreferredWidth(192);
		table2.getColumnModel().getColumn(8).setPreferredWidth(192);
		table2.getColumnModel().getColumn(9).setPreferredWidth(192);
		ArrayList<UsuarioDTO> array = new ArrayList<UsuarioDTO>();
		array = gu.getList();
		String rol = "";
		String nombreEquipo = "";
		for (UsuarioDTO udto : array) {
			if (udto instanceof AdministradorDTO) {
				rol = "admin";
			} else if (udto instanceof TecnicoDTO) {
				rol = "tecnico";
			} else {
				rol = "usuario";
			}

			if (udto.getEquipo() != null)
				nombreEquipo = udto.getEquipo().getNombre();
			Object[] fila = { udto.getIdUsuario(), rol, udto.getNombre(), udto.getApellidos(), udto.getEmail(),
					udto.getDireccion(), udto.getTelefono(), udto.getUserName(), udto.getPass(), nombreEquipo };
			defaultModel2.addRow(fila);
		}
		Object[] filaBlanca = { "", "", "", "", "", "", "", "", "", "" };
		defaultModel2.addRow(filaBlanca);
		table2.setModel(defaultModel2);
		scrollPane2.setViewportView(table2);
		table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				nombreSeleccionado = table2.getValueAt(table2.getSelectedRow(), 0).toString();
			}
		});

		JButton btnNewButton_2 = new JButton("A\u00F1adir");
		// btnNewButton_2.setForeground(new Color(0x43B581));
		btnNewButton_2.setForeground(new Color(0x43B581));
		btnNewButton_2.setBackground(new Color(86, 101, 115));

		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		try {
			img = ImageIO.read(getClass().getResource("/images/mas (1).png"));
			btnNewButton_2.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) table2.getModel();
					String id = model.getValueAt(table2.getSelectedRow(), 0).toString();
					String nombre = model.getValueAt(table2.getSelectedRow(), 2).toString();
					String apellidos = model.getValueAt(table2.getSelectedRow(), 3).toString();
					String email = model.getValueAt(table2.getSelectedRow(), 4).toString();
					String direccion = model.getValueAt(table2.getSelectedRow(), 5).toString();
					String telefono = model.getValueAt(table2.getSelectedRow(), 6).toString();
					String userName = model.getValueAt(table2.getSelectedRow(), 7).toString();
					String pass = model.getValueAt(table2.getSelectedRow(), 8).toString();
					UsuarioDTO user = new UsuarioDTO(id, userName, email, 0, pass, nombre, apellidos, direccion,
							telefono);
					JoptionCombos frame = new JoptionCombos(user);
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(488, 0, 188, 89);
		panel_4_1.add(btnNewButton_2);

		JButton btnNewButton_2_1 = new JButton("Modificar");
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2_1.setForeground(Color.ORANGE);
		try {
			img = ImageIO.read(getClass().getResource("/images/cambio (3).png"));
			btnNewButton_2_1.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table2.getModel();
				String id = model.getValueAt(table2.getSelectedRow(), 0).toString();
				String nombre = model.getValueAt(table2.getSelectedRow(), 2).toString();
				String apellidos = model.getValueAt(table2.getSelectedRow(), 3).toString();
				String email = model.getValueAt(table2.getSelectedRow(), 4).toString();
				String direccion = model.getValueAt(table2.getSelectedRow(), 5).toString();
				String telefono = model.getValueAt(table2.getSelectedRow(), 6).toString();
				String userName = model.getValueAt(table2.getSelectedRow(), 7).toString();
				String pass = model.getValueAt(table2.getSelectedRow(), 8).toString();
				UsuarioDTO user = new UsuarioDTO(id, userName, email, pass, nombre, apellidos, direccion, telefono);
				String mensaje = "!Usuario modificado correctamenteï¿½";
				if (!modificarusuario(user))
					mensaje = "!Error al modificar el usuarioï¿½";
				JOptionPane.showMessageDialog(null, mensaje);
				gu.cargarListaUsuarios();
			}
		});
		btnNewButton_2_1.setBackground(new Color(86, 101, 115));
		btnNewButton_2_1.setBounds(301, 0, 188, 89);
		panel_4_1.add(btnNewButton_2_1);

		JButton btnNewButton_2_2 = new JButton("Eliminar");
		btnNewButton_2_2.setForeground(new Color(241, 57, 83));
		btnNewButton_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));

		try {
			img = ImageIO.read(getClass().getResource("/images/eliminar (1).png"));
			btnNewButton_2_2.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensaje = "!Usuario borrado correctamenteï¿½";
				DefaultTableModel model = (DefaultTableModel) table2.getModel();
				String id = model.getValueAt(table2.getSelectedRow(), 0).toString();
				int option = JOptionPane.showConfirmDialog(null, "ï¿½Borrar usuario?", "Eliminar Usuario",
						JOptionPane.OK_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					if (!borrarUsuario(id))
						mensaje = "!Error al borrar el usuarioï¿½";
					JOptionPane.showMessageDialog(null, mensaje);
					gu.cargarListaUsuarios();
				}

			}
		});
		btnNewButton_2_2.setBackground(new Color(86, 101, 115));
		btnNewButton_2_2.setBounds(115, 0, 188, 89);
		panel_4_1.add(btnNewButton_2_2);

		JButton btnDegradar = new JButton("Degradar");
		btnDegradar.setForeground(Color.WHITE);
		btnDegradar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table2.getModel();
				String id = model.getValueAt(table2.getSelectedRow(), 0).toString();
				String rol = model.getValueAt(table2.getSelectedRow(), 1).toString();
				String nombre = model.getValueAt(table2.getSelectedRow(), 2).toString();
				String apellidos = model.getValueAt(table2.getSelectedRow(), 3).toString();
				String email = model.getValueAt(table2.getSelectedRow(), 4).toString();
				String direccion = model.getValueAt(table2.getSelectedRow(), 5).toString();
				String telefono = model.getValueAt(table2.getSelectedRow(), 6).toString();
				String userName = model.getValueAt(table2.getSelectedRow(), 7).toString();
				String pass = model.getValueAt(table2.getSelectedRow(), 8).toString();

				String mensaje = "!Error al degradar usuario!";
				if (rol.equals("admin")) {
					AdministradorDTO admin = new AdministradorDTO(id, userName, email, 0, pass, nombre, apellidos,
							direccion, telefono);
					if (gu.degradarUsuario(admin))
						mensaje = "ï¿½Usuario degradado!";
				} else if (rol.equals("tecnico")) {
					TecnicoDTO tec = new TecnicoDTO(id, userName, email, 0, pass, nombre, apellidos, direccion,
							telefono);
					if (gu.degradarUsuario(tec))
						mensaje = "ï¿½Usuario degradado!";
				} else {
					UsuarioDTO user = new UsuarioDTO(id, userName, email, 0, pass, nombre, apellidos, direccion,
							telefono);
					if (gu.degradarUsuario(user))
						mensaje = "ï¿½Usuario degradado!";
				}
				JOptionPane.showMessageDialog(null, mensaje);
				gu.cargarListaUsuarios();
			}
		});
		btnDegradar.setBounds(0, 0, 117, 89);
		btnDegradar.setBackground(new Color(86, 101, 115));
		try {
			img = ImageIO.read(getClass().getResource("/images/flecha-arriba (2).png"));
			btnDegradar.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel_4_1.add(btnDegradar);

		JButton btnPromocionar = new JButton("Promocionar");
		btnPromocionar.setForeground(Color.WHITE);
		btnPromocionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table2.getModel();
				String id = model.getValueAt(table2.getSelectedRow(), 0).toString();
				String nombre = model.getValueAt(table2.getSelectedRow(), 2).toString();
				String apellidos = model.getValueAt(table2.getSelectedRow(), 3).toString();
				String email = model.getValueAt(table2.getSelectedRow(), 4).toString();
				String direccion = model.getValueAt(table2.getSelectedRow(), 5).toString();
				String telefono = model.getValueAt(table2.getSelectedRow(), 6).toString();
				String userName = model.getValueAt(table2.getSelectedRow(), 7).toString();
				String pass = model.getValueAt(table2.getSelectedRow(), 8).toString();
				String rol = model.getValueAt(table2.getSelectedRow(), 1).toString();
				String mensaje = "!Error al pomocionar usuario!";
				if (rol.equals("admin")) {
					AdministradorDTO admin = new AdministradorDTO(id, userName, email, 0, pass, nombre, apellidos,
							direccion, telefono);
					if (gu.promocionarUsuario(admin))
						mensaje = "ï¿½Usuario promocionado!";
				} else if (rol.equals("tecnico")) {
					TecnicoDTO tec = new TecnicoDTO(id, userName, email, 0, pass, nombre, apellidos, direccion,
							telefono);
					if (gu.promocionarUsuario(tec))
						mensaje = "ï¿½Usuario promocionado!";
				} else {
					UsuarioDTO user = new UsuarioDTO(id, userName, email, 0, pass, nombre, apellidos, direccion,
							telefono);
					if (gu.promocionarUsuario(user))
						mensaje = "ï¿½Usuario promocionado!";
				}
				JOptionPane.showMessageDialog(null, mensaje);
				gu.cargarListaUsuarios();
			}
		});
		btnPromocionar.setBounds(676, 0, 117, 89);
		btnPromocionar.setBackground(new Color(86, 101, 115));
		try {
			img = ImageIO.read(getClass().getResource("/images/flecha-arriba (4).png"));
			btnPromocionar.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel_4_1.add(btnPromocionar);

		JButton btnNewButton_2_3 = new JButton("Cambiar Contrase\u00F1a");
		btnNewButton_2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPass();
			}
		});
		try {
			img = ImageIO.read(getClass().getResource("/images/desbloquear.png"));
			btnNewButton_2_3.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnNewButton_2_3.setBackground(new Color(86, 101, 115));
		btnNewButton_2_3.setBounds(1066, 11, 233, 89);
		perfil.add(btnNewButton_2_3);

		JButton btnNewButton_2_2_1 = new JButton("Solicitud");
		btnNewButton_2_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IncidenciaUsuPanel2 upanel = new IncidenciaUsuPanel2();
				upanel.setUndecorated(true);
				upanel.setLocationRelativeTo(null);

				upanel.setVisible(true);
			}
		});
		btnNewButton_2_2_1.setBackground(new Color(86, 101, 115));
		btnNewButton_2_2_1.setBounds(506, 11, 235, 89);
		perfil.add(btnNewButton_2_2_1);

		JButton btnNewButton_2_2_2 = new JButton("Incidencia");
		btnNewButton_2_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SolicitudUsuPanel soPanel = new SolicitudUsuPanel();
				soPanel.setUndecorated(true);
				soPanel.setLocationRelativeTo(null);

				soPanel.setVisible(true);

			}
		});
		btnNewButton_2_2_2.setBackground(new Color(86, 101, 115));
		btnNewButton_2_2_2.setBounds(782, 11, 235, 89);
		perfil.add(btnNewButton_2_2_2);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///***************************************************************METODOS PERFIL***********************************************************************///
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void cargarUsuarioOnline() {
		UsuarioDTO udto = gu.getUserOnline();
		try {
			if (udto != null) {
				txtNombreOnline.setText(udto.getUserName());
				txtEmailOnline.setText(udto.getEmail());
				if (udto.getEquipo() != null)
					txtEquipoOnline.setText((udto.getEquipo().getNombre()));
				if (udto.getIncidencias() != null)
					for (IncidenciaDTO inci : udto.getIncidencias()) {
						if (inci instanceof SolicitudDTO) {

						}
					}
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

	private boolean borrarUsuario(String idUsuario) {
		return gu.borrarUsuario(idUsuario);
	}

	private boolean modificarusuario(UsuarioDTO udto) {
		try {
			return gu.modificarUsuario(udto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean actualizarPerfil() {
		UsuarioDTO u = gu.getUserOnline();
		u.setUserName(txtNombreOnline.getText());
		u.setEmail(txtEmailOnline.getText());
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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///***************************************************************AULAS********************************************************************************///
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void visualizarAulas() {
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

		JButton btnAsignarAlumno = new JButton("Asignar");
		btnAsignarAlumno.setEnabled(false);
		btnAsignarAlumno.setBackground(Color.ORANGE);
		btnAsignarAlumno.setBounds(768, 744, 229, 36);
		aulas.add(btnAsignarAlumno);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(802, 384, 392, 340);
		aulas.add(scrollPane);
		JTable tableAlumnos = new JTable();
		tableAlumnos.setFillsViewportHeight(true);
		tableAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defaultModelAlumno = (new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre" }));
		tableAlumnos.setModel(defaultModelAlumno);

		tableAlumnos.getColumnModel().getColumn(0).setPreferredWidth(86);
		tableAlumnos.getColumnModel().getColumn(1).setPreferredWidth(86);
		ArrayList<UsuarioDTO> array = new ArrayList<UsuarioDTO>();
		array = gu.getList();

		for (UsuarioDTO e : array) {

			Object[] fila = { e.getIdUsuario(), e.getNombre() };
			defaultModelAlumno.addRow(fila);
		}
		Object[] fila = { "", "" };
		defaultModelAlumno.addRow(fila);

		scrollPane.setViewportView(tableAlumnos);
		JButton btnEliminarEquipo = new JButton("Desasignar");
		btnEliminarEquipo.setEnabled(false);
		btnEliminarEquipo.setBackground(new Color(220, 20, 60));
		btnEliminarEquipo.setBounds(997, 744, 229, 36);
		aulas.add(btnEliminarEquipo);

		JButton btnAnadirEquipo = new JButton("A\u00F1adir Equipo");
		btnAnadirEquipo.setBackground(Color.ORANGE);
		btnAnadirEquipo.setBounds(261, 45, 229, 31);
		aulas.add(btnAnadirEquipo);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_3.removeAll();
				panel_3.revalidate();
				panel_3.repaint();
				if (!comboBox.getSelectedItem().toString().equals("Seleccione un aula")) {
					cargarEquiposAula();
					aulaSeleccionada = ga.getAulaByNombre(comboBox.getSelectedItem().toString()).getIdAula();
				}
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox.addItem("Seleccione un aula");
		comboBox.setBounds(46, 45, 190, 31);
		aulas.add(comboBox);

		btnAsignarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) tableAlumnos.getModel();
				String id = model.getValueAt(tableAlumnos.getSelectedRow(), 0).toString();
				String mensaje = "Equipo asignado al alumno";
				if (!gu.asignarEquipo(id,equipoSeleccionado.getIdEquipo())) 
					mensaje = "Error al asignar el equipo";
				JOptionPane.showMessageDialog(null, mensaje);
			}
		});

		btnEliminarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnAnadirEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (aulaSeleccionada != 0) {
					try {
						AñdirEquipoPanel frame = new AñdirEquipoPanel(aulaSeleccionada);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		JButton btnDeleteEquipo = new JButton("Quitar Equipo");
		btnDeleteEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (equipoSeleccionado != null) {
					try {
						String mensaje = "Equipo eliminado del aula";
						if (!ge.desasignarAula(equipoSeleccionado.getIdEquipo())) 
							mensaje = "Error al eliminar el equipo";
						JOptionPane.showMessageDialog(null, mensaje);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0x566573));
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(768, 44, 458, 701);
		aulas.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setForeground(Color.WHITE);

		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 118, 14);
		panel_2.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Ip equipo");
		lblNewLabel_1.setForeground(Color.WHITE);

		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 94, 71, 14);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Disco duro");
		lblNewLabel_1_1.setForeground(Color.WHITE);

		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(12, 257, 82, 14);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Alumnos asignados");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);

		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(10, 319, 188, 14);
		panel_2.add(lblNewLabel_1_1_1);

		JLabel lblCaracteristicas_3 = new JLabel("");
		lblCaracteristicas_3.setBounds(20, 353, 408, 63);
		panel_2.add(lblCaracteristicas_3);

		txtNombreEquipo = new JTextField();
		txtNombreEquipo.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtNombreEquipo.setColumns(10);
		txtNombreEquipo.setForeground(Color.WHITE);
		txtNombreEquipo.setBackground(new Color(0x566573));

		txtNombreEquipo.setBounds(10, 38, 434, 22);
		panel_2.add(txtNombreEquipo);

		txtIp = new JTextField();
		txtIp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtIp.setColumns(10);
		txtIp.setForeground(Color.WHITE);
		txtIp.setBackground(new Color(0x566573));
		txtIp.setBounds(10, 121, 434, 22);
		panel_2.add(txtIp);

		txtDiscoDuro = new JTextField();
		txtDiscoDuro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDiscoDuro.setColumns(10);
		txtDiscoDuro.setForeground(Color.WHITE);
		txtDiscoDuro.setBackground(new Color(0x566573));
		txtDiscoDuro.setBounds(10, 284, 434, 22);
		panel_2.add(txtDiscoDuro);

		JLabel lblNewLabel_1_2 = new JLabel("Ram");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(10, 174, 71, 14);
		panel_2.add(lblNewLabel_1_2);

		txtRam = new JTextField();
		txtRam.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtRam.setColumns(10);
		txtRam.setForeground(Color.WHITE);
		txtRam.setBackground(new Color(0x566573));
		txtRam.setBounds(10, 201, 434, 22);
		panel_2.add(txtRam);

		btnDeleteEquipo.setBackground(Color.ORANGE);
		btnDeleteEquipo.setBounds(502, 45, 229, 31);
		aulas.add(btnDeleteEquipo);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///***************************************************************METODOS AULAS************************************************************************///
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void cargarDesplegableAula() {
		ArrayList<AulaDTO> listaAulas = ga.getListaAulas();

		for (AulaDTO a : listaAulas) {
			comboBox.addItem(a.getNombre());
		}
	}

	private void cargarEquiposAula() {
		int linea1 = 0;
		int linea2 = 0;
		int linea3 = 0;
		int linea4 = 0;
		int linea5 = 0;
		listaEquipos.clear();
		try {
			AulaDTO adto = ga.getAulaByNombre(comboBox.getSelectedItem().toString());
			listaEquipos = adto.getEquipos();

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
						cargarDatosEquipo(listaEquipos.get(e));
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
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void cargarDatosEquipo(EquipoDTO e) {
		txtNombreEquipo.setText(e.getNombre());
		txtIp.setText(e.getIpEquipo());
		equipoSeleccionado = e;
		txtDiscoDuro.setText(((Integer) e.getDiscoDuro()).toString());
		txtRam.setText(((Integer) e.getRam()).toString());
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///**************************************************************CRUD AULAS****************************************************************************///
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void visualizarCrudAulas() {
		crud = new JPanel();
		crud.setBounds(101, 37, 1278, 767);
		crud.setBackground(Color.WHITE);

		contentPane.add(crud);
		crud.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 244, 1155, 480);
		crud.add(scrollPane);
		tableAula = new JTable();
		tableAula.setFillsViewportHeight(true);
		tableAula.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defaultModelAula = (new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nombre", "Rango Ip", "Capacidad", "Descripcion" }));
		tableAula.setModel(defaultModelAula);

		tableAula.getColumnModel().getColumn(0).setPreferredWidth(86);
		tableAula.getColumnModel().getColumn(1).setPreferredWidth(86);
		tableAula.getColumnModel().getColumn(2).setPreferredWidth(106);
		tableAula.getColumnModel().getColumn(3).setPreferredWidth(86);
		tableAula.getColumnModel().getColumn(4).setPreferredWidth(192);
		ArrayList<AulaDTO> array = new ArrayList<AulaDTO>();
		array = ga.getListaAulas();

		for (AulaDTO e : array) {

			Object[] fila = { e.getIdAula(), e.getNombre(), e.getRangoIps(), e.getCapacidad(), e.getDescripcion() };
			defaultModelAula.addRow(fila);

		}
		Object[] fila = { "", "", "", "", "" };
		defaultModelAula.addRow(fila);
		// table.setModel(defaultModel);
		scrollPane.setViewportView(tableAula);

		JButton btnNewButton = new JButton("Aï¿½adir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableAula.getModel();
				// int ids = Integer.parseInt(model.getValueAt(tableAula.getSelectedRow(),
				// 0).toString());
				String ips = model.getValueAt(tableAula.getSelectedRow(), 2).toString();
				String nombre = model.getValueAt(tableAula.getSelectedRow(), 1).toString();

				int capacidad = Integer.parseInt(model.getValueAt(tableAula.getSelectedRow(), 3).toString());
				String descripcion = model.getValueAt(tableAula.getSelectedRow(), 4).toString();

				AulaDTO adto = new AulaDTO(ips, nombre, descripcion, capacidad);
				String mensaje = "!Aula creada correctamenteï¿½";
				if (!crearAula(adto))
					mensaje = "!Error al crear el aulaï¿½ï¿½";
				JOptionPane.showMessageDialog(null, mensaje);
				recargarAula();

			}
		});
		btnNewButton.setBounds(190, 122, 235, 89);
		btnNewButton.setForeground(new Color(0x43B581));
		btnNewButton.setBackground(new Color(86, 101, 115));

		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		try {
			img = ImageIO.read(getClass().getResource("/images/mas (1).png"));
			btnNewButton.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		crud.add(btnNewButton);
		tableAula.setVisible(true);
		// Object[] filaBlanca = { "", "", "", "" };
		// defaultModel.addRow(filaBlanca);

		JButton btnNewButton_3 = new JButton("Eliminar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableAula.getModel();
				// String nombre = model.getValueAt(tableAula.getSelectedRow(), 0);
				System.out.println("asdasdas" + tableAula.getSelectedRow());
				System.out.println("asdasdas" + tableAula.getSelectedRow());

				int option = JOptionPane.showConfirmDialog(null, "ï¿½Borrar Aula?", "Eliminar Aula",
						JOptionPane.OK_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String mensaje = "!Error al borrar el aulaï¿½";
					if (eliminarAula(
							(int) Integer.parseInt(model.getValueAt(tableAula.getSelectedRow(), 0).toString()))) {
						mensaje = "!Aula borrada correctamenteï¿½ï¿½";
						model.removeRow(tableAula.getSelectedRow());
						nombreSeleccionado = "";
					}
					JOptionPane.showMessageDialog(null, mensaje);

				}
			}
		});
		btnNewButton_3.setBounds(550, 124, 235, 89);
		btnNewButton_3.setBackground(new Color(86, 101, 115));

		crud.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Modificar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableAula.getModel();
				int ids = Integer.parseInt(model.getValueAt(tableAula.getSelectedRow(), 0).toString());

				String nombre = model.getValueAt(tableAula.getSelectedRow(), 1).toString();
				String ips = model.getValueAt(tableAula.getSelectedRow(), 2).toString();
				int capacidad = Integer.parseInt(model.getValueAt(tableAula.getSelectedRow(), 3).toString());
				String descripcion = model.getValueAt(tableAula.getSelectedRow(), 4).toString();

				AulaDTO adto = new AulaDTO(ids, ips, nombre, descripcion, capacidad);
				int option = JOptionPane.showConfirmDialog(null, "ï¿½Modificar aula?", "Modificar Aula",
						JOptionPane.OK_OPTION);
				if (option == JOptionPane.OK_OPTION) {

					String mensaje = "!Aula modificada correctamenteï¿½";
					if (!modificarAula(adto))
						mensaje = "!Error al modificada el aulaï¿½";
					JOptionPane.showMessageDialog(null, mensaje);

				}
			}
		});
		btnNewButton_4.setBounds(924, 124, 235, 89);
		btnNewButton_4.setBackground(new Color(86, 101, 115));
		crud.add(btnNewButton_4);

		JLabel lblNewLabel_7 = new JLabel("CRUD AULAS");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel_7.setBounds(10, 22, 197, 43);
		crud.add(lblNewLabel_7);

		// crud.add(table);
	}

	private void recargarAula() {
		ga.cargarListaAulas();

		int rowCount = defaultModelAula.getRowCount();
		System.out.println("cuanto trae la lista " + rowCount);
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			defaultModelAula.removeRow(i);
		}
		ArrayList<AulaDTO> array = new ArrayList<AulaDTO>();
		array = ga.getListaAulas();

		for (AulaDTO e : array) {

			Object[] fila = { e.getIdAula(), e.getNombre(), e.getRangoIps(), e.getCapacidad(), e.getDescripcion() };
			defaultModelAula.addRow(fila);

		}
		Object[] fila = { "", "", "", "", "" };
		defaultModelAula.addRow(fila);

	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///**************************************************************CRUD EQUIPOS**************************************************************************///
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void visualizarCrudEquipos() {
		crudEquipo = new JPanel();
		crudEquipo.setBounds(101, 37, 1278, 767);
		crudEquipo.setBackground(Color.WHITE);

		contentPane.add(crudEquipo);
		crudEquipo.setLayout(null);

		JScrollPane scrollPaneEquipo = new JScrollPane();
		scrollPaneEquipo.setBounds(76, 244, 1155, 427);
		crudEquipo.add(scrollPaneEquipo);
		JTable tableEquipo = new JTable();
		tableEquipo.setFillsViewportHeight(true);
		tableEquipo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defaultModel = (new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Nombre", "IP", "Disco Duro", "Ram" }));
		tableEquipo.setModel(defaultModel);
		tableEquipo.getColumnModel().getColumn(0).setPreferredWidth(86);
		tableEquipo.getColumnModel().getColumn(1).setPreferredWidth(106);
		tableEquipo.getColumnModel().getColumn(2).setPreferredWidth(86);
		tableEquipo.getColumnModel().getColumn(3).setPreferredWidth(192);
		tableEquipo.getColumnModel().getColumn(4).setPreferredWidth(192);
		recargar();
		tableEquipo.setModel(defaultModel);
		scrollPaneEquipo.setViewportView(tableEquipo);

		JButton btnNewButton = new JButton("Aï¿½adir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableEquipo.getModel();
				int id = Integer.parseInt(model.getValueAt(tableEquipo.getSelectedRow(), 0).toString());
				String nombre = model.getValueAt(tableEquipo.getSelectedRow(), 1).toString();
				String ip = model.getValueAt(tableEquipo.getSelectedRow(), 2).toString();
				int discoDuro = Integer.parseInt(model.getValueAt(tableEquipo.getSelectedRow(), 3).toString());
				int ram = Integer.parseInt(model.getValueAt(tableEquipo.getSelectedRow(), 4).toString());

				EquipoDTO edto = new EquipoDTO(ip, nombre, discoDuro, ram);
				String mensaje = "!Equipo creado correctamenteï¿½";
				if (!crearEquipo(edto))
					mensaje = "!Error al crear el equipoï¿½";
				JOptionPane.showMessageDialog(null, mensaje);
				ge.cargarListaEquipos();
				recargar();

			}
		});
		btnNewButton.setBounds(186, 100, 235, 89);
		btnNewButton.setForeground(new Color(241, 57, 83));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));

		try {
			img = ImageIO.read(getClass().getResource("/images/eliminar (1).png"));
			btnNewButton.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnNewButton.setBackground(new Color(86, 101, 115));
		crudEquipo.add(btnNewButton);
		tableEquipo.setVisible(true);

		JButton btnNewButton_3 = new JButton("Eliminar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableEquipo.getModel();
				int id = Integer.parseInt(model.getValueAt(tableEquipo.getSelectedRow(), 0).toString());

				int option = JOptionPane.showConfirmDialog(null, "ï¿½Bor235r 89uipo?", "Eliminar Equipo",
						JOptionPane.OK_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String mensaje = "!Equipo borrado correctamenteï¿½";
					if (!borrarEquipo(id))
						mensaje = "!Error al borrar el equipoï¿½";
					JOptionPane.showMessageDialog(null, mensaje);
					ge.cargarListaEquipos();

					recargar();

				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_3.setForeground(new Color(241, 57, 83));
		try {
			img = ImageIO.read(getClass().getResource("/images/cambio (3).png"));
			btnNewButton_3.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		btnNewButton_3.setBackground(new Color(86, 101, 115));

		btnNewButton_3.setBounds(897, 100, 235, 89);
		crudEquipo.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Modificar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableEquipo.getModel();

				int id = Integer.parseInt(model.getValueAt(tableEquipo.getSelectedRow(), 0).toString());
				String nombre = model.getValueAt(tableEquipo.getSelectedRow(), 1).toString();
				String ip = model.getValueAt(tableEquipo.getSelectedRow(), 2).toString();
				int discoDuro = Integer.parseInt(model.getValueAt(tableEquipo.getSelectedRow(), 3).toString());
				int ram = Integer.parseInt(model.getValueAt(tableEquipo.getSelectedRow(), 4).toString());

				EquipoDTO edto = new EquipoDTO(id, ip, nombre, discoDuro, ram);

				int option = JOptionPane.showConfirmDialog(null, "ï¿½Modificar aula?", "Modificar Aula",
						JOptionPane.OK_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String mensaje = "!Equipo modificado correctamenteï¿½";
					if (!modificarEquipo(edto))
						mensaje = "!Error al modificar el equipoï¿½";
					JOptionPane.showMessageDialog(null, mensaje);
					ge.cargarListaEquipos();
					recargar();
				}

			}
		});
		btnNewButton_4.setBounds(542, 100, 235, 89);
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_4.setForeground(Color.ORANGE);
		try {
			img = ImageIO.read(getClass().getResource("/images/cambio (3).png"));
			btnNewButton_4.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		btnNewButton_4.setBackground(new Color(86, 101, 115));
		crudEquipo.add(btnNewButton_4);
	}

	private boolean crearEquipo(EquipoDTO edto) {
		try {
			return ge.crearEquipo(edto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean borrarEquipo(int idEquipo) {
		return ge.borrarEquipo(idEquipo);
	}

	private boolean modificarEquipo(EquipoDTO edto) {
		try {
			return ge.modificarEquipo(edto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private void recargar() {
		int rowCount = defaultModel.getRowCount();

		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			defaultModel.removeRow(i);
		}
		ArrayList<EquipoDTO> array = new ArrayList<EquipoDTO>();
		array = ge.getListaEquipos();

		for (EquipoDTO e : array) {

			Object[] fila = { e.getIdEquipo(), e.getNombre(), e.getIpEquipo(), e.getDiscoDuro(), e.getRam() };
			defaultModel.addRow(fila);

		}
		Object[] fila = { "", "", "", "", "" };
		defaultModel.addRow(fila);

	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///**********************************************************INCIDENCIAS Y SOLICITUDES*****************************************************************///
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void visualizarIncidencias() {
		incidencias = new JPanel();
		incidencias.setBackground(Color.WHITE);
		incidencias.setBounds(88, 37, 1287, 780);
		contentPane.add(incidencias);
		incidencias.setLayout(null);
		JScrollPane scrollPaneIncidencias = new JScrollPane();
		scrollPaneIncidencias.setBounds(650, 30, 611, 667);
		incidencias.add(scrollPaneIncidencias);
		JTable tableIncidencias = new JTable();
		tableIncidencias.setFillsViewportHeight(true);
		tableIncidencias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel defaultModelinc = (new DefaultTableModel(new Object[][] {},
				new String[] { "COD", "FECHA", "Descripcion" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tableIncidencias.setModel(defaultModelinc);
		tableIncidencias.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableIncidencias.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableIncidencias.getColumnModel().getColumn(2).setPreferredWidth(450);

		ArrayList<IncidenciaDTO> array = new ArrayList<IncidenciaDTO>();
		array = gs.getListaAsignadasA(gu.getUserOnline().getIdUsuario());

		for (IncidenciaDTO e : array) {

			Object[] fila = { e.getCodigo(), e.getFechaSol(), e.getDescripcion() };
			defaultModelinc.addRow(fila);
		}
		tableIncidencias.setModel(defaultModelinc);
		scrollPaneIncidencias.setViewportView(tableIncidencias);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(20, 30, 611, 667);
		incidencias.add(scrollPane2);
		JTable tableSolicitudes = new JTable();
		tableSolicitudes.setFillsViewportHeight(true);
		tableSolicitudes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel defaultModelsol = (new DefaultTableModel(new Object[][] {

		}, new String[] { "COD", "FECHA", "Descripcion" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		}

		);
		tableSolicitudes.setModel(defaultModelsol);
		tableSolicitudes.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableSolicitudes.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableSolicitudes.getColumnModel().getColumn(2).setPreferredWidth(450);
		ArrayList<IncidenciaDTO> array2 = new ArrayList<IncidenciaDTO>();
		array2 = gs.getListaSol();

		for (IncidenciaDTO e : array2) {

			Object[] fila = { e.getCodigo(), e.getFechaSol(), e.getDescripcion() };
			defaultModelsol.addRow(fila);
		}
		tableSolicitudes.setModel(defaultModelsol);
		scrollPane2.setViewportView(tableSolicitudes);

		JLabel lblNewLabel_14 = new JLabel("Solicitudes");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_14.setForeground(Color.BLACK);
		lblNewLabel_14.setBounds(264, 11, 122, 14);
		incidencias.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("Incidencias");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_15.setBounds(903, 11, 89, 14);
		incidencias.add(lblNewLabel_15);
		tableIncidencias.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				// nombreSeleccionado =
				// tableIncidencias.getValueAt(tableIncidencias.getSelectedRow(), 0).toString();
				tableSolicitudes.getSelectionModel().removeSelectionInterval(0, 0);

			}
		});
		tableSolicitudes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				// nombreSeleccionado =
				// tableIncidencias.getValueAt(tableIncidencias.getSelectedRow(), 0).toString();
				tableIncidencias.getSelectionModel().removeSelectionInterval(0, 0);

			}
		});
		JButton btnNewButton_1 = new JButton("Visualizar");
		btnNewButton_1.setBounds(573, 711, 128, 45);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableSolicitudes.getSelectedRow() != -1) {
					SolicitudDTO sdto = (SolicitudDTO) gs.getIncidencia(Integer
							.parseInt(tableSolicitudes.getValueAt(tableSolicitudes.getSelectedRow(), 0).toString()));

					IncidenciaTecPanel3 intec = new IncidenciaTecPanel3(sdto);

					intec.setUndecorated(true);
					intec.setLocationRelativeTo(null);
					intec.setVisible(true);
					tableIncidencias.getSelectionModel().clearSelection();

				}
				if (tableIncidencias.getSelectedRow() != -1) {
					SolicitudTecPanel2 sotec = new SolicitudTecPanel2(
							Integer.parseInt(
									tableIncidencias.getValueAt(tableIncidencias.getSelectedRow(), 0).toString()),
							tableIncidencias.getValueAt(tableIncidencias.getSelectedRow(), 2).toString());
					sotec.setUndecorated(true);
					sotec.setLocationRelativeTo(null);
					sotec.setVisible(true);
				}
			}
		});
		incidencias.add(btnNewButton_1);

	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///**************************************************************************STOCK*********************************************************************///
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void visualizarIncidencias2() {
		ArrayList<StockDTO> listaCompra = new ArrayList<StockDTO>();
		
		stock = new JPanel();
		stock.setBounds(88, 37, 1157, 744);
		contentPane.add(stock);

		stock.setBackground(Color.WHITE);
		stock.setBounds(88, 37, 1287, 780);
		contentPane.add(incidencias);
		stock.setLayout(null);

		JScrollPane scrollPaneIncidencias = new JScrollPane();
		scrollPaneIncidencias.setBounds(54, 202, 566, 495);
		stock.add(scrollPaneIncidencias);
		JTable tableHardware = new JTable();
		tableHardware.setFillsViewportHeight(true);
		tableHardware.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		defaultModel2 = (new DefaultTableModel(new Object[][] {},
				new String[] { "COD", "Descripcion", "Tipo", "Marca", "Peso" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tableHardware.setModel(defaultModel2);
		tableHardware.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableHardware.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableHardware.getColumnModel().getColumn(2).setPreferredWidth(450);
		tableHardware.getColumnModel().getColumn(3).setPreferredWidth(450);
		tableHardware.getColumnModel().getColumn(4).setPreferredWidth(450);
		ArrayList<ComponenteDTO> arrayHardware = new ArrayList<ComponenteDTO>();
		arrayHardware = gc.getListaHardware();

		for (ComponenteDTO e : arrayHardware) {

			Object[] fila = { e.getIdComponente(), e.getDescripcion(), ((HardwareDTO) e).getTipo(),
					((HardwareDTO) e).getMarca() };
			defaultModel2.addRow(fila);
		}
		tableHardware.setModel(defaultModel2);
		scrollPaneIncidencias.setViewportView(tableHardware);

		scrollPaneIncidencias.setVisible(false);
		JScrollPane scrollPaneSoftware = new JScrollPane();
		scrollPaneSoftware.setBounds(54, 202, 566, 495);
		stock.add(scrollPaneSoftware);
		JTable tableSoftware = new JTable();
		tableSoftware.setFillsViewportHeight(true);
		tableSoftware.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defaultModel2 = (new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Descripcion", "Cod licencia" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tableSoftware.setModel(defaultModel2);
		tableSoftware.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableSoftware.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableSoftware.getColumnModel().getColumn(2).setPreferredWidth(450);

		ArrayList<ComponenteDTO> arraysoftware = new ArrayList<ComponenteDTO>();
		arraysoftware = gc.getListaSoftware();
		for (int i = 0; i < arraysoftware.size(); i++) {

		}
		// String rol = "";
		// Double total;
		for (ComponenteDTO e : arraysoftware) {
			Object[] fila = { e.getIdComponente(), e.getDescripcion(), ((SoftwareDTO) e).getCodLiciencia() };
			defaultModel2.addRow(fila);
		}
		tableSoftware.setModel(defaultModel2);
		scrollPaneSoftware.setViewportView(tableSoftware);

		JScrollPane scrollPaneCompras = new JScrollPane();
		scrollPaneCompras.setBounds(743, 202, 534, 495);
		stock.add(scrollPaneCompras);
		JTable tableCompras = new JTable();
		tableCompras.setFillsViewportHeight(true);
		tableCompras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel defaultModelCompras = (new DefaultTableModel(new Object[][] {},
				new String[] { "COD", "Precio", "Cantidad", "Descripcion" }) {
			public boolean isCellEditable(int row, int column) {
			    if(column == 0 ||column == 1||column == 3) {
			        return false;
			    }else {
				    return true;

			    }
			}
		});
		tableCompras.setModel(defaultModelCompras);
		tableCompras.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableCompras.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableCompras.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableCompras.getColumnModel().getColumn(3).setPreferredWidth(450);
		ArrayList<ComponenteDTO> arrayCompras = new ArrayList<ComponenteDTO>();
		arrayCompras = gc.getListaHardware();

		tableCompras.setModel(defaultModelCompras);
		scrollPaneCompras.setViewportView(tableCompras);

		JButton btnNewButton_8 = new JButton("Comprar");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_8.setBounds(635, 714, 89, 23);
		stock.add(btnNewButton_8);
		JButton btnNewButton_5 = new JButton("Hardware");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPaneIncidencias.setVisible(false);
				scrollPaneSoftware.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(54, 155, 288, 48);
		stock.add(btnNewButton_5);

		JButton btnNewButton_9 = new JButton("icono");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tm = (DefaultTableModel) tableCompras.getModel();
				if(tableSoftware.getSelectedRow()!=-1) {
					DefaultTableModel modelSoftware = (DefaultTableModel) tableSoftware.getModel();
					System.out.println("hola"+tableSoftware.getSelectedRow());
					String id = modelSoftware.getValueAt(tableSoftware.getSelectedRow(), 0).toString();
					String descri = modelSoftware.getValueAt(tableSoftware.getSelectedRow(), 2).toString();


					Object[] fila = { id, "", 1, descri };
					tm.addRow(fila);

				}
				if(tableHardware.getSelectedRow()!=-1) {
					DefaultTableModel modelHardware = (DefaultTableModel) tableSoftware.getModel();
					System.out.println("hola"+tableHardware.getSelectedRow());
					String id = modelHardware.getValueAt(tableHardware.getSelectedRow(), 0).toString();
					String descri = modelHardware.getValueAt(tableHardware.getSelectedRow(), 1).toString();


					Object[] fila = { id, "", 1, descri };
					tm.addRow(fila);

				}
			}
		});
		btnNewButton_9.setBounds(635, 400, 89, 23);
		stock.add(btnNewButton_9);

		JButton btnNewButton_6 = new JButton("Software");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPaneIncidencias.setVisible(true);
				scrollPaneSoftware.setVisible(false);

			}
		});
		btnNewButton_6.setBounds(338, 155, 282, 48);
		stock.add(btnNewButton_6);
		tableHardware.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				// nombreSeleccionado =
				// tableIncidencias.getValueAt(tableIncidencias.getSelectedRow(), 0).toString();
				tableSoftware.getSelectionModel().removeSelectionInterval(0, 0);

			}
		});
		tableSoftware.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				// nombreSeleccionado =
				// tableIncidencias.getValueAt(tableIncidencias.getSelectedRow(), 0).toString();
				tableHardware.getSelectionModel().removeSelectionInterval(0, 0);

			}
		});
	}
}
