package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatronDAO<TipoGen> {
	public boolean insertar(TipoGen t) throws SQLException;
	public boolean borrar(Object pk);
	public boolean actualizar(TipoGen t);
	public TipoGen buscar(Object pk);
	public ArrayList<TipoGen> listarTodos();
	public ArrayList<TipoGen> listarTodos(Object pk);
}

