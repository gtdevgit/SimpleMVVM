package com.example.simplemvvm.ui.view_model_factory;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplemvvm.debug.Tag;
import com.example.simplemvvm.ui.view_model.MainViewModel;

public class MainViewModelFactory implements ViewModelProvider.Factory{
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

    private volatile static MainViewModelFactory sInstance;

    public MainViewModelFactory() {
    }

    public static MainViewModelFactory getInstance() {
        Log.d(Tag.TAG, "MainViewModelFactory.getInstance() called");
        if (sInstance == null){
            synchronized (MainViewModelFactory.class) {
                if (sInstance == null) {
                    Log.d(Tag.TAG, "MainViewModelFactory.getInstance() create instance");
                    sInstance = new MainViewModelFactory();
                }
            }
        }

        return sInstance;
    }
}
