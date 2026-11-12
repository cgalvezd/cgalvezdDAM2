package Serializacion;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Ejercicio16 {
    static Scanner scanner = new Scanner(System.in);
    private static List<Producto> listaProducto = new ArrayList<>();

    private static  File rutaFichero = new File("producto.dat");

    private static void menu(){
        while(true){
        System.out.println("============= MENÚ DE OPCIONES =============");
        System.out.println("Elige una opción (0 PARA SALIR):");
        System.out.println("1. Dar de alta un producto.");
        System.out.println("2. Listar todos. ");
        System.out.println("3. Buscar por parte del nombre.");
        System.out.println("4. Modificar por código.");
        System.out.println("5. Borrar por código.");

        try {
        int opcion = Integer.parseInt(scanner.nextLine());


                    switch (opcion) {
                        case 1:
                            altaProducto();
                            break;
                        case 2:
                            listarTodos();
                            break;
                        case 3:
                            buscarParteDelNombre();
                            break;
                        case 4:
                            modificarPorCodigo();
                            break;
                        case 5:
                            borrarPorCodigo();
                            break;
                        case 0:
                            System.out.println("ADIOOSSSSSS!!!!");
                            return;
                        default:
                            System.out.println("Escribe un valor correcto.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Elige una opción válida: " + e.toString());
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error con el fichero: "+e.getMessage());
                }
                }
            }


        private static void guardarProducto() throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaFichero))){
            oos.writeObject(listaProducto);
            }
        }

        @SuppressWarnings("unchecked")
        private static List<Producto> cargarListaProductos() throws IOException, ClassNotFoundException{
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaFichero))){
            return (List<Producto>) ois.readObject();
            }
        }

        private static void altaProducto(){
        try{
            System.out.println("Ingresa el código del producto: ");
            String codigo = scanner.nextLine();

            System.out.println("Ingresa el nombre del producto:");
            String nombre = scanner.nextLine();

            System.out.println("Ingresa el precio del producto: ");
            double precio = Double.parseDouble(scanner.nextLine());

            listaProducto.add(new Producto(codigo, nombre, precio));
            guardarProducto();
            System.out.println("Producto agregado correctamente.");
        }catch(IOException e){
            System.err.println("ERROR al guardar el producto: "+e.getMessage());
        }catch (NumberFormatException e){
            System.err.println("El precio debe ser un número válido: "+e.getMessage());
        }
        }

        private static void listarTodos(){
        if(listaProducto.isEmpty()){
            System.out.println("Lista vacía. Primero debes agregar productos.");
            return;
        }else{
            for (Producto p : listaProducto){
                System.out.println(p.toString());
            }
        }
        }

        private static void buscarParteDelNombre(){
        }

        private static void modificarPorCodigo() throws IOException, ClassNotFoundException {
            System.out.println("Ingresa el código a buscar: ");
            String codigo = scanner.nextLine();

            if(rutaFichero.exists()){
                listaProducto = cargarListaProductos();
            }

        boolean encontrado = false;
        for (Producto p : listaProducto){
            if(p.getCodigo().equalsIgnoreCase(codigo)){
                encontrado = true;
                System.out.println("Producto encontrado.");
                System.out.println("Ingresa el nuevo precio: ");
                double nuevoPrecio = Double.parseDouble(scanner.nextLine());
                p.setPrecio(nuevoPrecio);
                break;
            }
        }

        if(encontrado){
            guardarProducto();
            System.out.println("Producto modificado exitosamente.");
        }else{
            System.out.println("ERROR: Producto con ese código no existe.");
        }
        }

        private static void borrarPorCodigo() throws IOException {
            System.out.println("Ingresa el código a buscar: ");
            String codigo = scanner.nextLine();

            if(listaProducto.isEmpty()){
                System.out.println("Lista vacía. Primero debes agregar productos.");
                return;
            }

            boolean encontrado = false;
            Iterator<Producto> it = listaProducto.iterator();
            while(it.hasNext()){
                Producto p = it.next();
                if(p.getCodigo().equalsIgnoreCase(codigo)){
                    it.remove();
                    encontrado=true;
                    break;
                }
            }

            if(encontrado){
                System.out.println("Producto eliminado existosamente.");
            }else{
                System.out.println("ERROR: No hemos encontrado producto con ese código.");
            }

            guardarProducto();
        }

    static void main() {
        if(rutaFichero.exists()) {
            try {
                listaProducto = cargarListaProductos();
            }catch (IOException | ClassNotFoundException e){
                System.err.println("Error al cargar productos iniciales: "+e.getMessage());
            }
        }

        menu();
    }
    }