package Gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.StockDAO;
import DTO.StockDTO;

public class GestorStock {
	private StockDAO sdao = new StockDAO();
	private static ArrayList<StockDTO> listaStock = new ArrayList<StockDTO>();
	private GestorComponentes gc = new GestorComponentes();
	private GestorEquipos ge = new GestorEquipos();
	
	public ArrayList<StockDTO> getListaStock(){
		return listaStock;
	}
	
	public void cargarListaStock() {
		listaStock = sdao.listarTodos();
	}
	public void cargarConponentesStock(){
		for(StockDTO sdto : listaStock) {
			sdto.setComponentes(gc.getComponentesStock(sdto.getIdStock()));
		}
	}
	
	public void cargarEquiposStock() {
		for(StockDTO sdto : listaStock) {
			sdto.setEquipos(ge.getEquiposStock(sdto.getIdStock()));
		}
	}
	
	public boolean crearStock(StockDTO sdto) throws SQLException {
		return sdao.insertar(sdto);
	}
	
	public boolean borrarStock(int idStock) {
		return sdao.borrar(idStock);
	}
	
	public boolean modificarStock(StockDTO sdto) {
		return sdao.actualizar(sdto);
	}
	
	public ArrayList<StockDTO> avisoStockBajo() {
		ArrayList<StockDTO> productosBajoMinimos = new ArrayList<StockDTO>();
		for(StockDTO sdto : listaStock) {
			if(sdto.getLimiteInferior()>=sdto.getUnidades())productosBajoMinimos.add(sdto);
		}
		return productosBajoMinimos;
	}
	
	public StockDTO buscarById(int idStock) {
		return sdao.buscar(idStock);
	}
}
