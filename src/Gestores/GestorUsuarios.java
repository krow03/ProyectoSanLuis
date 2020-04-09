package Gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DTO.AdministradorDTO;
import DTO.TecnicoDTO;
import DTO.UsuarioDTO;

import DAO.UsuarioDAO;


public class GestorUsuarios {
	
	private UsuarioDAO udao = new UsuarioDAO();
	private static ArrayList <UsuarioDTO> listaUsers;
	private static UsuarioDTO userOnline;
	
	public ArrayList <UsuarioDTO> getList() {
		return listaUsers;
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
		if (user instanceof AdministradorDTO || tienePermisos()!=3) return false;
		return udao.promocionar(user);
	}
	
	public boolean promocionarDegradar(UsuarioDTO user) {
		if (comprobarUltimoAdmin()) return false;
		return promocionarUsuario(user);
	}
	
	public boolean borrarUsuario(UsuarioDTO user) {
		if(!comprobarUltimoAdmin() && tienePermisos()==3) return udao.borrar(user.getIdUsuario());
		return false;
	}
	
	public boolean altaUsuario(UsuarioDTO user) {
		try {
			if(tienePermisos()==3)return udao.insertar(user);	
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}	
		return false;
	}
	
	public boolean modificarUsuario(UsuarioDTO user) {
		if(tienePermisos()==3)return udao.actualizar(user);
		return false;
	}
}
