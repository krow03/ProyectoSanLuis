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
		System.out.println("me cago en dios");
		System.out.println(cdao.buscar(idComponente).getIdComponente());

		return cdao.buscar(idComponente);
	}
	public void cargarLista() {
		listaComponentes=cdao.listarTodos();
	}
	
	public ArrayList<ComponenteDTO> getComponentesEquipo(int idEquipo){
		ArrayList<ComponenteDTO> listaComponentesEquipo = new ArrayList<ComponenteDTO>();
		for(ComponenteDTO cdto : listaComponentes) {
			if(cdto.getIdEquipo()==idEquipo)listaComponentesEquipo.add(cdto);
		}
		return listaComponentesEquipo;
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
	
	public ComponenteDTO getComponente(int id) {
		return cdao.buscar(id);
	}
}
