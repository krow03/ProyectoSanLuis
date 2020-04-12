package DTO;

public class SolicitudDTO extends IncidenciaDTO
{
    private ComponenteDTO componente;
    private String tipo;
	

	public SolicitudDTO(int codigo, String idRealizadaPor, String idAsignadaA, int idEquipo, String fechaSol,
			String fechaFin, String estado, String descripcion, int prioridad) {
		super(codigo, idRealizadaPor, idAsignadaA, idEquipo, fechaSol, fechaFin, estado, descripcion, prioridad);
		// TODO Auto-generated constructor stub
	}

	public SolicitudDTO(int codigo, String idRealizadaPor, String idAsignadaA, int idEquipo, String fechaSol,
			String fechaFin, String estado, String descripcion, int prioridad, ComponenteDTO componente,String tipo) {
		super(codigo, idRealizadaPor, idAsignadaA, idEquipo, fechaSol, fechaFin, estado, descripcion, prioridad);
		this.componente = componente;
		this.tipo = tipo;
	}

	public ComponenteDTO getComponente() {
		return componente;
	}

	public void setComponente(ComponenteDTO componente) {
		this.componente = componente;
	}
    
    
}
