package temperature.io;

import java.io.IOException;

public interface ReportSink {
    void writeReport(String fileName, String reportContent) throws IOException;
}
