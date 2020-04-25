package DTO;

import java.util.ArrayList;

public class ProveedorDTO extends UsuarioDTO
{
	private int idProveedor;
    private String cif;
    private String nombre;
    private String email;
    private String direccion;
    private String telefono;
    
    private ArrayList <StockDTO> listaStock;
    private ArrayList<ComponenteDTO>listaProductos;

	public ProveedorDTO(int idProveedor, String cif, String nombre, String email, String direccion, String telefono) {
		super();
		this.idProveedor = idProveedor;
		this.cif = cif;
		this.nombre = nombre;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public ArrayList<StockDTO> getListaStock() {
		return listaStock;
	}

	public void setListaStock(ArrayList<StockDTO> listaStock) {
		this.listaStock = listaStock;
	}

	public ArrayList<ComponenteDTO> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<ComponenteDTO> listaProductos) {
		this.listaProductos = listaProductos;
	}
    
    
}