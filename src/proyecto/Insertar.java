package proyecto;

import org.bson.Document;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;
import static proyecto.Principal.*;

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
                        System.err.println("Esta pelicula ya existe");
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
            if (Comprobar.comprobarNombreDirectorPelicula(director)) {
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
                    valoracionValida = Double.parseDouble(valoracion);
                    if (Comprobar.comprobarValoracionPelicula(valoracionValida)){
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
                    if(Comprobar.comprobarDuracionPelicula(duracionValida)){
                        seguir=false;
                    }else{
                        System.out.println("Introduzca una duración valida (La duracion debe ser mayor de 30 min)");
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
        nuevaPelicula.put("director", director);
        nuevaPelicula.put("mayorDeEdad", mayorDeEdad);
        nuevaPelicula.put("valoracion", valoracionValida);
        nuevaPelicula.put("duracion", duracionValida + " min");
        coleccionPeliculas.insertOne(nuevaPelicula);
        boolean bandera = true;
        while (bandera) {
            System.out.println("Introduce el género de la película: ");
            try {
                genero = br.readLine();
                if (!Comprobar.comprobarNombreGenero(genero)) {
                    if (!Comprobar.comprobarGeneroExistente(genero)) {
                        insertarGenero(genero, nombrePelicula);
                    } else {
                        coleccionPeliculas.updateMany(eq("nombre",nombrePelicula), push("generos", genero));
                        coleccionGenero.updateMany(eq("nombre", genero), push("peliculas", nombrePelicula));
                    }
                } else {
                    System.out.println("Introduce un nombre de un genero valido");
                }
                seguir = false;
                while (!seguir) {
                    System.out.println("¿Quieres insertar otro género?(Y/N)");
                    continuar = br.readLine();
                    if (Comprobar.comprobarSeguirString(continuar)) {
                        if (continuar.equalsIgnoreCase("Y")) {
                            bandera = true;
                            seguir = true;
                        } else if (continuar.equalsIgnoreCase("N")) {
                            bandera = false;
                            System.out.println("Se ha insertado la pelicula " + nombrePelicula);
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
        String nombreGenero = null, descripcionGenero = null;
        boolean seguir = false;
        while (!seguir) {
            try {
                System.out.println("Introduzca el nombre que desee poner al genero");
                nombreGenero = br.readLine();
                if (Comprobar.comprobarNombreGenero(nombreGenero)) {
                    seguir = true;
                } else {
                    System.out.println("Introduzca un nombre de un genero valido");
                }
            } catch (IOException ioe) {
                System.out.println("Error");
            }
        }
        while (seguir) {
            try {
                System.out.println("Introduzca la descripcion que desee poner al genero");
                descripcionGenero = br.readLine();
                if (Comprobar.comprobarDescripcionGenero(descripcionGenero)) {
                    seguir = false;
                } else {
                    System.out.println("Introduce una descripcion valida");
                }
            } catch (IOException ioe) {
                System.out.println("Error");
            }
        }
        Document nuevoGenero = new Document();
        nuevoGenero.put("nombre", nombreGenero);
        nuevoGenero.put("descripcion", descripcionGenero);
        coleccionGenero.insertOne(nuevoGenero);

    }

    public static void insertarGenero(String nombreGenero, String nombrePelicula) {
        String descripcionGenero = null;
        boolean seguir = true;
        while (seguir) {
            try {
                System.out.println("Introduzca la descripcion que desee poner al genero");
                descripcionGenero = br.readLine();
                if (Comprobar.comprobarDescripcionGenero(descripcionGenero)) {
                    seguir = false;
                } else {
                    System.out.println("Introduce una descripcion valida");
                }
            } catch (IOException ioe) {
                System.out.println("Error");
            }
        }
        Document nuevoGenero = new Document();
        nuevoGenero.put("nombre", nombreGenero);
        nuevoGenero.put("descripcion", descripcionGenero);
        coleccionGenero.insertOne(nuevoGenero);
        coleccionGenero.updateMany(eq("nombre", nombreGenero), push("Peliculas", nombrePelicula));
    }
}
