package Serializacion;

import java.io.*;
import java.util.*;

public class Ejercicio17b {
    static Scanner scanner = new Scanner(System.in);

    static List<Personaje> listaPersonaje = new ArrayList<>();

    static class Personaje  implements  Serializable {
        private static final long serialVersionUID = 1L;

        private String nombre;
        private int nivel;
        private int puntosDeVida;
        private List<String> inventario;

        public Personaje(String nombre, int nivel, int puntosDeVida, List<String> inventario) {
            this.nombre = nombre;
            this.nivel = nivel;
            this.puntosDeVida = puntosDeVida;
            this.inventario = inventario;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getNivel() {
            return nivel;
        }

        public void setNivel(int nivel) {
            this.nivel = nivel;
        }

        public int getPuntosDeVida() {
            return puntosDeVida;
        }

        public void setPuntosDeVida(int puntosDeVida) {
            this.puntosDeVida = puntosDeVida;
        }

        public List<String> getInventario() {
            return inventario;
        }

        public void setInventario(List<String> inventario) {
            this.inventario = inventario;
        }

        @Override
        public String toString() {
            return "Personaje ->  " +
                    " Nombre: " + nombre + '\'' +
                    " Nivel=" + nivel +
                    " Puntos de vida=" + puntosDeVida +
                    " inventario=" + inventario;
        }
    }

    public static void menuOpciones(){
            while(true){
                try {
                    System.out.println("BIENVENIDO AL MENÚ DE OPCIONES.");
                    System.out.println("Elige una opción válida. 0 para salir.");
                    System.out.println("\n1. Ver personaje.");
                    System.out.println("2. Subir de nivel.");
                    System.out.println("3. Recibir daño.");
                    System.out.println("4. Añadir objeto al inventario.");
                    System.out.println("0. SALIR.");

                    int opcion = Integer.parseInt(scanner.nextLine());

                    switch (opcion) {
                        case 1:
                            verPersonaje();
                            break;
                        case 2:
                            subirNivel();
                            break;
                        case 3:
                            recibirDanio();
                            break;
                        case 4:
                            agregarObjetoAlInventario();
                            break;
                        case 0:
                            System.out.println("SALIENDOOOOOO.");
                            guardar();
                            System.exit(0);
                            break;
                    }
                }catch(NumberFormatException e){
                    System.err.println("Escribe un valor válido.");
        }
        }

    }

    public static void agregarObjetoAlInventario(){
        //ASUMIMOS PARA EFECTOS PRÁCTICOS QUE EL USUARIO INGRESA LOS DATOS CORRECTAMENTE.
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Nivel: ");
        int nivel = Integer.parseInt(scanner.nextLine());

        System.out.println("Puntos de vida: ");
        int puntosDeVida = Integer.parseInt(scanner.nextLine());

        System.out.println("Inventario: ");
        List<String> inventario = new ArrayList<>();

        Personaje p = new Personaje(nombre, nivel, puntosDeVida, inventario);

        listaPersonaje.add(p);
        System.out.println("Personaje agregado exitosamente.");
    }

    //GUARDAR ESTADO
    public static void guardar(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("personajes.dat"))){
            //ESTO GUARDA LA LISTA ENTERA DE UNA VEZ
            oos.writeObject(listaPersonaje);
            System.out.println("Personajes guardado correctamente en el archivo.");
        }catch (IOException e){
            System.out.println("Error al guardar los datos "+e.toString());
        }
    }

    //LEER FICHERO
    public static void leer(){

    }

    public static void verPersonaje(){
        Iterator<Personaje> it = listaPersonaje.iterator();

        while(it.hasNext()){
            Personaje p = it.next();
            System.out.println(p.toString());
        }
    }
    public static void subirNivel(){
        //Asumimos que sube el nivel a todos. De otro modo tendría que primer preguntar a quien y buscarlo
        System.out.println("Cuantos puntos vas a subir? ");
        int puntosASubir = Integer.parseInt(scanner.nextLine());

        Iterator<Personaje> it = listaPersonaje.iterator();
        while(it.hasNext()){
            Personaje p = it.next();
            p.setNivel(p.getNivel() + puntosASubir);
        }
        //ASUMIMOS QUE TODO OK
        System.out.println("Nivel subido exitosamente.");
        guardar();
    }

    public static void recibirDanio(){
        System.out.println("Daño a recibir: ");
        int danio = Integer.parseInt(scanner.nextLine());

        Iterator<Personaje> it = listaPersonaje.iterator();
        while(it.hasNext()){
            Personaje p = it.next();
            p.setPuntosDeVida(Math.max(0, p.getPuntosDeVida()) - danio);
        }

        System.out.println("Personaje crítico. Daño recibido exitosamente.");
        guardar();
    }

    public static void cargar(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("personajes.dat"))){
            listaPersonaje = (ArrayList<Personaje>) ois.readObject();
            System.out.println("Datos cargados correctamente.");
        }catch(FileNotFoundException e){
            System.out.println("No hay archivo precio. Se iniciará una lista vacía.");
        }catch(IOException | ClassNotFoundException e){
            System.out.println("Error al cargar los datos: "+e.getMessage());
        }
    }

    static void main() {
        cargar();
        menuOpciones();
    }

}
