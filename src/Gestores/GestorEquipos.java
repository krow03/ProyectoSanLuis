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
	public EquipoDTO getEquipoById(int idEquipo) {
		EquipoDTO edto=null;
		for(EquipoDTO temp : listaEquipos) {
			if(idEquipo == temp.getIdEquipo()) edto=temp;
		}
		return edto;
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
	
	public ArrayList<EquipoDTO> getEquiposAula(int idAula) {
		ArrayList<EquipoDTO> equiposAula = edao.listarTodos(idAula);
		return equiposAula;
	}
	
	public boolean crearEquipo(EquipoDTO e) throws SQLException {
		return edao.insertar(e);
	}
	public boolean asignarAula(int idEquipo,int idAula) throws SQLException {
		return edao.asignarAula(idEquipo,idAula);
	}
	public boolean modificarEquipo(EquipoDTO e) {
		return edao.actualizar(e);
	}
	
	public boolean borrarEquipo(int idEquipo) {
		return edao.borrar(idEquipo);
	}
}
