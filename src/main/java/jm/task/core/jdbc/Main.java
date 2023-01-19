package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 95);
        userService.saveUser("Petr", "Petrov", (byte) 25);
        userService.saveUser("Stepan", "Stepanov", (byte) 55);
        userService.saveUser("Maksim", "Maksimenko", (byte) 44);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
   }
}