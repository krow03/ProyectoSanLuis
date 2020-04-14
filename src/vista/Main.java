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
import DTO.EquipoDTO;
import DTO.IncidenciaDTO;
import DTO.SolicitudDTO;
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
	private final int CENTRO_SELECCIONADO = 1;
	private static DefaultTableModel defaultModel = new DefaultTableModel();
	private static DefaultTableModel defaultModel2 = new DefaultTableModel();
	private String rol2;
	private String idEquipo2;
	private JComboBox comboBox;
	private Image img;
	private JPanel panel_3;
	private EquipoDTO equipoSeleccionado;
	private JPanel aulas;
	private JPanel perfil;
	private JPanel crud;
	private JPanel crudEquipo;
	private JPanel incidencias;
	private JPanel contentPane;
	private String nombreSeleccionado;
	int xx, xy;
	private JPanel stock;
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
		gu.cargarListaUsuarios();
		ge.cargarListaEquipos();
		ga.cargarListaAulas(CENTRO_SELECCIONADO);
		
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

			}
		});
		lvlSalida_1.setBounds(10, 11, 46, 64);
		panel.add(lvlSalida_1);

		cargarUsuarioOnline();

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
	private boolean modificarAula(AulaDTO a) {
        return ga.modificarAula(a);
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
	private boolean eliminarAula(int idAula) {
        return ga.borrarAula(idAula);
    }
	private void cargarDatosEquipo(EquipoDTO e) {
		txtNombreEquipo.setText(e.getNombre());
		txtIp.setText(e.getIpEquipo());
		equipoSeleccionado = e;
		txtDiscoDuro.setText(((Integer) e.getDiscoDuro()).toString());
		txtRam.setText(((Integer) e.getRam()).toString());
	}

	private boolean actualizarPerfil() {
		UsuarioDTO u = gu.getUserOnline();
		u.setUserName(txtNombreOnline.getText());
		u.setEmail(txtEmailOnline.getText());
		return gu.modificarUsuario(u);
	}

	private boolean crearAula(AulaDTO a) {
		try {
			return ga.crearAula(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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

	

	private void visualizarPerfil() {
		perfil = new JPanel();
		perfil.setBackground(Color.WHITE);
		perfil.setBounds(64, 37, 1311, 878);
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
		defaultModel2 = (new DefaultTableModel(new Object[][] {},
				new String[] { "DNI", "Rol" , "Nombre","Apellidos","Email", "Direccion","Telefono","User Name",  "Password","Id Equipo" }));
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
		Double total;
		for (UsuarioDTO e : array) {
			if(e instanceof AdministradorDTO) {
				rol = "admin";
			}else if(e instanceof TecnicoDTO){
				rol = "tecnico";
			}else {
				rol = "usuario";
			}
			Object[] fila = { e.getIdUsuario(), rol,e.getNombre(),e.getApellidos(), e.getEmail(),e.getDireccion(),e.getTelefono(), e.getUserName(), e.getPass(),e.getIdEquipo() };
			defaultModel2.addRow(fila);
		}
		Object[] filaBlanca = {"","","","","","","", "", "","" };
		defaultModel2.addRow(filaBlanca);
		table2.setModel(defaultModel2);
		scrollPane2.setViewportView(table2);
		table2.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
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
					UsuarioDTO user = new UsuarioDTO(id,userName,email,0, pass, nombre, apellidos,direccion,telefono);
					JoptionCombos frame = new JoptionCombos(user,false);
					frame.setVisible(true);
				}catch(Exception ex) {
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
				UsuarioDTO user = new UsuarioDTO(id,userName,email,0, pass, nombre, apellidos,direccion,telefono);
				JoptionCombos frame = new JoptionCombos(user,true);
				frame.setVisible(true);
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
                    if (!borrarUsuario(id))mensaje="!Error al borrar el usuarioï¿½";
    				JOptionPane.showMessageDialog(null, mensaje);
    				gu.cargarListaUsuarios();
                }
				
			}
		});
		btnNewButton_2_2.setBackground(new Color(86, 101, 115));
		btnNewButton_2_2.setBounds(115, 0, 188, 89);
		panel_4_1.add(btnNewButton_2_2);
		
		JButton btnDegradar = new JButton("Degradar");
		btnDegradar.addActionListener(new ActionListener() {
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
				String rol = model.getValueAt(table2.getSelectedRow(), 1).toString();
				String mensaje = "!Error al degradar usuario!";
				if(rol.equals("admin")) {
					AdministradorDTO admin = new AdministradorDTO(id,userName,email,0, pass, nombre, apellidos,direccion,telefono);
					if(gu.degradarUsuario(admin)) mensaje = "¡Usuario degradado!";
				}else if(rol.equals("tecnico")){
					TecnicoDTO tec = new TecnicoDTO(id,userName,email,0, pass, nombre, apellidos,direccion,telefono);
					if(gu.degradarUsuario(tec)) mensaje = "¡Usuario degradado!";
				}else {
					UsuarioDTO user = new UsuarioDTO(id,userName,email,0, pass, nombre, apellidos,direccion,telefono);
					if(gu.degradarUsuario(user)) mensaje = "¡Usuario degradado!";
				}
				JOptionPane.showMessageDialog(null, mensaje);
				gu.cargarListaUsuarios();
			}
		});
		btnDegradar.setBounds(0, 0, 117, 89);
		panel_4_1.add(btnDegradar);
		
		JButton btnPromocionar = new JButton("Promocionar");
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
				if(rol.equals("admin")) {
					AdministradorDTO admin = new AdministradorDTO(id,userName,email,0, pass, nombre, apellidos,direccion,telefono);
					if(gu.promocionarUsuario(admin)) mensaje = "¡Usuario promocionado!";
				}else if(rol.equals("tecnico")){
					TecnicoDTO tec = new TecnicoDTO(id,userName,email,0, pass, nombre, apellidos,direccion,telefono);
					if(gu.promocionarUsuario(tec)) mensaje = "¡Usuario promocionado!";
				}else {
					UsuarioDTO user = new UsuarioDTO(id,userName,email,0, pass, nombre, apellidos,direccion,telefono);
					if(gu.promocionarUsuario(user)) mensaje = "¡Usuario promocionado!";
				}
				JOptionPane.showMessageDialog(null, mensaje);
				gu.cargarListaUsuarios();
			}
		});
		btnPromocionar.setBounds(676, 0, 117, 89);
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

		JButton btnNewButton_2_2_1 = new JButton("Incidencia");
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

		JButton btnNewButton_2_2_2 = new JButton("Solicitud");
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

	private boolean borrarUsuario(String idUsuario) {
		return gu.borrarUsuario(idUsuario);
	}

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

		JButton btnModificarEquipo = new JButton("Modificar");
		btnModificarEquipo.setEnabled(false);
		btnModificarEquipo.setBackground(Color.ORANGE);
		btnModificarEquipo.setBounds(768, 744, 229, 36);
		aulas.add(btnModificarEquipo);

		JButton btnEliminarEquipo = new JButton("Eliminar");
		btnEliminarEquipo.setEnabled(false);
		btnEliminarEquipo.setBackground(new Color(220, 20, 60));
		btnEliminarEquipo.setBounds(997, 744, 229, 36);
		aulas.add(btnEliminarEquipo);

		JButton btnAnadirEquipo = new JButton("A\u00F1adir Equipo");
		btnAnadirEquipo.setBackground(Color.ORANGE);
		btnAnadirEquipo.setBounds(502, 44, 229, 31);
		aulas.add(btnAnadirEquipo);

		btnModificarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});

		btnEliminarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		btnAnadirEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_3.removeAll();
				panel_3.revalidate();
				panel_3.repaint();
				if (!comboBox.getSelectedItem().toString().equals("Seleccione un aula"))
					cargarEquiposAula();
				btnEliminarEquipo.setEnabled(true);
				btnModificarEquipo.setEnabled(true);
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

		JLabel lblNewLabel_1_1_1 = new JLabel("Instalaciones Pendientes");
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
	}

	private void visualizarCrudAulas() {
		crud = new JPanel();
		crud.setBounds(101, 37, 1278, 767);
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 244, 1155, 168);
		crud.add(scrollPane);
		JTable table = new JTable();
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defaultModel = (new DefaultTableModel(new Object[][] {},
				new String[] { "Nombre", "Rango Ip", "Capacidad", "Descripcion" }));
		table.setModel(defaultModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(86);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(86);
		table.getColumnModel().getColumn(3).setPreferredWidth(192);
		recargarAula();
		table.setModel(defaultModel);
		scrollPane.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	            nombreSeleccionado = table.getValueAt(table.getSelectedRow(), 0).toString();
	        }
	    });
		
		
		JButton btnNewButton = new JButton("Añadir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String nombre = model.getValueAt(table.getSelectedRow(), 0).toString();
				String ips = model.getValueAt(table.getSelectedRow(), 1).toString();
				int capacidad = Integer.parseInt(model.getValueAt(table.getSelectedRow(), 2).toString());
				String descripcion = model.getValueAt(table.getSelectedRow(), 3).toString();

				AulaDTO adto = new AulaDTO(ips, nombre, descripcion, capacidad, CENTRO_SELECCIONADO);
				String mensaje = "!Equipo creado correctamenteï¿½";
				if (!crearAula(adto))mensaje="!Error al crear el equipoï¿½";
					JOptionPane.showMessageDialog(null, mensaje);
					recargarAula();

			}
		});
		btnNewButton.setBounds(416, 485, 235, 89);
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
		table.setVisible(true);
		Object[] filaBlanca = { "", "", "", "" };
		defaultModel.addRow(filaBlanca);

		JButton btnNewButton_3 = new JButton("Eliminar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String nombre = model.getValueAt(table.getSelectedRow(), 0).toString();
				
				int option = JOptionPane.showConfirmDialog(null, "ï¿½Borrar equipo?", "Eliminar Equipo",
                        JOptionPane.OK_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String mensaje = "!Equipo borrado correctamenteï¿½";
                    if (!eliminarAula(ga.getAulaByNombre(nombre).getIdAula()))
                        mensaje = "!Error al borrar el equipoï¿½";
                    JOptionPane.showMessageDialog(null, mensaje);
            		recargarAula();

                }
			}
		});
		btnNewButton_3.setBounds(545, 485, 235, 89);
		crud.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Modificar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				String nombre = model.getValueAt(table.getSelectedRow(), 0).toString();
				String ips = model.getValueAt(table.getSelectedRow(), 1).toString();
				int capacidad = Integer.parseInt(model.getValueAt(table.getSelectedRow(), 2).toString());
				String descripcion = model.getValueAt(table.getSelectedRow(), 3).toString();
				
				AulaDTO adto = new AulaDTO(ga.getAulaByNombre(nombreSeleccionado).getIdAula(),ips, nombre, descripcion, capacidad, CENTRO_SELECCIONADO);
				int option = JOptionPane.showConfirmDialog(null, "ï¿½Modificar aula?", "Modificar Aula",
                        JOptionPane.OK_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                	
                    String mensaje = "!Aula modificada correctamenteï¿½";
                    if (!modificarAula(adto))
                        mensaje = "!Error al modificada el Aulaï¿½";
                    JOptionPane.showMessageDialog(null, mensaje);
            		recargarAula();

                }
			}
		});
		btnNewButton_4.setBounds(667, 485, 235, 89);
		crud.add(btnNewButton_4);
		
		JLabel lblNewLabel_7 = new JLabel("CRUD AULAS");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(0, 0, 46, 14);
		crud.add(lblNewLabel_7);
		// crud.add(table);
	}
	private void recargarAula() {
		AulaDAO ped = new AulaDAO();
		ArrayList<AulaDTO> array = new ArrayList<AulaDTO>();
		array = ga.getListaAulas();

		Double total;
		for (AulaDTO e : array) {

			Object[] fila = { e.getNombre(), e.getRangoIps(), e.getCapacidad(), e.getDescripcion() };
			defaultModel.addRow(fila);
		}
	}
	private void visualizarCrudEquipos() {
		crudEquipo = new JPanel();
		crudEquipo.setBounds(101, 37, 1278, 767);
		crudEquipo.setBackground(Color.WHITE);

		contentPane.add(crudEquipo);
		crudEquipo.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(54, 28, 1177, 89);
		crudEquipo.add(panel_4);
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

		JScrollPane scrollPaneEquipo = new JScrollPane();
		scrollPaneEquipo.setBounds(76, 244, 1155, 168);
		crudEquipo.add(scrollPaneEquipo);
		JTable tableEquipo = new JTable();
		tableEquipo.setFillsViewportHeight(true);
		tableEquipo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defaultModel = (new DefaultTableModel(new Object[][] {},
				new String[] { "Id","Nombre", "IP", "Disco Duro", "Ram" }));
		tableEquipo.setModel(defaultModel);
		tableEquipo.getColumnModel().getColumn(0).setPreferredWidth(86);
		tableEquipo.getColumnModel().getColumn(1).setPreferredWidth(106);
		tableEquipo.getColumnModel().getColumn(2).setPreferredWidth(86);
		tableEquipo.getColumnModel().getColumn(3).setPreferredWidth(192);
		tableEquipo.getColumnModel().getColumn(4).setPreferredWidth(192);
		recargar();
		tableEquipo.setModel(defaultModel);
		scrollPaneEquipo.setViewportView(tableEquipo);
		
		JButton btnNewButton = new JButton("Añadir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableEquipo.getModel();
				int id = Integer.parseInt(model.getValueAt(tableEquipo.getSelectedRow(), 0).toString());
				String nombre = model.getValueAt(tableEquipo.getSelectedRow(), 1).toString();
				String ip = model.getValueAt(tableEquipo.getSelectedRow(), 2).toString();
				int discoDuro = Integer.parseInt(model.getValueAt(tableEquipo.getSelectedRow(), 3).toString());
				int ram = Integer.parseInt(model.getValueAt(tableEquipo.getSelectedRow(), 4).toString());
				
				EquipoDTO edto = new EquipoDTO(ip, nombre, discoDuro,ram);
				String mensaje = "!Equipo creado correctamenteï¿½";			
				if (!crearEquipo(edto))mensaje="!Error al crear el equipoï¿½";
					JOptionPane.showMessageDialog(null, mensaje);
					ge.cargarListaEquipos();
					recargar();

			}
		});
		btnNewButton.setBounds(416, 485, 235, 89);
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
		btnNewButton_3.setForeground(Color.ORANGE);
		try {
			img = ImageIO.read(getClass().getResource("/images/cambio (3).png"));
			btnNewButton_3.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		btnNewButton_3.setBackground(new Color(86, 101, 115));

		btnNewButton_3.setBounds(545, 485,  235, 89);
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
				
				EquipoDTO edto = new EquipoDTO(id,ip,nombre, discoDuro,ram);
				
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
		btnNewButton_4.setBounds(667, 485, 235, 89);
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
		btnNewButton_4.setBounds(235, 0, 235, 89);
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
		
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			defaultModel.removeRow(i);
		}
		ArrayList<EquipoDTO> array = new ArrayList<EquipoDTO>();
		array = ge.getListaEquipos();

		for (EquipoDTO e : array) {

			Object[] fila = { e.getIdEquipo(), e.getNombre(), e.getIpEquipo(), e.getDiscoDuro(), e.getRam()};
			defaultModel.addRow(fila);

		}
		Object[] fila = { "", "", "", "", ""};
		defaultModel.addRow(fila);

	}
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
		defaultModel2 = (new DefaultTableModel(new Object[][] {},
				new String[] { "COD", "FECHA" , "Descripcion" }));
		tableIncidencias.setModel(defaultModel2);
		tableIncidencias.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableIncidencias.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableIncidencias.getColumnModel().getColumn(2).setPreferredWidth(450);

		ArrayList<IncidenciaDTO> array = new ArrayList<IncidenciaDTO>();
		array = gs.getListaSol();
		
		for (IncidenciaDTO e : array) {

			Object[] fila = { e.getCodigo(),e.getFechaSol(),e.getDescripcion() };
			defaultModel2.addRow(fila);
		}
		tableIncidencias.setModel(defaultModel2);
		scrollPaneIncidencias.setViewportView(tableIncidencias);
		tableIncidencias.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            nombreSeleccionado = tableIncidencias.getValueAt(tableIncidencias.getSelectedRow(), 0).toString();
	        }
	    });

		

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(20, 30, 611, 667);
		incidencias.add(scrollPane2);
		JTable tableSolicitudes = new JTable();
		tableSolicitudes.setFillsViewportHeight(true);
		tableSolicitudes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defaultModel2 = (new DefaultTableModel(new Object[][] {},
				new String[] { "COD", "FECHA" , "Descripcion" }));
		tableSolicitudes.setModel(defaultModel2);
		tableSolicitudes.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableSolicitudes.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableSolicitudes.getColumnModel().getColumn(2).setPreferredWidth(450);
		ArrayList<IncidenciaDTO> array2 = new ArrayList<IncidenciaDTO>();
		array2 = gs.getListaAsignadasA(gu.getUserOnline().getIdUsuario());
		
		for (IncidenciaDTO e : array2) {
			
			Object[] fila = { e.getCodigo(),e.getFechaSol(),e.getDescripcion() };
			defaultModel2.addRow(fila);
		}
		tableSolicitudes.setModel(defaultModel2);
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
		JButton btnNewButton_1 = new JButton("Visualizar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tableSolicitudes.getSelectedRow() != -1) {
	
					IncidenciaTecPanel3 sotec = new IncidenciaTecPanel3(gs.getIncidencia(Integer.parseInt(tableSolicitudes.getValueAt(tableSolicitudes.getSelectedRow(), 0).toString())));
					sotec.setUndecorated(true);
					sotec.setLocationRelativeTo(null);
					sotec.setVisible(true);
				}
				if(tableIncidencias.getSelectedRow() != -1) {
					SolicitudTecPanel2 sotec = new SolicitudTecPanel2(Integer.parseInt(tableIncidencias.getValueAt(tableIncidencias.getSelectedRow(), 0).toString()),tableIncidencias.getValueAt(tableIncidencias.getSelectedRow(), 3).toString());
					sotec.setUndecorated(true);
					sotec.setLocationRelativeTo(null);
					sotec.setVisible(true);
				}
			}
		});
		btnNewButton_1.setBounds(570, 730, 128, 45);
		incidencias.add(btnNewButton_1);
	}
	private void visualizarIncidencias2() {
		stock = new JPanel();
		stock.setBackground(Color.WHITE);
		stock.setBounds(88, 37, 1287, 780);
		contentPane.add(incidencias);
		stock.setLayout(null);
		JScrollPane scrollPaneIncidencias = new JScrollPane();
		scrollPaneIncidencias.setBounds(650, 30, 611, 667);
		stock.add(scrollPaneIncidencias);
		JTable tableIncidencias = new JTable();
		tableIncidencias.setFillsViewportHeight(true);
		tableIncidencias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defaultModel2 = (new DefaultTableModel(new Object[][] {},
				new String[] { "COD", "FECHA" , "Descripcion" }));
		tableIncidencias.setModel(defaultModel2);
		tableIncidencias.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableIncidencias.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableIncidencias.getColumnModel().getColumn(2).setPreferredWidth(450);

		ArrayList<UsuarioDTO> array = new ArrayList<UsuarioDTO>();
		array = gu.getList();
		String rol = "";
		Double total;
		for (UsuarioDTO e : array) {
			if(e instanceof AdministradorDTO) {
				rol = "admin";
			}else if(e instanceof TecnicoDTO){
				rol = "tecnico";
			}else {
				rol = "usuario";
			}
			Object[] fila = { e.getIdUsuario(), rol,e.getNombre(),e.getApellidos() };
			defaultModel2.addRow(fila);
		}
		tableIncidencias.setModel(defaultModel2);
		scrollPaneIncidencias.setViewportView(tableIncidencias);
		tableIncidencias.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            nombreSeleccionado = tableIncidencias.getValueAt(tableIncidencias.getSelectedRow(), 0).toString();
	        }
	    });
	}
}
