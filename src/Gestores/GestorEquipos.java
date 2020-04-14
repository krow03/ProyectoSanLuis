package Gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.EquipoDAO;
import DTO.EquipoDTO;


public class GestorEquipos {
	private static ArrayList<EquipoDTO> listaEquipos = new ArrayList<EquipoDTO>();
	private EquipoDAO edao = new EquipoDAO();
	private GestorComponentes gc = new GestorComponentes();
	
	public ArrayList<EquipoDTO> getListaEquipos(){
		return listaEquipos;
	}
	
	public void cargarListaEquipos() {
		listaEquipos = edao.listarTodos();
		cargarComponentesEquipo();
	}
	
	public void cargarComponentesEquipo() {
		for(EquipoDTO edto : listaEquipos) {
			 edto.setComponentes(gc.getComponentesEquipo(edto.getIdEquipo()));
		}
	}
	
	public ArrayList<EquipoDTO> getEquiposDisponiblesEnAulas() {
		ArrayList<EquipoDTO> equiposDisp = new ArrayList<EquipoDTO>();
		for(EquipoDTO equipo : listaEquipos) {
			if(equipo.getIdAula()>0) {
				equiposDisp.add(equipo);
			}
		}
		return equiposDisp;
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
