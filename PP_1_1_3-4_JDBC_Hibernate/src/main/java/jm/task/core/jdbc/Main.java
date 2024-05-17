package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Вася", "Пупкин", (byte) 78);
        userService.saveUser("Рома", "Иванов", (byte) 74);
        userService.saveUser("Костя", "Захаров", (byte) 59);
        userService.saveUser("Иван", "Путин", (byte) 74);

        userService.removeUserById(2);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
