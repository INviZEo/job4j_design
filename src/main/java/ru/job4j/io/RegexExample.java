package ru.job4j.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\S{1,}@\\S{1,}\\.\\S{1,}");

        String txtOne = "Job4j@a.ru @rc@diy122@@mail.ru gavno123@greedisgood.zalupa";
        Matcher matcherOne = pattern.matcher(txtOne);
        while (matcherOne.find()) {
            System.out.println(matcherOne.group());
        }
    }
}
