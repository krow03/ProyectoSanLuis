 package DTO;

import Gestores.GestorComponentes;

public class SolicitudDTO extends IncidenciaDTO
{
    private ComponenteDTO componente;
    private GestorComponentes gc = new GestorComponentes();
	public SolicitudDTO(int codigo, String idRealizadaPor, String idAsignadaA, int idEquipo, String fechaSol,
			String fechaFin, String estado, String descripcion, int prioridad) {
		super(codigo, idRealizadaPor, idAsignadaA, idEquipo, fechaSol, fechaFin, estado, descripcion, prioridad);
		// TODO Auto-generated constructor stub
	}

	public SolicitudDTO(int codigo, String idRealizadaPor, String idAsignadaA, int idEquipo, String fechaSol,
			String fechaFin, String estado, String descripcion, int prioridad, int idComponente) {
		super(codigo, idRealizadaPor, idAsignadaA, idEquipo, fechaSol, fechaFin, estado, descripcion, prioridad);
		this.componente = gc.getComponente(idComponente);
	}
	


	public SolicitudDTO(String idRealizadaPor, int idEquipo, String fechaSol,
			String estado, String descripcion, int idComponente) {
		super(idRealizadaPor, idEquipo, fechaSol, estado, descripcion);
		this.componente = gc.getComponente(idComponente);
	}

	public ComponenteDTO getComponente() {
		return componente;
	}

	public void setComponente(ComponenteDTO componente) {
		this.componente = componente;
	}

}
