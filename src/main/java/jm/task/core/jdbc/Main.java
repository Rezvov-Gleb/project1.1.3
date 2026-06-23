package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        //1. Создание таблицы User(ов)
        userService.createUsersTable();

        //2. Добавление 4 User(ов) в таблицу с данными на свой выбор.
        userService.saveUser("Michael", "Jordan", (byte) 23);
        userService.saveUser("Kobe", "Bryant", (byte) 24);
        userService.saveUser("Lebron", "James", (byte) 23);
        userService.saveUser("Derrick", "Rose", (byte) 19);

        //3. Получение всех User из базы и вывод в консоль
        userService.getAllUsers();

        //4. Очистка таблицы User(ов)
        userService.cleanUsersTable();

        //5. Удаление таблицы
        userService.dropUsersTable();

    }
}
