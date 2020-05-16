package DTO;

public class RequisitoDTO {
	private int idReq;
	private String descripcion;
	private String clave;
	
	public RequisitoDTO(int idReq, String descripcion, String clave) {
		super();
		this.idReq = idReq;
		this.descripcion = descripcion;
		this.clave = clave;
	}

	public int getIdReq() {
		return idReq;
	}

	public void setIdReq(int idReq) {
		this.idReq = idReq;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
}
