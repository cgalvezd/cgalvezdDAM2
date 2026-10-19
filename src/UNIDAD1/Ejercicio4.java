package UNIDAD1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio4 {

    private static void crearFicheroPrueba(String ruta, File archivo){
        try{
            //CREAR DIRECTORIO SI NO EXISTE
            File directorio = new File(ruta);
            if(!directorio.exists()){
                directorio.mkdir();
            }

            //SI EL ARCHIVO NO EXISTE, LO CREAMOS E INSERTAMOS DATOS INVENTADOS
            if(!archivo.exists()){
                try(PrintWriter printWriter = new PrintWriter(new FileWriter(archivo, true))){
                    printWriter.println("Juan; Pérez Gómez; 15/04/1992");
                    printWriter.println("María; López Castro; 23/08/1998");
                    printWriter.println("Carlos; Martín Ruiz; 02/12/1995");
                    printWriter.println("Ana; García Fernández; 30/01/2001");
                    printWriter.println("Luis; Sánchez Gómez; 11/07/1974");
                    System.out.println("[INFO:] Fichero creado con éxito.");
                }
            }
        }catch (IOException e){
            System.out.println("Error crítico al inicializar el fichero "+e.getMessage());
        }
    }

    //MÉTODO PARA LEER, PROCESAR Y CONTAR LAS PERSONAS EN EL FICHERO
    private static void mostrarContenidoFichero(File archivo){
        int contadorPersonas = 0;
        try(Scanner lectorFichero = new Scanner(archivo, "UTF-8")){
            System.out.println("\n--- Leyendo: "+archivo.getName() + " ---");

            while (lectorFichero.hasNextLine()){
                String line = lectorFichero.nextLine().trim();
                if(line.isEmpty()) continue;
                String[] datos = line.split(";");

                if(datos.length == 3){
                    String nombre = datos[0].trim();
                    String apellidos = datos[1].trim();
                    String fechaNac = datos[2].trim();

                    System.out.println("Nombre "+ nombre +" | Apellidos: "+apellidos+" | Fecha de nacimiento: "+fechaNac);
                    contadorPersonas++;
                }else{
                    System.out.println("Línea con formato incorrecto.");
                }
            }
            System.out.println("Número total de personas: "+contadorPersonas);
        }catch (IOException e){
            System.out.println("Error al leer el archivo "+e.getMessage());
        }
    }

    static void main() {
        //DEFINIR LA RUTA ABSOLUTA Y EL NOMBRE DEL ARCHIVO
        String rutaCarpeta = "/home/diurno/Escritorio/AccesoDatos";
        String nombreFichero = "listaPersonas.txt";

        //CREAR EL OBJETO FILE PARA EL ARCHIVO COMPLETO
        File fichero = new File(rutaCarpeta, nombreFichero);

        //1. CREAR LA CARPETA Y EL FICHERO CON DATOS DE PRUEBA SI NO EXISTE
        crearFicheroPrueba(rutaCarpeta, fichero);

        //2. PEDIR NOMBRE DEL FICHERO POR TECLADO
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el nombre del fichero a leer.");
        String nombreIntroducido = scanner.nextLine().trim();

        File ficheroALeer = new File(rutaCarpeta, nombreIntroducido);

        //3. VALIDAR SI EL FICHERO INTRODUCIDO EXISTE.
        if(!ficheroALeer.exists() || ficheroALeer.isDirectory() || nombreIntroducido.isEmpty()) {
            System.out.println("\n[!] El fichero '" + nombreIntroducido + "' no se encuentra");
        }
        if(ficheroALeer.exists()){

            System.out.println("[->] Fichero disponible: "+fichero.getAbsolutePath());
            //REDIRIGIR EL FLUJO PARA LEER EL ARCHIVO CORRECTO AUTOMÁTICAMENTE
            ficheroALeer = fichero;
            scanner.close();
        }
        mostrarContenidoFichero(ficheroALeer);
    }
}