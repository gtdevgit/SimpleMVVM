package com.example.simplemvvm.ui.view_model;



import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
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

    private MediatorLiveData<DetailViewState> viewStateMediatorLiveData = new MediatorLiveData<>();
    public LiveData<DetailViewState> getViewStateLiveData() {
        return viewStateMediatorLiveData;
    }

    public void setUserId(int userId){
        updateMediatorLiveData(userId);
    }

    private void updateMediatorLiveData(int userId){
        LiveData<Integer> userIdLiveData = dataRepository.getUserRepository().getFirstOrValidUserIdByIdLiveData(userId);
        LiveData<User> userLiveData = Transformations.switchMap(userIdLiveData,
                id -> {return dataRepository.getUserRepository().getUserByIdLiveData(id);});
        LiveData<Contract> contractLiveData = Transformations.switchMap(userIdLiveData,
                id -> {return dataRepository.getContractRepository().getContractByUserIdLiveData(id);});
        LiveData<Vehicle> vehicleLiveData = Transformations.switchMap(contractLiveData,
                contract -> {return dataRepository.getVehicleRepository().getVehiclesByIdLiveData(contract.getId());});

        LiveData<List<UserItem>> userItemsLiveData = Transformations.map(dataRepository.getUserRepository().getUsersLiveData(),
                users -> {
                    List<UserItem> items = new ArrayList<>();

                    for (User user : users){
                        Log.d("", "MainViewModel.getUsersLiveData() -> Transformation.map called with user = [" + user + "]");
                        items.add(new UserItem(user.getId(), user.getName()));
                    }
                    return items;
                });

        viewStateMediatorLiveData.addSource(userIdLiveData, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

            }
        });

        viewStateMediatorLiveData.addSource(userLiveData, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Log.d(Tag.TAG, "MainViewModel.getDetailViewStateLiveData->onChanged() called with: user = [" + user + "]");
                Log.d(Tag.TAG, "MainViewModel.getDetailViewStateLiveData call combine 1");
                combine(user, contractLiveData.getValue(), vehicleLiveData.getValue(), userItemsLiveData.getValue());
            }
        });

        viewStateMediatorLiveData.addSource(contractLiveData, new Observer<Contract>() {
            @Override
            public void onChanged(Contract contract) {
                Log.d(Tag.TAG, "MainViewModel.getDetailViewStateLiveData->onChanged() called with: contract = [" + contract + "]");
                Log.d(Tag.TAG, "MainViewModel.getDetailViewStateLiveData call combine 2");
                combine(userLiveData.getValue(), contract, vehicleLiveData.getValue(), userItemsLiveData.getValue());
            }
        });

        viewStateMediatorLiveData.addSource(vehicleLiveData, new Observer<Vehicle>() {
            @Override
            public void onChanged(Vehicle vehicle) {
                Log.d(Tag.TAG, "MainViewModel.getDetailViewStateLiveData->onChanged() called with: vehicle = [" + vehicle + "]");
                Log.d(Tag.TAG, "MainViewModel.getDetailViewStateLiveData call combine 3");
                combine(userLiveData.getValue(), contractLiveData.getValue(), vehicle, userItemsLiveData.getValue());
            }
        });

        viewStateMediatorLiveData.addSource(userItemsLiveData, new Observer<List<UserItem>>() {
            @Override
            public void onChanged(List<UserItem> userItems) {
                Log.d(Tag.TAG, "MainViewModel.getDetailViewStateLiveData->onChanged() called with: usersItems = [" + userItems + "]");
                Log.d(Tag.TAG, "MainViewModel.getDetailViewStateLiveData call combine 4");
                combine(userLiveData.getValue(), contractLiveData.getValue(), vehicleLiveData.getValue(), userItems);
            }
        });
    }

    private int findCurrentUserItemPosition(int userId, List<UserItem> userItems){
        for (int i = 0; i < userItems.size(); i++) {
            UserItem userItem = userItems.get(i);
            if (userItem.getId() == userId) return i;
        }
        return -1;
    }

    private void combine(User user, Contract contract, Vehicle vehicle, List<UserItem> userItems) {
        if ((user == null) || (contract == null) || (vehicle == null) || (userItems == null))
            return;

        Log.d(Tag.TAG, "MainViewModel.combine() called with: user = [" + user + "], " +
                "contract = [" + contract + "], " +
                "vehicle = ["+ vehicle + "]" +
                "userItems = [" + userItems + "]");

        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");

        int currentUserItemPosition = findCurrentUserItemPosition(user.getId(), userItems);
        Log.d(Tag.TAG, "combine() called with: currentUserItemPosition = [" + currentUserItemPosition + "]");

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
                sd.format(contract.getEntryDate()),
                userItems,
                currentUserItemPosition);

        viewStateMediatorLiveData.setValue(detailViewState);
    }
}
