package actividadIntegradoraUnidad1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Paso1 {
    static void main() {
        Path carpeta = Paths.get("datos");
        Path fichero = carpeta.resolve("notas.txt");

        try{
            //1. CREAR LA CARPETA SI NO EXISTE
            if(Files.notExists(carpeta)){
                Files.createDirectories(carpeta);
            }

            //2. ESCRIBIR TEXTO (UTF-8 EXPLÍCITO, SOBREESCRIBE)
            List<String> lineas = List.of("Primera línea", "Segunda: ñandú y café");
            Files.write(fichero, lineas, StandardCharsets.UTF_8);

            //3. AÑADIR UNA LÍNEA AL FINAL (modo append)
            Files.writeString(fichero, "Tercera línea" + System.lineSeparator(),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.APPEND);

            //4.LEER TODO
            List<String> leidas = Files.readAllLines(fichero, StandardCharsets.UTF_8);
            leidas.forEach(System.out::println);

            //5. MEDATADOS
            System.out.println("Tamaño: "+Files.size(fichero) + " bytes");
            System.out.println("Ruta absoluta: "+fichero.toAbsolutePath());
        }catch (IOException e){
            System.err.println("Error de E/S: "+e.getMessage());
        }
    }
}
