package DTO;

public class HistoricoComprasDTO {
	private int idStock;
	private int idCompra;
	private int unidades;
	
	public HistoricoComprasDTO(int idStock, int idCompra, int unidades) {
		super();
		this.idStock = idStock;
		this.idCompra = idCompra;
		this.unidades = unidades;
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
	
	
}
