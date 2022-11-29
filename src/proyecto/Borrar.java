package proyecto;

import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static proyecto.Principal.*;

public class Borrar {

    public static void borrarPelicula() { //Metodo para borrar una pelicula
        List<Document> consultaPeliculas = coleccionPeliculas.find().into(new ArrayList<>());
        String nombrePelicula = null;
        System.out.println("Listado de peliculas:");
        for (int i = 0; i < consultaPeliculas.size(); i++) {
            Document pelicula = consultaPeliculas.get(i);
            System.out.println(i + 1 + ". " + pelicula.getString("nombre"));
        }
        System.out.println("Introduzca SALIR si desee salir");
        try {
            System.out.println("Introduzca el nombre de la pelicula que desee borrar");
            nombrePelicula = br.readLine();
        } catch (IOException ioe) {
            System.err.println("Error de E/S");
        }
        if (nombrePelicula.equalsIgnoreCase("salir")) { //Comprueba si el usuario ha introducido salir
            System.out.println("SALIENDO...");
            System.out.println();
        } else {
            if (!Comprobar.comprobarPeliculaExistente(nombrePelicula)) {
                System.err.println("Esta pelicula no existe");
                borrarPelicula();
            } else {
                for (int i = 0; i < consultaPeliculas.size(); i++) {
                    Document peliculas = consultaPeliculas.get(i);
                    if (peliculas.getString("nombre").equalsIgnoreCase(nombrePelicula)) {
                        DeleteResult delPelicula = coleccionGenero.deleteOne(eq("peliculas", peliculas.getString("nombre")));
                    }
                }
                DeleteResult del = coleccionPeliculas.deleteMany(eq("nombre", nombrePelicula));
                System.out.println("Se ha borrado la pelicula");
            }
        }
    }

    public static void borrarGenero() { //Metodo para borrar un genero
        List<Document> consultaGenero = coleccionGenero.find().into(new ArrayList<>());
        String nombreGenero = null;
        System.out.println("Listado de peliculas:");
        for (int i = 0; i < consultaGenero.size(); i++) {
            Document genero = consultaGenero.get(i);
            System.out.println(i + 1 + ". " + genero.getString("nombre"));
        }
        System.out.println("Introduzca SALIR si desee salir");
        try {
            System.out.println("Introduzca el nombre del género que desee borrar");
            nombreGenero = br.readLine();
        } catch (IOException ioe) {
            System.err.println("Error de E/S");
        }
        if (nombreGenero.equalsIgnoreCase("salir")) {
            System.out.println("SALIENDO...");
            System.out.println();
        } else {
            if (!Comprobar.comprobarGeneroExistente(nombreGenero)) {
                System.err.println("Este genero no existe");
            } else {
                for (int i = 0; i < consultaGenero.size(); i++) {
                    Document generos = consultaGenero.get(i);
                    if (generos.getString("nombre").equalsIgnoreCase(nombreGenero)) {
                        DeleteResult delID = coleccionPeliculas.deleteOne(eq("generos", generos.getString("nombre")));
                    }
                }
                DeleteResult del = coleccionGenero.deleteOne(eq("nombre", nombreGenero));
                System.out.println("Se ha borrado el genero");
            }
        }
    }
}
