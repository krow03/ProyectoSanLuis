package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.ProveedorDTO;
import DTO.StockDTO;
import conexion.Conexion;

public class ProveedorDAO implements PatronDAO<ProveedorDTO>{
	private static final String SQL_INSERT="INSERT INTO sanluisdb.proveedores (cif, nombre, email, direccion, telef) VALUES (?,?,?,?,?);";
	private static final String SQL_DELETE="DELETE FROM proveedores WHERE idProveedores = ?";
	private static final String SQL_UPDATE="UPDATE proveedores SET cif=?,nombre=?,email=?,direccion=?,telef=? WHERE idProveedores = ?";
	private static final String SQL_FIND="SELECT * FROM proveedores WHERE idProveedores = ?";
	private static final String SQL_FINDALL="SELECT * FROM proveedores";
	private static final String SQL_FINDALLBYID="SELECT * FROM proveedores WHERE centro_idCentro = ?";
	
	private Conexion con = Conexion.getInstance();
	
	
	@Override
	public boolean insertar(ProveedorDTO t) throws SQLException {
		  try {
	            PreparedStatement ps = con.getCon().prepareStatement(SQL_INSERT);
	            ps.setString(1, t.getCif());
	            ps.setString(2, t.getNombre());
	            ps.setString(3, t.getEmail());
	            ps.setString(4, t.getDireccion());
	            ps.setString(5, t.getTelefono());
	            
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
	public boolean actualizar(ProveedorDTO t) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_UPDATE);
	        ps.setString(1, t.getCif());
	        ps.setString(2, t.getNombre());
	        ps.setString(3, t.getEmail());
	        ps.setString(4, t.getDireccion());
	        ps.setString(5, t.getTelefono());
	        
	        ps.setInt(6, t.getIdProveedor());

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
	public ProveedorDTO buscar(Object pk) {
		ProveedorDTO prov = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FIND);
			ps.setInt(1, (int)pk);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true){
				prov = new ProveedorDTO(rs.getInt("idProveedores"),rs.getString("cif"),rs.getString("nombre"),rs.getString("email"),rs.getString("direccion"),rs.getString("telef"));
				return prov;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prov;
	}

	@Override
	public ArrayList<ProveedorDTO> listarTodos() {
		ArrayList<ProveedorDTO> lista = new ArrayList<ProveedorDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ProveedorDTO prov = new ProveedorDTO(rs.getInt("idProveedores"),rs.getString("cif"),rs.getString("nombre"),rs.getString("email"),rs.getString("direccion"),rs.getString("telef"));
				lista.add(prov);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<ProveedorDTO> listarTodos(Object pk) {
		// TODO Auto-generated method stub
		return null;
	}

}
