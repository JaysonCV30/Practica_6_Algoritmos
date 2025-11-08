package Logica;

import Modelo.RegistroClima;
import java.util.Arrays;

public class MetodosDeOrdenamiento {
    public static void quickSort(RegistroClima[] arr) {
        
    }

    public static void mergeSort(RegistroClima[] arr) {
        
    }

    public static void shellSort(RegistroClima[] arr) {
        
    }

    public static void seleccionDirecta(RegistroClima[] arr) {
        
    }

    public static void sortJava(RegistroClima[] arr) {
        Arrays.sort(arr, (a, b) -> Double.compare(a.getTemperatura(), b.getTemperatura()));
    }

    public static void parallelSortJava(RegistroClima[] arr) {
        Arrays.parallelSort(arr, (a, b) -> Double.compare(a.getTemperatura(), b.getTemperatura()));
    }
}
