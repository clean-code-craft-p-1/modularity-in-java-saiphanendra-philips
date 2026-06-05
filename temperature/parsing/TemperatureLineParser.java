package temperature.parsing;

import temperature.domain.TemperatureReading;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class TemperatureLineParser {
    public LineParseResult parseReadingLine(int lineNumber, String rawLine) {
        String line = rawLine.trim();
        if (line.isEmpty()) {
            return LineParseResult.invalid(formatError(lineNumber, rawLine));
        }

        String[] parts = line.split(",");
        if (parts.length != 2) {
            return LineParseResult.invalid(formatError(lineNumber, rawLine));
        }

        String timestampText = parts[0].trim();
        String temperatureText = parts[1].trim();

        if (!timestampText.matches("\\d{2}:\\d{2}:\\d{2}")) {
            return LineParseResult.invalid(formatError(lineNumber, rawLine));
        }

        LocalTime timestamp;
        try {
            timestamp = LocalTime.parse(timestampText);
        } catch (DateTimeParseException exception) {
            return LineParseResult.invalid(formatError(lineNumber, rawLine));
        }

        double value;
        try {
            value = Double.parseDouble(temperatureText);
        } catch (NumberFormatException exception) {
            return LineParseResult.invalid(formatError(lineNumber, rawLine));
        }

        if (value < -100 || value > 200) {
            return LineParseResult.invalid(formatError(lineNumber, rawLine));
        }

        return LineParseResult.valid(new TemperatureReading(timestamp, value));
    }

    private String formatError(int lineNumber, String rawLine) {
        return "  Line " + lineNumber + ": " + rawLine;
    }
}
