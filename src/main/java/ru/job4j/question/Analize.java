package ru.job4j.question;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, String> map = current.stream().collect(Collectors.toMap(User::getId, User::getName));
        for (User mapPut : previous) {
            if (map.containsKey(mapPut.getId()) && !map.containsValue(mapPut.getName())) {
                info.setChanged(+1);
            }

            if (previous.size() > current.size() || !map.containsKey(mapPut.getId())) {
                info.setDeleted(+1);
            }

            info.setAdded(current.size() - (previous.size() - info.getDeleted()));
        }
        return info;
    }
}
