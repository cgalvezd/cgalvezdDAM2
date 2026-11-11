package primerExamenAccesoA_Datos;

import examen.Venta;

import javax.swing.plaf.basic.BasicSplitPaneUI;
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

    public static List<Venta> leerVentas(Path fichero, List<String> logErrores){
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
                if(datos.length != 5){
                    String error = "Línea "+numeroLinea+ " ignorada (faltan datos).";
                    System.err.println(error);
                    logErrores.add(error);
                    continue;
                }
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
                    String error = "Línea "+numeroLinea+ " ignorada (faltan datos).";
                    System.err.println(error);
                    logErrores.add(error);
                }
            }
        }catch (IOException e){
            System.err.println("Error de E/S: "+e.getMessage());
        }
        return listaventas;
    }

    public static void guardarLogErrores(List<String> logErrores){
        //DEFINIR LA RUTA EXACTA DENTRO DE LA CARPETA 'SALIDA'
        Path rutaLog = Paths.get("salida", "errores.txt");

        try{
            Files.write(rutaLog, logErrores, StandardCharsets.UTF_8);
            System.out.println("Registro de indicencias guardado con éxito");
        }catch (IOException e){
            System.err.println("No se pudo escribir el archivo de errores "+e.getMessage());
        }

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

    public static void generarInforme(List<Venta> listaVentas){
        //Si la lista está vacía, avisamos y no hacemos cálculos innecesarios.
        if(listaVentas == null || listaVentas.isEmpty()){
            System.out.println("Nada que mostrar. La lista actual está vacía.");
            return;
        }

        double ingresosTotales = 0;
        int totalUnidadesVendidas = 0;

        Venta productoMasCaro = listaVentas.get(0);

        System.out.println("=========== INFORME DE VENTAS INFORMAPLUS ===========");
        for(Venta v: listaVentas){
            double importeAsociado = v.getUnidades() * v.getPrecio();
            ingresosTotales += importeAsociado;
            totalUnidadesVendidas += v.getUnidades();

            //Producto más caro
            if(v.getPrecio() > productoMasCaro.getPrecio()){
                productoMasCaro = v;
            }

            // Mostramos la línea de detalle formateada
            System.out.printf("- %s: %d unidades x %.2f€ = %.2f€\n",
                    v.getProducto(), v.getUnidades(), v.getPrecio(), importeAsociado);
        }
        System.out.println("----------------------------------------");
        System.out.printf("Ingresos totales de la tienda: %.2f€\n", ingresosTotales);
        System.out.println("Total de unidades movilizadas: " + totalUnidadesVendidas);
        System.out.printf("Producto con mayor precio unitario: %s (%.2f€)\n",
                productoMasCaro.getProducto(), productoMasCaro.getPrecio());
        System.out.println("========================================\n");


    }

    static void main() {
        prepararEntorno();

        List<String> incidencias = new ArrayList<>();
        Path fichero = Paths.get("datos", "ventas.txt");

        List<Venta> ventasValidas = leerVentas(fichero, incidencias);

        generarInforme(ventasValidas);
        guardarLogErrores(incidencias);
        System.out.println("Total de ventas válidas cargadas: "+ventasValidas.size());

    }
}