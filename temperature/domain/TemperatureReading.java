package temperature.domain;

import java.time.LocalTime;

public class TemperatureReading {
    private final LocalTime timestamp;
    private final double value;

    public TemperatureReading(LocalTime timestamp, double value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public double getValue() {
        return value;
    }
}

