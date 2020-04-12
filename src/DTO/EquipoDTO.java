package DTO;

import java.util.ArrayList;

public class EquipoDTO
{
	private int idEquipo;
	private String ipEquipo;
    private String nombre;
    private int discoDuro;
    private int ram;
    private int idAula;
    private ArrayList<ComponenteDTO> componentes;
    
    //campo calculado
    private double disponibleHDD;
    
	public EquipoDTO(int idEquipo,String ipEquipo, String nombre, int discoDuro, int ram, int idAula) {
		super();
		this.idEquipo = idEquipo;
		this.ipEquipo = ipEquipo;
		this.nombre = nombre;
		this.discoDuro = discoDuro;
		this.ram = ram;
		this.idAula = idAula;
	}
    
	public EquipoDTO(String ipEquipo, String nombre, int discoDuro, int ram, int idAula) {
		super();
		this.ipEquipo = ipEquipo;
		this.nombre = nombre;
		this.discoDuro = discoDuro;
		this.ram = ram;
		this.idAula = idAula;
	}
	public EquipoDTO(String ipEquipo, String nombre, int discoDuro, int ram) {
		super();
		this.ipEquipo = ipEquipo;
		this.nombre = nombre;
		this.discoDuro = discoDuro;
		this.ram = ram;
	}
	
	public EquipoDTO(int idEquipo,String ipEquipo, String nombre, int discoDuro, int ram) {
		super();
		this.idEquipo = idEquipo;
		this.ipEquipo = ipEquipo;
		this.nombre = nombre;
		this.discoDuro = discoDuro;
		this.ram = ram;
	}
	
	public EquipoDTO(String nombre, String ipEquipo) {
		this.nombre = nombre;
		this.ipEquipo = ipEquipo;
		
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
	public double getDisponibleHDD() {
		return disponibleHDD;
	}
	public void setDisponibleHDD(double disponibleHDD) {
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
	public ArrayList<ComponenteDTO> getComponentes() {
		return componentes;
	}
	public void setComponentes(ArrayList<ComponenteDTO> componentes) {
		this.componentes = componentes;
	}

	public void calcularDisponibleHD(){
		double disponible = 0;
		for(ComponenteDTO c : componentes) {
			if(c instanceof SoftwareDTO) {
				SoftwareDTO s = (SoftwareDTO) c;
				disponible += s.getPeso();
			}
		}
		 
		this.setDisponibleHDD(this.discoDuro- disponible);
	}
    
}
