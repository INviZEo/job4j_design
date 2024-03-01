package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 22, "Dave", new Contact("1488-228"),
                new String[]{"Developer", "Java"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":77,"
                        + "\"name\":David,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(911)-001\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Senior\",\"Java\"]"
                        + "}";

        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }
}
