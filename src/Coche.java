public class Coche {

    public String marca;
    public String modelo;
    public int velocidadActual;
    public double precio;

    public void acelerar(int incremento){
        velocidadActual = velocidadActual + incremento;
        System.out.println("El coche aceleró. Velocidad: "+velocidadActual+" km/h");
    }

    public void frenar(){
        velocidadActual= 0;
        System.out.println("Freno aplicado. Velocidad actual "+velocidadActual+" km/h");

    }
}