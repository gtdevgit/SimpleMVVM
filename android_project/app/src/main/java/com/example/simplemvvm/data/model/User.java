package com.example.simplemvvm.data.model;

import androidx.annotation.NonNull;

import java.util.Locale;

public class User {
    private final int id;
    private final String name;
    private final String email;
    private final String phone;

    public User(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "user.id = [%d], " +
                        "user.name = [%s], " +
                        "user.email = [%s], " +
                        "user.phone =[%s]",
                id, name, email, phone);
    }
}
