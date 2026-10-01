package gestionVehiculosElectricos;

public class PatineteElectrico extends VehiculoElectrico{
    private int potenciaMotor;

    public PatineteElectrico(String marca, String modelo, int autonomia, int potenciaMotor) {
        super(marca, modelo, autonomia);
        this.potenciaMotor = potenciaMotor;
    }

    public int getPotenciaMotor() {
        return potenciaMotor;
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion()+"\n"
                +"Potencia del motor: "+potenciaMotor+" vatios.";
    }

    @Override
    public void cargar() {
        System.out.println("Cargando patiene eléctrico.");
    }
}
