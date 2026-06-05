package temperature.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessingResult {
    private final int totalReadings;
    private final int validReadings;
    private final int errorCount;
    private final List<String> invalidLines;
    private final TemperatureSummary summary;

    public ProcessingResult(
            int totalReadings,
            int validReadings,
            int errorCount,
            List<String> invalidLines,
            TemperatureSummary summary
    ) {
        this.totalReadings = totalReadings;
        this.validReadings = validReadings;
        this.errorCount = errorCount;
        this.invalidLines = Collections.unmodifiableList(new ArrayList<>(invalidLines));
        this.summary = summary;
    }

    public int getTotalReadings() {
        return totalReadings;
    }

    public int getValidReadings() {
        return validReadings;
    }

    public int getInvalidReadingCount() {
        return errorCount;
    }

    public List<String> getInvalidLines() {
        return invalidLines;
    }

    public TemperatureSummary getSummary() {
        return summary;
    }

    public boolean hasSummary() {
        return summary != null;
    }
}
