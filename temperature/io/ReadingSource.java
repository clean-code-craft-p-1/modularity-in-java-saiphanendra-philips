package temperature.io;

import java.io.IOException;
import java.util.List;

public interface ReadingSource {
    List<String> readLinesFrom(String fileName) throws IOException;
}
