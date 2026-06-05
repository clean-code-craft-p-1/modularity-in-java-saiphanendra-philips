package temperature.tests;

import temperature.SampleDataFile;
import temperature.parsing.TemperatureLineParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SampleDataFileTest {
    public void run() throws IOException {
        createsReadableSampleDataFile();
    }

    private void createsReadableSampleDataFile() throws IOException {
        String fileName = SampleDataFile.create();
        try {
            Path path = Path.of(fileName);
            List<String> lines = Files.readAllLines(path);
            assertFalse(lines.isEmpty(), "Expected sample file to contain readings");

            boolean firstLineValid = new TemperatureLineParser()
                    .parseReadingLine(1, lines.get(0))
                    .isValid();
            assertTrue(firstLineValid, "Expected first line to be parseable");
        } finally {
            Files.deleteIfExists(Path.of(fileName));
        }
    }

    private void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError(message);
        }
    }
}
