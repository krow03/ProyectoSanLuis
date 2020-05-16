package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.RequisitoDTO;
import DTO.StockDTO;
import conexion.Conexion;

public class RequisitoDAO implements PatronDAO<RequisitoDTO>{
	private static final String SQL_FINDALLBYIDCOMPONENTE="SELECT * FROM Requisitos WHERE Componentes_idComponentes = ?";
	private Conexion con = Conexion.getInstance();
	
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
		ArrayList<RequisitoDTO> lista = new ArrayList<RequisitoDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALLBYIDCOMPONENTE);
			ps.setInt(1, (int)pk);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				RequisitoDTO stock = new RequisitoDTO(rs.getInt("idRequisitos"),rs.getString("descripcion"),rs.getString("clave"));
				lista.add(stock);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
