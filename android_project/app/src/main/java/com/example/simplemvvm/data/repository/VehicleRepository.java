package com.example.simplemvvm.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.simplemvvm.data.model.User;
import com.example.simplemvvm.data.model.Vehicle;
import com.example.simplemvvm.data.sample.SampleContract;
import com.example.simplemvvm.data.sample.SampleUser;
import com.example.simplemvvm.data.sample.SampleVehicle;

import java.util.Arrays;
import java.util.List;

public class VehicleRepository {
    public LiveData<List<Vehicle>> getVehiclesLiveData(){
        MutableLiveData<List<Vehicle>> vehicleLiveData = new MutableLiveData<>();
        vehicleLiveData.setValue(Arrays.asList(SampleVehicle.getVehicles()));
        return vehicleLiveData;
    };

    public LiveData<Vehicle> getVehiclesByIdLiveData(int id){
        MutableLiveData<Vehicle> vehicleLiveData = new MutableLiveData<>();

        for (Vehicle vehicle : SampleVehicle.getVehicles()) {
            if (vehicle.getId() == id){
                vehicleLiveData.setValue(vehicle);
                return vehicleLiveData;
            }
        }

        return null;
    }
}
