package gestionVehiculosElectricos;

public class VehiculoElectrico {

    //ATRIBUTOS
    private String marca;
    private String modelo;
    private int autonomia;

    //CONSTRUCTOR CON TODOS LOS PARÁMETROS
    public VehiculoElectrico(String marca, String modelo, int autonomia) {
        setMarca(marca);
        setAutonomia(autonomia);
        setModelo(modelo);
    }

    //GETTERS
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if(marca == null || marca.trim().isEmpty()){
            System.out.println("Este campo no puede ser nulo ni vacío.");
        }
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    //SETTERS
    public void setModelo(String modelo) {
        if(modelo == null || modelo.trim().isEmpty()){
            System.out.println("Este campo no puede estar vacío.");
            return;
        }
        this.modelo = modelo;
    }

    public int getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(int autonomia) {
        if(autonomia<0){
            System.out.println("Este valor no puede ser negativo.");
            return;
        }
        this.autonomia = autonomia;
    }

    //MÉTODO MOSTRAR INFORMACIÓN
    public String mostrarInformacion(){
        return "Marca: "+marca+"\n"
                +"Modelo: "+modelo+"\n"+
                "Autonomía: "+autonomia+"KM";
    }

    //MÉTODO CARGAR
    public void cargar(){
        System.out.println("Cargando vehículo eléctrico.");
    }
}
