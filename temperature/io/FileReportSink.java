package temperature.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReportSink implements ReportSink {
    @Override
    public void writeReport(String fileName, String reportContent) throws IOException {
        Files.writeString(Path.of(fileName), reportContent);
    }
}
