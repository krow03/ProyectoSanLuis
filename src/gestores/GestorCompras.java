package gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.CompraDAO;
import DAO.LineaCompraDAO;
import DTO.CompraDTO;
import DTO.LineaCompraDTO;

public class GestorCompras {
	private CompraDAO cd = new CompraDAO();
	private LineaCompraDAO hd = new LineaCompraDAO();
	private static ArrayList<CompraDTO> listaCompras = new ArrayList<CompraDTO>();
	private static GestorCompras instancia =null;
	
	
	public static GestorCompras getInstance() {
		if(instancia == null) instancia=new GestorCompras();
		return instancia;
	}
	private GestorCompras() {
		cargarListaCompras();
	}
	public ArrayList<CompraDTO> getListaCompras(){
		return listaCompras;
	}
	
	private void cargarListaCompras() {
		listaCompras = cd.listarTodos();
		cargarLineaCompra();
	}
	public ArrayList<CompraDTO> getComprasProveedor(int idProveedor) {
        ArrayList<CompraDTO> compras = cd.listarTodos(idProveedor);
        for(CompraDTO cdto : compras) {
            cdto.setListaProdPorCompra(hd.listarTodos(cdto.getIdCompra()));
        }
        return compras;
    }
	
	private void cargarLineaCompra() {
		for(CompraDTO cdto : listaCompras) {
			cdto.setListaProdPorCompra(hd.listarTodos(cdto.getIdCompra()));
		}
	}
	
	public boolean crearCompra(CompraDTO cdto) throws SQLException {
		if(cd.insertar(cdto)) {
			cargarListaCompras();
			return true;
		}
		return false;
	}
	
	public boolean borrarCompra(int idCompra) {
		if(cd.borrar(idCompra)) {
			listaCompras.remove(getCompraById(idCompra));
			return true;
		}
		return false;
	}
	
	public boolean modificar(CompraDTO cdto) {
		cdto.setProveedor(getCompraById(cdto.getIdCompra()).getProveedor());
		if(cd.actualizar(cdto)) {
			listaCompras.set(listaCompras.indexOf(getCompraById(cdto.getIdCompra())), cdto);
			return true;
		}
		return false;
	}
	
	public boolean crearLineaCompra(ArrayList<LineaCompraDTO> listaProdCompra,int idCompra) throws SQLException {
		boolean finalizado=true;
		for(LineaCompraDTO hdto : listaProdCompra) {
			if(!hd.insertar(hdto,idCompra)) {
				finalizado = false;
			}else {
				cargarListaCompras();
			}
		}
		return finalizado;
	}
	
	public CompraDTO getCompraById(int idCompra) {
		for(CompraDTO cdto : listaCompras) {
			if(cdto.getIdCompra()==idCompra) {
				return cdto;
			}
		}
		return null;
	}
}
