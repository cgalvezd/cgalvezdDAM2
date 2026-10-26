package UNIDAD1;

public class Persona {
    private String nombres;
    private String apellidos;
    private int telefono;

    public Persona(String nombres, String apellidos, int telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getTelefono() {
        return telefono;
    }
}
