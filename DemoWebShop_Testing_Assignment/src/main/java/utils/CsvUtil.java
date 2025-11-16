package utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class CsvUtil {
    public static Map<String,String> readFirstRow(String path) {
        try {
            FileReader reader = new FileReader(path);
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord r : parser) {
                Map<String,String> m = new HashMap<>();
                for (String h : parser.getHeaderMap().keySet()) {
                    m.put(h, r.get(h));
                }
                return m;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
