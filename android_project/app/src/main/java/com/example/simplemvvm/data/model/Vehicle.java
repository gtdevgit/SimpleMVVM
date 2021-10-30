package com.example.simplemvvm.data.model;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Vehicle {
    private final int id;
    private final String brand;
    private final String model;
    private final int mileage;
    private final int price;

    public Vehicle(int id, String brand, String model, int mileage, int price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public int getPrice() {
        return price;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "vehicle.id = [%d], " +
                        "vehicle.brand = [%s], " +
                        "vehicle.model = [%s], " +
                        "vehicle.mileage = [%d], " +
                        "vehicle.price=[%d]",
                        id, brand, model, mileage, price);
    }
}
