package Gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ProveedorDAO;
import DTO.CompraDTO;
import DTO.LineaCompraDTO;
import DTO.ProveedorDTO;
import DTO.StockDTO;

public class GestorProveedores {
	private static GestorCompras gc =  GestorCompras.getInstance();
	private ProveedorDAO pd = new ProveedorDAO();
	private GestorStock gs = GestorStock.getInstance();
	private static ArrayList<ProveedorDTO> listaProveedores = new ArrayList<ProveedorDTO>();
	private static GestorProveedores instancia =null;
	
	public static GestorProveedores getInstance() {
		if(instancia == null) instancia=new GestorProveedores();
		return instancia;
	}
	private GestorProveedores() {
		cargarListaProv();
	}
	
	public ArrayList<ProveedorDTO> getListaProv(){
		return listaProveedores;
	}
	
	public void cargarListaProv() {
		listaProveedores = pd.listarTodos();
		cargarListaStock();
	}
	
	private void cargarListaStock() {
		ArrayList<CompraDTO>listaCompras = gc.getListaCompras();
		ArrayList<StockDTO>listaStock = new ArrayList<StockDTO>();
		for(ProveedorDTO pdto : listaProveedores) {
			for(CompraDTO cdto : listaCompras) {
				if(pdto.getIdProveedor()== cdto.getIdProveedor()) {
					for(LineaCompraDTO hdto : cdto.getListaProdPorCompra()) {
						if(gs.buscarById(hdto.getIdStock())!=null) {
							listaStock.add(gs.buscarById(hdto.getIdStock()));
						}
					}
				}
				pdto.setListaStock(listaStock);
			}
		}
	}
	
	public boolean crearProv(ProveedorDTO pdto) throws SQLException {
		return pd.insertar(pdto);
	}
	
	public boolean borrarProv(int idProveedor) {
		return pd.borrar(idProveedor);
	}
	
	public boolean modificarProv(ProveedorDTO pdto) {
		return pd.actualizar(pdto);
	}
}
