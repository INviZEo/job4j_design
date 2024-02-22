package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        String delimiter = argsName.get("delimiter");
        Path path = Path.of(argsName.get("path"));
        String filter = argsName.get("filter");
        String out = argsName.get("out");
        List<String> rsl = new ArrayList<>();
        List<String> names = new ArrayList<>();

        try (var scanner = new Scanner(path).useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                rsl.add(scanner.next());
            }
            String[] array = filter.split(",");
            String[] arr2 = rsl.get(0).split(delimiter);
            List<Integer> index = getIndex(array, arr2);
            for (String s : rsl) {
                arr2 = s.split(delimiter);
                for (Integer integer : index) {
                    names.add(arr2[integer]);
                }
            }
            if (out.equals("stdout")) {
                System.out.println(stringBuilder(index, names, delimiter));
            } else {
                try (PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
                    output.append(stringBuilder(index, names, delimiter));
                }
            }
        }
    }

    public static StringBuilder stringBuilder(List<Integer> index, List<String> names, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.size(); i++) {
            sb.append(names.get(i));
            if ((i + 1) % index.size() == 0) {
                sb.append(System.lineSeparator());
            } else {
                sb.append(delimiter);
            }
        }
        return sb;
    }


    public static List<Integer> getIndex(String[] array, String[] arr2) {
        List<Integer> rsl = new ArrayList<>();
        for (int s = 0; s < array.length; s++) {
            for (int i = 0; i < arr2.length; i++) {
                if (array[s].equals(arr2[i])) {
                    rsl.add(i);
                }
            }
        }
        return rsl;
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
        if (!"stdout".equals(argsName.get("out")) && !argsName.get("out").endsWith(".csv")) {
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