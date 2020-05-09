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

import DTO.CompraDTO;
import DTO.EquipoDTO;
import DTO.IncidenciaDTO;
import DTO.LineaCompraDTO;
import gestores.GestorCompras;
import gestores.GestorEquipos;
import gestores.GestorProveedores;
import gestores.GestorSolicitudes;

public class ProveedoresPantallaCompras extends JDialog {

	private JPanel contentPane;
	private GestorCompras gc = GestorCompras.getInstance();
	private GestorProveedores gp = GestorProveedores.getInstance();
	public ProveedoresPantallaCompras(ArrayList<LineaCompraDTO> listaCompra) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 690, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLACK));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(0x566573));

		contentPane.setLayout(null);

		System.out.println();
		
		JScrollPane scrollPaneEquipos = new JScrollPane();
        scrollPaneEquipos.setBounds(39, 85, 606, 288);
        contentPane.add(scrollPaneEquipos);
        JTable tableEquipos = new JTable();
        tableEquipos.setFillsViewportHeight(true);
        tableEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel defaultModelEquipos = (new DefaultTableModel(new Object[][] {},
                new String[] { "Descripcion", "Unidades", "Precio" }));
        tableEquipos.setModel(defaultModelEquipos);
        tableEquipos.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableEquipos.getColumnModel().getColumn(1).setPreferredWidth(100);
        tableEquipos.getColumnModel().getColumn(2).setPreferredWidth(100);
		
       
        

        for (LineaCompraDTO e : listaCompra) {

            Object[] fila = { e.getStock().getDescripcion(), e.getUnidades(),e.getPrecio()};
            defaultModelEquipos.addRow(fila);
        }
        tableEquipos.setModel(defaultModelEquipos);
        scrollPaneEquipos.setViewportView(tableEquipos);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(0xF44444));
		btnNewButton_1.setBounds(556, 11, 89, 41);
		contentPane.add(btnNewButton_1);
	}
}
