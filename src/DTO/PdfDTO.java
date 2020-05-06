package DTO;

public class PdfDTO {
	private String dniProveedor;
	private String nombreFichero;
	private String contenido;
	
	public PdfDTO(String dniProveedor, String nombreFichero, String contenido) {
		super();
		this.dniProveedor = dniProveedor;
		this.nombreFichero = nombreFichero;
		this.contenido = contenido;
	}
	
	public String getDniProveedor() {
		return dniProveedor;
	}

	public void setDniProveedor(String dniProveedor) {
		this.dniProveedor = dniProveedor;
	}

	public String getNombreFichero() {
		return nombreFichero;
	}

	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
}
