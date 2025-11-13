package Modelo;

public class RegistroClima {
    private double temperature;
    private double apparentTemperature;
    private double humidity;
    private double windSpeed;
    private double windBearing;
    private double visibility;
    private double loudCover;
    private double pressure;

    public RegistroClima(double temperature, double apparentTemperature, double humidity,
                         double windSpeed, double windBearing, double visibility,
                         double loudCover, double pressure) {
        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windBearing = windBearing;
        this.visibility = visibility;
        this.loudCover = loudCover;
        this.pressure = pressure;
    }

    public double getTemperature() { return temperature; }
    public double getApparentTemperature() { return apparentTemperature; }
    public double getHumidity() { return humidity; }
    public double getWindSpeed() { return windSpeed; }
    public double getWindBearing() { return windBearing; }
    public double getVisibility() { return visibility; }
    public double getLoudCover() { return loudCover; }
    public double getPressure() { return pressure; }

    @Override
    public String toString() {
        return "Temp: " + temperature + " | Apparent: " + apparentTemperature +
               " | Humidity: " + humidity + " | WindSpeed: " + windSpeed +
               " | WindBearing: " + windBearing + " | Visibility: " + visibility +
               " | LoudCover: " + loudCover + " | Pressure: " + pressure;
    }
}