package UNIDAD1;

import java.io.*;

public class copiarImagen {
    static void main() {
        System.out.println("Ruta actual de ejecución: "+new java.io.File(".").getAbsolutePath());
        try(InputStream entrada = new BufferedInputStream(new FileInputStream("origen/logo"));
            OutputStream salida = new BufferedOutputStream(new FileOutputStream("destino/logo_copia"))){

            byte[] buffer = new byte[4096]; // bloques de 4 KB
            int bytesLeidos;
            while((bytesLeidos = entrada.read(buffer)) != -1){
                salida.write(buffer, 0, bytesLeidos);
            }
            System.out.println("Imagen copiada correctamente.");
        }catch (IOException e){
            System.err.println("Error al copiar la imagen: "+e.getMessage());
        }
    }
}