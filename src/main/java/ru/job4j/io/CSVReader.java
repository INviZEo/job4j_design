package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        String delimiter = argsName.get("delimiter");
        Path path = Path.of(argsName.get("path"));
        String filter = argsName.get("filter");
        String out = argsName.get("out");
        List<String> rsl = new ArrayList<>();

        try (var scanner = new Scanner(path).useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                rsl.add(scanner.next());
            }
            String[] array = filter.split(",");
            String[] arr2 = rsl.get(0).split(delimiter);
            for (int s = 0; s < array.length; s++) {
                for (int i = 0; i < arr2.length; i++) {
                    if (array[s].equals(arr2[i])) {
                        rsl.add(arr2[i]);
                    }
                }
            }
                if (out.equals("stdout")) {
                    Arrays.stream(arr2).forEach(System.out::println);
                }
//                else {
//                file output stream
//            }
            System.out.println(rsl);
        }
}


    private void validate(ArgsName argsName) {
        if (!argsName.get("path").contains(".csv")) {
            throw new IllegalArgumentException();
        }
        String delimiter = argsName.get("delimiter");
        if (!" ".equals(delimiter)
                && !",".equals(delimiter)
                && !";".equals(delimiter)
                && !"\t".equals(delimiter)
                && !"|".equals(delimiter)) {
            throw new IllegalArgumentException("Separator must be one character long");
        }
        if (argsName.get("filter").isBlank()) {
            throw new IllegalArgumentException();
        }
        if (!"stdout".equals("out") && !argsName.get("out").equals("stdout")) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        CSVReader csvReader = new CSVReader();
        csvReader.validate(argsName);
        handle(argsName);
    }
}