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
	private static final String SQL_INSERT="INSERT INTO Equipos (idEquipos,nombre,discoDuro,ram,ipEquipo) VALUES (?,?,?,?,?)";
	private static final String SQL_DELETE="DELETE FROM Equipos WHERE idEquipos = ?";
	private static final String SQL_UPDATE="UPDATE Equipos SET nombre=?,discoDuro=?,ram=?,ipEquipo = ? WHERE idEquipos = ?";
	private static final String SQL_FIND="SELECT * FROM Equipos WHERE idEquipos = ?";
	private static final String SQL_FINDALL="SELECT * FROM Equipos";
	private static final String SQL_FINDALLBYIDAULA="SELECT * FROM Equipos WHERE Aulas_idAulas = ?";
	private static final String SQL_ASSIGN="UPDATE Equipos SET Aulas_idAulas = ? WHERE idEquipos = ?;";
	private static final String SQL_FINDALLSTOCK="SELECT * FROM Equipos WHERE Stock_idStock = ?";
	private static final String SQL_FINDALLDISP="SELECT * FROM Equipos WHERE Aulas_idAulas IS NULL";
	private static final String SQL_UNASSIGN="UPDATE Equipos SET Aulas_idAulas = null WHERE idEquipos = ?";
	private Conexion con = Conexion.getInstance();
	
	public boolean asignarAula(int idEquipo,int idAula) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_ASSIGN);
			ps.setInt(1, idAula);
			
			ps.setInt(2, idEquipo);

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
	
	public boolean desasignarAula(int idEquipo) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_UNASSIGN);
			ps.setInt(1, idEquipo);

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
	public boolean insertar(EquipoDTO t) throws SQLException {
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_INSERT);
			ps.setInt(1, t.getIdEquipo());
			ps.setString(2, t.getNombre());
			ps.setInt(3, t.getDiscoDuro());
			ps.setInt(4, t.getRam());
			ps.setString(5, t.getIpEquipo());
		
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
			ps.setString(1, t.getNombre());
			ps.setInt(2, t.getDiscoDuro());
			ps.setInt(3, t.getRam());
			ps.setString(4, t.getIpEquipo());
			
			ps.setInt(5, t.getIdEquipo());

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
				equip = new EquipoDTO(rs.getInt("idEquipos"),rs.getString("ipEquipo"),rs.getString("nombre"),rs.getInt("discoDuro"),rs.getInt("ram"));
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
				EquipoDTO equip = new EquipoDTO(rs.getInt("idEquipos"),rs.getString("ipEquipo"),rs.getString("nombre"),rs.getInt("discoDuro"),rs.getInt("ram"));
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
				EquipoDTO equip = new EquipoDTO(rs.getInt("idEquipos"),rs.getString("ipEquipo"),rs.getString("nombre"),rs.getInt("discoDuro"),rs.getInt("ram"));
				lista.add(equip);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	
	public ArrayList<EquipoDTO> listarTodosStock(Object pk) {
		ArrayList<EquipoDTO> lista = new ArrayList<EquipoDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALLSTOCK);
			ps.setString(1, pk.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				EquipoDTO equip = new EquipoDTO(rs.getInt("idEquipos"),rs.getString("ipEquipo"),rs.getString("nombre"),rs.getInt("discoDuro"),rs.getInt("ram"));
				lista.add(equip);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ArrayList<EquipoDTO> listarTodosDisponibles() {
		ArrayList<EquipoDTO> lista = new ArrayList<EquipoDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALLDISP);
 
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				EquipoDTO equip = new EquipoDTO(rs.getInt("idEquipos"),rs.getString("ipEquipo"),rs.getString("nombre"),rs.getInt("discoDuro"),rs.getInt("ram"));
				lista.add(equip);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
}
