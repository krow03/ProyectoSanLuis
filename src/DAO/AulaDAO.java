package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import DTO.AulaDTO;

public class AulaDAO implements PatronDAO<AulaDTO>{
	
	private static final String SQL_INSERT="INSERT INTO Aulas (idAulas,Centro_idCentro,rangoIps,nombre,descripcion,capacidad) VALUES (?,?,?,?,?,?)";
	private static final String SQL_DELETE="DELETE FROM Aulas WHERE idAulas = ?";
	private static final String SQL_UPDATE="UPDATE Aulas SET rangoIps,nombre,descripcion,capacidad = ?,?,?,? WHERE idAulas = ?";
	private static final String SQL_FIND="SELECT * FROM Aulas WHERE idAulas = ?";
	private static final String SQL_FINDALL="SELECT * FROM Aulas";
	private static final String SQL_FINDALLBYID="SELECT * FROM Aulas WHERE centro_idCentro = ?";
	private Conexion con = Conexion.getInstance();
	
	
	@Override
	public boolean insertar(AulaDTO t) throws SQLException {
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_INSERT);
			ps.setInt(1, t.getIdAula());
			ps.setInt(2, t.getIdCentro());
			ps.setString(3, t.getRangoIps());
			ps.setString(4, t.getNombre());
			ps.setString(5, t.getDescripcion());
			ps.setInt(6, t.getCapacidad());
		
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
	public boolean actualizar(AulaDTO t) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_UPDATE);
			ps.setString(1, t.getRangoIps());
			ps.setString(2, t.getNombre());
			ps.setString(3, t.getDescripcion());
			ps.setInt(4, t.getCapacidad());
			
			ps.setInt(5, t.getIdAula());

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
	public AulaDTO buscar(Object pk) {
		AulaDTO aula = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FIND);
			ps.setInt(1, (int)pk);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true){
				aula = new AulaDTO(rs.getInt("idAula"),rs.getString("rangoIps"),rs.getString("nombre"),rs.getString("descripcion"),rs.getInt("capacidad"),rs.getInt("Centro_idCentro"));
				return aula;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aula;
	}

	@Override
	public ArrayList<AulaDTO> listarTodos() {
		ArrayList<AulaDTO> lista = new ArrayList<AulaDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				AulaDTO aula = new AulaDTO(rs.getInt("idAulas"),rs.getString("rangoIps"),rs.getString("nombre"),rs.getString("descripcion"),rs.getInt("capacidad"),rs.getInt("Centro_idCentro"));
				lista.add(aula);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<AulaDTO> listarTodos(Object pk) {
		ArrayList<AulaDTO> lista = new ArrayList<AulaDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALLBYID);
			ps.setInt(1, (int)pk);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				AulaDTO aula = new AulaDTO(rs.getInt("idAula"),rs.getString("rangoIps"),rs.getString("nombre"),rs.getString("descripcion"),rs.getInt("capacidad"),rs.getInt("Centro_idCentro"));
				lista.add(aula);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
