package examen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestionVentas {
    //APARTADO 1
    public static void prepararEntorno() throws IOException {
        Path rutaFichero = Paths.get("datos/ventas");

        //1.1
        if(Files.notExists(rutaFichero) || !Files.isReadable(rutaFichero)){
            System.err.println("Error crítico: El archivo "+rutaFichero.toAbsolutePath()+" no existe o no s puede leer correctamente");
            System.exit(1);
        }

        //1.2
        System.out.println("Ruta absoluta del fichero: "+rutaFichero.toAbsolutePath()+"\nTamaño en bytes: "+rutaFichero.toFile().length());

        //1.3 Crear los directorios salidas y backup si no existen

        Path rutaSalida = Paths.get("examen", "salida");
        Path rutaBackup = Paths.get("examen", "backup");
        if(Files.notExists(rutaSalida)) {
            Files.createDirectories(rutaSalida);
        }

        if(Files.notExists(rutaBackup)){
            Files.createDirectories(rutaBackup);
        }

        //1.4
        //Crear fichero ventas_backup.txt en backup. Sobreescribirlo si ya existe
        Path backup = Paths.get("rutaBackup");

    }
}
