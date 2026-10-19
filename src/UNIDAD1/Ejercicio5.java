package UNIDAD1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Ejercicio 5: Añade un texto al final de un fichero pasado por parámetro,
 * creándolo si no existe (usa el modo append de FileWriter).
 */

public class Ejercicio5 {
    static Scanner scanner = new Scanner(System.in);

    private static void agregarTexto(File file){
        //VALIDAMOS QUE EL PARÁMETRO NO SEA NULL
        if(file == null || (file.exists() && file.isDirectory())){
            System.out.println("Error: La ruta específica es un directorio.");
            return;
        }
    }

    static void main() {
        System.out.println("Ingresa el nombre del archivo.");
        String nombreArchivo = scanner.nextLine();
        //CREAMOS EL OBJETO FILE CON EL NOMBRE INTRODUCIDO
        File archivo = new File(nombreArchivo);

        agregarTexto(archivo);
        System.out.println("Ingresa el texto que quieres añadir al final del archivo. ");
        String texto = scanner.nextLine();

        //El file de abajo fue anotado para que compile, se debe modificar.
        File file = new File("HOLA");

        try(FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter)){

            printWriter.println(texto);

            System.out.println("[OK] Texto añadido correctamente a: " + file.getName());

        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
}