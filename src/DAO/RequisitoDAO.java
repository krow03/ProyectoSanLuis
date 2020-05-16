package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import DTO.RequisitoDTO;

public class RequisitoDAO implements PatronDAO<RequisitoDTO>{

	@Override
	public boolean insertar(RequisitoDTO t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrar(Object pk) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizar(RequisitoDTO t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RequisitoDTO buscar(Object pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<RequisitoDTO> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<RequisitoDTO> listarTodos(Object pk) {
		// TODO Auto-generated method stub
		return null;
	}

}
