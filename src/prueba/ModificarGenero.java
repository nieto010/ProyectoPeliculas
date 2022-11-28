package prueba;

import proyecto.Principal;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static proyecto.Principal.br;

public class ModificarGenero {


    public static void modificarNombreGenero(String nombreGeneroModificar) {
        String nombreGenero = null;
        try {
            System.out.println("Introduzca el nombre que desee poner a la pelicula");
            nombreGenero = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        Principal.coleccionGenero.updateOne(eq("nombre", nombreGeneroModificar), set("nombre", nombreGenero));
    }

    public static void modificarDescripcion(String nombreGeneroModificar) {
        String descripcionGenero = null;
        try {
            System.out.println("Introduzca el nombre que desee poner a la pelicula");
            descripcionGenero = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        Principal.coleccionGenero.updateOne(eq("nombre", nombreGeneroModificar), set("descripcion", descripcionGenero));
    }
}
