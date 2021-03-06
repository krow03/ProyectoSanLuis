package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.StockDTO;
import conexion.Conexion;

public class StockDAO implements PatronDAO<StockDTO>{
	private static final String SQL_INSERT="INSERT INTO sanluisdb.stock (unidadesStock, limiteInferior, descripcion, precio) VALUES (?,?,?,?,?);";
	private static final String SQL_DELETE="DELETE FROM Stock WHERE idStock = ?";
	private static final String SQL_UPDATE="UPDATE Stock SET unidadesStock=?,limiteInferior=?,descripcion=?,precio=? WHERE idStock = ?";
	private static final String SQL_FIND="SELECT * FROM Stock WHERE idStock = ?";
	private static final String SQL_FINDALL="SELECT * FROM Stock";
	private static final String SQL_FINDALLBYIDPROVEEDOR="SELECT * FROM Stock WHERE Proveedores_idProveedores = ?";
	private Conexion con = Conexion.getInstance();
	
	
	@Override
    public boolean insertar(StockDTO t) throws SQLException {
        try {
            PreparedStatement ps = con.getCon().prepareStatement(SQL_INSERT);
            ps.setInt(1, t.getUnidades());
            ps.setInt(2, t.getLimiteInferior());
            ps.setString(3, t.getDescripcion());
            ps.setFloat(4, t.getPrecio());

            if (ps.executeUpdate()>0) {
                ps.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	@Override
	public boolean borrar(Object pk) {
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_DELETE);
			ps.setInt(1, (int)pk);
			int filas = ps.executeUpdate();
			
			if (filas>0) return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean actualizar(StockDTO t) {
		PreparedStatement ps = null;
		try {
			ps = con.getCon().prepareStatement(SQL_UPDATE);
			ps.setInt(1, t.getUnidades());
	        ps.setInt(2, t.getLimiteInferior());
	        ps.setString(3, t.getDescripcion());
	        ps.setFloat(4, t.getPrecio());
			
			ps.setInt(5, t.getIdStock());

			if (ps.executeUpdate()>0) return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if (ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return false;
	}

	@Override
	public StockDTO buscar(Object pk) {
		StockDTO stock = null;
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FIND);
			ps.setInt(1, (int)pk);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true){
				stock = new StockDTO(rs.getInt("idStock"),rs.getInt("unidadesStock"),rs.getInt("limimteInferior"),rs.getString("descripcion"),rs.getFloat("precio"));
				return stock;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stock;
	}

	@Override
	public ArrayList<StockDTO> listarTodos() {
		ArrayList<StockDTO> lista = new ArrayList<StockDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				StockDTO stock = new StockDTO(rs.getInt("idStock"),rs.getInt("unidadesStock"),rs.getInt("limiteInferior"),rs.getString("descripcion"),rs.getFloat("precio"));
				lista.add(stock);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<StockDTO> listarTodos(Object pk) {
		ArrayList<StockDTO> lista = new ArrayList<StockDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALLBYIDPROVEEDOR);
			ps.setInt(1, (int)pk);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				StockDTO stock = new StockDTO(rs.getInt("idStock"),rs.getInt("unidadesStock"),rs.getInt("limiteInferior"),rs.getString("descripcion"),rs.getFloat("precio"));
				lista.add(stock);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
