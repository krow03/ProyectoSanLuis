package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	private static MongoCollection<Document> proveedoresCollection = con.getClient().getDatabase("proveedores")
			.getCollection("proveedores");
	public ProviderDAO() {
		con.getClient().getDatabase("proveedores");
		this.db = con.getDataBase();
	}

	public static MongoDatabase getDb() {
		return db;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ProviderDAO pdao = new ProviderDAO();
			insertarPdf();
			getPdf("12022");
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Server is ready");
	}

	public static boolean insertarPdf() {
		String encodedString = null;
		String nombre = null;
		File file = null;
		try {
			JFileChooser jFileChooser = new JFileChooser();
			StringBuffer buffer;
			buffer = new StringBuffer();
			int result = jFileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				try {
					FileReader reader;
					reader = null;
					file = jFileChooser.getSelectedFile();
					reader = new FileReader(file);
					String filePath = file.toString();
					int idx = file.getName().lastIndexOf('.');
					if(!((idx > 0) ? file.getName().substring(idx) : "").equals(".pdf")) {
						return false;
					}
					if(file.length() > 134217720) {
						return false;
					}
					byte[] input_file = Files.readAllBytes(Paths.get(filePath));
					byte[] encodedBytes = Base64.getEncoder().encode(input_file);
					encodedString = new String(encodedBytes);
					int i = 1;
					while (i != -1) {
						i = reader.read();
						char ch = (char) i;
						buffer.append(ch);
					}
				} catch (IOException ex) {
				}
			}
			
			
			Document documento = new Document();
			documento.append("_id", "12122");
			documento.append("nombreFichero", file.getName());
			documento.append("contenido", encodedString);
			proveedoresCollection.insertOne(documento);
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		} finally {
			con.closeConnection();
		}
	}
	public static PdfDTO getPdf(Object id) {
		try {
			MongoCollection<Document> proveedoresCollection = con.getClient().getDatabase("proveedores")
					.getCollection("proveedores");
			PdfDTO fichero = new PdfDTO();
			//Document document = new Document();
			//document.append("_id", id);
			
			for (Document d : proveedoresCollection.find()) {
				System.out.println("pero que cojones");
				if(d.getString("_id").contentEquals(id.toString())) {
					fichero.setDniProveedor(d.getString("_id"));
					fichero.setNombreFichero(d.getString("nombreFichero"));
					fichero.setContenido(d.getString("contenido"));
				}
				
			}
			byte[] decodedBytes = Base64.getDecoder().decode(fichero.getContenido().getBytes());


            FileOutputStream fos = new FileOutputStream("C:\\Users\\Dani\\Downloads\\"+fichero.getNombreFichero());
            fos.write(decodedBytes);
            fos.flush();
            fos.close();
			return fichero;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		} finally {
			con.closeConnection();
		}
	}

}
