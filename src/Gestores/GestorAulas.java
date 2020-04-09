package Gestores;

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
	
	public AulaDTO getAulaByNombre(String nombre) {
		for(AulaDTO a : listaAulas) {
			if(a.getNombre().equals(nombre))return a;
		}
		return null;
	}
}
