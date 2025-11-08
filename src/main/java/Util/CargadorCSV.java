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
                String[] partes = linea.split(",");

                // Validar que haya al menos 12 columnas
                if (partes.length >= 12) {
                    try {
                        String fecha = partes[0];
                        double temperatura = Double.parseDouble(partes[3]);
                        double humedad = Double.parseDouble(partes[5]);
                        double presion = Double.parseDouble(partes[10]);

                        registros.add(new RegistroClima(fecha, temperatura, humedad, presion));
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
