package UNIDAD1;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio9 {

    static void main() {
        Path rutaFichero = Paths.get("fichero.txt");
        File carpetaRutaFichero = rutaFichero.toFile();

        Path rutaFicheroCopia = Paths.get("fichero_copia.txt");
        File carpetaRutaFicheroCopia = rutaFicheroCopia.toFile();

        if(Files.notExists(rutaFichero)){
            try{
                Files.createDirectories(rutaFichero);

            }

        }



    }
}
