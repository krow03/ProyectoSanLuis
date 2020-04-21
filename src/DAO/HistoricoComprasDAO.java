package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import DTO.CompraDTO;
import DTO.HistoricoComprasDTO;

public class HistoricoComprasDAO implements PatronDAO<HistoricoComprasDTO>{
	private static final String SQL_INSERT="INSERT INTO Historico_Compras (Stock_idStock,compras_idCompras,unidades,precio) VALUES (?,?,?,?)";
	private static final String SQL_DELETE_ALL="DELETE FROM Historico_Compras WHERE compras_idCompras = ? and Stock_idStock =?";
	private static final String SQL_DELETE="DELETE FROM Historico_Compras WHERE compras_idCompras = ?";
	private static final String SQL_UPDATE="UPDATE Historico_Compras SET unidades=? WHERE Stock_idStock=? and compras_idCompras=?,";
	private static final String SQL_FIND="SELECT * FROM Historico_Compras WHERE Stock_idStock = ? and compras_idCompras = ? ";
	private static final String SQL_FINDALL="SELECT * FROM Historico_Compras";
	private static final String SQL_FINDALLBYCOMPRA="SELECT * FROM Historico_Compras WHERE compras_idCompras = ?";
	private Conexion con = Conexion.getInstance();
	
	@Override
	public boolean insertar(HistoricoComprasDTO t) throws SQLException {
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_INSERT);
			ps.setInt(1, t.getIdStock());
			ps.setInt(2, t.getIdCompra());
			ps.setInt(3, t.getUnidades());
			ps.setDouble(4, t.getPrecio());
			
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
	public boolean actualizar(HistoricoComprasDTO t) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_UPDATE);
			ps.setInt(1, t.getUnidades());
			ps.setInt(2, t.getIdStock());
			ps.setInt(3, t.getIdCompra());

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
	public HistoricoComprasDTO buscar(Object pk) {
		HistoricoComprasDTO historico = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FIND);
			ps.setInt(1, (int)pk);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true){
				historico = new HistoricoComprasDTO(rs.getInt("Stock_idStock"),rs.getInt("compras_idStock"),rs.getInt("unidades"),rs.getDouble("precio"));
				return historico;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historico;
	}
	@Override
	public ArrayList<HistoricoComprasDTO> listarTodos() {
		ArrayList<HistoricoComprasDTO> lista = new ArrayList<HistoricoComprasDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				HistoricoComprasDTO historico = new HistoricoComprasDTO(rs.getInt("Stock_idStock"),rs.getInt("compras_idStock"),rs.getInt("unidades"),rs.getDouble("precio"));
				lista.add(historico);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	@Override
	public ArrayList<HistoricoComprasDTO> listarTodos(Object pk) {
		ArrayList<HistoricoComprasDTO> lista = new ArrayList<HistoricoComprasDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALLBYCOMPRA);
			ps.setInt(1, (int)pk);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				HistoricoComprasDTO historico = new HistoricoComprasDTO(rs.getInt("Stock_idStock"),rs.getInt("compras_idStock"),rs.getInt("unidades"),rs.getDouble("precio"));
				lista.add(historico);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	

}
