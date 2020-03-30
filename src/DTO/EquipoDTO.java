package DTO;

public class EquipoDTO
{
	private int idEquipo;
	private String ipEquipo;
    private String nombre;
    private int discoDuro;
    private int disponibleHDD;
    private int ram;
    private int idAula;
    private ComponenteDTO[] componentes;
    
	public EquipoDTO(int idEquipo,String ipEquipo, String nombre, int discoDuro, int disponibleHDD, int ram, int idAula) {
		super();
		this.idEquipo = idEquipo;
		this.ipEquipo = ipEquipo;
		this.nombre = nombre;
		this.discoDuro = discoDuro;
		this.disponibleHDD = disponibleHDD;
		this.ram = ram;
		this.idAula = idAula;
	}
    
    
	public int getIdEquipo() {
		return idEquipo;
	}


	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}


	public String getIpEquipo() {
		return ipEquipo;
	}
	public void setIpEquipo(String ipEquipo) {
		this.ipEquipo = ipEquipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDiscoDuro() {
		return discoDuro;
	}
	public void setDiscoDuro(int discoDuro) {
		this.discoDuro = discoDuro;
	}
	public int getDisponibleHDD() {
		return disponibleHDD;
	}
	public void setDisponibleHDD(int disponibleHDD) {
		this.disponibleHDD = disponibleHDD;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	public int getIdAula() {
		return idAula;
	}
	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}
	public ComponenteDTO[] getComponentes() {
		return componentes;
	}
	public void setComponentes(ComponenteDTO[] componentes) {
		this.componentes = componentes;
	}

    
}
