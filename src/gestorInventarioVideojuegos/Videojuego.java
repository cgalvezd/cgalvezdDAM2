package gestorInventarioVideojuegos;

public class Videojuego {

    //CONSTANTES
    private static final double IVA = 0.21;

    //ATRIBUTOS
    private String titulo;
    private Genero genero;
    private double precioBase;
    private int valoracion;

    //CONSTRUCTOR
    public Videojuego(String titulo, Genero genero, double precioBase, int valoracion) {
        this.titulo = titulo;
        this.genero = genero;
        this.precioBase = precioBase;
        setValoracion(valoracion);
    }

    //GETTERS
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    //SETTERS
    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        if (valoracion < 1 || valoracion > 100) {
            System.out.println("La valoración debe estar entre 1 y 100.");
            return;
        }
        this.valoracion = valoracion;
    }

    //MÉTODO SOBREESCRITO TOSTRING()
    @Override
    public String toString() {
        return "[Acción] " +
                "Titulo: " + titulo + "-" + "Precio Base: " + precioBase +"€"+
                "[Puntos: " + valoracion +"]";
    }
}