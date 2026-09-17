package laboratorioGestionDeMensajes;

/**
 * REPRESENTA UN MENSAJE GESTIONADO POR EL SISTEMA.
 * CALCULA DE FORMA AUTOMÁTICA SU LONGITUD SIN ESPACIOS Y EL TOTAL DE PALABRAS.
 *
 * @author Cesar
 * @version 1.0
 */
public class Mensaje {
    private String autor;
    private String contenido;
    private long longitud;
    private int palabras;

    /**
     * CONSTRUYE UNA NUEVA INSTANCIA DE MENSAJE VALIDANDO DATOS DE ENTRADA
     * Y CALCULANDO LAS MÉTRICAS DE TEXTO.
     *
     * @param autor Nombre del autor del mensaje (No puede estar vacío)
     * @param contenido Contenido textual del mensaje (Debe contener entre 5 y 200 caracteres)
     * @throws IllegalArgumentException Si el autor  está vacío o el contenido no cumple la condición
     */
    public Mensaje(String autor, String contenido) {
        this.autor = autor;
        if(autor.trim().isEmpty()){
            throw new IllegalArgumentException("Este campo no puede estar vacío");
        }
        this.contenido = contenido;
        if(contenido.trim().isEmpty()){
            throw new IllegalArgumentException("Este parámetro no puede estar vacío");
        }
        if(contenido.length()<5 || contenido.length()>200){
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
    public void setContenido(String contenido) {
        if(contenido == null || contenido.trim().isEmpty() || contenido.length()< 5 || contenido.length()>200){
            throw new IllegalArgumentException("Contenido inválido.");
        }
        this.contenido = contenido;
        this.longitud=calcularLongitud();
        this.palabras=totalPalabras();
    }

    //METODO PARA CALCULAR LA LONGITUD DEL MENSAJE
    //CUENTA LOS CARACTERES NO VACÍOS
    private int calcularLongitud(){
        int contador = 0;
        for (int i = 0; i < this.contenido.length(); i++) {
            if(this.contenido.charAt(i) != ' '){
                contador++;
            }
        }
        return contador;
    }

    //METODO PARA CALCULAR EL TOTAL DE PALABRAS DEL MENSAJE
    private int totalPalabras(){
        int contador = 0;
        boolean enPalabra = false;

        for (int i = 0; i < this.contenido.length(); i++) {
            if(this.contenido.charAt(i) != ' '){
                if(!enPalabra){
                    contador++;
                    enPalabra=true;
                }
            }else{
                enPalabra=false;
            }
        }
        return contador;
    }

    /**
     * GENERA UNA CADENA FORMATEADA CON TODOS LOS DATOS Y MÉTRICAS DEL MENSAJE.
     *
     * @return TEXTO MULTILÍNEA CON AUTOR, CONTENIDO, LONGITUD SIN ESPACIOS Y EL TOTAL DE PALABRAS.
     */
    public String mostrar() {
        return  "Autor: "+autor+ "\n"+
                "Contenido: " + contenido + "\n" +
                "Longitud sin espacios: " + longitud + "\n"+
                "Palabras: " + palabras;
    }
}
