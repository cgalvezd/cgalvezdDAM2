package Serializacion;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Empleado implements Serializable {
    static Scanner scanner = new Scanner(System.in);
    private static final String RUTA_FICHERO = "empleados.obj";
    static List<Empleado> listaEmpleados = new ArrayList<>();

    private String nombre;
    private double sueldo;

    public Empleado(String nombre, double sueldo) {
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }

    static void leerMenu() {
        while (true) {
            try {
                System.out.println("================ MENÚ DE OPCIONES ================");
                System.out.println("1. Listar");
                System.out.println("2. Dar de alta.");
                System.out.println("3. Borrar por nombre.");
                System.out.println("4 Buscar por parte del nombre.");
                System.out.println("5. Subir el sueldo a todos.");
                System.out.println("0. SALIR");
                System.out.println("ELIGE UNA OPCIÓN DEL MENÚ. 0 PARA SALIR");

                int usuarioOpcion = Integer.parseInt(scanner.nextLine());

                switch (usuarioOpcion) {
                    case 1:
                        listarEmpleados();
                        break;
                    case 2:
                        darDeAlta();
                        break;
                    case 3:
                        borrarPorNombre();
                        break;
                    case 4:
                        buscarPorParteDelNombre();
                        break;
                    case 5:
                        subirSueldoATodos();
                        break;
                    case 0:
                        System.out.println("¡HASTA LUEGO!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Elige una opción válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes introducir un valo válido.\n");
            }
        }
    }

    private static List<Empleado> leerFichero(){
        File file = new File(RUTA_FICHERO);

        //SI EL ARCHIVO NO EXISTE, DEVOLVEMOS UNA LISTA VACÍA.
        if(!file.exists()){
            return new ArrayList<>();
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            return (List<Empleado>) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Error al leer el fichero: "+e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void guardarFichero(List<Empleado> list) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_FICHERO))){
            oos.writeObject(list);
        }catch (IOException e){
            System.out.println("Error al escribir en el fichero: "+e.getMessage());
        }
    }

    static List<Empleado> listarEmpleados(){
        for (Empleado e : listaEmpleados){
            System.out.println(e.toString());
        }
        return listaEmpleados;
    }

    static void darDeAlta(){
        System.out.println("Nombre del empleado: ");
        String nombreEmpleado = scanner.nextLine();

        System.out.println("Salario del empleado: ");
        Double salarioEmpleado = Double.parseDouble(scanner.nextLine());

        Empleado e = new Empleado(nombreEmpleado, salarioEmpleado);
        listaEmpleados.add(e);

        guardarFichero(listaEmpleados);
    }

    static void borrarPorNombre(){
        System.out.println("Ingresa el nombre a borrar: ");
        String nombreBuscar = scanner.nextLine();

        boolean encontrado = false;

        Iterator<Empleado> iterator = listaEmpleados.iterator();

        while(iterator.hasNext()){
            Empleado e = iterator.next(); //ESTO "SACA" AL EMPLEADO ACTUAL Y AVANZA EL PUNTERO.

            if(e.getNombre().equalsIgnoreCase(nombreBuscar)){
                iterator.remove();
                encontrado = true;
            }
        }
        if(!encontrado){
            System.out.println("ERROR: No hay empleado con ese nombre.");
        }

        guardarFichero(listaEmpleados);
    }

    static void buscarPorParteDelNombre(){
    }

    static void subirSueldoATodos(){
        System.out.println("¿Cuanto es el sueldo que vas a incrementar?");
        Double incrementoSueldo = Double.parseDouble(scanner.nextLine());

        for (Empleado e : listaEmpleados){
            double nuevoSueldo = e.getSueldo();
            nuevoSueldo+= incrementoSueldo;
            e.setSueldo(nuevoSueldo);
        }

        guardarFichero(listaEmpleados);
    }

    static void main() {
        listaEmpleados = leerFichero();

        leerMenu();
    }
}