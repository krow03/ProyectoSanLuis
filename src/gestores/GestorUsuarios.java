package gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DTO.AdministradorDTO;
import DTO.EquipoDTO;
import DTO.IncidenciaDTO;
import DTO.ProveedorDTO;
import DTO.TecnicoDTO;
import DTO.UsuarioDTO;

import DAO.UsuarioDAO;

public class GestorUsuarios {
	private GestorSolicitudes gs = GestorSolicitudes.getInstance();
	private GestorEquipos ge = GestorEquipos.getInstance();
	private static final AdministradorDTO USER_ONLINE=new AdministradorDTO("1","a","a",1,"a","a","a","a","a");
	private UsuarioDAO udao = new UsuarioDAO();
	private static ArrayList <UsuarioDTO> listaUsers;
	private static UsuarioDTO userOnline=USER_ONLINE;
	
	private static GestorUsuarios instancia =null;
	
	public static GestorUsuarios getInstance() {
		if(instancia == null) instancia=new GestorUsuarios();
		return instancia;
	}
	private GestorUsuarios() {
		cargarListaUsuarios();
	}
	
	public ArrayList <UsuarioDTO> getList() {
		return listaUsers;
	}
	
	public void recargarUserOnline(UsuarioDTO user) {
		userOnline = user;
	}
	
	private void cargarListaUsuarios() {
		listaUsers = udao.listarTodos();
		cargarListaIncidencias();
	}
	
	public UsuarioDTO getUserById(String idUsuario) {
		UsuarioDTO udto = null;
		for(UsuarioDTO temp : listaUsers) {
			if(idUsuario.equals(temp.getIdUsuario())) return temp;
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
	
	public boolean autorizarProveedor(){
		if(tienePermisos()==4) return true;
		return false;
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
		}else if(userOnline instanceof ProveedorDTO) {
			return 4;
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
		if(udao.promocionar(user)) {
			cargarListaUsuarios();
			return true;			
		}
		return false;
	}
	
	public boolean degradarUsuario(UsuarioDTO user) {
		if (user instanceof AdministradorDTO && comprobarUltimoAdmin() || !autorizarAdmin()) return false;
		ArrayList<IncidenciaDTO>incidencias=user.getIncidencias();
		if(udao.degradar(user)) {
			cargarListaUsuarios();
			if(user instanceof TecnicoDTO)
				traspasarIncidencias(incidencias);
			return true;			
		}
		return false;
	}
	
	public boolean borrarUsuario(String idUsuario) {
		UsuarioDTO user = getUserById(idUsuario);
		ArrayList<IncidenciaDTO>incidencias=user.getIncidencias();
		if(user instanceof AdministradorDTO && comprobarUltimoAdmin() || !autorizarAdmin() ) return false;
		if(udao.borrar(idUsuario)) {
			listaUsers.remove(user);
			if(user instanceof TecnicoDTO)
				traspasarIncidencias(incidencias);
			return true;
		}
		return false;
	}
	
	public boolean altaUsuario(UsuarioDTO user) {
		try {
			if(tienePermisos()==3) {
				if(udao.insertar(user)) cargarListaUsuarios();
				return true;
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}	
		return false;
	}
	
	public boolean modificarUsuario(UsuarioDTO user) {
		UsuarioDTO usuario = getUserById(user.getIdUsuario());
		user.setEquipo(usuario.getEquipo());
		user.setIncidencias(usuario.getIncidencias());
		if(tienePermisos()==3) {
			if(udao.actualizar(user)) listaUsers.set(listaUsers.indexOf(usuario), user);	
			return true;
		}
		return false;
	}
	
	public boolean asignarEquipo(String idUsuario, int idEquipo) {
		if(udao.asignarEquipo(idUsuario,idEquipo)){
			getUserById(idUsuario).setEquipo(ge.getEquipoById(idEquipo));
			return true;
		}
		return false;
	}
	public boolean desasignarEquipo(String idUsuario) {
		if(udao.asignarEquipo(idUsuario)){
			getUserById(idUsuario).setEquipo(null);
			return true;
		}
		return false;
	}
	public void cargarListaIncidencias() {
		for(UsuarioDTO udto : listaUsers) {
			 udto.setIncidencias(gs.getListaAsignadasA(udto.getIdUsuario()));
		}
	}
	
	public void traspasarIncidencias(ArrayList<IncidenciaDTO>incidencias) {
		UsuarioDTO udto = getTecnicoMenosIncidencias();
		udto.setIncidencias(incidencias);
	}
	
	public UsuarioDTO getTecnicoMenosIncidencias() {
		UsuarioDTO userMenosInci = listaUsers.get(0);
		for(UsuarioDTO udto : listaUsers) {
			if(udto instanceof TecnicoDTO && udto.getIncidencias().size()<userMenosInci.getIncidencias().size()) {
				userMenosInci = udto;
			}
		}
		return userMenosInci;
	}
	
	public ArrayList<UsuarioDTO> getListaUsuariosAsignados(int idEquipo){
		ArrayList<UsuarioDTO> listaAsignados = new ArrayList<UsuarioDTO>();
		for(UsuarioDTO user : listaUsers) {
			if(user.getEquipo()!=null) 
				if(user.getEquipo().getIdEquipo()==idEquipo)listaAsignados.add(user);
		}
		return listaAsignados;
	}
}
