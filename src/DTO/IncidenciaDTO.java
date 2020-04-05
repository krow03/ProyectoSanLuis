package DTO;

public class IncidenciaDTO
{
    private int codigo;
    private String idRealizadaPor;
    private String idAsignadaA;
    private int idEquipo;
    private String fechaSol;
    private String fechaFin;
    private String estado;
    private String descripcion;
    private int prioridad;
	public IncidenciaDTO(int codigo, String idRealizadaPor, String idAsignadaA, int idEquipo, String fechaSol,
			String fechaFin, String estado, String descripcion, int prioridad) {
		super();
		this.codigo = codigo;
		this.idRealizadaPor = idRealizadaPor;
		this.idAsignadaA = idAsignadaA;
		this.idEquipo = idEquipo;
		this.fechaSol = fechaSol;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.descripcion = descripcion;
		this.prioridad = prioridad;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getIdRealizadaPor() {
		return idRealizadaPor;
	}
	public void setIdRealizadaPor(String idRealizadaPor) {
		this.idRealizadaPor = idRealizadaPor;
	}
	public String getIdAsignadaA() {
		return idAsignadaA;
	}
	public void setIdAsignadaA(String idAsignadaA) {
		this.idAsignadaA = idAsignadaA;
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
