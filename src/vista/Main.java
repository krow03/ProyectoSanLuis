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
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
import DTO.CompraDTO;
import DTO.EquipoDTO;
import DTO.HardwareDTO;
import DTO.IncidenciaDTO;
import DTO.LineaCompraDTO;
import DTO.ProveedorDTO;
import DTO.SoftwareDTO;
import DTO.SolicitudDTO;
import DTO.StockDTO;
import DTO.TecnicoDTO;
import DTO.UsuarioDTO;
import gestores.GestorAulas;
import gestores.GestorComponentes;
import gestores.GestorCompras;
import gestores.GestorEquipos;
import gestores.GestorProveedores;
import gestores.GestorSolicitudes;
import gestores.GestorStock;
import gestores.GestorUsuarios;

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
	private GestorSolicitudes gs = GestorSolicitudes.getInstance();
	private GestorComponentes gc = GestorComponentes.getInstance();
	private GestorEquipos ge = GestorEquipos.getInstance();
	private GestorUsuarios gu = GestorUsuarios.getInstance();
	private GestorAulas ga = GestorAulas.getInstance();
	private GestorCompras gcm = GestorCompras.getInstance();
	private GestorProveedores gp = GestorProveedores.getInstance();
	private GestorStock gst = GestorStock.getInstance();
	private ArrayList<HardwareDTO> hardwareLista = new ArrayList<HardwareDTO>();
	private ArrayList<SoftwareDTO> softwareLista = new ArrayList<SoftwareDTO>();
	private ArrayList<EquipoDTO> equipoLista = new ArrayList<EquipoDTO>();
	private ArrayList<EquipoDTO> listaEquipos = new ArrayList<EquipoDTO>();
	private static DefaultTableModel defaultModel = new DefaultTableModel();
	private static DefaultTableModel defaultModel2 = new DefaultTableModel();
	private static DefaultTableModel defaultModelAula = new DefaultTableModel();
	private static DefaultTableModel defaultModelAlumno = new DefaultTableModel();
	private static DefaultTableModel defaultModelAlumnoSoftware = new DefaultTableModel();
	private static DefaultTableModel defaultModelSoftware = new DefaultTableModel();
	private static DefaultTableModel defaultModelUsuarios = new DefaultTableModel();
	private String rol2;
	private JTable table2;
	
	private String idEquipo2;
	private JComboBox comboBox;
	private JComboBox comboBoxStock;
	private Image img;
	private JTable tableAula;
	private JPanel panel_3;
	private EquipoDTO equipoSeleccionado;
	private ProveedorDTO proveedorSeleccionado;
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
	AulaDTO adto;
	private JTable table;
	private JTable table_1;
	private JTextField txtNombreOnline;
	private JTextField txtId;
	private JTextField txtTelefono;
	private JTextField txtApellidos;
	private JTextField txtDireccion;
	private JTextField txtEmailOnline;
	private JTextField txtEquipoOnline;
	private JTextField txtNombreEquipo;
	private JTextField txtIp;
	private JTextField txtDiscoDuro;
	private JTextField txtRam;
	private JButton btnAsignarAlumno;
	private JButton btnDesasignarAlumno;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
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
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1417, 866);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel movimiento = new JLabel("");
		movimiento.setBounds(526, 0, 849, 27);
		

		movimiento.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xx = e.getX();
				xy = e.getY();
			}
		});
		movimiento.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				Main.this.setLocation(x - xx, y - xy);
			}
		});
		movimiento.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(movimiento);
		
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
		JLabel lblAulaVerde = new JLabel("");
		lblAulaVerde.setIcon(new ImageIcon(Main.class.getResource("/images/aulasVerde32.png")));

		lblAulaVerde.setBounds(10, 80, 46, 64);
		panel.add(lblAulaVerde);
		lblAulaVerde.setVisible(false);
		JLabel lblAula = new JLabel("");
		lblAula.setIcon(new ImageIcon(Main.class.getResource("/images/colegio (2).png")));
		
		lblAula.setBounds(10, 80, 46, 64);
		panel.add(lblAula);
		JLabel lblCrudAulaVerde = new JLabel("");
		lblCrudAulaVerde.setIcon(new ImageIcon(Main.class.getResource("/images/edificioVerde.png")));
		
		lblCrudAulaVerde.setBounds(10, 300, 46, 39);
		panel.add(lblCrudAulaVerde);
		lblAulaVerde.setVisible(false);
		JLabel lblCrudAulas = new JLabel("");
		lblCrudAulas.setIcon(new ImageIcon(Main.class.getResource("/images/edificio.png")));
		
		lblCrudAulas.setBounds(10, 300, 46, 39);
		panel.add(lblCrudAulas);
		JLabel lblIncidenciasVerde = new JLabel("");
		lblIncidenciasVerde.setIcon(new ImageIcon(Main.class.getResource("/images/incidenciaVerde.png")));

		lblIncidenciasVerde.setBounds(10, 150, 46, 64);
		panel.add(lblIncidenciasVerde);
		lblIncidenciasVerde.setVisible(false);
		JLabel lblIncidencias = new JLabel("");
		lblIncidencias.setIcon(new ImageIcon(Main.class.getResource("/images/incidenciaBlanca.png")));

		JLabel lblTextPerfil = new JLabel("Perfil");
		lblTextPerfil.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTextPerfil.setForeground(Color.WHITE);
		lblTextPerfil.setBounds(10, 61, 46, 14);
		panel.add(lblTextPerfil);
		
		JLabel lblTextAulas = new JLabel("Aulas");
		lblTextAulas.setForeground(Color.WHITE);
		lblTextAulas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTextAulas.setBounds(10, 130, 46, 14);
		panel.add(lblTextAulas);
		
		JLabel lblTextIncidencias = new JLabel("Incidencias");
		lblTextIncidencias.setForeground(Color.WHITE);
		lblTextIncidencias.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTextIncidencias.setBounds(0, 201, 70, 14);
		panel.add(lblTextIncidencias);
		
		JLabel lblTextGaulas = new JLabel("G.Aulas");
		lblTextGaulas.setForeground(Color.WHITE);
		lblTextGaulas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTextGaulas.setBounds(10, 335, 46, 14);
		panel.add(lblTextGaulas);
		
		JLabel lblTextEquipos = new JLabel("G.Equipos");
		lblTextEquipos.setForeground(Color.WHITE);
		lblTextEquipos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTextEquipos.setBounds(0, 410, 56, 14);
		panel.add(lblTextEquipos);
		
		JLabel lblTextPedidos = new JLabel("Pedidos");
		lblTextPedidos.setForeground(Color.WHITE);
		lblTextPedidos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTextPedidos.setBounds(10, 275, 46, 14);
		panel.add(lblTextPedidos);
		
		lblIncidencias.setBounds(10, 150, 46, 64);
		panel.add(lblIncidencias);
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
		JLabel lblPedidosVerde = new JLabel("");
		lblPedidosVerde.setIcon(new ImageIcon(Main.class.getResource("/images/pedidosVerde.png")));
		lblPedidosVerde.setBounds(10, 220, 46, 64);

		panel.add(lblPedidosVerde);
		lblPedidosVerde.setVisible(true);
		JLabel lblPedidos = new JLabel("");
		lblPedidos.setIcon(new ImageIcon(Main.class.getResource("/images/producto.png")));
		lblPedidos.setBounds(10, 220, 46, 64);
		
		panel.add(lblPedidos);


		JLabel lblEquiposVerde = new JLabel("");
		lblEquiposVerde.setIcon(new ImageIcon(Main.class.getResource("/images/ordenador-portatilVerde.png")));

		lblEquiposVerde.setBounds(10, 360, 46, 64);
		panel.add(lblEquiposVerde);
		JLabel lblEquipos = new JLabel("");
		lblEquipos.setIcon(new ImageIcon(Main.class.getResource("/images/ordenador-portatil.png")));

		lblEquipos.setBounds(10, 360, 46, 64);
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

		lvlSalida_1.setBounds(10, 10, 46, 64);
		panel.add(lvlSalida_1);
		JLabel lblCuentaVerde = new JLabel("");
		lblCuentaVerde.setIcon(new ImageIcon(Main.class.getResource("/images/cuentaVerde32.png")));
		
		lvlSalida_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				lvlSalida_1.setVisible(false);
				lblCuentaVerde.setVisible(true);
				lblAula.setVisible(true);
				lblAulaVerde.setVisible(false);
				lblIncidencias.setVisible(true);
				lblIncidenciasVerde.setVisible(false);
				lblPedidosVerde.setVisible(false);
				lblPedidos.setVisible(true);
				lblEquipos.setVisible(true);
				lblEquiposVerde.setVisible(false);
				lblCrudAulas.setVisible(true);
				lblCrudAulaVerde.setVisible(false);
				if(!(gu.getUserOnline() instanceof AdministradorDTO)) {
					System.out.println("UsuarioDTO");
					lblCrudAulas.setVisible(false);
					lblCrudAulaVerde.setVisible(false);
					lblEquipos.setVisible(false);
					lblEquiposVerde.setVisible(false);
					lblPedidos.setVisible(false);
					lblPedidosVerde.setVisible(false);
					lblIncidencias.setVisible(false);
					lblIncidenciasVerde.setVisible(false);
					lblTextAulas.setVisible(false);
					lblTextEquipos.setVisible(false);
					lblTextGaulas.setVisible(false);
					lblTextIncidencias.setVisible(false);
					lblTextPedidos.setVisible(false);
					lblTextPerfil.setVisible(false);
				}
				aulas.setVisible(false);
				perfil.setVisible(true);
				incidencias.setVisible(false);
				crud.setVisible(false);
				crudEquipo.setVisible(false);
				stock.setVisible(false);

			}
		});
		lblAula.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblCuentaVerde.setVisible(false);
				lvlSalida_1.setVisible(true);
				lblAulaVerde.setVisible(true);
				lblAula.setVisible(false);
				lblIncidencias.setVisible(true);
				lblIncidenciasVerde.setVisible(false);
				lblPedidosVerde.setVisible(false);
				lblPedidos.setVisible(true);
				lblEquipos.setVisible(true);
				lblEquiposVerde.setVisible(false);
				lblCrudAulas.setVisible(true);
				lblCrudAulaVerde.setVisible(false);
				if(!(gu.getUserOnline() instanceof AdministradorDTO)) {
					System.out.println("UsuarioDTO");
					lblCrudAulas.setVisible(false);
					lblCrudAulaVerde.setVisible(false);
					lblEquipos.setVisible(false);
					lblEquiposVerde.setVisible(false);
					lblPedidos.setVisible(false);
					lblPedidosVerde.setVisible(false);
					lblIncidencias.setVisible(false);
					lblIncidenciasVerde.setVisible(false);
					lblTextAulas.setVisible(false);
					lblTextEquipos.setVisible(false);
					lblTextGaulas.setVisible(false);
					lblTextIncidencias.setVisible(false);
					lblTextPedidos.setVisible(false);
					lblTextPerfil.setVisible(false);
				}
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
		lblCrudAulas.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblCrudAulas.setVisible(false);
				lblCrudAulaVerde.setVisible(true);

				lblCuentaVerde.setVisible(false);
				lvlSalida_1.setVisible(true);
				lblAulaVerde.setVisible(false);
				lblAula.setVisible(true);
				lblIncidencias.setVisible(true);
				lblIncidenciasVerde.setVisible(false);
				lblPedidosVerde.setVisible(false);
				lblPedidos.setVisible(true);
				lblEquipos.setVisible(true);
				lblEquiposVerde.setVisible(false);
				aulas.setVisible(false);
				perfil.setVisible(false);
				incidencias.setVisible(false);
				crud.setVisible(true);
				crudEquipo.setVisible(false);
				stock.setVisible(false);

			}
		});
		lblIncidencias.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblCuentaVerde.setVisible(false);
				lvlSalida_1.setVisible(true);
				lblAulaVerde.setVisible(false);
				lblAula.setVisible(true);
				lblIncidencias.setVisible(false);
				lblIncidenciasVerde.setVisible(true);
				lblPedidosVerde.setVisible(false);
				lblPedidos.setVisible(true);
				lblEquipos.setVisible(true);
				lblEquiposVerde.setVisible(false);
				lblCrudAulas.setVisible(true);
				lblCrudAulaVerde.setVisible(false);
				aulas.setVisible(false);
				perfil.setVisible(false);
				incidencias.setVisible(true);
				crud.setVisible(false);
				crudEquipo.setVisible(false);
				stock.setVisible(false);

			}
		});
		lblPedidos.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblCuentaVerde.setVisible(false);
				lvlSalida_1.setVisible(true);
				lblAulaVerde.setVisible(false);
				lblAula.setVisible(true);
				lblIncidencias.setVisible(true);
				lblIncidenciasVerde.setVisible(false);
				lblPedidosVerde.setVisible(true);
				lblPedidos.setVisible(false);
				lblEquipos.setVisible(true);
				lblEquiposVerde.setVisible(false);
				lblCrudAulas.setVisible(true);
				lblCrudAulaVerde.setVisible(false);
				
				aulas.setVisible(false);
				perfil.setVisible(false);
				incidencias.setVisible(false);
				crud.setVisible(false);
				crudEquipo.setVisible(false);
				stock.setVisible(true);
			}
		});
		lblEquipos.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblCuentaVerde.setVisible(false);
				lvlSalida_1.setVisible(true);
				lblAulaVerde.setVisible(false);
				lblAula.setVisible(true);
				lblIncidencias.setVisible(true);
				lblIncidenciasVerde.setVisible(false);
				lblPedidosVerde.setVisible(false);
				lblPedidos.setVisible(true);
				lblEquipos.setVisible(false);
				lblEquiposVerde.setVisible(true);
				lblCrudAulas.setVisible(true);
				lblCrudAulaVerde.setVisible(false);
				aulas.setVisible(false);
				perfil.setVisible(false);
				incidencias.setVisible(false);
				crud.setVisible(false);
				crudEquipo.setVisible(true);
				stock.setVisible(false);

			}
		});
		lblCuentaVerde.setBounds(10, 10, 46, 64);
		panel.add(lblCuentaVerde);
		

		
				lblPedidosVerde.setVisible(false);
		lblEquiposVerde.setVisible(false);
		lvlSalida_1.setVisible(false);
		lblCuentaVerde.setVisible(true);
		lblCrudAulaVerde.setVisible(false);
		cargarUsuarioOnline();

		if(gu.getUserOnline() instanceof AdministradorDTO) {
		}
		else if(gu.getUserOnline() instanceof TecnicoDTO) {
			System.out.println("TecnicoDTO");
		}
		else if(gu.getUserOnline() instanceof UsuarioDTO) {
			System.out.println("UsuarioDTO");
			lblCrudAulas.setVisible(false);
			lblCrudAulaVerde.setVisible(false);
			lblEquipos.setVisible(false);
			lblEquiposVerde.setVisible(false);
			lblPedidos.setVisible(false);
			lblPedidosVerde.setVisible(false);
			lblIncidencias.setVisible(false);
			lblIncidenciasVerde.setVisible(false);
			lblTextAulas.setVisible(false);
			lblTextEquipos.setVisible(false);
			lblTextGaulas.setVisible(false);
			lblTextIncidencias.setVisible(false);
			lblTextPedidos.setVisible(false);
			lblTextPerfil.setVisible(false);
		}

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
				//gu.modificarPerfil(new UsuarioDTO(gu.getUserOnline().getIdUsuario(),txtNombreOnline,txtDireccion,txtApellidos,txtEmailOnline));
				//TODO
			}
		});
		bntModificarPerfil.setBackground(new Color(0x43B581));
		panel_2_1.add(bntModificarPerfil);
		// txtNombreOnline = new JTextField(gu.getUserOnline().getUserName());
		txtNombreOnline = new JTextField();
		txtNombreOnline.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNombreOnline.setForeground(Color.WHITE);
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
		txtEmailOnline.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtEmailOnline.setForeground(Color.WHITE);
		txtEmailOnline.setBackground(new Color(0x566573));
		txtEmailOnline.setColumns(10);
		txtEmailOnline.setBounds(20, 403, 218, 22);
		
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
		txtEquipoOnline.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtEquipoOnline.setForeground(Color.WHITE);
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
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtTelefono.setForeground(Color.WHITE);
		txtTelefono.setColumns(10);
		txtTelefono.getDocument().addDocumentListener(new DocumentListener() {

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
		txtTelefono.setBackground(new Color(86, 101, 115));
		txtTelefono.setBounds(20, 564, 118, 22);
		panel_2_1.add(txtTelefono);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelefono.setBounds(20, 527, 155, 14);
		panel_2_1.add(lblTelefono);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDireccion.setBounds(20, 619, 155, 14);
		panel_2_1.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtDireccion.setForeground(Color.WHITE);
		txtDireccion.setColumns(10);
		txtDireccion.getDocument().addDocumentListener(new DocumentListener() {

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
		txtDireccion.setBackground(new Color(86, 101, 115));
		txtDireccion.setBounds(20, 658, 218, 22);
		panel_2_1.add(txtDireccion);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellidos.setBounds(237, 300, 118, 14);
		panel_2_1.add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtApellidos.setForeground(Color.WHITE);
		txtApellidos.setColumns(10);
		txtApellidos.getDocument().addDocumentListener(new DocumentListener() {

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
		txtApellidos.setBackground(new Color(86, 101, 115));
		txtApellidos.setBounds(237, 330, 118, 22);
		panel_2_1.add(txtApellidos);
		// Oculto el btn despues de los inputs
		bntModificarPerfil.setVisible(false);

		JPanel panel_4_1 = new JPanel();
		panel_4_1.setLayout(null);
		panel_4_1.setBounds(506, 75, 793, 89);
		if(gu.getUserOnline() instanceof AdministradorDTO) {
			perfil.add(panel_4_1);
		}
		

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(506, 200, 795, 500);
		if(gu.getUserOnline() instanceof AdministradorDTO) {
			perfil.add(scrollPane2);
		}
		table2 = new JTable();
		table2.setFillsViewportHeight(true);
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defaultModelUsuarios = (new DefaultTableModel(new Object[][] {}, new String[] { "DNI", "Rol", "Nombre", "Apellidos",
				"Email", "Direccion", "Telefono", "User Name", "Password", "Nombre Equipo" }) {
			public boolean isCellEditable(int row, int column) {
			    if(column == 1 || column == 9) {
			        return false;
			    }else {
				    return true;

			    }
			}
		});
		table2.setModel(defaultModelUsuarios);
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
			defaultModelUsuarios.addRow(fila);
		}
		Object[] filaBlanca = { "", "", "", "", "", "", "", "", "", "" };
		defaultModelUsuarios.addRow(filaBlanca);
		table2.setModel(defaultModelUsuarios);
		scrollPane2.setViewportView(table2);


		JButton btnNewButton_2 = new JButton("A\u00F1adir");
		// btnNewButton_2.setForeground(new Color(0x43B581));
		btnNewButton_2.setBackground(new Color(86, 101, 115));
		
		btnNewButton_2.setForeground(new Color(0x43B581));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		try {
			img = ImageIO.read(getClass().getResource("/images/more32.png"));
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
					Object[] filaBlanca = { "", "", "", "", "", "", "", "", "", "" };
					defaultModelUsuarios.addRow(filaBlanca);
					table2.setModel(defaultModelUsuarios);
					frame.setModal(true);
					frame.setVisible(true);					
					cargarTabla();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(480, 0, 161, 89);
		panel_4_1.add(btnNewButton_2);

		JButton btnNewButton_2_1 = new JButton("Modificar");
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2_1.setForeground(Color.ORANGE);
		try {
			img = ImageIO.read(getClass().getResource("/images/direcciones32.png"));
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
				String mensaje = "ï¿½Usuario modificado correctamente\u0021";
				if (!modificarusuario(user))
					mensaje = "ï¿½Error al modificar el usuario\u0021";
				JOptionPane.showMessageDialog(null, mensaje);
			}
		});
		btnNewButton_2_1.setBackground(new Color(86, 101, 115));
		btnNewButton_2_1.setBounds(313, 0, 170, 89);
		panel_4_1.add(btnNewButton_2_1);

		JButton btnNewButton_2_2 = new JButton("Eliminar");
		
		btnNewButton_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2_2.setForeground(new Color(241, 57, 83));
		try {
			img = ImageIO.read(getClass().getResource("/images/bin32.png"));
			btnNewButton_2_2.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensaje = "ï¿½Usuario borrado correctamente\u0021";
				DefaultTableModel model = (DefaultTableModel) table2.getModel();
				String id = model.getValueAt(table2.getSelectedRow(), 0).toString();
				int option = JOptionPane.showConfirmDialog(null, "\u00bfBorrar usuario?", "Eliminar Usuario",
						JOptionPane.OK_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					if (!borrarUsuario(id))
						mensaje = "¡Error al borrar el usuario\u0021";
					JOptionPane.showMessageDialog(null, mensaje);
					DefaultTableModel model22 = (DefaultTableModel) table2.getModel();
					model22.removeRow(table2.getSelectedRow());
				}
				
			}
		});
		btnNewButton_2_2.setBackground(new Color(86, 101, 115));
		btnNewButton_2_2.setBounds(144, 0, 170, 89);
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

				String mensaje = "¡Error al degradar usuario!";
				if (rol.equals("admin")) {
					AdministradorDTO admin = new AdministradorDTO(id, userName, email, 0, pass, nombre, apellidos,
							direccion, telefono);
					if (gu.degradarUsuario(admin))
						mensaje = "¡Usuario degradado!";
				} else if (rol.equals("tecnico")) {
					TecnicoDTO tec = new TecnicoDTO(id, userName, email, 0, pass, nombre, apellidos, direccion,
							telefono);
					if (gu.degradarUsuario(tec))
						mensaje = "¡Usuario degradado!";
				} else {
					UsuarioDTO user = new UsuarioDTO(id, userName, email, 0, pass, nombre, apellidos, direccion,
							telefono);
					if (gu.degradarUsuario(user))
						mensaje = "¡Usuario degradado!";
				}
				JOptionPane.showMessageDialog(null, mensaje);
				cargarTabla();
			}
		});
		btnDegradar.setBounds(0, 0, 147, 89);
		btnDegradar.setBackground(new Color(86, 101, 115));
		try {
			img = ImageIO.read(getClass().getResource("/images/degradar32.png"));
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
				cargarTabla();
			}
		});
		btnPromocionar.setBounds(639, 0, 154, 89);
		btnPromocionar.setBackground(new Color(86, 101, 115));
		try {
			img = ImageIO.read(getClass().getResource("/images/aumentar32.png"));
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
			img = ImageIO.read(getClass().getResource("/images/candado32.png"));
			btnNewButton_2_3.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnNewButton_2_3.setBackground(new Color(86, 101, 115));
		btnNewButton_2_3.setBounds(654, 507, 479, 109);
		if(!(gu.getUserOnline() instanceof AdministradorDTO)) {
			perfil.add(btnNewButton_2_3);
		}
		

		JButton btnNewButton_2_2_1 = new JButton("Solicitud");
		btnNewButton_2_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SolicitudUsuarioPanel upanel = new SolicitudUsuarioPanel();
				upanel.setUndecorated(true);
				upanel.setLocationRelativeTo(null);

				upanel.setVisible(true);
			}
		});
		btnNewButton_2_2_1.setBackground(new Color(86, 101, 115));
		btnNewButton_2_2_1.setBounds(654, 274, 210, 147);
		if(!(gu.getUserOnline() instanceof AdministradorDTO)) {
			perfil.add(btnNewButton_2_2_1);
		}

		JButton btnNewButton_2_2_2 = new JButton("Incidencia");
		btnNewButton_2_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IncidenciaUsuarioPanel soPanel = new IncidenciaUsuarioPanel();
				soPanel.setUndecorated(true);
				soPanel.setLocationRelativeTo(null);

				soPanel.setVisible(true);

			}
		});
		btnNewButton_2_2_2.setBackground(new Color(86, 101, 115));
		btnNewButton_2_2_2.setBounds(923, 274, 210, 147);
		if(!(gu.getUserOnline() instanceof AdministradorDTO)) {
			perfil.add(btnNewButton_2_2_2);
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///***************************************************************METODOS PERFIL***********************************************************************///
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void cargarTabla() {
		System.out.println("hola?");
		int rowCount = defaultModelUsuarios.getRowCount();
		System.out.println(rowCount);
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			defaultModelUsuarios.removeRow(i);
		}
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
			defaultModelUsuarios.addRow(fila);

		}
		Object[] filaBlanca = { "", "", "", "", "", "", "", "", "", "" };
		defaultModelUsuarios.addRow(filaBlanca);
		table2.setModel(defaultModelUsuarios);
	}
	private void cargarUsuarioOnline() {
		UsuarioDTO udto = gu.getUserOnline();
		try {
			if (udto != null) {
				txtNombreOnline.setText(udto.getUserName());
				txtEmailOnline.setText(udto.getEmail());
				txtApellidos.setText(udto.getApellidos());
				txtTelefono.setText(udto.getTelefono());
				txtDireccion.setText(udto.getDireccion());
				if (udto.getEquipo() != null)
					txtEquipoOnline.setText((udto.getEquipo().getNombre()));
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
		h.setLocationRelativeTo(null);
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
		return gu.modificarPerfil(u);
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
					return gu.modificarPerfil(u);
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

		btnAsignarAlumno = new JButton("Asignar");
		btnAsignarAlumno.setEnabled(true);
		btnAsignarAlumno.setBackground(Color.ORANGE);
		btnAsignarAlumno.setBounds(768, 744, 229, 36);
		btnAsignarAlumno.setEnabled(false);
		if((gu.getUserOnline() instanceof AdministradorDTO) || (gu.getUserOnline() instanceof TecnicoDTO)) {
			aulas.add(btnAsignarAlumno);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(802, 547, 392, 177);
		aulas.add(scrollPane);
		JTable tableAlumnos = new JTable();
		tableAlumnos.setFillsViewportHeight(true);
		tableAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defaultModelAlumno = (new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre" }));
		tableAlumnos.setModel(defaultModelAlumno);
		tableAlumnos.getColumnModel().getColumn(0).setPreferredWidth(86);
		tableAlumnos.getColumnModel().getColumn(1).setPreferredWidth(86);
		scrollPane.setViewportView(tableAlumnos);
		
		JScrollPane scrollPane_software = new JScrollPane();
		scrollPane_software.setBounds(802, 400, 392, 100);
		aulas.add(scrollPane_software);
		JTable tableAlumnos_software = new JTable();
		tableAlumnos_software.setFillsViewportHeight(true);
		tableAlumnos_software.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defaultModelAlumnoSoftware = (new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre" }));
		tableAlumnos_software.setModel(defaultModelAlumnoSoftware);
		tableAlumnos_software.getColumnModel().getColumn(0).setPreferredWidth(86);
		tableAlumnos_software.getColumnModel().getColumn(1).setPreferredWidth(86);
		scrollPane_software.setViewportView(tableAlumnos_software);
		
		btnDesasignarAlumno = new JButton("Desasignar");
		btnDesasignarAlumno.setEnabled(true);
		btnDesasignarAlumno.setBackground(new Color(220, 20, 60));
		btnDesasignarAlumno.setBounds(997, 744, 229, 36);
		btnDesasignarAlumno.setEnabled(false);
		if((gu.getUserOnline() instanceof AdministradorDTO) || (gu.getUserOnline() instanceof TecnicoDTO)) {
			aulas.add(btnDesasignarAlumno);
		}
		

		JButton btnAnadirEquipo = new JButton("A\u00F1adir Equipo");
		btnAnadirEquipo.setBackground(Color.ORANGE);
		btnAnadirEquipo.setBounds(261, 45, 229, 31);
		if((gu.getUserOnline() instanceof AdministradorDTO) || (gu.getUserOnline() instanceof TecnicoDTO)) {
			aulas.add(btnAnadirEquipo);
		}

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
				AsignarUsuarioEquipoPanel2 frame = new AsignarUsuarioEquipoPanel2(equipoSeleccionado.getIdEquipo());
				frame.setModal(true);
				frame.setVisible(true);
				ga.cargarEquiposAula();
				int rowCount = defaultModelAlumno.getRowCount();
				System.out.println(""+rowCount);
				// Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
					defaultModelAlumno.removeRow(i);
				}
				ArrayList<UsuarioDTO> array = new ArrayList<UsuarioDTO>();
				array = gu.getListaUsuariosAsignados(equipoSeleccionado.getIdEquipo());

				for (UsuarioDTO udto : array) {

					Object[] fila = { udto.getIdUsuario(), udto.getNombre() };
					defaultModelAlumnoSoftware.addRow(fila);
				}
				Object[] fila = { "", "" };
				defaultModelAlumnoSoftware.addRow(fila);
			}
		});

		btnDesasignarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableAlumnos.getModel();
				String id = model.getValueAt(tableAlumnos.getSelectedRow(), 0).toString();
				
				String mensaje = "Usuario eliminado del equipo";
				if (!gu.desasignarEquipo(id)) 
					mensaje = "Error al eliminar el usuario del equipo";
				JOptionPane.showMessageDialog(null, mensaje);
				model.removeRow(tableAlumnos.getSelectedRow());

			}
		});

		btnAnadirEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (aulaSeleccionada != 0) {
					try {
						if(ga.calcularDisponibilidad(ga.getAulaById(aulaSeleccionada))) {
							AnadirEquipoPanel frame = new AnadirEquipoPanel(aulaSeleccionada);
							frame.setModal(true);
							frame.setVisible(true);
							ga.cargarEquiposAula();
							System.out.println("que si funciona");
							cargarEquiposAula();
						}else {
							JOptionPane.showMessageDialog(null, "Aula llena");
						}

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
						ga.cargarEquiposAula();
						cargarEquiposAula();
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
		lblNewLabel_1_1_1.setBounds(10, 475, 188, 14);
		panel_2.add(lblNewLabel_1_1_1);

		txtNombreEquipo = new JTextField();
		txtNombreEquipo.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtNombreEquipo.setColumns(10);
		txtNombreEquipo.setForeground(Color.WHITE);
		txtNombreEquipo.setBackground(new Color(0x566573));

		txtNombreEquipo.setBounds(10, 38, 434, 22);
		panel_2.add(txtNombreEquipo);
		txtId = new JTextField();
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtId.setColumns(10);
		txtId.setForeground(Color.WHITE);
		txtId.setBackground(new Color(0x566573));

		txtId.setBounds(10, 38, 434, 22);
		panel_2.add(txtId);
		txtId.setVisible(false);
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
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Software");
		lblNewLabel_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_2.setBounds(10, 330, 82, 14);
		panel_2.add(lblNewLabel_1_1_2);
		

		btnDeleteEquipo.setBackground(Color.ORANGE);
		btnDeleteEquipo.setBounds(502, 45, 229, 31);
		if((gu.getUserOnline() instanceof AdministradorDTO) || (gu.getUserOnline() instanceof TecnicoDTO)) {
			aulas.add(btnDeleteEquipo);
		}
		
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

	public void cargarEquiposAula() {
		panel_3.removeAll();
		panel_3.revalidate();
		panel_3.repaint();
		
		int linea1 = 0;
		int linea2 = 0;
		int linea3 = 0;
		int linea4 = 0;
		int linea5 = 0;
		try {
			if(comboBox.getSelectedItem().toString() != null) {
				adto = ga.getAulaByNombre(comboBox.getSelectedItem().toString());
			}
			
			listaEquipos = adto.getEquipos();
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
		btnAsignarAlumno.setEnabled(true);
		btnDesasignarAlumno.setEnabled(true);

		txtIp.setText(e.getIpEquipo());
		txtId.setText(String.valueOf(e.getIdEquipo()));
		equipoSeleccionado = e;
		txtDiscoDuro.setText(((Integer) e.getDiscoDuro()).toString());
		txtRam.setText(((Integer) e.getRam()).toString());
		int rowCount = defaultModelAlumno.getRowCount();
		try {
			System.out.println(""+rowCount);
			// Remove rows one by one from the end of the table
			for (int i = rowCount - 1; i >= 0; i--) {
				defaultModelAlumno.removeRow(i);
			}
			ArrayList<UsuarioDTO> array = new ArrayList<UsuarioDTO>();
			array = gu.getListaUsuariosAsignados(equipoSeleccionado.getIdEquipo());

			for (UsuarioDTO udto : array) {

				Object[] fila = { udto.getIdUsuario(), udto.getNombre() };
				defaultModelAlumno.addRow(fila);
			}
			ArrayList<ComponenteDTO> componentesPC = new ArrayList<ComponenteDTO>();
			
			for (int i = 0; i < e.getComponentes().size(); i++) {
				
				if (e.getComponentes().get(i) instanceof SoftwareDTO) {
					componentesPC.add(e.getComponentes().get(i));
					
				}
			}
			for (ComponenteDTO udto : componentesPC) {

				Object[] fila = { udto.getIdComponente(), udto.getDescripcion() };
				defaultModelAlumno.addRow(fila);
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
		
		
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

		JButton btnNewButton = new JButton("A\u00F1adir");
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
				String mensaje = "ï¿½Aula creada correctamente\u0021";
				if (!crearAula(adto))
					mensaje = "ï¿½Error al crear el aula\u0021";
				JOptionPane.showMessageDialog(null, mensaje);
				recargarAula();

			}
		});
		btnNewButton.setBounds(190, 124, 235, 89);
		btnNewButton.setForeground(new Color(0x43B581));
		btnNewButton.setBackground(new Color(86, 101, 115));

		btnNewButton.setForeground(new Color(0x43B581));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		try {
			img = ImageIO.read(getClass().getResource("/images/more32.png"));
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
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableAula.getModel();
				// String nombre = model.getValueAt(tableAula.getSelectedRow(), 0);

				int option = JOptionPane.showConfirmDialog(null, "ï¿½Borrar Aula?", "Eliminar Aula",
						JOptionPane.OK_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String mensaje = "ï¿½Error al borrar el aula\u0021";
					AulaDTO adto = ga.getAulaById((int) Integer.parseInt(model.getValueAt(tableAula.getSelectedRow(), 0).toString()));
					for(EquipoDTO edto : adto.getEquipos()) {
						ge.desasignarAula(edto.getIdEquipo());
					}
					if (eliminarAula(
							(int) Integer.parseInt(model.getValueAt(tableAula.getSelectedRow(), 0).toString()))) {
						mensaje = "ï¿½Aula borrada correctamente\u0021";
						model.removeRow(tableAula.getSelectedRow());
						nombreSeleccionado = "";
					}
					JOptionPane.showMessageDialog(null, mensaje);

				}
			}
		});
		btnNewButton_3.setBounds(900, 124, 235, 89);
		btnNewButton_3.setForeground(new Color(241, 57, 83));
		try {
			img = ImageIO.read(getClass().getResource("/images/bin32.png"));
			btnNewButton_3.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnNewButton_3.setBackground(new Color(86, 101, 115));

		crud.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Modificar");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 15));
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

					String mensaje = "ï¿½Aula modificada correctamente\u0021";
					if (!modificarAula(adto))
						mensaje = "ï¿½Error al modificada el aula\u0021";
					JOptionPane.showMessageDialog(null, mensaje);

				}
			}
		});
		btnNewButton_4.setBounds(542, 124, 235, 89);
		btnNewButton_4.setBackground(new Color(86, 101, 115));
		btnNewButton_4.setForeground(Color.ORANGE);
		try {
			img = ImageIO.read(getClass().getResource("/images/direcciones32.png"));
			btnNewButton_4.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		crud.add(btnNewButton_4);

		JLabel lblNewLabel_7 = new JLabel("CRUD AULAS");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel_7.setBounds(10, 22, 197, 43);
		crud.add(lblNewLabel_7);

		// crud.add(table);
	}

	private void recargarAula() {
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
		scrollPaneEquipo.setBounds(76, 244, 1155, 480);
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
		JLabel lblNewLabel_7 = new JLabel("CRUD EQUIPOS");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel_7.setBounds(10, 22, 210, 43);
		crudEquipo.add(lblNewLabel_7);
		JButton btnNewButton = new JButton("A"+'\u00f1'+"adir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableEquipo.getModel();
				int id = Integer.parseInt(model.getValueAt(tableEquipo.getSelectedRow(), 0).toString());
				String nombre = model.getValueAt(tableEquipo.getSelectedRow(), 1).toString();
				String ip = model.getValueAt(tableEquipo.getSelectedRow(), 2).toString();
				int discoDuro = Integer.parseInt(model.getValueAt(tableEquipo.getSelectedRow(), 3).toString());
				int ram = Integer.parseInt(model.getValueAt(tableEquipo.getSelectedRow(), 4).toString());

				EquipoDTO edto = new EquipoDTO(ip, nombre, discoDuro, ram);
				String mensaje = "ï¿½Equipo creado correctamente\u0021";
				if (!crearEquipo(edto))
					mensaje = "ï¿½Error al crear el equipo\u0021";
				JOptionPane.showMessageDialog(null, mensaje);
				recargar();

			}
		});
		btnNewButton.setBounds(190, 124, 235, 89);
		btnNewButton.setForeground(new Color(241, 57, 83));
		btnNewButton.setForeground(new Color(0x43B581));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnNewButton.setForeground(new Color(0x43B581));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		try {
			img = ImageIO.read(getClass().getResource("/images/more32.png"));
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

				int option = JOptionPane.showConfirmDialog(null, "\u00bfBuscar equipo?", "Eliminar Equipo",
						JOptionPane.OK_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String mensaje = "ï¿½Equipo borrado correctamente\u0021";
					if (!borrarEquipo(id))
						mensaje = "¡Error al borrar el equipo\u0021";
					JOptionPane.showMessageDialog(null, mensaje);
					recargar();

				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_3.setForeground(new Color(241, 57, 83));
		try {
			img = ImageIO.read(getClass().getResource("/images/bin32.png"));
			btnNewButton_3.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		btnNewButton_3.setBackground(new Color(86, 101, 115));

		btnNewButton_3.setBounds(900, 124, 235, 89);
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

				int option = JOptionPane.showConfirmDialog(null, "\u00bfModificar aula?", "Modificar Aula",
						JOptionPane.OK_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String mensaje = "ï¿½Equipo modificado correctamente\u0021";
					if (!modificarEquipo(edto))
						mensaje = "ï¿½Error al modificar el equipo\u0021";
					JOptionPane.showMessageDialog(null, mensaje);
					recargar();
				}

			}
		});
		btnNewButton_4.setBounds(542, 124, 235, 89);
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_4.setForeground(Color.ORANGE);
		btnNewButton_4.setForeground(Color.ORANGE);
		try {
			img = ImageIO.read(getClass().getResource("/images/direcciones32.png"));
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
		for (UsuarioDTO udto : gu.getUsersByIdEquipo(idEquipo)) {
			gu.desasignarEquipo(udto.getIdUsuario());
		}
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
		if(gu.getUserOnline() instanceof AdministradorDTO)
			array = gs.getListaNoAtendidas();
		else if(gu.getUserOnline() instanceof TecnicoDTO)
			array = ((TecnicoDTO)gu.getUserOnline()).getIncidenciasAsignadasNoAtendidas();
		if(array!=null) {
			for (IncidenciaDTO e : array) {

				Object[] fila = { e.getCodigo(), e.getFechaSol(), e.getDescripcion() };
				defaultModelinc.addRow(fila);
			}
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

					SolicitudTecnicoPanel intec = new SolicitudTecnicoPanel(sdto);

					intec.setUndecorated(true);
					intec.setLocationRelativeTo(null);
					intec.setVisible(true);
					tableIncidencias.getSelectionModel().clearSelection();

				}
				if (tableIncidencias.getSelectedRow() != -1) {
					IncidenciaTecnicoPanel sotec = new IncidenciaTecnicoPanel(
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
				new String[] { "COD", "Descripcion", "Tipo", "Marca", "Peso","Precio","Id Stock" }) {
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
		tableHardware.getColumnModel().getColumn(5).setPreferredWidth(450);
		tableHardware.getColumnModel().getColumn(6).setPreferredWidth(450);
		
		scrollPaneIncidencias.setViewportView(tableHardware);

		scrollPaneIncidencias.setVisible(false);
		JScrollPane scrollPaneSoftware = new JScrollPane();
		scrollPaneSoftware.setBounds(54, 202, 566, 495);
		stock.add(scrollPaneSoftware);
		JTable tableSoftware = new JTable();
		tableSoftware.setFillsViewportHeight(true);
		tableSoftware.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defaultModelSoftware = (new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Descripcion", "Cod licencia" , "Precio", "Id Stock" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tableSoftware.setModel(defaultModelSoftware);
		tableSoftware.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableSoftware.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableSoftware.getColumnModel().getColumn(2).setPreferredWidth(450);
		tableSoftware.getColumnModel().getColumn(3).setPreferredWidth(450);
		tableSoftware.getColumnModel().getColumn(4).setPreferredWidth(450);

		
		scrollPaneSoftware.setViewportView(tableSoftware);
		JLabel lblNewLabel_7 = new JLabel("COMPRAS Y STOCK");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel_7.setBounds(10, 22, 400, 43);
		stock.add(lblNewLabel_7);
		JScrollPane scrollPaneCompras = new JScrollPane();
		scrollPaneCompras.setBounds(743, 202, 534, 495);
		stock.add(scrollPaneCompras);
		JTable tableCompras = new JTable();
		tableCompras.setFillsViewportHeight(true);
		tableCompras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel defaultModelCompras = (new DefaultTableModel(new Object[][] {},
				new String[] { "COD", "Precio", "Cantidad", "Descripcion","idStock" }) {
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
		tableCompras.getColumnModel().getColumn(4).setPreferredWidth(450);


		tableCompras.setModel(defaultModelCompras);
		scrollPaneCompras.setViewportView(tableCompras);

		JButton btnNewButton_8 = new JButton("");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<LineaCompraDTO> cesta = new ArrayList<LineaCompraDTO>();
				CompraDTO compra = null;
				int unidadesTotales = tableCompras.getRowCount();
				float precioTotal = 0;
				String fechaActual = getFechaHoraActual();
		
				for(int i = 0; i < unidadesTotales; i++) {
					int id = Integer.parseInt(tableCompras.getValueAt(i, 0).toString());
					//String descri = tableCompras.getValueAt(i, 2).toString();
					float precio = Float.parseFloat(tableCompras.getValueAt(i, 1).toString());
					precioTotal += precio;
					int cant = Integer.parseInt(tableCompras.getValueAt(i, 2).toString());
					int idStock = Integer.parseInt(tableCompras.getValueAt(i, 4).toString());
					cesta.add(new LineaCompraDTO(idStock,cant,precio));
				}
					
				try {
					compra = new CompraDTO(0,proveedorSeleccionado.getIdProveedor(),fechaActual,precioTotal,cesta,3);
					gcm.crearCompra(compra);
					gcm.crearLineaCompra(cesta,gcm.getListaCompras().size());
					for(LineaCompraDTO lcdto : cesta) {
						gst.sumarStock(lcdto.getStock().getIdStock(), lcdto.getUnidades());
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_8.setBounds(1000, 714, 100, 64);
		btnNewButton_8.setBackground(Color.WHITE);
		try {
			img = ImageIO.read(getClass().getResource("/images/supermercado (1).png"));
			btnNewButton_8.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnNewButton_8.setBorder(null);

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

		JButton btnNewButton_9 = new JButton("");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel tm = (DefaultTableModel) tableCompras.getModel();
				if(tableSoftware.getSelectedRow()!=-1) {
					DefaultTableModel modelSoftware = (DefaultTableModel) tableSoftware.getModel();
					System.out.println("hola"+tableSoftware.getSelectedRow());
					int id = Integer.parseInt(modelSoftware.getValueAt(tableSoftware.getSelectedRow(), 0).toString());
					String descri = modelSoftware.getValueAt(tableSoftware.getSelectedRow(), 2).toString();
					float precio = Float.parseFloat(modelSoftware.getValueAt(tableSoftware.getSelectedRow(), 3).toString());
					int idStock = Integer.parseInt(modelSoftware.getValueAt(tableSoftware.getSelectedRow(), 4).toString());

					Object[] fila = { id, precio, 1, descri,idStock };
					tm.addRow(fila);

				}
				if(tableHardware.getSelectedRow()!=-1) {
					DefaultTableModel modelHardware = (DefaultTableModel) tableHardware.getModel();
					System.out.println("hola"+tableHardware.getSelectedRow());
					int id = Integer.parseInt(modelHardware.getValueAt(tableHardware.getSelectedRow(), 0).toString());
					String descri = modelHardware.getValueAt(tableHardware.getSelectedRow(), 1).toString();
					float precio = Float.parseFloat(modelHardware.getValueAt(tableHardware.getSelectedRow(), 5).toString());
					int idStock = Integer.parseInt(modelHardware.getValueAt(tableHardware.getSelectedRow(), 6).toString());
					Object[] fila = { id, precio, 1, descri, idStock };
					tm.addRow(fila);

				}
			}
		});
		btnNewButton_9.setBounds(635, 400, 100, 45);
		btnNewButton_9.setBackground(Color.WHITE);
		try {
			img = ImageIO.read(getClass().getResource("/images/proximo.png"));
			btnNewButton_9.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnNewButton_9.setBorder(null);
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
		comboBoxStock = new JComboBox();
		comboBoxStock = new JComboBox();
		comboBoxStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proveedorSeleccionado =  gp.getProvById(Integer.parseInt(comboBoxStock.getSelectedItem().toString()));
				for(StockDTO stock:proveedorSeleccionado.getListaStock()) {
					if(stock.getComponentes().size() != 0) {
						if(stock.getComponentes().get(0) instanceof HardwareDTO) {
							hardwareLista.add((HardwareDTO) stock.getComponentes().get(0));
						}
						else if(stock.getComponentes().get(0) instanceof SoftwareDTO) {
							softwareLista.add((SoftwareDTO) stock.getComponentes().get(0));
						}

					}
					else if (stock.getEquipos() != null && stock.getEquipos().size() != 0) {
						equipoLista.add((EquipoDTO) stock.getEquipos().get(0));
					}
				}
				ArrayList<ComponenteDTO> arrayHardware = new ArrayList<ComponenteDTO>();
				arrayHardware = gc.getListaHardware();
				float precio = 0;
				int idStock = 0;
				for (ComponenteDTO componente : arrayHardware) {
					for(StockDTO sdto : proveedorSeleccionado.getListaStock()) {
						for(ComponenteDTO cdto : sdto.getComponentes()) {
							if(cdto.getIdComponente()==componente.getIdComponente()) {
								precio = sdto.getPrecio();
								idStock = sdto.getIdStock();
							}
						}
					}
					Object[] fila = { componente.getIdComponente(), componente.getDescripcion(), ((HardwareDTO) componente).getTipo(),
							((HardwareDTO) componente).getMarca(),precio,idStock };
					defaultModel2.addRow(fila);
				}
				tableHardware.setModel(defaultModel2);
				ArrayList<ComponenteDTO> arraysoftware = new ArrayList<ComponenteDTO>();
				arraysoftware = gc.getListaSoftware();
				for (int i = 0; i < arraysoftware.size(); i++) {

				}
				// String rol = "";
				// Double total;
				for (ComponenteDTO so : arraysoftware) {
					for(StockDTO sdto : proveedorSeleccionado.getListaStock()) {
						for(ComponenteDTO cdto : sdto.getComponentes()) {
							if(cdto.getIdComponente()==so.getIdComponente()) {
								precio = sdto.getPrecio();
								idStock = sdto.getIdStock();
							}
						}
					}
					Object[] fila = { so.getIdComponente(), so.getDescripcion(), ((SoftwareDTO) so).getCodLiciencia(),precio,idStock };
					defaultModelSoftware.addRow(fila);
				}
				tableSoftware.setModel(defaultModelSoftware);
			}
		});
		comboBoxStock.setFont(new Font("Tahoma", Font.BOLD, 15));
		ArrayList<ProveedorDTO> listaAulas = gp.getListaProv();

		for (ProveedorDTO a : listaAulas) {
			comboBoxStock.addItem(a.getIdProveedor());
		}
		comboBoxStock.setBounds(59, 90, 190, 31);
		stock.add(comboBoxStock);
	}
	private String getFechaActual() {
		Calendar c2 = new GregorianCalendar();
	
	
	    String fecha = Integer.toString(c2.get(Calendar.YEAR)) + "-" + Integer.toString(c2.get(Calendar.MONTH+1))
	            + "-" + Integer.toString(c2.get(Calendar.DATE));
	    
	    return fecha;
	}
	
	private String getFechaHoraActual() {
		Calendar c2 = new GregorianCalendar();

	
	    String fecha = Integer.toString(c2.get(Calendar.YEAR)) + "-" + Integer.toString(c2.get(Calendar.MONTH)+1)
	            + "-" + Integer.toString(c2.get(Calendar.DATE)) + " " + Integer.toString(c2.get(Calendar.HOUR)) +
	            ":" + Integer.toString(c2.get(Calendar.MINUTE)) + ":" + Integer.toString(c2.get(Calendar.SECOND));
	    
	    return fecha;
	}
}
