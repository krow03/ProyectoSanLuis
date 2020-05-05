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
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DTO.EquipoDTO;
import DTO.IncidenciaDTO;
import DTO.UsuarioDTO;
import Gestores.GestorEquipos;
import Gestores.GestorSolicitudes;
import Gestores.GestorUsuarios;

public class AsignarUsuarioEquipoPanel2 extends JDialog {

	private JPanel contentPane;
	private GestorUsuarios gu = GestorUsuarios.getInstance();
	
	public AsignarUsuarioEquipoPanel2(int idEquipo) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 557, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLACK));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(0x566573));

		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione un usuario: ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(171, 11, 231, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuarios del sistema");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 48, 152, 24);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPaneEquipos = new JScrollPane();
        scrollPaneEquipos.setBounds(39, 85, 457, 178);
        contentPane.add(scrollPaneEquipos);
        JTable tableUsuarios = new JTable();
        tableUsuarios.setFillsViewportHeight(true);
        tableUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel defaultModelEquipos = (new DefaultTableModel(new Object[][] {},
                new String[] { "Id", "Nombre" }));
        tableUsuarios.setModel(defaultModelEquipos);
        tableUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableUsuarios.getColumnModel().getColumn(1).setPreferredWidth(100);
        
        ArrayList<UsuarioDTO> listaUsuarios = new ArrayList<UsuarioDTO>();
        listaUsuarios = gu.getList();

        for (UsuarioDTO e : listaUsuarios) {
        	if(e.getEquipo()==null) {
        		Object[] fila = { e.getIdUsuario(), e.getNombre() };
            	defaultModelEquipos.addRow(fila);
        	}else {
        		if(e.getEquipo().getIdEquipo()!=idEquipo) {
        			Object[] fila = { e.getIdUsuario(), e.getNombre() };
	            	defaultModelEquipos.addRow(fila);
        		}
        	}
        }
        tableUsuarios.setModel(defaultModelEquipos);
        scrollPaneEquipos.setViewportView(tableUsuarios);
		
		JButton btnNewButton = new JButton("Añadir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tableUsuarios.getModel();
				String id = model.getValueAt(tableUsuarios.getSelectedRow(), 0).toString();
				String mensaje = "Equipo asignado al alumno";
				if (!gu.asignarEquipo(id,idEquipo))
					mensaje = "Error al asignar el equipo";
				JOptionPane.showMessageDialog(null, mensaje);
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
	}

}
