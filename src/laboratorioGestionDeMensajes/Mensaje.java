package laboratorioGestionDeMensajes;

public class Mensaje {
    private String autor;
    private String contenido;
    private long longitud;
    private int palabras;

    public Mensaje(String autor, String contenido) {
        this.autor = autor;
        if(autor.trim().isEmpty()){
            throw new IllegalArgumentException("Este campo no puede estar vacío");
        }
        this.contenido = contenido;
        if(contenido.trim().isEmpty()){
            throw new IllegalArgumentException("Este parámetro no puede estar vacío");
        }
        if(contenido.length()<5 && contenido.length()>200){
            throw new IllegalArgumentException("El contenido del mensaje debe ser mayor a 5 caracteres y debe ser a 200 caracteres");
        }
        this.longitud=calcularLongitud();
        this.palabras=totalPalabras();
    }

    //GETTERS
    public String getAutor() {
        return autor;
    }

    public String getContenido() {
        return contenido;
    }

    public long getLongitud() {
        return longitud;
    }

    public int getPalabras() {
        return palabras;
    }

    //SETTERS
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setLongitud(long longitud) {
        this.longitud = longitud;
    }

    public void setPalabras(int palabras) {
        this.palabras = palabras;
    }

    //METODO PARA CALCULAR LA LONGITUD DEL MENSAJE
    //CUENTA LOS CARACTERES NO VACÍOS
    private int calcularLongitud(){
        int contador = 0;
        for (int i = 0; i < this.contenido.length(); i++) {
            contador++;
        }
        return contador;

    }

    //METODO PARA CALCULAR EL TOTAL DE PALABRAS DEL MENSAJE
    private int totalPalabras(){
        int contador = 0;
        for (int i = 0; i < this.contenido.length(); i++) {
            contador++;
        }
        return contador;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "contenido='" + contenido + '\'' +
                ", longitud=" + calcularLongitud() +
                ", palabras=" + totalPalabras() +
                ", autor='" + autor + '\'' +
                '}';
    }
}
