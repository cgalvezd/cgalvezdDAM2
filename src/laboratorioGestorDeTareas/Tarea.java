package laboratorioGestorDeTareas;

/**
 *Clase Tarea. Representa una tarea gestionado por el sistema.
 * Asigna de forma automática la duración de horas iniciales en 0.
 * Calcula automáticamente su prioridad entre 10 y 20.
 *
 * @author Cesar
 * @version 1.0
 */
public class Tarea {
    private String name;
    private int priority;
    private String categoria;
    private double hours;

    /**
     * Construye una nueva instancia de Tarea validando que el campo name no esté vacío.
     *
     *
     * @param name Nombre de la tarea
     * @param categoria Categoria de la tarea
     */
    public Tarea(String name, String categoria) {
        this.name = name;
        if(name.trim().isEmpty()){
            System.out.println("Error, el nombre del mensaje no puede estar vacío.");
        }
        this.categoria = categoria;
        //El enunciado dice que se inicializa en 0.
        this.hours = 0;
        //La prioridad se genera automáticamente aquí
        this.priority = (int)(Math.random()*11) + 10;
    }

    /**
     *
     * @return name, el nombre de la tarea
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return priority: la prioridad del mensaje
     */
    public int getPriority() {
        return priority;
    }

    /**
     *
     * @return categoria: Categoría del mensaje.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     *
     * @return hours: la cantidad de horas que dura la tarea.
     */
    public Double getHours() {
        return hours;
    }

    /**
     *
     * @param hours es el nuevo valor que se le asigna al parámetro inicializado en 0.
     * Valida que el valor del parámetro no sea negativo. Lanza un mensaje de error y cierra.
     */
    public void setHours(double hours) {
        if(hours<0){
            System.out.println("Hours cannot be less than 0.");
            return;
        }
        this.hours = hours;
    }

    /**
     *
     * @return los datos de la tarea creada.
     */
    @Override
    public String toString() {
        return "Tarea{" +
                "Name: '" + name + "\n" +
                "Priority: " + priority + "\n" +
                "Categoria: " + categoria + "\n" +
                "Hours: " + hours +
                '}';
    }
}