package DTO;

public class ComponenteDTO
{
	private int idComponente;
    private String descripcion;
    private int udsDisponibles;
    
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
    
} 
