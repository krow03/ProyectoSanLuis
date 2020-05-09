package DTO;

import java.util.ArrayList;

import gestores.GestorProveedores;
/*
 * Estados posibles
 * 1 = aceptada
 * 2 = denegada
 * 3 = pendiente
 * */
public class CompraDTO {
	private GestorProveedores gp = GestorProveedores.getInstance();
	private int idCompra;
	private ProveedorDTO proveedor;
	private String fechaCompra;
	private double precioTotal;
	private int estado;
	private ArrayList<LineaCompraDTO> listaProdPorCompra= new ArrayList<LineaCompraDTO>();
	
	public CompraDTO(int idProveedor, String fechaCompra, double precioTotal,int estado) {
		super();
		this.proveedor = gp.getProvById(idProveedor);
		this.fechaCompra = fechaCompra;
		this.precioTotal = precioTotal;
		this.estado = estado;
	}

	public CompraDTO(int idCompra, int idProveedor, String fechaCompra, double precioTotal,ArrayList<LineaCompraDTO> listaProdPorCompra,int estado) {
		super();
		this.idCompra = idCompra;
		this.proveedor = gp.getProvById(idProveedor);
		this.fechaCompra = fechaCompra;
		this.precioTotal = precioTotal;
		this.listaProdPorCompra = listaProdPorCompra;
		this.estado = estado;
	}
	
	public CompraDTO(int idCompra, int idProveedor, String fechaCompra, double precioTotal,int estado) {
		super();
		this.idCompra = idCompra;
		this.proveedor = gp.getProvById(idProveedor);;
		this.fechaCompra = fechaCompra;
		this.precioTotal = precioTotal;
		this.estado = estado;
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
	
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public ArrayList<LineaCompraDTO> getListaProdPorCompra() {
		return listaProdPorCompra;
	}

	public void setListaProdPorCompra(ArrayList<LineaCompraDTO> listaProdPorCompra) {
		this.listaProdPorCompra = listaProdPorCompra;
	}
	
}
