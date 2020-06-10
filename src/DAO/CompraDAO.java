package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.CompraDTO;
import DTO.EquipoDTO;
import conexion.Conexion;

public class CompraDAO implements PatronDAO<CompraDTO>{
	private static final String SQL_INSERT="INSERT INTO Compras (Proveedores_idProveedores,fechaCompra, precioTotal,idEstados) VALUES (?,?,?,3)";
	private static final String SQL_DELETE="DELETE FROM Compras WHERE idCompras = ?";
	private static final String SQL_UPDATE="UPDATE Compras SET Proveedores_idProveedores=?,fechaCompra=?,idEstados=? WHERE idCompras = ?";
	private static final String SQL_FIND="SELECT * FROM Compras WHERE idCompras = ?";
	private static final String SQL_FINDALL="SELECT * FROM Compras";
	private static final String SQL_FINDALLBYID="SELECT * FROM Compras WHERE Proveedores_idProveedores = ?";
	private Conexion con = Conexion.getInstance();
	@Override
	public boolean insertar(CompraDTO t) throws SQLException {
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_INSERT);
			ps.setInt(1, t.getProveedor().getIdProveedor());
			ps.setString(2, t.getFechaCompra());
			ps.setDouble(3, t.getPrecioTotal());
		
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
	@Override
	public boolean actualizar(CompraDTO t) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_UPDATE);
			ps.setInt(1, t.getProveedor().getIdProveedor());
			ps.setString(2, t.getFechaCompra());
			ps.setInt(3, t.getEstado());
			ps.setInt(4, t.getIdCompra());

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
	public CompraDTO buscar(Object pk) {
		CompraDTO compra = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FIND);
			ps.setInt(1, (int)pk);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true){
				compra = new CompraDTO(rs.getInt("idCompras"),rs.getInt("Proveedores_idPorveedor"),rs.getString("ipEquipo"), rs.getDouble("precioTotal"), rs.getInt("idEstado"));
				return compra;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compra;
	}
	@Override
	public ArrayList<CompraDTO> listarTodos() {
		ArrayList<CompraDTO> lista = new ArrayList<CompraDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				CompraDTO compra = new CompraDTO(rs.getInt("idCompras"),rs.getInt("Proveedores_idProveedores"),rs.getDate("fechaCompra").toString(), rs.getDouble("precioTotal"), rs.getInt("idEstados"));
				lista.add(compra);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	@Override
	public ArrayList<CompraDTO> listarTodos(Object pk) {
		ArrayList<CompraDTO> lista = new ArrayList<CompraDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALLBYID);
			ps.setInt(1, (int)pk);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				CompraDTO compra = new CompraDTO(rs.getInt("idCompras"),rs.getInt("Proveedores_idProveedores"),rs.getDate("fechaCompra").toString(), rs.getDouble("precioTotal"), rs.getInt("idEstados"));
				lista.add(compra);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	

}
