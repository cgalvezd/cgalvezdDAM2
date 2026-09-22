package laboratorioGestorDeTareas;

import java.util.Scanner;

public class TestTarea {
    private static Scanner scanner = new Scanner(System.in);

    private Tarea tarea1 = null;
    private Tarea tarea2 = null;
    private Tarea tarea3 = null;

    //Método que registra tareas
    public void registrarTareas() {
        for (int i = 0; i < 3; i++) {

            //1. PEDIMOS EL NOMBRE DE LA TAREA.
            System.out.println("Write the task name " + (i + 1) + ": ");
            String name = scanner.nextLine();
            //VALIDAMOS QUE EL NOMBRE NO SEA UN CAMPO VACÍO
            while (name.trim().isEmpty()) {
                System.out.println("This field cannot be empty. Try again.");
                System.out.println("Write the task name " + (i + 1) + ": ");
                name = scanner.nextLine();
            }

                //2. Pedimos la categoría
                System.out.println("Write the category task.");
                String category = scanner.nextLine();
                while (category.trim().isEmpty()) {
                    System.out.println("This field cannot be empty. Try again.");
                    System.out.println("Write the category task " + (i + 1) + ": ");
                    category = scanner.nextLine();
                }

                //3. PEDIMOS LA DURACIÓN
                double duration = 0;
                boolean duracionValida = false;
                while (!duracionValida) {
                    System.out.println("Introduce la duración en horas (0.5 - 12): ");
                    try {
                        duration = Double.parseDouble(scanner.nextLine());
                        if (duration < 0.5 || duration > 12) {
                            System.out.println("FATAL: You cannot enter values less than 0.5 and greater than 12. Try again.");
                        } else {
                            duracionValida = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: You should enter an valid number. " + e.getMessage());
                    }
                }

                //4. CREAR INSTANCIA Y ASIGNAR DURACION
                Tarea nuevaTarea = new Tarea(name, category);
                nuevaTarea.setHours(duration);
            System.out.println("La prioridad generada automáticamente es: "+nuevaTarea.getPriority());

            //5. Asignar a una variable independiente
            if(i == 0){
                this.tarea1 = nuevaTarea;
            }else if(i == 1){
                this.tarea2 = nuevaTarea;
            }else{
                this.tarea3 = nuevaTarea;
            }
        }
    }

    //MENU INTERACTIVO
    public void menuInteractivo(){
        int opcion = 0;
        do{
            System.out.println("============== MENU DE OPCIONES ==============");
            System.out.println("1. Mostrar todas las tareas.");
            System.out.println("2. Tarea con mayor prioridad.");
            System.out.println("3. Buscar tarea por nombre.");
            System.out.println("4. Calcular y mostrar el promedio redondeado de las duraciones.");
            System.out.println("5. Mostrar las tareas que superen un valor de prioridad introducido por el usuario.");
            System.out.println("6. SALIR");
            System.out.println("Elige una opción.");

            try{
                opcion = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("ERROR: Introduce una opción numérica válida.");
                opcion = 0;
                continue;
            }

            switch (opcion){
                case 1:
                    System.out.println("Has elegido la opción 1.");
                    System.out.println("====== TAREAS REGISTRADAS ======");
                    System.out.println("Tarea 1:");
                    this.tarea1.toString();
                    System.out.println("\nTarea 2:");
                    this.tarea2.toString();
                    System.out.println("\nTarea 3:");
                    this.tarea3.toString();
                    break;

            }


        }while (opcion !=6);
    }

    public static void main(String[] args){
        TestTarea app = new TestTarea();
        app.registrarTareas();
        app.menuInteractivo();
    }
}