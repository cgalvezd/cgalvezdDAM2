package UNIDAD1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class OperacionesFicheros {
    static void main() throws IOException {
        Path origin = Path.of("catalogo/config.txt");
        Path copia = Path.of("catalogo/config_copia.txt");
        Path destino = Path.of("catalogo/backup/config.txt");

        try {
            //Copiar (Sobreescribimos si ya existe)
            Files.copy(origin, copia, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        //MOVER / RENOMBRAR
        Files.createDirectories((destino.getParent()));
        Files.move(copia, destino, StandardCopyOption.REPLACE_EXISTING);

        //BORRAR DE FORMA SEGURA
        boolean borrado = Files.deleteIfExists(Path.of("catalogo/fichero_temporal.tmp"));
        System.out.println("¿Se borró el fichero temporal? "+borrado);

    }
}