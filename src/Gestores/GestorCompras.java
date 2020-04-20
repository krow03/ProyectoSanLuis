package Gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.CompraDAO;
import DAO.HistoricoComprasDAO;
import DTO.CompraDTO;
import DTO.HistoricoComprasDTO;

public class GestorCompras {
	private CompraDAO cd = new CompraDAO();
	private HistoricoComprasDAO hd = new HistoricoComprasDAO();
	private static ArrayList<CompraDTO> listaCompras = new ArrayList<CompraDTO>();

	public ArrayList<CompraDTO> getListaCompras(){
		return listaCompras;
	}
	
	public void cargarListaCompras() {
		listaCompras = cd.listarTodos();
		cargarHistorico();
	}
	
	private void cargarHistorico() {
		for(CompraDTO cdto : listaCompras) {
			cdto.setListaProdPorCompra(hd.listarTodos(cdto.getIdCompra()));
		}
	}
	
	public boolean crearCompra(CompraDTO cdto) throws SQLException {
		return cd.insertar(cdto);
	}
	
	public boolean borrarCompra(int idCompra) {
		return cd.borrar(idCompra);
	}
	
	public boolean modificar(CompraDTO cdto) {
		return cd.actualizar(cdto);
	}
	
	public boolean crearHistorico(ArrayList<HistoricoComprasDTO> listaProdCompra) throws SQLException {
		boolean finalizado=true;
		for(HistoricoComprasDTO hdto : listaProdCompra) {
			if(!hd.insertar(hdto)) finalizado = false;
		}
		return finalizado;
	}
}
