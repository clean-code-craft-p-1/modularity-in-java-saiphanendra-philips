package temperature.tests;

import temperature.analysis.TemperatureAnalyzer;
import temperature.application.TemperatureProcessingService;
import temperature.domain.ProcessingResult;
import temperature.io.ReadingSource;
import temperature.parsing.TemperatureLineParser;

import java.io.IOException;
import java.util.List;

public class TemperatureProcessingServiceTest {
    public void run() throws IOException {
        tracksBoundaryAndInvalidRows();
    }

    private void tracksBoundaryAndInvalidRows() throws IOException {
        ReadingSource source = fileName -> List.of(
                "09:00:00,20.0",
                "bad-line",
                "09:02:00,999.0",
                "09:03:00,24.0"
        );

        TemperatureProcessingService service = new TemperatureProcessingService(
                source,
                new TemperatureLineParser(),
                new TemperatureAnalyzer()
        );

        ProcessingResult result = service.processFile("ignored.csv");

        assertEquals(4, result.getTotalReadings(), "Expected total boundary size");
        assertEquals(2, result.getValidReadings(), "Expected valid reading count");
        assertEquals(2, result.getInvalidReadingCount(), "Expected invalid reading count");
        assertEquals(2, result.getInvalidLines().size(), "Expected invalid lines list size");
    }

    private void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + ": expected " + expected + " but got " + actual);
        }
    }
}
