package gestionVehiculosElectricos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class EcoMovilApp {

    static Scanner scanner = new Scanner(System.in);
    static List<VehiculoElectrico> vehiculoElectricos = new ArrayList<>();

    //MÉTODO PARA AGREGAR UNA BIBICLETA ELÉCTRICA
    private static void agregarBicicleta(){
        String marca, modelo; int autonomia; boolean pedales;
        System.out.println("Escribe la marca: ");
        marca = scanner.nextLine();
        System.out.println("Escribe el modelo: ");
        modelo = scanner.nextLine();
        System.out.println("Escribe la autonomía: ");
        autonomia = Integer.parseInt(scanner.nextLine());
        System.out.println("¿Tiene pedales (Sí o No)?: ");
        pedales = scanner.nextLine().trim().equalsIgnoreCase("si");

        BicicletaElectrica bE = new BicicletaElectrica(marca, modelo, autonomia, pedales);
        vehiculoElectricos.add(bE);
        System.out.println("Bicicleta registrada exitosamente.");
    }

    //MÉTODO PARA AGREGAR UN PATINETE ELÉCTRICO
    private static void agregarPatinete(){
        String marca, modelo; int autonomia, potenciaMotor;
        System.out.println("Escribe la marca: ");
        marca = scanner.nextLine();
        System.out.println("Escribe el modelo: ");
        modelo = scanner.nextLine();
        System.out.println("Escribe la autonomía: ");
        autonomia = Integer.parseInt(scanner.nextLine());
        System.out.println("Potencia del motor: ");
        potenciaMotor = Integer.parseInt(scanner.nextLine());

        PatineteElectrico pE = new PatineteElectrico(marca, modelo, autonomia, potenciaMotor);
        vehiculoElectricos.add(pE);
        System.out.println("Patinete eléctrico registrada exitosamente.");
    }

    //MÉTODO PARA AGREGAR UN COCHE ELÉCTRICO
    private static void cocheElectrico(){
        String marca, modelo; int autonomia, numeroPlazas;
        System.out.println("Escribe la marca: ");
        marca = scanner.nextLine();
        System.out.println("Escribe el modelo: ");
        modelo = scanner.nextLine();
        System.out.println("Escribe la autonomía: ");
        autonomia = Integer.parseInt(scanner.nextLine());
        System.out.println("Potencia del motor: ");
        numeroPlazas = Integer.parseInt(scanner.nextLine());

        CocheElectrico pE = new CocheElectrico(marca, modelo, autonomia, numeroPlazas);
        vehiculoElectricos.add(pE);
        System.out.println("Coche eléctrico registrada exitosamente.");
    }

    // MÉTODO PRA MOSTRAR TODOS LOS VEHÍCULOS REGISTRADOS
    public void mostrarVehiculos(){
        if(vehiculoElectricos.isEmpty()){
            System.out.println("La lista actual está vacía. Añada vehículos antes de listarlos.");
        }
        Iterator<VehiculoElectrico> misVehiculos = vehiculoElectricos.iterator();
        while(misVehiculos.hasNext()){
            VehiculoElectrico v = misVehiculos.next();
            v.mostrarInformacion();
            System.out.println("=============================================================");

        }
    }
    // MÉTODO PARA CARGAR TODOS LOS VEHÍCULOS

    private static void mostrarMenu(){
        int opcion;
        do{
            System.out.println("Bienvenido al Menú de opciones.");
            System.out.println("Elige una opción (1 - 6).");
            System.out.println("1. Agregar una bicicleta eléctrica.");
            System.out.println("2. Agregar un patinete eléctrico.");
            System.out.println("3. Agregar un coche eléctrico.");
            System.out.println("4. Mostrar todos los vehiculos registrados.");
            System.out.println("5. Cargar todos los vehículois.");
            System.out.println("6. Salir del programa. ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion){
                case 1: agregarBicicleta();
                    break;
                case 2:
                    agregarPatinete();
                    break;
                case 3:
                    cocheElectrico();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("SALIENDO.....");
                    System.out.println("ADIOSSSS");
            }

        }while(opcion !=6);

    }
    static void main() {
        mostrarMenu();
    }
}
