package ru.job4j.serialization.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

public class Serializator {

    public void serialize() throws IOException {
        Contact contact = new Contact(321654, "+14-88-228-322");
        Contact contact2 = new Contact(123456, "+7-1345-333-7877");
        File tempFile = new File("data/serialize.data");
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
            oos.writeObject(contact2);
        }
    }
}