package gestores;

import java.util.ArrayList;

import DAO.ComponenteDAO;
import DAO.RequisitoDAO;
import DTO.ComponenteDTO;
import DTO.HardwareDTO;
import DTO.SoftwareDTO;;

public class GestorComponentes {
	private static ArrayList<ComponenteDTO> listaComponentes = new ArrayList<ComponenteDTO>();
	private ComponenteDAO cdao = new ComponenteDAO();
	private RequisitoDAO rdao = new RequisitoDAO();
	private static GestorComponentes instancia =null;
	
	
	public static GestorComponentes getInstance() {
		if(instancia == null) instancia=new GestorComponentes();
		return instancia;
	}
	private GestorComponentes() {
		cargarLista();
		cargarRequisitos();
	}
	
	private void cargarRequisitos() {
		for(ComponenteDTO cdto : listaComponentes) {
			cdto.setRequisitos(rdao.listarTodos(cdto.getIdComponente()));
		}
	}
	public ArrayList<ComponenteDTO> getList(){
		return listaComponentes;
	}
	public ComponenteDTO getComponenteId(int idComponente) {
		for(ComponenteDTO cdto : listaComponentes) {
			if(cdto.getIdComponente()==idComponente)return cdto;
		}
		return null;
	}
	private void cargarLista() {
		listaComponentes=cdao.listarTodos();
	}
	
	public ArrayList<ComponenteDTO> getComponentesEquipo(int idEquipo){
		return cdao.listarTodos(idEquipo);
	}
	
	public ArrayList<ComponenteDTO> getComponentesStock(int idStock){
		return cdao.listarTodosStock(idStock);
	}
	
	public ArrayList<ComponenteDTO> getListaHardware() {
		ArrayList<ComponenteDTO> listaHard = new ArrayList<ComponenteDTO>();
		for(ComponenteDTO cdto : listaComponentes) {
			if(cdto instanceof HardwareDTO) listaHard.add(cdto);
		}
		return listaHard;
	}
	
	public ArrayList<ComponenteDTO> getListaSoftware() {
		ArrayList<ComponenteDTO> listaSoft = new ArrayList<ComponenteDTO>();
		for(ComponenteDTO cdto : listaComponentes) {
			if(cdto instanceof SoftwareDTO) listaSoft.add(cdto);
		}
		return listaSoft;
	}
	
	public boolean asignarStock(ComponenteDTO cdto, int idStock) {
		if(cdao.asignarStock(cdto, idStock)) {
			cargarLista();
			return true;
		}
		return false;
	}
	
	public boolean asignarEquipo(ComponenteDTO cdto, int idEquipo) {
		if(cdao.asignarEquipo(cdto, idEquipo)) {
			cargarLista();
			return true;
		}
		return false;
	}
	
	public ComponenteDTO getComponente(int id) {
		for(ComponenteDTO cdto : listaComponentes) {
			if(cdto.getIdComponente()==id)return cdto;
		}
		return null;
	}
}
