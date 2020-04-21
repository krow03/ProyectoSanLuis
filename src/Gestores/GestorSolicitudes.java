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
	private GestorComponentes gc = new GestorComponentes();
	private GestorUsuarios gu = new GestorUsuarios();
	
	public ArrayList<IncidenciaDTO> getList(){
		return listaSoli;
	}
	
	public void cargarLista() {
		listaSoli=idao.listarTodos();
		cargarComponentes();
	}
	
	public void traspasarIncidencias(String idTecnico) {
		for(IncidenciaDTO idto : listaSoli) {
			if(idto.getIdAsignadaA().equals(idTecnico)) {
				idto.setIdAsignadaA(gu.getTecnicoMenosIncidencias().getIdUsuario());
			}
		}
	}
	
	public void cargarComponentes() {
		for(IncidenciaDTO idto : listaSoli) {
			if(idto instanceof SolicitudDTO) 
				((SolicitudDTO) idto).setComponente(gc.getComponente(((SolicitudDTO) idto).getIdComponente()));
		}
	}
	private void cargarComponente() {
		for(IncidenciaDTO sol : listaSoli) {
			if(sol instanceof SolicitudDTO)
				((SolicitudDTO) sol).setComponente(gc.getComponente(((SolicitudDTO) sol).getIdComponente()));
		}
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
			if(!(idto instanceof SolicitudDTO) && !idto.getEstado().equals("atendida"))
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
	
	public boolean crearSolicitud(IncidenciaDTO sdto) throws SQLException {
		return idao.insertarSolicitud(sdto);
	}
	public boolean crearIncidencia(IncidenciaDTO sdto) throws SQLException {
		return idao.insertar(sdto);
	}
	public boolean modificarIncidencia(IncidenciaDTO idto) {
        return idao.actualizar(idto);
    }
	
	public IncidenciaDTO getIncidencia(int id) {
		return idao.buscar(id);
	}
}
