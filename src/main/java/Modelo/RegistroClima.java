package Modelo;


public class RegistroClima {
    private String fecha;
    private double temperatura;
    private double humedad;
    private double presion;

    public RegistroClima(String fecha, double temperatura, double humedad, double presion) {
        this.fecha = fecha;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.presion = presion;
    }

    public String getFecha() { return fecha; }
    public double getTemperatura() { return temperatura; }
    public double getHumedad() { return humedad; }
    public double getPresion() { return presion; }

    @Override
    public String toString() {
        return fecha + " | Temp: " + temperatura + " | Hum: " + humedad + " | Pres: " + presion;
    }
}
