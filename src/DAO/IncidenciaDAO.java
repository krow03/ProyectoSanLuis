package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import DTO.IncidenciaDTO;
import DTO.SolicitudDTO;

public class IncidenciaDAO implements PatronDAO<IncidenciaDTO>{

	private static final String SQL_INSERT="INSERT INTO SolInci (idSolInci,Realizada,Asignada,Equipos_idEquipos,tipo,fechaSol,fechaFin,estado,descripcion,prioridad) VALUES (?,?,?,?,?,?)";
	private static final String SQL_DELETE="DELETE FROM SolInci WHERE idSolInci = ?";
	private static final String SQL_UPDATE="UPDATE SolInci SET Asignada,fechaFin,estado,descripcion,prioridad = ?,?,?,?,? WHERE idSolInci = ?";
	private static final String SQL_FIND="SELECT * FROM SolInci WHERE idSolInci = ?";
	private static final String SQL_FINDALL="SELECT * FROM SolInci";
	private static final String SQL_FINDALLBYIDASIGNADA="SELECT * FROM SolInci WHERE Asignada = ?";
	private static final String SQL_FINDALLBYIDREALIZADA="SELECT * FROM SolInci WHERE Realizada = ?";
	private static final String SQL_FINDALLBYIDEQUIPO="SELECT * FROM SolInci WHERE Equipos_idEquipos = ?";	
	
	private Conexion con = Conexion.getInstance();

	@Override
	public boolean insertar(IncidenciaDTO t) throws SQLException {
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_INSERT);
			ps.setInt(1, t.getCodigo());
			ps.setString(2, t.getIdRealizadaPor());
			ps.setString(3, t.getIdAsignadaA());
			ps.setInt(4, t.getIdEquipo());
			if(t instanceof SolicitudDTO) {
				ps.setInt(5, 2);
			}else {
				ps.setInt(5, 1);
			}
			ps.setString(6, t.getFechaSol());
			ps.setString(7, t.getFechaFin());
			ps.setString(8,t.getEstado());
			ps.setString(9,t.getDescripcion());
			ps.setInt(10, t.getPrioridad());
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
	public boolean actualizar(IncidenciaDTO t) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_UPDATE);
			ps.setString(1, t.getIdAsignadaA());
			ps.setString(2, t.getFechaFin());
			ps.setString(3, t.getEstado());
			ps.setString(4, t.getDescripcion());
			ps.setInt(5, t.getPrioridad());
			
			ps.setInt(6, t.getCodigo());

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
	public IncidenciaDTO buscar(Object pk) {
		IncidenciaDTO inci = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FIND);
			ps.setInt(1, (int)pk);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true){
				if(rs.getString("tipo").equals("inci")) {
					inci = new IncidenciaDTO(rs.getInt("idSolInci"),rs.getString("Realizada"),rs.getString("Asignada"),rs.getInt("Equipos_idEquipos"),rs.getDate("fechaSol").toString(),rs.getDate("fechaFin").toString(),rs.getString("estado"),rs.getString("descripcion"),rs.getInt("prioridad"));
					return inci;
				}else if(rs.getString("tipo").equals("sol")) {
					SolicitudDTO sol = new SolicitudDTO(rs.getInt("idSolInci"),rs.getString("Realizada"),rs.getString("Asignada"),rs.getInt("Equipos_idEquipos"),rs.getDate("fechaSol").toString(),rs.getDate("fechaFin").toString(),rs.getString("estado"),rs.getString("descripcion"),rs.getInt("prioridad"));
					return sol;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inci;
	}

	@Override
	public ArrayList<IncidenciaDTO> listarTodos() {
		ArrayList<IncidenciaDTO> lista = new ArrayList<IncidenciaDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getString("tipo").equals("inci")) {
					IncidenciaDTO inci = new IncidenciaDTO(rs.getInt("idSolInci"),rs.getString("Realizada"),rs.getString("Asignada"),rs.getInt("Equipos_idEquipos"),rs.getDate("fechaSol").toString(),rs.getDate("fechaFin").toString(),rs.getString("estado"),rs.getString("descripcion"),rs.getInt("prioridad"));
					lista.add(inci);
				}else{
					SolicitudDTO sol = new SolicitudDTO(rs.getInt("idSolInci"),rs.getString("Realizada"),rs.getString("Asignada"),rs.getInt("Equipos_idEquipos"),rs.getDate("fechaSol").toString(),rs.getDate("fechaFin").toString(),rs.getString("estado"),rs.getString("descripcion"),rs.getInt("prioridad"));
					lista.add(sol);
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<IncidenciaDTO> listarTodos(Object pk) {
		ArrayList<IncidenciaDTO> lista = new ArrayList<IncidenciaDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALLBYIDASIGNADA);
			ps.setInt(1, (int)pk);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getInt("tipo")==1) {
					IncidenciaDTO inci = new IncidenciaDTO(rs.getInt("idSolInci"),rs.getString("Realizada"),rs.getString("Asignada"),rs.getInt("Equipos_idEquipos"),rs.getDate("fechaSol").toString(),rs.getDate("fechaFin").toString(),rs.getString("estado"),rs.getString("descripcion"),rs.getInt("prioridad"));
					lista.add(inci);
				}else{
					SolicitudDTO sol = new SolicitudDTO(rs.getInt("idSolInci"),rs.getString("Realizada"),rs.getString("Asignada"),rs.getInt("Equipos_idEquipos"),rs.getDate("fechaSol").toString(),rs.getDate("fechaFin").toString(),rs.getString("estado"),rs.getString("descripcion"),rs.getInt("prioridad"));
					lista.add(sol);
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	public ArrayList<IncidenciaDTO> listarPorAsignadoA(Object pk) {
		ArrayList<IncidenciaDTO> lista = new ArrayList<IncidenciaDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALLBYIDREALIZADA);
			ps.setInt(1, (int)pk);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getInt("tipo")==1) {
					IncidenciaDTO inci = new IncidenciaDTO(rs.getInt("idSolInci"),rs.getString("Realizada"),rs.getString("Asignada"),rs.getInt("Equipos_idEquipos"),rs.getDate("fechaSol").toString(),rs.getDate("fechaFin").toString(),rs.getString("estado"),rs.getString("descripcion"),rs.getInt("prioridad"));
					lista.add(inci);
				}else{
					SolicitudDTO sol = new SolicitudDTO(rs.getInt("idSolInci"),rs.getString("Realizada"),rs.getString("Asignada"),rs.getInt("Equipos_idEquipos"),rs.getDate("fechaSol").toString(),rs.getDate("fechaFin").toString(),rs.getString("estado"),rs.getString("descripcion"),rs.getInt("prioridad"));
					lista.add(sol);
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	public ArrayList<IncidenciaDTO> listarPorEquipo(Object pk) {
		ArrayList<IncidenciaDTO> lista = new ArrayList<IncidenciaDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALLBYIDEQUIPO);
			ps.setInt(1, (int)pk);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getInt("tipo")==1) {
					IncidenciaDTO inci = new IncidenciaDTO(rs.getInt("idSolInci"),rs.getString("Realizada"),rs.getString("Asignada"),rs.getInt("Equipos_idEquipos"),rs.getDate("fechaSol").toString(),rs.getDate("fechaFin").toString(),rs.getString("estado"),rs.getString("descripcion"),rs.getInt("prioridad"));
					lista.add(inci);
				}else{
					SolicitudDTO sol = new SolicitudDTO(rs.getInt("idSolInci"),rs.getString("Realizada"),rs.getString("Asignada"),rs.getInt("Equipos_idEquipos"),rs.getDate("fechaSol").toString(),rs.getDate("fechaFin").toString(),rs.getString("estado"),rs.getString("descripcion"),rs.getInt("prioridad"));
					lista.add(sol);
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
