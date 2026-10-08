package UNIDAD1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ejercicio2 {
    static Scanner scanner = new Scanner(System.in);

    public void recibeParametro(){
        System.out.println("Ingresa una ruta de carpeta.");
        String ruta = scanner.nextLine();

        //CREAR EL OBJETO PATH Y COMPROBAR SI EXISTE Y SI ES UN DIRECTORIO
        Path rutaFichero = Paths.get(ruta);

        //VERIFICAR SI LA RUTA EXISTE
        if(Files.notExists(rutaFichero)){
            System.out.println("Error. No existe una carpeta con ese nombre.");
            return;
        }

        //VERIFICAR SI ES UNA CARPETA
        if(Files.isDirectory(rutaFichero)){
            System.out.println("Carpeta encontrada. Contenido y subdirectorios");

            try(Stream<Path> stream = Files.walk(rutaFichero)){
                //ESTO MUESTRA CADA RUTA EN LA CONSOLA
                stream.forEach(elemento -> System.out.println(elemento));
            }catch (IOException e){
                System.err.println("FATAL. Error al leer el directorio" + e.getMessage());
            }

        }else{
            System.out.println("La ruta indicada no existe");
        }
    }
}