package gestores;

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

	private static GestorSolicitudes instancia =null;
	
	public static GestorSolicitudes getInstance() {
		if(instancia == null) instancia=new GestorSolicitudes();
		return instancia;
	}
	private GestorSolicitudes() {
		cargarLista();
	}
	
	public ArrayList<IncidenciaDTO> getList(){
		return listaSoli;
	}
	
	private void cargarLista() {
		listaSoli=idao.listarTodos();
	}
	
	public ArrayList<IncidenciaDTO> getIncidenciasTecnico(String idTecnico) {
		return idao.listarPorAsignadoA(idTecnico);
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
		if(idao.insertarSolicitud(sdto)) {
			cargarLista();
			return true;
		}
		return false;
	}
	public boolean crearIncidencia(IncidenciaDTO sdto) throws SQLException {
		if(idao.insertar(sdto)) {
			cargarLista();
			return true;
		}
		return false;
	}
	public boolean modificarIncidencia(IncidenciaDTO idto) {
		((SolicitudDTO)idto).setComponente(((SolicitudDTO)getIncidencia(idto.getCodigo())).getComponente());
        if(idao.actualizar(idto)) {
        	listaSoli.set(listaSoli.indexOf(getIncidencia(idto.getCodigo())), idto);
        	return true;
        }
        return false;
    }
	public IncidenciaDTO getIncidencia(int id) {
		for(IncidenciaDTO idto : listaSoli) {
			if(idto.getCodigo()==id)return idto;
		}
		return null;
	}
}
