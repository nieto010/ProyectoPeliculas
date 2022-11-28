package proyecto;

import org.bson.Document;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;
import static com.mongodb.client.model.Updates.set;
import static proyecto.Principal.br;
import static proyecto.Principal.coleccionPeliculas;

public class Insertar{

    public static void insertarPelicula() {
        String nombrePelicula = null, sinopsis = null, fecha = null, director = null,mayorDeEdad = null,valoracion = null, duracion = null,genero = null, continuar;
        int duracionValida = 0;
        double valoracionValida = 0;
        Boolean seguir = false;
        while (!seguir) {
            try {
                System.out.println("Introduzca el nombre que desee poner a la pelicula");
                nombrePelicula = br.readLine();
                if (Comprobar.comprobarNombrePelicula(nombrePelicula)) {
                    if (!Comprobar.comprobarPeliculaExistente(nombrePelicula)) {
                        seguir = true;
                    } else {
                        System.out.println("Esta pelicula ya existe");
                    }
                } else {
                    System.out.println("Introduce un nombre valido");
                }
            } catch (IOException ioe) {
                System.out.println("Error");
            }
        }
        try {
            System.out.println("Introduce la sinopsis de la película: ");
            sinopsis = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
    while (seguir) {
        try {
            System.out.println("Introduce la fecha de la película(dd/mm/yyyy)");
            fecha = br.readLine();
            if (Comprobar.comprobarFechaPelicula(fecha)) {
                seguir = false;
            } else {
                System.out.println("El formato de la fecha no es valido");
            }
        } catch (IOException ioe) {
            System.out.println("Error");
        }
    }
    while (!seguir) {
        System.out.println("Introduce el nombre del director: ");
        try {
            director = br.readLine();
            if (!Comprobar.comprobarNombreDirectorPelicula(director)) {
                seguir = true;
            } else {
                System.out.println("El nombre del director no es valido");
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
    while (seguir) {
        System.out.println("Introduce si es para mayores de edad o no: (true or false)");
        try {
            mayorDeEdad = br.readLine();
            if(Comprobar.comprobarBoolean(mayorDeEdad)) {
                seguir=false;

            } else{
                System.out.println("Introduza true o false por favor");
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
        while (!seguir){
            System.out.println("Introduce la valoración: ");
            try {
                valoracion = br.readLine();
                if (Comprobar.comprobarNumeroDouble(valoracion)) {
                    if (!Comprobar.comprobarValoracionPelicula(valoracionValida)){
                        seguir=true;
                    } else {
                        System.out.println("Introduzca una valoración valida");
                    }
                } else {
                    System.out.println("Introduzca un numero, por favor");
                }

            } catch (IOException e) {
                System.out.println("Error");
            }
        }
        while (seguir){
            System.out.println("Introduce la duración de la película: ");
            try{
                duracion= br.readLine();
                if (Comprobar.comprobarDuracionPelicula(duracion)){
                    duracionValida = Integer.parseInt(duracion);
                    if(!Comprobar.comprobarDuracionPelicula(duracionValida)){
                        seguir=false;
                    }else{
                        System.out.println("Introduzca una duración valida");
                    }
                } else {
                    System.out.println("Introduzca un numero por favor");
                }

            }catch (IOException e){
                System.out.println("Error");
            }
        }
        Document nuevaPelicula = new Document();
        nuevaPelicula.put("nombre", nombrePelicula);
        nuevaPelicula.put("sinopsis", sinopsis);
        nuevaPelicula.put("fecha", fecha);
        nuevaPelicula.put("mayorDeEdad", mayorDeEdad);
        nuevaPelicula.put("valoracion", valoracionValida);
        nuevaPelicula.put("duracion", duracionValida);
        coleccionPeliculas.insertOne(nuevaPelicula);
        boolean bandera = true;
        while (bandera) {
            System.out.println("Introduce el género de la película: ");
            try {
                genero = br.readLine();
                if (!Comprobar.comprobarNombreGenero(genero)) {
                    if (!Comprobar.comprobarGeneroExistente(genero)) {
                        insertarGenero();
                    } else {
                        coleccionPeliculas.updateMany(eq("nombre",nombrePelicula), push("generos", genero));
                    }
                } else {
                    System.out.println("Introduce un nombre de un genero valido");
                }
                while (!seguir) {
                    System.out.println("¿Quieres insertar otro género?(Y/N)");
                    continuar = br.readLine();
                    if (Comprobar.comprobarSeguirString(continuar)) {
                        if (continuar.equalsIgnoreCase("Y")) {
                            bandera = true;
                        } else if (continuar.equalsIgnoreCase("N")) {
                            bandera = false;
                            break;
                        }
                    } else {
                        System.out.println("Introduce Y o N");
                        seguir = false;
                    }
                }

            } catch (IOException e) {
                System.out.println("Error");
            }
            
        }


    }

    }
    
    public static void insertarGenero() {
        
    }
}
