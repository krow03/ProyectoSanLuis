package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import DTO.CentroEstudioDTO;


public class CentroEstudioDAO implements PatronDAO<CentroEstudioDTO>{
	private static final String SQL_FIND="SELECT * FROM Usuarios WHERE idUsuarios = ?";
	private Conexion con = Conexion.getInstance();
	
	
	@Override
	public boolean insertar(CentroEstudioDTO t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean borrar(Object pk) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean actualizar(CentroEstudioDTO t) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public CentroEstudioDTO buscar(Object pk) {
		CentroEstudioDTO centro = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FIND);
			ps.setInt(1, (int)pk);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true){
				centro = new CentroEstudioDTO(rs.getInt("idCentro"),rs.getString("nombre_centro"),rs.getString("ciudad_centro"));
				return centro;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return centro;
	}
	@Override
	public ArrayList<CentroEstudioDTO> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<CentroEstudioDTO> listarTodos(Object pk) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
