package DTO;

import java.util.ArrayList;

import gestores.GestorProveedores;

public class CompraDTO {
	private GestorProveedores gp = GestorProveedores.getInstance();
	private int idCompra;
	private ProveedorDTO proveedor;
	private String fechaCompra;
	private double precioTotal;
	private ArrayList<LineaCompraDTO> listaProdPorCompra= new ArrayList<LineaCompraDTO>();
	
	
	public CompraDTO(int idProveedor, String fechaCompra, double precioTotal) {
		super();
		this.proveedor = gp.getProvById(idProveedor);
		this.fechaCompra = fechaCompra;
		this.precioTotal = precioTotal;
	}

	public CompraDTO(int idCompra, int idProveedor, String fechaCompra, double precioTotal,ArrayList<LineaCompraDTO> listaProdPorCompra) {
		super();
		this.idCompra = idCompra;
		this.proveedor = gp.getProvById(idProveedor);
		this.fechaCompra = fechaCompra;
		this.precioTotal = precioTotal;
		this.listaProdPorCompra = listaProdPorCompra;
	}
	
	public CompraDTO(int idCompra, int idProveedor, String fechaCompra, double precioTotal) {
		super();
		this.idCompra = idCompra;
		this.proveedor = gp.getProvById(idProveedor);;
		this.fechaCompra = fechaCompra;
		this.precioTotal = precioTotal;
	}
	
	public int getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	public ProveedorDTO getProveedor() {
		return proveedor;
	}
	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}
	public String getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public ArrayList<LineaCompraDTO> getListaProdPorCompra() {
		return listaProdPorCompra;
	}

	public void setListaProdPorCompra(ArrayList<LineaCompraDTO> listaProdPorCompra) {
		this.listaProdPorCompra = listaProdPorCompra;
	}
	
}
