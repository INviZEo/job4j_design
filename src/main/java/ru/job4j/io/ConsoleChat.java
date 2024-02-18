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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<String> text = new ArrayList<>();
        text = readPhrases();
        Random random = new Random();

        flag:
        while (!chatting) {
            System.out.print("Вы пишите: ");
            String userInput = scanner.nextLine();
            saveLog(text);
            int randomIndex = random.nextInt(readPhrases().size());
            System.out.println(randomIndex);
            String randomWord = readPhrases().get(randomIndex);
            System.out.println("Бот отвечает: " + randomWord);
            if (OUT.equals(userInput)) {
                chatting = true;
                System.out.println("Закончили из первого цикла");
            } else if (STOP.equals(userInput)) {
                System.out.println("Бот теперь молчит");
                boolean chattingNoBot = false;
                while (!chattingNoBot) {
                    System.out.print("Вы ведёте монолог: ");
                    String inputNoBot = scanner.nextLine();
                    if (CONTINUE.equals(inputNoBot)) {
                        System.out.println("Теперь бот отвечает");
                        chattingNoBot = true;
                    } else if (OUT.equals(inputNoBot)) {
                        System.out.println("Закончили внутри второго цикла");
                        break flag;
                    }
                }
            }
        }
    }


    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader input = (new BufferedReader(
                new FileReader("data/botanswers.txt", StandardCharsets.UTF_8)))) {
            phrases.add(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        String filePath = "data/dialog.txt";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Scanner scanner = new Scanner(System.in);
        try {
            FileWriter fileWriter = new FileWriter(filePath, StandardCharsets.UTF_8, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("[" + LocalDateTime.now().format(formatter) + "] Вы пишите: " + log.add(filePath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("", "data/botanswers.txt");
        consoleChat.run();
    }
}
