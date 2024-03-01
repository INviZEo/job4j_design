package ru.job4j.serialization.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;

public class Deserializator {

    public void deserialize() throws IOException {
        try (FileInputStream fis = new FileInputStream("data/serialize.data");
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            final Contact contactFromFile2 = (Contact) ois.readObject();
            System.out.println(contactFromFile);
            System.out.println(contactFromFile2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
