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
import Gestores.GestorEquipos;
import Gestores.GestorSolicitudes;

public class AñdirEquipoPanel extends JFrame {

	private JPanel contentPane;
	private GestorEquipos ge = new GestorEquipos();
	
	public AñdirEquipoPanel(int idAula) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 557, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLACK));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(0x566573));

		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione un equipo: ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(171, 11, 231, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Equipos disponibles");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 48, 152, 24);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPaneEquipos = new JScrollPane();
        scrollPaneEquipos.setBounds(39, 85, 457, 178);
        contentPane.add(scrollPaneEquipos);
        JTable tableEquipos = new JTable();
        tableEquipos.setFillsViewportHeight(true);
        tableEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel defaultModelEquipos = (new DefaultTableModel(new Object[][] {},
                new String[] { "Id", "Nombre" }));
        tableEquipos.setModel(defaultModelEquipos);
        tableEquipos.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableEquipos.getColumnModel().getColumn(1).setPreferredWidth(100);
        
        ArrayList<EquipoDTO> listaEquipos = new ArrayList<EquipoDTO>();
        listaEquipos = ge.getEquiposDisp();

        for (EquipoDTO e : listaEquipos) {

            Object[] fila = { e.getIdEquipo(), e.getNombre() };
            defaultModelEquipos.addRow(fila);
        }
        tableEquipos.setModel(defaultModelEquipos);
        scrollPaneEquipos.setViewportView(tableEquipos);
		
		JButton btnNewButton = new JButton("Añadir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt((String) defaultModelEquipos.getValueAt(tableEquipos.getSelectedRow(), 0).toString());
				String mensaje = "Equipo añadido al aula";
				try {
					if(!ge.asignarAula(id, idAula))
					    mensaje = "!Error al modificar el usuarioï¿½";
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                JOptionPane.showMessageDialog(null, mensaje);
                //Main m = new Main();
                //m.cargarEquiposAula();
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
