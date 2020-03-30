package DTO;

import java.util.ArrayList;

public class CentroEstudioDTO {
	private int idCentro;
	private String nombreCentro;
	private String ciudadCentro;
	
	private ArrayList<AulaDTO> listaAulas;

	
	
	public CentroEstudioDTO(int idCentro, String nombreCentro, String ciudadCentro) {
		super();
		this.idCentro = idCentro;
		this.nombreCentro = nombreCentro;
		this.ciudadCentro = ciudadCentro;
	}

	public int getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(int idCentro) {
		this.idCentro = idCentro;
	}

	public String getNombreCentro() {
		return nombreCentro;
	}

	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}

	public String getCiudadCentro() {
		return ciudadCentro;
	}

	public void setCiudadCentro(String ciudadCentro) {
		this.ciudadCentro = ciudadCentro;
	}

	public ArrayList<AulaDTO> getListaAulas() {
		return listaAulas;
	}

	public void setListaAulas(ArrayList<AulaDTO> listaAulas) {
		this.listaAulas = listaAulas;
	}
}
