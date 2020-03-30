package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	private static Conexion instancia = null;
	private static Connection con;
	private Conexion() {
	 String host = "127.0.0.1";
	 String user = "root";
	 String pass = "";
	 String dtbs = "sanluisdb";
	try{
	 Class.forName("com.mysql.jdbc.Driver"); //Inicializar el driver
	 String newConnectionURL = "jdbc:mysql://" + host + "/" + dtbs + "?" + "user=" + user + "&password=" + pass;
	con = DriverManager.getConnection(newConnectionURL);
	 }catch (Exception e) {
	 System.out.println("Error al abrir la conexi�n.");
	 }
	 }
	public static Conexion getInstance(){
	if (instancia == null) instancia = new Conexion();
	return instancia;
	 }
	public Connection getCon (){
	return con;
	 }
	public void cerrarConexion() {
	try {
		con.close();
	}catch (Exception e) {
		 System.out.println("Error al cerrar la conexi�n.");
		 }
	 } 
}
