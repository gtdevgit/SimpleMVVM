package com.example.simplemvvm.ui.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.simplemvvm.R;
import com.example.simplemvvm.debug.Tag;
import com.example.simplemvvm.ui.view_model.MainViewModel;
import com.example.simplemvvm.ui.view_model_factory.MainViewModelFactory;
import com.example.simplemvvm.ui.view_state.DetailViewState;
import com.example.simplemvvm.ui.view_state.UserItem;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextInputLayout textInputLayoutUsers;

    TextView textViewUserId;
    TextView textViewUserName;
    TextView textViewUserEmail;
    TextView textViewUserPhone;

    TextView textViewContractId;
    TextView textViewContractUserId;
    TextView textViewContractVehicleId;
    TextView textViewContractDate;

    TextView textViewVehicleId;
    TextView textViewVehicleBrand;
    TextView textViewVehicleModel;
    TextView textViewVehiclePrice;
    TextView textViewVehicleMileage;

    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Tag.TAG", "MainActivity.onCreate() called");
        setContentView(R.layout.activity_main);

        configureComponents();
        configureViewModel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Tag.TAG", "MainActivity.onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Tag.TAG", "MainActivity.onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Tag.TAG", "MainActivity.onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Tag.TAG", "MainActivity.onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Tag.TAG", "MainActivity.onDestroy() called");
    }

    private void configureComponents() {

        textInputLayoutUsers = findViewById(R.id.textInputLayout_users);

        textViewUserId = findViewById(R.id.user_id_value);
        textViewUserName = findViewById(R.id.user_name_value);
        textViewUserEmail = findViewById(R.id.user_email_value);
        textViewUserPhone = findViewById(R.id.user_phone_value);

        textViewContractId = findViewById(R.id.contract_id_value);
        textViewContractUserId = findViewById(R.id.contract_user_id_value);
        textViewContractVehicleId = findViewById(R.id.contract_vehicle_id_value);
        textViewContractDate = findViewById(R.id.contract_entry_date_value);

        textViewVehicleId = findViewById(R.id.vehicle_id_value);
        textViewVehicleBrand = findViewById(R.id.vehicle_brand_value);
        textViewVehicleModel = findViewById(R.id.vehicle_model_value);
        textViewVehiclePrice = findViewById(R.id.vehicle_price_value);
        textViewVehicleMileage = findViewById(R.id.vehicle_mileage_value);
    }

    /**
     * to retrieve context in OnChanged call back
     * @return
     */
    private Context getMyContext(){
        return this;
    }

    private void configureViewModel(){
        mainViewModel = new ViewModelProvider(
                this,
                MainViewModelFactory.getInstance()).get(MainViewModel.class);

        // load list users in textInputLayout with dropDown mode
        mainViewModel.getUsersLiveData().observe(this, new Observer<List<UserItem>>() {
            @Override
            public void onChanged(List<UserItem> userItems) {
                Log.d(Tag.TAG, "MainActivity.onChanged() called with: userItems = [" + userItems + "]");
                ArrayAdapter userItemsAdapter = new ArrayAdapter(getMyContext(), R.layout.list_item, userItems);
                AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) textInputLayoutUsers.getEditText();
                autoCompleteTextView.setAdapter(userItemsAdapter);
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        UserItem userItem = (UserItem) userItemsAdapter.getItem(position);
                        int userId = userItem.getId();
                        mainViewModel.setUserId(userId);
                    }
                });
            }
        });

        // When user change
        mainViewModel.getUserIdLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                configureDetailByUserIdViewModel(integer);
            }
        });
    }

    private void configureDetailByUserIdViewModel(int userId){
        mainViewModel.getDetailViewStateByUserIdLiveData(userId).observe(this , new Observer<DetailViewState>() {
            @Override
            public void onChanged(DetailViewState detailViewState) {
                Log.d("Tag.TAG", "MainActivity.configureDetailViewModel -> onChanged() called with: detailViewState = [" + detailViewState + "]");
                textViewUserId.setText(detailViewState.getUserId());
                textViewUserName.setText(detailViewState.getUserName());
                textViewUserEmail.setText(detailViewState.getUserEmail());
                textViewUserPhone.setText(detailViewState.getUserPhone());

                textViewContractId.setText(detailViewState.getContractId());
                textViewContractUserId.setText(detailViewState.getContractUserId());
                textViewContractVehicleId.setText(detailViewState.getContractVehicleId());
                textViewContractDate.setText(detailViewState.getContractDate());;

                textViewVehicleId.setText(detailViewState.getVehicleId());
                textViewVehicleBrand.setText(detailViewState.getVehicleBrand());
                textViewVehicleModel.setText(detailViewState.getVehicleModel());
                textViewVehiclePrice.setText(detailViewState.getVehiclePrice());
                textViewVehicleMileage.setText(detailViewState.getVehicleMileage());
            }
        });
    }
}