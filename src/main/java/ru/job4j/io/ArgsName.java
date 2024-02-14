package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: 'Xms' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            validate(arg);
            String[] array = arg.replaceFirst("-", "").split("=", 2);
            if (array.length == 2) {
                values.put(array[0], array[1]);
            }
        }
    }


    private static void validate(String s) {
        if (!s.startsWith("-")) {
            throw new IllegalArgumentException("Error: This argument 'request=?msg=Exit=' does not start with a '-' character");
        }
        if (!s.contains("=")) {
            throw new IllegalArgumentException("Error: This argument '-request?msgHello' does not contain an equal sign");
        }
        String[] array = s.replaceFirst("-", "").split("=", 2);
        if (array[0].isBlank()) {
            throw new IllegalArgumentException("Error: This argument '" + s + "' does not contain a key");
        }
        if (array[1].isBlank()) {
            throw new IllegalArgumentException("Error: This argument '" + s + "' does not contain a value");
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}