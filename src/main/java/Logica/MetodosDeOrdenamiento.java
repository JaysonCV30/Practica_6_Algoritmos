package Logica;

import Modelo.RegistroClima;
import java.util.*;

public class MetodosDeOrdenamiento {

    public static void quickSort(RegistroClima[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    private static void quicksort(RegistroClima[] A, int inicio, int fin) {
        int pos = inicio;
        int izq = inicio;
        int der = fin;
        boolean band = true;

        while (band) {
            band = false;

            while (A[pos].getTemperatura() <= A[der].getTemperatura() && pos != der) {
                der--;
            }

            if (pos != der) {
                RegistroClima aux = A[pos];
                A[pos] = A[der];
                A[der] = aux;
                pos = der;

                while (A[pos].getTemperatura() >= A[izq].getTemperatura() && pos != izq) {
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
            quicksort(A, inicio, pos - 1);
        }
        if (fin > (pos + 1)) {
            quicksort(A, pos + 1, fin);
        }
    }

    public static void mergeSort(RegistroClima[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(RegistroClima[] A, int izq, int der) {
        if (izq < der) {
            int medio = (izq + der) / 2;
            mergeSort(A, izq, medio);
            mergeSort(A, medio + 1, der);
            merge(A, izq, medio, der);
        }
    }

    private static void merge(RegistroClima[] A, int izq, int medio, int der) {
        RegistroClima[] temp = new RegistroClima[der - izq + 1];
        int i = izq, j = medio + 1, k = 0;
        while (i <= medio && j <= der) {
            if (A[i].getTemperatura() <= A[j].getTemperatura()) {
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

    public static void shellSort(RegistroClima[] a) {
        int n = a.length;
        int inter = n + 1;

        while (inter > 1) {
            inter = inter / 2;
            boolean band = true;
            while (band) {
                band = false;
                int i = 0;
                while ((i + inter) < n) {
                    if (a[i].getTemperatura() > a[i + inter].getTemperatura()) {
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

    public static void seleccionDirecta(RegistroClima[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int k = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j].getTemperatura() < a[k].getTemperatura()) {
                    k = j;
                }
            }
            RegistroClima temp = a[i];
            a[i] = a[k];
            a[k] = temp;
        }
    }

    public static void radixSort(RegistroClima[] arr) {
        List<RegistroClima> positivos = new ArrayList<>();
        List<RegistroClima> negativos = new ArrayList<>();

        for (RegistroClima r : arr) {
            if (r.getTemperatura() >= 0) {
                positivos.add(r);
            } else {
                negativos.add(r);
            }
        }

        RegistroClima[] positivosArr = positivos.toArray(new RegistroClima[0]);
        RegistroClima[] negativosArr = negativos.toArray(new RegistroClima[0]);

        radixSortPositivos(positivosArr);
        radixSortNegativos(negativosArr);

        int i = 0;
        for (int j = negativosArr.length - 1; j >= 0; j--) {
            arr[i++] = negativosArr[j];
        }
        for (RegistroClima r : positivosArr) {
            arr[i++] = r;
        }
    }


    private static void radixSortPositivos(RegistroClima[] arr) {
        int max = (int) Math.round(Arrays.stream(arr)
                .mapToDouble(RegistroClima::getTemperatura)
                .max().orElse(0));

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortPorDigito(arr, exp);
        }
    }

    private static void radixSortNegativos(RegistroClima[] arr) {
        RegistroClima[] copia = Arrays.stream(arr)
            .map(r -> new RegistroClima(Math.abs(r.getTemperatura()), r.getHumedad(), r.getPresion()))
            .toArray(RegistroClima[]::new);

        radixSortPositivos(copia);

        for (int i = 0; i < copia.length; i++) {
            arr[i] = new RegistroClima(-copia[i].getTemperatura(), copia[i].getHumedad(), copia[i].getPresion());
        }
    }

    private static void countingSortPorDigito(RegistroClima[] arr, int exp) {
        int n = arr.length;
        RegistroClima[] output = new RegistroClima[n];
        int[] count = new int[10];

        for (RegistroClima r : arr) {
            int valor = (int) Math.round(r.getTemperatura());
            int digito = Math.abs((valor / exp) % 10);
            if (digito < 0 || digito >= count.length) continue;
            count[digito]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int valor = (int) Math.round(arr[i].getTemperatura());
            int digito = Math.abs((valor / exp) % 10);
            if (digito < 0 || digito >= count.length) continue;
            output[count[digito] - 1] = arr[i];
            count[digito]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void sort(RegistroClima[] arr) {
        Arrays.sort(arr, (a, b) -> Double.compare(a.getTemperatura(), b.getTemperatura()));
    }

    public static void parallelSort(RegistroClima[] arr) {
        Arrays.parallelSort(arr, (a, b) -> Double.compare(a.getTemperatura(), b.getTemperatura()));
    }
}
