package proyecto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Comprobar {

    public boolean comprobarNombrePelicula(String nombre) {
        boolean comprobado = false;
        Pattern pat = Pattern.compile("[^ + - * / = % & # ! ? ^  “ ‘ ~ \\ | < > ( ) [ ] { } : ; . , $ · € ~ ¬ { } € ]");
        Matcher m = pat.matcher(nombre);
        if(m.matches()){
            comprobado = true;
        }else{
            comprobado = false;
        }
        return comprobado;
    }

    public boolean validarFechaPelicula(String fecha) {
        final String fechaREGEX =
                "^(?:(?:(?:0?[13578]|1[02])(\\/|-|\\.)31)\\1|" +
                        "(?:(?:0?[1,3-9]|1[0-2])(\\/|-|\\.)(?:29|30)\\2))" +
                        "(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:0?2(\\/|-|\\.)29\\3" +
                        "(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|" +
                        "[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|" +
                        "^(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\\.)(?:0?[1-9]|1\\d|" +
                        "2[0-8])\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

        final Pattern fechaPATTERN = Pattern.compile(fechaREGEX);
        Matcher matcher = fechaPATTERN.matcher(fecha);
        return matcher.matches();
    }

    public boolean comprobarNombreDirectorPelicula(String nombreDirector) {
        if (nombreDirector.matches("[a-zA-Z]")){
            return true;
        } else {
            return false;
        }
    }

    public boolean comprobarDuracionPelicula(int duracion) {
        if (duracion > 30) {
            return true;
        } else {
            return false;
        }
    }

    public boolean comprobarValoracionPelicula(double valoracion) {
        if (valoracion < 5 && valoracion > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean comprobarMayorDeEdadPelicula(int numero) {
        if (numero >= 18) {
            return true;
        } else {
            return false;
        }
    }

    public boolean comprobarNombreGenero(String nombreGenero) {
        if (nombreGenero.matches("[a-zA-Z]")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean comprobarDescripcionGenero(String descripcionGenero) {
        if (descripcionGenero.matches("a-zA-Z_0-9")) {
            return true;
        } else {
            return false;
        }
    }
}
