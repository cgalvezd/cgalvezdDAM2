package practicaPrimerExamen2910;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class pruebaPrevia {

    //LEER LÍNEA A LÍNEA
    static void main() {
        try(BufferedReader br = new BufferedReader(
                new FileReader("datos.txt", StandardCharsets.UTF_8)
        )){
            String linea;
            while((linea = br.readLine()) != null){
                System.out.println(linea);
            }
        }catch (IOException e){
            System.err.println("Error: "+e.getMessage());
        }

        //ESCRIBIR (EL SEGUNDO PARÁMETRO TRUE = AÑADIR AL FINAL; SIN ÉL, SOBREESCRIBE)

        try(BufferedWriter bw = new BufferedWriter(
                new FileWriter("datos.txt", StandardCharsets.UTF_8, true)
        )){
            bw.write("Nueva Línea");
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error: "+e.getMessage());
        }
    }
}