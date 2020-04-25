package DTO;

import java.util.ArrayList;

public class StockDTO{
	private int idStock;
	private int unidades;
	private int limiteInferior;
	private String descripcion;
	
	private ArrayList<ComponenteDTO> componentes;
	private ArrayList<EquipoDTO> equipos;
	
	public StockDTO(int idStock, int unidades, int limiteInferior, String descripcion) {
		super();
		this.idStock = idStock;
		this.unidades = unidades;
		this.limiteInferior = limiteInferior;
		this.descripcion = descripcion;
	}
	
	public StockDTO(int unidades, int limiteInferior, String descripcion) {
		super();
		this.unidades = unidades;
		this.limiteInferior = limiteInferior;
		this.descripcion = descripcion;
	}
	
	public int getIdStock() {
		return idStock;
	}
	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public int getLimiteInferior() {
		return limiteInferior;
	}
	public void setLimiteInferior(int limiteInferior) {
		this.limiteInferior = limiteInferior;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ArrayList<ComponenteDTO> getComponentes() {
		return componentes;
	}

	public void setComponentes(ArrayList<ComponenteDTO> componentes) {
		this.componentes = componentes;
	}

	public ArrayList<EquipoDTO> getEquipos() {
		return equipos;
	}

	public void setEquipos(ArrayList<EquipoDTO> equipos) {
		this.equipos = equipos;
	}
}