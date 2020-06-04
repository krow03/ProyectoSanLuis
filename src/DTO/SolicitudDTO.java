 package DTO;

import gestores.GestorComponentes;

public class SolicitudDTO extends IncidenciaDTO
{
    private ComponenteDTO componente;
    private GestorComponentes gc = GestorComponentes.getInstance();
	public SolicitudDTO(int codigo, String idRealizadaPor, String idAsignadaA, int idEquipo, String fechaSol,
			String fechaFin, String estado, String descripcion, int prioridad) {
		super(codigo, idAsignadaA, idEquipo, fechaSol, fechaFin, estado, descripcion, prioridad);
		// TODO Auto-generated constructor stub
	}
	public SolicitudDTO(int codigo, String idAsignadaA, int idEquipo, String fechaSol,
			String fechaFin, String estado, String descripcion, int prioridad) {
		super(codigo, idAsignadaA, idEquipo, fechaSol, fechaFin, estado, descripcion, prioridad);
		// TODO Auto-generated constructor stub
	}
	public SolicitudDTO(int codigo,  String idAsignadaA, int idEquipo, String fechaSol,
			String fechaFin, String estado, String descripcion, int prioridad, int idComponente) {
		super(codigo, idAsignadaA, idEquipo, fechaSol, fechaFin, estado, descripcion, prioridad);
		this.componente = gc.getComponente(idComponente);
	}
	


	public SolicitudDTO( int idEquipo, String fechaSol,String estado, String descripcion, int idComponente) {
		super( idEquipo, fechaSol, estado, descripcion);
		this.componente = gc.getComponente(idComponente);
	}
	public SolicitudDTO(UsuarioDTO realizadaPor, int idEquipo, String fechaSol,String estado, String descripcion, int idComponente) {
		super( realizadaPor,idEquipo, fechaSol,estado, descripcion);
		this.componente = gc.getComponente(idComponente);
	}
	public ComponenteDTO getComponente() {
		return componente;
	}

	public void setComponente(ComponenteDTO componente) {
		this.componente = componente;
	}

}
