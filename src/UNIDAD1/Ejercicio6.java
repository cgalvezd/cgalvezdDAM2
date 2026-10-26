package UNIDAD1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio6 {
    static Scanner scanner = new Scanner(System.in);

    public void pedirDatos(){
        int opcion=-1;
        List<Persona> datosPersonas = new ArrayList<>();
        do{
            System.out.println("Escribe los datos de las personas que deseas agregar: ");
            System.out.println("Escribe 0 para salir.");

            System.out.println("Escribe el nombre: ");
            String nombre = scanner.next();

            System.out.println("Escribe el apellido: ");
            String apellido = scanner.nextLine();

            System.out.println("Escribe el número de telefono: ");
            int telefono = Integer.parseInt(scanner.nextLine());
            Persona persona = new Persona(nombre, apellido, telefono);

            datosPersonas.add(persona);

        }while(opcion!=0);
    }

    public void agregarPersonasFichero(){
        pedirDatos();
    }

    static void main() {

    }
}