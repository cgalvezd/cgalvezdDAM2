package practicaPrimerExamen2910;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class lecturaEscritura {
    static void main() {
        //LEER LÍNEA A LÍNEA
        try(BufferedReader br = new BufferedReader(new FileReader("datos.txt", StandardCharsets.UTF_8)
        )){
            String linea;
            while((linea = br.readLine()) != null){
                System.out.println(linea);
            }
        }catch (IOException e){
            System.out.println("ERROR: "+e.getMessage());
        }

        //ESCRIBIR
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("datos.txt", StandardCharsets.UTF_8, true)
        )){
            bw.write("Nueva línea");
            bw.newLine();
        }catch (IOException e){
            System.err.println("ERROR DE ESCRITURA: "+e.getMessage());
        }
    }
}