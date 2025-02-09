package ru.otus.java.hw10;
import java.util.*;

public class PhoneBook {
    Map<String, String> phoneBook = new HashMap<>();
    public void addPerson(String phoneNumber, String fio) {
        phoneBook.put(phoneNumber, fio);
    }

    public void find(String fio) {
        if (phoneBook.containsValue(fio)) {
            System.out.println("The phone number for this person: ");
            for (String key : phoneBook.keySet()) {
                String value = phoneBook.get(key);
                if (Objects.equals(value, fio)) {
                    System.out.println(key);
                }
            }
        } else System.out.println("No such person in the phone book");
    }

    public void containsPhoneNumber(String phoneNumber) {
        if (phoneBook.containsKey(phoneNumber)) {
            System.out.println("Such phone in the phone book");
        } else System.out.println("No such phone in the phone book");
    }
}


