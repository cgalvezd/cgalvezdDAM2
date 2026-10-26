package UNIDAD1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Ejercicio7 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Path rutaFichero = Paths.get("datos.txt");
        File ficheroOriginal = rutaFichero.toFile();

        // Aseguramos que el archivo original exista con datos de prueba si no está creado
        if (Files.notExists(rutaFichero)) {
            try {
                // Crear directorios padres si los hubiera, luego el archivo vacío
                if (rutaFichero.getParent() != null) {
                    Files.createDirectories(rutaFichero.getParent());
                }

                Files.createFile(rutaFichero);
                // Inicializamos con algo de texto para poder probar el ejercicio
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroOriginal))) {
                    bw.write("Línea número uno\nLínea número dos\nLínea número tres\nLínea número cuatro");
                }
                System.out.println("Fichero 'datos.txt' creado con texto de prueba.");
            } catch (IOException e) {
                System.out.println("Error al crear el fichero original: " + e.getMessage());
                return;
            }
        }

        // 1. Pedir los datos al usuario
        System.out.print("Introduce el número de línea que deseas modificar (empezando en 1): ");
        int lineaAModificar = Integer.parseInt(scanner.nextLine());

        System.out.print("Introduce el nuevo texto para esa línea: ");
        String nuevoTexto = scanner.nextLine();

        // 2. Aplicar la técnica del fichero temporal
        try {
            // Creamos un fichero temporal en el directorio por defecto del sistema
            File ficheroTemporal = File.createTempFile("temp_", ".txt");

            // Flujos de lectura para el original y escritura para el temporal
            try (BufferedReader lector = new BufferedReader(new FileReader(ficheroOriginal));
                 BufferedWriter escritor = new BufferedWriter(new FileWriter(ficheroTemporal))) {

                String lineaActual;
                int contadorLineas = 1;
                boolean lineaModificada = false;

                // Leemos línea por línea el archivo original
                while ((lineaActual = lector.readLine()) != null) {
                    if (contadorLineas == lineaAModificar) {
                        // Escribimos el nuevo texto en lugar del antiguo
                        escritor.write(nuevoTexto);
                        lineaModificada = true;
                    } else {
                        // Copiamos la línea tal cual estaba
                        escritor.write(lineaActual);
                    }
                    escritor.newLine(); // Mantenemos el salto de línea
                    contadorLineas++;
                }

                if (!lineaModificada) {
                    System.out.println("Aviso: El número de línea introducido supera el total de líneas del fichero.");
                }
            }

            // 3. Reemplazar el fichero original por el temporal de forma segura
            // Eliminamos el original primero
            if (ficheroOriginal.delete()) {
                // Renombramos el temporal con el nombre del original
                if (ficheroTemporal.renameTo(ficheroOriginal)) {
                    System.out.println("Fichero modificado y reescrito exitosamente mediante fichero temporal.");
                } else {
                    System.out.println("Error crítico: No se pudo renombrar el fichero temporal.");
                }
            } else {
                System.out.println("Error crítico: No se pudo eliminar el fichero original. Asegúrate de cerrar todos los flujos.");
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Ocurrió un error durante el proceso: " + e.getMessage());
        }
    }
}