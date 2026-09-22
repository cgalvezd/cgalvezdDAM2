package laboratorioGestionVehiculos;

public class Vehiculo {
    private String marca;
    private int velocidadMaxima;
    private boolean enMarcha;

    public Vehiculo(String marca, int velocidadMaxima) {
        this.marca = marca;
        if(marca.trim().isEmpty()){
            System.out.println("Debes anotar la Marca del vehiculo.");
            return;
        }

        this.velocidadMaxima = velocidadMaxima;
        if(velocidadMaxima<0){
            System.out.println("El valor de la velocidad máxima no debe ser negaqtivo.");
            return;
        }
        this.enMarcha=false;

    }

    public Vehiculo(String marca) {
        this.marca = marca;
        if(marca.trim().isEmpty()){
            System.out.println("Debes anotar la Marca del vehiculo.");
            return;
        }
        this.velocidadMaxima=120;
        this.enMarcha=false;
    }

    //GETTERS
    public String getMarca() {
        return marca;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public boolean isEnMarcha() {
        return enMarcha;
    }

    //SETTERS

    public void setEnMarcha(boolean enMarcha) {
        this.enMarcha = enMarcha;
    }

    public void setVelocidadMaxima(int velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
        if(velocidadMaxima<0){
            System.out.println("El valor de la velocidad máxima no debe ser negaqtivo.");
            return;
        }
    }

    public void setMarca(String marca) {
        this.marca = marca;
        if(marca.trim().isEmpty()){
            System.out.println("Debes anotar la Marca del vehiculo.");
            return;
        }
    }

    //MÉTODOS
    //1. ARRANCAR
    public void arrancar(){
        this.enMarcha = true;
        System.out.println("El vehiculo ha arrancado.");
    }

    //2. DETENER
    public void detener(){
        this.enMarcha = false;
        System.out.println("Freno inesperado. El vehiculo se ha detenido.");
    }

    //3. MOSTRAR DATOS
    public String mostrar(){
        return "Marca: " + this.marca + "\n"
                +"Velocidad máxima: "+this.velocidadMaxima+"\n"+
                "Estado: "+this.enMarcha;
    }

    //MÉTODOS ESTÁTICOS
    public static Vehiculo vehiculoMasRapido(Vehiculo v1, Vehiculo v2) {
        if (v1.getVelocidadMaxima() > v2.getVelocidadMaxima()) {
            return v1;
        } else if (v1.getVelocidadMaxima() == v2.getVelocidadMaxima()) {
            System.out.println("Ambos vehículos tienen la misma velocidad");
            return v1;
        } else {
            return v2;
        }
    }
}