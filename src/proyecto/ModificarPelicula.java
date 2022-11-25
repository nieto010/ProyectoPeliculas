package proyecto;

import java.io.IOException;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;


public class ModificarPelicula {
    Principal p = new Principal();

    public void modificarNombrePelicula(String nombrePeliculaModificar) {
        String nombrePelicula = null;
        try {
            System.out.println("Introduzca el nombre que desee poner a la pelicula");
            nombrePelicula = p.br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        p.coleccionPeliculas.updateOne(eq("nombre", nombrePeliculaModificar), set("nombre", nombrePelicula));
    }

    public void modificarDirectorPelicula(String nombrePeliculaModificar) {
        String nombreDirector = null;
        try {
            System.out.println("Introduzca el nombre del director que realizo la pelicula");
            nombreDirector = p.br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        p.coleccionPeliculas.updateOne(eq("nombre", nombrePeliculaModificar), set("director", nombreDirector));
    }

    public void modificarSipnosisPelicula(String nombrePeliculaModificar) {
        String sipnosis = null;
        try {
            System.out.println("Introduzca la sipnosis de la pelicula");
            sipnosis = p.br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        p.coleccionPeliculas.updateOne(eq("nombre", nombrePeliculaModificar), set("sipnosis", sipnosis));
    }

    public void modificarDuracionPelicula(String nombrePeliculaModificar) {
        int duracion = 0;
        try {
            System.out.println("Introduzca la duracion de la pelicula");
            duracion = Integer.parseInt(p.br.readLine());
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        p.coleccionPeliculas.updateOne(eq("nombre", nombrePeliculaModificar), set("duracion", duracion));
    }

    public void modificarValoracionPelicula(String nombrePeliculaModificar) {
        double valoracion = 0;
        try {
            System.out.println("Introduzca la valoracion de la pelicula");
            valoracion = Double.parseDouble(p.br.readLine());
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        p.coleccionPeliculas.updateOne(eq("nombre", nombrePeliculaModificar), set("valoracion", valoracion));
    }

    public void modificarFechaEstrenoPelicula(String nombrePeliculaModificar) {
        String fechaPelicula = null;
        try {
            System.out.println("Introduzca la fechaPelicula de la pelicula");
            fechaPelicula = p.br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        p.coleccionPeliculas.updateOne(eq("nombre", nombrePeliculaModificar), set("fecha", fechaPelicula));
    }

    public void modificarPublicoPelicula(String nombrePeliculaModificar) {
        boolean mayorDeEdad = false;
        try {
            System.out.println("Introduzca si la pelicula es mayor de 18 anio");
            mayorDeEdad = Boolean.parseBoolean(p.br.readLine());
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        p.coleccionPeliculas.updateOne(eq("nombre", nombrePeliculaModificar), set("publico", mayorDeEdad));
    }


}
