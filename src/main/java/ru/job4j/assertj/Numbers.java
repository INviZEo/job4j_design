package ru.job4j.assertj;

import java.util.Scanner;

public class Numbers {
    public static void main(String[] args) {
        Scanner d = new Scanner(System.in);
        while (true) {
            if (d.hasNextInt()) {
                if (d.nextInt() % 2 != 0) {
                    System.out.println("Число не четное");
                } else {
                    System.out.println("Число чётное");
                }
            } else {
                if (d.nextLine().equals("end")) {
                    break;
                }
            }
        }
    }
}
