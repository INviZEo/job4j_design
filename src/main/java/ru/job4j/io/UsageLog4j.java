package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 7;
        int i = 100;
        short s = 39;
        long l = 1488L;
        char ch = 'c';
        double d = 232;
        float ss = 32f;
        boolean bool = true;
        LOG.debug("User info b : {}, i : {}, s : {}, l : {}, ch : {}, d : {}, ss : {}, bool : {}", b, i, s,
                l, ch, d, ss, bool);
    }
}
