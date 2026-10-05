package UNIDAD1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ExploradorFicheros {
    static void main() throws IOException {
        Path raiz = Path.of("catalogo");

        //LISTAR SOLO EL CONTENIDO DIRECTO (NO RECURSIVO)
        try(Stream<Path> listado = Files.list(raiz)){
            listado.forEach(System.out::println);
        }

        //RECORRER TODO EL ÁRBOL DE SUBDIRECTORIOS
        try(Stream<Path> arbol = Files.walk(raiz)){
            arbol.filter(Files::isRegularFile).forEach(p -> System.out.println("Fichero encontrado: "+p));
        }
    }
}