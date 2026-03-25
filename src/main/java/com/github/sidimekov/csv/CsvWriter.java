package com.github.sidimekov.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    public static void writeCsv(String filePath, List<Double> xs, List<Double> results, String delimiter) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("X").append(delimiter).append("Result").append("\n");
            for (int i = 0; i < xs.size(); i++) {
                writer.append(xs.get(i).toString())
                        .append(delimiter)
                        .append(results.get(i).toString())
                        .append("\n");
            }
        }
    }
}
