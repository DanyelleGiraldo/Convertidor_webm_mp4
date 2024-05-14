package com.example;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class filechoser {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccione el archivo WebM a convertir");
        int seleccionado = chooser.showOpenDialog(null);

        if (seleccionado == JFileChooser.APPROVE_OPTION) {
            File archivoWebM = chooser.getSelectedFile();

            // Obtener la ruta del archivo seleccionado
            String rutaArchivoWebM = archivoWebM.getAbsolutePath();

            // Mostrar la ruta seleccionada (opcional)
            System.out.println("Archivo WebM seleccionado: " + rutaArchivoWebM);

            // Crear el archivo MP4 en la misma ubicación y con el mismo nombre
            String rutaArchivoMP4 = rutaArchivoWebM.replace(".webm", ".mp4");

            // Comando ffmpeg para la conversión
            String comando = String.format("ffmpeg -i \"%s\" \"%s\"", rutaArchivoWebM, rutaArchivoMP4);

            try {
                // Ejecutar el comando ffmpeg
                Process proceso = Runtime.getRuntime().exec(comando);

                // Leer la salida del proceso
                BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                String linea;
                while ((linea = lector.readLine()) != null) {
                    System.out.println(linea);
                }

                // Esperar a que el proceso termine
                proceso.waitFor();
                System.out.println("La conversión ha finalizado.");

            } catch (IOException | InterruptedException e) {
                e.printStackTrace(); // Imprime la excepción en caso de error
            }
        } else {
            System.out.println("No se ha seleccionado ningún archivo.");
        }
    }
}
