package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ToJSON {
    public static void main(String[] args) {

        JSONArray jsonContact = new JSONArray("[{\"phone\":\"+7(924)1448-111\"}]");

        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        final Person person = new Person(false, 77, "David", new Contact("+7(99949)-112"),
                new String[] {"Senior", "Java"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.getSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("name", person.getName());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(person));
    }
}
