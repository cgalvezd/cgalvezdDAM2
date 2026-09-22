package practicasPersonales;

public class practica1Metodos {
    public static int contarGruposNumericos(String texto){
        boolean esNumero = false;
        int contador= 0;

        //RECORREMOS EL TEXTO
        for (int i = 0; i < texto.length(); i++) {
            if(Character.isDigit(texto.charAt(i))){
                if(!esNumero){
                    contador++;
                    esNumero = true;
                }
            }else{
                esNumero = false;
            }
        }
        return contador;
    }

    //
}