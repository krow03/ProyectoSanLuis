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

// TODO: Auto-generated Javadoc
/**
 * The Class GestorUsuarios.
 */
public class GestorUsuarios {
	
	/** The gs. */
	private GestorSolicitudes gs = GestorSolicitudes.getInstance();
	
	/** The ge. */
	private GestorEquipos ge = GestorEquipos.getInstance();
	

	
	/** The udao. */
	private UsuarioDAO udao = new UsuarioDAO();
	
	/** The lista users. */
	private static ArrayList <UsuarioDTO> listaUsers;
	
	/** The user online. */
	private static UsuarioDTO userOnline;
	
	/** The instancia. */
	private static GestorUsuarios instancia =null;
	
	/**
	 * Gets the single instance of GestorUsuarios.
	 *
	 * @return single instance of GestorUsuarios
	 */
	public static GestorUsuarios getInstance() {
		if(instancia == null) instancia=new GestorUsuarios();
		return instancia;
	}
	
	/**
	 * Instantiates a new gestor usuarios.
	 */
	private GestorUsuarios() {
		cargarListaUsuarios();
	}
	
	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public ArrayList <UsuarioDTO> getList() {
		return listaUsers;
	}
	
	/**
	 * Recargar user online.
	 *
	 * @param user the user
	 */
	public void recargarUserOnline(UsuarioDTO user) {
		userOnline = user;
	}
	
	/**
	 * Cargar lista usuarios.
	 */
	private void cargarListaUsuarios() {
		listaUsers = udao.listarTodos();
		cargarListaIncidencias();
	}
	
	/**
	 * Gets the user by id.
	 *
	 * @param idUsuario the id usuario
	 * @return the user by id
	 */
	public UsuarioDTO getUserById(String idUsuario) {
		UsuarioDTO udto = null;
		for(UsuarioDTO temp : listaUsers) {
			if(idUsuario.equals(temp.getIdUsuario())) return temp;
		}
		return udto;
	}
	
	/**
	 * Gets the user online.
	 *
	 * @return the user online
	 */
	public UsuarioDTO getUserOnline() {
		return userOnline;
	}

	/**
	 * Comprobar ultimo admin.
	 *
	 * @return true, if successful
	 */
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
	
	/**
	 * Autorizar proveedor.
	 *
	 * @return true, if successful
	 */
	public boolean autorizarProveedor(){
		if(tienePermisos()==4) return true;
		return false;
	}
	
	/**
	 * Autorizar admin.
	 *
	 * @return true, if successful
	 */
	public boolean autorizarAdmin() {
		if(tienePermisos()==3) return true;
		return false;
	}
	
	/**
	 * Autorizar tecnico.
	 *
	 * @return true, if successful
	 */
	public boolean autorizarTecnico() {
		if(tienePermisos()==2) return true;
		return false;
	}
	
	/**
	 * Tiene permisos.
	 * @author Pablo Barreda
	 * @author Daniel Aparicio
	 * Este metodo devuelve un valor int en funcion de la instancia del objeto
	 * para darle permisos dentro de la aplicacion a cada rol de usuario 
	 * @return the int
	 */
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
	
	/**
	 * Login.
	 *
	 * @param user the user
	 * @param pass the pass
	 * @return true, if successful
	 */
	public boolean  login(String user,String pass){
		userOnline = udao.login(user, pass);
		if(userOnline instanceof TecnicoDTO)((TecnicoDTO)userOnline).setIncidenciasAsignadas(gs.getIncidenciasTecnico(userOnline.getIdUsuario()));
		if(userOnline!=null) return true;
		
		return false;
	}
	
	/**
	 * Log out.
	 */
	public void logOut() {
		userOnline = null;
	}
	
	/**
	 * Promocionar usuario.
	 *
	 * @param user the user
	 * @return true, if successful
	 */
	public boolean promocionarUsuario(UsuarioDTO user) {
		if (user instanceof AdministradorDTO || !autorizarAdmin()) return false;
		if(udao.promocionar(user)) {
			cargarListaUsuarios();
			return true;			
		}
		return false;
	}
	
	/**
	 * Degradar usuario.
	 *
	 * @param user the user
	 * @return true, if successful
	 */
	public boolean degradarUsuario(UsuarioDTO user) {
		if (user instanceof AdministradorDTO && comprobarUltimoAdmin() || !autorizarAdmin()) return false;
		ArrayList<IncidenciaDTO>incidencias=((TecnicoDTO)user).getIncidenciasAsignadas();
		if(udao.degradar(user)) {
			cargarListaUsuarios();
			if(user instanceof TecnicoDTO)
				traspasarIncidencias(incidencias);
			return true;			
		}
		return false;
	}
	
	/**
	 * Borrar usuario.
	 *
	 * @param idUsuario the id usuario
	 * @return true, if successful
	 */
	public boolean borrarUsuario(String idUsuario) {
		UsuarioDTO user = getUserById(idUsuario);
		ArrayList<IncidenciaDTO>incidencias=null;
		if(user instanceof TecnicoDTO)incidencias=((TecnicoDTO)user).getIncidenciasAsignadas();
		if(user instanceof AdministradorDTO && comprobarUltimoAdmin() || !autorizarAdmin() ) return false;
		if(udao.borrar(idUsuario)) {
			listaUsers.remove(user);
			if(user instanceof TecnicoDTO)
				traspasarIncidencias(incidencias);
			return true;
		}
		return false;
	}
	
	/**
	 * Alta usuario.
	 *
	 * @param user the user
	 * @return true, if successful
	 */
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
	
	/**
	 * Modificar usuario.
	 *
	 * @param user the user
	 * @return true, if successful
	 */
	public boolean modificarUsuario(UsuarioDTO user) {
		UsuarioDTO usuario = getUserById(user.getIdUsuario());
		user.setEquipo(usuario.getEquipo());
		if(tienePermisos()==3) {
			if(udao.actualizar(user)) listaUsers.set(listaUsers.indexOf(usuario), user);	
			return true;
		}
		return false;
	}
	public boolean modificarPerfil(UsuarioDTO user) {
		UsuarioDTO usuario = getUserOnline();
		user.setEquipo(usuario.getEquipo());

		if(udao.actualizar(user)) { 
			listaUsers.set(listaUsers.indexOf(usuario), user);	
			userOnline = user;
			return true;
		}
		return false;
	}
	/**
	 * Asignar equipo.
	 *
	 * @param idUsuario the id usuario
	 * @param idEquipo the id equipo
	 * @return true, if successful
	 */
	public boolean asignarEquipo(String idUsuario, int idEquipo) {
		if(udao.asignarEquipo(idUsuario,idEquipo)){
			getUserById(idUsuario).setEquipo(ge.getEquipoById(idEquipo));
			return true;
		}
		return false;
	}
	
	/**
	 * Desasignar equipo.
	 *
	 * @param idUsuario the id usuario
	 * @return true, if successful
	 */
	public boolean desasignarEquipo(String idUsuario) {
		if(udao.asignarEquipo(idUsuario)){
			getUserById(idUsuario).setEquipo(null);
			return true;
		}
		return false;
	}
	
	/**
	 * Cargar lista incidencias.
	 */
	public void cargarListaIncidencias() {
		for(UsuarioDTO udto : listaUsers) {
			if(udto instanceof TecnicoDTO) {
			 ((TecnicoDTO) udto).setIncidenciasAsignadas(gs.getIncidenciasTecnico(udto.getIdUsuario()));
			}
		}
	}
	
	/**
	 * Traspasar incidencias.
	 * @author Pablo Barreda
	 * @author Daniel Aparicio
	 * Este metodo se encarga de traspasar las incidencias de un tecnico que este siendo borrado 
	 * o degradado en el sistema a otro tecnico, siendo este el devuelto por el metodo
	 * getTecnicoMenosIncidencias
	 * @param incidencias the incidencias
	 */
	public void traspasarIncidencias(ArrayList<IncidenciaDTO>incidencias) {
		UsuarioDTO udto = getTecnicoMenosIncidencias();
		((TecnicoDTO)udto).setIncidenciasAsignadas(incidencias);
	}
	
	/**
	 * Gets the tecnico menos incidencias.
	 * @author Pablo Barreda
	 * @author Daniel Aparicio
	 * Este metodo se encarga de buscar el tecnico con menos incidencias asignadas
	 * @return the tecnico menos incidencias
	 */
	public UsuarioDTO getTecnicoMenosIncidencias() {
		UsuarioDTO userMenosInci = listaUsers.get(0);
		for(UsuarioDTO udto : listaUsers) {
			if(udto instanceof TecnicoDTO && ((TecnicoDTO)udto).getIncidenciasAsignadas().size()<((TecnicoDTO)userMenosInci).getIncidenciasAsignadas().size()) {
				userMenosInci = udto;
			}
		}
		return userMenosInci;
	}
	
	/**
	 * Gets the lista usuarios asignados.
	 * @author Pablo Barreda
	 * @author Daniel Aparicio
	 * Este metodo saca un listado de todos los usuarios asignados a un equipo determinado
	 * para ello utiliza el atributo equipo de cada usuario cargado en la lista de usuarios de esta clase
	 * @param idEquipo the id equipo
	 * @return the lista usuarios asignados
	 */
	public ArrayList<UsuarioDTO> getListaUsuariosAsignados(int idEquipo){
		ArrayList<UsuarioDTO> listaAsignados = new ArrayList<UsuarioDTO>();
		for(UsuarioDTO user : listaUsers) {
			if(user.getEquipo()!=null) 
				if(user.getEquipo().getIdEquipo()==idEquipo)listaAsignados.add(user);
		}
		return listaAsignados;
	}
}
