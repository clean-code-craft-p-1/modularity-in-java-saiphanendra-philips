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
            if (lines.isEmpty()) {
                throw new AssertionError("Expected sample file to contain readings");
            }

            boolean firstLineValid = new TemperatureLineParser()
                    .parseReadingLine(1, lines.get(0))
                    .isValid();
            if (!firstLineValid) {
                throw new AssertionError("Expected first line to be parseable");
            }
        } finally {
            Files.deleteIfExists(Path.of(fileName));
        }
    }
}
