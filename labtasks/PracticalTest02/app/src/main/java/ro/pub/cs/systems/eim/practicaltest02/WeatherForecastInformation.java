package ro.pub.cs.systems.eim.practicaltest02;

public class WeatherForecastInformation {
    public String temperature;
    public String wind_speed;
    public String humidity;
    public String pressure;
    public String condition;

    public WeatherForecastInformation(String temperature, String wind_speed, String humidity, String pressure, String condition) {
        this.temperature = temperature;
        this.wind_speed = wind_speed;
        this.humidity = humidity;
        this.pressure = pressure;
        this.condition = condition;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "WeatherForecastInformation{" +
                "temperature='" + temperature + '\'' +
                ", wind_speed='" + wind_speed + '\'' +
                ", humidity='" + humidity + '\'' +
                ", pressure='" + pressure + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
}
