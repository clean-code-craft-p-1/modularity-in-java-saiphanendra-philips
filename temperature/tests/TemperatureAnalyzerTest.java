package temperature.tests;

import temperature.analysis.TemperatureAnalyzer;
import temperature.domain.TemperatureReading;
import temperature.domain.TemperatureSummary;

import java.time.LocalTime;
import java.util.List;

public class TemperatureAnalyzerTest {
    private final TemperatureAnalyzer analyzer = new TemperatureAnalyzer();

    public void run() {
        calculatesMinMaxAndAverage();
    }

    private void calculatesMinMaxAndAverage() {
        List<TemperatureReading> readings = List.of(
                new TemperatureReading(LocalTime.parse("09:00:00"), 20.0),
                new TemperatureReading(LocalTime.parse("10:00:00"), 25.0),
                new TemperatureReading(LocalTime.parse("11:00:00"), 30.0)
        );

        TemperatureSummary summary = analyzer.calculateSummary(readings);

        assertEquals(30.0, summary.getMax(), "Expected max temperature");
        assertEquals(20.0, summary.getMin(), "Expected min temperature");
        assertEquals(25.0, summary.getAverage(), "Expected average temperature");
    }

    private void assertEquals(double expected, double actual, String message) {
        if (Math.abs(expected - actual) > 0.0001) {
            throw new AssertionError(message + ": expected " + expected + " but got " + actual);
        }
    }
}
