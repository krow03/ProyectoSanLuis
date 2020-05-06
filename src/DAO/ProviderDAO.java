package DAO;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Base64;

import javax.swing.JFileChooser;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import DTO.PdfDTO;
import conexion.ConexionNoSQL;

public class ProviderDAO {
	private static ConexionNoSQL con = ConexionNoSQL.getInstance();
	private static MongoDatabase db; 
	
	public ProviderDAO() {
		con.setDatabase("CatalogoProv");
		this.db = (MongoDatabase) con.getDataBase();
	}
	
	public static MongoDatabase getDb() {
		return db;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ProviderDAO pdao = new ProviderDAO();
			System.out.println("connected to database");
			pdao.getDb().getCollection("productos");
			System.out.println("Coleccion obtenida");
			ins(new PdfDTO("ad","afae","daaaef"));
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Server is ready");
	}
	
	public static boolean ins(PdfDTO e) {
		String encodedString = null;
		try {
			JFileChooser jFileChooser=new JFileChooser();
			   StringBuffer buffer;
			    buffer = new StringBuffer();
			   int result= jFileChooser.showOpenDialog(null);
			    if(result==JFileChooser.APPROVE_OPTION)
			    {
			        try {
			            FileReader reader;
			            reader = null;
			            File file=jFileChooser.getSelectedFile();
			            reader=new FileReader(file);
			            if(file == null)
			            	System.out.println("que no es nulo");
			            //AQUI TENEMOS LA RUTA
			            String filePath = file.toString();
			            System.out.println(filePath);
			            byte[] input_file = Files.readAllBytes(Paths.get(filePath));
			            System.out.println("1");
			            byte[] encodedBytes = Base64.getEncoder().encode(input_file);
			            System.out.println("1");
			            encodedString =  new String(encodedBytes);
			            System.out.println(encodedString);
			            
			            System.out.println(file);
			            int i=1;
			            while(i!=-1)
			            {
			                i=reader.read();
			                char ch=(char) i;
			                buffer.append(ch);

			            }

			            

			        } catch (IOException ex) {
			        }

			    }
			MongoCollection<Document> proveedoresCollection = (MongoCollection<Document>) getDb().getCollection("productos");
			Document documento = new Document();
			documento.append("_id", "00022");
			documento.append("nombreFichero", e.getNombreFichero());
			documento.append("contenido", encodedString);
			proveedoresCollection.insertOne(documento);
			MessageDigest md = MessageDigest.getInstance("MD5");
			try (InputStream is = Files.newInputStream(Paths.get("file.txt"));
			     DigestInputStream dis = new DigestInputStream(is, md)) 
			{
			  /* Read decorated stream (dis) to EOF as normal... */
			}
			byte[] digest = md.digest();
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		} finally {
			con.closeConnection();
		}

	}
	
}
