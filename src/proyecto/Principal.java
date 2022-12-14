package proyecto;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import static proyecto.Borrar.borrarGenero;
import static proyecto.Borrar.borrarPelicula;

public class Principal {
    static MongoClient cliente = new MongoClient();
    static MongoDatabase db = cliente.getDatabase("peliculas");
    public static MongoCollection<Document> coleccionPeliculas = db.getCollection("peliculas");
    public static MongoCollection<Document> coleccionGenero = db.getCollection("generos");
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        menu();
    }

    public static void cargaInicial() { //Carga inicial de 3 peliculas y 3 generos para trabajar con ellos
        Document genero1 = new Document();
        genero1.put("nombre", "Drama");
        genero1.put("descripcion", "El drama es un genero que trata situaciones generalmente no é¡epicas en un contexto serio");

        Document genero2 = new Document();
        genero2.put("nombre", "Misterio");
        genero2.put("descripcion", "El misterio es un genero que trata de investigaciones de crimenes");

        Document genero3 = new Document();
        genero3.put("nombre", "Superheroes");
        genero3.put("descripcion", "Peliculas que se basan en personas que tienen superpoderes y necesitan salvar el mundo");

        Document pelicula1 = new Document();
        pelicula1.put("nombre", "Cadena perpetua");
        pelicula1.put("sinopsis", "Primera realizacion cinematografica de un director que habia conseguido" +
                " cierto prestigio en la television, en la que adapto un atipico relato de Stephen King. A traves de" +
                " la tradicional estructura del melodrama carcelario, propone una fabula entre fantasiosa y voluntarista, con" +
                " imaginativos recovecos. Sin llegar a la excelencia, supuso una agradable sorpresa");
        pelicula1.put("fecha", "24/02/1995");
        pelicula1.put("director", "Frank Darabont");
        pelicula1.put("mayorDeEdad", true);
        pelicula1.put("valoracion", 4.2);
        pelicula1.put("duracion", "130min");
        pelicula1.put("generos", genero2.getString("nombre"));

        Document pelicula2 = new Document();
        pelicula2.put("nombre", "El padrino");
        pelicula2.put("sinopsis", "Adaptacion de una celebrada novela de Mario Puzo, en la que se revisaba la mitica mafiosa a traves de una mirada que pretendía comprender su razon de ser desde la Historia " +
                "y la tradicion ancestral. Con este prometedor material, Coppola construyo una de las grandes tragedias del cine contemporaneo, donde el cine " +
                "negro alcanzaba resonancias shakespearianas. Contra todo pronostico, sus secuelas estuvieron a su altura");
        pelicula2.put("fecha", "24/05/1972");
        pelicula2.put("director", "Francis Ford Coppola");
        pelicula2.put("mayorDeEdad", true);
        pelicula2.put("valoracion", 4.8);
        pelicula2.put("duracion", "90min");
        pelicula2.put("generos", genero1.getString("nombre"));

        Document pelicula3 = new Document();
        pelicula3.put("nombre", "El caballero oscuro");
        pelicula3.put("sinopsis", "El Caballero Oscuro' desmiente su condicion inicial de blockbuster revienta " +
                "taquilla con una serie de argumentos esteticos, interpretativos, emocionales, filosoficos e " +
                "ideologicos que la propulsan muy por encima de cualquier otra adaptacion de comic que hayamos visto " +
                "hasta la fecha. Sin renunciar a las concesiones propias del cine espectaculo, pero dotadas estas " +
                "de una elegancia, tenebrosidad y radicalidad alejada de la orbita mainstream, Christopher Nolan " +
                "profundiza hasta las ultimas consecuencias en la psicologia de sus personajes centrales y obra " +
                "el milagro con una historia de aires shakespearianos en clave apocaliptica que te deja con un " +
                "mal cuerpo indescriptible");
        pelicula3.put("fecha", "24/10/2008");
        pelicula3.put("director", "Christopher Nolan");
        pelicula3.put("mayorDeEdad", false);
        pelicula3.put("valoracion", 4.0);
        pelicula3.put("duracion", "98min");
        pelicula3.put("generos", genero3.getString("nombre"));

        genero1.put("peliculas", pelicula2.getString("nombre"));
        genero2.put("peliculas", pelicula1.getString("nombre"));
        genero3.put("peliculas", pelicula3.getString("nombre"));
        coleccionGenero.insertOne(genero1);
        coleccionGenero.insertOne(genero2);
        coleccionGenero.insertOne(genero3);

        coleccionPeliculas.insertOne(pelicula1);
        coleccionPeliculas.insertOne(pelicula2);
        coleccionPeliculas.insertOne(pelicula3);

    }

    private static void menu() throws NumberFormatException, IOException { //Menu principal
        String opcion;
        int opcionValida = 100;
        do {
            System.out.println("-MENU PRINCIPAL-");
            System.out.println("1. Menu Usuario");
            System.out.println("2. Menu Administrador");
            System.out.println("0. Salir");
            try {
                System.out.println("Seleccione una opcion: ");
                opcion = br.readLine();
                if (Comprobar.comprobarNumeroMenu(opcion)) { //Comprueba si el dato que ha introducido es un numero
                    opcionValida = Integer.parseInt(opcion); //Si es un numero se convierte a integer
                } else {
                    System.out.println("Introduzca un numero, por favor");
                }
            } catch (InputMismatchException ime) {
                opcionValida = 100;
            }
            switch (opcionValida) {
                case 1:
                    //Menu de usuario para buscar peliculas
                    menuUsuario();
                    break;
                case 2:
                    //Menu administrador con todos los metodos del CRUD.
                    menuAdministrador();
                    break;
                case 0:
                    System.out.println("Fin del programa");
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Introduzca un numero del 0 al 2");
                    break;
            }
        } while (opcionValida != 0);

    }

    private static void menuAdministrador() throws NumberFormatException, IOException {
        String opcion;
        int opcionValida = 5;
        do {
            System.out.println("-MENU ADMINISTRADOR-");
            System.out.println("1. Menu del CRUD de películas");
            System.out.println("2. Menu del CRUD de género");
            System.out.println("0. Salir");
            try {
                System.out.println("Seleccione una opcion: ");
                opcion = br.readLine();
                if (Comprobar.comprobarNumeroMenu(opcion)) { //Comprueba si el dato que ha introducido es un numero
                    opcionValida = Integer.parseInt(opcion); //Si es un numero se convierte a integer
                } else {
                    System.out.println("Introduzca un numero, por favor");
                }
            } catch (InputMismatchException ime) {
                opcionValida = 100;
            }
                switch (opcionValida) {
                    case 1:
                        //Menu CRUD de las peliculas
                        menuCRUDPelicula();
                        break;
                    case 2:
                        //Menu CRUD de los generos
                        menuCRUDGenero();
                    case 0:
                        System.out.println(" ");
                        break;
                    default:
                        System.out.println("Introduzca un numero del 0 al 2");
                        break;
                }
        } while (opcionValida != 0) ;
    }

    private static void menuCRUDPelicula() throws NumberFormatException, IOException {
        String opcion;
        int opcionValida = 100;
        do {
            System.out.println("-MENU PELICULAS (CRUD)-");
            System.out.println("1. Consultar peliculas");
            System.out.println("2. Agregar pelicula");
            System.out.println("3. Borrar pelicula");
            System.out.println("4. Modificar pelicula");
            System.out.println("0. Salir");
            try {
                System.out.println("Seleccione una opcion: ");
                opcion = br.readLine();
                if (Comprobar.comprobarNumeroMenu(opcion)) { //Comprueba si el dato que ha introducido es un numero
                    opcionValida = Integer.parseInt(opcion); //Si es un numero se convierte a integer
                } else {
                    System.out.println("Introduzca un numero por favor");
                }
            } catch (InputMismatchException ime) {
                opcionValida = 100;
            }
            switch (opcionValida) {
                case 1:
                    //Muestra las peliculas que se encuentran en la base de datos
                    listadoPeliculas();
                    break;
                case 2:
                    //Inserta una pelicula nueva
                    Insertar.insertarPelicula();
                    break;
                case 3:
                    //Borra una pelicula
                    borrarPelicula();
                    break;
                case 4:
                    //Modifica cualquier campo de la pelicula
                    modificarPelicula();
                    break;
                case 0:
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Introduzca un numero del 0 al 4");
                    break;
            }
        } while (opcionValida != 0);

    }

    private static void menuCRUDGenero() throws NumberFormatException, IOException {
        String opcion;
        int opcionValida = 100;
        do {
            System.out.println("-MENU PELICULAS (CRUD)-");
            System.out.println("1. Consultar géneros");
            System.out.println("2. Metodo crear géneros");
            System.out.println("3. Metodo borrar géneros");
            System.out.println("4. Metodo modificar géneros");
            System.out.println("0. Salir");
            try {
                System.out.println("Seleccione una opcion: ");
                opcion = br.readLine();
                if (Comprobar.comprobarNumeroMenu(opcion)) { //Comprueba si el dato que ha introducido es un numero
                    opcionValida = Integer.parseInt(opcion); //Si es un numero se convierte a integer
                } else {
                    System.out.println("Introduzca un numero por favor");
                }
            } catch (InputMismatchException ime) {
                opcionValida = 100;
            }
            switch (opcionValida) {
                case 1:
                    // Muestra todos los generos
                    listadoGeneros();
                    break;
                case 2:
                    //Inserta un nuevo genero
                    Insertar.insertarGenero();
                    break;
                case 3:
                    //Borra un genero
                    borrarGenero();
                    break;
                case 4:
                    //Modificar un genero
                    modificarGenero();
                    break;
                case 0:
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Introduzca un numero del 0 al 4");
                    break;
            }
        } while (opcionValida != 0);

    }

    private static void menuUsuario() throws NumberFormatException, IOException {
        int opcionValida = 0;
        String opcion;
        do {
            System.out.println("-MENU USUARIO-");
            System.out.println("1. Buscar película por nombre");
            System.out.println("2. Buscar película por género");
            System.out.println("0. Salir");
            try {
                System.out.println("Seleccione una opcion: ");
                opcion = br.readLine();
                if (Comprobar.comprobarNumeroMenu(opcion)){//Comprueba si el dato que ha introducido es un numero
                    opcionValida = Integer.parseInt(opcion); //Si es un numero se convierte a integer
                } else {
                    System.out.println("Introduzca un numero por favor");
                }
            } catch (InputMismatchException ime) {
                opcionValida = 100;
            }
            switch (opcionValida) {
                case 1:
                    //Busca una pelicula por nombre
                    buscarPorNombre();
                    break;
                case 2:
                    //Busca un genero
                    buscarPorGenero();
                    break;
                case 0:
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Introduce un numero del 0 al 2");
                    break;
            }
        } while (opcionValida != 0);
    }

    private static void buscarPorNombre() {
        List<Document> consultaPeliculas = coleccionPeliculas.find().into(new ArrayList<>());
        int i = 0;
        boolean buscar = false;
        try {
            System.out.println("-Introduce el nombre de la pelicula que quieres buscar: ");
            String nombreP = br.readLine();
            if (!Comprobar.comprobarPeliculaExistente(nombreP)) { //Comprueba de que la pelicula existe, si no existe te muestra un mensaje por pantalla
                System.out.println("Esta pelicula no existe");
            } else {
                while (i < consultaPeliculas.size() && !buscar) {
                    Document pel = consultaPeliculas.get(i);
                    if (nombreP.equalsIgnoreCase(pel.getString("nombre"))) {
                        //Muestra la pelicula si el nombre coincide con el introducido por el usuario
                        System.out.println("Nombre: " + pel.getString("nombre") + "\nSipnosis: " + pel.get("sinopsis") + "\nFecha de estreno: "
                                + pel.get("fecha") + "\nDirector: " + pel.get("director") + "\nMayor de 18 anios: " + pel.get("publico") + "\nValoracion:"
                                + pel.get("valoracion") + "\nDuracion: " + pel.get("duracion") + "\nGeneros: " + pel.get("generos"));
                    } else {
                        i++;
                    }
                    break;
                }
            }
            } catch(NumberFormatException nfe){
                buscar = true;
            }catch(IOException ioe){
                System.out.println("Error de E/S");
            }
    }

    // TERMINAR
    private static int buscarPorGenero() throws IOException {
        List<Document> consultaGenero = coleccionGenero.find().into(new ArrayList<>());
        int i = 0;
        boolean buscar = false;
        try {
            System.out.println("-Introduce el nombre del género de la pelicula que quieres buscar: ");
            String nombreGen = br.readLine();
            if (Comprobar.comprobarGeneroExistente(nombreGen)) { //Comprueba si existe el genero, si no existe te muestra por pantalla que el genero no existe
                while (i < consultaGenero.size() && !buscar) {
                    Document genero = consultaGenero.get(i);
                    System.out.println(genero.getString("nombre"));
                    if (nombreGen.equalsIgnoreCase(genero.getString("nombre"))) {
                        //Muestra por pantalla el genero si el nombre es igual al nombre introducido por el usuario
                        System.out.println("Nombre: " + genero.getString("nombre") + "\nDescripcion: " +
                                genero.getString("descripcion") + "\nPeliculas: " + genero.get("peliculas"));
                        buscar = true;
                    } else {
                        i++;
                    }
                }
            } else {
                System.out.println("Este genero no existe");
            }
        } catch (NumberFormatException nfe) {
            buscar = true;
        }
        return i;
    }


    public static void modificarPelicula() {
        List<Document> consultaPeliculas = coleccionPeliculas.find().into(new ArrayList<>());
        String nombrePeliculaModificar;
        System.out.println("Listado de peliculas:");
        for (int i = 0; i < consultaPeliculas.size(); i++) {
            Document pelicula = consultaPeliculas.get(i);
            System.out.println(i + 1 + ". " + pelicula.getString("nombre"));
        }
        try {
        System.out.println("Introduzca el nombre de la película que desee modificar");
            System.out.println("Si desee salir introduzca SALIR");
        nombrePeliculaModificar = br.readLine();
        if (nombrePeliculaModificar.equalsIgnoreCase("salir")) { //Comprueba si el usuario introduce salir para salir del metodo
            System.out.println("SALIENDO...");
            System.out.println();
        } else {
            if (!Comprobar.comprobarPeliculaExistente(nombrePeliculaModificar)) { //Comprueba si la pelicula existe
                System.err.println("El nombre de la pelicula no existe");
                modificarPelicula();
            } else {
                menuModificarPelicula(nombrePeliculaModificar);
            }
        }
       } catch (IOException ioe) {
           System.out.println("Error de E/S");
       }

    }

    private static void menuModificarPelicula(String nombrePelicula) throws NumberFormatException, IOException {
        String opcion = null;
        int opcionValida = 100;
        do {
            System.out.println("-MENU MODIFICAR PELICULA-");
            System.out.println("1. Modificar el nombre de la pelicula");
            System.out.println("2. Modificar la sipnosis de la pelicula");
            System.out.println("3. Modificar el director de la pelicula");
            System.out.println("4. Modificar la duracion de la pelicula");
            System.out.println("5. Modificar la valoracion de la pelicula");
            System.out.println("6. Modificar fecha de estreno de la pelicula");
            System.out.println("7. Modificar si la pelicula es para mayor de 18 anios");
            System.out.println("0. Salir");
            try {
                System.out.println("Seleccione una opcion: ");
                opcion = br.readLine();
                if (Comprobar.comprobarNumeroMenu(opcion)) {
                    opcionValida = Integer.parseInt(opcion);
                } else {
                    System.out.println("Introduzca un numero, por favor");
                }
            } catch (InputMismatchException ime) {
                opcionValida = 100;
            }
            switch (opcionValida) {
                case 1:
                    //Modifica el nombre de la pelicula
                    ModificarPelicula.modificarNombrePelicula(nombrePelicula);
                    break;
                case 2:
                    //Modifica la sinopsis de la pelicula
                    ModificarPelicula.modificarSipnosisPelicula(nombrePelicula);
                    break;
                case 3:
                    //Modifica el director de la pelicula
                    ModificarPelicula.modificarDirectorPelicula(nombrePelicula);
                    break;
                case 4:
                    //Modifica la duracion de la pelicula
                    ModificarPelicula.modificarDuracionPelicula(nombrePelicula);
                    break;
                case 5:
                    //Modifica la valoracion de la pelicula
                    ModificarPelicula.modificarValoracionPelicula(nombrePelicula);
                    break;
                case 6:
                    //Modifica la fecha de estreno de la pelicula
                    ModificarPelicula.modificarFechaEstrenoPelicula(nombrePelicula);
                    break;
                case 7:
                    //Modifica si la pelicula es para mayores de edad
                    ModificarPelicula.modificarPublicoPelicula(nombrePelicula);
                    break;
                case 0:
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Introduzca un numero del 0 al 7");
                    break;
            }
        } while (opcionValida != 0) ;
    }

    public static void modificarGenero() {
        String nombreGeneroModificar;
        try {
            System.out.println("Introduzca el nombre del género que desee modificar");
            System.out.println("Si desee salir introduzca SALIR");
            nombreGeneroModificar = br.readLine();
            if (nombreGeneroModificar.equalsIgnoreCase("salir")) { //Comprueba si el usuario introduce salir para salir del metodo
                System.out.println("SALIENDO...");
                System.out.println();
            } else {
                if (!Comprobar.comprobarGeneroExistente(nombreGeneroModificar)) { //Comprueba si el genero existe
                    System.err.println("El nombre del genero no existe");
                    modificarGenero();
                } else {
                    menuModificarGenero(nombreGeneroModificar);
                }
            }
        } catch (IOException ioe) {
            System.out.println();
        }
    }

    private static void menuModificarGenero(String nombreGenero) throws NumberFormatException, IOException {
        String opcion = null;
        int opcionValida = 100;
        do {
            System.out.println("-MENU MODIFICAR GENERO-");
            System.out.println("1. Modificar el nombre de el genero");
            System.out.println("2. Modificar la descripcion de el genero");
            System.out.println("0. Salir");
            try {
                System.out.println("Seleccione una opcion: ");
                opcion = br.readLine();
                if (Comprobar.comprobarNumeroMenu(opcion)) {
                    opcionValida = Integer.parseInt(opcion);
                } else {
                    System.out.println("Introduzca un numero, por favor");
                }
            } catch (InputMismatchException ime) {
                opcionValida = 100;
            }
            switch (opcionValida) {
                case 1:
                    //Modifica el nombre del genero
                    ModificarGenero.modificarNombreGenero(nombreGenero);
                    break;
                case 2:
                    //Modifica la descripcion del genero
                    ModificarGenero.modificarDescripcion(nombreGenero);
                    break;
                case 0:
                    System.out.println("");
                    break;
                default:
                    System.out.println("Introduzca un numero del 0 al 2");
                    break;
            }
        } while (opcionValida != 0);

    }

    public static void listadoPeliculas() { //Muestra todas las peliculas
        List<Document> consultaPeliculas = coleccionPeliculas.find().into(new ArrayList<>());
        for (int i = 0; i < consultaPeliculas.size(); i++) {
            Document pelicula = consultaPeliculas.get(i);
            System.out.println("Pelicula " + (i+1));
            System.out.println("Nombre: " + pelicula.getString("nombre") + "\nSipnosis: " + pelicula.get("sinopsis") + "\nFecha de estreno: "
                    + pelicula.get("fecha") + "\nDirector: " + pelicula.getString("director") + "\nMayor de 18 anios: " + pelicula.get("mayorDeEdad") + "\nValoracion:"
                    + pelicula.get("valoracion") + "\nDuracion: " + pelicula.get("duracion") + "\nGeneros: " + pelicula.get("generos"));
        }
    }

    public static void listadoGeneros() { //Muestra todos los generos
        List<Document> consultaGenero = coleccionGenero.find().into(new ArrayList<>());
        for (int i = 0; i < consultaGenero.size(); i++) {
            Document genero = consultaGenero.get(i);
            System.out.println("Genero " + (i+1));
            System.out.println("Nombre: " + genero.getString("nombre") + "\nDescripcion: " + genero.get("descripcion") + "\nPeliculas: "
                    + genero.get("peliculas"));
        }
    }
}
