package practicaPrimerExamen2910;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class Ejercicio1Solo extends IOException{
    static Scanner scanner = new Scanner(System.in);

    //1. Pedir nombre de la carpeta
    public static String pedirNombreCarpeta(){
        //PEDIMOS AL USUARIO EL NOMBRE DE CARPETA
        System.out.println("Introduce el nombre de la carpeta");
        String nombreCarpeta = scanner.nextLine();
        return nombreCarpeta;
    }

    //2. Si esta no existe, lo creamos
    public static String crearCarpeta(){
        //Lo creamos si no existe
        Path carpeta = Paths.get(pedirNombreCarpeta());

        try {
            if (Files.notExists(carpeta)) {
                //Si no existe, lo creamos.
                Files.createDirectories(carpeta);
                System.out.println("Carpeta creada exitosamente.");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el carpeta" + e.getMessage());
        }
        return carpeta.getFileName().toString();
    }

    //3. Cremos el fichero y pedimos los 3 nombres
    public static void pedirNombreCrearFichero() throws IOException {
        //Con la carpeta creada, creamos alumnos.txt con tres nombres pedidos por consola, uno por línea
        System.out.println("Escribe el primer nombre: ");
        String nombre1 = scanner.nextLine();
        System.out.println("Escribe el segundo nombre: ");
        String nombre2 = scanner.nextLine();
        System.out.println("Escribe el tercer nombre: ");
        String nombre3 = scanner.nextLine();

        String nombreFichero = "listaNombres.txt";
        String carpeta = "";

        //Path fichero = carpeta.resolveConstantDesc(nombreFichero);
        //Con el fichero creado, pasamos los nombre

//        Files.write(fichero, List.of(nombre1, nombre2, nombre3), StandardCharsets.UTF_8);

        System.out.println("Escribe un último nombre: ");
        String lastName = scanner.nextLine();

        //Leemos y modificamos el fichero actual
       // List<String> lineas = Files.readAllLines(fichero, StandardCharsets.UTF_8);
        //lineas.add(lastName);
       // Files.write(fichero, lineas, StandardCharsets.UTF_8);

        //Forma alternativa, más corta, para añadir al final sin releer.
        //Files.writeString(fichero, lastName + System.lineSeparator(),
               // StandardCharsets.UTF_8, StandardOpenOption.APPEND);

    }

    static void main() throws IOException {
        pedirNombreCarpeta();
        crearCarpeta();
        pedirNombreCrearFichero();
    }
}