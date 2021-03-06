package gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ProveedorDAO;
import DAO.ProviderDAO;
import DTO.CompraDTO;
import DTO.LineaCompraDTO;
import DTO.ProveedorDTO;
import DTO.StockDTO;

public class GestorProveedores {
	
	private ProveedorDAO pd = new ProveedorDAO();
	private GestorStock gs = GestorStock.getInstance();
	private ProviderDAO pdao = new ProviderDAO();
	private static ArrayList<ProveedorDTO> listaProveedores = new ArrayList<ProveedorDTO>();
	private static GestorProveedores instancia =null;
	private ProveedorDTO provOnline;
	public static GestorProveedores getInstance() {
		if(instancia == null) instancia=new GestorProveedores();
		return instancia;
	}
	
	public ProveedorDTO getProvOnline() {
		return provOnline;
	}

	public void setProvOnline(ProveedorDTO provOnline) {
		this.provOnline = provOnline;
	}

	public boolean login(String cif, String pass) {
		provOnline = pd.login(cif, pass);
		if(provOnline!=null)return true;
		return false;
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
		for(ProveedorDTO pdto : listaProveedores) {
			pdto.setListaStock(gs.cargarListaStockProv(pdto.getIdProveedor()));
		}
	}
	public ProveedorDTO getProvById(int idProv) {
        for(ProveedorDTO pdto : listaProveedores) {
            if(pdto.getIdProveedor()==idProv)return pdto;
        }
        return null;
    }
	public boolean insertarPdf() {
		pdao.insertarPdf();
		
		return true;
		
	}
	public boolean obtenerPdf() {
		pdao.getPdf(getProvOnline().getIdProveedor());
		return true;
	}
	public boolean crearProv(ProveedorDTO pdto) throws SQLException {
		if(pd.insertar(pdto)) {
			cargarListaProv();
			return true;
		}
		return false;
	}
	
	public boolean borrarProv(int idProveedor) {
		if(pd.borrar(idProveedor)) {
			listaProveedores.remove(getProvById(idProveedor));
			return true;
		}
		return false;
	}
	
	public boolean modificarProv(ProveedorDTO pdto) {
		pdto.setListaStock(getProvById(pdto.getIdProveedor()).getListaStock());
		if(pd.actualizar(pdto)) {
			listaProveedores.set(listaProveedores.indexOf(getProvById(pdto.getIdProveedor())), pdto);
			return true;
		}
		return false;
	}
}
