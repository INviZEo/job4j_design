package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode="
                + zipCode
                + ", phone='"
                + phone
                + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Serializator serializator = new Serializator();
        serializator.serialize();
        Deserializator deserializator = new Deserializator();
        deserializator.deserialize();
    }
}
