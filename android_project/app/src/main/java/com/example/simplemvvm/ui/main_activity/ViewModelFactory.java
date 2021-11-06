package com.example.simplemvvm.ui.main_activity;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplemvvm.debug.Tag;

public class ViewModelFactory implements ViewModelProvider.Factory{
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            Log.d(Tag.TAG, "MainViewModelFactory.create() called with: modelClass = [" + modelClass + "]");
            Log.d(Tag.TAG, "MainViewModelFactory.create and return MainViewModel");
            return (T) new MainViewModel();
        }
        throw new IllegalArgumentException("Unknown ViewModel class : " + modelClass);
    }

    private volatile static ViewModelFactory sInstance;

    public ViewModelFactory() {
    }

    public static ViewModelFactory getInstance() {
        Log.d(Tag.TAG, "MainViewModelFactory.getInstance() called");
        if (sInstance == null){
            synchronized (ViewModelFactory.class) {
                if (sInstance == null) {
                    Log.d(Tag.TAG, "MainViewModelFactory.getInstance() create instance");
                    sInstance = new ViewModelFactory();
                }
            }
        }

        return sInstance;
    }
}
