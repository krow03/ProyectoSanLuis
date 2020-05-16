package DTO;

import java.util.ArrayList;

public class ComponenteDTO
{
	private int idComponente;
    private String descripcion;
    private int udsDisponibles;
    private ArrayList<RequisitoDTO> requisitos= new ArrayList<RequisitoDTO>();
	public ComponenteDTO(int idComponente, String descripcion) {
		super();
		this.idComponente = idComponente;
		this.descripcion = descripcion;
	}

	public int getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getUdsDisponibles() {
		return udsDisponibles;
	}

	public void setUdsDisponibles(int udsDisponibles) {
		this.udsDisponibles = udsDisponibles;
	}

	public ArrayList<RequisitoDTO> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(ArrayList<RequisitoDTO> requisitos) {
		this.requisitos = requisitos;
	}
    
} 
