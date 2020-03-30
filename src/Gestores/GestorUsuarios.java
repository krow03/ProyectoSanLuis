package Gestores;

import java.util.ArrayList;

import DTO.UsuarioDTO;
import DTO.TecnicoDTO;
import DTO.AdministradorDTO;

import DAO.UsuarioDAO;


public class GestorUsuarios {
	private UsuarioDAO udao = new UsuarioDAO();
	private static ArrayList <UsuarioDTO> listaUsers;
}
