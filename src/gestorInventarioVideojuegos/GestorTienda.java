package gestorInventarioVideojuegos;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorTienda {
    static ArrayList<Videojuego> catalogo = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    //ELIMINAR POR TITULO
    public static void eliminarPorTitulo(String titulo){
        catalogo.removeIf(vj -> vj.getTitulo().equalsIgnoreCase(titulo));
    }
    static void main() {

        //1. AÑADIR VIDEOJUEGO
        Videojuego vj = new Videojuego("GTA VI", Genero.M, 69.99, 85);
        catalogo.add(vj);

        //2. ELIMINAR POR TITULO
        System.out.println("¿Qué titulo deseas eliminar?");
        String titulo = scanner.nextLine();
        eliminarPorTitulo(titulo);

        //3. BUSQUEDA INTELIGENTE
        System.out.println("Introduce una palabra clave: ");
        String clave = scanner.nextLine();
        for (Videojuego videojuego : catalogo) {
            if(catalogo.contains(videojuego.getTitulo().equalsIgnoreCase(clave))){
                System.out.println(videojuego.toString());
            }
        }

        //4. CAMBIAR PRIORIDAD DE ESCAPARATE (SWAP)
        System.out.println("Ingresa dos posiciones del inventario para intercambiarlas.");

        System.out.println("Posición 1: ");
        int posicion1 = Integer.parseInt(scanner.nextLine());
        if(posicion1 >= 0 || posicion1< catalogo.size()) {
        }

        System.out.println("Posición 2: ");
        int posicion2 = Integer.parseInt(scanner.nextLine());
        if(posicion2 >= 0 || posicion2< catalogo.size()) {
        }

        //5. ANÁLISIS DE PRECIOS (Clase Math)
        System.out.println(" ");
    }
}