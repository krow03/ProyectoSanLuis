package DAO;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;

import Conexion.Conexion;
import DTO.AdministradorDTO;
import DTO.TecnicoDTO;
import DTO.UsuarioDTO;

public class UsuarioDAO implements PatronDAO<UsuarioDTO>{

	private static final String SQL_INSERT_ASSIGN_EQUIP="INSERT INTO Usuarios (idUsuarios,Roles_idRol,Equipos_idEquipos,userName,email,pass,direccion,telefono,nombre,apellidos) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_INSERT="INSERT INTO Usuarios (idUsuarios,Roles_idRol,userName,email,pass,direccion,telefono,nombre,apellidos) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String SQL_DELETE="DELETE FROM Usuarios WHERE idUsuarios = ?";
	private static final String SQL_UPDATE="UPDATE Usuarios SET userName = ?, email = ?, pass = ?,direccion=?,telefono=?,nombre=?,apellidos=?,Equipos_idEquipos=? WHERE idUsuarios = ?";
	private static final String SQL_FINDPERSONA="SELECT * FROM Usuarios WHERE idUsuarios = ?";
	private static final String SQL_FINDALL="SELECT * FROM Usuarios";
	private static final String SQL_LOGIN="SELECT * FROM Usuarios WHERE userName like ? and pass like ?;";
	private static final String SQL_PROMOTE="UPDATE Usuarios SET Roles_idRol = ? WHERE idUsuarios = ?;";
	
	private Conexion con = Conexion.getInstance();
	

	public boolean insertarConEquipo(UsuarioDTO t) throws SQLException {
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_INSERT_ASSIGN_EQUIP);
			ps.setString(1, t.getIdUsuario());
			if(t instanceof AdministradorDTO) ps.setInt(2, 3);
			else if(t instanceof TecnicoDTO) ps.setInt(2, 2);
			else ps.setInt(2, 1); 
			ps.setInt(3, t.getIdEquipo());
			ps.setString(4, t.getUserName());
			ps.setString(5, t.getEmail());
			ps.setString(6, DigestUtils.sha256Hex(t.getPass()));
			ps.setString(7, t.getDireccion());
			ps.setString(8, t.getTelefono());
			ps.setString(9, t.getNombre());
			ps.setString(10, t.getApellidos());
			
			if (ps.executeUpdate()>0) {
				ps.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean insertar(UsuarioDTO t) throws SQLException {
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_INSERT);
			ps.setString(1, t.getIdUsuario());
			if(t instanceof AdministradorDTO) ps.setInt(2, 3);
			else if(t instanceof TecnicoDTO) ps.setInt(2, 2);
			else ps.setInt(2, 1); 
			ps.setString(3, t.getUserName());
			ps.setString(4, t.getEmail());
			ps.setString(5, DigestUtils.sha256Hex(t.getPass()));
			ps.setString(6, t.getDireccion());
			ps.setString(7, t.getTelefono());
			ps.setString(8, t.getNombre());
			ps.setString(9, t.getApellidos());
			
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
			ps.setString(1, pk.toString());
			int filas = ps.executeUpdate();
			
			if (filas>0) return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean actualizar(UsuarioDTO t) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_UPDATE);
			ps.setString(1, t.getUserName());
			ps.setString(2, t.getEmail());
			ps.setString(3, t.getPass());
			ps.setString(4, t.getDireccion());
			ps.setString(5, t.getTelefono());
			ps.setString(6, t.getNombre());
			ps.setString(7, t.getApellidos());
			ps.setInt(8, t.getIdEquipo());
			
			ps.setString(9, t.getIdUsuario());

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
	public UsuarioDTO buscar(Object pk) {
		UsuarioDTO user = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDPERSONA);
			ps.setString(1, pk.toString());
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true){
				if(rs.getInt("Roles_idRol")==1) {
					user = new UsuarioDTO(rs.getString("idUsuarios"),rs.getString("userName"),rs.getString("email"),rs.getInt("Equipos_idEquipos"),rs.getString("pass"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("direccion"),rs.getString("telefono"));
					return user;
				}else if(rs.getInt("Roles_idRol")==2) {
					TecnicoDTO tec = new TecnicoDTO(rs.getString("idUsuarios"),rs.getString("userName"),rs.getString("email"),rs.getInt("Equipos_idEquipos"),rs.getString("pass"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("direccion"),rs.getString("telefono"));
					return tec;
				}else if(rs.getInt("Roles_idRol")==3) {
					AdministradorDTO admin = new AdministradorDTO(rs.getString("idUsuarios"),rs.getString("userName"),rs.getString("email"),rs.getInt("Equipos_idEquipos"),rs.getString("pass"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("direccion"),rs.getString("telefono"));
					return admin;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public ArrayList<UsuarioDTO> listarTodos() {
		ArrayList<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getInt("Roles_idRol")==1) {
					UsuarioDTO user = new UsuarioDTO(rs.getString("idUsuarios"),rs.getString("userName"),rs.getString("email"),rs.getInt("Equipos_idEquipos"),rs.getString("pass"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("direccion"),rs.getString("telefono"));
					lista.add(user);
				}else if(rs.getInt("Roles_idRol")==2) {
					TecnicoDTO user = new TecnicoDTO(rs.getString("idUsuarios"),rs.getString("userName"),rs.getString("email"),rs.getInt("Equipos_idEquipos"),rs.getString("pass"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("direccion"),rs.getString("telefono"));
					lista.add(user);
				}else if(rs.getInt("Roles_idRol")==3) {
					AdministradorDTO user = new AdministradorDTO(rs.getString("idUsuarios"),rs.getString("userName"),rs.getString("email"),rs.getInt("Equipos_idEquipos"),rs.getString("pass"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("direccion"),rs.getString("telefono"));
					lista.add(user);
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<UsuarioDTO> listarTodos(Object pk) {
		// TODO Auto-generated method stub
		return null;
	}

	public UsuarioDTO login(String usuario, String pass) {
		UsuarioDTO user=null;
		pass =  DigestUtils.sha256Hex(pass);
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_LOGIN);
			ps.setString(1, usuario);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true){
				if(rs.getInt("Roles_idRol")==1) {
					user = new UsuarioDTO(rs.getString("idUsuarios"),rs.getString("userName"),rs.getString("email"),rs.getInt("Equipos_idEquipos"),rs.getString("pass"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("direccion"),rs.getString("telefono"));
					return user;
				}else if(rs.getInt("Roles_idRol")==2) {
					TecnicoDTO tec = new TecnicoDTO(rs.getString("idUsuarios"),rs.getString("userName"),rs.getString("email"),rs.getInt("Equipos_idEquipos"),rs.getString("pass"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("direccion"),rs.getString("telefono"));
					return tec;
				}else if(rs.getInt("Roles_idRol")==3) {
					AdministradorDTO admin = new AdministradorDTO(rs.getString("idUsuarios"),rs.getString("userName"),rs.getString("email"),rs.getInt("Equipos_idEquipos"),rs.getString("pass"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("direccion"),rs.getString("telefono"));
					return admin;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean promocionar(UsuarioDTO t) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_PROMOTE);
			if(t instanceof AdministradorDTO) {
				ps.setInt(2, 3);
			}else if(t instanceof TecnicoDTO) 
				{ps.setInt(2, 2);
			}else { 
				ps.setInt(2, 1); 
			}
			
			ps.setString(2, t.getIdUsuario());

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
}
