package proyecto;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Comprobar {

    public static boolean comprobarNombrePelicula(String nombre) {
        boolean comprobado = false;
        Pattern pat = Pattern.compile("[^ + - * / = % & # ! ? ^  “ ‘ ~ \\ | < > ( ) [ ] { } : ; . , $ · € ~ ¬ { } € ]");
        Matcher m = pat.matcher(nombre);
        if(m.matches()){
            comprobado = false;
        }else{
            comprobado = true;
        }
        return comprobado;
    }

    public static boolean comprobarFechaPelicula(String fecha) {
        final String regex = "^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$";

        final Pattern fechaPATTERN = Pattern.compile(regex);
        Matcher matcher = fechaPATTERN.matcher(fecha);
        return matcher.matches();
    }

    public static boolean comprobarNombreDirectorPelicula(String nombreDirector) {
        if (nombreDirector.matches("[a-zA-Z]")){
            return true;
        } else {
            return false;
        }
    }

    public static boolean comprobarDuracionPelicula(int duracion) {
        if (duracion > 30) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean comprobarValoracionPelicula(double valoracion) {
        if (valoracion < 5 && valoracion > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean comprobarNombreGenero(String nombreGenero) {
        return nombreGenero.matches("[a-zA-Z]");
    }

    public static boolean comprobarDescripcionGenero(String descripcionGenero) {
        return descripcionGenero.matches("a-zA-Z_0-9");
    }

    public static boolean comprobarNumeroMenu(String opcionMenu) {
        return opcionMenu.matches("[0-9]");
    }

    public static boolean comprobarNumeroDouble(String numero){
        return numero.matches("[0-9].[0-9]") || numero.matches("[0-9]");
    }

    public static boolean comprobarBoolean(String booleano) {
        return booleano.equalsIgnoreCase("true") || booleano.equalsIgnoreCase("false");
    }

    public static boolean comprobarPeliculaExistente(String nombrePelicula) {
        int i = 0;
        boolean encontrado = false;
        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("test");
        MongoCollection<Document> coleccion = db.getCollection("peliculas");
        ArrayList<Document> consultaPeliculas = coleccion.find().into(new ArrayList<Document>());
        while (i < consultaPeliculas.size() && !encontrado) {
            Document pelicula = consultaPeliculas.get(i);
            if (nombrePelicula.equalsIgnoreCase(pelicula.getString("nombre"))){
                System.out.println("encontrado");
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
        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("test");
        MongoCollection<Document> coleccion = db.getCollection("generos");
        ArrayList<Document> consultaPeliculas = coleccion.find().into(new ArrayList<Document>());
        while (i < consultaPeliculas.size() && !encontrado) {
            Document genero = consultaPeliculas.get(i);
            if (nombreGenero.equalsIgnoreCase(genero.getString("nombre"))){
                System.out.println("encontrado");
                encontrado = true;
            } else {
                i++;
            }
        }
        return encontrado;
    }

    public static boolean comprobarSeguirString(String seguir) {
        return seguir.matches("[YNyn]");
    }

    public static boolean comprobarDuracionPelicula(String duracion) {
        return duracion.matches("[0-9]") || duracion.matches("[0-9][0-9]") || duracion.matches("[0-9][0-9][0-9]");
    }
}
