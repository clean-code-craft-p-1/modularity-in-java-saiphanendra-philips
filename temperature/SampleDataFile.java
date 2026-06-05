package temperature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class SampleDataFile {
    private SampleDataFile() {
    }

    public static String create() throws IOException {
        Path file = Files.createTempFile("temperature-readings-", ".csv");
        Files.write(file, List.of(
                "09:00:00,20.0",
                "10:00:00,25.0",
                "11:00:00,23.0"
        ));
        return file.toString();
    }
}
