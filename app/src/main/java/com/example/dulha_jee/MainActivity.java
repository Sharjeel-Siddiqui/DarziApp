package com.example.dulha_jee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.dulha_jee.api.ApiClient;
import com.example.dulha_jee.api.Iapi;
import com.example.dulha_jee.userlist.DatePickerFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import gr.net.maroulis.library.EasySplashScreen;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    AlertDialog.Builder builder;
    private static final String TAG = "TailorApp";
    EditText editText, C_Name, C_Number, C_OrderNumber, Customer_OrderDate, C_KaregarName;
    NavController navigation;
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView filter;
    Iapi iapi;
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iapi = ApiClient.getClient().create(Iapi.class);
        toolbar = findViewById(R.id.toolbar);
        filter = findViewById(R.id.filter);
        sharedPreference = new SharedPreference(MainActivity.this);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(MainActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.filter_dialog, viewGroup, false);

                C_Name = dialogView.findViewById(R.id.customer_name);
                C_Number = dialogView.findViewById(R.id.customer_number);
                C_OrderNumber = dialogView.findViewById(R.id.order_number);
                Customer_OrderDate = dialogView.findViewById(R.id.orderDate);
                C_KaregarName = dialogView.findViewById(R.id.karegar_name);


                dialogView.findViewById(R.id.datePicker).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogFragment datePicker = new DatePickerFragment();
                        datePicker.show(getSupportFragmentManager(), "date picker");
                    }
                });

                editText = dialogView.findViewById(R.id.orderDate);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                dialogView.findViewById(R.id.Search).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();

                        iapi.search("Bearer " + sharedPreference.getToken(), Customer_OrderDate.getText().toString(), C_Name.getText().toString(),
                                C_OrderNumber.getText().toString(),
                                C_KaregarName.getText().toString(), C_Number.getText().toString()).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Toast.makeText(MainActivity.this, "Success" + response.code(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            }
        });

        setToolbarhere("Dulha Jee");
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);
        String currentDateString = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        editText.setText(currentDateString);
    }

    public void setToolbar(String title) {
        toolbar.setTitle(title);
        filter.setVisibility(View.GONE);
    }

    public void setToolbarhere(String title) {
        toolbar.setTitle(title);
        filter.setVisibility(View.VISIBLE);
    }

    public void setToolbarVisibility(boolean isVisible) {
        if (isVisible) {
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

}