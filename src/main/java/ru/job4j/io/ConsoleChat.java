package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.Format;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String chatting = CONTINUE;
        List<String> text = new ArrayList<>();
        Random random = new Random();
        List<String> phrases = readPhrases();

        while (!chatting.equals(OUT)) {
            System.out.print("Вы пишите: ");
            String userInput = scanner.nextLine();
            text.add("Вы пишите: " + userInput);
            int randomIndex = random.nextInt(phrases.size());
            String randomWord = phrases.get(randomIndex);
            if (CONTINUE.equals(userInput)) {
                chatting = CONTINUE;
            }
            if (STOP.equals(userInput)) {
                chatting = STOP;
                System.out.println("Бот теперь молчит");
                text.add("Бот теперь молчит");
                System.out.println("Вы ведёте монолог: ");
                text.add("Вы ведёте монолог: " + userInput);
            }
            if (CONTINUE.equals(chatting)) {
                chatting = CONTINUE;
                System.out.println("Бот отвечает: " + randomWord);
                text.add("Бот отвечает: " + randomWord);
            }
            if (OUT.equals(userInput)) {
                chatting = OUT;
                System.out.println("Диалог окончен");
                text.add("Диалог окончен");
            }
        }
        saveLog(text);
    }


    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader input = (new BufferedReader(
                new FileReader("data/botanswers.txt", StandardCharsets.UTF_8)))) {
            while (input.ready()) {
                phrases.add(input.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter printWriter = new PrintWriter(path, StandardCharsets.UTF_8)) {
            log.forEach(printWriter::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/dialog.txt", "data/botanswers.txt");
        consoleChat.run();
    }
}
