package examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GestionVentas {
    //APARTADO 1
    public static void prepararEntorno() throws IOException {
        Path rutaFichero = Paths.get("datos/ventas");

        //1.1
        if(Files.notExists(rutaFichero) || !Files.isReadable(rutaFichero)){
            System.err.println("Error crítico: El archivo "+rutaFichero.toAbsolutePath()+" no existe o no s puede leer correctamente");
            System.exit(1);
        }

        //1.2
        System.out.println("Ruta absoluta del fichero: "+rutaFichero.toAbsolutePath()+"\nTamaño en bytes: "+rutaFichero.toFile().length());

        //1.3 Crear los directorios salidas y backup si no existen

        Path rutaSalida = Paths.get("examen", "salida");
        Path rutaBackup = Paths.get("examen", "backup");
        if(Files.notExists(rutaSalida)) {
            Files.createDirectories(rutaSalida);
        }

        if(Files.notExists(rutaBackup)){
            Files.createDirectories(rutaBackup);
        }

        //1.4
        //Crear fichero ventas_backup.txt en backup. Sobreescribirlo si ya existe
        Path backup = Paths.get("rutaBackup");

    }

    public static List<Venta> leerVentas(Path fichero){
        List<Venta> listaVentas = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(fichero, StandardCharsets.UTF_8)){
            String linea;
            int numeroLinea = 0;
            while((linea = br.readLine()) != null){
                numeroLinea++;
                if(linea.isEmpty() || linea.startsWith("#")){
                    continue;
                }

                //CONVERTIR A OBJETO DE FORMA SEGURA
                String[] partes = linea.split(";");

                if(partes.length != 5){
                    System.err.println("Línea "+numeroLinea+ " incompleto. Ignorado.");
                    continue;
                }

                try{
                    String codigo = partes[0].trim();
                    String producto = partes[1].trim();
                    String categoria = partes[2].trim();
                    int unidades = Integer.parseInt(partes[3].trim());
                    double precio = Double.parseDouble(partes[4].trim());

                    if(unidades < 0 || precio <0 ){
                        System.err.println("Error en la línea "+numeroLinea+ ". Ignorada.");
                        continue;
                    }
                    //Crear objeto y agregarlo a la lista.
                    listaVentas.add(new Venta(codigo, producto, categoria, unidades, precio));
                }catch(NumberFormatException e){
                    System.err.println();
                }
            }
        }catch (IOException e){
            System.err.println("Error crítico al leer el archivo: "+fichero + ": "+e.getMessage());
        }
        return listaVentas;
    }
}
