package com.example.simplemvvm.ui.main_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.simplemvvm.R;
import com.example.simplemvvm.debug.Tag;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private final int USER_ID_NO_INITIALIZED = -1;

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

    private void configureViewModel() {
        mainViewModel = new ViewModelProvider(
                this,
                ViewModelFactory.getInstance()).get(MainViewModel.class);

        mainViewModel.getViewStateLiveData().observe(this, new Observer<MainViewState>() {
            @Override
            public void onChanged(MainViewState mainViewState) {
                Log.d("Tag.TAG", "MainActivity.configureViewModel -> onChanged() called with: detailViewState = [" + mainViewState + "]");

                textViewUserId.setText(mainViewState.getUserId());
                textViewUserName.setText(mainViewState.getUserName());
                textViewUserEmail.setText(mainViewState.getUserEmail());
                textViewUserPhone.setText(mainViewState.getUserPhone());

                textViewContractId.setText(mainViewState.getContractId());
                textViewContractUserId.setText(mainViewState.getContractUserId());
                textViewContractVehicleId.setText(mainViewState.getContractVehicleId());
                textViewContractDate.setText(mainViewState.getContractDate());
                ;

                textViewVehicleId.setText(mainViewState.getVehicleId());
                textViewVehicleBrand.setText(mainViewState.getVehicleBrand());
                textViewVehicleModel.setText(mainViewState.getVehicleModel());
                textViewVehiclePrice.setText(mainViewState.getVehiclePrice());
                textViewVehicleMileage.setText(mainViewState.getVehicleMileage());

                Log.d(Tag.TAG, "MainActivity.configureViewModel() called with: userItems = [" + mainViewState.getUserItems() + "]");

                ArrayAdapter userItemsAdapter = new ArrayAdapter(getMyContext(), R.layout.list_item, mainViewState.getUserItems());
                AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) textInputLayoutUsers.getEditText();
                // update text with current user name
                // must setText before adapter else setTest clear adapter
                autoCompleteTextView.setText(mainViewState.getUserName());
                // load users list in dropdown
                autoCompleteTextView.setAdapter(userItemsAdapter);

                // update list position with current user position
                if (mainViewState.getCurrentUserItemPosition() >= 0) {
                    autoCompleteTextView.setListSelection(mainViewState.getCurrentUserItemPosition());
                }

                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Log.d(Tag.TAG, "onItemClick()");
                        MainViewState.UserItem userItem = (MainViewState.UserItem) userItemsAdapter.getItem(position);
                        int userId = userItem.getId();
                        mainViewModel.setUserId(userId);
                    }
                });
            }
        });
        // Will be find and load first user.
        mainViewModel.setUserId(USER_ID_NO_INITIALIZED);
    }
}