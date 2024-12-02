package ru.otus.java.hw4;

public class User {

    public String name;
    public String surname;
    public String middleName;
    public int year;
    public String email;

    public User(String surname, String name, String middleName, int year, String email) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.year = year;
        this.email = email;
    }

    public static void printInfoUser(User user) {
        System.out.println("ФИО: " + user.surname + " " + user.name + " " + user.middleName + ". " + "Родился в " + user.year + " году." + "Email:" + user.email);
    }


    public static void sortUsers(User users[], int age, int currentYear) {
        System.out.println("Следующим лицам на данный год " + "(" + currentYear + ")" + " больше " + age + " лет");
        for (int i = 0; i < users.length; i++) {
            if ((currentYear - users[i].year) > age)
                printInfoUser(users[i]);
        }
        System.out.println(" ");
    }
}
