package practicaPrimerExamen2910;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ejercicio3 {
    static Scanner scanner = new Scanner(System.in);

    public static String pedirRutaRelativa(){
        System.out.println("Escribe el nombre de ruta");
        return scanner.nextLine();
    }

    public static List<String> pedirLineasUsuario(){
        List<String> lineas = new ArrayList<>();
        System.out.println("Escribe la primera línea: ");
        lineas.add(scanner.nextLine());

        System.out.println("Escribe la segunda línea: ");
        lineas.add(scanner.nextLine());

        System.out.println("Escribe la tercera línea: ");
        lineas.add(scanner.nextLine());
        return lineas;
    }

    static void main() throws IOException {
        String ruta = pedirRutaRelativa();

        Path carpeta = Paths.get(ruta);
        Path carpetaCopias = carpeta.resolve("copias");

        if(Files.notExists(carpetaCopias)){
            Files.createDirectories(carpetaCopias);
            System.out.println("Ruta carpeta creada exitosamente.");
        }
        List<String> lineasParaEscribir = pedirLineasUsuario();

        Path fichero = carpeta.resolve("datos.txt");

        Files.write(fichero, lineasParaEscribir, StandardCharsets.UTF_8);
        System.out.println("Fichero datos.txt creado exitosamente.");

        //Mover y renombrar datos.txt a copias/datos_old.txt
        Path nuevoFichero = carpetaCopias.resolve("datos_old.txt");
        Files.move(fichero, nuevoFichero, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Fichero movido y renombrado a copias/datos_old.txt.");

        //MOSTRAR TODOS LOS FICHEROS .TXT DE LA CARPETA Y SUBCARPETAS
        System.out.println("\n--- Lista de archivos .txt ---");
        try(Stream<Path> stream= Files.walk(carpeta)){
            stream.filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".txt"))
                    .forEach(System.out::println);
        }

        Files.delete(nuevoFichero);

        if(Files.exists(nuevoFichero)){
            System.out.println("El archivo no fue eliminado");
        }else{
            System.out.println("El archivo fue eliminado");
        }
    }
}