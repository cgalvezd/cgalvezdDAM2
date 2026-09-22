package laboratorioGestionDeMensajes;

import java.util.Scanner;

public class GestorMensaje {
    public static void main(String[] args) {
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
            if (i == 0) {
                mensaje1 = nuevoMensaje;
            } else if (i == 1) {
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
                    System.out.println("Has elegido la opción 1.");
                    System.out.println(mensaje1.mostrar());
                    System.out.println(mensaje2.mostrar());
                    System.out.println(mensaje3.mostrar());
                    break;
                case 2:
                    System.out.println("Has elegido la opción 2.");
                    Mensaje mayor = mensaje1;
                    String nombreMensajeMasLargo = mensaje1.getAutor();
                    if(mensaje2.getLongitud() > mayor.getLongitud()){
                        mayor = mensaje2;
                    }
                    if(mensaje3.getLongitud() > mayor.getLongitud()){
                        mayor = mensaje3;
                    }
                    System.out.println("El mensaje más largo es el de " +mayor.getAutor() + ":");
                    System.out.println("\""+mayor.getContenido()+"\"");
                    System.out.println("Longitud sin espacios: "+mayor.getLongitud());
                    System.out.println("Palabras: "+mayor.getPalabras());

                    break;
                case 3:
                    System.out.println("Has elegido la opción 3.");
                    System.out.println("Selecciona el número del mensaje (1-3):");
                    int value = Integer.parseInt(scanner.nextLine());

                    //1. ASIGNAR EL MENSAJE SELECCIONADO
                    Mensaje mensajeSeleccionado = null;
                    if(value == 1){
                        mensajeSeleccionado = mensaje1;
                    }else if(value == 2){
                        mensajeSeleccionado = mensaje2;
                    }else if(value == 3){
                        mensajeSeleccionado = mensaje3;
                    }else{
                        System.out.println("Número de mensaje inválido.");
                        break;
                    }

                    //2. PEDIR Y VALIDAR LA LETRA
                    System.out.println("Introduce la letra a buscar: ");
                    String letra = scanner.nextLine();
                    while(letra.trim().isEmpty()){
                        System.out.println("Entrada vacía. Introduce una letra: ");
                        letra = scanner.nextLine();
                    }
                    //PASAMOS LA LETRA BUSCADA A MINÚSCULA PARA IGNORAR DIFERENCIAS
                    char letraBuscada = Character.toLowerCase(letra.charAt(0));

                    //3. RECORRER EL CONTENIDO CON CONTINUE
                    String texto = mensajeSeleccionado.getContenido();
                    int contadorOcurrencias = 0;
                    for (int i = 0; i < texto.length(); i++) {
                        char caracterActual = texto.charAt(i);

                        //USAR CONTINUE PARA SALTAR ESPACIOS
                        if(caracterActual == ' '){
                            continue;
                        }

                        //COMPARAR CONVIRTIENDO A MINÚSCULA
                        if(Character.toLowerCase(caracterActual) == letraBuscada){
                            contadorOcurrencias++;
                        }
                    }

                    //4. SALIDA ESPERADA
                    System.out.println("La letra '"+letra.charAt(0)+"' aparece "+contadorOcurrencias+ " veces en este mensaje.");
                    break;
                case 4:
                    System.out.println("Has elegido la opción 4.");
                    System.out.println("Elige el mensaje a invertir (1-3):");
                    int opcion2 = Integer.parseInt(scanner.nextLine());

                    String original ="";
                    if(opcion2==1){
                        original = mensaje1.getContenido();
                    } else if (opcion2==2) {
                        original = mensaje2.getContenido();
                    }else if (opcion2 == 3){
                        original = mensaje3.getContenido();
                    }else{
                        System.out.println("Opción de mensaje inválido.");
                        break;
                    }
                    System.out.println("El contenido original es: "+original);
                    String invertida ="";
                    for(int i= original.length() - 1;i>=0;i--){
                        invertida +=original.charAt(i);
                    }
                    System.out.println("Cadena invertida: "+invertida);
                    break;
                case 5:
                    System.out.println("Has elegido la opción 5.");
                    System.out.println("Selecciona el mensaje (1-3): ");
                    int option = Integer.parseInt(scanner.nextLine());

                    //VALIDAMOS EL VALOR INGRESADO POR EL USUARIO.
                    while(option < 1 || option > 3){
                        System.out.println("Opcion no valida. Debes elegir un valor entre 1 y 3.");
                        System.out.println("Intenta nuevamente: ");
                        option = Integer.parseInt(scanner.nextLine());
                    }

                    //GUARDAR Y MOSTRAR EL CONTENIDO ORIGINAL.
                    String textoOriginal ="";
                    if(option==1){
                        textoOriginal = mensaje1.getContenido();
                    } else if (option==2) {
                        textoOriginal = mensaje2.getContenido();
                    }else{
                        textoOriginal = mensaje3.getContenido();
                    }

                    System.out.println("Contenigo original: "+textoOriginal);
                    //TRANSFORMACIÓN A TÍTULO CAPITALIZADO.
                    boolean capitalizarSiguiente = true;
                    String resultado = "";

                    for(int i= 0;i<textoOriginal.length();i++){
                        char c = textoOriginal.charAt(i);

                        if(c == ' '){
                            resultado += c;
                            capitalizarSiguiente = true;
                        }else{
                            if(capitalizarSiguiente){
                                resultado +=Character.toUpperCase(c);
                                capitalizarSiguiente = false;
                            }else{
                                resultado +=Character.toLowerCase(c);
                            }
                        }
                    }

                    System.out.println("Versión en Titulo Capitalizado:");
                    System.out.println(resultado);
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
