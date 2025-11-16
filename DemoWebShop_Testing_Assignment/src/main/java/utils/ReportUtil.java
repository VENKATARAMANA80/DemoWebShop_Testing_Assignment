package utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ReportUtil {
    private PrintWriter writer;

    public ReportUtil() {
        try {
            writer = new PrintWriter(new FileWriter("report.txt", true));
            writer.println("\n--- Run at: " + LocalDateTime.now() + " ---");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String line) {
        System.out.println(line);
        if (writer != null) writer.println(line);
    }

    public void flush() {
        if (writer != null) {
            writer.println("--- End of Run ---\n");
            writer.flush();
            writer.close();
        }
    }
}
