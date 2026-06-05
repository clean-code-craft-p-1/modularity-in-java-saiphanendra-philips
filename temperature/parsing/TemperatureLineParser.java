package temperature.parsing;

import temperature.domain.TemperatureReading;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class TemperatureLineParser {
    private static final Pattern TIMESTAMP_PATTERN = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");

    public LineParseResult parseReadingLine(int lineNumber, String rawLine) {
        String line = rawLine.trim();
        if (line.isEmpty()) {
            return LineParseResult.invalid(formatError(lineNumber, rawLine));
        }

        String[] parts = line.split(",", 2);
        if (parts.length != 2) {
            return LineParseResult.invalid(formatError(lineNumber, rawLine));
        }

        String timestampText = parts[0].trim();
        String temperatureText = parts[1].trim();

        if (!TIMESTAMP_PATTERN.matcher(timestampText).matches()) {
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
