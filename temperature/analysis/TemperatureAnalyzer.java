package temperature.analysis;

import temperature.domain.TemperatureReading;
import temperature.domain.TemperatureSummary;

import java.util.DoubleSummaryStatistics;
import java.util.List;

public class TemperatureAnalyzer {
    public TemperatureSummary calculateSummary(List<TemperatureReading> readings) {
        DoubleSummaryStatistics stats = readings.stream()
                .mapToDouble(TemperatureReading::getValue)
                .summaryStatistics();
        return new TemperatureSummary(stats.getMax(), stats.getMin(), stats.getAverage());
    }
}
