package Gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.IncidenciaDAO;
import DTO.IncidenciaDTO;
import DTO.SolicitudDTO;

/*
 * estados incidencias solicitudes:
 * -pendiente
 * -proceso
 * -atendida
*/
public class GestorSolicitudes {
	private ArrayList<IncidenciaDTO> listaSoli = new ArrayList<IncidenciaDTO>();
	private IncidenciaDAO idao = new IncidenciaDAO();
	
	public ArrayList<IncidenciaDTO> getList(){
		return listaSoli;
	}
	
	public void cargarLista() {
		listaSoli=idao.listarTodos();
	}
	
	public ArrayList<IncidenciaDTO> getListaNoAtendidas() {
		ArrayList<IncidenciaDTO> listaNoAtendidas = new ArrayList<IncidenciaDTO>();
		for(IncidenciaDTO idto : listaSoli) {
			if(!idto.getEstado().equals("atendida")) listaNoAtendidas.add(idto);
		}
		return listaNoAtendidas;
	}
	
	public ArrayList<IncidenciaDTO> getListaSol() {
		ArrayList<IncidenciaDTO> listaSol = new ArrayList<IncidenciaDTO>();
		for(IncidenciaDTO idto : listaSoli) {
			if(idto instanceof SolicitudDTO) listaSol.add(idto);
		}
		return listaSol;
	}
	
	public ArrayList<IncidenciaDTO> getListaInci() {
		ArrayList<IncidenciaDTO> listaInci = new ArrayList<IncidenciaDTO>();
		for(IncidenciaDTO idto : listaSoli) {
			if(!(idto instanceof SolicitudDTO)) listaInci.add(idto);
		}
		return listaInci;
	}
	
	public ArrayList<IncidenciaDTO> getListaAsignadasA(String id) {
		ArrayList<IncidenciaDTO> listaAsignadas = new ArrayList<IncidenciaDTO>();
		for(IncidenciaDTO idto : listaSoli) {
			if(idto.getIdAsignadaA().equals(id)) listaAsignadas.add(idto);
		}
		return listaAsignadas;
	}
	
	public ArrayList<IncidenciaDTO> getListaRealizadasPor(String id) {
		ArrayList<IncidenciaDTO> listaRealizadas = new ArrayList<IncidenciaDTO>();
		for(IncidenciaDTO idto : listaSoli) {
			if(idto.getIdRealizadaPor().equals(id)) listaRealizadas.add(idto);
		}
		return listaRealizadas;
	}
	
	public boolean crearIncidencia(IncidenciaDTO sdto) throws SQLException {
		return idao.insertarSolicitud(sdto);
	}
	public boolean modificarIncidencia(IncidenciaDTO idto) {
		return idao.actualizar(idto);
	}
	
	public IncidenciaDTO getIncidencia(int id) {
		return idao.buscar(id);
	}
}
