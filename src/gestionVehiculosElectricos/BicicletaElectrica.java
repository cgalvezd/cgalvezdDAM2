package gestionVehiculosElectricos;

public class BicicletaElectrica extends VehiculoElectrico{
    private boolean tienePedales;

    public BicicletaElectrica(String marca, String modelo, int autonomia, boolean tienePedales) {
        super(marca, modelo, autonomia);
        this.tienePedales = tienePedales;
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion()+"\n"
                +"Tiene pedales: "+(tienePedales ? "Sí" : "No" );
    }

    @Override
    public void cargar() {
        System.out.println("Cargando bicicleta eléctrica.");
    }
}
