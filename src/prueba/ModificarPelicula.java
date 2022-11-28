package prueba;

import proyecto.Principal;

import java.io.IOException;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import static proyecto.Principal.br;
import static proyecto.Principal.coleccionPeliculas;


public class ModificarPelicula {
    Principal p = new Principal();

    public static void modificarNombrePelicula(String nombrePeliculaModificar) {
        String nombrePelicula = null;
        try {
            System.out.println("Introduzca el nombre que desee poner a la pelicula");
            nombrePelicula = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
       // p.coleccionPeliculas.updateOne(eq("nombre", nombrePeliculaModificar), set("nombre", nombrePelicula));
    }

    public static void modificarDirectorPelicula(String nombrePeliculaModificar) {
        String nombreDirector = null;
        try {
            System.out.println("Introduzca el nombre del director que realizo la pelicula");
            nombreDirector = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        //p.coleccionPeliculas.updateOne(eq("nombre", nombrePeliculaModificar), set("director", nombreDirector));
    }

    public static void modificarSipnosisPelicula(String nombrePeliculaModificar) {
        String sipnosis = null;
        try {
            System.out.println("Introduzca la sipnosis de la pelicula");
            sipnosis = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        //p.coleccionPeliculas.updateOne(eq("nombre", nombrePeliculaModificar), set("sipnosis", sipnosis));
    }

    public static void modificarDuracionPelicula(String nombrePeliculaModificar) {
        int duracion = 0;
        try {
            System.out.println("Introduzca la duracion de la pelicula");
            duracion = Integer.parseInt(br.readLine());
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        coleccionPeliculas.updateOne(eq("nombre", nombrePeliculaModificar), set("duracion", duracion));
    }

    public static void modificarValoracionPelicula(String nombrePeliculaModificar) {
        double valoracion = 0;
        try {
            System.out.println("Introduzca la valoracion de la pelicula");
            valoracion = Double.parseDouble(br.readLine());
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        coleccionPeliculas.updateOne(eq("nombre", nombrePeliculaModificar), set("valoracion", valoracion));
    }

    public static void modificarFechaEstrenoPelicula(String nombrePeliculaModificar) {
        String fechaPelicula = null;
        try {
            System.out.println("Introduzca la fechaPelicula de la pelicula");
            fechaPelicula = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        coleccionPeliculas.updateOne(eq("nombre", nombrePeliculaModificar), set("fecha", fechaPelicula));
    }

    public static void modificarPublicoPelicula(String nombrePeliculaModificar) {
        boolean mayorDeEdad = false;
        try {
            System.out.println("Introduzca si la pelicula es mayor de 18 anio");
            mayorDeEdad = Boolean.parseBoolean(br.readLine());
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        coleccionPeliculas.updateOne(eq("nombre", nombrePeliculaModificar), set("publico", mayorDeEdad));
    }


}
