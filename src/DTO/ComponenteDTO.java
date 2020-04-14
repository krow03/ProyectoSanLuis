package DTO;

public class ComponenteDTO
{
	private int idComponente;
	private int idEquipo;
	private int idStock;
    private String descripcion;
    private int udsDisponibles;
    
	public ComponenteDTO(int idComponente, int idEquipo, int idStock, String descripcion) {
		super();
		this.idComponente = idComponente;
		this.idEquipo = idEquipo;
		this.idStock = idStock;
		this.descripcion = descripcion;
	}

	public int getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public int getIdStock() {
		return idStock;
	}

	public void setIdStock(int idStock) {
		this.idStock = idStock;
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
