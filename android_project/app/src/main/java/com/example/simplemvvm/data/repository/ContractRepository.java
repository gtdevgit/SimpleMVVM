package com.example.simplemvvm.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.simplemvvm.data.model.Contract;
import com.example.simplemvvm.data.model.User;
import com.example.simplemvvm.data.sample.SampleContract;
import com.example.simplemvvm.data.sample.SampleUser;

import java.util.Arrays;
import java.util.List;

public class ContractRepository {
    public LiveData<List<Contract>> getContractsLiveData(){
        MutableLiveData<List<Contract>> contractsMutableLiveData= new MutableLiveData<>();
        contractsMutableLiveData.setValue(Arrays.asList(SampleContract.getContracts()));
        return contractsMutableLiveData;
    }

    public LiveData<Contract> getContractByUserIdLiveData(int userId){
        MutableLiveData<Contract> contractLiveData = new MutableLiveData<>();

        for (Contract contract : SampleContract.getContracts()) {
            if (contract.getUserId() == userId){
                contractLiveData.setValue(contract);
                return contractLiveData;
            }
        }

        return null;
    }

}
