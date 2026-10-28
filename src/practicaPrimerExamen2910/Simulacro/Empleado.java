package practicaPrimerExamen2910.Simulacro;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Empleado {

    private static final Path FICHERO = Paths.get("empleados.csv");

    public static void main(String[] args) {
        try {
            // Aseguramos que el fichero exista al arrancar con datos iniciales si no está creado
            inicializarFichero();

            Scanner sc = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n--- MENÚ DE GESTIÓN ---");
                System.out.println("1. Listar todos los empleados");
                System.out.println("2. Buscar por ID");
                System.out.println("3. Modificar salario");
                System.out.println("4. Borrar empleado");
                System.out.println("5. Añadir empleado");
                System.out.println("6. Crear carpetas anidadas y copiar fichero");
                System.out.println("7. Borrar fichero copiado");
                System.out.println("8. Salir");
                System.out.print("Selecciona una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1 -> listarEmpleados();
                    case 2 -> buscarPorId(sc);
                    case 3 -> modificarSalario(sc);
                    case 4 -> borrarEmpleado(sc);
                    case 5 -> añadirEmpleado(sc);
                    case 6 -> copiarABackup(sc);
                    case 7 -> borrarCopia();
                    case 8 -> System.out.println("¡Saliendo del programa!");
                    default -> System.out.println("Opción no válida.");
                }
            } while (opcion != 8);

        } catch (IOException e) {
            System.err.println("Error crítico en el sistema: " + e.getMessage());
        }
    }

    // Crea el fichero base exigido por el enunciado si no existe
    private static void inicializarFichero() throws IOException {
        if (Files.notExists(FICHERO)) {
            List<String> lineasIniciales = List.of(
                    "id;nombre;departamento;salario",
                    "1;Juan Perez;IT;3500.50",
                    "2;Maria Lopez;Marketing;2800.00",
                    "3;Pedro Gomez;Ventas;2100.25",
                    "4;Ana Martinez;IT;4100.00",
                    "5;Luis Rodriguez;RRHH;2600.75"
            );
            Files.write(FICHERO, lineasIniciales, StandardCharsets.UTF_8);
        }
    }

    // CASO 1: Listar todos
    private static void listarEmpleados() throws IOException {
        System.out.println("\n--- LISTA DE EMPLEADOS ---");
        Files.readAllLines(FICHERO, StandardCharsets.UTF_8).forEach(System.out::println);
    }

    // CASO 2: Buscar por ID
    private static void buscarPorId(Scanner sc) throws IOException {
        System.out.print("Introduce el ID a buscar: ");
        String idBuscar = sc.nextLine();

        List<String> lineas = Files.readAllLines(FICHERO, StandardCharsets.UTF_8);
        boolean encontrado = false;

        for (String linea : lineas) {
            if (linea.startsWith(idBuscar + ";")) {
                System.out.println("Empleado encontrado: " + linea);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) System.out.println("Aviso: El empleado con ID " + idBuscar + " no existe.");
    }

    // CASO 3: Modificar Salario
    private static void modificarSalario(Scanner sc) throws IOException {
        System.out.print("Introduce el ID: ");
        String idModificar = sc.nextLine();
        System.out.print("Introduce el nuevo salario: ");
        String nuevoSalario = sc.nextLine();

        List<String> lineas = Files.readAllLines(FICHERO, StandardCharsets.UTF_8);
        List<String> nuevasLineas = new ArrayList<>();
        boolean modificado = false;

        for (String linea : lineas) {
            if (linea.startsWith(idModificar + ";")) {
                String[] campos = linea.split(";");
                campos[3] = nuevoSalario; // Modifica la columna salario
                nuevasLineas.add(String.join(";", campos));
                modificado = true;
            } else {
                nuevasLineas.add(linea);
            }
        }

        if (modificado) {
            Files.write(FICHERO, nuevasLineas, StandardCharsets.UTF_8);
            System.out.println("Salario modificado y fichero actualizado.");
        } else {
            System.out.println("El empleado no existe.");
        }
    }

    // CASO 4: Borrar por ID
    private static void borrarEmpleado(Scanner sc) throws IOException {
        System.out.print("Introduce el ID a borrar: ");
        String idBorrar = sc.nextLine();

        List<String> lineas = Files.readAllLines(FICHERO, StandardCharsets.UTF_8);
        List<String> nuevasLineas = new ArrayList<>();
        boolean borrado = false;

        for (String linea : lineas) {
            if (linea.startsWith(idBorrar + ";")) {
                borrado = true; // Al no añadirlo a 'nuevasLineas', se elimina automáticamente
            } else {
                nuevasLineas.add(linea);
            }
        }

        if (borrado) {
            Files.write(FICHERO, nuevasLineas, StandardCharsets.UTF_8);
            System.out.println("Empleado eliminado y fichero guardado.");
        } else {
            System.out.println("El empleado no existe.");
        }
    }

    // CASO 5: Añadir empleado nuevo
    private static void añadirEmpleado(Scanner sc) throws IOException {
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("Nombre: "); String nombre = sc.nextLine();
        System.out.print("Departamento: "); String dept = sc.nextLine();
        System.out.print("Salario: "); String salario = sc.nextLine();

        String nuevaLinea = String.format("%s;%s;%s;%s", id, nombre, dept, salario);

        // APPEND añade la línea al final sin sobreescribir lo anterior
        Files.write(FICHERO, List.of(nuevaLinea), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        System.out.println("Empleado añadido con éxito.");
    }

    // Variable global temporal para guardar la ruta elegida por el usuario para el Caso 7
    private static Path ficheroCopiadoRuta;

    // CASO 6: Crear carpetas anidadas y copiar
    private static void copiarABackup(Scanner sc) throws IOException {
        System.out.print("Introduce la ruta de destino (ej: backup/2026/octubre): ");
        String rutaString = sc.nextLine();
        Path carpetasAnidadas = Paths.get(rutaString);

        // Crea las carpetas (no hace nada si ya existen)
        Files.createDirectories(carpetasAnidadas);
        System.out.println("Estructura de directorios verificada/creada.");

        // Definimos la ruta completa del nuevo fichero copiado
        ficheroCopiadoRuta = carpetasAnidadas.resolve(FICHERO.getFileName());

        // Copiamos sobreescribiendo si ya existía la copia
        Files.copy(FICHERO, ficheroCopiadoRuta, StandardCopyOption.REPLACE_EXISTING);

        // Comprobamos si el fichero copiado realmente existe
        if (Files.exists(ficheroCopiadoRuta)) {
            System.out.println("Fichero copiado con éxito en: " + ficheroCopiadoRuta);
        }
    }

    // CASO 7: Borrar copia y comprobar
    private static void borrarCopia() throws IOException {
        if (ficheroCopiadoRuta == null || Files.notExists(ficheroCopiadoRuta)) {
            System.out.println("No existe ninguna copia para borrar o no se ha realizado el paso 6.");
            return;
        }

        Files.delete(ficheroCopiadoRuta);

        // Comprobar que ya no está
        if (Files.notExists(ficheroCopiadoRuta)) {
            System.out.println("Copia eliminada con éxito del disco duro.");
        }
    }
}