package com.example.simplemvvm.data.sample;

import com.example.simplemvvm.data.model.User;

public class SampleUser {
    private static final User[] users = new User[]{
            new User(1, "Williams", "Williams@email.com", "646-268-3154"),
            new User(2, "Johnson", "Johnson@email.com", "607-624-9366"),
            new User(3, "Miller", "Miller@email.com", "716-804-7724"),
            new User(4, "Jones", "Jones@email.com", "518-752-3860"),
            new User(5, "Rodriguez", "Rodriguez@email.com", "646-446-9258"),
            new User(6, "Line", "Line@email.com", "914-321-0734"),
            new User(7, "Lee Vihn", "Lee.Vihn@email.com", "646-785-9722"),
            new User(8, "Davis", "Davis@email.com", "716-488-6426"),
            new User(9, "Rivera", "Rivera@email.com", "917-742-2959"),
            new User(10, "Smith", "Smith@email.com", "585-275-6747"),
            new User(11, "Brown", "Brown@email.com", "516-428-9478")
    };

    public static User[] getUsers(){
        return users;
    }
}
