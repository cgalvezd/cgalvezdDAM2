package laboratorioGestionDeMensajes;

import java.util.Scanner;

public class GestorMensaje {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Mensaje mensaje1 = null;
        Mensaje mensaje2 = null;
        Mensaje mensaje3 = null;

        for (int i = 0; i < 3; i++) {
            System.out.println("MENSAJE: " + (i+1));

            //1. LECTURA Y VALIDACIÓN DEL AUTOR
            System.out.println("Introduce el autor del mensaje " + (i+1) + ":");
            String autor = scanner.nextLine();
            while (autor.trim().isEmpty()) {
                System.out.println("El nombre del autor no puede ser un campo vacío.");
                System.out.println("Introduce el autor del mensaje " + (i+1) + ":");
                autor = scanner.nextLine();
            }


            //2. LECTURA Y VALIDACIÓN DEL CONTENIDO
            System.out.println("Introduce el texto del mensaje: ");
            String contenido = scanner.nextLine();
            while (contenido.length() < 5 || contenido.length() > 200) {
                System.out.println("Este campo debe estar contenido entre 5 y 200 caracteres.");
                System.out.println("Introduce el texto del mensaje: ");
                contenido = scanner.nextLine();
            }

            //3. CREACIÓN DEL OBJETO MENSAJE
            Mensaje nuevoMensaje = new Mensaje(autor, contenido);

            //4. ASIGNACIÓN A LA VARIABLE CORRESPONDIENTE SEGÚN LA ITERACIÓN
            if (i == 1) {
                mensaje1 = nuevoMensaje;
            } else if (i == 2) {
                mensaje2 = nuevoMensaje;
            }else{
                mensaje3 = nuevoMensaje;
            }

            //5. SALIDA INFORMATIVA
            System.out.println("Mensaje registrado correctamente.");
            System.out.println("Longitud sin espacios: "+nuevoMensaje.getLongitud());
            System.out.println("Palabras: "+nuevoMensaje.getPalabras());
            System.out.println();
        }

        //MENÚ INTERACTIVO
        int opcion = 0;
        do{
            System.out.println("================BIENVENIDO AL MENÚ DE OPCIONES================");
            System.out.println("1. Mostrar todos los mensajes.");
            System.out.println("2. Mostrar el mensaje más largo.");
            System.out.println("3. Contar ocurrencias de una letra.");
            System.out.println("4. Invertir el contenido de un mensaje.");
            System.out.println("5. Convertir contenido a \"Título Capitalizado\"");
            System.out.println("6. Salir del programa.");
            System.out.println("Elige una opción.");

            try{
                opcion = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Error. Debes introducir un valor válido.");
                opcion = 0;
                continue;
            }

            switch (opcion){
                case 1:
                    // TODO: Opción 1
                    break;
                case 2:
                    // TODO: Opción 2
                    break;
                case 3:
                    // TODO: Opción 3
                    break;
                case 4:
                    // TODO: Opción 4
                    break;
                case 5:
                    // TODO: Opción 5
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opción no válida. Debe ser entre 1 y 6.");
            }
            System.out.println();
        }while (opcion !=6);
    }
}
