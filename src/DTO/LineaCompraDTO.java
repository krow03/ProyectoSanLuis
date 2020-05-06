package DTO;

import gestores.GestorStock;

public class LineaCompraDTO {
	private GestorStock gs = GestorStock.getInstance();
	private StockDTO stock;
	private int unidades;
	private double precio;
	
	public LineaCompraDTO(int idStock, int unidades, double precio) {
		super();
		this.stock = gs.getStockById(idStock);
		this.unidades = unidades;
		this.precio=precio;
	}
	
	public StockDTO getStock() {
		return stock;
	}
	public void setStock(StockDTO idStock) {
		this.stock = idStock;
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
