package laboratorioGestionDeMensajes;

import java.util.Scanner;

public class GestorMensajes {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //DATOS DEL PRIMER MENSAJE

        System.out.println("Introduce el nombre del autor del primer mensaje: ");
        String autor1 = scanner.nextLine();
        //VALIDACIÓN DEL CAMPO AUTOR
        if(autor1.trim().isEmpty()){
            throw new IllegalArgumentException("Este campo no puede estar vacío.");
        }


        System.out.println("Introduce el contenido del primer mensaje: ");
        String contenido1 = scanner.nextLine();
        //VALIDACIÓN DEL CAMPO CONTENIDO
        if(contenido1.length()<5 || contenido1.length()>200){
            throw new IllegalArgumentException("La longitud del mensaje debe ser mayor a 5 y menor a 200.");
        }

        //DATOS DEL SEGUNDO MENSAJE


        //MENSAJE 2
        System.out.println("Introduce el autor del segundo mensaje: ");
        String autor2 = scanner.nextLine();

        //VALIDACIÓN DEL CAMPO AUTOR
        if(autor2.trim().isEmpty()){
            throw new IllegalArgumentException("Este campo no puede estar vacío.");
        }


        System.out.println("Introduce el contenido del segundo mensaje: ");
        String contenido2 = scanner.nextLine();

        //VALIDACIÓN DEL CAMPO CONTENIDO
        if(contenido2.length()<5 || contenido2.length()>200){
            throw new IllegalArgumentException("La longitud del mensaje debe ser mayor a 5 y menor a 200.");
        }


        //MENSAJE 3
        System.out.println("Introduce el nombre del autor del tercer mensaje: ");
        String autor3 = scanner.nextLine();
        //VALIDACIÓN DEL CAMPO AUTOR
        if(autor3.trim().isEmpty()){
            throw new IllegalArgumentException("Este campo no puede estar vacío.");
        }

        System.out.println("Introduce el contenido del tercer mensaje: ");
        String contenido3 = scanner.nextLine();

        //VALIDACIÓN DEL CAMPO CONTENIDO
        if(contenido3.length()<5 || contenido3.length()>200){
            throw new IllegalArgumentException("La longitud del mensaje debe ser mayor a 5 y menor a 200.");
        }

        Mensaje mensaje1= new Mensaje(autor1, contenido1);
        Mensaje mensaje2= new Mensaje(autor2, contenido2);
        Mensaje mensaje3= new Mensaje(autor3, contenido3);
    }
}
