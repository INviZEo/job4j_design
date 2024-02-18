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
        boolean chatting = false;
        List<String> text = new ArrayList<>();
        Random random = new Random();

        flag:
        while (!chatting) {
            System.out.print("Вы пишите: ");
            String userInput = scanner.nextLine();
            int randomIndex = random.nextInt(readPhrases().size());
            String randomWord = readPhrases().get(randomIndex);
            text.add("Вы пишите: " + userInput);
            if (!STOP.equals(userInput)) {
                System.out.println("Бот отвечает: " + randomWord);
                text.add("Бот отвечает: " + randomWord);
            }
            if (OUT.equals(userInput)) {
                chatting = true;
                System.out.println("Закончили из первого цикла");
                text.add("Закончили из первого цикла");
            } else if (STOP.equals(userInput)) {
                System.out.println("Бот теперь молчит");
                text.add("Бот теперь молчит");
                boolean chattingNoBot = false;
                while (!chattingNoBot) {
                    System.out.print("Вы ведёте монолог: ");
                    String inputNoBot = scanner.nextLine();
                    text.add("Вы ведёте монолог: " + inputNoBot);
                    if (CONTINUE.equals(inputNoBot)) {
                        System.out.println("Теперь бот отвечает");
                        text.add("Теперь бот отвечает");
                        chattingNoBot = true;
                    } else if (OUT.equals(inputNoBot)) {
                        System.out.println("Закончили внутри второго цикла");
                        text.add("Закончили внутри второго цикла");
                        break flag;
                    }
                }
            }
        }
        saveLog(text);
    }


    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader input = (new BufferedReader(new FileReader("data/botanswers.txt", StandardCharsets.UTF_8)))) {
            while (input.ready()) {
                phrases.add(input.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path)))) {
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
