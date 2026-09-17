import java.util.Scanner;

public class EjercicioA {
    static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escribe una frase: ");
        String frase= scanner.nextLine();

        //ESTO CALCULA E IMPRIME LA LONGITUD DE LA FRASE.
        int longitud = frase.length();
            System.out.println("Longitud: "+longitud+" caracteres.");

        //PRIMERA Y ÚLTIMA LETRA
        if(longitud>0) {
            char primeraLetra = frase.charAt(0);
            char ultimaLetra = frase.charAt(longitud - 1);
            System.out.println("Primera letra: "+primeraLetra);
            System.out.println("Última letra: "+ultimaLetra);
        }else{
            System.out.println("Primera y última letra no disponible. Frase vacía.");
        }

        //FRASE EN MAYÚSCULAS
        String mayusculas = frase.toUpperCase();


            System.out.println("Primera letra de la frase: ");
            System.out.println("Número de palabras: ");
            System.out.println("Frase invertida");

    }
}