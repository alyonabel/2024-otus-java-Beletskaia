package ru.otus.java.hw4;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        User [] users = new User[10];
        users[0] = new User("Мусоргский","Модест", "Петрович", 1839, "musorgskiy@yandex.ru");
        users[1] = new User("Чайковский","Пётр", "Ильич", 1840, "chaikovskiy@yandex.ru");
        users[2] = new User("Римский-Корсаков","Николай", "Андреевич", 1844, "rimskiy@yandex.ru");
        users[3] = new User("Хачатурян","Арам", "Ильич", 1903, "chachaturan@yandex.ru");
        users[4] = new User("Стравинский","Игорь", "Фёдорович", 1882, "stravinskiy@yandex.ru");
        users[5] = new User("Прокофьев","Сергей", "Сергеевич", 1891, "prokofev@yandex.ru");
        users[6] = new User("Гедике","Александр", "Фёдорович", 1877, "gedike@yandex.ru");
        users[7] = new User("Рахманинов","Сергей", "Васильевич", 1873, "rachmaninov@yandex.ru");
        users[8] = new User("Блантер","Матвей", "Исаакович", 1903, "blanter@yandex.ru");
        users[9] = new User("Лурье","Артур", "Сергеевич", 1892, "lurie@yandex.ru");
        User.sortUsers(users,40,1930);

        Box box = new Box(Color.gray,10,20,false,true);
        Box.repaint(box,Color.cyan);
        Box.fill();
        Box.fill();
        Box.clean();
        Box.open();
        Box.open();
        Box.close();

    }
}
