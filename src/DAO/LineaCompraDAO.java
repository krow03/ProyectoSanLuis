package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.CompraDTO;
import DTO.LineaCompraDTO;
import conexion.Conexion;

public class LineaCompraDAO implements PatronDAO<LineaCompraDTO>{
	private static final String SQL_INSERT="INSERT INTO Linea_Compras (Stock_idStock,compras_idCompras,unidades,precio) VALUES (?,?,?,?)";
	private static final String SQL_DELETE_ALL="DELETE FROM Linea_Compras WHERE compras_idCompras = ? and Stock_idStock =?";
	private static final String SQL_DELETE="DELETE FROM Linea_Compras WHERE compras_idCompras = ?";
	private static final String SQL_UPDATE="UPDATE Linea_Compras SET unidades=? WHERE Stock_idStock=? and compras_idCompras=?,";
	private static final String SQL_FIND="SELECT * FROM Linea_Compras WHERE Stock_idStock = ? and compras_idCompras = ? ";
	private static final String SQL_FINDALL="SELECT * FROM Linea_Compras";
	private static final String SQL_FINDALLBYCOMPRA="SELECT * FROM Linea_Compras WHERE compras_idCompras = ?";
	private Conexion con = Conexion.getInstance();
	
	@Override
	public boolean insertar(LineaCompraDTO t) throws SQLException {
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_INSERT);
			ps.setInt(1, t.getStock().getIdStock());
			ps.setInt(2, t.getUnidades());
			ps.setDouble(3, t.getPrecio());
			
			if (ps.executeUpdate()>0) {
				ps.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean borrar(Object pk) {
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_DELETE);
			ps.setInt(1, (int)pk);
			int filas = ps.executeUpdate();
			
			if (filas>0) return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean borrar(Object pk, Object pk2) {
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_DELETE_ALL);
			ps.setInt(1, (int)pk);
			ps.setInt(2, (int)pk);
			int filas = ps.executeUpdate();
			
			if (filas>0) return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean actualizar(LineaCompraDTO t) {
		return false;
	}
	
	public boolean actualizar(LineaCompraDTO t,int idCompra) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_UPDATE);
			ps.setInt(1, t.getUnidades());
			ps.setInt(2, t.getStock().getIdStock());
			ps.setInt(3, idCompra);

			if (ps.executeUpdate()>0) return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if (ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return false;
	}
	@Override
	public LineaCompraDTO buscar(Object pk) {
		LineaCompraDTO historico = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FIND);
			ps.setInt(1, (int)pk);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true){
				historico = new LineaCompraDTO(rs.getInt("Stock_idStock"),rs.getInt("unidades"),rs.getDouble("precio"));
				return historico;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historico;
	}
	@Override
	public ArrayList<LineaCompraDTO> listarTodos() {
		ArrayList<LineaCompraDTO> lista = new ArrayList<LineaCompraDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				LineaCompraDTO historico = new LineaCompraDTO(rs.getInt("Stock_idStock"),rs.getInt("unidades"),rs.getDouble("precio"));
				lista.add(historico);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	@Override
	public ArrayList<LineaCompraDTO> listarTodos(Object pk) {
		ArrayList<LineaCompraDTO> lista = new ArrayList<LineaCompraDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALLBYCOMPRA);
			ps.setInt(1, (int)pk);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				LineaCompraDTO historico = new LineaCompraDTO(rs.getInt("Stock_idStock"),rs.getInt("unidades"),rs.getDouble("precio"));
				lista.add(historico);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	

}
