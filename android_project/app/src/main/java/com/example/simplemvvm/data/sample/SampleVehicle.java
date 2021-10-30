package com.example.simplemvvm.data.sample;

import com.example.simplemvvm.data.model.Vehicle;

public class SampleVehicle {
    private static Vehicle[] vehicles = new Vehicle[]{
                new Vehicle(1, "Chevrolet", "Spark", 3500, 26),
                new Vehicle(2, "Ford", "Fiest", 4500, 23),
                new Vehicle(3, "Kia", "Rio", 30145, 19),
                new Vehicle(4, "Mazda", "CX-5.2", 2145, 47),
                new Vehicle(5, "Mitsubishi ", "Mirage", 10, 34),
                new Vehicle(6, "Mazda", "CX-5", 2145, 26),
                new Vehicle(7, "Ford", "Focus", 4751, 28),
                new Vehicle(8, "Nissan", "Versa", 402, 19),
                new Vehicle(9, "Kia", "Soul", 512, 23),
                new Vehicle(10, "Chevrolet", "Spark5", 2145, 26),
                new Vehicle(11, "Jeep ", "Compass", 703, 22),
                new Vehicle(12, "Ford", "Edge", 304, 29),
                new Vehicle(13, "Chevrolet ", "Equinox", 145, 32),
                new Vehicle(14, "Hyundai ", "Santa Fe", 214, 32),
                new Vehicle(15, "Mazda", "Mazda 3", 62, 28),
                new Vehicle(16, "Toyota", "Corola", 484, 28),
                new Vehicle(17, "Chevrolet", "Cruze", 1054, 28),
                new Vehicle(18, "Nissan", "Note", 789, 19),
                new Vehicle(19, "Nissan", "Pathfinder ", 212, 43),
                new Vehicle(20, "Hyundai ", "Elantra ", 52, 29)
    };

    public static Vehicle[] getVehicles() {
        return vehicles;
    }
}
