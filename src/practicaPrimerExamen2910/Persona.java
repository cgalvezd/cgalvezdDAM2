package practicaPrimerExamen2910;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persona {
    private static final long serialVersionUID = 1L;
    String nombre;
    transient String clave;     //transient: NO se guarda

    static void main() {
        Persona  persona = new Persona();
        ArrayList<Persona> lista = new ArrayList();

        //Guarder
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("p.dat"))){
            oos.writeObject(lista);     //ArrayList<Persona> entera
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Leer
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("p.dat"))){
            List<Persona> listaOis = (List<Persona>) ois.readObject();
        }catch (IOException e){
            System.err.println("ERROR DE E/S: "+e.getMessage());
        }

        //LEER Y ESCRIBIR EN UNA POSITION CONCRETA SIN CARGAR TODO
        try(RandomAccessFile raf = new RandomAccessFile("datos.dat", "rw")){
            raf.seek(8);    //Salta al byte 8
            raf.writeInt(42);
            raf.seek(0);
            int x = raf.readInt();
            long tam = raf.length();
        }catch (IOException e){
            System.err.println("ERROR DE E/S: "+e.getMessage());
        }
    }
}
