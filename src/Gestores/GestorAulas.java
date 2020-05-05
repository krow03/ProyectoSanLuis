package Gestores;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.AulaDAO;
import DTO.AulaDTO;
import DTO.UsuarioDTO;

public class GestorAulas {
	private AulaDAO ad = new AulaDAO();
	private static ArrayList <AulaDTO> listaAulas;
	private  GestorEquipos ge = GestorEquipos.getInstance();;
	private static GestorAulas instancia =null;
	
	
	public static GestorAulas getInstance() {
		if(instancia == null) instancia=new GestorAulas();
		return instancia;
	}
	private GestorAulas() {
		cargarListaAulas();
	}
	/////////////////////////////////Metodos Conexiones BD///////////////////////////////
	public boolean crearAula(AulaDTO a) throws SQLException {
		if(ad.insertar(a)) {
			cargarListaAulas();
			return true;
		}
		return false;
	}
	public boolean modificarAula(AulaDTO a) {
		if(ad.actualizar(a)) {
			listaAulas.set(listaAulas.indexOf(getAulaById(a.getIdAula())), a);
			return true;
		}
		return false;
	}
	public boolean borrarAula(int idAula) {
		if(ad.borrar(idAula)) {
			listaAulas.remove(getAulaById(idAula));
			return true;
		}
		return false;
	}
	private void cargarListaAulas() {
		listaAulas = ad.listarTodos();
		cargarEquiposAula();
	}
	
	public void cargarEquiposAula() {
		for(AulaDTO adto : listaAulas) {
			 adto.setEquipos(ge.getEquiposAula(adto.getIdAula()));
		}
	}
	
	public AulaDTO getAulaByNombre(String nombre) {
		for(AulaDTO a : listaAulas) {
			if(a.getNombre().equals(nombre))return a;
		}
		return null;
	}
	public AulaDTO getAulaById(int idAula) {
		for(AulaDTO a : listaAulas) {
			if(a.getIdAula()==idAula)return a;
		}
		return null;
	}
	public ArrayList<AulaDTO> getListaAulas(){
		return listaAulas;
	}

}
