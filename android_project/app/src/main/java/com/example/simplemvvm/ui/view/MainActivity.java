package com.example.simplemvvm.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.simplemvvm.R;
import com.example.simplemvvm.ui.view_model.MainViewModel;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureComponents();
        configureViewModel();
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

    private Context getMyContext(){
        return this;
    }

    private void configureViewModel() {
        MainViewModel mainViewModel = new MainViewModel();

        mainViewModel.getUsersLiveData().observe(this, new Observer<List<UserItem>>() {
            @Override
            public void onChanged(List<UserItem> userItems) {
                Log.d("MainActivity", "onChanged() called with: userItems = [" + userItems + "]");
                ArrayAdapter userItemsAdapter = new ArrayAdapter(getMyContext(), R.layout.list_item, userItems);
                AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) textInputLayoutUsers.getEditText();
                autoCompleteTextView.setAdapter(userItemsAdapter);
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        UserItem userItem = (UserItem) userItemsAdapter.getItem(position);
                        int userId = userItem.getId();
                        configureDetailViewModel(mainViewModel, userId);
                    }
                });
            }
        });
    }

    private void configureDetailViewModel(MainViewModel mainViewModel, int userId) {
        mainViewModel.getDetailViewStateLiveData(userId).observe(this, new Observer<DetailViewState>() {
            @Override
            public void onChanged(DetailViewState detailViewState) {
                Log.d("MainActivity", "onChanged() called with: detailViewState = [" + detailViewState + "]");
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