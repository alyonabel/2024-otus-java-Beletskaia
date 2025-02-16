package ru.otus.java.hw10;

import java.util.*;

public class PhoneBook {
    Map<String, List<String>> phoneBook = new HashMap<>();

    public void addPerson(String fio, List<String> phoneNumber) {
        phoneBook.put(fio, phoneNumber);
    }

    public void find(String fio) {
        if (phoneBook.containsKey(fio)) {
            System.out.println("The phone numbers for this person: ");
            for (String phoneNumber : phoneBook.get(fio)) {
                System.out.println(phoneNumber);
            }
        } else {
            System.out.println("No such person in the phone book");
        }
    }

    public void containsPhoneNumber(String phoneNumber) {
        boolean result = false;
        for (List<String> valueList : phoneBook.values()) {
            for (String value : valueList) {
                if (value.equals(phoneNumber)) {
                    result = true;
                    break;
                }
            }
        }
        if (result) {
            System.out.println("Such phone in the phone book");
        } else {
            System.out.println("No such phone in the phone book");
        }
    }
}







