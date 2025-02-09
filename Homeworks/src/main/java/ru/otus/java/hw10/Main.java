package ru.otus.java.hw10;

public class Main {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addPerson("125-56-45", "Антон Павлович Чехов");
        phoneBook.addPerson("454-11-05", "Салтыков-Щедрин Михаил Евграфович");
        phoneBook.addPerson("454-88-05", "Гюстав Флобер");
        phoneBook.addPerson("00-33-1-42-96-70-00", "Гюстав Флобер");
        phoneBook.find("Гюстав Флобер");
        phoneBook.containsPhoneNumber("454-11-05");
    }
}
