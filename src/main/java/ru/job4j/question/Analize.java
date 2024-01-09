package ru.job4j.question;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, User> map = new HashMap<>();
        boolean rsl = false;
        for (User prev: previous) {
            for (User curr: current) {
                if (prev.getId() == curr.getId() && !prev.getName().equals(curr.getName())) {
                    info.setChanged(info.getChanged() + 1);
                    break;
                }
            }
        }

        for (User curr : current) {
            rsl = false;
            for (User prev : previous) {
                if (curr.getId() == prev.getId()) {
                    rsl = true;
                    break;
                }
            }
            if (!rsl) {
                info.setAdded(info.getAdded() + 1);
            }
        }

        for (User prev : previous) {
            rsl = false;
            for (User curr : current) {
                if (prev.getId() == curr.getId()) {
                    rsl = true;
                    break;
                }
            }
            if (!rsl) {
                info.setDeleted(info.getDeleted() + 1);
            }
        }
            return info;
    }
}
