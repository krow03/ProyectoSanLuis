package Gestores;

import java.util.ArrayList;

import DAO.EquipoDAO;
import DTO.EquipoDTO;

public class GestorEquipos {
	private static ArrayList<EquipoDTO> listaEquipos = new ArrayList<EquipoDTO>();
	private EquipoDAO edao = new EquipoDAO();
	
	public ArrayList<EquipoDTO> getListaEquipos(){
		return listaEquipos;
	}
	
	public boolean cargarListaEquipos() {
		listaEquipos = edao.listarTodos();
		if (listaEquipos.size()<0 ) {
			return true;
		}
		return false;
	}
	
	public ArrayList<EquipoDTO> getEquiposAula(int idAula) {
		ArrayList<EquipoDTO> equiposAula = new ArrayList<EquipoDTO>();
		for(EquipoDTO equipo : listaEquipos) {
			if(equipo.getIdAula() == idAula) {
				equiposAula.add(equipo);
			}
		}
		return equiposAula;
	}
	
	public boolean modificarEquipoAula(EquipoDTO e) {
		return edao.actualizar(e);
	}
	
	public boolean borrarEquipo(int idEquipo) {
		return edao.borrar(idEquipo);
	}
}
