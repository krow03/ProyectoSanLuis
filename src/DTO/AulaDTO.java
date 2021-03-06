package DTO;

import java.util.ArrayList;

public class AulaDTO
{
	private int idAula;
    private String rangoIps;
    private String nombre;
    private String descripcion;
    private int capacidad;
	//campo calculado
    private boolean disponibilidad;
    private ArrayList<EquipoDTO> equipos;

	public AulaDTO(int idAula, String rangoIps, String nombre, String descripcion, int capacidad) {
		super();
		this.idAula = idAula;
		this.rangoIps = rangoIps;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.capacidad = capacidad;
	}

	public int getIdAula() {
		return idAula;
	}

	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}

	public String getRangoIps() {
		return rangoIps;
	}

	public void setRangoIps(String rangoIps) {
		this.rangoIps = rangoIps;
	}

	public String getNombre() {
		return nombre;
	}
	public AulaDTO(String rangoIps, String nombre, String descripcion, int capacidad) {
        super();
        this.rangoIps = rangoIps;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
    }
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public ArrayList<EquipoDTO> getEquipos() {
		return equipos;
	}

	public void setEquipos(ArrayList<EquipoDTO> equipos) {
		this.equipos = equipos;
	}

	
    
   
}