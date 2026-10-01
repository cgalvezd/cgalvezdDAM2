package gestionVehiculosElectricos;

public class CocheElectrico extends VehiculoElectrico {
    private int numeroPlazas;

    public CocheElectrico(String marca, String modelo, int autonomia, int numeroPlazas) {
        super(marca, modelo, autonomia);
        this.numeroPlazas = numeroPlazas;
    }

    //MÉTODOS


    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion()+"\n"
                +"Número de plazas: "+numeroPlazas;
    }

    @Override
    public void cargar() {
        System.out.println("Cargando coche eléctrico.");
    }
}
