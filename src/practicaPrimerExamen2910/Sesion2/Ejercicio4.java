import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio4 {

    // Clase interna para gestionar los productos fácilmente
    static class Producto {
        int id;
        String nombre;
        double precio;
        int stock;

        public Producto(int id, String nombre, double precio, int stock) {
            this.id = id;
            this.nombre = nombre;
            this.precio = precio;
            this.stock = stock;
        }

        // Convierte el objeto al formato de línea CSV para guardar
        public String toCSV() {
            return id + ";" + nombre + ";" + precio + ";" + stock;
        }
    }

    public static void main(String[] args) {
        String ruta = "productos.csv";
        Scanner sc = new Scanner(System.in);

        // 1. CARGAR los productos en memoria
        List<Producto> lista = cargarProductos(ruta);

        // 2. MOSTRAR productos con stock 0
        System.out.println("--- Productos con Stock 0 ---");
        for (Producto p : lista) {
            if (p.stock == 0) {
                System.out.println("ID: " + p.id + " | " + p.nombre);
            }
        }

        // 3. PEDIR ID Y CANTIDAD VENDIDA (Restar stock)
        System.out.print("\nIntroduce el ID del producto vendido: ");
        int idVenta = sc.nextInt();
        System.out.print("Introduce la cantidad vendida: ");
        int cantVenta = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer

        for (Producto p : lista) {
            if (p.id == idVenta) {
                if (p.stock >= cantVenta) {
                    p.stock -= cantVenta;
                    System.out.println("Stock actualizado correctamente.");
                } else {
                    System.out.println("AVISO: No hay suficiente stock. No se modifica.");
                }
            }
        }

        // 4. PEDIR DATOS DE UN NUEVO PRODUCTO (ID autoincremental)
        System.out.println("\n--- Añadir Nuevo Producto ---");
        System.out.print("Nombre: ");
        String nuevoNombre = sc.nextLine();
        System.out.print("Precio: ");
        double nuevoPrecio = sc.nextDouble();
        System.out.print("Stock inicial: ");
        int nuevoStock = sc.nextInt();

        // Obtener el último ID (si la lista está vacía, empieza en 1)
        int ultimoId = lista.isEmpty() ? 0 : lista.get(lista.size() - 1).id;
        int nuevoId = ultimoId + 1;

        lista.add(new Producto(nuevoId, nuevoNombre, nuevoPrecio, nuevoStock));
        System.out.println("Producto añadido con ID: " + nuevoId);

        // 5. GUARDAR el archivo
        guardarProductos(ruta, lista);

        // EXTRA: Calcular y mostrar el valor total del inventario
        double valorTotal = 0;
        for (Producto p : lista) {
            valorTotal += p.precio * p.stock;
        }
        System.out.printf("\nValor total del inventario: %.2f€\n", valorTotal);
    }

    // ==========================================
    // MEMORIZAR: MÉTODO PARA CARGAR (LECTURA)
    // ==========================================
    public static List<Producto> cargarProductos(String ruta) {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                double precio = Double.parseDouble(datos[2]);
                int stock = Integer.parseInt(datos[3]);

                productos.add(new Producto(id, nombre, precio, stock));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se creará uno nuevo al guardar.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return productos;
    }

    // ==========================================
    // MEMORIZAR: MÉTODO PARA GUARDAR (ESCRITURA)
    // ==========================================
    public static void guardarProductos(String ruta, List<Producto> productos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Producto p : productos) {
                bw.write(p.toCSV());
                bw.newLine();
            }
            System.out.println("Archivo guardado con éxito.");
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}
