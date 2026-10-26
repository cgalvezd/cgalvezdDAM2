package practicaPrimerExamen2910;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class KitExamen {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        //1. Pedir datos al usuario
        System.out.println("Nombre de la carpeta: ");
        String nombreCarpeta = scanner.nextLine();
        System.out.println("Nombre del fichero:");
        String nombreFichero = scanner.nextLine();

        Path carpeta = Paths.get(nombreCarpeta);
        Path fichero = carpeta.resolve(nombreFichero);

        try {
         //2. Carpetas: crear y comprobar
         if(Files.notExists(carpeta)){
             Files.createDirectories(carpeta);
             System.out.println("Carpeta creada");
         }
         //3. Escribir
            Files.write(fichero, List.of("uno", "dos", "TRES", "CUATRO", "CINCO"), StandardCharsets.UTF_8);

         //4. Leer y modificar en memoria3ºº
            List<String> lineas = Files.readAllLines(fichero, StandardCharsets.UTF_8);
            lineas.set(0, "UNO MODIFICADO");
            lineas.add("tres");

            //5. Reescribir
            Files.write(fichero, lineas, StandardCharsets.UTF_8);
            try(var stream = Files.list(carpeta)){
                stream.forEach(p -> System.out.println(p.getFileName()));
            }
        } catch (IOException e){
            System.err.println("ERROR DE E/S: "+e.getLocalizedMessage());
        }
    }
}