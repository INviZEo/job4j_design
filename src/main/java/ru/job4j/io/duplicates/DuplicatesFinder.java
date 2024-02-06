package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor rsl = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), rsl);
        Map<FileProperty, Set<Path>> map = rsl.getRsl();
        for (FileProperty property : map.keySet()) {
            if (map.get(property).size() > 1) {
                System.out.println(String.format("%s - %s", property.getName(), property.getSize()));
                for (Path valueSet : map.get(property)) {
                    System.out.println(valueSet);
                }
            }
        }
    }
}