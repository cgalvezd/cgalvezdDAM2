package practicaPrimerExamen2910;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Scanner;

public class Ejercicio2Solo {

    //Pedimos el  nombre de la ruta
    static Scanner scanner =  new Scanner(System.in);

    public static String pedirNombreRuta(){
        System.out.println("Escribe una ruta (ejemplo: \"examen/practica/datos\")");
        return scanner.nextLine();
    }

    //2. Crear carpetas anidadas
    public static void listarContenidoCarpeta(Path carpeta) throws IOException {
        System.out.println("\n--- Contenido de la carpeta: "+carpeta.getFileName());
        try(var stream = Files.list(carpeta)){
            stream.forEach(p -> {
                try{
                    String tipo = Files.isDirectory(p) ? "[CARPETA]" : "[FICHERO]";
                    long tamano = Files.size(p);
                    System.out.println(tipo + " "+p.getFileName() + " | Tamaño: "+tamano+ " bytes.");
                }catch(Exception e){
                    System.err.println("Error al leer atributos de: "+p.getFileName());
                }
            });
        }
        System.out.println("====================================================================================");
    }

    static void main() {
        String rutaTexto = pedirNombreRuta();

        if(rutaTexto== null || rutaTexto.isEmpty() ){
            System.err.println("La ruta no puede estar vacía.");
            return;
        }

        //1. Obtener la ruta del directorio
        Path carpeta = Paths.get(rutaTexto);

        try{
            //Esto lo busqué en google (Como crear carpetas anidadas)
            if(Files.notExists(carpeta)){
                Files.createDirectories(carpeta);
                System.out.println("Estructura de carpeta creada con éxito.");
            }

            //3. Definir ficheros dentro de esa ruta.
            Path ficheroA = carpeta.resolve("a.txt");
            Path ficheroB = carpeta.resolve("b.txt");

            //4. crear los dos ficheros con una línea cada uno
            Files.write(ficheroA, List.of("Línea del fichero A"), StandardCharsets.UTF_8);
            Files.write(ficheroB, List.of("Línea del fichero B"), StandardCharsets.UTF_8);
            System.out.println("Ficheros a.txt y b.txt creados exitosamente.");

            //5. Primer listado (Tipo y Tamaño)
            listarContenidoCarpeta(carpeta);

            //6. Copiar a.txt como copia_a.txt
            Path copiaA = carpeta.resolve("copia_a.txt");
            Files.copy(ficheroA, copiaA, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copiado a.txt como copia_a.txt.");

            //7. Borrar b.txt
            Files.delete(ficheroB);
            System.out.println("Fichero b.txt borrado exitosamente.");

            //8. Segundo listado para comprobar.
            listarContenidoCarpeta(carpeta);
        }catch (IOException e){
            System.err.println("Error de E/S: "+e.getMessage());
        }
    }
}