package proyecto;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Principal {
   static MongoClient cliente = new MongoClient();
   static MongoDatabase db = cliente.getDatabase("peliculas");
   static MongoCollection<Document> coleccionPeliculas = db.getCollection("peliculas");
    public static MongoCollection<Document> coleccionGenero = db.getCollection("generos");
    static List<Document> consultaPeliculas =  coleccionPeliculas.find().into(new ArrayList<>());
    static List<Document> consultaGenero =  coleccionGenero.find().into(new ArrayList<>());
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        modificarGenero();
    }

    private static void menu() throws NumberFormatException, IOException {
        int opcion = 0;
        do {
            System.out.println("-MENU PRINCIPAL-");
            System.out.println("1. Menu Usuario");
            System.out.println("2. Menu Administrador");
            System.out.println("0. Salir");
            try {
                System.out.println("Seleccione una opcion: ");
                opcion = Integer.parseInt(br.readLine());
            } catch (InputMismatchException ime) {
                opcion = 100;
            }
            switch (opcion) {
                case 1:
//Menu
                    MenuUsuario();
                    break;
                case 2:
//Menu administrador con todos los metodos del CRUD.
                    MenuAdministrador();
                    break;
                case 0:
                    System.out.println("Fin del programa");
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 0);

    }

    private static void MenuAdministrador() throws NumberFormatException, IOException {
        int opcion = 0;
        do {
            System.out.println("-MENU ADMINISTRADOR-");
            System.out.println("1. Menu del CRUD de películas");
            System.out.println("2. Menu del CRUD de género");
            System.out.println("0. Salir");
            try {
                System.out.println("Seleccione una opcion: ");
                opcion = Integer.parseInt(br.readLine());
            } catch (InputMismatchException ime) {
                opcion = 100;
            }
            switch (opcion) {
                case 1:
//leer los documentos de la coleccion
                    MenuCRUDPelicula();
                    break;
                case 2:
//menu crear
                    MenuCRUDGenero();
                    break;
                case 0:
                    System.out.println("Fin del programa");
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 0);

    }

    private static void MenuCRUDGenero() throws NumberFormatException, IOException {
        int opcion = 0;
        do {
            System.out.println("-MENU PELICULAS (CRUD)-");
            System.out.println("1. Consultar películas");
            System.out.println("2. Metodo crear películas");
            System.out.println("3. Metodo borrar películas");
            System.out.println("4. Metodo modificar películas");
            System.out.println("0. Salir");
            try {
                System.out.println("Seleccione una opcion: ");
                opcion = Integer.parseInt(br.readLine());
            } catch (InputMismatchException ime) {
                opcion = 100;
            }
            switch (opcion) {
                case 1:
                    ConsultarPeliculas();
                    break;
                case 2:
                    CrearPeliculas();
                    break;
                case 3:
                    BorrarPelículas();
                    break;
                case 4:
                    ModificarPeliculas();
                    break;
                case 0:
                    System.out.println("Fin del programa");
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 0);

    }

    private static void MenuCRUDPelicula() throws NumberFormatException, IOException {
        int opcion = 0;
        do {
            System.out.println("-MENU PELICULAS (CRUD)-");
            System.out.println("1. Consultar géneros");
            System.out.println("2. Metodo crear géneros");
            System.out.println("3. Metodo borrar géneros");
            System.out.println("4. Metodo modificar géneros");
            System.out.println("0. Salir");
            try {
                System.out.println("Seleccione una opcion: ");
                opcion = Integer.parseInt(br.readLine());
            } catch (InputMismatchException ime) {
                opcion = 100;
            }
            switch (opcion) {
                case 1:
                    ConsultarGéneros();
                    break;
                case 2:
                    CrearGéneros();
                    break;
                case 3:
                    BorrarGéneros();
                    break;
                case 4:
                    ModificarGéneros();
                    break;
                case 0:
                    System.out.println("Fin del programa");
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 0);

    }

    private static void MenuUsuario() throws NumberFormatException, IOException {
        int opcion = 0;
        do {
            System.out.println("-MENU USUARIO-");
            System.out.println("1. Buscar película por nombre");
            System.out.println("2. Buscar película por género");
            System.out.println("3. Ordenar películas por valoración");
            System.out.println("0. Salir");
            try {
                System.out.println("Seleccione una opcion: ");
                opcion = Integer.parseInt(br.readLine());
            } catch (InputMismatchException ime) {
                opcion = 100;
            }
            switch (opcion) {
                case 1:
//leer los documentos de la coleccion
                    MenuCRUDPelicula();
                    break;
                case 2:
//menu crear
                    MenuCRUDGenero();
                    break;
                case 0:
                    System.out.println("Fin del programa");
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 0);
    }
}


    public static void modificarPelicula() {
       String nombrePeliculaModificar;
        try {
        System.out.println("Introduzca el nombre de la película que desee modificar");
        nombrePeliculaModificar = br.readLine();
            if (!comprobarPeliculaExistente(nombrePeliculaModificar)) {
                System.err.println("El nombre de la pelicula no existe");
                modificarPelicula();
            }
       } catch (IOException ioe) {
           System.out.println("Error de E/S");
       }

    }

    public static void modificarGenero() {
        String nombreGeneroModificar;
        try {
            System.out.println("Introduzca el nombre del género que desee modificar");
            nombreGeneroModificar = br.readLine();
            if (!comprobarGeneroExistente(nombreGeneroModificar)) {
                System.err.println("El nombre del genero no existe");
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

    public void borrarPelicula() {
        String nombrePelicula = null;
        try {
            System.out.println("Introduzca el nombre de la pelicula que desee borrar");
            nombrePelicula = br.readLine();
        } catch (IOException ioe) {
            System.err.println("Error de E/S");
        }
        if (!comprobarPeliculaExistente(nombrePelicula)){
            System.err.println("Esta pelicula no existe");
            borrarPelicula();
        } else {
            DeleteResult del = coleccionPeliculas.deleteOne(eq("nombre", nombrePelicula));
            System.out.println("Se ha borrado la pelicula");
        }
    }

    public void borrarGenero() {
        String nombreGenero = null;
        try {
            System.out.println("Introduzca el nombre del género que desee borrar");
            nombreGenero = br.readLine();
        } catch (IOException ioe) {
            System.err.println("Error de E/S");
        }
        if (!comprobarGeneroExistente(nombreGenero)) {
            System.err.println("Este genero no existe");
        } else {
            DeleteResult del = coleccionGenero.deleteOne(eq("nombre", nombreGenero));
            System.out.println("Se ha borrado el genero");
        }
    }
}
