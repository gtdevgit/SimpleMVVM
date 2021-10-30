package com.example.simplemvvm.data.repository;

public class DataRepository {
    private final UserRepository userRepository = new UserRepository();
    private final VehicleRepository vehicleRepository = new VehicleRepository();
    private final ContractRepository contractRepository = new ContractRepository();

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    public ContractRepository getContractRepository() {
        return contractRepository;
    }
}
