package Gestores;

import java.util.ArrayList;

import DAO.ComponenteDAO;
import DTO.ComponenteDTO;
import DTO.HardwareDTO;
import DTO.SoftwareDTO;;

public class GestorComponentes {
	private ArrayList<ComponenteDTO> listaComponentes = new ArrayList<ComponenteDTO>();
	private ComponenteDAO cdao = new ComponenteDAO();
	
	public ArrayList<ComponenteDTO> getList(){
		return listaComponentes;
	}
	
	private void cargarLista() {
		listaComponentes=cdao.listarTodos();
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
	
	public ComponenteDTO getIncidencia(int id) {
		return cdao.buscar(id);
	}
}
