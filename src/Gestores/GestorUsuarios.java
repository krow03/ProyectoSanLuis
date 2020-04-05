package Gestores;

import java.util.ArrayList;

import DTO.UsuarioDTO;
import DTO.TecnicoDTO;
import DTO.AdministradorDTO;

import DAO.UsuarioDAO;


public class GestorUsuarios {
	private UsuarioDAO udao = new UsuarioDAO();
	private static ArrayList <UsuarioDTO> listaUsers;
	private static UsuarioDTO userOnline;
	
	public UsuarioDTO getUserOnline() {
		return userOnline;
	}
	
	public boolean  login(String user,String pass){
		userOnline = udao.login(user, pass);
		if(userOnline!=null) {
			return true;
		}
		return false;
	}
	
	
}
