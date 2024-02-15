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
        String filePath = "data/dialog.txt";
        List<String> text = new ArrayList<>();

//        try {
//            FileWriter fileWriter = new FileWriter(filePath, StandardCharsets.UTF_8, true);
//            PrintWriter printWriter = new PrintWriter(fileWriter);

            flag:
            while (!chatting) {
                System.out.print("Вы пишите: ");
                String userInput = scanner.nextLine();
                saveLog(text);
//                readPhrases();
//                printWriter.println("[" + LocalDateTime.now().format(formatter) + "] Вы пишите: " + userInput);
                if (OUT.equals(userInput)) {
                    chatting = true;
                    System.out.println("Закончили из первого цикла");
                } else if (STOP.equals(userInput)) {
                    System.out.println("Бот теперь молчит");
                    boolean chattingNoBot = false;
                    while (!chattingNoBot) {
                        System.out.print("Вы ведёте монолог: ");
                        String inputNoBot = scanner.nextLine();
//                        printWriter.println();
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
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    private List<String> readPhrases() {
        try (BufferedReader input = (new BufferedReader(
                new FileReader("data/botanswers.txt", StandardCharsets.UTF_8)))) {
            input.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveLog(List<String> log) {
        String filePath = "data/dialog.txt";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Scanner scanner = new Scanner(System.in);
        try {
            FileWriter fileWriter = new FileWriter(filePath, StandardCharsets.UTF_8, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("[" + LocalDateTime.now().format(formatter) + "] Вы пишите: " + log); //( Не пойму как сделать этот метод )

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("", "data/botanswers.txt");
        consoleChat.run();
    }
}