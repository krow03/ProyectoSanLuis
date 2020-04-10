package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import DTO.AdministradorDTO;
import DTO.AulaDTO;
import DTO.EquipoDTO;
import DTO.TecnicoDTO;

public class EquipoDAO implements PatronDAO<EquipoDTO>{
	private static final String SQL_INSERT="INSERT INTO Equipos (idEquipos,nombre,discoDuro,ram,disponibleHD,ipEquipo) VALUES (?,?,?,?,?,?)";
	private static final String SQL_INSERT_AULA="INSERT INTO Equipos (idEquipos,Aulas_idAulas,nombre,discoDuro,ram,disponibleHD,ipEquipo) VALUES (?,?,?,?,?,?,?)";
	private static final String SQL_DELETE="DELETE FROM Equipos WHERE idEquipos = ?";
	private static final String SQL_UPDATE="UPDATE Equipos SET Aulas_idAulas=?,nombre=?,discoDuro=?,ram=?,disponibleHD=?,ipEquipo = ? WHERE idEquipos = ?";
	private static final String SQL_FIND="SELECT * FROM Equipos WHERE idEquipos = ?";
	private static final String SQL_FINDALL="SELECT * FROM Equipos";
	private static final String SQL_FINDALLBYIDAULA="SELECT * FROM Equipos WHERE Aulas_idAulas = ?";
	private Conexion con = Conexion.getInstance();
	

	public boolean insertarEquipoAula(EquipoDTO t) throws SQLException {
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_INSERT_AULA);
			ps.setInt(1, t.getIdEquipo());
			ps.setInt(2, t.getIdAula());
			ps.setString(3, t.getNombre());
			ps.setInt(4, t.getDiscoDuro());
			ps.setInt(5, t.getRam());
			ps.setDouble(6, t.getDisponibleHDD());
			ps.setString(7, t.getIpEquipo());
		
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
	public boolean insertar(EquipoDTO t) throws SQLException {
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_INSERT);
			ps.setInt(1, t.getIdEquipo());
			ps.setString(2, t.getNombre());
			ps.setInt(3, t.getDiscoDuro());
			ps.setInt(4, t.getRam());
			ps.setDouble(5, t.getDisponibleHDD());
			ps.setString(6, t.getIpEquipo());
		
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
	public boolean actualizar(EquipoDTO t) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_UPDATE);
			ps.setInt(1, t.getIdAula());
			ps.setString(2, t.getNombre());
			ps.setInt(3, t.getDiscoDuro());
			ps.setInt(4, t.getRam());
			ps.setDouble(5, t.getDisponibleHDD());
			ps.setString(6, t.getIpEquipo());
			
			ps.setInt(7, t.getIdEquipo());

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
	public EquipoDTO buscar(Object pk) {
		EquipoDTO equip = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FIND);
			ps.setInt(1, (int)pk);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true){
				equip = new EquipoDTO(rs.getInt("idEquipos"),rs.getString("ipEquipo"),rs.getString("nombre"),rs.getInt("discoDuro"),rs.getDouble("disponibleHD"),rs.getInt("ram"),rs.getInt("Aulas_idAulas"));
				return equip;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equip;
	}

	@Override
	public ArrayList<EquipoDTO> listarTodos() {
		ArrayList<EquipoDTO> lista = new ArrayList<EquipoDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				EquipoDTO equip = new EquipoDTO(rs.getInt("idEquipos"),rs.getString("ipEquipo"),rs.getString("nombre"),rs.getInt("discoDuro"),rs.getInt("disponibleHD"),rs.getInt("ram"),rs.getInt("Aulas_idAulas"));
				lista.add(equip);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<EquipoDTO> listarTodos(Object pk) {
		ArrayList<EquipoDTO> lista = new ArrayList<EquipoDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALLBYIDAULA);
			ps.setString(1, pk.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				EquipoDTO equip = new EquipoDTO(rs.getInt("idEquipos"),rs.getString("ipEquipo"),rs.getString("nombre"),rs.getInt("discoDuro"),rs.getInt("disponibleHD"),rs.getInt("ram"),rs.getInt("Aulas_idAulas"));
				lista.add(equip);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
