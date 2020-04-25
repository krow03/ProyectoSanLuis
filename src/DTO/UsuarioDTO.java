package DTO;

import java.util.ArrayList;

import Gestores.GestorEquipos;
/*
 * Rol Usuario = 1
 * Rol Tecnico = 2
 * Rol Admin = 3
 * */
public class UsuarioDTO
{
	private GestorEquipos ge= new GestorEquipos();
	private String idUsuario;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String direccion;
    private String userName;
    private String email;
    private EquipoDTO equipo;
    private String pass;
    private ArrayList<IncidenciaDTO> incidenciasRealizadas;
	

	public UsuarioDTO(String idUsuario, String userName, String email, int idEquipo, String pass, String nombre,String apellidos,String direccion,String telefono) {
		super();
		this.idUsuario = idUsuario;
		this.userName = userName;
		this.email = email;
		this.equipo = ge.getEquipoById(idEquipo);
		this.pass = pass;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.direccion=direccion;
		this.telefono=telefono;
    }
	public UsuarioDTO(String idUsuario, String userName, String email, String pass, String nombre,String apellidos,String direccion,String telefono) {
		super();
		this.idUsuario = idUsuario;
		this.userName = userName;
		this.email = email;
		this.pass = pass;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.direccion=direccion;
		this.telefono=telefono;
    }
    public UsuarioDTO(String idUsuario, String userName, String email, int idEquipo) {
		super();
		this.idUsuario = idUsuario;
		this.userName = userName;
		this.email = email;
		this.equipo = ge.getEquipoById(idEquipo);
	}
    
    public UsuarioDTO() {
    	
    }

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EquipoDTO getEquipo() {
		return equipo;
	}

	public void setIdEquipo(EquipoDTO equipo) {
		this.equipo = equipo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public ArrayList<IncidenciaDTO> getIncidencias() {
		return incidenciasRealizadas;
	}

	public void setIncidencias(ArrayList<IncidenciaDTO> incidencias) {
		this.incidenciasRealizadas = incidencias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
    
}