package prueba;

import proyecto.Principal;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class ModificarGenero {
    Principal p = new Principal();

    public void modificarNombreGenero(String nombreGeneroModificar) {
        String nombreGenero = null;
        try {
            System.out.println("Introduzca el nombre que desee poner a la pelicula");
            nombreGenero = p.br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        p.coleccionGenero.updateOne(eq("nombre", nombreGeneroModificar), set("nombre", nombreGenero));
    }

    public void modificarDescripcion(String nombreGeneroModificar) {
        String descripcionGenero = null;
        try {
            System.out.println("Introduzca el nombre que desee poner a la pelicula");
            descripcionGenero = p.br.readLine();
        } catch (IOException ioe) {
            System.out.println("Error");
        }
        p.coleccionGenero.updateOne(eq("nombre", nombreGeneroModificar), set("descripcion", descripcionGenero));
    }
}
