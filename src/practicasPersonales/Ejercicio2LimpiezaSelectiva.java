package practicasPersonales;

public class Ejercicio2LimpiezaSelectiva {
    public static String limpiarYComprimirVocales(String texto){
        String nuevaCadena= "";
        for (int i = 0; i < texto.length(); i++) {
            char actual = texto.charAt(i);
            //EVALUAMOS EL ESPACIO
            if(actual == ' '){
                continue;
            }

            //2. Comprobar si es vocal
            boolean esVocal = "aeiouAEIOU".indexOf(actual) != -1;

            if(esVocal && nuevaCadena.length()> 0){
                char ultimoAgregado = nuevaCadena.charAt(nuevaCadena.length() - 1);
                if(Character.toLowerCase(actual) == Character.toLowerCase(ultimoAgregado)){
                    continue;
                }
            }
            nuevaCadena +=actual;
        }
        return nuevaCadena;
    }

    static void main() {
        String saludo = "Hoooooolaaaaaaaaaaaaaa";
        System.out.println("Texto original: "+saludo+"\n");
        System.out.println("Texto sin caracteres repetidos: "+limpiarYComprimirVocales(saludo));
    }

}
