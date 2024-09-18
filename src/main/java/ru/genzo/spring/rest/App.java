package ru.genzo.spring.rest;

import ru.genzo.spring.rest.entity.User;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
//
//        Communication communication = context.getBean("communication", Communication.class);
//
//        List<User> allUsers = communication.getAllUsers();
//        System.out.println(allUsers);


        Communication communication = new Communication();
        List<User> allUsers = communication.getAllUsers();

        User user = new User(3L, "James", "Brown", (byte) 25);
        communication.saveUser(user);

        user.setName("Thomas");
        user.setLastName("Shelby");
        communication.updateUser(user);

        communication.deleteUser(3L);
    }

}

