package proyecto;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import static java.util.Arrays.asList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Insertar1 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String nombre, sinopsis, fecha, director, genero, respuestaGenero, idGenero, respuestaInsertar, nombreGenero, descripcion,generarIdGeneros;
    static double valoracion;
    static int duracion, mayoresEdad, generarIdPelicula ;
    static ArrayList<String> verGenerosDisponibles = new ArrayList<>();
    static List<String> insertarGenero = new ArrayList<>();
    static int contador = 0;
    static int contadorGenero = 0;

    public static void main(String[] args) {
        //generarGenero();
        insertarPelicula();
    }


    public static void insertarPelicula(){
        buscarIdDisponible();
        insertarGenero.clear();
        ArrayList<String> guardarGenero = new ArrayList<>();
        boolean seguir = true;
        boolean comprobar = false;
        boolean comprobarGenero = true;
        while(seguir){
            try{
                System.out.println("Introduce el nombre de la película: ");
                nombre = br.readLine();
                if (Comprobar.comprobarNombrePelicula(nombre)){
                if(Comprobar.comprobarPeliculaExistente(nombre)){
                    System.out.println("La pelìcula ya existe");
                    seguir = true;
                }else {
                    System.out.println("Introduce la sinopsis de la película: ");
                    sinopsis = br.readLine();
                    System.out.println("Introduce la fecha de la película: ");
                    fecha = br.readLine();
                    System.out.println("Introduce el nombre del director: ");
                    director = br.readLine();
                    System.out.println("Introduce si es para mayores de edad o no: ");
                    mayoresEdad = Integer.parseInt(br.readLine());
                    System.out.println("Introduce la valoración: ");
                    valoracion = Double.parseDouble(br.readLine());
                    System.out.println("Introduce la duración de la pelicula: ");
                    duracion = Integer.parseInt(br.readLine());
                }
                    verGenerosDisponibles();
                    while(comprobarGenero = true){
                        byte comprobarGenero32 = 0;
                        System.out.println("Introduce el género de la película: ");
                        genero = br.readLine();
                        if(guardarGenero.contains(genero)){
                            System.out.println("El genero ya ha sido introducido");
                            comprobarGenero = true;
                        }else{
                            for (int i=0; i<verGenerosDisponibles.size(); i++){
                                String generosDisponibles = verGenerosDisponibles.get(i);
                                if(genero.equalsIgnoreCase(generosDisponibles) && contadorGenero == 0){
                                    comprobar = true;
                                    //el genero ya existe por lo tanto hago un método para obtener el id del género
                                    determinarGenero(genero);
                                    //almacenar el id del género en una variable
                                    insertarGenero.add(idGenero);
                                    //inserto la película
                                    Document peliculas = new Document();
                                    peliculas.put("_id", generarIdPelicula);
                                    peliculas.put("nombre", nombre);
                                    peliculas.put("sinopsis", sinopsis);
                                    peliculas.put("fecha", fecha);
                                    peliculas.put("director", director);
                                    peliculas.put("publico", mayoresEdad);
                                    peliculas.put("valoracion", valoracion);
                                    peliculas.put("duracion", duracion);
                                    peliculas.put("genero",  asList(new Document().append("_id", insertarGenero)));
                                    Principal.coleccionPeliculas.insertOne(peliculas);
                                    generarIdGenero(genero, generarIdPelicula);
                                    idGenero = "";
                                    System.out.println("genero insertado");
                                    guardarGenero.add(genero);
                                    //aumento el contador ya que no necesita insertarse más veces
                                    comprobarGenero32 = 1;
                                    contadorGenero = 1;
                                }else if(genero.equalsIgnoreCase(generosDisponibles) && contadorGenero == 1){
                                    insertarGenero.clear();
                                    determinarGenero(genero);
                                    insertarGenero.add(idGenero);
                                    Bson filter = Filters.eq("nombre", nombre);
                                    Bson updates = Updates.push("genero",  new Document().append("_id", insertarGenero));
                                    UpdateOptions options = new UpdateOptions().upsert(true);
                                    Principal.coleccionPeliculas.updateOne(filter, updates,options);
                                    generarIdGenero(genero, generarIdPelicula);
                                    idGenero = "";
                                    comprobarGenero32 = 1;
                                    guardarGenero.add(genero);
                                    //el documento se tiene que actualizar+
                                    //coleccion.findOneAndUpdate(eq("nombre", nombre), set("genero", asList(new Document().set("_id", insertarGenero))));
                                    System.out.println("genero insertado");
                                }
                            }
                            if(comprobarGenero32 == 0){
                                System.out.println("El género introducido no existe. Inserta este nuevo género primero");
                            }else{

                            }
                            System.out.println("¿Quieres insertar otro género?(Y/N)");
                            respuestaGenero = br.readLine();
                            if(respuestaGenero.equals("Y")){
                                comprobarGenero = true;
                            }else{
                                comprobarGenero = false;
                                break;
                            }
                        }
                    }
                    System.out.println("¿Quieres seguir insertando peliculas?(Y/N)");
                    respuestaInsertar = br.readLine();
                    if(respuestaInsertar.equalsIgnoreCase("Y")){
                        seguir = true;
                    }else{
                        seguir = false;
                        break;
                    }
                }
            }catch(NumberFormatException nfe){
                seguir = true;
            }catch(IOException ioe){
                seguir = true;
            }
        }
    }

    private static void generarIdGenero(String genero, int generarIdPelicula) {
        int i = 0;
        boolean encontrado = false;
        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("test");
        MongoCollection<Document> coleccion = db.getCollection("generos");
        ArrayList<Document> consultaGeneros = coleccion.find().into(new ArrayList<Document>());
        while (i < consultaGeneros.size() && !encontrado) {
            Document pelicula = consultaGeneros.get(i);
            if (genero.equalsIgnoreCase(pelicula.getString("nombre"))){
                //inserto en el idGnero el id del genero correspondiente.
                Bson filter = Filters.eq("nombre", genero);
                Bson updates = Updates.push("peliculas",  new Document().append("_id", generarIdPelicula));
                UpdateOptions options = new UpdateOptions().upsert(true);
                coleccion.updateOne(filter, updates,options);
                encontrado = true;
                if(contador == 0){
                }
                contador ++;
            } else {
                i++;
            }
        }
    }

    //metodo que genera el id de un genero
    private static void generarGenero(){
        boolean seguir = true;
        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("test");
        MongoCollection<Document> coleccion = db.getCollection("generos");
        Document generos = new Document();
        while (seguir){
            try{
                System.out.println("Introduce el nombre del género: ");
                nombreGenero = br.readLine();
                if(Comprobar.comprobarGeneroExistente(nombreGenero)){
                    System.out.println("El nombre del género existe");
                    seguir = true;
                }else{
                    System.out.println("Introduce la descripción del género: ");
                    descripcion = br.readLine();
                    insertarIdGenero();
                    generos.put("_id", generarIdGeneros);
                    generos.put("nombre", nombreGenero);
                    generos.put("descripcion", descripcion);
                    coleccion.insertOne(generos);
                    System.out.println("Género insertado correctamente");
                    //generos.put("genero",  asList(new Document()));
                    System.out.println("¿Quieres seguir insertando géneros?");
                    String respuesta = br.readLine();
                    if(respuesta.equalsIgnoreCase("Y")){
                        seguir = true;
                    }else{
                        seguir = false;
                    }
                }

            }catch(NumberFormatException nfe){
                seguir = true;
            }catch(IOException ioe){
                seguir = true;
            }
        }

    }

    //metodo para determinar el id del género
    private static boolean determinarGenero(String nombreGenero){
        int i = 0;
        Document peliculaInserccion = new Document();
        boolean encontrado = false;
        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("test");
        MongoCollection<Document> coleccion = db.getCollection("generos");
        ArrayList<Document> consultaGeneros = coleccion.find().into(new ArrayList<Document>());
        while (i < consultaGeneros.size() && !encontrado) {
            Document pelicula = consultaGeneros.get(i);
            if (nombreGenero.equalsIgnoreCase(pelicula.getString("nombre"))){
                //inserto en el idGnero el id del genero correspondiente.
                idGenero = pelicula.getString("_id");
                encontrado = true;
                if(contador == 0){
                }
                contador ++;
            } else {
                i++;
            }
        }
        return encontrado;
    }

    //insertar genero normal
    private static void insertarIdGenero(){
        boolean encontrado = false;
        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("test");
        MongoCollection<Document> coleccion = db.getCollection("generos");
        ArrayList<Document> consultaGeneros = coleccion.find().into(new ArrayList<Document>());
        generarIdGeneros = String.valueOf(consultaGeneros.size() + 1);
    }

    //busca el id que va a tener la pelicula
    private static void buscarIdDisponible(){
        boolean encontrado = false;
        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("test");
        MongoCollection<Document> coleccion = db.getCollection("peliculas");
        ArrayList<Document> consultaPeliculas = coleccion.find().into(new ArrayList<Document>());
        generarIdPelicula = consultaPeliculas.size() + 1;
    }


    //método para comprobar los géneros disponible antes de insertar
    public static void verGenerosDisponibles(){
        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("test");
        MongoCollection<Document> coleccion = db.getCollection("generos");
        ArrayList<Document> consultaGeneros = coleccion.find().into(new ArrayList<Document>());
        for(int i=0; i<consultaGeneros.size(); i++){
            Document pelicula = consultaGeneros.get(i);
            String peliculas = pelicula.getString("nombre");
            verGenerosDisponibles.add(peliculas);
            //System.out.println(verGenerosDisponibles.get(i));
        }
    }



}