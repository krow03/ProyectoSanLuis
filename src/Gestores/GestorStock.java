package Gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.StockDAO;
import DTO.StockDTO;

public class GestorStock {
	private StockDAO sd = new StockDAO();
	private static ArrayList<StockDTO> listaStock = new ArrayList<StockDTO>();
	
	public ArrayList<StockDTO> getListaStock(){
		return listaStock;
	}
	
	public void cargarListaStock() {
		listaStock = sd.listarTodos();
	}
	
	public boolean crearStock(StockDTO sdto) throws SQLException {
		return sd.insertar(sdto);
	}
	
	public boolean borrarStock(int idStock) {
		return sd.borrar(idStock);
	}
	
	public boolean modificarStock(StockDTO sdto) {
		return sd.actualizar(sdto);
	}
	
	public ArrayList<StockDTO> avisoStockBajo() {
		ArrayList<StockDTO> productosBajoMinimos = new ArrayList<StockDTO>();
		for(StockDTO sdto : listaStock) {
			if(sdto.getLimiteInferior()>=sdto.getUnidades())productosBajoMinimos.add(sdto);
		}
		return productosBajoMinimos;
	}
	
	public StockDTO buscarById(int idStock) {
		return sd.buscar(idStock);
	}
}
