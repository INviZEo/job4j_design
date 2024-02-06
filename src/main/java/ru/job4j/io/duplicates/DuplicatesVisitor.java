package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, Set<Path>> hMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty rsl = new FileProperty(Files.size(file), file.getFileName().toString());
        hMap.putIfAbsent(rsl, new HashSet<>());
        hMap.get(rsl).add(file);
        return FileVisitResult.CONTINUE;
    }
    public Map<FileProperty, Set<Path>> getRsl() {
        return hMap;
    }
}