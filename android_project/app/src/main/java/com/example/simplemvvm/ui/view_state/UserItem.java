package com.example.simplemvvm.ui.view_state;

import androidx.annotation.NonNull;

public class UserItem {
    private final int id;
    private final String name;

    public UserItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * need toString to display user name in TextInputLayout with dropdown mode list
     * @return
     */
    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
