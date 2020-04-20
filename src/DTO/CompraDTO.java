package DTO;

import java.util.ArrayList;

public class CompraDTO {
	private int idCompra;
	private int idProveedor;
	private String fechaCompra;
	private ArrayList<HistoricoComprasDTO> listaProdPorCompra= new ArrayList<HistoricoComprasDTO>();
	
	
	public CompraDTO(int idProveedor, String fechaCompra) {
		super();
		this.idProveedor = idProveedor;
		this.fechaCompra = fechaCompra;
	}

	public CompraDTO(int idCompra, int idProveedor, String fechaCompra) {
		super();
		this.idCompra = idCompra;
		this.idProveedor = idProveedor;
		this.fechaCompra = fechaCompra;
	}
	
	public int getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public ArrayList<HistoricoComprasDTO> getListaProdPorCompra() {
		return listaProdPorCompra;
	}

	public void setListaProdPorCompra(ArrayList<HistoricoComprasDTO> listaProdPorCompra) {
		this.listaProdPorCompra = listaProdPorCompra;
	}
	
}
