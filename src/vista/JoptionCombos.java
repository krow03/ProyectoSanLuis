package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DTO.AdministradorDTO;
import DTO.EquipoDTO;
import DTO.TecnicoDTO;
import DTO.UsuarioDTO;
import Gestores.GestorEquipos;
import Gestores.GestorUsuarios;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class JoptionCombos extends JFrame {

	private JPanel contentPane;
	private String rol = "Usuario";
	private int idEquipo;
	private GestorUsuarios gu = new GestorUsuarios();
	private GestorEquipos ge = new GestorEquipos();
	
	public JoptionCombos(UsuarioDTO user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 292, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Usuario");
		comboBox.addItem("Tecnico");
		comboBox.addItem("Administrador");

		comboBox.setBounds(83, 29, 110, 20);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals(null)) rol = comboBox.getSelectedItem().toString();
			
			}
		});
	
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(83, 98, 110, 20);
		comboBox_1.addItem("Sin seleccionar");

		contentPane.add(comboBox_1);
		ArrayList<EquipoDTO> equiposDisp=ge.getEquiposDisponiblesEnAulas();
		for(EquipoDTO eDisp : equiposDisp) {
			comboBox_1.addItem(eDisp.getIdEquipo());
		}
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comboBox_1.getSelectedItem().equals("Sin seleccionar")) idEquipo = Integer.parseInt(comboBox_1.getSelectedItem().toString());
			}
		});

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(rol);
				System.out.println(idEquipo);
				try {
					String mensaje = "!Usuario creado correctamenteï¿½";
					if(rol.equals("Administrador")) {
						AdministradorDTO adto = new AdministradorDTO(user.getIdUsuario(),user.getUserName(),user.getEmail(),idEquipo, user.getPass(), user.getNombre(), user.getApellidos(),user.getDireccion(),user.getTelefono());
						if (!crearUsuario(adto))mensaje="!Error al crear el administrador¿½";
						JOptionPane.showMessageDialog(null, mensaje);
						gu.cargarListaUsuarios();
					}else if(rol.equals("Tecnico")){
						TecnicoDTO tdto = new TecnicoDTO(user.getIdUsuario(),user.getUserName(),user.getEmail(),idEquipo, user.getPass(), user.getNombre(), user.getApellidos(),user.getDireccion(),user.getTelefono());
						if (!crearUsuario(tdto))mensaje="!Error al crear el tecnicoï¿½";
						JOptionPane.showMessageDialog(null, mensaje);
						gu.cargarListaUsuarios();
					}else {
						UsuarioDTO udto = new UsuarioDTO(user.getIdUsuario(),user.getUserName(),user.getEmail(),idEquipo, user.getPass(), user.getNombre(), user.getApellidos(),user.getDireccion(),user.getTelefono());	
						if (!crearUsuario(udto))mensaje="!Error al crear el usuarioï¿½";
						JOptionPane.showMessageDialog(null, mensaje);
						gu.cargarListaUsuarios();
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				dispose();
			}
		});
		btnNewButton.setBounds(10, 155, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(177, 155, 89, 23);
		contentPane.add(btnNewButton_1);
	}
	
	private boolean crearUsuario(UsuarioDTO udto) {
		try {
			return gu.altaUsuario(udto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
