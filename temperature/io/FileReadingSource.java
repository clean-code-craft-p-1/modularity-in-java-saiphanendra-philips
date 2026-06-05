package temperature.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReadingSource implements ReadingSource {
    @Override
    public List<String> readLinesFrom(String fileName) throws IOException {
        return Files.readAllLines(Path.of(fileName));
    }
}
