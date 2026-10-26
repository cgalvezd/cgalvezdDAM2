package practicaPrimerExamen2910;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Scanner;

public class Ejercicio2_1Solo {
    //Pedismos el nombre de la ruta
    static Scanner scanner = new Scanner(System.in);

    public static String pedirNombreRuta() {
        System.out.println("Escribe una ruta: (Ejemplo: \"examen/practica/datos\"");
        return scanner.nextLine();
    }

    //2. Crear carpetas anidadas
    public static void listarContenidoCarpeta(Path carpeta) throws IOException {
        System.out.println("\n--- Contenido de la carpeta: "+carpeta.getFileName());
        try(var stream = Files.list(carpeta)){
            stream.forEach(p->{
                try{
                    String tipo = Files.isDirectory(p) ? "[CARPETA]" : "[FICHERO]";
                    long tamanio = Files.size(p);
                    System.out.println(tipo + ": "+p.getFileName()+ " | Tamaño: "+tamanio+" bytes.");
                }catch(IOException e){
                    System.err.println("Error de lectura: "+p.getFileName());
                }
            });
        }
    }

    static void main() {
        String rutaTexto = pedirNombreRuta();
        if(rutaTexto == null || rutaTexto.isEmpty()){
            System.err.println("Este campo no puede ser nulo o vacío.");
            return;
        }

        //1.OBTENER LA RUTA DEL DIRECTORIO
        Path carpeta = Paths.get(rutaTexto);

        try{
            //2. CREAR CARPETAS ANIDADAS
            if(Files.notExists(carpeta)){
                Files.createDirectories(carpeta);
                System.out.println("Estructura de carpeta creada correctamente.");
            }

            //3. DEFINIR FICHEROS DENTRO DE ESA RUTA.
            Path ficheroA = carpeta.resolve("a.txt");
            Path ficheroB = carpeta.resolve("b.txt");

            //4. ESCRIBIMOS EN LOS FICHEROS
            Files.write(ficheroA, List.of("Línea del fichero A"), StandardCharsets.UTF_8);
            Files.write(ficheroB, List.of("Contenido del fichero B"), StandardCharsets.UTF_8);

            //5. Primer listado (Tipo y Tamaño)
            listarContenidoCarpeta(carpeta);

            //6. COPIAR a.txt COMO copia_a.txt
            Path copiaA = carpeta.resolve("copia_a.txt");
            Files.copy(ficheroA, copiaA, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copiado a.txt como copia_a.txt");

            //7. Borrar b.txt
            Files.delete(ficheroB);
            System.out.println("Fichero b.txt borrado exitosamente.");

            //8. 2DO LISTADO PARA COMPROBAR
            listarContenidoCarpeta(carpeta);
        }catch(IOException e){
            System.out.println("ERROR E/S: "+e.getLocalizedMessage());
        }
    }
}
