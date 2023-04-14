package org.example;

import java.util.ArrayList;

public class Admin {
    public static ArrayList<User> users = new ArrayList<User>();

    public static void addUser(User u){users.add(u);}

    public static void deleteUser(User u){users.remove(u);}
}
