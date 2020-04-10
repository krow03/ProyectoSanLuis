package Gestores;

import java.sql.SQLException;
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
	
	public boolean crearEquipo(EquipoDTO e) throws SQLException {
		return edao.insertar(e);
	}
	public boolean crearEquipoAula(EquipoDTO e) throws SQLException {
		return edao.insertarEquipoAula(e);
	}
	public boolean modificarEquipo(EquipoDTO e) {
		return edao.actualizar(e);
	}
	
	public boolean borrarEquipo(int idEquipo) {
		return edao.borrar(idEquipo);
	}
}
