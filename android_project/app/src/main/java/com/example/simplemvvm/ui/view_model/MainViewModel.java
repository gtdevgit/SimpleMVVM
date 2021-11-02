package com.example.simplemvvm.ui.view_model;



import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.simplemvvm.data.model.Contract;
import com.example.simplemvvm.data.model.User;
import com.example.simplemvvm.data.model.Vehicle;
import com.example.simplemvvm.data.repository.DataRepository;
import com.example.simplemvvm.debug.Tag;
import com.example.simplemvvm.ui.view_state.DetailViewState;
import com.example.simplemvvm.ui.view_state.UserItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private final int USER_ID_NOT_INITIALIZED = -1;

    private final DataRepository dataRepository = new DataRepository();

    public MainViewModel() {
        // Just for the log
        Log.d(Tag.TAG, "MainViewModel constructor");
    }

    public LiveData<List<UserItem>> getUsersLiveData(){
        Log.d(Tag.TAG, "MainViewModel.getUsersLiveData() called");
        // use transformation.map to convert users list to users items list.
        LiveData<List<UserItem>> userItemsLiveData = Transformations.map(dataRepository.getUserRepository().getUsersLiveData(),
                users -> {
                    List<UserItem> items = new ArrayList<>();

                    for (User user : users){
                        Log.d("", "MainViewModel.getUsersLiveData() -> Transformation.map called with user = [" + user + "]");
                        items.add(new UserItem(user.getId(), user.getName()));
                    }
                    return items;
                });
        return userItemsLiveData;
    }

    private MutableLiveData<Integer> userIdMutableLiveData = new MutableLiveData<>();
    public LiveData<Integer> getUserIdLiveData() {
        return userIdMutableLiveData;
    }

    public void setUserId(int id){
        userIdMutableLiveData.setValue(id);
    }

    public LiveData<DetailViewState> getDetailViewStateByUserIdLiveData(int id){
        MediatorLiveData<DetailViewState> mediatorLiveData = new MediatorLiveData();

        LiveData<User> userLiveData = dataRepository.getUserRepository().getUserByIdLiveData(id);

        LiveData<Contract> contractLiveData = dataRepository.getContractRepository().getContractByUserIdLiveData(id);

        // when we get contract, use Transformation.switchMap to get vehicle with contract.id
        LiveData<Vehicle> vehicleLiveData = Transformations.switchMap(contractLiveData,
                contract -> {return dataRepository.getVehicleRepository().getVehiclesByIdLiveData(contract.getId());});

        mediatorLiveData.addSource(userLiveData, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Log.d(Tag.TAG, "MainViewModel.getDetailViewStateLiveData->onChanged() called with: user = [" + user + "]");
                Log.d(Tag.TAG, "MainViewModel.getDetailViewStateLiveData call combine 1");
                combine(mediatorLiveData, user, contractLiveData.getValue(), vehicleLiveData.getValue());
            }
        });

        mediatorLiveData.addSource(contractLiveData, new Observer<Contract>() {
            @Override
            public void onChanged(Contract contract) {
                Log.d(Tag.TAG, "MainViewModel.getDetailViewStateLiveData->onChanged() called with: contract = [" + contract + "]");
                Log.d(Tag.TAG, "MainViewModel.getDetailViewStateLiveData call combine 2");
                combine(mediatorLiveData, userLiveData.getValue(), contract, vehicleLiveData.getValue());
            }
        });

        mediatorLiveData.addSource(vehicleLiveData, new Observer<Vehicle>() {
            @Override
            public void onChanged(Vehicle vehicle) {
                Log.d(Tag.TAG, "MainViewModel.getDetailViewStateLiveData->onChanged() called with: vehicle = [" + vehicle + "]");
                Log.d(Tag.TAG, "MainViewModel.getDetailViewStateLiveData call combine 3");
                combine(mediatorLiveData, userLiveData.getValue(), contractLiveData.getValue(), vehicle);
            }
        });

        return mediatorLiveData;
    }

    private void combine(MediatorLiveData mediatorLiveData, User user, Contract contract, Vehicle vehicle) {
        if ((user == null) || (contract == null) || (vehicle == null))
            return;

        Log.d(Tag.TAG, "MainViewModel.combine() called with: mediatorLiveData = [" + mediatorLiveData + "], user = [" + user + "], contract = [" + contract + "], vehicle = ["+ vehicle + "]");
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");

        DetailViewState detailViewState = new DetailViewState(
                Integer.toString(user.getId()),
                user.getName(),
                user.getEmail(),
                user.getName(),
                Integer.toString(vehicle.getId()),
                vehicle.getBrand(),
                vehicle.getModel(),
                Integer.toString(vehicle.getMileage()),
                Integer.toString(vehicle.getPrice()),
                Integer.toString(contract.getId()),
                Integer.toString(contract.getUserId()),
                Integer.toString(contract.getVehicleId()),
                sd.format(contract.getEntryDate()));

        mediatorLiveData.setValue(detailViewState);
    }
}
