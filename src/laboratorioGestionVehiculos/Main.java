package laboratorioGestionVehiculos;

public class Main {
    static void main() {
        //3.1 CREACIÓN DE OBJETOS
        Vehiculo v = new Vehiculo("Toyota", 320);
        Vehiculo v2 = new Vehiculo("Honda");

        Coche coche = new Coche("Toyota", 420, 2, true);
        Moto moto = new Moto("Ninja", 500, 650, Tipo.DEPORTIVA);

        //3.2 USO DE SETTERS & GETTERS
        //GETTERS
        System.out.println("Marca del primer coche creado: ");
        System.out.println(coche.getMarca());
        System.out.println("Velocuidad del primer coche creado.");
        System.out.println(coche.getVelocidadMaxima());

        //SETTERS
        System.out.println("\nNueva velocidad del coche primer creado (SET)");
        coche.setVelocidadMaxima(10);
        System.out.println(coche.getVelocidadMaxima());

        //3.3.1 MOSTRAR DATOS
        System.out.println("\nDatos completos del primer VEHICULO creado.");
        System.out.println(v.mostrar());
        System.out.println("\nDatos completos del segundo VEHICULO creado.");

        System.out.println(v2.mostrar());

        coche.mostrar();
        moto.mostrar();

        //3.3.2 ARRANCA
        System.out.println("\nArrancar coches:");
        v.arrancar();
        coche.arrancar("llave");
        moto.arrancar(true);
        moto.arrancar(false);


        //3.4 USO DEL MÉTODO ESTÁTICO
        System.out.println("Uso del método estático. Comparamos ");
        Vehiculo vehiculoMasRapido = Vehiculo.vehiculoMasRapido(v, v2);
        System.out.println("La marca del vehículo más rápido es " +vehiculoMasRapido.getMarca());

    }
}