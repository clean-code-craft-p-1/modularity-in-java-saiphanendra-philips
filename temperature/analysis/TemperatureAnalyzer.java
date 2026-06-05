package temperature.analysis;

import temperature.domain.TemperatureReading;
import temperature.domain.TemperatureSummary;

import java.util.Comparator;
import java.util.List;

public class TemperatureAnalyzer {
    public TemperatureSummary calculateSummary(List<TemperatureReading> readings) {
        double max = readings.stream().map(TemperatureReading::getValue).max(Comparator.naturalOrder()).orElse(0.0);
        double min = readings.stream().map(TemperatureReading::getValue).min(Comparator.naturalOrder()).orElse(0.0);
        double avg = readings.stream().mapToDouble(TemperatureReading::getValue).average().orElse(0.0);
        return new TemperatureSummary(max, min, avg);
    }
}
