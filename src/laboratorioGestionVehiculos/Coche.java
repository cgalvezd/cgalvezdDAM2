package laboratorioGestionVehiculos;

public class Coche extends Vehiculo{
    //ATRIBUTOS PROPIOS
    private int numeroPuerta;
    private boolean automatico;

    //CONSTRUCTOR RECIBE TODOS LOS PARÁMETROS.
    public Coche(String marca, int velocidadMaxima, int numeroPuerta, boolean automatico) {
        super(marca, velocidadMaxima);
        this.numeroPuerta = numeroPuerta;
        this.automatico = automatico;
    }

    //MÉTODOS
    //SOBRECARGA DEL MÉTODO ARRANCAR
    public void arrancar(String modo) {
        if(modo.equalsIgnoreCase("llave")){
            System.out.println("Arrancando el coche con llave.");
        }
        if(modo.equalsIgnoreCase("boton")){
            System.out.println("Arrancando el coche con botón.");
        }
    }

    //SOBREESCRIBIR mostrar()
    @Override
    public String mostrar() {
        return super.mostrar() + "\n"+
                "Numero de puertas: "+numeroPuerta + "\n"+
                "Es automático: "+automatico;
    }
}