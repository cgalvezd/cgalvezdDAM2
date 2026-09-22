package laboratorioGestionVehiculos;

public class Moto extends Vehiculo{
    //ATRIBUTOS PROPIOS
    private int cilindrada;
    private Tipo tipo;

    //CONSTRUCTOR CON TODOS LOS PARÁMETROS
    public Moto(String marca, int velocidadMaxima, int cilindrada, Tipo tipo) {
        super(marca, velocidadMaxima);
        this.cilindrada = cilindrada;
        this.tipo = tipo;
    }

    @Override
    public String mostrar() {
        return super.mostrar()+
                "Cilindrada: "+cilindrada+
                "Tipo: "+tipo;
    }

    public void arrancar(boolean caballete) {
        if(caballete){
            System.out.println("No se puede arrancar la moto con el caballete puesto.");
        }else{
            System.out.println("La moto ha arrancado correctamente.");
        }
    }
}
