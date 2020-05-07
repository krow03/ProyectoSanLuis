package conexion;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;

public class ConexionNoSQL {
	private static ConexionNoSQL instancia = null;
	private static MongoClientURI mcURI = null;
	private static MongoClient mongoClient = null;
	private static MongoDatabase db = null;
	private static final String user = "krow";
	private static final String pass = "1234";
	private static final String host0 = "proyectosl-shard-00-00-tjv0p.mongodb.net:27017";
	private static final String host1 = "proyectosl-shard-00-01-tjv0p.mongodb.net:27017";
	private static final String host2 = "proyectosl-shard-00-02-tjv0p.mongodb.net:27017";
	
	private ConexionNoSQL() {
		try {
			//String connectionURL = "mongodb://"+ user +":"+pass+"@"+host0+","+host1+","+host2+"/test?ssl=true&replicaSet=proyectoSL-shard-0&authSource=admin&retryWrites=true&w=majority";
			
			String connectionURL = "mongodb://localhost:27017";
			mcURI = new MongoClientURI(connectionURL);
			mongoClient = new MongoClient(mcURI);
			System.out.println("conexion establecida");
		}catch(MongoException me) {
			me.printStackTrace();
			System.out.println("Error al abrir la conexión.");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error al abrir la conexión.");
		}
	}
	
	public static ConexionNoSQL getInstance() {
		if(instancia == null) instancia = new ConexionNoSQL();
		
		return instancia;
	}
	
	public static MongoClient getClient() {
		return mongoClient;
	}
	
	public MongoDatabase getDataBase() {
		return db;
	}
	
	//public void setDatabase(String dataBase) {
	//	db = mongoClient.getDB(dataBase);
	//}
	
	public static void closeConnection() {
		try {
			mongoClient.close();
			db =null;
		}catch(MongoException me){
			me.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
