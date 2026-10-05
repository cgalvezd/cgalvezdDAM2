package UNIDAD1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GestionDirectorios {
    static void main() {
        Path carpetaCatalogo = Path.of("catalogo");
        Path subcarpetaImagenes = carpetaCatalogo.resolve("imagenes");

        try{
            Files.createDirectories(subcarpetaImagenes);
            System.out.println("Directorios creados correctamente.");

            Path ficheroConfig = carpetaCatalogo.resolve("config.txt");
            if(!Files.exists(ficheroConfig)){
                Files.createFile(ficheroConfig);
            }
        }catch (IOException e){
            System.err.println("Error al crear la estructura de directorios: "+e.getMessage());
        }

    }

}
