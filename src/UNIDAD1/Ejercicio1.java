package UNIDAD1;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Ejercicio1 {
    static Scanner scanner = new Scanner(System.in);

    public static void ficheroParametro(){
            System.out.println("Ingresa el nombre del fichero:");
            String nombreFichero = scanner.nextLine();

            Path ruta = Paths.get(".");
            //Validar si existe, si es fichero o carpeta
    }

    static void main() {
        ficheroParametro();

    }
}