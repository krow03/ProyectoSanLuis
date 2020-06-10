package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.ComponenteDTO;
import DTO.HardwareDTO;
import DTO.SoftwareDTO;
import conexion.Conexion;


public class ComponenteDAO implements PatronDAO<ComponenteDTO>{

	private static final String SQL_INSERT="INSERT INTO Componentes (descripcion,tipo) VALUES (?,?)";
	private static final String SQL_DELETE="DELETE FROM Componentes WHERE idComponentes = ?";
	private static final String SQL_UPDATE="UPDATE Componentes SET descripcion = ? WHERE idComponentes = ?";
	private static final String SQL_FINDPERSONA="SELECT * FROM Componentes WHERE idComponentes = ?";
	private static final String SQL_FINDALL="SELECT * FROM Componentes";
	private static final String SQL_FINDALLBYID="SELECT * FROM Componentes WHERE Equipos_idEquipos = ?";
	private static final String SQL_FINDALLBYSTOCK="SELECT * FROM Componentes WHERE Stock_idStock = ?";
	private static final String SQL_ASSIGNSTOCK="UPDATE Componentes SET Stock_idStock WHERE idComponentes = ?";
	private static final String SQL_ASSIGNEQUIPO="UPDATE Componentes SET Equipo_idEquipos=? WHERE idComponentes = ?";
	private static final String SQL_UNASSIGNEQUIPO="UPDATE Componentes SET Equipos_idEquipos=null WHERE Equipos_idEquipos = ?";
	private Conexion con = Conexion.getInstance();
	
	@Override
	public boolean insertar(ComponenteDTO t) throws SQLException {
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_INSERT);
			ps.setInt(1, t.getIdComponente());
			ps.setString(2, t.getDescripcion());
			if(t instanceof HardwareDTO) {
				ps.setInt(3, 2);
			}else if(t instanceof SoftwareDTO) 
				{ps.setInt(3, 1);
			}
			
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
	public boolean actualizar(ComponenteDTO t) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_UPDATE);
			ps.setString(1, t.getDescripcion());
			
			ps.setInt(2, t.getIdComponente());

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

	public boolean asignarStock(ComponenteDTO t, int idStock) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_ASSIGNSTOCK);
			ps.setInt(1, idStock);
			
			ps.setInt(2, t.getIdComponente());

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
	
	public boolean asignarEquipo(ComponenteDTO t, int idEquipo) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_ASSIGNEQUIPO);
			ps.setInt(1, idEquipo);
			
			ps.setInt(2, t.getIdComponente());

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
	
	public boolean desasignarEquipo(int idEquipo) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_UNASSIGNEQUIPO);
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
	public ComponenteDTO buscar(Object pk) {
		ComponenteDTO comp = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDPERSONA);
			ps.setInt(1, (int)pk);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true){
				if(rs.getString("tipo").equals("soft")) {
					SoftwareDTO soft = new SoftwareDTO(rs.getInt("idComponentes"),rs.getString("descripcion"),rs.getString("codLicencia"),rs.getDouble("peso"));
					return soft;
				}else if(rs.getString("tipo").equals("hard")) {
					HardwareDTO hard = new HardwareDTO(rs.getInt("idComponentes"),rs.getString("descripcion"),rs.getString("tipoHardware"),rs.getString("marca"));
					return hard;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comp;
	}

	@Override
	public ArrayList<ComponenteDTO> listarTodos() {
		ArrayList<ComponenteDTO> lista = new ArrayList<ComponenteDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getString("tipo").equals("soft")) {
					SoftwareDTO soft = new SoftwareDTO(rs.getInt("idComponentes"),rs.getString("descripcion"),rs.getString("codLicencia"),rs.getDouble("peso"));
					lista.add(soft);
				}else if(rs.getString("tipo").equals("hard")) {
					HardwareDTO hard = new HardwareDTO(rs.getInt("idComponentes"),rs.getString("descripcion"),rs.getString("tipoHardware"),rs.getString("marca"));
					lista.add(hard);
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<ComponenteDTO> listarTodos(Object pk) {
		ArrayList<ComponenteDTO> lista = new ArrayList<ComponenteDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALLBYID);
			ps.setInt(1, (int)pk);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getString("tipo").equals("soft")) {
					SoftwareDTO soft = new SoftwareDTO(rs.getInt("idComponentes"),rs.getString("descripcion"),rs.getString("codLicencia"),rs.getDouble("peso"));
					lista.add(soft);
				}else if(rs.getString("tipo").equals("hard")) {
					HardwareDTO hard = new HardwareDTO(rs.getInt("idComponentes"),rs.getString("descripcion"),rs.getString("tipoHardware"),rs.getString("marca"));
					lista.add(hard);
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ArrayList<ComponenteDTO> listarTodosStock(Object pk) {
		ArrayList<ComponenteDTO> lista = new ArrayList<ComponenteDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALLBYSTOCK);
			ps.setInt(1, (int)pk);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getString("tipo").equals("soft")) {
					SoftwareDTO soft = new SoftwareDTO(rs.getInt("idComponentes"),rs.getString("descripcion"),rs.getString("codLicencia"),rs.getDouble("peso"));
					lista.add(soft);
				}else if(rs.getString("tipo").equals("hard")) {
					HardwareDTO hard = new HardwareDTO(rs.getInt("idComponentes"),rs.getString("descripcion"),rs.getString("tipoHardware"),rs.getString("marca"));
					lista.add(hard);
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
