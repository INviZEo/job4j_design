package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Analysis {

    private final Map<String, String> values = new HashMap<>();

    public void unavailable(String source, String target) {
        try (BufferedReader input = new BufferedReader(new FileReader(source));
            BufferedWriter output = new BufferedWriter(new FileWriter(target))) {

            String startValue = null;
            String endValue = null;
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                String[] array = line.split(" ", 2);
                String key = array[0];
                String value = array[1];
                if (("400".equals(key) || "500".equals(key)) && startValue == null) {
                    startValue = value;
                } else if (("100".equals(key) || "200".equals(key) || "300".equals(key)) && startValue != null) {
                    endValue = value;
                }
                if (startValue != null && endValue != null) {
                    output.append(startValue)
                            .append(" ")
                            .append(endValue)
                            .append(System.lineSeparator());
                    startValue = null;
                    endValue = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
