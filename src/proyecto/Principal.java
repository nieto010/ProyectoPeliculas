package proyecto;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Principal {
   static MongoClient cliente = new MongoClient();
   static MongoDatabase db = cliente.getDatabase("peliculas");
   static MongoCollection<Document> coleccionPeliculas = db.getCollection("peliculas");
    static MongoCollection<Document> coleccionGenero = db.getCollection("generos");
    static List<Document> consultaPeliculas =  coleccionPeliculas.find().into(new ArrayList<>());
    static List<Document> consultaGenero =  coleccionGenero.find().into(new ArrayList<>());
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        modificarGenero();
    }
    public static void modificarPelicula() {
       String nombrePeliculaModificar;
        try {
        System.out.println("Introduzca el nombre de la película que desee modificar");
        nombrePeliculaModificar = br.readLine();
            if (!comprobarPeliculaExistente(nombrePeliculaModificar)) {
                System.out.println("El nombre de la pelicula no existe");
                modificarPelicula();
            }
       } catch (IOException ioe) {
           System.out.println("Hola");
       }

    }

    public static void modificarGenero() {
        String nombreGeneroModificar;
        try {
            System.out.println("Introduzca el nombre del género que desee modificar");
            nombreGeneroModificar = br.readLine();
            if (!comprobarGeneroExistente(nombreGeneroModificar)) {
                System.out.println("El nombre del genero no existe");
                modificarGenero();
            }
        } catch (IOException ioe) {
            System.out.println();
        }
    }

    public static void formatoFecha(String fecha) {
        String.format(fecha, "--/--/----");

    }

    public static boolean comprobarPeliculaExistente(String nombrePelicula) {
        int i = 0;
        boolean encontrado = false;
        while (i < consultaPeliculas.size() && !encontrado) {
            Document pelicula = consultaPeliculas.get(i);
            if (nombrePelicula.equalsIgnoreCase(pelicula.getString("nombre"))){
                encontrado = true;
            } else {
                i++;
            }
        }
        return encontrado;
    }

    public static boolean comprobarGeneroExistente(String nombreGenero) {
        int i = 0;
        boolean encontrado = false;
        while (i < consultaGenero.size() && !encontrado) {
            Document genero = consultaGenero.get(i);
            if (nombreGenero.equalsIgnoreCase(genero.getString("nombre"))){
                encontrado = true;
            } else {
                i++;
            }
        }
        return encontrado;
    }
}
