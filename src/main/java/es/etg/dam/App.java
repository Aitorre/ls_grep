package es.etg.dam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class App {
    private static final String MENSAJE_ERROR = "Error al ejecutar los procesos", SALTO_LINEA = "\n";
    private static final String[] COMANDOS_LS = {"ls"}, COMANDOS_GREP = {"grep", "a"};

    public static void main(String[] args) throws IOException, InterruptedException {
        String linea;
        int salidaLs, salidaGrep;

        Process procesoLs = Runtime.getRuntime().exec(COMANDOS_LS);
        Process procesoGrep = Runtime.getRuntime().exec(COMANDOS_GREP);

        try (
                BufferedReader salidaProcesoLs = new BufferedReader(new InputStreamReader(procesoLs.getInputStream()));
                OutputStream entradaProcesoGrep = procesoGrep.getOutputStream()) {

            while ((linea = salidaProcesoLs.readLine()) != null) {
                entradaProcesoGrep.write((linea + SALTO_LINEA).getBytes());
            }
        }

        try (
                BufferedReader salidaFinal = new BufferedReader(new InputStreamReader(procesoGrep.getInputStream()))) {

            while ((linea = salidaFinal.readLine()) != null) {
                System.out.println(linea);
            }
        }

        salidaLs = procesoLs.waitFor();
        salidaGrep = procesoGrep.waitFor();

        if (salidaLs != 0 || salidaGrep != 0) {
            System.err.println(MENSAJE_ERROR);
        }
    }
}