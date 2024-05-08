package controllers;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import entities.Ccaa;

public class ControladorCcaa {

	
	static int port_no = 27017;
    static String host_name = "localhost";
	static String db_name = "ComunidadesProvinciasPoblaciones";
	static String db_coll_name = "ccaa";

    // Mongodb creando la cadena de conexión.
    static String client_url = "mongodb://" + host_name + ":" + port_no + "/" + db_name;
    static MongoClientURI uri = new MongoClientURI(client_url);

    // Conectando y obteniendo un cliente.
    static MongoClient mongo_client = new MongoClient(uri);

    // Obteniendo un enlace a la base de datos.
    static MongoDatabase db = mongo_client.getDatabase(db_name);

    // Obteniendo la colección de la base de datos
    static MongoCollection<Document> col = db.getCollection(db_coll_name);
    
	public static ControladorCcaa instance = null;
	
	public static ControladorCcaa getInstance() {
		if (instance == null) {
			instance = new ControladorCcaa();
		}
		return instance;
	}
	
	 public static List<Ccaa> getAllCcaas() {
	        System.out.println("Obteniendo todas las ccaa de la colección");
	 
	        // Performing a read operation on the collection.
	        FindIterable<Document> fi = col.find();
	        MongoCursor<Document> cursor = fi.iterator();

	        List<Ccaa> allCcaa = new ArrayList<Ccaa>();
	        try {
	            while(cursor.hasNext()) {
	            	allCcaa.add(documentToCcaa(cursor.next()));
	            }
	        } finally {
	            cursor.close();
	        }
	        
	        return allCcaa;
	    }
	 
	 
	 private static Ccaa documentToCcaa(Document doc) {
		 Ccaa ccaa = new Ccaa();
	    	ccaa.setParent_code(doc.getString("parent_code"));
	    	ccaa.setCode(doc.getString("code"));
	    	ccaa.setLabel(doc.getString("label"));
	    	return ccaa;
	    }
	 
	 public static void getSelectiveDocument(MongoCollection<Document> col) {
	    	System.out.println("Filtrando elementos de una colección");
	 
	        // Performing a read operation on the collection.
	        FindIterable<Document> fi = col.find(Filters.eq("label", "Andalucía"));        
	        MongoCursor<Document> cursor = fi.iterator();
	        try {
	            while(cursor.hasNext()) {
	            	System.out.println(cursor.next().toJson());
	            }
	        } finally {
	            cursor.close();
	        }
	    }
	 
	 public static Ccaa findById(int n) {
	        Ccaa p = null;
	        String code = null;
	        if (n < 10) {
	            code = "0"+ String.valueOf(n);
	        } else {
				code = String.valueOf(n);
			}
	        System.out.println("Filtrando elementos de una colección");

	        // Performing a read operation on the collection.
	        FindIterable<Document> fi = col.find(Filters.eq("code", code));
	        MongoCursor<Document> cursor = fi.iterator();
	        try {
	            while (cursor.hasNext()) {
	                p = documentToCcaa(cursor.next());
	            }
	        } finally {
	            cursor.close();
	        }
	        return p;

	    }

	 public static void updateDocument (String code,String pr) {
	        try {
	            Document query = new Document().append("code",  code);
	            Bson update = Updates.combine(Updates.set("label",pr));

	            UpdateResult result = col.updateOne(query, update);
	            System.out.println("Modificados: " + result.getModifiedCount());

	        }
	        catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        
	    }
	 
}
