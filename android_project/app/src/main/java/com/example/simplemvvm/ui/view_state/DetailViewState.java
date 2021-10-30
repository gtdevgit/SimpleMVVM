package com.example.simplemvvm.ui.view_state;

import androidx.annotation.NonNull;

import java.util.Date;

public class DetailViewState {
    private final String userId;
    private final String userName;
    private final String userEmail;
    private final String userPhone;


    private final String vehicleId;
    private final String vehicleBrand;
    private final String vehicleModel;
    private final String vehicleMileage;
    private final String vehiclePrice;

    private final String contractId;
    private final String contractUserId;
    private final String contractVehicleId;
    private final String contractDate;

    public DetailViewState(String userId, String userName, String userEmail, String userPhone, String vehicleId, String vehicleBrand, String vehicleModel, String vehicleMileage, String vehiclePrice, String contractId, String contractUserId, String contractVehicleId, String contractDate) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.vehicleId = vehicleId;
        this.vehicleBrand = vehicleBrand;
        this.vehicleModel = vehicleModel;
        this.vehicleMileage = vehicleMileage;
        this.vehiclePrice = vehiclePrice;
        this.contractId = contractId;
        this.contractUserId = contractUserId;
        this.contractVehicleId = contractVehicleId;
        this.contractDate = contractDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getVehicleMileage() {
        return vehicleMileage;
    }

    public String getVehiclePrice() {
        return vehiclePrice;
    }

    public String getContractId() {
        return contractId;
    }

    public String getContractUserId() {
        return contractUserId;
    }

    public String getContractVehicleId() {
        return contractVehicleId;
    }

    public String getContractDate() {
        return contractDate;
    }
}
