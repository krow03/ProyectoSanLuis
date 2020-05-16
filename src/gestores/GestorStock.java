package gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.StockDAO;
import DTO.StockDTO;

public class GestorStock {
	private StockDAO sdao = new StockDAO();
	private static ArrayList<StockDTO> listaStock = new ArrayList<StockDTO>();
	private GestorComponentes gc = GestorComponentes.getInstance();
	private GestorEquipos ge = GestorEquipos.getInstance();
	private static GestorStock instancia =null;
	
	public static GestorStock getInstance() {
		if(instancia == null) instancia=new GestorStock();
		return instancia;
	}
	
	private GestorStock() {
		cargarListaStock();
	}
	
	public ArrayList<StockDTO> getListaStock(){
		return listaStock;
	}
	
	private void cargarListaStock() {
		listaStock = sdao.listarTodos();
	}
	
	public void cargarConponentesStock(){
		for(StockDTO sdto : listaStock) {
			sdto.setComponentes(gc.getComponentesStock(sdto.getIdStock()));
		}
	}
	
	public ArrayList<StockDTO> cargarListaStockProv(int idProveedor) {
		ArrayList<StockDTO>listaStockProv =  sdao.listarTodos(idProveedor);
		return cargarConponentesStockProveedor(listaStockProv);
	}
	
	public ArrayList<StockDTO> cargarConponentesStockProveedor(ArrayList<StockDTO> listaStockProv){
		for(StockDTO sdto : listaStockProv) {
			sdto.setComponentes(gc.getComponentesStock(sdto.getIdStock()));
		}
		return listaStockProv;
	}
	
	public void cargarEquiposStock() {
		for(StockDTO sdto : listaStock) {
			sdto.setEquipos(ge.getEquiposStock(sdto.getIdStock()));
		}
	}
	
	public boolean crearStock(StockDTO sdto) throws SQLException {
		if(sdao.insertar(sdto)) {
			cargarListaStock();
			return true;
		}
		return false;
	}
	
	public boolean borrarStock(int idStock) {
		if(sdao.borrar(idStock)){
			listaStock.remove(getStockById(idStock));
			return true;
		}
		return false;
	}
	
	public boolean modificarStock(StockDTO sdto) {
		sdto.setComponentes(getStockById(sdto.getIdStock()).getComponentes());
		sdto.setEquipos(getStockById(sdto.getIdStock()).getEquipos());
		if(sdao.actualizar(sdto)) {
			listaStock.set(listaStock.indexOf(getStockById(sdto.getIdStock())), sdto);
			return true;
		}
		return false;
	}
	
	public ArrayList<StockDTO> avisoStockBajo() {
		ArrayList<StockDTO> productosBajoMinimos = new ArrayList<StockDTO>();
		for(StockDTO sdto : listaStock) {
			if(sdto.getLimiteInferior()>=sdto.getUnidades())productosBajoMinimos.add(sdto);
		}
		return productosBajoMinimos;
	}
	
	public StockDTO getStockById(int idStock) {
		for(StockDTO sdto : listaStock) {
			if(sdto.getIdStock()==idStock) {
				return sdto;
			}
		}
		return null;
	}
}
