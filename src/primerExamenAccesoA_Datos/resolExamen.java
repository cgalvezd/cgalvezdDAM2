package primerExamenAccesoA_Datos;

import examen.Venta;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class resolExamen {

    public static List<Venta> leerVentas(Path fichero){
        List<Venta> listaventas = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(fichero, StandardCharsets.UTF_8)){
            String linea;
            int numeroLinea = 0;

            while((linea = br.readLine()) != null){
                numeroLinea++;
                if(linea.isEmpty() || linea.startsWith("#")){
                    continue;
                }
                String[] datos = linea.split(";");
                try{
                    String codigo = datos[0].trim();
                    String producto = datos[1].trim();
                    String categoria = datos[2].trim();
                    int unidades = Integer.parseInt(datos[3].trim());
                    double precio = Double.parseDouble(datos[4].trim());

                    if(unidades <=0 || precio < 0){
                        System.err.println("Líena "+numeroLinea + " ignorado (presenta valores negativos o iguales a o) "+linea);
                        continue;
                    }

                    Venta venta = new Venta(codigo, producto, categoria, unidades, precio);
                    listaventas.add(venta);
                }catch (NumberFormatException e){
                    System.err.println("Línea "+numeroLinea + " ignorada (error numérico) "+linea);
                }
            }
        }catch (IOException e){
            System.err.println("Error de E/S: "+e.getMessage());
        }
        return listaventas;
    }

    public static void prepararEntorno(){
        Path ficheroOriginal = Paths.get("datos", "ventas.txt");

        if(!Files.exists(ficheroOriginal) || !Files.isReadable(ficheroOriginal)){
            System.err.println("ERROR crítio: El archivo "+ficheroOriginal.toAbsolutePath() + " no existe o no se puede leer");
            System.exit(1);
        }

        try{
            //REQUISITO 2: MOSTRAR RUTA ABSOLUTA Y TAMAÑO EN BYTES
            System.out.println("Ruta absoluta del archivo: "+ficheroOriginal.toAbsolutePath());
            System.out.println("Tamaño del archivo: "+Files.size(ficheroOriginal)+ " bytes.");

            //REQUISITO 3: Crear los directorios salida y backup si no existe
            Path dirSalida = Paths.get("salida");
            Path dirBackup = Paths.get("backup");

            if(Files.notExists(dirSalida)){
                Files.createDirectories(dirSalida);
                System.out.println("Directorio 'salida' creado correctamente");
            }

            if(Files.notExists(dirBackup)){
                Files.createDirectories(dirBackup);
                System.out.println("Directorio 'backup' creado correctamente.");
            }

            //REQUISITO 4: Copiar ventas.txt en backup/ventas_backup.txt
            Path ficheroCopia = dirBackup.resolve("ventas_backup.txt");
            Files.copy(ficheroOriginal, ficheroCopia, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado con éxito a: "+ficheroCopia);
        }catch (IOException e){
            System.err.println("ERROR DE E/S al procesar los ficheros " + e.getMessage());
        }
    }

    static void main() {

        Path fichero = Paths.get("datos", "ventas.txt");
        List<Venta> ventasValidas = leerVentas(fichero);

        prepararEntorno();

        leerVentas(fichero);
        System.out.println("Total de ventas válidas cargadas: "+ventasValidas.size());

    }
}