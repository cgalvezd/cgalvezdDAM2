package practicaPrimerExamen2910.Sesion2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.out;

public class Producto_2 {
    int id;
    String nombre;
    double precio;
    int stock;

    public Producto_2(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    //DE TEXTO A OBJETO
    static Producto desdeLinea(String linea){
        String[] c = linea.split(";", -1);
        return new Producto(
                Integer.parseInt(c[0].trim()),
                c[1].trim(),
                Double.parseDouble(c[2].trim().replace(',', '.')),
                Integer.parseInt(c[3].trim())
        );
    }
    //DE OBJETO A TEXTO
        String aLinea(){
            return id +";" + nombre + ";" + String.format(Locale.US, "%.2f", precio).replace(',','.');
    }

    @Override
    public String toString(){ return id + " - "+nombre +" ("+precio+" €, stock "+ ")";}

    static List<Producto> cargar(Path fichero) throws IOException{
        List<Producto> productos = new ArrayList<>();
        List<String> linea = Files.readAllLines(fichero, StandardCharsets.UTF_8);
        for (int i = 1; i < linea.size(); i++) { //i = 1 se salta la cabecera
            String l = linea.get(i);
            if(l.isBlank()) continue;
            productos.add(Producto.desdeLinea(l));
        }
        return productos;
    }

    //MODIFICAR UN REGISTRO Y REESCRIBIR
    static void guardar(Path fichero, List<Producto> productos) throws IOException{
        List<String> lineas = new ArrayList<>();
        lineas.add("id;nomobre;precio;stock");
        for (Producto producto : productos) lineas.add(producto.aLinea());
        Files.write(fichero, lineas, StandardCharsets.UTF_8);
    }

    static Producto buscarPorId(List<Producto> productos, int id){
        for (Producto producto : productos) if(producto.id==id) return producto;
        return null;
    }

    static void main() throws IOException{
        Scanner scanner = new Scanner(System.in);
        Path fichero = Paths.get("producto.csv");

        List<Producto> productos = cargar(fichero);
        productos.forEach(System.out::println);

        out.println("Id del producto a modificar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Producto producto = buscarPorId(productos, id);

        if(producto == null){
            out.println("El ID ingresado no existe");
        }else{
            out.println("El ID ingresado no existe.");
            producto.precio = Double.parseDouble(scanner.nextLine().replace(',', '.'));
            guardar(fichero, productos);
            out.println("Guardado.");
        }

    }
}