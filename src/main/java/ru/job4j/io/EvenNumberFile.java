package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder builder = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                if (read % 2 == 0) {
                    builder.append(read + " ");
                }
            }
            System.out.println(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
