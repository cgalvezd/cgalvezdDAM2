package primerExamenAccesoA_Datos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class resolExamen2 {
    public static void prepararEntorno(){
        Path ficheroOriginal = Paths.get("datos", "ventas.txt");

        if(!Files.exists(ficheroOriginal) || !Files.isReadable(ficheroOriginal)){
            System.err.println("ERROR crítico: El archivo "+ficheroOriginal.toAbsolutePath() + " no existe o no se puede leer.");
            System.out.println(1);
        }

        try{
            //REQUISITO 2: MOSTRAR RUTA ABSOLUTA Y TAMAÑO EN BYTES
            System.out.println("Ruta absoluta del archivo: "+ficheroOriginal.toAbsolutePath());
            System.out.println("Tamaño del archivo "+Files.size(ficheroOriginal) + " bytes.");

            //REQUISITO 3: Crear los directorios salida y backup si no existe.
            Path dirSalida = Paths.get("salida");
            Path dirBackup = Paths.get("backup");

            if(Files.notExists(dirSalida)){
                Files.createDirectories(dirSalida);
                System.out.println("Directorio 'salida' creado exitosamente.");
            }
            if(Files.notExists(dirBackup)){
                Files.createDirectories(dirBackup);
                System.out.println("Directorio 'backup' creado exitosamente.");
            }

            //REQUISITO 4: Copiar ventas.txt en backup/ventas_backup.txt
            Path ficheroCopia = dirBackup.resolve("ventas_backup.txt");
            Files.copy(ficheroOriginal, ficheroCopia, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo conpiado con éxito a: "+ficheroCopia);
        }catch (IOException e){
            System.err.println("Error de E/S: "+e.getMessage());
        }

    }
}