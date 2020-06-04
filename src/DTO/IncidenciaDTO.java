package DTO;



public class IncidenciaDTO
{
    private int codigo;
    private UsuarioDTO realizadaPor;
    private int idEquipo;
    private String fechaSol;
    private String fechaFin;
    private String estado;
    private String descripcion;
    private int prioridad;
    
	public IncidenciaDTO(int codigo, String idAsignadaA, int idEquipo, String fechaSol,
			String fechaFin, String estado, String descripcion, int prioridad) {
		super();
		this.codigo = codigo;
		this.idEquipo = idEquipo;
		this.fechaSol = fechaSol;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.descripcion = descripcion;
		this.prioridad = prioridad;
	}
	
	public IncidenciaDTO(int codigo, UsuarioDTO realizadaPor, String idAsignadaA, int idEquipo, String fechaSol,
			String fechaFin, String estado, String descripcion, int prioridad) {
		super();
		this.codigo = codigo;
		this.realizadaPor = realizadaPor;
		this.idEquipo = idEquipo;
		this.fechaSol = fechaSol;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.descripcion = descripcion;
		this.prioridad = prioridad;
	}
	
	public IncidenciaDTO( int idEquipo, String fechaSol, String estado, String descripcion) {
		super();
		this.idEquipo = idEquipo;
		this.fechaSol = fechaSol;
		this.estado = estado;
		this.descripcion = descripcion;
	}

	public IncidenciaDTO( UsuarioDTO realizadaPor, int idEquipo, String fechaSol,String estado, String descripcion) {
		super();
		this.realizadaPor = realizadaPor;
		this.idEquipo = idEquipo;
		this.fechaSol = fechaSol;
		this.estado = estado;
		this.descripcion = descripcion;
		
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public UsuarioDTO getIdRealizadaPor() {
		return realizadaPor;
	}
	public void setIdRealizadaPor(UsuarioDTO idRealizadaPor) {
		this.realizadaPor = idRealizadaPor;
	}

	public int getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}
	public String getFechaSol() {
		return fechaSol;
	}
	public void setFechaSol(String fechaSol) {
		this.fechaSol = fechaSol;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
}
