package Gestores;

import java.util.ArrayList;

import DAO.ComponenteDAO;
import DTO.ComponenteDTO;
import DTO.HardwareDTO;
import DTO.SoftwareDTO;;

public class GestorComponentes {
	private static ArrayList<ComponenteDTO> listaComponentes = new ArrayList<ComponenteDTO>();
	private ComponenteDAO cdao = new ComponenteDAO();
	
	public ArrayList<ComponenteDTO> getList(){
		return listaComponentes;
	}
	public ComponenteDTO getComponenteId(int idComponente) {
		for(ComponenteDTO cdto : listaComponentes) {
			if(cdto.getIdComponente()==idComponente)return cdto;
		}
		return null;
	}
	public void cargarLista() {
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
		return cdao.asignarStock(cdto, idStock);
	}
	
	public boolean asignarEquipo(ComponenteDTO cdto, int idEquipo) {
		return cdao.asignarEquipo(cdto, idEquipo);
	}
	
	public ComponenteDTO getComponente(int id) {
		return cdao.buscar(id);
	}
}
