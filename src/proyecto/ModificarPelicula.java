package proyecto;

import proyecto.Comprobar;
import proyecto.Principal;

import java.io.IOException;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import static proyecto.Principal.br;
import static proyecto.Principal.coleccionPeliculas;


public class ModificarPelicula {

    public static void modificarNombrePelicula(String nombrePeliculaModificar) {
        String nombrePelicula = null;
        try {
            System.out.println("Introduzca el nombre que desee poner a la pelicula");
            nombrePelicula = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        if (Comprobar.comprobarNombrePelicula(nombrePelicula)) {
            coleccionPeliculas.updateMany(eq("nombre", nombrePeliculaModificar), set("nombre", nombrePelicula));
        } else {
            System.out.println("Introduzca un nombre de pelicula correcto");
        }
    }

    public static void modificarDirectorPelicula(String nombrePeliculaModificar) {
        String nombreDirector = null;
        try {
            System.out.println("Introduzca el nombre del director que realizo la pelicula");
            nombreDirector = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        if (Comprobar.comprobarNombreDirectorPelicula(nombreDirector)) {
            coleccionPeliculas.updateMany(eq("nombre", nombrePeliculaModificar), set("director", nombreDirector));
        } else {
            System.out.println("Nombre de director no valido");
        }
    }

    public static void modificarSipnosisPelicula(String nombrePeliculaModificar) {
        String sipnosis = null;
        try {
            System.out.println("Introduzca la sipnosis de la pelicula");
            sipnosis = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        coleccionPeliculas.updateMany(eq("nombre", nombrePeliculaModificar), set("sipnosis", sipnosis));
    }

    public static void modificarDuracionPelicula(String nombrePeliculaModificar) {
        String duracion = null;
        int duracionValida = 0;
        try {
            System.out.println("Introduzca la duracion de la pelicula");
            duracion = br.readLine();
            if (Comprobar.comprobarNumeroMenu(duracion)) {
                duracionValida = Integer.parseInt(duracion);
            }
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        if (Comprobar.comprobarDuracionPelicula(duracionValida)) {
            coleccionPeliculas.updateMany(eq("nombre", nombrePeliculaModificar), set("duracion", duracionValida+"min"));
        } else {
            System.out.println("La duracion no es valida");
        }
    }

    public static void modificarValoracionPelicula(String nombrePeliculaModificar) {
        double valoracionValida = 0;
        String valoracion;
        try {
            System.out.println("Introduzca la valoracion de la pelicula");
            valoracion = br.readLine();
            if (Comprobar.comprobarNumeroDouble(valoracion)) {
                valoracionValida = Double.parseDouble(valoracion);
            }
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        if (Comprobar.comprobarValoracionPelicula(valoracionValida)) {
            coleccionPeliculas.updateMany(eq("nombre", nombrePeliculaModificar), set("valoracion", valoracionValida));
        } else {
            System.out.println("La valoracion no es valida");
        }

    }

    public static void modificarFechaEstrenoPelicula(String nombrePeliculaModificar) {
        String fechaPelicula = null;
        try {
            System.out.println("Introduzca la fecha de la pelicula (__-__-____)");
            fechaPelicula = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        if (Comprobar.comprobarFechaPelicula(fechaPelicula)) {
            coleccionPeliculas.updateMany(eq("nombre", nombrePeliculaModificar), set("fecha", fechaPelicula));
        } else {
            System.out.println("Formato de fecha no valido");
        }
    }

    public static void modificarPublicoPelicula(String nombrePeliculaModificar) {
        boolean mayorDeEdadValido = false;
        String mayorDeEdad;
        try {
            System.out.println("Introduzca si la pelicula es mayor de 18 anio (True o False)");
            mayorDeEdad = br.readLine();
            if (Comprobar.comprobarBoolean(mayorDeEdad)) {
                mayorDeEdadValido = Boolean.parseBoolean(mayorDeEdad);
            }
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        coleccionPeliculas.updateMany(eq("nombre", nombrePeliculaModificar), set("publico", mayorDeEdadValido));
    }


}
