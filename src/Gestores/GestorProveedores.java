package Gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ProveedorDAO;
import DTO.CompraDTO;
import DTO.LineaCompraDTO;
import DTO.ProveedorDTO;
import DTO.StockDTO;

public class GestorProveedores {
	private GestorCompras gc= new GestorCompras();
	private ProveedorDAO pd = new ProveedorDAO();
	private GestorStock gs = new GestorStock();
	private static ArrayList<ProveedorDTO> listaProveedores = new ArrayList<ProveedorDTO>();
	
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
