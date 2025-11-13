package Modelo;

public class RegistroClima {
    private double temperatura;
    private double humedad;
    private double presion;

    public RegistroClima(double temperatura, double humedad, double presion) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.presion = presion;
    }

    public double getTemperatura() { return temperatura; }
    public double getHumedad() { return humedad; }
    public double getPresion() { return presion; }

    @Override
    public String toString() {
        return " | Temp: " + temperatura + " | Hum: " + humedad + " | Pres: " + presion;
    }
}