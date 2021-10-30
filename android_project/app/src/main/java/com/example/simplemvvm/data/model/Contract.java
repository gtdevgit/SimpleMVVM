package com.example.simplemvvm.data.model;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.Locale;

public class Contract {
    private final int id;
    private final int userId;
    private final int vehicleId;
    private final Date entryDate;

    public Contract(int id, int userId, int vehicleId, Date entryDate) {
        this.id = id;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.entryDate = entryDate;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "contract.id = [%d], " +
                "contract.userId = [%d], " +
                "contract.vehicleId = [%d], " +
                "contract.entryDate=[%s]",
                id, userId, vehicleId, entryDate);
    }
}
