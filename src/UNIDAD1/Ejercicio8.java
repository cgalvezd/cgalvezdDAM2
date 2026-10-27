package UNIDAD1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Ejercicio8 {

    public static void borrarLinea(String rutaFichero, int numLineaBorrar){
        File archivoOriginal = new File(rutaFichero);
        //CREAR ARCHIVO TEMPORAL EN EL MISMO DIRECTORIO
        File archivoTemporal = new File(rutaFichero + ".tmp");

        if(!archivoOriginal.exists()){
            System.out.println("Error: El archivo '"+rutaFichero+ "' no existe.");
            return;
        }

        int lineaActual = 1;
        boolean encontrado = false;

        try(BufferedReader lector = new BufferedReader(new FileReader(archivoOriginal));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoTemporal))){
            String linea;
            while ((linea = lector.readLine()) != null) {

                if(lineaActual != numLineaBorrar){
                    escritor.write(linea);
                    escritor.newLine();
                }else{
                    encontrado = true;
                }
                lineaActual++;
            }
        } catch (IOException e) {
            System.out.println("Error de procesamiento de datos: "+e.getMessage());
            return;
        }

    }
    static void main() {

       Scanner scanner = new Scanner(System.in);
        Path nombreArchivo = Paths.get("ficheroPrueba.txt");

        if(Files.notExists(nombreArchivo)){
            try{
                if(nombreArchivo.getParent() != null){
                    Files.createDirectories(nombreArchivo.getParent());
                }
                Files.createDirectories(nombreArchivo);
                System.out.println("Archivo creado exitosamente");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Error de E/S");
        }

        System.out.println("¿Qué línea del fichero quieres eliminar?");
        int linea = Integer.parseInt(scanner.nextLine());


    }
}