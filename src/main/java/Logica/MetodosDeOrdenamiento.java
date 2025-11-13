package Logica;

import Modelo.RegistroClima;
import java.util.*;

public class MetodosDeOrdenamiento {

    public enum CampoOrdenable {
        TEMPERATURE, APPARENT_TEMPERATURE, HUMIDITY,
        WIND_SPEED, WIND_BEARING, VISIBILITY, LOUD_COVER, PRESSURE;

        public static CampoOrdenable desdeTexto(String texto) {
            switch (texto) {
                case "Temperature": return TEMPERATURE;
                case "Apparent Temperature": return APPARENT_TEMPERATURE;
                case "Humidity": return HUMIDITY;
                case "Wind Speed": return WIND_SPEED;
                case "Wind Bearing": return WIND_BEARING;
                case "Visibility": return VISIBILITY;
                case "Loud Cover": return LOUD_COVER;
                case "Pressure": return PRESSURE;
                default: return TEMPERATURE;
            }
        }
    }

    public static Comparator<RegistroClima> getComparador(CampoOrdenable campo) {
        switch (campo) {
            case TEMPERATURE: return Comparator.comparingDouble(RegistroClima::getTemperature);
            case APPARENT_TEMPERATURE: return Comparator.comparingDouble(RegistroClima::getApparentTemperature);
            case HUMIDITY: return Comparator.comparingDouble(RegistroClima::getHumidity);
            case WIND_SPEED: return Comparator.comparingDouble(RegistroClima::getWindSpeed);
            case WIND_BEARING: return Comparator.comparingDouble(RegistroClima::getWindBearing);
            case VISIBILITY: return Comparator.comparingDouble(RegistroClima::getVisibility);
            case LOUD_COVER: return Comparator.comparingDouble(RegistroClima::getLoudCover);
            case PRESSURE: return Comparator.comparingDouble(RegistroClima::getPressure);
            default: return Comparator.comparingDouble(RegistroClima::getTemperature);
        }
    }

    public static void quickSort(RegistroClima[] arr, Comparator<RegistroClima> comp) {
        quicksort(arr, 0, arr.length - 1, comp);
    }

    private static void quicksort(RegistroClima[] A, int inicio, int fin, Comparator<RegistroClima> comp) {
        int pos = inicio;
        int izq = inicio;
        int der = fin;
        boolean band = true;

        while (band) {
            band = false;

            while (comp.compare(A[pos], A[der]) <= 0 && pos != der) {
                der--;
            }

            if (pos != der) {
                RegistroClima aux = A[pos];
                A[pos] = A[der];
                A[der] = aux;
                pos = der;

                while (comp.compare(A[pos], A[izq]) >= 0 && pos != izq) {
                    izq++;
                }

                if (pos != izq) {
                    band = true;
                    aux = A[pos];
                    A[pos] = A[izq];
                    A[izq] = aux;
                    pos = izq;
                }
            }
        }

        if ((pos - 1) > inicio) {
            quicksort(A, inicio, pos - 1, comp);
        }
        if (fin > (pos + 1)) {
            quicksort(A, pos + 1, fin, comp);
        }
    }

    public static void mergeSort(RegistroClima[] arr, Comparator<RegistroClima> comp) {
        mergeSort(arr, 0, arr.length - 1, comp);
    }

    private static void mergeSort(RegistroClima[] A, int izq, int der, Comparator<RegistroClima> comp) {
        if (izq < der) {
            int medio = (izq + der) / 2;
            mergeSort(A, izq, medio, comp);
            mergeSort(A, medio + 1, der, comp);
            merge(A, izq, medio, der, comp);
        }
    }

    private static void merge(RegistroClima[] A, int izq, int medio, int der, Comparator<RegistroClima> comp) {
        RegistroClima[] temp = new RegistroClima[der - izq + 1];
        int i = izq, j = medio + 1, k = 0;
        while (i <= medio && j <= der) {
            if (comp.compare(A[i], A[j]) <= 0) {
                temp[k++] = A[i++];
            } else {
                temp[k++] = A[j++];
            }
        }
        while (i <= medio) {
            temp[k++] = A[i++];
        }
        while (j <= der) {
            temp[k++] = A[j++];
        }
        for (i = izq, k = 0; i <= der; i++, k++) {
            A[i] = temp[k];
        }
    }

    public static void shellSort(RegistroClima[] a, Comparator<RegistroClima> comp) {
        int n = a.length;
        int inter = n + 1;

        while (inter > 1) {
            inter = inter / 2;
            boolean band = true;
            while (band) {
                band = false;
                int i = 0;
                while ((i + inter) < n) {
                    if (comp.compare(a[i], a[i + inter]) > 0) {
                        RegistroClima aux = a[i];
                        a[i] = a[i + inter];
                        a[i + inter] = aux;
                        band = true;
                    }
                    i++;
                }
            }
        }
    }

    public static void seleccionDirecta(RegistroClima[] a, Comparator<RegistroClima> comp) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int k = i;
            for (int j = i + 1; j < n; j++) {
                if (comp.compare(a[j], a[k]) < 0) {
                    k = j;
                }
            }
            RegistroClima temp = a[i];
            a[i] = a[k];
            a[k] = temp;
        }
    }

    public static void radixSort(RegistroClima[] arr, Comparator<RegistroClima> comp) {
        sort(arr, comp); // fallback: usar sort con comparador
    }

    public static void sort(RegistroClima[] arr, Comparator<RegistroClima> comp) {
        Arrays.sort(arr, comp);
    }

    public static void parallelSort(RegistroClima[] arr, Comparator<RegistroClima> comp) {
        Arrays.parallelSort(arr, comp);
    }
}
