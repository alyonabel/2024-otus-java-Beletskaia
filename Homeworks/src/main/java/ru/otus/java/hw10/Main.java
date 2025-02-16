package ru.otus.java.hw10;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addPerson("Антон Павлович Чехов", List.of("125-56-45"));
        phoneBook.addPerson("Салтыков-Щедрин Михаил Евграфович", List.of("454-11-05"));
        phoneBook.addPerson("Гюстав Флобер", List.of("454-88-05","00-33-1-42-96-70-00"));
        phoneBook.find("Гюстав Флобер");
        phoneBook.containsPhoneNumber("125-56-45");
    }
}
