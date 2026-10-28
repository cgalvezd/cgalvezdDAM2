package practicaPrimerExamen2910;

import java.io.*;

public class lecturaEscritura2 {
    static void main() {
        //LECTURA
        try(BufferedReader br = new BufferedReader(new FileReader("datos.txt")
        )){
            String linea;
            while((linea= br.readLine()) != null){
                System.out.println(linea);
            }

        }catch (IOException e){
            System.err.println("ERROR DE E/S: "+e.getMessage());
        }

        //ESCRITURA
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("datos.txt")
        )){
            bw.write("Nueva línea");
            bw.newLine();
        }catch (IOException e){
            System.err.println("ERROR DE E/S: "+e.getMessage());
        }
        }
    }