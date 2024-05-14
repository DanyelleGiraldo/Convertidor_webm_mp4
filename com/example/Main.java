package com.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la ruta al archivo WebM a convertir: ");
        String archivoWebM = scanner.nextLine();

        System.out.print("Ingrese la ruta de salida para el archivo MP4: ");
        String archivoMP4 = scanner.nextLine();

        String comando = String.format("ffmpeg -i %s %s", archivoWebM, archivoMP4);

        try {
            Process proceso = Runtime.getRuntime().exec(comando);

            BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }

            // Esperar a que el proceso termine
            proceso.waitFor();
            System.out.println("La conversión ha finalizado.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
