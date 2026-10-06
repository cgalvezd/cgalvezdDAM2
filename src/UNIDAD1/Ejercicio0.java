package UNIDAD1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ejercicio0 {
    static Scanner scanner = new Scanner(System.in);

    //MÉTODOS
    //1. MOSTRAR RUTA ABSOLUTA DE LA CARPETA ACTUAL.
    public static void mostrarRutaActual(){
        Path rutaCarpetaActual = Path.of(".").toAbsolutePath();
        System.out.println("La ruta absoluta de la carpeta actual es -> "+rutaCarpetaActual);
    }

    //2. PEDIR POR TECLADO UNA RUTA DE FICHERO O CARPETA Y MOSTRAR SI LO INTRODUCIDO EXISTE
    public static void pedirRutaFichero(){
        boolean esRuta = false, esDirectorio = false;
        //1. PEDIMOS LA RUTA
        System.out.println("Introduce la ruta de un fichero o carpeta");
        String rutaBuscar = scanner.nextLine();
        Path ruta = Paths.get(rutaBuscar);

        //2. BUSCAMOS LA RUTA
        if(Files.notExists(ruta)){
            System.out.println("La ruta indicada no existe.");
            return;
        }
        if(Files.isRegularFile(ruta)){
            esRuta = true;
        }
        if(Files.isDirectory(ruta)){
            esDirectorio = true;
        }

        //3. OBTENER ATRIBUTOS DE FECHA Y HORA
        try {
            BasicFileAttributes atributos = Files.readAttributes(ruta, BasicFileAttributes.class);

            System.out.println("La ruta indicada sí existe.");
            System.out.println("¿Es un fichero?" + (esRuta ? "Sí" : "NO"));
            System.out.println("¿Es un una carpeta?" + (esDirectorio ? "Sí" : "No"));
            System.out.println("Fecha de modificación: ");
        }catch(IOException e){
            System.out.println("No se pudieron leer los datos del archivo "+e.getMessage());
        }
    }

    //3. MOSTRAR EL CONTENIDO DE UNA CARPETA CUYA RUTA SE PIDE POR TECLADO
    public static void mostrarContenido(){
        System.out.println("Indique la ruta de la carpeta a mostrar: ");
        String ruta = scanner.nextLine();
        Path rutaCarpeta = Paths.get(ruta);

        if(Files.notExists(rutaCarpeta)){
            System.out.println(" La ruta indicada no existe.");
            return;
        }

        if(Files.isDirectory(rutaCarpeta)){
            System.out.println("Contenido de la carpeta: ");
            try(Stream<Path> contenido = Files.list(rutaCarpeta)){
                //RECORRER CADA ELEMENTO Y MOSTRAR SOLO EL NOMBRE
                contenido.forEach(elemento -> {
                    System.out.println("- "+elemento.getFileName());
                });
            }catch(IOException e){
                System.out.println("Error al leer el contenido del archivo: "+e.getMessage());
            }

        }else{
            System.out.println("La ruta indicada no es una carpeta.");
        }
    }

    //4. CREAR UNA CARPETA CUYO NOMBRE SE PIDE POR TECLADO
    public static void crearCarpeta(){
        //1. PEDIMOS EL NOMBRE DE LA CARPERA POR TECLADO
        System.out.println("Vas a crear una carpeta.");
        System.out.println("Introduce el nombre de la carpeta que vas a crear.");
        String nombre = scanner.nextLine();

        //2. DEFINIR LA RUTA DE LA NUEVA CARPETA
        Path nuevaRutaCarpeta = Paths.get(nombre);
        //3. COMPROBAR QUE NO EXISTÍA ANTES DE CREARLO
        if(Files.exists(nuevaRutaCarpeta)){
            System.out.println("ERROR: Ya existe una carpeta o fichero con este nombre.");
            return;
        }

        //3. AHORA CREAMOS LA CARPETA
        try{
            Files.createDirectory(nuevaRutaCarpeta);
            System.out.println("Carpeta "+nombre+" creada correctamente.");
            //MUESTRA DONDE FUE CREADA
            System.out.println("Ruta de la carpeta creada: "+nuevaRutaCarpeta.toAbsolutePath());
        } catch (RuntimeException | IOException e) {
            System.out.println("ERror al crear la nueva carpeta -> "+e.getMessage());
        }
    }

    //5. CREAR UN FICHERO CUYO NOMBRE FUE INGRESADO POR TECLADO
    public static void crearFichero(){
        //1. PEDIR EL NOMBRE DEL FICHERO
        System.out.println("Vas a crear un fichero:");
        System.out.println("Introduce el nombre del fichero.");
        String nombreFichero = scanner.nextLine();

        //2. DEFINIR LA RUTA DEL ARCHIVO
        Path ruta = Paths.get(nombreFichero);

        //3. COMPROBAR QUE NO EXISTÍA
        if(Files.exists(ruta)){
            System.out.println("ERROR: Ya existe un archivo o carpeta con ese nombre.");
            return;
        }

        //4. CREAR EL FICHERO
        try{
            Files.createFile(ruta);
            System.out.println("Fichero "+nombreFichero+ " creado correctamente.");
            //RUTA DEL FICHERO
            System.out.println("Ruta del fichero creado "+ruta.toAbsolutePath());
        } catch (Exception e) {
            System.out.println("ERROR AL CREAR EL FICHERO -> "+e.getMessage());
        }
    }

    //6. RENOMBRAR UN FICHERO QUE YA EXISTE.
    public static void renombrarFichero(){
        System.out.println("Vas a renombrar un fichero.");
        System.out.println("Ingresa la ruta del fichero que vas a renombrar");
        String ruta = scanner.nextLine();
        Path siRutaExiste = Paths.get(ruta);

        //COMPROBAMOS QUE LA RUTA EXISTE
        if(Files.notExists(siRutaExiste)){
            System.out.println("Error, esta ruta no existe.");
            return;
        }
        System.out.println("Ingresa el nuevo nombre del fichero:");
        String nuevoNombre = scanner.nextLine();
        //VALIDAMOS QUE ESTE NUEVO NOMBRE NO EXISTE.

    }

    //MENÚ DE OPCIONES
    public static void menu() {
        int opcion = -1;

        do {
            System.out.println("============= BIENVENIDO AL MENÚ DE OPCIONES =============");
            System.out.println("1. Mostrar la ruta absoluta de la carpeta actual.");
            System.out.println("2. Indica la ruta de un fichero o carpeta.");
            System.out.println("3. Mostrar el contenido de una carpeta.");
            System.out.println("4. Crear una carpeta.");
            System.out.println("5. Crear un fichero.");
            System.out.println("6. Renombrar fichero.");
            System.out.println("0. SALIR.");

            if(scanner.hasNextInt()){
                opcion = scanner.nextInt();
                scanner.nextLine();
            }else{
                System.out.println("");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    mostrarRutaActual();
                    break;
                case 2:
                    pedirRutaFichero();
                    break;
                case 3:
                    mostrarContenido();
                    break;
                case 4:
                    crearCarpeta();
                    break;
                case 5:
                    crearFichero();
                    break;
                case 6:
                    renombrarFichero();
                    break;
                case 0:
                    System.out.println("SALIENDO DEL PROGRAMAAAA.....");
                    break;
                default:
                    System.out.println("ERROR al igresar un valor.");

            }
        }while (opcion != 0) ;

    }


    static void main() {
        menu();
    }
}