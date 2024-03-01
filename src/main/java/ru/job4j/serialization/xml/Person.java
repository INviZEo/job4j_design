package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlAttribute
    private boolean sex;

    @XmlAttribute
    private int age;
    private String name;
    private Contact contact;

    private String[] statuses;

    public Person() {
    }

    public Person(boolean sex, int age, String name, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.name = name;
        this.contact = contact;
        this.statuses = statuses;
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


    public static void main(String[] args) throws JAXBException {

        final Person person = new Person(false, 77, "David", new Contact("+707-555"), "Senior", "Java");

        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            String result = writer.getBuffer().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

