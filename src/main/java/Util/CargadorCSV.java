package Util;

import Modelo.RegistroClima;
import java.io.*;
import java.util.*;

public class CargadorCSV {

    public static List<RegistroClima> cargar(String ruta) {
        List<RegistroClima> registros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            br.readLine(); // Saltar encabezado

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",", -1);

                if (partes.length >= 11) {
                    try {
                        double temperature = Double.parseDouble(partes[3]);
                        double apparentTemperature = Double.parseDouble(partes[4]);
                        double humidity = Double.parseDouble(partes[5]);
                        double windSpeed = Double.parseDouble(partes[6]);
                        double windBearing = Double.parseDouble(partes[7]);
                        double visibility = Double.parseDouble(partes[8]);
                        double loudCover = Double.parseDouble(partes[9]);
                        double pressure = Double.parseDouble(partes[10]);

                        registros.add(new RegistroClima(
                            temperature, apparentTemperature, humidity,
                            windSpeed, windBearing, visibility,
                            loudCover, pressure
                        ));
                    } catch (NumberFormatException e) {
                        System.out.println("Fila ignorada por datos inválidos: " + Arrays.toString(partes));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al cargar CSV: " + e.getMessage());
        }
        return registros;
    }
}
