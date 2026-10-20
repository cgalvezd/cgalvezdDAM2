package UNIDAD1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Ejercicio5Alternativo {
    static Scanner scanner = new Scanner(System.in);

    public String pedirTexto(){
        System.out.println("Escribe el texto que deseas agregar al final del archivo: ");
        return scanner.nextLine();
    }

    public void agregarTexto(File file){
        //1. PRIMERO CONVERTIMOS EL OBJETO FILE A PATH
        Path path = file.toPath();

        //2. PEDIMOS EL TEXTO A ESCRIBIR
        String textoEscribir = pedirTexto() + System.lineSeparator();

        try{
            //3. ESCRIBIMOS EL TEXTO UTILIZANDO FILES.WRITESTRING CON LAS OPCIONES INDICADAS.
            Files.writeString(path, textoEscribir, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Texto agregado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero.");;
        }
    }

    static void main() {
        Ejercicio5Alternativo programa = new Ejercicio5Alternativo();
        File miFichero = new File("mi_archivo_prueba.txt");
        //Ejecutamos el método
        programa.agregarTexto(miFichero);

    }
}
