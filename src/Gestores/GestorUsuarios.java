package Gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DTO.AdministradorDTO;
import DTO.EquipoDTO;
import DTO.IncidenciaDTO;
import DTO.TecnicoDTO;
import DTO.UsuarioDTO;

import DAO.UsuarioDAO;

public class GestorUsuarios {
	private GestorSolicitudes gs = new GestorSolicitudes();
	private static final AdministradorDTO USER_ONLINE=new AdministradorDTO("1","a","a",1,"a","a","a","a","a");
	private UsuarioDAO udao = new UsuarioDAO();
	private static ArrayList <UsuarioDTO> listaUsers;
	private static UsuarioDTO userOnline=USER_ONLINE;
	
	public ArrayList <UsuarioDTO> getList() {
		return listaUsers;
	}
	
	public void recargarUserOnline(UsuarioDTO user) {
		userOnline = user;
	}
	
	public void cargarListaUsuarios() {
		listaUsers = udao.listarTodos();
		cargarListaIncidencias();
	}
	public UsuarioDTO getUserById(String idUsuario) {
		UsuarioDTO udto = null;
		for(UsuarioDTO temp : listaUsers) {
			if(idUsuario.equals(temp.getIdUsuario())) return udto;
		}
		return udto;
	}
	
	public UsuarioDTO getUserOnline() {
		return userOnline;
	}

	private boolean comprobarUltimoAdmin() {
		int cont = 0;
		for(UsuarioDTO user : listaUsers) {
			if(user instanceof AdministradorDTO) {
				cont++;
			}
		}
		//si es ultimo admin devuelve true
		if (cont > 1) return false;
		return true;
	}	
	
	public boolean autorizarAdmin() {
		if(tienePermisos()==3) return true;
		return false;
	}
	
	public boolean autorizarTecnico() {
		if(tienePermisos()==2) return true;
		return false;
	}
	
	private int tienePermisos() {
		if(userOnline instanceof AdministradorDTO) {
			return 3;
		}else if(userOnline instanceof TecnicoDTO){
			return 2;
		}
		return 1;
	}
	
	public boolean  login(String user,String pass){
		userOnline = udao.login(user, pass);
		if(userOnline!=null) return true;
		return false;
	}
	
	public void logOut() {
		userOnline = null;
	}
	
	public boolean promocionarUsuario(UsuarioDTO user) {
		if (user instanceof AdministradorDTO || !autorizarAdmin()) return false;
		return udao.promocionar(user);
	}
	
	public boolean degradarUsuario(UsuarioDTO user) {
		if (user instanceof AdministradorDTO && comprobarUltimoAdmin() || !autorizarAdmin()) return false;
		return udao.degradar(user);
	}
	
	public boolean borrarUsuario(String idUsuario) {
		UsuarioDTO user = getUserById(idUsuario);
		if(user instanceof AdministradorDTO && comprobarUltimoAdmin() || !autorizarAdmin() ) return false;
		return udao.borrar(idUsuario);
	}
	
	public boolean altaUsuario(UsuarioDTO user) {
		try {
			if(tienePermisos()==3)
				return udao.insertar(user);	
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}	
		return false;
	}
	
	public boolean modificarUsuario(UsuarioDTO user) {
		if(tienePermisos()==3)
			return udao.actualizar(user);	
		return false;
	}
	
	public boolean asignarEquipo(UsuarioDTO udto, EquipoDTO edto) {
		udto.setIdEquipo(edto);
		return udao.asignarEquipo(udto);
	}
	
	public void cargarListaIncidencias() {
		for(UsuarioDTO udto : listaUsers) {
			 udto.setIncidencias(gs.getListaAsignadasA(udto.getIdUsuario()));
		}
	}
	
	public void traspasarIncidencias() {
		UsuarioDTO udto = getTecnicoMenosIncidencias();
		udto.setIncidencias(gs.getIncidenciasTecnico(udto.getIdUsuario()));
	}
	
	public UsuarioDTO getTecnicoMenosIncidencias() {
		UsuarioDTO userMenosInci = listaUsers.get(0);
		for(UsuarioDTO udto : listaUsers) {
			if(udto.getIncidencias().size()<userMenosInci.getIncidencias().size()) {
				userMenosInci = udto;
			}
		}
		return userMenosInci;
	}
}
