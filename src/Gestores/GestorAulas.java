package Gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.AulaDAO;
import DTO.AulaDTO;

public class GestorAulas {
	private AulaDAO ad = new AulaDAO();
	private static ArrayList <AulaDTO> listaAulas;
	
	public ArrayList<AulaDTO> getListaAulas(){
		return listaAulas;
	}
	
	public void cargarListaAulas(int idCentro) {
		listaAulas = ad.listarTodos(idCentro);
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
