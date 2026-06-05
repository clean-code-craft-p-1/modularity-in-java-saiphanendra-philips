package temperature.parsing;

import temperature.domain.TemperatureReading;

public class LineParseResult {
    private final TemperatureReading reading;
    private final String error;

    private LineParseResult(TemperatureReading reading, String error) {
        this.reading = reading;
        this.error = error;
    }

    public static LineParseResult valid(TemperatureReading reading) {
        return new LineParseResult(reading, null);
    }

    public static LineParseResult invalid(String error) {
        return new LineParseResult(null, error);
    }

    public boolean isValid() {
        return reading != null;
    }

    public TemperatureReading getReading() {
        return reading;
    }

    public String getError() {
        return error;
    }
}

