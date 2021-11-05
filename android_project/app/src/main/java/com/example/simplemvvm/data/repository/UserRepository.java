package com.example.simplemvvm.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.simplemvvm.data.model.User;
import com.example.simplemvvm.data.sample.SampleUser;

import java.util.Arrays;
import java.util.List;

public class UserRepository {

    public LiveData<List<User>> getUsersLiveData(){
        MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();
        usersLiveData.setValue(Arrays.asList(SampleUser.getUsers()));
        return usersLiveData;
    };

    private User findUserById(int id) {
        for (User user : SampleUser.getUsers()) {
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public LiveData<User> getUserByIdLiveData(int id){
        MutableLiveData<User> userLiveData = new MutableLiveData<>();

        User user = findUserById(id);
        if (user != null) {
            userLiveData.setValue(user);
            return userLiveData;
        }
        return null;
    }

    public LiveData<Integer> getFirstOrValidUserIdByIdLiveData(int id){
        MutableLiveData<Integer> userIdLiveData = new MutableLiveData<>();

        User user = findUserById(id);
        if (user == null) user = SampleUser.getUsers()[0];
        userIdLiveData.setValue(user.getId());
        return userIdLiveData;
    }

}
