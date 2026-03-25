package com.github.sidimekov.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    public static void writeCsv(String file, List<String[]> rows, String delim) {
        try (FileWriter w = new FileWriter(file)) {
            for (String[] row : rows) {
                w.append(String.join(delim, row)).append("\n");
            }
        } catch (IOException e) {
            System.err.println("CSV write error: " + e.getMessage());
        }
    }
}
