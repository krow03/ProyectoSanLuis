package Gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.AulaDAO;
import DTO.AulaDTO;
import DTO.UsuarioDTO;

public class GestorAulas {
	private AulaDAO ad = new AulaDAO();
	private static ArrayList <AulaDTO> listaAulas;
	private GestorEquipos ge = new GestorEquipos();
	
	public ArrayList<AulaDTO> getListaAulas(){
		return listaAulas;
	}
	
	public void cargarListaAulas(int idCentro) {
		listaAulas = ad.listarTodos(idCentro);
		cargarEquiposAula();
	}
	
	public void cargarEquiposAula() {
		for(AulaDTO adto : listaAulas) {
			 adto.setEquipos(ge.getEquiposAula(adto.getIdAula()));
		}
	}
	public boolean crearAula(AulaDTO a) throws SQLException {
        return ad.insertar(a);
    }
	public AulaDTO getAulaByNombre(String nombre) {
		for(AulaDTO a : listaAulas) {
			if(a.getNombre().equals(nombre))return a;
		}
		return null;
	}
	
	public boolean modificarAula(AulaDTO a) {
		return ad.actualizar(a);
	}
	
	public boolean borrarAula(int idAula) {
		return ad.borrar(idAula);
	}
	

}
