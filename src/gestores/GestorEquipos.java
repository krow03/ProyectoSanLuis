package gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.EquipoDAO;
import DTO.EquipoDTO;


public class GestorEquipos {
	private static ArrayList<EquipoDTO> listaEquipos = new ArrayList<EquipoDTO>();
	private static ArrayList<EquipoDTO> listaEquiposDisp = new ArrayList<EquipoDTO>();
	
	private EquipoDAO edao = new EquipoDAO();
	private GestorComponentes gc = GestorComponentes.getInstance();
	private static GestorEquipos instancia =null;
	
	public static GestorEquipos getInstance() {
		if(instancia == null) instancia=new GestorEquipos();
		return instancia;
	}
	private GestorEquipos() {
		cargarListaEquipos();
	}
	public ArrayList<EquipoDTO> getListaEquipos(){
		return listaEquipos;
	}
	
	public ArrayList<EquipoDTO> getEquiposDisp(){
		return listaEquiposDisp;
	}
	
	public EquipoDTO getEquipoById(int idEquipo) {
		EquipoDTO edto=null;
		for(EquipoDTO temp : listaEquipos) {
			if(idEquipo == temp.getIdEquipo()) edto=temp;
		}
		return edto;
	}
	
	private void cargarListaEquipos() {
		listaEquipos = edao.listarTodos();
		listaEquiposDisp = edao.listarTodosDisponibles();
		cargarComponentesEquipo();
	}
	
	public void cargarComponentesEquipo() {
		for(EquipoDTO edto : listaEquipos) {
			 edto.setComponentes(gc.getComponentesEquipo(edto.getIdEquipo()));
		}
		for(EquipoDTO edto : listaEquiposDisp) {
			 edto.setComponentes(gc.getComponentesEquipo(edto.getIdEquipo()));
		}
	}
	
	public ArrayList<EquipoDTO> getEquiposStock(int idStock) {
		return edao.listarTodosStock(idStock);
	}
	
	public ArrayList<EquipoDTO> getEquiposAula(int idAula) {
		return edao.listarTodos(idAula);
	}
	
	public boolean crearEquipo(EquipoDTO e) throws SQLException {
		if(edao.insertar(e)) {
			cargarListaEquipos();
			return true;
		}
		return false;
	}
	
	public boolean asignarAula(int idEquipo,int idAula) throws SQLException {
		if(edao.asignarAula(idEquipo,idAula)) {
			cargarListaEquipos();
			return true;
		}
		return false;
	}
	
	public boolean desasignarAula(int idEquipo) throws SQLException {
		if(edao.desasignarAula(idEquipo)) {
			cargarListaEquipos();
			return true;
		}
		return false;
	}
	
	public boolean modificarEquipo(EquipoDTO e) {
		if(edao.actualizar(e)) {
			listaEquipos.set(listaEquipos.indexOf(getEquipoById(e.getIdEquipo())), e);
			if(listaEquiposDisp.contains(getEquipoById(e.getIdEquipo()))) {
				listaEquiposDisp.set(listaEquiposDisp.indexOf(getEquipoById(e.getIdEquipo())), e);
			}
			return true;
		}
		return false;
	}
	
	public boolean borrarEquipo(int idEquipo) {
		if(edao.borrar(idEquipo)) {
			listaEquipos.remove(getEquipoById(idEquipo));
			if(listaEquiposDisp.contains(getEquipoById(idEquipo))) {
				listaEquiposDisp.remove(getEquipoById(idEquipo));
			}
			
			return true;
		}
		return false;
	}
}
