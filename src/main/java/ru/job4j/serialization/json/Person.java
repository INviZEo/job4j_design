package ru.job4j.serialization.json;

import java.util.Arrays;

public class Person {
    private final boolean sex;
    private final int age;
    private final Contact contact;
    private final String[] statuses;
    private final String name;

    public Person(boolean sex, int age, String name, Contact contact, String[] statuses) {
        this.sex = sex;
        this.age = age;
        this.name = name;
        this.contact = contact;
        this.statuses = statuses;
    }

    public boolean getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getStatuses() {
        return statuses;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", name=" + name
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}