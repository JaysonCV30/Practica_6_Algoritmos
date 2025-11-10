package GUI;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import Modelo.RegistroClima;
import Util.CargadorCSV;
import Util.MedidorTiempo;
import Logica.MetodosDeOrdenamiento;
import java.util.*;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class VistaPrincipal {

    private List<RegistroClima> registros;

    public VistaPrincipal(Stage stage) {
        registros = CargadorCSV.cargar("src/main/resources/weatherHistory.csv");

        // CheckBoxes para algoritmos
        Label tituloSeleccion = new Label("Seleccione los algoritmos que desea visualizar:");
        CheckBox cbQuickSort = new CheckBox("QuickSort");
        CheckBox cbMergeSort = new CheckBox("MergeSort");
        CheckBox cbShellSort = new CheckBox("ShellSort");
        CheckBox cbSeleccion = new CheckBox("Seleccion Directa");
        CheckBox cbRadixSort = new CheckBox("RadixSort");
        CheckBox cbSort = new CheckBox("Sort");
        CheckBox cbParallelSort = new CheckBox("ParallelSort");

        VBox seleccionAlgoritmos = new VBox(5, tituloSeleccion, cbQuickSort, cbMergeSort, cbShellSort, cbSeleccion, cbRadixSort, cbSort, cbParallelSort);
        seleccionAlgoritmos.setPadding(new Insets(10));
        seleccionAlgoritmos.setStyle("-fx-border-color: gray; -fx-border-radius: 5; -fx-padding: 10;");

        // Gráfica de tiempos
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Algoritmo");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Tiempo (ns)");

        LineChart<String, Number> grafica = new LineChart<>(xAxis, yAxis);
        grafica.setTitle("Comparación de Tiempos de Ordenamiento");
        grafica.setLegendVisible(true);
        grafica.setAnimated(false);
        grafica.setPrefHeight(600);

        // Botón para graficar
        Button btnGraficar = new Button("Graficar");
        btnGraficar.setOnAction(e -> {
            Map<String, Long> tiempos = new LinkedHashMap<>();

            if (cbQuickSort.isSelected()) {
                RegistroClima[] copia = registros.toArray(new RegistroClima[0]);
                long t = MedidorTiempo.medir(() -> MetodosDeOrdenamiento.quickSort(copia));
                tiempos.put("QuickSort", t);
            }

            if (cbMergeSort.isSelected()) {
                RegistroClima[] copia = registros.toArray(new RegistroClima[0]);
                long t = MedidorTiempo.medir(() -> MetodosDeOrdenamiento.mergeSort(copia));
                tiempos.put("MergeSort", t);
            }

            if (cbShellSort.isSelected()) {
                RegistroClima[] copia = registros.toArray(new RegistroClima[0]);
                long t = MedidorTiempo.medir(() -> MetodosDeOrdenamiento.shellSort(copia));
                tiempos.put("ShellSort", t);
            }

            if (cbSeleccion.isSelected()) {
                RegistroClima[] copia = registros.toArray(new RegistroClima[0]);
                long t = MedidorTiempo.medir(() -> MetodosDeOrdenamiento.seleccionDirecta(copia));
                tiempos.put("Seleccion Directa", t);
            }

            if (cbRadixSort.isSelected()) {
                RegistroClima[] copia = registros.toArray(new RegistroClima[0]);
                long t = MedidorTiempo.medir(() -> MetodosDeOrdenamiento.radixSort(copia));
                tiempos.put("RadixSort", t);
            }

            if (cbSort.isSelected()) {
                RegistroClima[] copia = registros.toArray(new RegistroClima[0]);
                long t = MedidorTiempo.medir(() -> MetodosDeOrdenamiento.sort(copia));
                tiempos.put("Sort", t);
            }

            if (cbParallelSort.isSelected()) {
                RegistroClima[] copia = registros.toArray(new RegistroClima[0]);
                long t = MedidorTiempo.medir(() -> MetodosDeOrdenamiento.parallelSort(copia));
                tiempos.put("ParallelSort", t);
            }

            if (tiempos.isEmpty()) {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecciona al menos un algoritmo para graficar.");
                alerta.show();
                return;
            }

            XYChart.Series<String, Number> serie = new XYChart.Series<>();
            serie.setName("Tiempos de ejecución");

            for (Map.Entry<String, Long> entry : tiempos.entrySet()) {
                String etiqueta = entry.getKey() + " (ns: " + entry.getValue() + ")";
                XYChart.Data<String, Number> punto = new XYChart.Data<>(etiqueta, entry.getValue());

                serie.getData().add(punto);
            }

            grafica.getData().clear();
            grafica.getData().add(serie);
        });

        HBox controles = new HBox(20, seleccionAlgoritmos, btnGraficar);
        VBox layout = new VBox(20, controles, grafica);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 900, 800);
        stage.setTitle("Visualizador de Clima y Rendimiento");
        stage.setScene(scene);
        stage.show();
    }
}
