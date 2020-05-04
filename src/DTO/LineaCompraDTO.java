package DTO;

public class LineaCompraDTO {
	private int idStock;
	private int idCompra;
	private int unidades;
	private double precio;
	
	
	public LineaCompraDTO(int idStock, int idCompra, int unidades, double precio) {
		super();
		this.idStock = idStock;
		this.idCompra = idCompra;
		this.unidades = unidades;
		this.precio=precio;
	}
	
	public int getIdStock() {
		return idStock;
	}
	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}
	public int getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
}
